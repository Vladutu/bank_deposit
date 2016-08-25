package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.ICurrency;

import java.util.List;

public interface INewAccountView {

    void show(List<ICurrency> currencies);

    ICurrency getSelectedCurrency();

    int getInitialDeposit();

    void closeWindow();
}
