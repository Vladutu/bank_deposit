package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDecimalFormatter;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.impl.InnerDeposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class InnerDepositBuilder implements IInnerDepositBuilder, Serializable {

	private IInnerPublisherBuilder innerPublisherBuilder;

	private IDecimalFormatter decimalFormatter;

	public InnerDepositBuilder(IInnerPublisherBuilder innerPublisherBuilder, IDecimalFormatter decimalFormatter) {
		this.innerPublisherBuilder = innerPublisherBuilder;
		this.decimalFormatter = decimalFormatter;
	}

	@Override
	public IInnerDeposit build(ICurrency currency, IInterestCalculator interestCalculator, int money, IDate clone,
			int period) {
		return new InnerDeposit(decimalFormatter, innerPublisherBuilder.build(), currency, interestCalculator, clone,
				money, period);
	}
}
