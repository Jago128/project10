package clases;

public class Categoria {
	private int codeC;
	private String desc;
	private double salary;
	
	public Categoria(int codeC, String desc, double salary) {
		this.codeC = codeC;
		this.desc = desc;
		this.salary = salary;
	}

	public int getCodeC() {
		return codeC;
	}

	public void setCodeC(int codeC) {
		this.codeC = codeC;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Categoria [codeC=" + codeC + ", desc=" + desc + ", salary=" + salary + "]";
	}
	
}
