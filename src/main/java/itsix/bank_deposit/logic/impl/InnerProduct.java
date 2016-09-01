package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInterestCalculatorBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IDepositGenerator;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.repository.IDepositRepository;

public class InnerProduct implements IInnerProduct, Serializable {

	private String name;

	private int period;

	private float interestRate;

	private ICurrency currency;

	private int minSum;

	private int maxSum;

	private IInterestCalculator interestCalculator;

	private IDepositGenerator depositGenerator;

	private IDepositRepository depositRepository;

	private IInterestCalculatorBuilder interestCalculatorBuilder;

	public InnerProduct(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum,
			IDepositGenerator depositGenerator, IInterestCalculator interestCalculator,
			IDepositRepository depositRepository, IInterestCalculatorBuilder interestCalculatorBuilder) {
		update(name, interestRate, period, currency, minSum, maxSum);
		this.depositGenerator = depositGenerator;
		this.interestCalculator = interestCalculator;
		this.depositRepository = depositRepository;
		this.interestCalculatorBuilder = interestCalculatorBuilder;
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
	public void updateWithInterestCalculator(String name, float interestRate, int period, ICurrency currency,
			int minSum, int maxSum) {
		update(name, interestRate, period, currency, minSum, maxSum);

		interestCalculator.update(interestRate);
	}

	@Override
	public void generatorRenewalState() {
		depositGenerator = depositGenerator.getNextRenewalState();

	}

	@Override
	public void generatorCapitalizationState() {
		depositGenerator = depositGenerator.getNextCapitalizationState();

	}

	@Override
	public void generatorReset() {
		depositGenerator = depositGenerator.getInitialState();

	}

	@Override
	public void createDeposit(IClient selectedClient, int money) {
		selectedClient.withdrawMoney(getCurrency(), money);
		IDeposit deposit = depositGenerator.build(this, selectedClient, currency, interestCalculator, money, period);
		selectedClient.addDeposit(deposit);
		depositRepository.addDeposit(deposit);
	}

	@Override
	public boolean canCreateWith(int money) {
		return (minSum <= money && maxSum >= money);
	}

	@Override
	public void removeDeposit(IDeposit deposit) {
		depositRepository.remove(deposit);
	}

	@Override
	public void renew(IDeposit deposit) {
		deposit.restart(interestCalculator);
	}

	@Override
	public void updateAndReplaceInterestCalculator(String name, float interestRate, int period, ICurrency currency,
			int minSum, int maxSum) {
		update(name, interestRate, period, currency, minSum, maxSum);

		interestCalculator = interestCalculatorBuilder.build(interestRate);

	}

	private void update(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		this.name = name;
		this.interestRate = interestRate;
		this.period = period;
		this.currency = currency;
		this.minSum = minSum;
		this.maxSum = maxSum;
	}

}
