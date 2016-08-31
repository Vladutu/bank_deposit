package itsix.bank_deposit.logic;

public interface IDeposit {

	void update();

	IDate getCreationDate();

	int getPeriod();

	int getDaysLeft();

	float getInterest();

	int getDepositAmount();

	float getMoneyGained();

	boolean getRenewal();

	boolean getCapitalization();

	ICurrency getCurrency();

}
