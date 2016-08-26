package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IInterest;

public class Interest implements IInterest {

	private float value;

	public Interest(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
