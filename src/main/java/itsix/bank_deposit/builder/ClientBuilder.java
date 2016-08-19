package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.Client;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientBuilder;
import itsix.bank_deposit.logic.IClientInformation;

public class ClientBuilder implements IClientBuilder {

	private IClientInformationBuilder clientInformationBuilder;

	public ClientBuilder(IClientInformationBuilder clientInformationBuilder) {
		this.clientInformationBuilder = clientInformationBuilder;
	}

	@Override
	public IClient build(String ssn, String firstName, String lastName, String address) {
		IClientInformation clientInformation = clientInformationBuilder.build(ssn, firstName, lastName, address);

		return new Client(clientInformation);
	}

}
