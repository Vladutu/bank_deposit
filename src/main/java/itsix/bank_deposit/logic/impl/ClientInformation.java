package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IClientInformation;

import java.io.Serializable;

public class ClientInformation implements IClientInformation, Serializable {

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

	@Override
	public boolean hasSsn(String ssn) {
		return this.ssn.equals(ssn);
	}

	@Override
	public String getSsn() {
		return ssn;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void update(String firstName, String lastName, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

}
