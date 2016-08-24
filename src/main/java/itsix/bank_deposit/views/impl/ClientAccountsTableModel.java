package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.logic.IAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientAccountsTableModel extends AbstractTableModel {

    private String[] columns = {"Name", "Symbol"};

    private List<IAccount> accounts = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return accounts.get(rowIndex).getCurrencyName();
            case 1:
                return accounts.get(rowIndex).getCurrencySymbol();
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

    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
        fireTableDataChanged();
    }

    public void clearAccounts() {
        accounts = new ArrayList<>();
        fireTableDataChanged();
    }

    public IAccount getAccountAtRow(int row) {
        return accounts.get(row);
    }

    public void update() {
        fireTableDataChanged();
    }
}
