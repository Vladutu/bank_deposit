package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.ICurrency;

public interface INewProductView {

	String getProductName();

	float getProductInterestRate();

	int getProductPeriod();

	ICurrency getProductCurrency();

	int getMinSum();

	int getMaxSum();

	void closeWindow();

	void show(List<ICurrency> currencies);
}
