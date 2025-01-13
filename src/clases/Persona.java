package clases;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surN;
	private String dni;
	
	public Persona(String name, String surN, String dni) {
		this.name = name;
		this.surN = surN;
		this.dni = dni;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurN() {
		return surN;
	}

	public void setSurN(String surN) {
		this.surN = surN;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Persona [name=" + name + ", surN=" + surN + ", dni=" + dni + "]";
	}
}
