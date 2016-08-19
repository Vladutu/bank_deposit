package itsix.bank_deposit.validator;

public interface IValidator {

	void checkNullOrEmpty(String stringName, String stringValue);

	void checkZero(String numberName, Number numberValue);

	void checkNull(String objectName, Object objectValue);

	void checkGreaterThan(String greaterName, String smallerName, int greater, int smaller);

	IValidationResult buildResult();
}
