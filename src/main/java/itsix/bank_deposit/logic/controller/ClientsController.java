package itsix.bank_deposit.logic.controller;

import javax.swing.JOptionPane;

import itsix.bank_deposit.exception.EntityNotFoundException;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.IClientBuilder;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewClientView;

public class ClientsController implements IClientsController {

	private IMainView mainView;

	private INewClientView newClientView;

	private IClientRepository clientRepository;

	private IClientBuilder clientBuilder;

	private IClientValidator clientValidator;

	public ClientsController(IClientRepository clientRepository, IClientBuilder clientBuilder,
			IClientValidator clientValidator) {
		this.clientRepository = clientRepository;
		this.clientBuilder = clientBuilder;
		this.clientValidator = clientValidator;
	}

	@Override
	public void setMainView(IMainView mainView) {
		this.mainView = mainView;

	}

	@Override
	public void openNewClientView() {
		newClientView.showWindow();
	}

	@Override
	public void saveClient() {
		String ssn = newClientView.getSsn();
		String firstName = newClientView.getFirstName();
		String lastName = newClientView.getLastName();
		String address = newClientView.getAddress();

		IValidationResult result = clientValidator.validate(ssn, firstName, lastName, address);

		if (!result.isValid()) {
			JOptionPane.showMessageDialog(null, result.getErrorDescription());

			return;
		}

		IClient client = clientBuilder.build(ssn, firstName, lastName, address);

		clientRepository.save(client);
		newClientView.closeWindow();
	}

	@Override
	public void searchClient() {
		String ssn = mainView.getClientSsn();

		try {
			IClient client = clientRepository.findBySsn(ssn);

			mainView.setClientFields(client.getSsn(), client.getFirstName(), client.getLastName(), client.getAddress(),
					client.getAccounts());
		} catch (EntityNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Client not found!");
		}

	}

	@Override
	public void setNewClientView(INewClientView newClientView) {
		this.newClientView = newClientView;
	}

}
