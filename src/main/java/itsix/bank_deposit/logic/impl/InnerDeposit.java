package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

public class InnerDeposit implements IInnerDeposit {

	private IInterestCalculator interestCalculator;

	private int money;

	private IDate creationDate;

	private int period;

	private int daysLeft;

	private ICurrency currency;

	private float gainedMoney;

	public InnerDeposit(ICurrency currency, IInterestCalculator interestCalculator, IDate creationDate, int money,
			int period) {
		this.interestCalculator = interestCalculator;
		this.creationDate = creationDate;
		this.money = money;
		this.period = period;
		this.daysLeft = period;
		this.currency = currency;
		gainedMoney = 0;
	}

	@Override
	public void update() {
		gainedMoney += interestCalculator.calculateDailyIncome(money);
		daysLeft--;
	}

	@Override
	public IDate getCreationDate() {
		return creationDate;
	}

	@Override
	public int getPeriod() {
		return period;
	}

	@Override
	public int getDaysLeft() {
		return daysLeft;
	}

	@Override
	public float getInterest() {
		return interestCalculator.getInterest();
	}

	@Override
	public int getDepositAmount() {
		return money;
	}

	@Override
	public float getMoneyGained() {
		return gainedMoney;
	}

	@Override
	public ICurrency getCurrency() {
		return currency;
	}
}
