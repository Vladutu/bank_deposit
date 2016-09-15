package itsix.bank_deposit.serialization;

import java.io.IOException;

import itsix.bank_deposit.repository.IMainRepository;

public interface IRepositorySerializator {

	IMainRepository deserialize() throws IOException, ClassNotFoundException;

	void serialize() throws IOException;
}
