package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;

/**
 * Created by Geo on 24.08.2016.
 */
public class Date implements IDate, Cloneable {

	private int dayNumber;

	public Date(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	@Override
	public IDate createClone() {
		return new Date(dayNumber);
	}

	@Override
	public void increment() {
		dayNumber++;
	}

}
