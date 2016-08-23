package itsix.bank_deposit.views;

import itsix.bank_deposit.controller.IProductsController;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.Serializable;

public class MyListSelectionListener implements ListSelectionListener, Serializable {

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
