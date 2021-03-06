package itsix.bank_deposit.repository;

import itsix.bank_deposit.exception.EntityNotFoundException;
import itsix.bank_deposit.logic.IClient;

public interface IClientRepository {

	void save(IClient client);

	IClient findBySsn(String ssn) throws EntityNotFoundException;

}
