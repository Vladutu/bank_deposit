package itsix.bank_deposit.logic;

public class Client implements IClient {

	private IClientInformation clientInformation;

	public Client(IClientInformation clientInformation) {
		this.clientInformation = clientInformation;
	}

}
