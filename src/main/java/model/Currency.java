package model;

public class Currency {

	private Long id;
	private String name;
	private String symbol;
	private double conversionRatio; // to USD

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getConversionRatio() {
		return conversionRatio;
	}

	public void setConversionRatio(double conversionRatio) {
		this.conversionRatio = conversionRatio;
	}
}
