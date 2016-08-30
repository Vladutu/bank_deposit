package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.*;

/**
 * Created by Geo on 30.08.2016.
 */
public interface IDepositBuilder {
    IDeposit build(IClient selectedClient, ICurrency currency, IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator, int money, IDate clone, int period);
}
