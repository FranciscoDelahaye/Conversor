package multiconversor.recursos;

public class Lista {
	private String name;
	private String symbol;
	private double equivalencia;
	
	public Lista(String name, String symbol) {
		if(name!=null && symbol!=null) {
			setName(name);
			setSymbol(symbol);
		}
	}

	public Lista(String name, String symbol, double equivalencia) {
		if(name!=null && symbol!=null && equivalencia!=0) {
			setName(name);
			setSymbol(symbol);
			setEquivalencia(equivalencia);
		}
	}
	
	private void setName(String name) {
		this.name = name;
	}
	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	private void setEquivalencia(double equivalencia) {
		this.equivalencia = equivalencia;
	}
	
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getEquivalencia() {
		return equivalencia;
	}
}