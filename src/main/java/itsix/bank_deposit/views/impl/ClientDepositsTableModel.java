package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientDepositsTableModel extends AbstractTableModel implements ISubscriber {

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
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return Boolean.class;
		case 9:
			return Boolean.class;
		default:
			return String.class;
		}
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
		unsubscribe(deposits);

		this.deposits = deposits;

		subscribe(deposits);

		fireTableDataChanged();
	}

	public void clearDeposits() {
		unsubscribe(deposits);

		deposits = new ArrayList<>();
		fireTableDataChanged();
	}

	@Override
	public void update() {
		fireTableDataChanged();
	}

	private void unsubscribe(List<IDeposit> deposits) {
		for (IDeposit deposit : deposits) {
			deposit.unsubscribe(this);
		}
	}

	private void subscribe(List<IDeposit> deposits) {
		for (IDeposit deposit : deposits) {
			deposit.subscribe(this);
		}
	}

}
