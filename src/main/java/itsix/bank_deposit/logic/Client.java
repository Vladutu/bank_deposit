package itsix.bank_deposit.logic;

public class Client implements IClient {

	private IClientInformation clientInformation;

	public Client(IClientInformation clientInformation) {
		this.clientInformation = clientInformation;
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

}
