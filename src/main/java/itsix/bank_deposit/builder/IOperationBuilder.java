package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public interface IOperationBuilder {
	IOperation buildDepositOperation(float debitBalance, float debitAfter);

	IOperation buildWithdrawOperation(float creditBefore, float creditAfter);
}
