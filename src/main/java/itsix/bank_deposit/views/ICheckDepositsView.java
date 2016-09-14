package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.IDeposit;

public interface ICheckDepositsView {

	void show(List<IDeposit> deposits);

}
