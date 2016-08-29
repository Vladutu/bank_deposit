package itsix.bank_deposit.logic;

public interface IDepositGenerator {

	IDepositGenerator getNextRenewalState();

	IDepositGenerator getNextCapitalizationState();

	IDepositGenerator getInitialState();

	void setNextRenewalState(IDepositGenerator generator);

	void setNextCapitalizationlState(IDepositGenerator generator);

	void setInitialState(IDepositGenerator generator);

	IDeposit create(IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator,
			int money);

}
