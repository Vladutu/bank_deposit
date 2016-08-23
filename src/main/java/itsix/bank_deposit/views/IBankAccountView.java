package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public interface IBankAccountView extends ISubscriber {

	void show(IAccount selectedAccount);

	int getMoneyAmount();

}
