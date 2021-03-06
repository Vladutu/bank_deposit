package itsix.bank_deposit.views.impl;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.views.INewDepositView;

public class NewDepositView extends JFrame implements INewDepositView {

	private IntegerJTextField sumTextField;
	private JComboBox<IProduct> productComboBox;
	private JTextArea productInfoTextArea;
	private JCheckBox renewalCheckButton;
	private JCheckBox capitalizationCheckButton;
	private JButton createButton;

	private IClientsController clientsController;

	private ActionListener comboBoxActionListener;

	public NewDepositView(IClientsController clientsController) {
		this.clientsController = clientsController;
		initialize();
	}

	private void initialize() {
		comboBoxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.updateProductInfo();
			}

		};

		setBounds(100, 100, 505, 284);
		getContentPane().setLayout(null);

		setTitle("New deposit");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("deposit.png")));

		JLabel depositTypeLabel = new JLabel("Deposit Type:");
		depositTypeLabel.setBounds(28, 37, 77, 23);
		getContentPane().add(depositTypeLabel);

		productComboBox = new JComboBox<>();
		productComboBox.addActionListener(comboBoxActionListener);
		productComboBox.setBounds(124, 38, 91, 23);
		productComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.onProductSelect();
			}
		});
		getContentPane().add(productComboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 36, 216, 145);
		getContentPane().add(scrollPane);

		productInfoTextArea = new JTextArea();
		scrollPane.setViewportView(productInfoTextArea);

		JLabel sumLabel = new JLabel("Sum:");
		sumLabel.setBounds(28, 84, 46, 14);
		getContentPane().add(sumLabel);

		sumTextField = new IntegerJTextField();
		sumTextField.setBounds(124, 81, 91, 20);
		getContentPane().add(sumTextField);
		sumTextField.setColumns(10);

		renewalCheckButton = new JCheckBox("Renewal");
		renewalCheckButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clientsController.changeRenewalState();
			}
		});
		renewalCheckButton.setBounds(28, 117, 97, 23);
		getContentPane().add(renewalCheckButton);

		capitalizationCheckButton = new JCheckBox("Capitalization");
		capitalizationCheckButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.changeCapitalizationState();
			}
		});
		capitalizationCheckButton.setBounds(28, 158, 97, 23);
		getContentPane().add(capitalizationCheckButton);

		createButton = new JButton("Create");
		createButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("accept.png")));
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.createDeposit();

			}
		});
		createButton.setBounds(177, 205, 89, 23);
		getContentPane().add(createButton);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});

		setLocationRelativeTo(null);
	}

	@Override
	public void show(List<IProduct> products) {
		productComboBox.removeActionListener(comboBoxActionListener);
		productComboBox.removeAllItems();

		for (IProduct product : products) {
			productComboBox.addItem(product);
		}
		productComboBox.addActionListener(comboBoxActionListener);
		clientsController.updateProductInfo();

		setVisible(true);
	}

	@Override
	public IProduct getSelectedProduct() {
		return (IProduct) productComboBox.getSelectedItem();
	}

	@Override
	public void updateProductInfo(IProduct product) {
		productInfoTextArea.setText(product.description());
	}

	@Override
	public void disableCapitalizationButton() {
		capitalizationCheckButton.setEnabled(false);
	}

	@Override
	public void enableCapitalizationButton() {
		capitalizationCheckButton.setEnabled(true);
	}

	@Override
	public void uncheckCapitalizationButton() {
		capitalizationCheckButton.setSelected(false);

	}

	@Override
	public void resetButtons() {
		capitalizationCheckButton.setSelected(false);
		renewalCheckButton.setSelected(false);
		capitalizationCheckButton.setEnabled(false);
	}

	@Override
	public int getSum() {
		return Integer.valueOf(sumTextField.getText());
	}

	@Override
	public void closeWindow() {
		setVisible(false);
		resetButtons();
		sumTextField.setText("");
	}
}
