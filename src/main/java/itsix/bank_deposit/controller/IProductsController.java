package itsix.bank_deposit.controller;

import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewProductView;
import itsix.bank_deposit.views.IUpdateProductView;

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

	void setNewProductView(INewProductView newProductView);

	void setUpdateProductView(IUpdateProductView updateProductView);

}
