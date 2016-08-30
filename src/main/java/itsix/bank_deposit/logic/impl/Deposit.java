package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

public class Deposit implements IDeposit {

	private IInterestCalculator alwaysUpdatedInterestCalculator;

	private IInterestCalculator interestCalculator;

	private int money;

	private IDate creationDate;

	public Deposit(IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator,
			IDate creationDate, int money) {
		this.alwaysUpdatedInterestCalculator = alwaysUpdatedInterestCalculator;
		this.interestCalculator = interestCalculator;
		this.creationDate = creationDate;
		this.money = money;
	}

}
