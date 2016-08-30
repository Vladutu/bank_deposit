package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.logic.impl.Deposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class DepositBuilder implements IDepositBuilder {

    @Override
    public IDeposit build(IClient selectedClient, ICurrency currency, IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator, int money, IDate clone, int period) {
        return new Deposit(selectedClient, currency, alwaysUpdatedInterestCalculator, interestCalculator, clone, money, period);
    }
}
