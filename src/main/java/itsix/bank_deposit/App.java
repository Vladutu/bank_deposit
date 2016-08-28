package itsix.bank_deposit;

import itsix.bank_deposit.builder.*;
import itsix.bank_deposit.builder.impl.*;
import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.controller.ISerializerController;
import itsix.bank_deposit.controller.impl.ClientsController;
import itsix.bank_deposit.controller.impl.ProductsController;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.repository.impl.ClientRepository;
import itsix.bank_deposit.repository.impl.CurrencyRepository;
import itsix.bank_deposit.repository.impl.ProductRepository;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.impl.ClientValidator;
import itsix.bank_deposit.validator.impl.ProductValidator;
import itsix.bank_deposit.views.*;
import itsix.bank_deposit.views.impl.*;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // IMainRepositoryBuilder mainRepositoryBuilder = new
        // MainRepositoryBuilder();
        // IMainRepository mainRepository = null;
        //
        // try {
        // mainRepository = mainRepositoryBuilder.deserialize();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        IInnerPublisherBuilder innerPublisherBuilder = new InnerPublisherBuilder();

        IProductRepository productsRepository = new ProductRepository(innerPublisherBuilder.build());
        // IProductRepository productsRepository = mainRepository.getProductRepository();
        ICurrencyRepository currencyRepository = new CurrencyRepository();
        // ICurrencyRepository currencyRepository = mainRepository.getCurrencyRepository();

        IInterestBuilder interestBuilder = new InterestBuilder();
        IInnerProductBuilder innerProductBuilder = new InnerProductBuilder(interestBuilder);
        IProductBuilder fixedInterestProductBuilder = new FixedInterestProductBuilder(innerProductBuilder);
        IProductBuilder variableInterestProductBuilder = new VariableInterestProductBuilder(innerProductBuilder);

        IValidationResultBuilder validationResultBuilder = new ValidationResultBuilder();
        IValidatorBuilder validatorBuilder = new ValidatorBuilder(validationResultBuilder);
        IProductValidator productValidator = new ProductValidator(validatorBuilder);

        IProductsController productsController = new ProductsController(interestBuilder, fixedInterestProductBuilder,
                variableInterestProductBuilder, productsRepository, currencyRepository, productValidator);

        IClientRepository clientRepository = new ClientRepository();
        // IClientRepository clientRepository = mainRepository.getClientRepository();
        IClientInformationBuilder clientInformationBuilder = new ClientInformationBuilder();

        IDateBuilder dateBuilder = new DateBuilder();
        IOperationBuilder operationBuilder = new OperationBuilder(dateBuilder);
        IAccountBuilder accountBuilder = new AccountBuilder(currencyRepository, innerPublisherBuilder,
                operationBuilder);
        IClientBuilder clientBuilder = new ClientBuilder(clientInformationBuilder, accountBuilder, currencyRepository);

        IClientValidator clientValidator = new ClientValidator(validatorBuilder);

        IClientsController clientsController = new ClientsController(clientRepository, productsRepository,
                clientBuilder, clientValidator, accountBuilder);

        INewProductView newProductView = new NewProductView(productsController);
        productsController.setNewProductView(newProductView);

        IUpdateProductView updateProductView = new UpdateProductView(productsController);
        productsController.setUpdateProductView(updateProductView);

        INewClientView newClientView = new NewClientView(clientsController);
        clientsController.setNewClientView(newClientView);

        IBankAccountView bankAccountView = new BankAccountView(clientsController);
        clientsController.setBankAccountView(bankAccountView);

        INewAccountView newAccountView = new NewAccountView(clientsController);
        clientsController.setNewAccountView(newAccountView);

        INewDepositView newDepositView = new NewDepositView(clientsController);
        clientsController.setNewDepositView(newDepositView);

        ICapitalizationButtonStateBuilder capitalizationButtonStateBuilder = new CapitalizationStateBuilder(
                newDepositView);
        clientsController.setCapitalizationButtonState(capitalizationButtonStateBuilder.build());

        // ISerializerController serializerController = new
        // SerializerController(mainRepositoryBuilder);
        ISerializerController serializerController = null;

        SwingUtilities.invokeLater(() -> {
            IMainView mainView = new MainView(productsController, clientsController, serializerController,
                    productsRepository.getProducts());
            productsRepository.subscribe(mainView);
            productsController.setMainView(mainView);
            clientsController.setMainView(mainView);

            // IMainRepository mainRepository = new MainRepository();
            // mainRepository.setClientRepository(clientRepository);
            // mainRepository.setCurrencyRepository(currencyRepository);
            // mainRepository.setProductRepository(productsRepository);
            //
            // IMainRepositoryBuilder mainRepositoryBuilder = new
            // MainRepositoryBuilder(mainRepository);
            // try {
            // mainRepositoryBuilder.serialize();
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
        });
    }
}
