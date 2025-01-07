package FileStuff;

import java.io.*;

import clases.*;
public class FileProject1 {

	public static void main(String[] args) {
		File fich=new File("Personas.dat");
		Persona p1=new Persona("1234","Leire", 25);
		Persona p2=new Persona("4321","Begoña", 26);
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(fich));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.close();
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

			Persona read;
			try {
				read = (Persona)ois.readObject();
				while (read!=null) {
				System.out.println(read.toString());
				read=(Persona)ois.readObject();
			}
			} catch (EOFException e1) {
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		


	}
}
