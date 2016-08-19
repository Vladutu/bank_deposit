package itsix.bank_deposit.views;

import javax.swing.table.DefaultTableModel;

public class ClientAccountsTableModel extends DefaultTableModel {

	public ClientAccountsTableModel(Object[][] objects, String[] strings) {
		super(objects, strings);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
