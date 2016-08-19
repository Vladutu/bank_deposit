package itsix.bank_deposit.logic;

public interface IClientBuilder {

	IClient build(String ssn, String firstName, String lastName, String address);

}
