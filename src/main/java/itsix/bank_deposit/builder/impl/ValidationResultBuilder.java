package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.validator.impl.ValidationResult;

import java.io.Serializable;

public class ValidationResultBuilder implements IValidationResultBuilder, Serializable {

    @Override
    public IValidationResult buildResult(boolean success, String errorMessage) {
        return new ValidationResult(success, errorMessage);
    }

}
