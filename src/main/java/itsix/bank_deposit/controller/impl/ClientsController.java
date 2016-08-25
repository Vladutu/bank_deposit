package itsix.bank_deposit.controller.impl;

import java.io.Serializable;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.exception.EntityNotFoundException;
import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.ICurrencyDivider;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.views.IBankAccountView;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewAccountView;
import itsix.bank_deposit.views.INewClientView;
import itsix.bank_deposit.views.INewDepositView;

public class ClientsController implements IClientsController, Serializable {

	private transient IMainView mainView;

	private transient INewClientView newClientView;

	private transient IBankAccountView bankAccountView;

	private transient INewAccountView newAccountView;

	private transient INewDepositView newDepositView;

	private IClientRepository clientRepository;

	private IProductRepository productsRepository;

	private IClientBuilder clientBuilder;

	private IAccountBuilder accountBuilder;

	private IClientValidator clientValidator;

	private ICurrencyDivider currencyDivider;

	private IClient selectedClient = null;

	private IAccount selectedAccount = null;

	public ClientsController(IClientRepository clientRepository, IProductRepository productsRepository,
			IClientBuilder clientBuilder, IClientValidator clientValidator, ICurrencyDivider currencyDivider,
			IAccountBuilder accountBuilder) {
		this.clientRepository = clientRepository;
		this.productsRepository = productsRepository;
		this.clientBuilder = clientBuilder;
		this.clientValidator = clientValidator;
		this.currencyDivider = currencyDivider;
		this.accountBuilder = accountBuilder;
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
		String ssn = mainView.getSearchClientSsn();

		try {
			IClient client = clientRepository.findBySsn(ssn);
			selectedClient = client;

			mainView.setClientFields(client.getSsn(), client.getFirstName(), client.getLastName(), client.getAddress(),
					client.getAccounts());
		} catch (EntityNotFoundException e) {
			selectedClient = null;
			mainView.clearClientFields();
			JOptionPane.showMessageDialog(null, "Client not found!");
		}

	}

	@Override
	public void setNewClientView(INewClientView newClientView) {
		this.newClientView = newClientView;
	}

	@Override
	public void updateClient() {
		if (selectedClient == null) {
			JOptionPane.showMessageDialog(null, "No client available!");

			return;
		}
		
		String firstName = mainView.getClientFirstName();
		String lastName = mainView.getClientLastName();
		String address = mainView.getClientAddress();

		IValidationResult result = clientValidator.validate(firstName, lastName, address);

		if (!result.isValid()) {
			JOptionPane.showMessageDialog(null, result.getErrorDescription());

			return;
		}

		selectedClient.update(firstName, lastName, address);
	}

	@Override
	public void openBankAccountView() {
		selectedAccount = mainView.getSelectedBankAccount();
		bankAccountView.show(selectedAccount);
		selectedAccount.subscribe(bankAccountView);

	}

	@Override
	public void setBankAccountView(IBankAccountView bankAccountView) {
		this.bankAccountView = bankAccountView;
	}

	@Override
	public void depositMoney() {
		int money = bankAccountView.getMoneyAmount();
		selectedAccount.deposit(money);

	}

	@Override
	public void withdrawMoney() {
		int money = bankAccountView.getMoneyAmount();

		try {
			selectedAccount.withdraw(money);
		} catch (InvalidOperationException e) {
			JOptionPane.showMessageDialog(null, "Founds not enough to withdaw");
		}

	}

	@Override
	public void setNewAccountView(INewAccountView newAccountView) {
		this.newAccountView = newAccountView;

	}

	@Override
	public void openNewAccountView() {
		if (selectedClient == null) {
			JOptionPane.showMessageDialog(null, "No client available!");

			return;
		}

		List<ICurrency> currencies = currencyDivider.getRemainingCurrencies(selectedClient);

		if (currencies.size() == 0) {
			newAccountView.showEmpty();
			return;
		}

		newAccountView.show(currencies);
	}

	@Override
	public void createNewAccount() {
		ICurrency currency = newAccountView.getSelectedCurrency();
		int initialDeposit = newAccountView.getInitialDeposit();

		IAccount account = accountBuilder.build(currency, initialDeposit);
		selectedClient.addAccount(account);

		mainView.updateAccountsTable();
		newAccountView.closeWindow();
	}

	@Override
	public void setNewDepositView(INewDepositView newDepositView) {
		this.newDepositView = newDepositView;

	}

	@Override
	public void openNewDepositView() {
		if (selectedClient == null) {
			JOptionPane.showMessageDialog(null, "No client available!");

			return;
		}

		List<IProduct> products = productsRepository.getProducts();
		newDepositView.show(products);
	}

	@Override
	public void updateProductInfo() {
		IProduct product = newDepositView.getSelectedProduct();

		newDepositView.updateProductInfo(product);

	}

}
