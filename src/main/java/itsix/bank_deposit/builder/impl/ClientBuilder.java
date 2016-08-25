package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientInformation;
import itsix.bank_deposit.logic.impl.Client;
import itsix.bank_deposit.repository.ICurrencyRepository;

import java.io.Serializable;

public class ClientBuilder implements IClientBuilder, Serializable {

    private IClientInformationBuilder clientInformationBuilder;

    private IAccountBuilder accountBuilder;

    private ICurrencyRepository currencyRepository;

    public ClientBuilder(IClientInformationBuilder clientInformationBuilder, IAccountBuilder accountBuilder, ICurrencyRepository currencyRepository) {
        this.clientInformationBuilder = clientInformationBuilder;
        this.accountBuilder = accountBuilder;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public IClient build(String ssn, String firstName, String lastName, String address) {
        IClientInformation clientInformation = clientInformationBuilder.build(ssn, firstName, lastName, address);
        IAccount defaultAccount = accountBuilder.buildDefaultAccount();

        return new Client(clientInformation, defaultAccount, currencyRepository.getCurrencies());
    }

}
