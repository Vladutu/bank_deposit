package itsix.bank_deposit.controller;

import itsix.bank_deposit.views.IDayChangerView;

public interface IDateController {

	void setDateChangerView(IDayChangerView dayChangerView);

	void incrementDays(Integer value);
}
