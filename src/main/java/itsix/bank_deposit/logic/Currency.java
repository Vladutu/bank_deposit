package itsix.bank_deposit.logic;

public class Currency implements ICurrency {

	private String name;

	private String symbol;

	public Currency(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return name + " [" + symbol + "]";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}
}
