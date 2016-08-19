package itsix.bank_deposit.logic.controller;

import itsix.bank_deposit.views.IMainView;

public interface IClientsController {

	void setMainView(IMainView mainView);

	void openNewClientView();

	void saveClient();

	void searchClient();

}
