package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDeposit;

public class RenewalCapitalizationDeposit implements IDeposit {

    private IDeposit innerDeposit;

    public RenewalCapitalizationDeposit(IDeposit innerDeposit) {
        this.innerDeposit = innerDeposit;
    }

}
