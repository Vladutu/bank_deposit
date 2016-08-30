package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IInterestCalculator;

public interface IInterestCalculatorBuilder {

	IInterestCalculator build(float interestRate);

}
