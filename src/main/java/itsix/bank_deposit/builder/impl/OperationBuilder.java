package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDateBuilder;
import itsix.bank_deposit.builder.IOperationBuilder;
import itsix.bank_deposit.logic.IOperation;
import itsix.bank_deposit.logic.impl.DepositOperation;
import itsix.bank_deposit.logic.impl.WithdrawOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class OperationBuilder implements IOperationBuilder {

	private IDateBuilder dateBuilder;

	public OperationBuilder(IDateBuilder dateBuilder) {
		this.dateBuilder = dateBuilder;
	}

	@Override
	public IOperation buildDepositOperation(float debitBefore, float debitAfter) {
		return new DepositOperation(debitBefore, debitAfter, dateBuilder.buildCurrentDate());
	}

	@Override
	public IOperation buildWithdrawOperation(float creditBefore, float creditAfter) {
		return new WithdrawOperation(creditBefore, creditAfter, dateBuilder.buildCurrentDate());
	}
}
