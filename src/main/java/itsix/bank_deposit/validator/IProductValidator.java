package itsix.bank_deposit.validator;

import itsix.bank_deposit.logic.ICurrency;

public interface IProductValidator {

	IValidationResult validate(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum);

}
