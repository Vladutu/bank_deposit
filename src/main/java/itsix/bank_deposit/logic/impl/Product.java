package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.repository.IDepositRepository;

import java.io.Serializable;

public class Product implements IProduct, Serializable {

    private String name;

    private int period;

    private float interestRate;

    private ICurrency currency;

    private int minSum;

    private int maxSum;

    private IInterestCalculator interestCalculator;

    private IDepositGenerator depositGenerator;

    private IDepositRepository depositRepository;

    public Product(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum,
                   IDepositGenerator depositGenerator, IInterestCalculator interestCalculator,
                   IDepositRepository depositRepository) {
        this.name = name;
        this.interestRate = interestRate;
        this.period = period;
        this.currency = currency;
        this.minSum = minSum;
        this.maxSum = maxSum;
        this.depositGenerator = depositGenerator;
        this.interestCalculator = interestCalculator;
        this.depositRepository = depositRepository;
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
}
