package itsix.bank_deposit.logic.impl;

import java.util.ArrayList;
import java.util.List;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.ICurrencyDivider;
import itsix.bank_deposit.repository.ICurrencyRepository;

/**
 * Created by Geo on 24.08.2016.
 */
public class CurrencyDivider implements ICurrencyDivider {

	private ICurrencyRepository currencyRepository;

	public CurrencyDivider(ICurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public List<ICurrency> getRemainingCurrencies(IClient selectedClient) {
		List<ICurrency> currencies = currencyRepository.getCurrencies();
		List<ICurrency> notUsedCurrencies = new ArrayList<>();
		notUsedCurrencies.addAll(currencies);

		return selectedClient.removeExistingCurrenciesFrom(notUsedCurrencies);
	}
}
