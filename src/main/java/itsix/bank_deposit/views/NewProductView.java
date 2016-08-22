package itsix.bank_deposit.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.controller.IProductsController;

public class NewProductView extends JFrame implements INewProductView {

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nameTextField;
	private DoubleJTextField interestRateTextField;
	private IntegerJTextField periodTextField;
	private IntegerJTextField minSumTextField;
	private IntegerJTextField maxSumTextField;
	private JLabel nameLabel;
	private JLabel interestLabel;
	private JLabel periodLabel;
	private JLabel currencyLabel;
	private JLabel minSumLabel;
	private JLabel maxSumLabel;
	private JRadioButton fixedIRRadioButton;
	private JRadioButton variableIRRadioButton;
	private JButton saveButton;
	private JComboBox<ICurrency> currencyComboBox;

	private IProductsController productsController;

	public NewProductView(IProductsController controller) {
		initializeGUI();

		this.productsController = controller;
	}

	private void initializeGUI() {
		setResizable(false);
		setBounds(100, 100, 321, 429);
		getContentPane().setLayout(null);

		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(71, 27, 31, 14);
		getContentPane().add(nameLabel);

		interestLabel = new JLabel("Interest rate (%):");
		interestLabel.setBounds(71, 65, 91, 14);
		getContentPane().add(interestLabel);

		periodLabel = new JLabel("Period");
		periodLabel.setBounds(71, 104, 46, 14);
		getContentPane().add(periodLabel);

		currencyLabel = new JLabel("Currency:");
		currencyLabel.setBounds(71, 145, 73, 14);
		getContentPane().add(currencyLabel);

		minSumLabel = new JLabel("Min sum:");
		minSumLabel.setBounds(71, 188, 46, 14);
		getContentPane().add(minSumLabel);

		maxSumLabel = new JLabel("Max sum:");
		maxSumLabel.setBounds(71, 232, 46, 14);
		getContentPane().add(maxSumLabel);

		fixedIRRadioButton = new JRadioButton("Fixed Interest rate");
		fixedIRRadioButton.setSelected(true);
		fixedIRRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.changeToFixedInterestProduct();
			}
		});
		buttonGroup.add(fixedIRRadioButton);
		fixedIRRadioButton.setBounds(9, 281, 135, 23);
		getContentPane().add(fixedIRRadioButton);

		variableIRRadioButton = new JRadioButton("Variable interest rate");
		variableIRRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.changeToVariableInterestProduct();

			}
		});
		buttonGroup.add(variableIRRadioButton);
		variableIRRadioButton.setBounds(159, 281, 138, 23);
		getContentPane().add(variableIRRadioButton);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.saveProduct();

			}
		});
		saveButton.setBounds(102, 344, 89, 23);
		getContentPane().add(saveButton);

		nameTextField = new JTextField();
		nameTextField.setBounds(172, 24, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setText("0.0");
		interestRateTextField.setBounds(172, 62, 86, 20);
		getContentPane().add(interestRateTextField);
		interestRateTextField.setColumns(10);

		periodTextField = new IntegerJTextField();
		periodTextField.setText("0");
		periodTextField.setBounds(172, 101, 86, 20);
		getContentPane().add(periodTextField);
		periodTextField.setColumns(10);

		minSumTextField = new IntegerJTextField();
		minSumTextField.setText("0");
		minSumTextField.setBounds(172, 185, 86, 20);
		getContentPane().add(minSumTextField);
		minSumTextField.setColumns(10);

		maxSumTextField = new IntegerJTextField();
		maxSumTextField.setText("0");
		maxSumTextField.setBounds(172, 229, 86, 20);
		getContentPane().add(maxSumTextField);
		maxSumTextField.setColumns(10);

		currencyComboBox = new JComboBox<ICurrency>();
		currencyComboBox.setBounds(172, 142, 86, 20);
		getContentPane().add(currencyComboBox);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearTextFields();
				setVisible(false);
			}
		});
	}

	@Override
	public String getProductName() {
		return nameTextField.getText();
	}

	@Override
	public float getProductInterestRate() {
		return Float.valueOf(interestRateTextField.getText());
	}

	@Override
	public int getProductPeriod() {
		return Integer.valueOf(periodTextField.getText());
	}

	@Override
	public ICurrency getProductCurrency() {
		return (ICurrency) currencyComboBox.getSelectedItem();
	}

	@Override
	public int getMinSum() {
		return Integer.valueOf(minSumTextField.getText());
	}

	@Override
	public int getMaxSum() {
		return Integer.valueOf(maxSumTextField.getText());
	}

	@Override
	public void closeWindow() {
		setVisible(false);
		clearTextFields();
	}

	@Override
	public void show(List<ICurrency> currencies) {
		for (ICurrency currency : currencies) {
			currencyComboBox.addItem(currency);
		}

		setVisible(true);
	}

	private void clearTextFields() {
		interestRateTextField.setText("0.0");
		maxSumTextField.setText("0");
		minSumTextField.setText("0");
		nameTextField.setText("");
		periodTextField.setText("0");
		currencyComboBox.removeAllItems();
	}

}
