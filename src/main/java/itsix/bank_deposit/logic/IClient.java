package itsix.bank_deposit.logic;

import java.util.List;

public interface IClient {

	boolean hasSsn(String ssn);

	String getSsn();

	String getFirstName();

	String getLastName();

	String getAddress();

	List<IAccount> getAccounts();

}
