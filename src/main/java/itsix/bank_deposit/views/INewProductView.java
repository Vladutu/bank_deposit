package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.ICurrency;

public interface INewProductView {

	String getProductName();

	float getProductInterestRate();

	int getProductPeriod();

	ICurrency getProductCurrency();

	int getMinSum();

	int getMaxSum();

	void closeWindow();
}
