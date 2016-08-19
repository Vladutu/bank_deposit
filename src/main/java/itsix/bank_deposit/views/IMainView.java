package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

public interface IMainView extends ISubscriber {

	IProduct getSelectedProduct();

	void updateProductDescription(String description);

	void clearScreen();
}
