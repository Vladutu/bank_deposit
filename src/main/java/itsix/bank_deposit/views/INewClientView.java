package itsix.bank_deposit.views;

public interface INewClientView {

	String getSsn();

	String getFirstName();

	String getLastName();

	String getAddress();

	void closeWindow();

	void showWindow();

}
