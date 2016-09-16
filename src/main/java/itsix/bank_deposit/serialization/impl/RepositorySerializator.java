package itsix.bank_deposit.serialization.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import itsix.bank_deposit.repository.IMainRepository;
import itsix.bank_deposit.serialization.IRepositorySerializator;

public class RepositorySerializator implements IRepositorySerializator {

	private IMainRepository mainRepository;

	@Override
	public IMainRepository deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("app.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		mainRepository = (IMainRepository) in.readObject();
		in.close();
		fileIn.close();

		return mainRepository;
	}

	@Override
	public void serialize() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("app.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(mainRepository);
		out.close();
		fileOut.close();
		System.out.printf("Serialized data is saved in app.ser");
	}

}
