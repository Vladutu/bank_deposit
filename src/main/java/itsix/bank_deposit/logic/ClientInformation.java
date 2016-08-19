package itsix.bank_deposit.logic;

public class ClientInformation implements IClientInformation {

	private String ssn;

	private String firstName;

	private String lastName;

	private String address;

	public ClientInformation(String ssn, String firstName, String lastName, String address) {
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

}
