package itsix.bank_deposit.builder;

import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.validator.ValidationResult;

import java.io.Serializable;

public class ValidationResultBuilder implements IValidationResultBuilder, Serializable {

    @Override
    public IValidationResult buildResult(boolean success, String errorMessage) {
        return new ValidationResult(success, errorMessage);
    }

}
