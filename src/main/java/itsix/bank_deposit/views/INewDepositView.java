package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.IProduct;

public interface INewDepositView {

	void show(List<IProduct> products);

	IProduct getSelectedProduct();

	void updateProductInfo(IProduct product);

	void disableCapitalizationButton();

	void enableCapitalizationButton();

	void uncheckCapitalizationButton();

	void resetButtons();

}
