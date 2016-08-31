package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.builder.IDepositGeneratorBuilder;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDepositGenerator;
import itsix.bank_deposit.logic.impl.NoRenewalDepositGenerator;
import itsix.bank_deposit.logic.impl.RenewalCapitalizationDepositGenerator;
import itsix.bank_deposit.logic.impl.RenewalNoCapitalizationDepositGenerator;

public class DepositGeneratorBuilder implements IDepositGeneratorBuilder {

    private IDepositGenerator noRenewal;

    private IDepositGenerator renewalNoCap;

    private IDepositGenerator renewalCap;

    private IInnerDepositBuilder depositBuilder;

    private IDate currentDate;

    public DepositGeneratorBuilder(IInnerDepositBuilder depositBuilder, IDate currentDate) {
        this.depositBuilder = depositBuilder;
        this.currentDate = currentDate;
    }

    @Override
    public IDepositGenerator build() {
        noRenewal = new NoRenewalDepositGenerator(depositBuilder, currentDate);
        renewalNoCap = new RenewalNoCapitalizationDepositGenerator(depositBuilder, currentDate);
        renewalCap = new RenewalCapitalizationDepositGenerator(depositBuilder, currentDate);

        configureStates(noRenewal, noRenewal, renewalNoCap, noRenewal);
        configureStates(renewalNoCap, noRenewal, noRenewal, renewalCap);
        configureStates(renewalCap, noRenewal, noRenewal, renewalNoCap);

        return noRenewal;
    }

    private void configureStates(IDepositGenerator source, IDepositGenerator initialState,
                                 IDepositGenerator renewalState, IDepositGenerator capitalizationState) {
        source.setInitialState(initialState);
        source.setNextRenewalState(renewalState);
        source.setNextCapitalizationlState(capitalizationState);
    }

}
