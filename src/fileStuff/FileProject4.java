package fileStuff;

import java.io.*;
import java.util.ArrayList;
import clases.Animal;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;

public class FileProject4 {

	public static ArrayList<Animal> fillData() {
		ArrayList <Animal> a=new ArrayList<>();
		Animal a1=new Animal("1","Leon",3);
		Animal a2=new Animal("2","Cebra",2);
		a.add(a1);
		a.add(a2);
		return a;
	}

	public static void main(String[] args) {
		ArrayList <Animal> a=new ArrayList<>();
		a=fillData();
		int menu=menu();

		switch (menu) {
		case 1:
			show(a);
			break;

		case 2:
			add(a);
			break;

		case 3:
			modify(a);
			break;

		case 4:
			delete(a);
			break;

		case 5:
			System.out.println("Adios!");
		}
	}

	public static int menu() {
		System.out.println("1. Mostrar animales");
		System.out.println("2. Añadir un animal");
		System.out.println("3. Modificar la edad de un animal por su ID");
		System.out.println("4. Eliminar un animal por su ID");
		System.out.println("5. Salir");
		return Utilidades.leerInt(1,5);
	}

	public static File read(ArrayList <Animal> a) {
		File file=new File("animales2.dat");
		boolean end=false;
		ObjectOutputStream oos=null;
		while (!end) {
			try {
				oos=new ObjectOutputStream(new FileOutputStream(file));
				for (int i=0;i<a.size();i++) {
					oos.writeObject(a.get(i));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error fatal.");
			}
		}
		return file;
	}

	public static void show(ArrayList <Animal> a) {
		ObjectInputStream ois=null;
		boolean end=false;
		File file=read(a);
		try {
			ois=new ObjectInputStream(new FileInputStream(file));
			while (!end) {
				try {
					Animal read=(Animal)ois.readObject();
					System.out.println(read.toString());
				} catch (EOFException e) {
					end=true;
				}
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void add(ArrayList <Animal> a) {
		int more;
		File file=read(a);
		MyObjectOutputStream moos=null;
		ObjectOutputStream oos=null;
		if (file.exists()) {
			try {
				System.out.println("El fichero ya existe, los nuevos datos se añadiran al final.");
				moos=new MyObjectOutputStream(new FileOutputStream(file,true));
				do {
					Animal an=new Animal();
					System.out.println("Introduce el ID:");
					an.setId(Utilidades.introducirCadena());
					System.out.println("Introduce el nombre:");
					an.setName(Utilidades.introducirCadena());
					System.out.println("Introduce la edad:");
					an.setAge(Utilidades.leerInt());

					a.add(an);
					moos.writeObject(an);
					System.out.println("¿Quiere añadir mas animales? (1=Si/2=No)");
					more=Utilidades.leerInt(1,2);
				} while (more==1);
				moos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Se va a crear un nuevo fichero.");
			try {
				oos=new ObjectOutputStream(new FileOutputStream(file));
				do {
					Animal an=new Animal();
					System.out.println("Introduce el ID:");
					an.setId(Utilidades.introducirCadena());
					System.out.println("Introduce el nombre:");
					an.setName(Utilidades.introducirCadena());
					System.out.println("Introduce la edad:");
					an.setAge(Utilidades.leerInt());

					a.add(an);
					oos.writeObject(an);
					System.out.println("¿Quiere añadir mas animales? (1=Si/2=No)");
					more=Utilidades.leerInt(1,2);
				} while (more==1);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void modify(ArrayList <Animal> a) {
		File file=read(a);
		File filePH=new File("animalesAux.dat");
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		String id;
		int age, pos=0;
		boolean end=false, modified=false, found=false;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			ois=new ObjectInputStream(new FileInputStream(filePH));
			System.out.println("Introduce el ID del animal:");
			id=Utilidades.introducirCadena();
			while (!end) {
				for (int i=0;i<a.size()&&!found;i++) {
					if (a.get(i).getId().equals(id)) {
						pos=i;
						found=true;
					}
				}

				try {
					Animal ph=(Animal)ois.readObject();
					if (ph.getId().equals(id)) {
						System.out.println("Introduce la nueva edad:");
						age=Utilidades.leerInt();
						ph.setAge(age);
						modified=true;
						a.get(pos).setAge(age);
					}
					oos.writeObject(ph);
				} catch (EOFException e) {
					end=true;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			oos.close();
			ois.close();	 
			if (modified) {
				System.out.println("La edad del animal se ha modificado.");
				if (file.delete()) {
					filePH.renameTo(file);
				}
			} else {
				System.out.println("No existe ningun animal con el ID introducido.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void delete(ArrayList <Animal> a) {
		File file=read(a);
		File filePH=new File("animalesAux.dat");
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		boolean end=false, modified=false;

		String id;
		System.out.println("Introduce el ID del animal:");
		id=Utilidades.introducirCadena();
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			ois=new ObjectInputStream(new FileInputStream(filePH));
			while (!end) {
				try {
					Animal ph=(Animal)ois.readObject();
					if (!ph.getId().equals(id)) {
						oos.writeObject(ph);
					} else {
						modified=true;
					}
				} catch (EOFException e) {
					end=true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			oos.close();
			ois.close();	 
			if (modified) {
				System.out.println("El animal se ha eliminado.");
				if (file.delete()) {
					filePH.renameTo(file);
				}
			} else {
				System.out.println("No existe ningun animal con el ID introducido.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
