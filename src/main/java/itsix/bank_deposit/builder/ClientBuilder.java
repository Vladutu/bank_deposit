package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.Client;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientBuilder;
import itsix.bank_deposit.logic.IClientInformation;

public class ClientBuilder implements IClientBuilder {

	private IClientInformationBuilder clientInformationBuilder;

	private IAccountBuilder accountBuilder;

	public ClientBuilder(IClientInformationBuilder clientInformationBuilder, IAccountBuilder accountBuilder) {
		this.clientInformationBuilder = clientInformationBuilder;
		this.accountBuilder = accountBuilder;
	}

	@Override
	public IClient build(String ssn, String firstName, String lastName, String address) {
		IClientInformation clientInformation = clientInformationBuilder.build(ssn, firstName, lastName, address);
		IAccount defaultAccount = accountBuilder.buildDefaultAccount();

		return new Client(clientInformation, defaultAccount);
	}

}
