package itsix.bank_deposit.views.impl;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.views.ICheckDepositsView;

public class CheckDepositsView extends JFrame implements ICheckDepositsView {

	private JTable depositsTable;
	private ClientDepositsTableModel clientDepositsTableModel;

	private IClientsController clientsController;

	public CheckDepositsView(IClientsController clientsController) {
		this.clientsController = clientsController;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 828, 366);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		clientDepositsTableModel = new ClientDepositsTableModel();
		depositsTable = new JTable();
		depositsTable.setModel(clientDepositsTableModel);
		depositsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		depositsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		depositsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		depositsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		depositsTable.getColumnModel().getColumn(4).setPreferredWidth(30);
		depositsTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		depositsTable.getColumnModel().getColumn(6).setPreferredWidth(40);
		depositsTable.getColumnModel().getColumn(7).setPreferredWidth(50);
		depositsTable.getColumnModel().getColumn(8).setPreferredWidth(30);
		depositsTable.getColumnModel().getColumn(9).setPreferredWidth(40);
		scrollPane.setViewportView(depositsTable);

	}

	@Override
	public void show(List<IDeposit> deposits) {
		clientDepositsTableModel.setDeposits(deposits);
		setVisible(true);
	}

}
