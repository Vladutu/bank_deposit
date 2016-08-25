package itsix.bank_deposit.repository.impl;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.impl.Currency;
import itsix.bank_deposit.repository.ICurrencyRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyRepository implements ICurrencyRepository, Serializable {

    private List<ICurrency> currencies;

    public CurrencyRepository() {
        currencies = new ArrayList<>();
        currencies.add(new Currency("Leu", "RON"));
        currencies.add(new Currency("Dollar", "$"));
        currencies.add(new Currency("Euro", "\u20ac"));
    }

    @Override
    public List<ICurrency> getCurrencies() {
        return Collections.unmodifiableList(currencies);
    }

    @Override
    public ICurrency getDefaultCurrency() {
        return currencies.get(0);
    }
}
