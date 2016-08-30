package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;

public class RenewalNoCapitalizationDeposit implements IDeposit {

    private IDeposit innerDeposit;

    public RenewalNoCapitalizationDeposit(IDeposit innerDeposit) {
        this.innerDeposit = innerDeposit;
    }

}
