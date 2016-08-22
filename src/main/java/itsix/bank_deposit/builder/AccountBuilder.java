package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.Account;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.repository.ICurrencyRepository;

public class AccountBuilder implements IAccountBuilder {

	private ICurrencyRepository currencyRepository;

	public AccountBuilder(ICurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public IAccount buildDefaultAccount() {
		return new Account(currencyRepository.getDefaultCurrency());
	}

}
