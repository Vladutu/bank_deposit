package itsix.bank_deposit;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.bank_deposit.builder.AccountBuilder;
import itsix.bank_deposit.builder.ClientBuilder;
import itsix.bank_deposit.builder.ClientInformationBuilder;
import itsix.bank_deposit.builder.FixedInterestProductBuilder;
import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.builder.IMainRepositoryBuilder;
import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.builder.InnerPublisherBuilder;
import itsix.bank_deposit.builder.MainRepositoryBuilder;
import itsix.bank_deposit.builder.ValidationResultBuilder;
import itsix.bank_deposit.builder.ValidatorBuilder;
import itsix.bank_deposit.builder.VariableInterestProductBuilder;
import itsix.bank_deposit.logic.controller.ClientsController;
import itsix.bank_deposit.logic.controller.IClientsController;
import itsix.bank_deposit.logic.controller.IProductsController;
import itsix.bank_deposit.logic.controller.ISerializerController;
import itsix.bank_deposit.logic.controller.ProductsController;
import itsix.bank_deposit.logic.controller.SerializerController;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IMainRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.validator.ClientValidator;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.ProductValidator;
import itsix.bank_deposit.views.BankAccountView;
import itsix.bank_deposit.views.IBankAccountView;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewClientView;
import itsix.bank_deposit.views.INewProductView;
import itsix.bank_deposit.views.IUpdateProductView;
import itsix.bank_deposit.views.MainView;
import itsix.bank_deposit.views.NewClientView;
import itsix.bank_deposit.views.NewProductView;
import itsix.bank_deposit.views.UpdateProductView;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		IMainRepositoryBuilder mainRepositoryBuilder = new MainRepositoryBuilder();
		IMainRepository mainRepository = null;

		try {
			mainRepository = mainRepositoryBuilder.deserialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IInnerPublisherBuilder innerPublisherBuilder = new InnerPublisherBuilder();

		IInnerPublisher productsInnerPublisher = innerPublisherBuilder.build();

		IProductRepository productsRepository = mainRepository.getProductRepository();
		ICurrencyRepository currencyRepository = mainRepository.getCurrencyRepository();

		IProductBuilder fixedInterestProductBuilder = new FixedInterestProductBuilder();
		IProductBuilder variableInterestProductBuilder = new VariableInterestProductBuilder();

		IValidationResultBuilder validationResultBuilder = new ValidationResultBuilder();
		IValidatorBuilder validatorBuilder = new ValidatorBuilder(validationResultBuilder);
		IProductValidator productValidator = new ProductValidator(validatorBuilder);

		IProductsController productsController = new ProductsController(fixedInterestProductBuilder,
				variableInterestProductBuilder, productsRepository, currencyRepository, productValidator);

		IClientRepository clientRepository = mainRepository.getClientRepository();
		IClientInformationBuilder clientInformationBuilder = new ClientInformationBuilder();
		IAccountBuilder accountBuilder = new AccountBuilder(currencyRepository, innerPublisherBuilder);
		IClientBuilder clientBuilder = new ClientBuilder(clientInformationBuilder, accountBuilder);

		IClientValidator clientValidator = new ClientValidator(validatorBuilder);
		IClientsController clientsController = new ClientsController(clientRepository, clientBuilder, clientValidator);

		INewProductView newProductView = new NewProductView(productsController);
		productsController.setNewProductView(newProductView);

		IUpdateProductView updateProductView = new UpdateProductView(productsController);
		productsController.setUpdateProductView(updateProductView);

		INewClientView newClientView = new NewClientView(clientsController);
		clientsController.setNewClientView(newClientView);

		IBankAccountView bankAccountView = new BankAccountView(clientsController);
		clientsController.setBankAccountView(bankAccountView);

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
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		ISerializerController serializerController = new SerializerController(mainRepositoryBuilder);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				IMainView mainView = new MainView(productsController, clientsController, serializerController,
						productsRepository.getProducts());
				productsRepository.subscribe(mainView);
				productsController.setMainView(mainView);
				clientsController.setMainView(mainView);
			}
		});
	}
}
