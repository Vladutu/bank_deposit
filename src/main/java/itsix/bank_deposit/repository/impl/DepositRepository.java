package itsix.bank_deposit.repository.impl;

import java.util.ArrayList;
import java.util.List;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.repository.IDepositRepository;

public class DepositRepository implements IDepositRepository {

	private List<IDeposit> deposits = new ArrayList<>();

	public DepositRepository() {
	}

	@Override
	public void addDeposit(IDeposit deposit) {
		deposits.add(deposit);
	}

}
