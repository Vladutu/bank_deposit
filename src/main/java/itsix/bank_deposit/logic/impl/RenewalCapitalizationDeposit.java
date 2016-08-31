package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerDeposit;

public class RenewalCapitalizationDeposit implements IDeposit {

	private IInnerDeposit innerDeposit;

	private IClient client;

	public RenewalCapitalizationDeposit(IClient client, IInnerDeposit innerDeposit) {
		this.innerDeposit = innerDeposit;
		this.client = client;
	}

	@Override
	public void update() {
		innerDeposit.update();
		// TODO: add termination for this deposit
	}

	@Override
	public IDate getCreationDate() {
		return innerDeposit.getCreationDate();
	}

	@Override
	public int getPeriod() {
		return innerDeposit.getPeriod();
	}

	@Override
	public int getDaysLeft() {
		return innerDeposit.getDaysLeft();
	}

	@Override
	public float getInterest() {
		return innerDeposit.getInterest();
	}

	@Override
	public int getDepositAmount() {
		return innerDeposit.getDepositAmount();
	}

	@Override
	public float getMoneyGained() {
		return innerDeposit.getMoneyGained();
	}

	@Override
	public boolean getRenewal() {
		return true;
	}

	@Override
	public boolean getCapitalization() {
		return true;
	}

	@Override
	public ICurrency getCurrency() {
		return innerDeposit.getCurrency();
	}
}
