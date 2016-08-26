package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.ICapitalizationButtonStateBuilder;
import itsix.bank_deposit.views.ICapitalizationButtonState;
import itsix.bank_deposit.views.INewDepositView;
import itsix.bank_deposit.views.impl.OffCapitalizationButtonState;
import itsix.bank_deposit.views.impl.OnCapitalizationButtonState;

public class CapitalizationStateBuilder implements ICapitalizationButtonStateBuilder {

	private ICapitalizationButtonState onState;

	private ICapitalizationButtonState offState;

	private INewDepositView newDepositView;

	public CapitalizationStateBuilder(INewDepositView newDepositView) {
		this.newDepositView = newDepositView;

		onState = new OnCapitalizationButtonState(newDepositView);
		offState = new OffCapitalizationButtonState(newDepositView);

		onState.setNextState(offState);
		offState.setNextState(onState);
	}

	@Override
	public ICapitalizationButtonState build() {
		return offState;
	}

}
