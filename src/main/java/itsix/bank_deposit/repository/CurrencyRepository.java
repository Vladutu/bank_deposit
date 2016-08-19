package itsix.bank_deposit.repository;

import java.util.ArrayList;
import java.util.List;

import itsix.bank_deposit.logic.Currency;
import itsix.bank_deposit.logic.ICurrency;

public class CurrencyRepository implements ICurrencyRepository {

	private List<ICurrency> currencies;

	public CurrencyRepository() {
		// TODO: remove this from here after you add the serialization

		currencies = new ArrayList<>();
		currencies.add(new Currency("Leu", "RON"));
		currencies.add(new Currency("Dollar", "$"));
		currencies.add(new Currency("Euro", "€"));
	}

	@Override
	public List<ICurrency> getCurrencies() {
		return currencies;
	}
}
