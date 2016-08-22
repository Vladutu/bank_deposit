package itsix.bank_deposit.logic.controller;

import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewClientView;

public interface IClientsController {

	void setMainView(IMainView mainView);

	void openNewClientView();

	void saveClient();

	void searchClient();

	void setNewClientView(INewClientView newClientView);

}
