package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInterestCalculatorBuilder;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.impl.InterestCalculator;

public class InterestCalculatorBuilder implements IInterestCalculatorBuilder, Serializable {

	@Override
	public IInterestCalculator build(float interestRate) {
		return new InterestCalculator(interestRate);
	}

}
