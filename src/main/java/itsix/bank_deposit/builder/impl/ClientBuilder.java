package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.logic.impl.Client;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientInformation;

import java.io.Serializable;

public class ClientBuilder implements IClientBuilder, Serializable {

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
