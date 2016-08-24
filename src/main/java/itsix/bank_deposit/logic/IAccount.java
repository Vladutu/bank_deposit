package itsix.bank_deposit.logic;

import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.publisher_subscriber.IPublisher;

import java.util.List;

public interface IAccount extends IPublisher {

    String getCurrencyName();

    String getCurrencySymbol();

    int getBalance();

    int getCreditBalance();

    int getDebitBalance();

    ICurrency getCurrency();

    void deposit(int money);

    void withdraw(int money) throws InvalidOperationException;

    void removeExistingCurrencyFrom(List<ICurrency> currencies);
}
