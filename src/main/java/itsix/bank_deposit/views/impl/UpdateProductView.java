package itsix.bank_deposit.views.impl;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.views.IUpdateProductView;

public class UpdateProductView extends JFrame implements IUpdateProductView {

	private JTextField nameTextField;
	private DoubleJTextField interestRateTextField;
	private IntegerJTextField periodTextField;
	private IntegerJTextField minSumTextField;
	private IntegerJTextField maxSumTextField;
	private JComboBox<ICurrency> currencyComboBox;
	private JButton updateButton;
	private IProductsController productsController;

	public UpdateProductView(IProductsController productsController) {
		this.productsController = productsController;
		initializeGUI();
	}

	private void initializeGUI() {

		setBounds(100, 100, 224, 328);
		getContentPane().setLayout(null);

		setTitle("Update");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("product.png")));

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 14, 31, 14);
		getContentPane().add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(111, 11, 86, 20);
		getContentPane().add(nameTextField);

		JLabel interestRateLabel = new JLabel("Interest rate (%):");
		interestRateLabel.setBounds(10, 52, 91, 14);
		getContentPane().add(interestRateLabel);

		interestRateTextField = new DoubleJTextField();
		interestRateTextField.setColumns(10);
		interestRateTextField.setBounds(111, 49, 86, 20);
		getContentPane().add(interestRateTextField);

		JLabel periodLabel = new JLabel("Period");
		periodLabel.setBounds(10, 91, 46, 14);
		getContentPane().add(periodLabel);

		periodTextField = new IntegerJTextField();
		periodTextField.setColumns(10);
		periodTextField.setBounds(111, 88, 86, 20);
		getContentPane().add(periodTextField);

		JLabel currencyLabel = new JLabel("Currency:");
		currencyLabel.setBounds(10, 132, 73, 14);
		getContentPane().add(currencyLabel);

		currencyComboBox = new JComboBox<ICurrency>();
		currencyComboBox.setEnabled(false);
		currencyComboBox.setBounds(111, 129, 86, 20);
		getContentPane().add(currencyComboBox);

		JLabel minSumLabel = new JLabel("Min sum:");
		minSumLabel.setBounds(10, 175, 46, 14);
		getContentPane().add(minSumLabel);

		minSumTextField = new IntegerJTextField();
		minSumTextField.setColumns(10);
		minSumTextField.setBounds(111, 172, 86, 20);
		getContentPane().add(minSumTextField);

		JLabel maxSumLabel = new JLabel("Max sum:");
		maxSumLabel.setBounds(10, 219, 46, 14);
		getContentPane().add(maxSumLabel);

		maxSumTextField = new IntegerJTextField();
		maxSumTextField.setColumns(10);
		maxSumTextField.setBounds(111, 216, 86, 20);
		getContentPane().add(maxSumTextField);

		updateButton = new JButton("Update");
		updateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit.png")));
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productsController.updateProduct();

			}
		});
		updateButton.setBounds(54, 260, 89, 23);
		getContentPane().add(updateButton);

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
	public void show(List<ICurrency> currencies, String name, float interestRate, int period, ICurrency currency,
			int minSum, int maxSum) {
		for (ICurrency c : currencies) {
			currencyComboBox.addItem(c);
		}
		nameTextField.setText(name);
		currencyComboBox.setSelectedItem(currency);
		interestRateTextField.setText(String.valueOf(interestRate));
		periodTextField.setText(String.valueOf(period));
		minSumTextField.setText(String.valueOf(minSum));
		maxSumTextField.setText(String.valueOf(maxSum));

		setVisible(true);
	}

	private void clearTextFields() {
		interestRateTextField.setText("0.0");
		maxSumTextField.setText("0");
		minSumTextField.setText("0");
		nameTextField.setText("");
		periodTextField.setText("0");
	}
}
