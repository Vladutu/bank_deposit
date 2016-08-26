package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.views.ICapitalizationButtonState;
import itsix.bank_deposit.views.INewDepositView;

public class OnCapitalizationButtonState implements ICapitalizationButtonState {

	private INewDepositView newDepositView;

	private ICapitalizationButtonState nextState;

	public OnCapitalizationButtonState(INewDepositView newDepositView) {
		this.newDepositView = newDepositView;
	}

	@Override
	public ICapitalizationButtonState nextState() {
		return nextState;
	}

	@Override
	public void execute() {
		newDepositView.enableCapitalizationButton();

	}

	@Override
	public void setNextState(ICapitalizationButtonState state) {
		this.nextState = state;
	}

}
