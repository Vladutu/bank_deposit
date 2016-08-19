package itsix.bank_deposit.builder;

import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.validator.ValidationResult;

public class ValidationResultBuilder implements IValidationResultBuilder {

	@Override
	public IValidationResult buildResult(boolean success, String errorMessage) {
		return new ValidationResult(success, errorMessage);
	}

}
