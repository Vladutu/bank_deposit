package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientInformation;
import itsix.bank_deposit.logic.ICurrency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements IClient, Serializable {

    private IClientInformation clientInformation;

    private List<IAccount> accounts = new ArrayList<>();

    public Client(IClientInformation clientInformation, IAccount defaultAccount) {
        this.clientInformation = clientInformation;
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
    public void removeExistingCurrenciesFrom(List<ICurrency> currencies) {
        for (IAccount account : accounts) {
            account.removeExistingCurrencyFrom(currencies);
        }
    }


}
