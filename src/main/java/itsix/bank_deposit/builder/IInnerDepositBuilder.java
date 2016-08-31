package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;

/**
 * Created by Geo on 30.08.2016.
 */
public interface IInnerDepositBuilder {
	IInnerDeposit build(ICurrency currency, IInterestCalculator interestCalculator, int money, IDate clone, int period);
}
