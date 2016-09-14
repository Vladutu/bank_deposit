package itsix.bank_deposit.views.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.logic.IRenewableDeposit;
import itsix.bank_deposit.views.ICheckDepositsView;

public class CheckDepositsView extends JFrame implements ICheckDepositsView {

	private JTable depositsTable;
	private ClientDepositsTableModel clientDepositsTableModel;

	private IClientsController clientsController;

	private ListSelectionListener listSelectionListener;
	private JButton depositTerminationButton;

	public CheckDepositsView(IClientsController clientsController) {
		this.clientsController = clientsController;
		initialize();
	}

	private void initialize() {

		listSelectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (depositsTable.getSelectedRow() >= 0) {
					clientsController.onDepositSelect();
				}

			}
		};

		setBounds(100, 100, 828, 407);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 812, 327);
		getContentPane().add(scrollPane);

		clientDepositsTableModel = new ClientDepositsTableModel();
		depositsTable = new JTable();
		depositsTable.setModel(clientDepositsTableModel);
		depositsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

		depositsTable.getSelectionModel().addListSelectionListener(listSelectionListener);

		depositTerminationButton = new JButton("Mark selected for termination");
		depositTerminationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientsController.terminateSelectedDepositWhenMaturated();

			}
		});
		depositTerminationButton.setBounds(305, 334, 181, 23);
		getContentPane().add(depositTerminationButton);

		setLocationRelativeTo(null);
	}

	@Override
	public void show(List<IDeposit> deposits) {
		depositsTable.getSelectionModel().removeListSelectionListener(listSelectionListener);
		clientDepositsTableModel.setDeposits(deposits);
		setVisible(true);
		depositsTable.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	@Override
	public IDeposit getSelectedDeposit() {
		return clientDepositsTableModel.getDeposit(depositsTable.getSelectedRow());
	}

	@Override
	public void disableTerminationButton() {
		depositTerminationButton.setEnabled(false);
	}

	@Override
	public void enableTerminationButton() {
		depositTerminationButton.setEnabled(true);

	}

	@Override
	public IRenewableDeposit getSelectedRenewableDeposit() {
		return (IRenewableDeposit) getSelectedDeposit();
	}
}
