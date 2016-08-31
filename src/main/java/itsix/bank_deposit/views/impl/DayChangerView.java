package itsix.bank_deposit.views.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import itsix.bank_deposit.controller.IDateController;
import itsix.bank_deposit.views.IDayChangerView;

public class DayChangerView extends JFrame implements IDayChangerView {

	private JButton daysIncrementButton;
	private JSpinner daySpinner;

	private IDateController dateController;

	public DayChangerView(IDateController dateController) {
		this.dateController = dateController;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 297, 124);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		daysIncrementButton = new JButton("Add 0 day");
		daysIncrementButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateController.incrementDays((Integer) daySpinner.getValue());
			}
		});
		daysIncrementButton.setBounds(127, 27, 106, 23);
		getContentPane().add(daysIncrementButton);

		daySpinner = new JSpinner();
		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 360, 1);
		daySpinner.setModel(spinnerNumberModel);
		daySpinner.setBounds(55, 28, 43, 20);
		daySpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				daysIncrementButton.setText("Add " + daySpinner.getValue() + " days");

			}
		});
		getContentPane().add(daySpinner);
		setVisible(true);
	}
}
