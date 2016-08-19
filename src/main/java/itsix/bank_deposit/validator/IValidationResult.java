package itsix.bank_deposit.validator;

public interface IValidationResult {

	boolean isValid();

	String getErrorDescription();

}
