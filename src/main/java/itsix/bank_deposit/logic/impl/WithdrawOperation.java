package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class WithdrawOperation implements IOperation, Serializable {

	private float creditBefore;

	private float creditAfter;

	private IDate iDate;

	public WithdrawOperation(float creditBefore, float creditAfter, IDate iDate) {
		this.creditBefore = creditBefore;
		this.creditAfter = creditAfter;
		this.iDate = iDate;
	}
}
