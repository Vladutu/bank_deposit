package itsix.bank_deposit.views.impl;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.views.INewProductView;

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
		setBounds(100, 100, 249, 381);
		getContentPane().setLayout(null);

		setTitle("New product");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("new_product.png")));

		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(22, 25, 31, 14);
		getContentPane().add(nameLabel);

		interestLabel = new JLabel("Interest rate (%):");
		interestLabel.setBounds(22, 63, 91, 14);
		getContentPane().add(interestLabel);

		periodLabel = new JLabel("Period");
		periodLabel.setBounds(22, 102, 46, 14);
		getContentPane().add(periodLabel);

		currencyLabel = new JLabel("Currency:");
		currencyLabel.setBounds(22, 143, 73, 14);
		getContentPane().add(currencyLabel);

		minSumLabel = new JLabel("Min sum:");
		minSumLabel.setBounds(22, 186, 46, 14);
		getContentPane().add(minSumLabel);

		maxSumLabel = new JLabel("Max sum:");
		maxSumLabel.setBounds(22, 230, 46, 14);
		getContentPane().add(maxSumLabel);

		fixedIRRadioButton = new JRadioButton("Fixed Interest");
		fixedIRRadioButton.setSelected(true);
		fixedIRRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.changeToFixedInterestProduct();
			}
		});
		buttonGroup.add(fixedIRRadioButton);
		fixedIRRadioButton.setBounds(15, 276, 98, 23);
		getContentPane().add(fixedIRRadioButton);

		variableIRRadioButton = new JRadioButton("Variable interest");
		variableIRRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.changeToVariableInterestProduct();

			}
		});
		buttonGroup.add(variableIRRadioButton);
		variableIRRadioButton.setBounds(122, 276, 103, 23);
		getContentPane().add(variableIRRadioButton);

		saveButton = new JButton("Save");
		saveButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("accept.png")));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.saveProduct();

			}
		});
		saveButton.setBounds(75, 315, 89, 23);
		getContentPane().add(saveButton);

		nameTextField = new JTextField();
		nameTextField.setBounds(134, 25, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setText("0.0");
		interestRateTextField.setBounds(134, 63, 86, 20);
		getContentPane().add(interestRateTextField);
		interestRateTextField.setColumns(10);

		periodTextField = new IntegerJTextField();
		periodTextField.setText("0");
		periodTextField.setBounds(134, 102, 86, 20);
		getContentPane().add(periodTextField);
		periodTextField.setColumns(10);

		minSumTextField = new IntegerJTextField();
		minSumTextField.setText("0");
		minSumTextField.setBounds(134, 186, 86, 20);
		getContentPane().add(minSumTextField);
		minSumTextField.setColumns(10);

		maxSumTextField = new IntegerJTextField();
		maxSumTextField.setText("0");
		maxSumTextField.setBounds(134, 230, 86, 20);
		getContentPane().add(maxSumTextField);
		maxSumTextField.setColumns(10);

		currencyComboBox = new JComboBox<ICurrency>();
		currencyComboBox.setBounds(134, 143, 86, 20);
		getContentPane().add(currencyComboBox);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearTextFields();
				setVisible(false);
			}
		});

		setLocationRelativeTo(null);
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
