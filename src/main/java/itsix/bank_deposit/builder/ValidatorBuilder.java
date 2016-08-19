package itsix.bank_deposit.builder;

import itsix.bank_deposit.validator.IValidator;
import itsix.bank_deposit.validator.Validator;

public class ValidatorBuilder implements IValidatorBuilder {

	private IValidationResultBuilder validationResultBuilder;

	public ValidatorBuilder(IValidationResultBuilder validationResultBuilder) {
		this.validationResultBuilder = validationResultBuilder;
	}

	@Override
	public IValidator build() {
		return new Validator(validationResultBuilder);
	}

}
