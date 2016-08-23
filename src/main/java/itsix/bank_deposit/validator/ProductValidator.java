package itsix.bank_deposit.validator;

import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.logic.ICurrency;

import java.io.Serializable;

public class ProductValidator implements IProductValidator, Serializable {

    private IValidator validator;

    private IValidatorBuilder validatorBuilder;

    public ProductValidator(IValidatorBuilder validatorBuilder) {
        this.validatorBuilder = validatorBuilder;
    }

    @Override
    public IValidationResult validate(String name, float interestRate, int period, ICurrency currency, int minSum,
                                      int maxSum) {
        validator = validatorBuilder.build();

        validator.checkNullOrEmpty("name", name);
        validator.checkZero("interestRate", interestRate);
        validator.checkZero("period", period);
        validator.checkNull("currency", currency);
        validator.checkZero("minSum", minSum);
        validator.checkZero("maxSum", maxSum);
        validator.checkGreaterThan("maxSum", "minSum", maxSum, minSum);

        return validator.buildResult();
    }

}
