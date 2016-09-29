package itsix.bank_deposit.logic;

public interface IDepositGenerator {

	IDepositGenerator getNextRenewalState();

	IDepositGenerator getNextCapitalizationState();

	IDepositGenerator getInitialState();

	void setNextRenewalState(IDepositGenerator generator);

	void setNextCapitalizationState(IDepositGenerator generator);

	void setInitialState(IDepositGenerator generator);

	IDeposit build(IInnerProduct product, IClient selectedClient, ICurrency currency,
			IInterestCalculator interestCalculator, int money, int period);

}
