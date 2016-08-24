package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.validator.IValidator;
import itsix.bank_deposit.validator.impl.Validator;

import java.io.Serializable;

public class ValidatorBuilder implements IValidatorBuilder, Serializable {

    private IValidationResultBuilder validationResultBuilder;

    public ValidatorBuilder(IValidationResultBuilder validationResultBuilder) {
        this.validationResultBuilder = validationResultBuilder;
    }

    @Override
    public IValidator build() {
        return new Validator(validationResultBuilder);
    }

}
