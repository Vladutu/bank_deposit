package itsix.bank_deposit.validator;

public interface IClientValidator {

	IValidationResult validate(String ssn, String firstName, String lastName, String address);

	IValidationResult validate(String firstName, String lastName, String address);

}
