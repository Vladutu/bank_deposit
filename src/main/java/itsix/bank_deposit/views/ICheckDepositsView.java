package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IRenewableDeposit;

public interface ICheckDepositsView {

	void show(List<IDeposit> deposits);

	IDeposit getSelectedDeposit();

	void disableTerminationButton();

	void enableTerminationButton();

	IRenewableDeposit getSelectedRenewableDeposit();

}
