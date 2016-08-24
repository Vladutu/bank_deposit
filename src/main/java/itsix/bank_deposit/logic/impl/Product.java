package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

import java.io.Serializable;

public class Product implements IProduct, Serializable {

	private String name;

	private int period;

	private float interestRate;

	private ICurrency currency;

	private int minSum;

	private int maxSum;

	public Product(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		this.name = name;
		this.interestRate = interestRate;
		this.period = period;
		this.currency = currency;
		this.minSum = minSum;
		this.maxSum = maxSum;
	}

	@Override
	public String toString() {
		return name + "[" + interestRate + "%]";
	}

	@Override
	public String description() {
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ");
		builder.append(name);
		builder.append("\n");

		builder.append("Time period (in days): ");
		builder.append(period);
		builder.append("\n");

		builder.append("Interest rate: ");
		builder.append(interestRate);
		builder.append("%");
		builder.append("\n");

		builder.append("Currency: ");
		builder.append(currency);
		builder.append("\n");

		builder.append("Minimum sum: ");
		builder.append(minSum);
		builder.append("\n");

		builder.append("Maximum sum: ");
		builder.append(maxSum);
		builder.append("\n");

		return builder.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getInterestRate() {
		return interestRate;
	}

	@Override
	public int getPeriod() {
		return period;
	}

	@Override
	public ICurrency getCurrency() {
		return currency;
	}

	@Override
	public int getMinSum() {
		return minSum;
	}

	@Override
	public int getMaxSum() {
		return maxSum;
	}

	@Override
	public void update(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		this.name = name;
		this.interestRate = interestRate;
		this.period = period;
		this.currency = currency;
		this.minSum = minSum;
		this.maxSum = maxSum;
	}
}