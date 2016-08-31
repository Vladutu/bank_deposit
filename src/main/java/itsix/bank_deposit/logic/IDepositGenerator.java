package itsix.bank_deposit.logic;

public interface IDepositGenerator {

	IDepositGenerator getNextRenewalState();

	IDepositGenerator getNextCapitalizationState();

	IDepositGenerator getInitialState();

	void setNextRenewalState(IDepositGenerator generator);

	void setNextCapitalizationlState(IDepositGenerator generator);

	void setInitialState(IDepositGenerator generator);

	IDeposit build(IProduct product, IClient selectedClient, ICurrency currency, IInterestCalculator interestCalculator,
			int money, int period);

}
