package itsix.bank_deposit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.bank_deposit.builder.ClientBuilder;
import itsix.bank_deposit.builder.ClientInformationBuilder;
import itsix.bank_deposit.builder.FixedInterestProductBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.builder.ValidationResultBuilder;
import itsix.bank_deposit.builder.ValidatorBuilder;
import itsix.bank_deposit.builder.VariableInterestProductBuilder;
import itsix.bank_deposit.logic.IClientBuilder;
import itsix.bank_deposit.logic.controller.ClientsController;
import itsix.bank_deposit.logic.controller.IClientsController;
import itsix.bank_deposit.logic.controller.IProductsController;
import itsix.bank_deposit.logic.controller.ProductsController;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;
import itsix.bank_deposit.publisher_subscriber.Publisher;
import itsix.bank_deposit.repository.ClientRepository;
import itsix.bank_deposit.repository.CurrencyRepository;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.repository.ProductRepository;
import itsix.bank_deposit.validator.ClientValidator;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.ProductValidator;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.MainView;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher productsInnerPublisher = new Publisher(subscribers);

		IProductRepository productsRepository = new ProductRepository(productsInnerPublisher);
		ICurrencyRepository currencyRepository = new CurrencyRepository();

		IProductBuilder fixedInterestProductBuilder = new FixedInterestProductBuilder();
		IProductBuilder variableInterestProductBuilder = new VariableInterestProductBuilder();

		IValidationResultBuilder validationResultBuilder = new ValidationResultBuilder();
		IValidatorBuilder validatorBuilder = new ValidatorBuilder(validationResultBuilder);
		IProductValidator productValidator = new ProductValidator(validatorBuilder);

		IProductsController productsController = new ProductsController(fixedInterestProductBuilder,
				variableInterestProductBuilder, productsRepository, currencyRepository, productValidator);

		IClientRepository clientRepository = new ClientRepository();
		IClientInformationBuilder clientInformationBuilder = new ClientInformationBuilder();
		IClientBuilder clientBuilder = new ClientBuilder(clientInformationBuilder);

		IClientValidator clientValidator = new ClientValidator(validatorBuilder);
		IClientsController clientsController = new ClientsController(clientRepository, clientBuilder, clientValidator);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				IMainView mainView = new MainView(productsController, clientsController,
						productsRepository.getProducts());
				subscribers.add(mainView);
				productsController.setMainView(mainView);
			}
		});
	}
}
