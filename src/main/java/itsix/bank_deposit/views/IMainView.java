package itsix.bank_deposit.views;

import java.util.List;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public interface IMainView extends ISubscriber {

	IProduct getSelectedProduct();

	void updateProductDescription(String description);

	void clearScreen();

	String getClientSsn();

	void setClientFields(String ssn, String firstName, String lastName, String address, List<IAccount> accounts);
}