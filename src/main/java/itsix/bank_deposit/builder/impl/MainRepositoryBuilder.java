package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IMainRepositoryBuilder;
import itsix.bank_deposit.repository.IMainRepository;

import java.io.*;

public class MainRepositoryBuilder implements IMainRepositoryBuilder, Serializable {

    private IMainRepository mainRepository;

    public MainRepositoryBuilder(IMainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public MainRepositoryBuilder() {

    }

    @Override
    public IMainRepository deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("d:\\mainRepository.ser");
        ObjectInputStream ois = new ObjectInputStream(fin);
        mainRepository = (IMainRepository) ois.readObject();
        ois.close();

        return mainRepository;
    }

    @Override
    public void serialize() throws IOException {
        FileOutputStream fout = new FileOutputStream("d:\\mainRepository.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(mainRepository);
        oos.close();
    }

}
