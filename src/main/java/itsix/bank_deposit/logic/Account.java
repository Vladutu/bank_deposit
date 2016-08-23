package itsix.bank_deposit.logic;

import java.io.Serializable;

import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class Account implements IAccount, Serializable {

	private ICurrency currency;

	private int debitBalance;

	private int creditBalance;

	private IInnerPublisher publisher;

	public Account(ICurrency currency, IInnerPublisher publisher) {
		this.currency = currency;
		this.publisher = publisher;
		debitBalance = 0;
		creditBalance = 0;
	}

	@Override
	public String getCurrencyName() {
		return currency.getName();
	}

	@Override
	public String getCurrencySymbol() {
		return currency.getSymbol();
	}

	@Override
	public int getBalance() {
		return debitBalance - creditBalance;
	}

	@Override
	public int getCreditBalance() {
		return creditBalance;
	}

	@Override
	public int getDebitBalance() {
		return debitBalance;
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

	@Override
	public void deposit(int money) {
		debitBalance += money;
		publisher.notifySubscribers();
	}

	@Override
	public void withdraw(int money) throws InvalidOperationException {
		if (getBalance() < money) {
			throw new InvalidOperationException();
		}

		creditBalance += money;
		publisher.notifySubscribers();
	}
}
