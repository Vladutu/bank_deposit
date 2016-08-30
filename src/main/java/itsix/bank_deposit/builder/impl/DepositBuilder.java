package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.impl.Deposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class DepositBuilder implements IDepositBuilder {

    @Override
    public IDeposit build(IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator, int money, IDate clone) {
        return new Deposit(alwaysUpdatedInterestCalculator, interestCalculator, clone, money);
    }
}
