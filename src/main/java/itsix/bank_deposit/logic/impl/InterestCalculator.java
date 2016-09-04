package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IInterestCalculator;

public class InterestCalculator implements IInterestCalculator {

	private float interestRate;

	public InterestCalculator(float interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public float calculateDailyIncome(float money) {
		return (interestRate / 100 / 360) * money;
	}

	@Override
	public float getInterest() {
		return interestRate;
	}

	@Override
	public void update(float interestRate) {
		this.interestRate = interestRate;
	}
}