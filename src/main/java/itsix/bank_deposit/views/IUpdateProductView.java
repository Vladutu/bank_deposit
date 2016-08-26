package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IInterest;

public interface IUpdateProductView {

	String getProductName();

	float getProductInterestRate();

	int getProductPeriod();

	ICurrency getProductCurrency();

	int getMinSum();

	int getMaxSum();

	void closeWindow();

	void show(List<ICurrency> currencies, String name, IInterest interestRate, int period, ICurrency currency,
			int minSum, int maxSum);

}
