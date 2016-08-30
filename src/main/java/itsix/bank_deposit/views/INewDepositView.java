package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.IProduct;

import java.util.List;

public interface INewDepositView {

    void show(List<IProduct> products);

    IProduct getSelectedProduct();

    void updateProductInfo(IProduct product);

    void disableCapitalizationButton();

    void enableCapitalizationButton();

    void uncheckCapitalizationButton();

    void resetButtons();

    int getSum();

    void closeWindow();
}
