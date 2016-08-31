package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class NoRenewalDeposit implements IDeposit {

    private IInnerDeposit innerDeposit;

    private IClient client;

    private IProduct product;

    public NoRenewalDeposit(IProduct product, IClient client, IInnerDeposit innerDeposit) {
        this.innerDeposit = innerDeposit;
        this.product = product;
        this.client = client;
    }

    @Override
    public void update() {
        innerDeposit.update();

        if (innerDeposit.hasMaturated()) {
            innerDeposit.depositAllMoney(client);
            client.removeDeposit(this);
            product.removeDeposit(this);
        }
    }

    @Override
    public IDate getCreationDate() {
        return innerDeposit.getCreationDate();
    }

    @Override
    public int getPeriod() {
        return innerDeposit.getPeriod();
    }

    @Override
    public int getDaysLeft() {
        return innerDeposit.getDaysLeft();
    }

    @Override
    public float getInterest() {
        return innerDeposit.getInterest();
    }

    @Override
    public float getDepositAmount() {
        return innerDeposit.getDepositAmount();
    }

    @Override
    public float getMoneyGained() {
        return innerDeposit.getMoneyGained();
    }

    @Override
    public boolean getRenewal() {
        return false;
    }

    @Override
    public boolean getCapitalization() {
        return false;
    }

    @Override
    public ICurrency getCurrency() {
        return innerDeposit.getCurrency();
    }

    @Override
    public void restart(IInterestCalculator interestCalculator) {
        //TODO: ask professor what to do here
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        innerDeposit.subscribe(subscriber);

    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        innerDeposit.unsubscribe(subscriber);

    }
}
