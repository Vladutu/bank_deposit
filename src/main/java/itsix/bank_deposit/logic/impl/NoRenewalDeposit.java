package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;

public class NoRenewalDeposit implements IDeposit {

	private IDeposit innerDeposit;

	public NoRenewalDeposit(IDeposit innerDeposit) {
		this.innerDeposit = innerDeposit;
	}

}
