package itsix.bank_deposit.views.impl;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.views.INewAccountView;

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
		setBounds(100, 100, 253, 178);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(null);

		setTitle("New account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("new_account.png")));

		JLabel currencyLabel = new JLabel("Currency:");
		currencyLabel.setBounds(20, 24, 48, 22);
		getContentPane().add(currencyLabel);

		currencyComboBox = new JComboBox<>();
		currencyComboBox.setBounds(148, 24, 78, 22);
		getContentPane().add(currencyComboBox);

		JLabel initialDepositLabel = new JLabel("Initial deposit:");
		initialDepositLabel.setBounds(20, 69, 68, 22);
		getContentPane().add(initialDepositLabel);

		initialDepositTextFIeld = new IntegerJTextField();
		initialDepositTextFIeld.setText("0");
		initialDepositTextFIeld.setBounds(148, 69, 78, 20);
		getContentPane().add(initialDepositTextFIeld);
		initialDepositTextFIeld.setColumns(10);

		submitButton = new JButton("Submit");
		submitButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("accept.png")));
		submitButton.addActionListener(e -> {
			clientsController.createNewAccount();
		});
		submitButton.setBounds(80, 106, 89, 23);
		getContentPane().add(submitButton);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearFields();
				setVisible(false);
			}
		});

		setLocationRelativeTo(null);
	}

	private void clearFields() {
		currencyComboBox.removeAllItems();
		initialDepositTextFIeld.setText("0");
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

}
