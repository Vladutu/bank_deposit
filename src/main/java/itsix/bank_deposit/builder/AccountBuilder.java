package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.Account;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.repository.ICurrencyRepository;

import java.io.Serializable;

public class AccountBuilder implements IAccountBuilder, Serializable {

	private ICurrencyRepository currencyRepository;

	private IInnerPublisherBuilder innerPublisherBuilder;

	public AccountBuilder(ICurrencyRepository currencyRepository, IInnerPublisherBuilder innerPublisherBuilder) {
		this.currencyRepository = currencyRepository;
		this.innerPublisherBuilder = innerPublisherBuilder;
	}

	@Override
	public IAccount buildDefaultAccount() {
		return new Account(currencyRepository.getDefaultCurrency(), innerPublisherBuilder.build());
	}

}
