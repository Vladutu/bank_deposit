package itsix.bank_deposit.logic;

import java.util.List;

import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.publisher_subscriber.IPublisher;

public interface IAccount extends IPublisher {

	String getCurrencyName();

	String getCurrencySymbol();

	float getBalance();

	float getCreditBalance();

	float getDebitBalance();

	ICurrency getCurrency();

	void deposit(int money);

	void withdraw(int money) throws InvalidOperationException;

	List<ICurrency> subtractOwnCurrency(List<ICurrency> remainingCurrencies);

	boolean hasCurrency(ICurrency currency);

	boolean hasFunds(int money);

}
