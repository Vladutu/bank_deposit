package itsix.bank_deposit.logic;

import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.publisher_subscriber.IPublisher;

import java.util.List;

public interface IAccount extends IPublisher {

    String getCurrencyName();

    String getCurrencySymbol();

    float getBalance();

    float getCreditBalance();

    float getDebitBalance();

    ICurrency getCurrency();

    void deposit(float money);

    void withdraw(float money) throws InvalidOperationException;

    List<ICurrency> subtractOwnCurrency(List<ICurrency> remainingCurrencies);

    boolean hasCurrency(ICurrency currency);

    boolean hasFunds(float money);

}
