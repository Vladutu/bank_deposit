package itsix.bank_deposit.views.impl;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientAccountView extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	public ClientAccountView() {
		setResizable(false);
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 483, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(20, 11, 157, 127);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCurrency = new JLabel("Currency:");
		lblCurrency.setBounds(32, 7, 51, 28);
		panel.add(lblCurrency);

		JLabel label = new JLabel("$");
		label.setBounds(101, 11, 46, 21);
		panel.add(label);

		JLabel lblCredit = new JLabel("Credit balance:");
		lblCredit.setBounds(10, 31, 73, 21);
		panel.add(lblCredit);

		JLabel lblDebitBalance = new JLabel("Debit balance:");
		lblDebitBalance.setBounds(10, 55, 73, 24);
		panel.add(lblDebitBalance);

		JLabel label_1 = new JLabel("123124");
		label_1.setBounds(93, 60, 46, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("2312312");
		label_2.setBounds(93, 34, 46, 14);
		panel.add(label_2);

		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(37, 84, 46, 14);
		panel.add(lblBalance);

		JLabel label_3 = new JLabel("21341");
		label_3.setBounds(93, 84, 46, 14);
		panel.add(label_3);

		JLabel lblDeposit = new JLabel("Deposit:");
		lblDeposit.setBounds(217, 24, 40, 23);
		getContentPane().add(lblDeposit);

		JLabel lblWithdraw = new JLabel("Withdraw:");
		lblWithdraw.setBounds(207, 69, 50, 23);
		getContentPane().add(lblWithdraw);

		textField = new JTextField();
		textField.setBounds(267, 25, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(267, 70, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(362, 24, 89, 23);
		getContentPane().add(btnSubmit);

		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setBounds(362, 69, 89, 23);
		getContentPane().add(btnSubmit_1);
	}

}
