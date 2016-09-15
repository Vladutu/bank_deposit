package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class RenewalNoCapitalizationDeposit implements IRenewableDeposit {

    private IInnerDeposit innerDeposit;

    private IClient client;

    private IInnerProduct product;

    private IDepositBuilder depositBuilder;

    public RenewalNoCapitalizationDeposit(IInnerProduct product, IClient client, IInnerDeposit innerDeposit, IDepositBuilder depositBuilder) {
        this.innerDeposit = innerDeposit;
        this.product = product;
        this.client = client;
        this.depositBuilder = depositBuilder;
    }

    @Override
    public void update() {
        innerDeposit.update();

        if (innerDeposit.hasMaturated()) {
            innerDeposit.depositInterest(client);
            product.renew(this);
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
        return true;
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
        innerDeposit.restart(interestCalculator);
    }

    @Override
    public IDeposit createNoRenewalClone(IDeposit parent) {
        return depositBuilder.buildNoRenewalDeposit(innerDeposit, client, product, parent);
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
