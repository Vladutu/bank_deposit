package itsix.bank_deposit.logic;

import java.io.Serializable;

public class Currency implements ICurrency, Serializable {

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
