package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IProduct;

public class FixedInterestProduct implements IProduct, Serializable {

	private IInnerProduct product;

	public FixedInterestProduct(IInnerProduct product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return product.toString();
	}

	@Override
	public String description() {
		String description = product.description();
		description += "Interest type: Fixed Interest\n";

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
		product.updateAndReplaceInterestCalculator(name, interestRate, period, currency, minSum, maxSum);

	}

	@Override
	public void generatorRenewalState() {
		product.generatorRenewalState();
	}

	@Override
	public void generatorCapitalizationState() {
		product.generatorCapitalizationState();
	}

	@Override
	public void generatorReset() {
		product.generatorReset();
	}

	@Override
	public void createDeposit(IClient selectedClient, int money) {
		product.createDeposit(selectedClient, money);
	}

	@Override
	public boolean canCreateWith(int money) {
		return product.canCreateWith(money);
	}

	@Override
	public void removeDeposit(IDeposit deposit) {
		product.removeDeposit(deposit);
	}

	@Override
	public void renew(IDeposit deposit) {
		product.renew(deposit);
	}
}
