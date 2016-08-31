package itsix.bank_deposit.logic;

import itsix.bank_deposit.publisher_subscriber.IPublisher;

public interface IInnerDeposit extends IPublisher {

	void update();

	IDate getCreationDate();

	int getPeriod();

	int getDaysLeft();

	float getInterest();

	int getDepositAmount();

	float getMoneyGained();

	ICurrency getCurrency();
}
