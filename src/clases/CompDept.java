package clases;

public class CompDept implements Comparable<CompDept>{

	private String dept; 
	
	public CompDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public int compareTo(CompDept o) {

		return 0;
	}

}
