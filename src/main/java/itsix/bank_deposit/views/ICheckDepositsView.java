package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.ICloseableDeposit;
import itsix.bank_deposit.logic.IDeposit;

import java.util.List;

public interface ICheckDepositsView {

    void show(List<IDeposit> deposits);

    IDeposit getSelectedDeposit();

    void changeTerminationButtonState(boolean renewal);

    ICloseableDeposit getSelectedCloseableDeposit();
}
