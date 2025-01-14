package clases;

public class CompCateg implements Comparable<CompCateg> {
	
	private int cod;
	
	public CompCateg(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	@Override
	public int compareTo(CompCateg o) {
		
		return 0;
	}
}
