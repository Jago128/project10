package fileStuff;

import java.io.*;

import clases.*;
public class FileProject1 {

	public static void main(String[] args) {
		File fich=new File("Personas.dat");
		Persona1 p1=new Persona1("1234","Leire", 25);
		Persona1 p2=new Persona1("4321","Begoña", 26);
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		boolean end=false;
		
		
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
			while (!end) {
				try {
					Persona1 read=(Persona1)ois.readObject();
				
				System.out.println(read.toString());
				} catch (EOFException e) {
					end=true;
				}
			}
			ois.close();
		} catch (Exception e) {
			System.out.println("FATAL ERROR.");
		}
	}
}
