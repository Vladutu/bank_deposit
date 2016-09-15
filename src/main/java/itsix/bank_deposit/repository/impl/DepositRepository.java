package itsix.bank_deposit.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.repository.IDepositRepository;

public class DepositRepository implements IDepositRepository, Serializable {

	private List<IDeposit> deposits;

	public DepositRepository() {
		deposits = new ArrayList<>();
	}

	@Override
	public void addDeposit(IDeposit deposit) {
		deposits.add(deposit);
	}

	@Override
	public List<IDeposit> getDeposits() {
		return deposits;
	}

	@Override
	public void remove(IDeposit deposit) {
		deposits.remove(deposit);
	}

}
