package itsix.bank_deposit.validator.impl;

import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.validator.IDepositValidator;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.validator.IValidator;

public class DepositValidator implements IDepositValidator {

	private IValidator validator;

	private IValidatorBuilder validatorBuilder;

	public DepositValidator(IValidatorBuilder validatorBuilder) {
		this.validatorBuilder = validatorBuilder;
	}

	@Override
	public IValidationResult validate(IProduct selectedProduct, IClient selectedClient, int money) {
		validator = validatorBuilder.build();
		validator.checkMoneyInInterval(selectedProduct, money);
		validator.checkCanCreateDeposit(selectedClient, selectedProduct.getCurrency(), money);

		return validator.buildResult();
	}

}
