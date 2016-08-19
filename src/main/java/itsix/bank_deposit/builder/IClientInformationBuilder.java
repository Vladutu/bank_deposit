package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IClientInformation;

public interface IClientInformationBuilder {

	IClientInformation build(String ssn, String firstName, String lastName, String address);

}
