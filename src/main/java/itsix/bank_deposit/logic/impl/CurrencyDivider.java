package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.ICurrencyDivider;
import itsix.bank_deposit.repository.ICurrencyRepository;

import java.util.ArrayList;
import java.util.List;

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
        List<ICurrency> ownedCurrencies = new ArrayList<>();
        selectedClient.populateCurrencies(ownedCurrencies);
        List<ICurrency> allCurrencies = currencyRepository.getCurrencies();
        allCurrencies.removeAll(ownedCurrencies);

        return allCurrencies;
    }
}
