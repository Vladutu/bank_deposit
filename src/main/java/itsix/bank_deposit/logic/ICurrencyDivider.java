package itsix.bank_deposit.logic;

import java.util.List;

/**
 * Created by Geo on 24.08.2016.
 */
public interface ICurrencyDivider {
    List<ICurrency> getRemainingCurrencies(IClient selectedClient);
}
