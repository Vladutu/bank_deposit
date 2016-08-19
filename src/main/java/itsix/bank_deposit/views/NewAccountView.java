package itsix.bank_deposit.views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewAccountView extends JFrame {
	private JTextField textField;

	public NewAccountView() {
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 351, 207);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCurrency = new JLabel("Currency:");
		lblCurrency.setBounds(89, 23, 48, 22);
		getContentPane().add(lblCurrency);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Euro", "Dollar", "Yen" }));
		comboBox.setBounds(169, 24, 78, 22);
		getContentPane().add(comboBox);

		JLabel lblInitialDeposit = new JLabel("Initial deposit:");
		lblInitialDeposit.setBounds(69, 68, 68, 22);
		getContentPane().add(lblInitialDeposit);

		textField = new JTextField();
		textField.setBounds(161, 69, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(116, 111, 89, 23);
		getContentPane().add(btnSubmit);

	}
}
