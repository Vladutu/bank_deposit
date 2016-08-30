package itsix.bank_deposit.controller.impl;

import itsix.bank_deposit.controller.IDateController;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.ITimeScheduler;
import itsix.bank_deposit.views.IDayChangerView;

public class DateController implements IDateController {

	private IDayChangerView dayChangerView;

	private IDate currentDate;

	private ITimeScheduler timeScheduler;

	public DateController(IDate currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public void setDateChangerView(IDayChangerView dayChangerView) {
		this.dayChangerView = dayChangerView;
	}

	@Override
	public void incrementDays(Integer value) {
		for (int i = 0; i < value; i++) {
			currentDate.increment();
			timeScheduler.start();
		}

	}

}
