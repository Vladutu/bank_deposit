package itsix.bank_deposit.repository.impl;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.repository.IDepositRepository;

import java.util.ArrayList;
import java.util.List;

public class DepositRepository implements IDepositRepository {

    private List<IDeposit> deposits = new ArrayList<>();

    public DepositRepository() {
    }

    @Override
    public void addDeposit(IDeposit deposit) {
        deposits.add(deposit);
    }

    @Override
    public List<IDeposit> getDeposits() {
        return deposits;
    }

}
