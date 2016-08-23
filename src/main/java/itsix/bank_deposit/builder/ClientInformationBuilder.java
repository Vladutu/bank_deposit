package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.ClientInformation;
import itsix.bank_deposit.logic.IClientInformation;

import java.io.Serializable;

public class ClientInformationBuilder implements IClientInformationBuilder, Serializable {

    @Override
    public IClientInformation build(String ssn, String firstName, String lastName, String address) {
        return new ClientInformation(ssn, firstName, lastName, address);
    }

}
