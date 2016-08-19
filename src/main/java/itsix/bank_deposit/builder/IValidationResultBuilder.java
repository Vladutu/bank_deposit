package itsix.bank_deposit.builder;

import itsix.bank_deposit.validator.IValidationResult;

public interface IValidationResultBuilder {

	IValidationResult buildResult(boolean success, String string);

}
