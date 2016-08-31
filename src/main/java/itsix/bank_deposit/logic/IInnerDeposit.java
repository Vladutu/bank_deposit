package itsix.bank_deposit.logic;

public interface IInnerDeposit {

	void update();

	IDate getCreationDate();

	int getPeriod();

	int getDaysLeft();

	float getInterest();

	int getDepositAmount();

	float getMoneyGained();

	ICurrency getCurrency();
}
