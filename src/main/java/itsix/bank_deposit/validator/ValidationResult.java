package itsix.bank_deposit.validator;

public class ValidationResult implements IValidationResult {

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
