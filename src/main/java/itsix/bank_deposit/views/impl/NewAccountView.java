package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.views.INewAccountView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class NewAccountView extends JFrame implements INewAccountView {
    private IntegerJTextField initialDepositTextFIeld;

    private JComboBox<ICurrency> currencyComboBox;

    private JButton submitButton;

    private IClientsController clientsController;

    public NewAccountView(IClientsController clientsController) {
        this.clientsController = clientsController;
        initialize();
    }

    private void initialize() {
        setResizable(false);
        setBounds(100, 100, 351, 207);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel currencyLabel = new JLabel("Currency:");
        currencyLabel.setBounds(89, 23, 48, 22);
        getContentPane().add(currencyLabel);

        currencyComboBox = new JComboBox<>();
        currencyComboBox.setBounds(169, 24, 78, 22);
        getContentPane().add(currencyComboBox);

        JLabel initialDepositLabel = new JLabel("Initial deposit:");
        initialDepositLabel.setBounds(69, 68, 68, 22);
        getContentPane().add(initialDepositLabel);

        initialDepositTextFIeld = new IntegerJTextField();
        initialDepositTextFIeld.setText("0");
        initialDepositTextFIeld.setBounds(169, 69, 78, 20);
        getContentPane().add(initialDepositTextFIeld);
        initialDepositTextFIeld.setColumns(10);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            clientsController.createNewAccount();
        });
        submitButton.setBounds(116, 111, 89, 23);
        getContentPane().add(submitButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clearFields();
                setVisible(false);
            }
        });
    }

    private void clearFields() {
        currencyComboBox.removeAllItems();
        initialDepositTextFIeld.setText("0");

        currencyComboBox.setEditable(true);
        currencyComboBox.setEnabled(true);
        submitButton.setEnabled(true);
    }

    @Override
    public void show(List<ICurrency> currencies) {
        for (ICurrency currency : currencies) {
            currencyComboBox.addItem(currency);
        }

        setVisible(true);
    }

    @Override
    public ICurrency getSelectedCurrency() {
        return (ICurrency) currencyComboBox.getSelectedItem();
    }

    @Override
    public int getInitialDeposit() {
        return Integer.valueOf(initialDepositTextFIeld.getText());
    }

    @Override
    public void closeWindow() {
        clearFields();
        setVisible(false);
    }

    @Override
    public void showEmpty() {
        currencyComboBox.setEditable(false);
        currencyComboBox.setEnabled(false);
        submitButton.setEnabled(false);

        setVisible(true);
    }
}
