package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class InnerDeposit implements IInnerDeposit {

	private IInterestCalculator interestCalculator;

	private int money;

	private IDate creationDate;

	private int period;

	private int daysLeft;

	private ICurrency currency;

	private float gainedMoney;

	private IInnerPublisher publisher;

	public InnerDeposit(IInnerPublisher publisher, ICurrency currency, IInterestCalculator interestCalculator,
			IDate creationDate, int money, int period) {
		this.interestCalculator = interestCalculator;
		this.publisher = publisher;
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

		publisher.notifySubscribers();
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

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);

	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);

	}
}
