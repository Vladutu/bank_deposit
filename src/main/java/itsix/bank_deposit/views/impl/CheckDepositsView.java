package itsix.bank_deposit.views.impl;

import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.logic.ICloseableDeposit;
import itsix.bank_deposit.logic.IDeposit;
import itsix.bank_deposit.views.ICheckDepositsView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CheckDepositsView extends JFrame implements ICheckDepositsView {

    private JTable depositsTable;
    private ClientDepositsTableModel clientDepositsTableModel;
    private JButton terminationButton;
    private ListSelectionListener listSelectionListener;

    private IClientsController clientsController;

    public CheckDepositsView(IClientsController clientsController) {
        this.clientsController = clientsController;
        initialize();
    }

    private void initialize() {

        listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (depositsTable.getSelectedRow() >= 0) {
                    clientsController.onSelectedDepositRow();
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
        depositsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        depositsTable.getSelectionModel().addListSelectionListener(listSelectionListener);
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

        terminationButton = new JButton("Mark selected for termination");
        terminationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientsController.terminateDeposit();
            }
        });
        terminationButton.setBounds(305, 334, 181, 23);
        getContentPane().add(terminationButton);

        setLocationRelativeTo(null);
    }

    @Override
    public void show(List<IDeposit> deposits) {
        depositsTable.getSelectionModel().removeListSelectionListener(listSelectionListener);
        clientDepositsTableModel.setDeposits(deposits);
        depositsTable.getSelectionModel().addListSelectionListener(listSelectionListener);
        setVisible(true);
    }

    @Override
    public IDeposit getSelectedDeposit() {
        return clientDepositsTableModel.getDepositAt(depositsTable.getSelectedRow());
    }

    @Override
    public void changeTerminationButtonState(boolean renewal) {
        terminationButton.setEnabled(renewal);
    }

    @Override
    public ICloseableDeposit getSelectedCloseableDeposit() {
        return (ICloseableDeposit) getSelectedDeposit();
    }
}
