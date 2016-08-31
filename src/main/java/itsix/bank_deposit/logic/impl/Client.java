package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.logic.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements IClient, Serializable {

    private IClientInformation clientInformation;

    private List<IAccount> accounts = new ArrayList<>();

    private List<IDeposit> deposits = new ArrayList<>();

    private List<ICurrency> allCurrencies;

    public Client(IClientInformation clientInformation, IAccount defaultAccount, List<ICurrency> allCurrencies) {
        this.clientInformation = clientInformation;
        this.allCurrencies = allCurrencies;
        accounts.add(defaultAccount);
    }

    @Override
    public boolean hasSsn(String ssn) {
        return clientInformation.hasSsn(ssn);
    }

    @Override
    public String getSsn() {
        return clientInformation.getSsn();
    }

    @Override
    public String getFirstName() {
        return clientInformation.getFirstName();
    }

    @Override
    public String getLastName() {
        return clientInformation.getLastName();
    }

    @Override
    public String getAddress() {
        return clientInformation.getAddress();
    }

    @Override
    public List<IAccount> getAccounts() {
        return accounts;
    }

    @Override
    public void update(String firstName, String lastName, String address) {
        clientInformation.update(firstName, lastName, address);

    }

    @Override
    public void addAccount(IAccount account) {
        accounts.add(account);

    }

    @Override
    public boolean canCreateBankAccount() {
        return accounts.size() < allCurrencies.size();
    }

    @Override
    public List<ICurrency> getRemainingCurrenciesForAccounts() {
        List<ICurrency> remainingCurrencies = new ArrayList<>();
        remainingCurrencies.addAll(allCurrencies);

        for (IAccount account : accounts) {
            remainingCurrencies = account.subtractOwnCurrency(remainingCurrencies);
        }

        return remainingCurrencies;
    }

    @Override
    public void addDeposit(IDeposit deposit) {
        deposits.add(deposit);
    }

    @Override
    public boolean canCreateDeposit(ICurrency currency, int money) {
        for (IAccount account : accounts) {
            if (account.hasCurrency(currency) && account.hasFunds(money)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void withdrawMoney(ICurrency currency, int money) {
        for (IAccount account : accounts) {
            if (account.hasCurrency(currency)) {
                try {
                    account.withdraw(money);
                } catch (InvalidOperationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<IDeposit> getDeposits() {
        return deposits;
    }

    @Override
    public void removeDeposit(IDeposit deposit) {
        deposits.remove(deposit);
    }

    @Override
    public void depositMoney(ICurrency currency, float money) {
        for (IAccount account : accounts) {
            if (account.hasCurrency(currency)) {
                account.deposit(money);
            }
        }
    }

}
