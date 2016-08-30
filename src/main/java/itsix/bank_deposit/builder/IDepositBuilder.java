package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

/**
 * Created by Geo on 30.08.2016.
 */
public interface IDepositBuilder {
    IDeposit build(IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator, int money, IDate clone);
}
