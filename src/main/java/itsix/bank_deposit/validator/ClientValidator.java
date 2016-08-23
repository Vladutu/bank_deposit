package itsix.bank_deposit.validator;

import itsix.bank_deposit.builder.IValidatorBuilder;

import java.io.Serializable;

public class ClientValidator implements IClientValidator, Serializable {

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

    @Override
    public IValidationResult validate(String firstName, String lastName, String address) {
        validator = validatorBuilder.build();
        validator.checkNullOrEmpty("firstName", firstName);
        validator.checkNullOrEmpty("lastName", lastName);
        validator.checkNullOrEmpty("address", address);

        return validator.buildResult();
    }

}
