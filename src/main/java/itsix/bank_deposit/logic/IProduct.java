package itsix.bank_deposit.logic;

public interface IProduct {

	String description();

	String getName();

	float getInterestRate();

	int getPeriod();

	ICurrency getCurrency();

	int getMinSum();

	int getMaxSum();

	void update(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum);

}