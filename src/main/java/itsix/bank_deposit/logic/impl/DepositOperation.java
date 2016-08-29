package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class DepositOperation implements IOperation {

	private float debitBefore;

	private float debitAfter;

	private IDate date;

	public DepositOperation(float debitBefore, float debitAfter, IDate date) {
		this.debitBefore = debitBefore;
		this.debitAfter = debitAfter;
		this.date = date;
	}
}
