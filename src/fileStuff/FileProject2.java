package fileStuff;

import java.io.*;

import clases.Persona1;
import utilidades.Utilidades;

public class FileProject2 {

	public static void main(String[] args) {
		Persona1 p1= new Persona1("1234","Leire",25);
		Persona1 p2= new Persona1("4321","Begona",26);

		File fich=new File("personas.dat");
		File fichAux=new File("personasAux.dat");
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;

		try {
			oos=new ObjectOutputStream(new FileOutputStream(fich));
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ois=new ObjectInputStream(new FileInputStream(fich));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			for (int i=0;i<Utilidades.calculoFichero(fich);i++) {
				Persona1 nueva=(Persona1)ois.readObject();
				System.out.println(nueva.toString());
				}
			ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		boolean finArchivo = false;
		try {	
			ois=new ObjectInputStream(new FileInputStream(fich));
			while (!finArchivo) {
				try {
					Persona1 aux=(Persona1)ois.readObject();
					System.out.println(aux.toString());				 
				} catch (EOFException e) {
					finArchivo = true;
				}
			}
			ois.close();	 

		} catch(Exception e) {
			System.out.println("Fatal error");
		}

		finArchivo = false;
		boolean modificado= false;
		System.out.println("Introduce tu dni: ");
		String dni= Utilidades.introducirCadena();
		try {	
			ois=new ObjectInputStream(new FileInputStream(fich));
			oos=new ObjectOutputStream(new FileOutputStream(fichAux));			
			while (!finArchivo) {
				try {
					Persona1 aux=(Persona1)ois.readObject();
					if (!aux.getDni().equals(dni)) {
						oos.writeObject(aux);
					} else {
						modificado=true;
					}
				} catch (EOFException e) {
					finArchivo = true;
				}
			}
			oos.close();
			ois.close();	 
			if (modificado) {
				if (fich.delete()) {
					fichAux.renameTo(fich);
				}
			}
		} catch (Exception e) {
			System.out.println("Fatal error");
		}	

		finArchivo = false;
		try {	
			ois=new ObjectInputStream(new FileInputStream(fich));
			while (!finArchivo) {
				try {
					Persona1 aux=(Persona1) ois.readObject();
					System.out.println(aux.toString());				 
				} catch (EOFException e) {
					finArchivo = true;
				}
			}
			ois.close();
		} catch(Exception e) {
			System.out.println("Fatal error");
		}
	}

}
