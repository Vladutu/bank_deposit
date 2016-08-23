package itsix.bank_deposit.controller;

import itsix.bank_deposit.builder.IMainRepositoryBuilder;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;

public class SerializerController implements ISerializerController, Serializable {

    private IMainRepositoryBuilder mainRepositoryBuilder;

    public SerializerController(IMainRepositoryBuilder mainRepositoryBuilder) {
        this.mainRepositoryBuilder = mainRepositoryBuilder;

    }

    @Override
    public void serialize() {
        try {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
                e.printStackTrace();
            }
            mainRepositoryBuilder.serialize();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
