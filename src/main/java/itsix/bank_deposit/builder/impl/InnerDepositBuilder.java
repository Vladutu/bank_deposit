package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.impl.InnerDeposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class InnerDepositBuilder implements IInnerDepositBuilder {

	private IInnerPublisherBuilder innerPublisherBuilder;

	public InnerDepositBuilder(IInnerPublisherBuilder innerPublisherBuilder) {
		this.innerPublisherBuilder = innerPublisherBuilder;
	}

	@Override
	public IInnerDeposit build(ICurrency currency, IInterestCalculator interestCalculator, int money, IDate clone,
			int period) {
		return new InnerDeposit(innerPublisherBuilder.build(), currency, interestCalculator, clone, money, period);
	}
}
