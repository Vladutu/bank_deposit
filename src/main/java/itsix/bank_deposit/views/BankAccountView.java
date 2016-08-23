package itsix.bank_deposit.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.controller.IClientsController;

public class BankAccountView extends JFrame implements IBankAccountView, Serializable {

	private JTextField creditBalanceTextField;
	private JTextField debitBalanceTextField;
	private JTextField balanceTextField;
	private IntegerJTextField moneyTextField;
	private JButton depositButton;
	private JButton withdrawButton;

	private IAccount account;
	private JTextField currencyTextField;

	private IClientsController clientsController;

	public BankAccountView(IClientsController clientsController) {
		this.clientsController = clientsController;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 327, 278);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel creditBalanceLabel = new JLabel("Credit balance:");
		creditBalanceLabel.setBounds(16, 61, 80, 14);
		getContentPane().add(creditBalanceLabel);

		JLabel debitBalanceLabel = new JLabel("Debit balance:");
		debitBalanceLabel.setBounds(16, 98, 80, 14);
		getContentPane().add(debitBalanceLabel);

		JLabel balanceLabel = new JLabel("Balance:");
		balanceLabel.setBounds(16, 136, 46, 14);
		getContentPane().add(balanceLabel);

		JLabel currencyLabel = new JLabel("Currency:");
		currencyLabel.setBounds(16, 25, 67, 14);
		getContentPane().add(currencyLabel);

		creditBalanceTextField = new JTextField();
		creditBalanceTextField.setEditable(false);
		creditBalanceTextField.setBounds(121, 58, 86, 20);
		getContentPane().add(creditBalanceTextField);
		creditBalanceTextField.setColumns(10);

		debitBalanceTextField = new JTextField();
		debitBalanceTextField.setEnabled(false);
		debitBalanceTextField.setBounds(121, 95, 86, 20);
		getContentPane().add(debitBalanceTextField);
		debitBalanceTextField.setColumns(10);

		balanceTextField = new JTextField();
		balanceTextField.setEnabled(false);
		balanceTextField.setBounds(121, 133, 86, 20);
		getContentPane().add(balanceTextField);
		balanceTextField.setColumns(10);

		moneyTextField = new IntegerJTextField();
		moneyTextField.setText("0");
		moneyTextField.setBounds(10, 209, 86, 20);
		getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);

		depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.depositMoney();
			}
		});
		depositButton.setBounds(118, 208, 80, 23);
		getContentPane().add(depositButton);

		withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.withdrawMoney();

			}
		});
		withdrawButton.setBounds(208, 208, 89, 23);
		getContentPane().add(withdrawButton);

		currencyTextField = new JTextField();
		currencyTextField.setEditable(false);
		currencyTextField.setBounds(121, 22, 86, 20);
		getContentPane().add(currencyTextField);
		currencyTextField.setColumns(10);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearTextFields();
				account.unsubscribe(BankAccountView.this);
				setVisible(false);
			}
		});
	}

	@Override
	public void update() {
		balanceTextField.setText(String.valueOf(account.getBalance()));
		creditBalanceTextField.setText(String.valueOf(account.getCreditBalance()));
		debitBalanceTextField.setText(String.valueOf(account.getDebitBalance()));
		currencyTextField.setText(account.getCurrency().toString());
	}

	private void clearTextFields() {
		moneyTextField.setText("");
	}

	@Override
	public void show(IAccount selectedAccount) {
		account = selectedAccount;
		setVisible(true);
	}

	@Override
	public int getMoneyAmount() {
		return Integer.valueOf(moneyTextField.getText());
	}
}
