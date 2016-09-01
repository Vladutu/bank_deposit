package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IDepositGenerator;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;

public class RenewalNoCapitalizationDepositGenerator implements IDepositGenerator {

	private IDepositGenerator renewalState;

	private IDepositGenerator capitalizationState;

	private IDepositGenerator initialState;

	private IInnerDepositBuilder depositBuilder;

	private IDate currentDate;

	public RenewalNoCapitalizationDepositGenerator(IInnerDepositBuilder depositBuilder, IDate currentDate) {
		this.depositBuilder = depositBuilder;
		this.currentDate = currentDate;
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
		IInnerDeposit innerDeposit = depositBuilder.build(currency, interestCalculator, money,
				currentDate.createClone(), period);
		return new RenewalNoCapitalizationDeposit(product, selectedClient, innerDeposit);
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
