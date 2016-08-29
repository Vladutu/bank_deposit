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
}
