package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class DepositOperation implements IOperation, Serializable {

	private float debitBefore;

	private float debitAfter;

	private IDate date;

	public DepositOperation(float debitBefore, float debitAfter, IDate date) {
		this.debitBefore = debitBefore;
		this.debitAfter = debitAfter;
		this.date = date;
	}
}
