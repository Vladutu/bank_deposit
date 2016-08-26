package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IInterestBuilder;
import itsix.bank_deposit.logic.IInterest;
import itsix.bank_deposit.logic.impl.Interest;

public class InterestBuilder implements IInterestBuilder {

	@Override
	public IInterest build(float interestRate) {
		return new Interest(interestRate);
	}

}
