package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

public class Deposit implements IInnerDeposit {

	@SuppressWarnings("unused")
	private IInterestCalculator alwaysUpdatedInterestCalculator;

	private IInterestCalculator interestCalculator;

	private int money;

	private IDate creationDate;

	private int period;

	private int daysLeft;

	private IClient client;

	private ICurrency currency;

	private float gainedMoney;

	public Deposit(IClient client, ICurrency currency, IInterestCalculator alwaysUpdatedInterestCalculator,
			IInterestCalculator interestCalculator, IDate creationDate, int money, int period) {
		this.alwaysUpdatedInterestCalculator = alwaysUpdatedInterestCalculator;
		this.interestCalculator = interestCalculator;
		this.creationDate = creationDate;
		this.money = money;
		this.period = period;
		this.daysLeft = period;
		this.currency = currency;
		this.client = client;
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
