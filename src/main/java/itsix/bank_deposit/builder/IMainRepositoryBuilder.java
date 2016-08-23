package itsix.bank_deposit.builder;

import java.io.IOException;

import itsix.bank_deposit.repository.IMainRepository;

public interface IMainRepositoryBuilder {

	IMainRepository deserialize() throws IOException, ClassNotFoundException;

	void serialize() throws IOException;

}
