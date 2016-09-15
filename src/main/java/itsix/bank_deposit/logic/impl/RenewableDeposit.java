package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

/**
 * Created by Geo on 04.09.2016.
 */
public class RenewableDeposit implements IDeposit, ICloseableDeposit {

    private IDeposit deposit;

    private IRenewableDeposit renewableDeposit;

    public RenewableDeposit(IRenewableDeposit deposit) {
        this.deposit = deposit;
        this.renewableDeposit = deposit;
    }


    @Override
    public void subscribe(ISubscriber subscriber) {
        deposit.subscribe(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        deposit.unsubscribe(subscriber);
    }

    @Override
    public void update() {
        deposit.update();
    }

    @Override
    public IDate getCreationDate() {
        return deposit.getCreationDate();
    }

    @Override
    public int getPeriod() {
        return deposit.getPeriod();
    }

    @Override
    public int getDaysLeft() {
        return deposit.getDaysLeft();
    }

    @Override
    public float getInterest() {
        return deposit.getInterest();
    }

    @Override
    public float getDepositAmount() {
        return deposit.getDepositAmount();
    }

    @Override
    public float getMoneyGained() {
        return deposit.getMoneyGained();
    }

    @Override
    public boolean getRenewal() {
        return deposit.getRenewal();
    }

    @Override
    public boolean getCapitalization() {
        return deposit.getCapitalization();
    }

    @Override
    public ICurrency getCurrency() {
        return deposit.getCurrency();
    }

    @Override
    public void markForTermination() {
        deposit = renewableDeposit.createNoRenewalClone(this);
    }
}
