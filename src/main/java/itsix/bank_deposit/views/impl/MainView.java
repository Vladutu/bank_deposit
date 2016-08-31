package itsix.bank_deposit.views.impl;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.controller.ISerializerController;
import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.views.IMainView;

public class MainView extends JFrame implements IMainView, Serializable {

	private JTextField ssnSearchTextField;
	private JTextField ssnTextField;
	private JTextField addressTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;

	private JTable accountTable;
	private ClientAccountsTableModel accountTableModel;

	private JList<IProduct> productList;
	private DefaultListModel<IProduct> productListModel;

	private JTextArea productDescriptionTextArea;

	private JLabel productDescriptionLabel;
	private JLabel ssnSearchLabel;
	private JLabel ssnLabel;
	private JLabel addressLabel;
	private JLabel firstNameLabel;
	private JLabel accountLabel;
	private JLabel lastNameLabel;

	private JButton searchButton;
	private JButton newDepositButton;
	private JButton updateButton;
	private JButton newAccountButton;
	private JButton newProductButton;
	private JButton checkDepositButton;
	private JButton productDeleteButton;
	private JButton newClientButton;

	private IProductsController productsController;
	private IClientsController clientsController;
	private ISerializerController serializerController;

	private List<IProduct> products;

	private ListSelectionListener productListSelectionListener;

	public MainView(IProductsController productsController, IClientsController clientsController,
			ISerializerController serializerController, List<IProduct> products) {
		this.productsController = productsController;
		this.clientsController = clientsController;
		this.serializerController = serializerController;
		this.products = products;
		productListSelectionListener = new MyListSelectionListener(productsController);
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 725, 629);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Le Bank App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("bank_icon.png")));
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);

		JPanel productsPanel = new JPanel();
		productsPanel.setBackground(SystemColor.menu);
		tabbedPane.addTab("Products", new ImageIcon(getClass().getClassLoader().getResource("products_tab.png")),
				productsPanel, null);
		productsPanel.setLayout(null);

		JPanel productListPanel = new JPanel();
		productListPanel.setBounds(73, 124, 191, 375);
		productsPanel.add(productListPanel);
		productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane_1 = new JScrollPane();
		productListPanel.add(scrollPane_1);

		productList = new JList<>();
		productList.addListSelectionListener(productListSelectionListener);
		productList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					productsController.openUpdateProductView();
				}
			}
		});
		productListModel = new DefaultListModel<>();
		productList.setModel(productListModel);

		scrollPane_1.setViewportView(productList);

		JPanel panel = new JPanel();
		panel.setBounds(310, 124, 334, 375);
		productsPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		productDescriptionTextArea = new JTextArea();
		productDescriptionTextArea.setEditable(false);
		panel.add(productDescriptionTextArea);

		newProductButton = new JButton("New product");
		newProductButton.addActionListener(arg0 -> productsController.openNewProductView());
		newProductButton.setBounds(533, 48, 111, 23);
		productsPanel.add(newProductButton);

		JLabel productsLabel = new JLabel("Products:");
		productsLabel.setBounds(73, 96, 46, 14);
		productsPanel.add(productsLabel);

		productDescriptionLabel = new JLabel("Product Information:");
		productDescriptionLabel.setBounds(310, 96, 111, 14);
		productsPanel.add(productDescriptionLabel);

		productDeleteButton = new JButton("Delete");
		productDeleteButton.addActionListener(e -> productsController.deleteSelectedProduct());
		productDeleteButton.setBounds(190, 508, 73, 23);
		productsPanel.add(productDeleteButton);

		JPanel clientsPanel = new JPanel();
		tabbedPane.addTab("Clients", new ImageIcon(getClass().getClassLoader().getResource("clients_tab.png")),
				clientsPanel, null);
		clientsPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 694, 47);
		clientsPanel.add(panel_1);
		panel_1.setLayout(null);

		ssnSearchLabel = new JLabel("SSN :");
		ssnSearchLabel.setBounds(10, 16, 26, 14);
		panel_1.add(ssnSearchLabel);

		ssnSearchTextField = new JTextField();
		ssnSearchTextField.setBounds(40, 13, 166, 20);
		panel_1.add(ssnSearchTextField);
		ssnSearchTextField.setColumns(20);

		searchButton = new JButton("Seach");
		searchButton.addActionListener(e -> clientsController.searchClient());
		searchButton.setBounds(216, 11, 81, 25);
		panel_1.add(searchButton);
		searchButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("search_icon.png")));

		newClientButton = new JButton("New client");
		newClientButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("new_client.png")));
		newClientButton.addActionListener(e -> clientsController.openNewClientView());
		newClientButton.setBounds(552, 12, 132, 23);
		panel_1.add(newClientButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		panel_2.setBounds(10, 69, 694, 482);
		clientsPanel.add(panel_2);
		panel_2.setLayout(null);

		ssnLabel = new JLabel("     SSN :");
		ssnLabel.setBounds(10, 11, 41, 14);
		panel_2.add(ssnLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 169, 293, 173);
		panel_2.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));

		accountTable = new JTable();
		accountTable.setFillsViewportHeight(true);
		accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(accountTable);
		accountTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					clientsController.openBankAccountView();
				}

			}
		});

		accountTableModel = new ClientAccountsTableModel();
		accountTable.setModel(accountTableModel);

		ssnTextField = new JTextField();
		ssnTextField.setBounds(61, 8, 220, 20);
		panel_2.add(ssnTextField);
		ssnTextField.setEnabled(false);
		ssnTextField.setEditable(false);
		ssnTextField.setColumns(20);

		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(10, 44, 43, 14);
		panel_2.add(addressLabel);

		firstNameLabel = new JLabel("First Name :");
		firstNameLabel.setBounds(321, 11, 58, 14);
		panel_2.add(firstNameLabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(62, 39, 220, 19);
		panel_2.add(addressTextField);
		addressTextField.setColumns(20);

		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(389, 41, 220, 20);
		panel_2.add(lastNameTextField);
		lastNameTextField.setColumns(20);

		newDepositButton = new JButton("Create new deposit");
		newDepositButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("deposit.png")));
		newDepositButton.addActionListener(e -> clientsController.openNewDepositView());
		newDepositButton.setBounds(344, 353, 149, 23);
		panel_2.add(newDepositButton);

		accountLabel = new JLabel("Accounts : ");
		accountLabel.setBounds(200, 144, 54, 14);
		panel_2.add(accountLabel);

		lastNameLabel = new JLabel("Last Name :");
		lastNameLabel.setBounds(322, 44, 57, 14);
		panel_2.add(lastNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(390, 8, 220, 20);
		panel_2.add(firstNameTextField);
		firstNameTextField.setColumns(20);

		updateButton = new JButton("Update");
		updateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit.png")));
		updateButton.addActionListener(e -> clientsController.updateClient());
		updateButton.setBounds(281, 69, 97, 23);
		panel_2.add(updateButton);

		newAccountButton = new JButton("Create new account");
		newAccountButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("new_account.png")));
		newAccountButton.addActionListener(e -> {
			clientsController.openNewAccountView();

		});
		newAccountButton.setBounds(344, 135, 149, 23);
		panel_2.add(newAccountButton);
		newAccountButton.setHorizontalAlignment(SwingConstants.TRAILING);

		checkDepositButton = new JButton("Check deposits");
		checkDepositButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.openCheckDepositsView();

			}
		});
		checkDepositButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("browse.png")));
		checkDepositButton.setBounds(200, 353, 134, 23);
		panel_2.add(checkDepositButton);

		setVisible(true);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// serializerController.serialize();
				setVisible(false);
				dispose();
			}
		});

		setLocationRelativeTo(null);
	}

	@Override
	public void update() {
		productList.removeListSelectionListener(productListSelectionListener);
		productListModel.removeAllElements();

		for (IProduct product : products) {
			productListModel.addElement(product);
		}

		productList.addListSelectionListener(productListSelectionListener);
	}

	@Override
	public void updateProductDescription(String productDescription) {
		productDescriptionTextArea.setText(productDescription);
	}

	@Override
	public IProduct getSelectedProduct() {
		return productList.getSelectedValue();
	}

	@Override
	public void clearScreen() {
		productDescriptionTextArea.setText("");
		productList.clearSelection();

	}

	@Override
	public String getSearchClientSsn() {
		return ssnSearchTextField.getText();
	}

	@Override
	public void setClientFields(String ssn, String firstName, String lastName, String address,
			List<IAccount> accounts) {

		ssnTextField.setText(ssn);
		firstNameTextField.setText(firstName);
		lastNameTextField.setText(lastName);
		addressTextField.setText(address);
		accountTableModel.setAccounts(accounts);
	}

	@Override
	public void clearClientFields() {
		ssnTextField.setText("");
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		addressTextField.setText("");
		accountTableModel.clearAccounts();

	}

	@Override
	public String getClientFirstName() {
		return firstNameTextField.getText();
	}

	@Override
	public String getClientLastName() {
		return lastNameTextField.getText();
	}

	@Override
	public String getClientAddress() {
		return addressTextField.getText();
	}

	@Override
	public IAccount getSelectedBankAccount() {
		int row = accountTable.getSelectedRow();

		return accountTableModel.getAccountAtRow(row);
	}

	@Override
	public void updateAccountsTable() {
		accountTableModel.update();
	}

	@Override
	public void enableButtons() {
		updateButton.setEnabled(true);
		newAccountButton.setEnabled(true);
		checkDepositButton.setEnabled(true);
		newDepositButton.setEnabled(true);
	}

	@Override
	public void disableButtons() {
		updateButton.setEnabled(false);
		newAccountButton.setEnabled(false);
		checkDepositButton.setEnabled(false);
		newDepositButton.setEnabled(false);
	}

	@Override
	public void disableCreateNewAccountButton() {
		newAccountButton.setEnabled(false);
	}

}
