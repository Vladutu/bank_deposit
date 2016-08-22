package itsix.bank_deposit.repository;

import java.util.List;

import itsix.bank_deposit.logic.ICurrency;

public interface ICurrencyRepository {

	List<ICurrency> getCurrencies();

	ICurrency getDefaultCurrency();
}
