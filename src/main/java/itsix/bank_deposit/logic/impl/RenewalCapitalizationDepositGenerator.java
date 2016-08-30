package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.*;

public class RenewalCapitalizationDepositGenerator implements IDepositGenerator {

    private IDepositGenerator renewalState;

    private IDepositGenerator capitalizationState;

    private IDepositGenerator initialState;

    private IDepositBuilder depositBuilder;

    private IDate currentDate;

    public RenewalCapitalizationDepositGenerator(IDepositBuilder depositBuilder, IDate currentDate) {
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
    public IDeposit build(IClient selectedClient, ICurrency currency, IInterestCalculator alwaysUpdatedInterestCalculator, IInterestCalculator interestCalculator,
                          int money, int period) {
        IDeposit innerDeposit = depositBuilder.build(selectedClient, currency, alwaysUpdatedInterestCalculator, interestCalculator, money, currentDate.createClone(), period);
        return new RenewalCapitalizationDeposit(innerDeposit);
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
