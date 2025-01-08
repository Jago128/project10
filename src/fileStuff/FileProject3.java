package fileStuff;

import java.io.*;
import utilidades.*;
import clases.*;

public class FileProject3 {

	public static void main(String[] args) {
		File file=new File("animales.dat");
		int menu=menu();

		switch (menu) {

		case 1:
			
			break;

		case 2:
			add(file);
			break;

		case 3:

			break;

		case 4:

			break;

		case 5:

			break;
		}

	}

	public static int menu() {
		System.out.println("1. Mostrar animales");
		System.out.println("2. Añadir un animal");
		System.out.println("3. Modificar la edad de un animal por su ID");
		System.out.println("4. Eliminar un animal por su ID");
		System.out.println("5. Salir");
		return Utilidades.leerInt("Elija una opcion:",1,5);
	}
	
	public static void show(File file) {
		ObjectInputStream ois=null;
		boolean end=false;
		
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void add(File file) {
		int more;
		MyObjectOutputStream moos=null;
		ObjectOutputStream oos=null;
		if (file.exists()) {
			try {
				System.out.println("El fichero ya existe, los nuevos datos se añadiran al final.");
				moos=new MyObjectOutputStream(new FileOutputStream(file,true));
				do {
					Animal a=new Animal();
					System.out.println("Introduce el ID:");
					a.setId(Utilidades.introducirCadena());
					System.out.println("Introduce el nombre:");
					a.setName(Utilidades.introducirCadena());
					System.out.println("Introduce la edad:");
					a.setAge(Utilidades.leerInt());
					moos.writeObject(a);
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
					Animal a=new Animal();
					System.out.println("Introduce el ID:");
					a.setId(Utilidades.introducirCadena());
					System.out.println("Introduce el nombre:");
					a.setName(Utilidades.introducirCadena());
					System.out.println("Introduce la edad:");
					a.setAge(Utilidades.leerInt());
					oos.writeObject(a);
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
}