package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IDepositGenerator;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;

public class NoRenewalDepositGenerator implements IDepositGenerator, Serializable {

	private IDepositGenerator renewalState;

	private IDepositGenerator capitalizationState;

	private IDepositGenerator initialState;

	private IDepositBuilder depositBuilder;

	public NoRenewalDepositGenerator(IDepositBuilder depositBuilder) {
		this.depositBuilder = depositBuilder;
	}

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
	public IDeposit build(IInnerProduct product, IClient selectedClient, ICurrency currency,
			IInterestCalculator interestCalculator, int money, int period) {
		return depositBuilder.buildNoRenewalDeposit(product, selectedClient, currency, interestCalculator, money,
				period);
	}

	@Override
	public void setNextRenewalState(IDepositGenerator generator) {
		this.renewalState = generator;
	}

	@Override
	public void setNextCapitalizationState(IDepositGenerator generator) {
		this.capitalizationState = generator;

	}

	@Override
	public void setInitialState(IDepositGenerator generator) {
		this.initialState = generator;

	}

}
