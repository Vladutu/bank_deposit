package itsix.bank_deposit.repository.impl;

import itsix.bank_deposit.exception.EntityNotFoundException;
import itsix.bank_deposit.logic.IClient;
import itsix.bank_deposit.repository.IClientRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IClientRepository, Serializable {

    private List<IClient> clients;

    public ClientRepository() {
        clients = new ArrayList<>();
    }

    @Override
    public void save(IClient client) {
        clients.add(client);
    }

    @Override
    public IClient findBySsn(String ssn) throws EntityNotFoundException {
        for (IClient client : clients) {
            if (client.hasSsn(ssn)) {
                return client;
            }
        }

        throw new EntityNotFoundException();
    }
}
