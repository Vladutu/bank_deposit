package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IDepositGenerator;
import itsix.bank_deposit.logic.IInterestCalculator;

public class NoRenewalDepositGenerator implements IDepositGenerator {

	private IDepositGenerator renewalState;

	private IDepositGenerator capitalizationState;

	private IDepositGenerator initialState;

	@Override
	public IDepositGenerator getNextRenewalState() {
		return renewalState;
	}

	@Override
	public IDepositGenerator getNextCapitalizationState() {
		return capitalizationState;
	}

	@Override
	public IDepositGenerator getInitialState() {
		return initialState;
	}

	@Override
	public IDeposit build(IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator,
			int money) {
		return new NoRenewalDeposit(alwaysUpdatedInterestCalculator, interestCalculator, money);
	}

	@Override
	public void setNextRenewalState(IDepositGenerator generator) {
		this.renewalState = generator;
	}

	@Override
	public void setNextCapitalizationlState(IDepositGenerator generator) {
		this.capitalizationState = generator;

	}

	@Override
	public void setInitialState(IDepositGenerator generator) {
		this.initialState = generator;

	}

}
