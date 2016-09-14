package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;

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

	IDeposit buildNoRenewableDepositFromRenewableDeposit(IDeposit deposit);
}
