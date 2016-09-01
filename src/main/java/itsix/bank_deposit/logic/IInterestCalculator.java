package itsix.bank_deposit.logic;

public interface IInterestCalculator {

	float calculateDailyIncome(float money);

	float getInterest();

	void update(float interestRate);
}
