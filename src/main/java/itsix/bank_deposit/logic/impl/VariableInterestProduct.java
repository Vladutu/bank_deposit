package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

public class VariableInterestProduct implements IProduct, Serializable {

	private IProduct product;

	public VariableInterestProduct(IProduct product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return product.toString();
	}

	@Override
	public String description() {
		String description = product.description();
		description += "Interest type: Variable Interest\n";

		return description;
	}

	@Override
	public String getName() {
		return product.getName();
	}

	@Override
	public float getInterestRate() {
		return product.getInterestRate();
	}

	@Override
	public int getPeriod() {
		return product.getPeriod();
	}

	@Override
	public ICurrency getCurrency() {
		return product.getCurrency();
	}

	@Override
	public int getMinSum() {
		return product.getMinSum();
	}

	@Override
	public int getMaxSum() {
		return product.getMaxSum();
	}

	@Override
	public void update(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		product.update(name, interestRate, period, currency, minSum, maxSum);

	}
}
