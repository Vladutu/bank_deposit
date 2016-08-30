package itsix.bank_deposit.validator;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

public interface IValidator {

	IValidationResult buildResult();

	void checkNullOrEmpty(String stringName, String stringValue);

	void checkZero(String numberName, Number numberValue);

	void checkNull(String objectName, Object objectValue);

	void checkGreaterThan(String greaterName, String smallerName, int greater, int smaller);

	void checkOnlyDigits(String stringName, String stringValue);

	void checkMoneyInInterval(IProduct selectedProduct, int money);

	void checkCanCreateDeposit(IClient selectedClient, ICurrency currency, int money);

}
