package itsix.bank_deposit.views.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import itsix.bank_deposit.logic.IDeposit;

public class ClientDepositsTableModel extends AbstractTableModel {

	private String[] columns = { "No.", "Currency", "Creation Date", "Period(days)", "Days left", "Interest (%)",
			"Deposit amount", "Money gained", "Renewal", "Capitalization" };

	private List<IDeposit> deposits = new ArrayList<>();

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return deposits.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return deposits.get(rowIndex).getCurrency();
		case 2:
			return deposits.get(rowIndex).getCreationDate();
		case 3:
			return deposits.get(rowIndex).getPeriod();
		case 4:
			return deposits.get(rowIndex).getDaysLeft();
		case 5:
			return deposits.get(rowIndex).getInterest();
		case 6:
			return deposits.get(rowIndex).getDepositAmount();
		case 7:
			return deposits.get(rowIndex).getMoneyGained();
		case 8:
			return deposits.get(rowIndex).getRenewal();
		case 9:
			return deposits.get(rowIndex).getCapitalization();
		}

		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public void setDeposits(List<IDeposit> deposits) {
		this.deposits = deposits;
		fireTableDataChanged();
	}

	public void clearDeposits() {
		deposits = new ArrayList<>();
		fireTableDataChanged();
	}

	public void update() {
		fireTableDataChanged();
	}

}
