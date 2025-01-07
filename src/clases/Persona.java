package clases;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String dni;
	private String nom;
	private int edad;
	
	public Persona(String d, String n, int e) {
		this.dni = d;
		this.nom = n;
		this.edad = e;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nom=" + nom + ", edad=" + edad + "]";
	}
}
