package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IInterestCalculator;

public class InterestCalculator implements IInterestCalculator {

    private float interestRate;

    public InterestCalculator(float interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public float calculateDailyIncome(int money) {
        return (interestRate / 100 / 360) * money;
    }
}
