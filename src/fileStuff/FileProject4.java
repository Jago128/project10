package fileStuff;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import clases.Animal;
import utilidades.Utilidades;

public class FileProject4 {

	public static void main(String[] args) {
		File file=new File("animales2.dat");
		ArrayList<Animal> a=new ArrayList<>();
		int menu;

		if (!file.exists()) {
			System.out.println("El fichero ha sido creado.");
			fillData(file);
		}

		loadArray(file,a);

		do {
			menu=menu();
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
				loadFile(file,a);
				System.out.println("Adios!");
			}
		} while (menu!=5);
	}

	public static int menu() {
		System.out.println("1. Mostrar animales");
		System.out.println("2. Añadir un animal");
		System.out.println("3. Modificar la edad de un animal por su ID");
		System.out.println("4. Eliminar un animal por su ID");
		System.out.println("5. Salir");
		System.out.println("¿Que quieres hacer?");
		return Utilidades.leerInt(1,5);

	}

	public static void fillData(File file) {
		Animal a1=new Animal("1","Leon",2);
		Animal a2=new Animal("2","Cebra",3);
		ObjectOutputStream oos;

		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(a1);
			oos.writeObject(a2);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadFile(File file, ArrayList<Animal> a) {
		ObjectOutputStream oos;

		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			for (Animal an:a) {
				System.out.println(an);
				oos.writeObject(an);
			}
			oos.close();
			System.out.println("El fichero ha sido cargado.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadArray(File file, ArrayList<Animal> a) {
		boolean end=false;
		ObjectInputStream ois;

		try {
			ois=new ObjectInputStream(new FileInputStream(file));		
			while (!end) {
				try {
					Animal an=(Animal)ois.readObject();
					a.add(an);
				} catch (EOFException e) {
					end=true;
				}
			}
			ois.close();
		} catch (Exception e) {
			System.out.println("Fatal error");
		}
	}

	public static void show(ArrayList<Animal> a) {
		for (Animal an:a) {
			System.out.println(an);
		}
	}

	public static void add(ArrayList<Animal> a) {
		String id, name;
		int age;

		System.out.println("Introduce el ID:");
		id = Utilidades.introducirCadena();
		System.out.println("Introduce el nombre:");
		name=Utilidades.introducirCadena();
		System.out.println("Introduce la edad:");
		age=Utilidades.leerInt();
		a.add(new Animal(id,name,age));
	}

	public static void modify(ArrayList<Animal> a) {
		boolean modified=false;
		int age;

		System.out.println("Introduce el ID del animal:");
		String id=Utilidades.introducirCadena();
		for (Animal an:a) {
			if (an.getId().equals(id)) {
				System.out.println("Introduce la nueva edad:");
				age=Utilidades.leerInt();
				an.setAge(age);
				modified=true;
			}
		}
		if (modified) {
			System.out.println("El animal fue modificado.");
		} else {
			System.out.println("No existe un animal con el ID introducido.");
		}
	}

	public static void delete(ArrayList<Animal> a) {
		boolean deleted=false;

		System.out.println("Introduce el ID del animal: ");
		String id = Utilidades.introducirCadena();
		Iterator<Animal> i=a.iterator();

		while (i.hasNext()) {
			Animal animal=i.next();
			if (animal.getId().equals(id)) {
				i.remove();
				deleted=true;
			}
		}
		
		if (deleted) {
			System.out.println("El animal fue eliminado.");
		} else {
			System.out.println("No existe un animal con el ID introducido.");
		}

	}
}
