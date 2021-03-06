package itsix.bank_deposit.logic;

import java.util.List;

public interface IClient {

    boolean hasSsn(String ssn);

    String getSsn();

    String getFirstName();

    String getLastName();

    String getAddress();

    List<IAccount> getAccounts();

    void update(String firstName, String lastName, String address);

    void addAccount(IAccount account);

    boolean canCreateBankAccount();

    List<ICurrency> getRemainingCurrenciesForAccounts();

    void addDeposit(IDeposit deposit);

    boolean canCreateDeposit(ICurrency currency, int money);

    void withdrawMoney(ICurrency currency, int money);

    List<IDeposit> getDeposits();

    void removeDeposit(IDeposit deposit);

    void depositMoney(ICurrency currency, float money);
}
