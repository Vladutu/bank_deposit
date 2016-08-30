package itsix.bank_deposit.controller.impl;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.exception.EntityNotFoundException;
import itsix.bank_deposit.exception.InvalidOperationException;
import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.IDepositRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IDepositValidator;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.views.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

public class ClientsController implements IClientsController, Serializable {

    private transient IMainView mainView;

    private transient INewClientView newClientView;

    private transient IBankAccountView bankAccountView;

    private transient INewAccountView newAccountView;

    private transient INewDepositView newDepositView;

    private IClientRepository clientRepository;

    private IProductRepository productsRepository;

    private IDepositRepository depositsRepository;

    private IClientBuilder clientBuilder;

    private IAccountBuilder accountBuilder;

    private IClientValidator clientValidator;

    private IDepositValidator depositValidator;

    private IClient selectedClient = null;

    private IAccount selectedAccount = null;

    private IProduct selectedProduct = null;

    private ICapitalizationButtonState capitalizationButtonState;

    public ClientsController(IDepositRepository depositsRepository, IClientRepository clientRepository,
                             IProductRepository productsRepository, IClientBuilder clientBuilder, IClientValidator clientValidator,
                             IDepositValidator depositValidator, IAccountBuilder accountBuilder) {
        this.clientRepository = clientRepository;
        this.productsRepository = productsRepository;
        this.clientBuilder = clientBuilder;
        this.clientValidator = clientValidator;
        this.accountBuilder = accountBuilder;
        this.depositValidator = depositValidator;
        this.depositsRepository = depositsRepository;
    }

    @Override
    public void setMainView(IMainView mainView) {
        this.mainView = mainView;
        mainView.disableButtons();
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

        try {
            clientRepository.findBySsn(ssn);
            JOptionPane.showMessageDialog(null, "Client already exists!");
            return;
        } catch (EntityNotFoundException e) {
            IClient client = clientBuilder.build(ssn, firstName, lastName, address);

            clientRepository.save(client);
            newClientView.closeWindow();
        }

    }

    @Override
    public void searchClient() {
        String ssn = mainView.getSearchClientSsn();

        try {
            selectedClient = clientRepository.findBySsn(ssn);

            mainView.setClientFields(selectedClient.getSsn(), selectedClient.getFirstName(),
                    selectedClient.getLastName(), selectedClient.getAddress(), selectedClient.getAccounts());
            mainView.enableButtons();

            if (!selectedClient.canCreateBankAccount()) {
                mainView.disableCreateNewAccountButton();
            }

        } catch (EntityNotFoundException e) {
            selectedClient = null;
            mainView.clearClientFields();
            mainView.disableButtons();
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
        JOptionPane.showMessageDialog(null, "Client updated!");
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
        List<ICurrency> currencies = selectedClient.getRemainingCurrenciesForAccounts();
        newAccountView.show(currencies);
    }

    @Override
    public void createNewAccount() {
        ICurrency currency = newAccountView.getSelectedCurrency();
        int initialDeposit = newAccountView.getInitialDeposit();

        IAccount account = accountBuilder.build(currency, initialDeposit);
        selectedClient.addAccount(account);

        if (!selectedClient.canCreateBankAccount()) {
            mainView.disableCreateNewAccountButton();
        }

        mainView.updateAccountsTable();
        newAccountView.closeWindow();
    }

    @Override
    public void setNewDepositView(INewDepositView newDepositView) {
        this.newDepositView = newDepositView;

    }

    @Override
    public void openNewDepositView() {
        List<IProduct> products = productsRepository.getProducts();
        newDepositView.show(products);
    }

    @Override
    public void updateProductInfo() {
        IProduct product = newDepositView.getSelectedProduct();

        if (product != null) {
            newDepositView.updateProductInfo(product);
        }

    }

    @Override
    public void changeRenewalState() {
        capitalizationButtonState = capitalizationButtonState.nextState();
        capitalizationButtonState.execute();

        selectedProduct.generatorRenewalState();
    }

    @Override
    public void changeCapitalizationState() {
        selectedProduct.generatorCapitalizationState();

    }

    @Override
    public void setCapitalizationButtonState(ICapitalizationButtonState capitalizationButtonState) {
        this.capitalizationButtonState = capitalizationButtonState;
        capitalizationButtonState.execute();
    }

    @Override
    public void onProductSelect() {
        if (selectedProduct != null) {
            selectedProduct.generatorReset();
        }

        selectedProduct = newDepositView.getSelectedProduct();
        newDepositView.resetButtons();
        capitalizationButtonState = capitalizationButtonState.reset();
    }

    @Override
    public void createDeposit() {
        int money = newDepositView.getSum();

        IValidationResult result = depositValidator.validate(selectedProduct, selectedClient, money);
        if (!result.isValid()) {
            JOptionPane.showMessageDialog(null, result.getErrorDescription());
            return;
        }

        selectedClient.withdrawMoney(selectedProduct.getCurrency(), money);
        IDeposit deposit = selectedProduct.createDeposit(selectedClient, money);
        selectedClient.addDeposit(deposit);
        depositsRepository.addDeposit(deposit);
    }

}
