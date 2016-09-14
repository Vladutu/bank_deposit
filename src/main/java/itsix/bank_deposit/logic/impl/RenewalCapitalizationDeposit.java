package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public class RenewalCapitalizationDeposit implements IDeposit {

	private IInnerDeposit innerDeposit;

	private IClient client;

	private IInnerProduct product;

	public RenewalCapitalizationDeposit(IInnerProduct product, IClient client, IInnerDeposit innerDeposit) {
		this.innerDeposit = innerDeposit;
		this.product = product;
		this.client = client;
	}

	@Override
	public void update() {
		innerDeposit.update();

		if (innerDeposit.hasMaturated()) {
			innerDeposit.accumulateInterest();
			product.renew(innerDeposit);
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
	public void subscribe(ISubscriber subscriber) {
		innerDeposit.subscribe(subscriber);

	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		innerDeposit.unsubscribe(subscriber);

	}

	@Override
	public void copyFields(IInnerDeposit innerDeposit, IClient client, IInnerProduct product, IDeposit deposit) {
		innerDeposit = this.innerDeposit;
		client = this.client;
		product = this.product;

	}
}
