package itsix.bank_deposit.controller;

import itsix.bank_deposit.views.*;

public interface IClientsController {

    void setMainView(IMainView mainView);

    void openNewClientView();

    void saveClient();

    void searchClient();

    void setNewClientView(INewClientView newClientView);

    void updateClient();

    void openBankAccountView();

    void setBankAccountView(IBankAccountView bankAccountView);

    void depositMoney();

    void withdrawMoney();

    void setNewAccountView(INewAccountView newAccountView);

    void openNewAccountView();

    void createNewAccount();

    void setNewDepositView(INewDepositView newDepositView);

    void openNewDepositView();

    void updateProductInfo();

    void changeRenewalState();

    void changeCapitalizationState();

    void setCapitalizationButtonState(ICapitalizationButtonState capitalizationButtonState);

    void onProductSelect();

    void createDeposit();

    void setCheckDepositsView(ICheckDepositsView checkDepositsView);

    void openCheckDepositsView();

    void terminateDeposit();

    void onSelectedDepositRow();
}
