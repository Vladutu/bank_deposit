package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.builder.IOperationBuilder;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.impl.Account;
import itsix.bank_deposit.repository.ICurrencyRepository;

import java.io.Serializable;

public class AccountBuilder implements IAccountBuilder, Serializable {

    private ICurrencyRepository currencyRepository;

    private IInnerPublisherBuilder innerPublisherBuilder;

    private IOperationBuilder operationBuilder;

    public AccountBuilder(ICurrencyRepository currencyRepository, IInnerPublisherBuilder innerPublisherBuilder, IOperationBuilder operationBuilder) {
        this.currencyRepository = currencyRepository;
        this.innerPublisherBuilder = innerPublisherBuilder;
        this.operationBuilder = operationBuilder;
    }

    @Override
    public IAccount buildDefaultAccount() {
        return new Account(currencyRepository.getDefaultCurrency(), innerPublisherBuilder.build(), operationBuilder);
    }

}
