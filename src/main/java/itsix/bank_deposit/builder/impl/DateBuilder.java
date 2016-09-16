package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IDateBuilder;
import itsix.bank_deposit.logic.IDate;

/**
 * Created by Geo on 24.08.2016.
 */
public class DateBuilder implements IDateBuilder, Serializable {

	private IDate currentDate;

	public DateBuilder(IDate currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public IDate buildCurrentDate() {
		return currentDate.createClone();
	}
}
