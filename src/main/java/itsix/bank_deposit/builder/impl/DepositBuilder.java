package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.logic.impl.NoRenewalDeposit;
import itsix.bank_deposit.logic.impl.RenewableDeposit;
import itsix.bank_deposit.logic.impl.RenewalCapitalizationDeposit;
import itsix.bank_deposit.logic.impl.RenewalNoCapitalizationDeposit;

/**
 * Created by Geo on 04.09.2016.
 */
public class DepositBuilder implements IDepositBuilder {

    private IInnerDepositBuilder innerDepositBuilder;

    private IDate currentDate;

    public DepositBuilder(IInnerDepositBuilder innerDepositBuilder, IDate currentDate) {
        this.innerDepositBuilder = innerDepositBuilder;
        this.currentDate = currentDate;
    }

    @Override
    public IDeposit buildNoRenewalDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency, IInterestCalculator interestCalculator, int money, int period) {
        IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
                currentDate.createClone(), period);
        return new NoRenewalDeposit(product, selectedClient, innerDeposit);
    }

    @Override
    public IDeposit buildRenewalNoCapitalizationDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency, IInterestCalculator interestCalculator, int money, int period) {
//        IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
//                currentDate.createClone(), period);
//        return new RenewalNoCapitalizationDeposit(product, selectedClient, innerDeposit);

        IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
                currentDate.createClone(), period);
        IRenewableDeposit renewalNoCapitalizationDeposit = new RenewalNoCapitalizationDeposit(product, selectedClient, innerDeposit);

        return new RenewableDeposit(renewalNoCapitalizationDeposit);
    }

    @Override
    public IDeposit buildRenewalCapitalizationDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency, IInterestCalculator interestCalculator, int money, int period) {
//        IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
//                currentDate.createClone(), period);
//        return new RenewalCapitalizationDeposit(product, selectedClient, innerDeposit);

        IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
                currentDate.createClone(), period);
        IRenewableDeposit renewalCapitalizationDeposit = new RenewalCapitalizationDeposit(product, selectedClient, innerDeposit);

        return new RenewableDeposit(renewalCapitalizationDeposit);
    }
}
