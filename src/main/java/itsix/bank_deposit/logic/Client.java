package itsix.bank_deposit.logic;

import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {

	private IClientInformation clientInformation;

	private List<IAccount> accounts = new ArrayList<>();

	public Client(IClientInformation clientInformation, IAccount defaultAccount) {
		this.clientInformation = clientInformation;
		accounts.add(defaultAccount);
	}

	@Override
	public boolean hasSsn(String ssn) {
		return clientInformation.hasSsn(ssn);
	}

	@Override
	public String getSsn() {
		return clientInformation.getSsn();
	}

	@Override
	public String getFirstName() {
		return clientInformation.getFirstName();
	}

	@Override
	public String getLastName() {
		return clientInformation.getLastName();
	}

	@Override
	public String getAddress() {
		return clientInformation.getAddress();
	}

	@Override
	public List<IAccount> getAccounts() {
		return accounts;
	}

}
