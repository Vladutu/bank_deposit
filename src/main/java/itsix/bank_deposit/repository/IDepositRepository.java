package itsix.bank_deposit.repository;

import itsix.bank_deposit.logic.IDeposit;

import java.util.List;

public interface IDepositRepository {

    void addDeposit(IDeposit deposit);

    List<IDeposit> getDeposits();
}
