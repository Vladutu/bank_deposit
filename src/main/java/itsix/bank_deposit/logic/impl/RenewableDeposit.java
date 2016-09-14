package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IInnerDeposit;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.IRenewableDeposit;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

/**
 * Created by Geo on 04.09.2016.
 */
public class RenewableDeposit implements IDeposit, IRenewableDeposit {

	private IDeposit deposit;

	private IDepositBuilder depositBuilder;

	public RenewableDeposit(IDeposit deposit, IDepositBuilder depositBuilder) {
		this.deposit = deposit;
		this.depositBuilder = depositBuilder;
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		deposit.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		deposit.unsubscribe(subscriber);
	}

	@Override
	public void update() {
		deposit.update();
	}

	@Override
	public IDate getCreationDate() {
		return deposit.getCreationDate();
	}

	@Override
	public int getPeriod() {
		return deposit.getPeriod();
	}

	@Override
	public int getDaysLeft() {
		return deposit.getDaysLeft();
	}

	@Override
	public float getInterest() {
		return deposit.getInterest();
	}

	@Override
	public float getDepositAmount() {
		return deposit.getDepositAmount();
	}

	@Override
	public float getMoneyGained() {
		return deposit.getMoneyGained();
	}

	@Override
	public boolean getRenewal() {
		return deposit.getRenewal();
	}

	@Override
	public boolean getCapitalization() {
		return deposit.getCapitalization();
	}

	@Override
	public ICurrency getCurrency() {
		return deposit.getCurrency();
	}

	@Override
	public void markForTermination() {
		deposit = depositBuilder.buildNoRenewableDepositFromRenewableDeposit(deposit);

	}

	@Override
	public void copyFields(IInnerDeposit innerDeposit, IClient client, IInnerProduct product, IDeposit deposit) {
		deposit.copyFields(innerDeposit, client, product, deposit);

	}
}
