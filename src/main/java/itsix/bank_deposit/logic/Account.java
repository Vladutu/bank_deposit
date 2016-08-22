package itsix.bank_deposit.logic;

public class Account implements IAccount {

	private ICurrency currency;

	private int debitBalance;

	private int creditBalance;

	public Account(ICurrency currency) {
		this.currency = currency;
		debitBalance = 0;
		creditBalance = 0;
	}

	@Override
	public String getCurrencyName() {
		return currency.getName();
	}

	@Override
	public String getCurrencySymbol() {
		return currency.getSymbol();
	}
}
