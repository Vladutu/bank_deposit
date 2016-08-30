package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.ITimeScheduler;

import java.util.List;

public class TimeScheduler implements ITimeScheduler {

    private List<IDeposit> deposits;

    public TimeScheduler(List<IDeposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void start() {
        for (IDeposit deposit : deposits) {
            deposit.update();
        }
    }

}
