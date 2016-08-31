package itsix.bank_deposit.logic;

import itsix.bank_deposit.publisher_subscriber.IPublisher;

public interface IDeposit extends IPublisher {

	void update();

	IDate getCreationDate();

	int getPeriod();

	int getDaysLeft();

	float getInterest();

	float getDepositAmount();

	float getMoneyGained();

	boolean getRenewal();

	boolean getCapitalization();

	ICurrency getCurrency();

	void restart(IInterestCalculator interestCalculator);
}
