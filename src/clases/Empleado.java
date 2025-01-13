package clases;

public class Empleado extends Persona {
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String dept;
	private int codCategProf;
	
	public Empleado(String name, String surN, String dni, int code, String dept, int codCategProf) {
		super(name, surN, dni);
		this.code = code;
		this.dept = dept;
		this.codCategProf = codCategProf;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getCodCategProf() {
		return codCategProf;
	}

	public void setCodCategProf(int codCategProf) {
		this.codCategProf = codCategProf;
	}

	@Override
	public String toString() {
		return "Empleado [code=" + code + ", dept=" + dept + ", codCategProf=" + codCategProf + ", toString()=" + super.toString() + "]";
	}
	
}
