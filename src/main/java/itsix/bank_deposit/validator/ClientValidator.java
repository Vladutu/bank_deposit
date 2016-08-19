package itsix.bank_deposit.validator;

import itsix.bank_deposit.builder.IValidatorBuilder;

public class ClientValidator implements IClientValidator {

	private IValidator validator;

	private IValidatorBuilder validatorBuilder;

	public ClientValidator(IValidatorBuilder validatorBuilder) {
		this.validatorBuilder = validatorBuilder;
	}

	@Override
	public IValidationResult validate(String ssn, String firstName, String lastName, String address) {
		validator = validatorBuilder.build();
		validator.checkNullOrEmpty("ssn", ssn);
		validator.checkOnlyDigits("ssn", ssn);
		validator.checkNullOrEmpty("first name", firstName);
		validator.checkNullOrEmpty("last name", lastName);
		validator.checkNullOrEmpty("address", address);

		return validator.buildResult();
	}

}
