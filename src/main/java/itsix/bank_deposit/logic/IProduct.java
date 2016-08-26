package itsix.bank_deposit.logic;

public interface IProduct {

	String description();

	String getName();

	IInterest getInterestRate();

	int getPeriod();

	ICurrency getCurrency();

	int getMinSum();

	int getMaxSum();

	void update(String name, IInterest interestRate, int period, ICurrency currency, int minSum, int maxSum);

}
