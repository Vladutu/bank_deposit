package itsix.bank_deposit.builder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import itsix.bank_deposit.repository.IMainRepository;

public class MainRepositoryBuilder implements IMainRepositoryBuilder {

	private IMainRepository mainRepository;

	public MainRepositoryBuilder(IMainRepository mainRepository) {
		this.mainRepository = mainRepository;
	}

	public MainRepositoryBuilder() {

	}

	@Override
	public IMainRepository deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("e:\\mainRepository.ser");
		ObjectInputStream ois = new ObjectInputStream(fin);
		mainRepository = (IMainRepository) ois.readObject();
		ois.close();

		return mainRepository;
	}

	@Override
	public void serialize() throws IOException {
		FileOutputStream fout = new FileOutputStream("e:\\mainRepository.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(mainRepository);
		oos.close();
	}

}
