package itsix.bank_deposit.controller;

import itsix.bank_deposit.views.IBankAccountView;
import itsix.bank_deposit.views.ICapitalizationButtonState;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewAccountView;
import itsix.bank_deposit.views.INewClientView;
import itsix.bank_deposit.views.INewDepositView;

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
}
