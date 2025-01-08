package clases;

import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int age;
	
	public Animal() {
		this.id="";
		this.name="";
		this.age=0;
	}
	
	public Animal(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
