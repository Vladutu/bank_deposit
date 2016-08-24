package itsix.bank_deposit.views;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;

import java.util.List;

public interface IMainView extends ISubscriber {

    IProduct getSelectedProduct();

    void updateProductDescription(String description);

    void clearScreen();

    String getSearchClientSsn();

    void setClientFields(String ssn, String firstName, String lastName, String address, List<IAccount> accounts);

    void clearClientFields();

    String getClientFirstName();

    String getClientLastName();

    String getClientAddress();

    IAccount getSelectedBankAccount();

    void updateAccountsTable();
}
