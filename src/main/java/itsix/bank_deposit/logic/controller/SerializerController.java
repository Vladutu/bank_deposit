package itsix.bank_deposit.logic.controller;

import java.io.IOException;

import itsix.bank_deposit.builder.IMainRepositoryBuilder;

public class SerializerController implements ISerializerController {

	private IMainRepositoryBuilder mainRepositoryBuilder;

	public SerializerController(IMainRepositoryBuilder mainRepositoryBuilder) {
		this.mainRepositoryBuilder = mainRepositoryBuilder;

	}

	@Override
	public void serialize() {
		try {
			mainRepositoryBuilder.serialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
