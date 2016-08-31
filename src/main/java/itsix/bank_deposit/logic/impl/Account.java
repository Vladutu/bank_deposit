package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.builder.IOperationBuilder;
import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IOperation;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements IAccount, Serializable {

    private ICurrency currency;

    private float debitBalance;

    private float creditBalance;

    private IInnerPublisher publisher;

    private List<IOperation> executedOperations;

    private IOperationBuilder operationBuilder;

    public Account(ICurrency currency, IInnerPublisher publisher, IOperationBuilder operationBuilder) {
        this.currency = currency;
        this.publisher = publisher;
        this.operationBuilder = operationBuilder;
        executedOperations = new ArrayList<>();
        debitBalance = 0;
        creditBalance = 0;
    }

    public Account(ICurrency currency, int initialDeposit, IInnerPublisher publisher,
                   IOperationBuilder operationBuilder) {
        this.currency = currency;
        this.publisher = publisher;
        this.operationBuilder = operationBuilder;
        executedOperations = new ArrayList<>();
        debitBalance = initialDeposit;
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

    @Override
    public float getBalance() {
        return debitBalance - creditBalance;
    }

    @Override
    public float getCreditBalance() {
        return creditBalance;
    }

    @Override
    public float getDebitBalance() {
        return debitBalance;
    }

    @Override
    public ICurrency getCurrency() {
        return currency;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        publisher.subscribe(subscriber);

    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        publisher.unsubscribe(subscriber);

    }

    @Override
    public void deposit(float money) {
        IOperation operation = operationBuilder.buildDepositOperation(debitBalance, debitBalance + money);
        debitBalance += money;
        executedOperations.add(operation);

        publisher.notifySubscribers();
    }

    @Override
    public void withdraw(float money) throws InvalidOperationException {
        if (getBalance() < money) {
            throw new InvalidOperationException();
        }

        IOperation operation = operationBuilder.buildWithdrawOperation(creditBalance, creditBalance + money);
        creditBalance += money;
        executedOperations.add(operation);
        publisher.notifySubscribers();
    }

    @Override
    public List<ICurrency> subtractOwnCurrency(List<ICurrency> remainingCurrencies) {
        remainingCurrencies.remove(currency);

        return remainingCurrencies;
    }

    @Override
    public boolean hasCurrency(ICurrency currency) {
        return currency.equals(this.currency);
    }

    @Override
    public boolean hasFunds(float money) {
        return getBalance() >= money;
    }

}
