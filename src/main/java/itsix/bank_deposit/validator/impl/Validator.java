package itsix.bank_deposit.validator.impl;

import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.validator.IValidator;

import java.io.Serializable;

public class Validator implements IValidator, Serializable {

    private IValidationResultBuilder validationResultBuilder;

    private boolean success = true;

    private StringBuilder stringBuilder = new StringBuilder();

    public Validator(IValidationResultBuilder validationResultBuilder) {
        this.validationResultBuilder = validationResultBuilder;
    }

    @Override
    public IValidationResult buildResult() {
        return validationResultBuilder.buildResult(success, stringBuilder.toString());
    }

    @Override
    public void checkNullOrEmpty(String stringName, String stringValue) {
        if (stringValue.equals("") || stringValue == null) {
            success = false;
            stringBuilder.append(stringName + " must not be null or empty\n");
        }

    }

    @Override
    public void checkZero(String numberName, Number numverValue) {
        if (numverValue.equals(0) || numverValue.equals(0.0f)) {
            success = false;
            stringBuilder.append(numberName + " must not be zero \n");
        }

    }

    @Override
    public void checkNull(String objectName, Object objectValue) {
        if (objectValue == null) {
            success = false;
            stringBuilder.append(objectName + " must not be null \n");
        }

    }

    @Override
    public void checkGreaterThan(String greaterName, String smallerName, int greater, int smaller) {
        if (greater < smaller) {
            success = false;
            stringBuilder.append(greaterName + " must not be smaller than " + smallerName + "\n");
        }

    }

    @Override
    public void checkOnlyDigits(String stringName, String stringValue) {
        if (!stringValue.matches("\\d+")) {
            success = false;
            stringBuilder.append(stringName + " must contain only digits");
        }

    }

}
