package itsix.bank_deposit.logic.controller;

import itsix.bank_deposit.views.IMainView;

public interface IProductsController {

	void openNewProductView();

	void changeToFixedInterestProduct();

	void changeToVariableInterestProduct();

	void saveProduct();

	void setMainView(IMainView mainView);

	void updateProductInformation();

	void deleteSelectedProduct();

	void openUpdateProductView();

	void updateProduct();

}
