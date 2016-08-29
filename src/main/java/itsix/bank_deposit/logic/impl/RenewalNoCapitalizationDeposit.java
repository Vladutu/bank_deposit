package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

public class RenewalNoCapitalizationDeposit implements IDeposit {

	private IInterestCalculator alwaysUpdatedInterestCalculator;

	private IInterestCalculator interestCalculator;

	private int money;

	public RenewalNoCapitalizationDeposit(IInterestCalculator alwaysUpdatedInterestCalculator,
			IInterestCalculator interestCalculator, int money) {
		this.alwaysUpdatedInterestCalculator = alwaysUpdatedInterestCalculator;
		this.interestCalculator = interestCalculator;
		this.money = money;
	}

}
