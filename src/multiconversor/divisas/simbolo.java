package multiconversor.divisas;

public class simbolo {
	private String name;
	private String symbol;
	
	public simbolo(String name, String symbol) {
		if(name!=null && symbol!=null) {
			setName(name);
			setSymbol(symbol);
		}
	}
	
	private void setName(String name) {
		this.name = name;
	}
	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
}