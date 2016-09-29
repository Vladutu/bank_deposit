package itsix.bank_deposit.validator;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IProduct;

public interface IDepositValidator {

	IValidationResult validate(IProduct selectedProduct, IClient selectedClient, int money);

}
