package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.views.ICapitalizationButtonState;
import itsix.bank_deposit.views.INewDepositView;

public class OffCapitalizationButtonState implements ICapitalizationButtonState {

	private INewDepositView newDepositView;

	private ICapitalizationButtonState nextState;

	public OffCapitalizationButtonState(INewDepositView newDepositView) {
		this.newDepositView = newDepositView;
	}

	@Override
	public ICapitalizationButtonState nextState() {
		return nextState;
	}

	@Override
	public void execute() {
		newDepositView.uncheckCapitalizationButton();
		newDepositView.disableCapitalizationButton();
	}

	@Override
	public void setNextState(ICapitalizationButtonState state) {
		this.nextState = state;
	}

}
