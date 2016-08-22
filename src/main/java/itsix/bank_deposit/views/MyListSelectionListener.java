package itsix.bank_deposit.views;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import itsix.bank_deposit.logic.controller.IProductsController;

public class MyListSelectionListener implements ListSelectionListener {

	private IProductsController productsController;

	public MyListSelectionListener(IProductsController productsController) {
		super();
		this.productsController = productsController;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		productsController.updateProductInformation();

	}

}
