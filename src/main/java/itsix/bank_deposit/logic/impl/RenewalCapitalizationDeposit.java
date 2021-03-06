package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IInterestCalculator;
import itsix.bank_deposit.logic.IRenewableDeposit;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class RenewalCapitalizationDeposit implements IRenewableDeposit, Serializable {

	private IInnerDeposit innerDeposit;

	private IClient client;

	private IInnerProduct product;

	private IDepositBuilder depositBuilder;

	public RenewalCapitalizationDeposit(IInnerProduct product, IClient client, IInnerDeposit innerDeposit,
			IDepositBuilder depositBuilder) {
		this.innerDeposit = innerDeposit;
		this.product = product;
		this.client = client;
		this.depositBuilder = depositBuilder;
	}

	@Override
	public void update() {
		innerDeposit.update();

		if (innerDeposit.hasMaturated()) {
			innerDeposit.accumulateInterest();
			product.renew(this);
		}
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
	public float getDepositAmount() {
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

	@Override
	public void restart(IInterestCalculator interestCalculator) {
		innerDeposit.restart(interestCalculator);
	}

	@Override
	public IDeposit createNoRenewalClone(IDeposit parent) {
		return depositBuilder.buildNoRenewalDeposit(innerDeposit, client, product, parent);
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		innerDeposit.subscribe(subscriber);

	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		innerDeposit.unsubscribe(subscriber);
	}
}
