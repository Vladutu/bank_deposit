package itsix.bank_deposit.validator;

import java.io.Serializable;

public class ValidationResult implements IValidationResult, Serializable {

    private boolean success;

    private String errorMessage;

    public ValidationResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid() {
        return success;
    }

    @Override
    public String getErrorDescription() {
        return errorMessage;
    }

}
