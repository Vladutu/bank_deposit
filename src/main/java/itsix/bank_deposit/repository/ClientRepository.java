package itsix.bank_deposit.repository;

import java.util.ArrayList;
import java.util.List;

import itsix.bank_deposit.logic.IClient;

public class ClientRepository implements IClientRepository {

	private List<IClient> clients;

	public ClientRepository() {
		clients = new ArrayList<>();
	}

	@Override
	public void save(IClient client) {
		clients.add(client);
	}
}
