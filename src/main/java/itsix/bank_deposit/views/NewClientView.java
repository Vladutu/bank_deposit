package itsix.bank_deposit.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import itsix.bank_deposit.logic.controller.IClientsController;

public class NewClientView extends JFrame implements INewClientView {
	private JTextField ssnTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField addressTextField;
	private JButton saveButton;

	private IClientsController clientsController;

	public NewClientView(IClientsController clientsController) {
		initializeGUI();
		initializeData(clientsController);
	}

	private void initializeData(IClientsController clientsController) {
		this.clientsController = clientsController;
	}

	private void initializeGUI() {
		setBounds(100, 100, 249, 274);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel ssnLabel = new JLabel("SSN:");
		ssnLabel.setBounds(34, 39, 46, 14);
		getContentPane().add(ssnLabel);

		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(34, 74, 79, 14);
		getContentPane().add(firstNameLabel);

		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(34, 110, 64, 14);
		getContentPane().add(lastNameLabel);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBounds(34, 149, 64, 14);
		getContentPane().add(addressLabel);

		ssnTextField = new JTextField();
		ssnTextField.setBounds(122, 36, 86, 20);
		getContentPane().add(ssnTextField);
		ssnTextField.setColumns(10);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(122, 71, 86, 20);
		getContentPane().add(firstNameTextField);
		firstNameTextField.setColumns(10);

		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(122, 107, 86, 20);
		getContentPane().add(lastNameTextField);
		lastNameTextField.setColumns(10);

		addressTextField = new JTextField();
		addressTextField.setBounds(122, 146, 86, 20);
		getContentPane().add(addressTextField);
		addressTextField.setColumns(10);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.saveClient();

			}
		});
		saveButton.setBounds(70, 199, 89, 23);
		getContentPane().add(saveButton);

		setVisible(true);
	}

	@Override
	public String getSsn() {
		return ssnTextField.getText();
	}

	@Override
	public String getFirstName() {
		return firstNameTextField.getText();
	}

	@Override
	public String getLastName() {
		return lastNameTextField.getText();
	}

	@Override
	public String getAddress() {
		return addressTextField.getText();
	}
}
