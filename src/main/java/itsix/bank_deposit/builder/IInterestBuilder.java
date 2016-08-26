package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IInterest;

public interface IInterestBuilder {

	IInterest build(float interestRate);

}
