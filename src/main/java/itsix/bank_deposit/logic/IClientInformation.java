package itsix.bank_deposit.logic;

public interface IClientInformation {

	boolean hasSsn(String ssn);

	String getSsn();

	String getFirstName();

	String getLastName();

	String getAddress();

	void update(String firstName, String lastName, String address);

}
