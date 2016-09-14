package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.*;

/**
 * Created by Geo on 04.09.2016.
 */
public interface IDepositBuilder {

    IDeposit buildNoRenewalDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency,
                                   IInterestCalculator interestCalculator, int money, int period);

    IDeposit buildRenewalNoCapitalizationDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency,
                                                 IInterestCalculator interestCalculator, int money, int period);

    IDeposit buildRenewalCapitalizationDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency,
                                               IInterestCalculator interestCalculator, int money, int period);

    IDeposit buildNoRenewalDeposit(IInnerDeposit innerDeposit, IClient client, IInnerProduct product, IDeposit parent);
}
