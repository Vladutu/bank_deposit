package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IClient;

public interface IClientBuilder {

	IClient build(String ssn, String firstName, String lastName, String address);

}
