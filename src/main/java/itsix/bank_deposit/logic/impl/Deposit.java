package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.*;

public class Deposit implements IDeposit {

    private IInterestCalculator alwaysUpdatedInterestCalculator;

    private IInterestCalculator interestCalculator;

    private int money;

    private IDate creationDate;

    private int period;

    private int daysLeft;

    private IClient client;

    private ICurrency currency;

    public Deposit(IClient client, ICurrency currency, IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator,
                   IDate creationDate, int money, int period) {
        this.alwaysUpdatedInterestCalculator = alwaysUpdatedInterestCalculator;
        this.interestCalculator = interestCalculator;
        this.creationDate = creationDate;
        this.money = money;
        this.period = period;
        this.daysLeft = period;
        this.currency = currency;
        this.client = client;
    }

}
