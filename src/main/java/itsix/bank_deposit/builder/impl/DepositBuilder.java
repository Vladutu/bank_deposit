package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.IRenewableDeposit;
import itsix.bank_deposit.logic.impl.NoRenewalDeposit;
import itsix.bank_deposit.logic.impl.RenewableDeposit;
import itsix.bank_deposit.logic.impl.RenewalCapitalizationDeposit;
import itsix.bank_deposit.logic.impl.RenewalNoCapitalizationDeposit;

/**
 * Created by Geo on 04.09.2016.
 */
public class DepositBuilder implements IDepositBuilder, Serializable {

	private IInnerDepositBuilder innerDepositBuilder;

	private IDate currentDate;

	public DepositBuilder(IInnerDepositBuilder innerDepositBuilder, IDate currentDate) {
		this.innerDepositBuilder = innerDepositBuilder;
		this.currentDate = currentDate;
	}

	@Override
	public IDeposit buildNoRenewalDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency,
			IInterestCalculator interestCalculator, int money, int period) {
		IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
				currentDate.createClone(), period);
		return new NoRenewalDeposit(product, selectedClient, innerDeposit);
	}

	@Override
	public IDeposit buildRenewalNoCapitalizationDeposit(IInnerProduct product, IClient selectedClient,
			ICurrency currency, IInterestCalculator interestCalculator, int money, int period) {
		IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
				currentDate.createClone(), period);
		IRenewableDeposit renewalNoCapitalizationDeposit = new RenewalNoCapitalizationDeposit(product, selectedClient,
				innerDeposit, this);

		return new RenewableDeposit(renewalNoCapitalizationDeposit);
	}

	@Override
	public IDeposit buildRenewalCapitalizationDeposit(IInnerProduct product, IClient selectedClient, ICurrency currency,
			IInterestCalculator interestCalculator, int money, int period) {
		IInnerDeposit innerDeposit = innerDepositBuilder.build(currency, interestCalculator, money,
				currentDate.createClone(), period);
		IRenewableDeposit renewalCapitalizationDeposit = new RenewalCapitalizationDeposit(product, selectedClient,
				innerDeposit, this);

		return new RenewableDeposit(renewalCapitalizationDeposit);
	}

	@Override
	public IDeposit buildNoRenewalDeposit(IInnerDeposit innerDeposit, IClient client, IInnerProduct product,
			IDeposit parent) {
		return new NoRenewalDeposit(innerDeposit, client, product, parent);
	}
}
