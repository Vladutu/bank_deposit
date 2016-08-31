package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.impl.Deposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class DepositBuilder implements IDepositBuilder {

	@Override
	public IInnerDeposit build(IClient selectedClient, ICurrency currency,
			IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator, int money,
			IDate clone, int period) {
		return new Deposit(selectedClient, currency, alwaysUpdatedInterestCalculator, interestCalculator, clone, money,
				period);
	}
}
