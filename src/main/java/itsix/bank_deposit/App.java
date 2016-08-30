package itsix.bank_deposit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.ICapitalizationButtonStateBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.builder.IDateBuilder;
import itsix.bank_deposit.builder.IDepositGeneratorBuilder;
import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.builder.IInterestCalculatorBuilder;
import itsix.bank_deposit.builder.IOperationBuilder;
import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.builder.IValidationResultBuilder;
import itsix.bank_deposit.builder.IValidatorBuilder;
import itsix.bank_deposit.builder.impl.AccountBuilder;
import itsix.bank_deposit.builder.impl.CapitalizationStateBuilder;
import itsix.bank_deposit.builder.impl.ClientBuilder;
import itsix.bank_deposit.builder.impl.ClientInformationBuilder;
import itsix.bank_deposit.builder.impl.DateBuilder;
import itsix.bank_deposit.builder.impl.DepositGeneratorBuilder;
import itsix.bank_deposit.builder.impl.FixedInterestProductBuilder;
import itsix.bank_deposit.builder.impl.InnerProductBuilder;
import itsix.bank_deposit.builder.impl.InnerPublisherBuilder;
import itsix.bank_deposit.builder.impl.InterestCalculatorBuilder;
import itsix.bank_deposit.builder.impl.OperationBuilder;
import itsix.bank_deposit.builder.impl.ValidationResultBuilder;
import itsix.bank_deposit.builder.impl.ValidatorBuilder;
import itsix.bank_deposit.builder.impl.VariableInterestProductBuilder;
import itsix.bank_deposit.controller.IClientsController;
import itsix.bank_deposit.controller.IDateController;
import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.controller.ISerializerController;
import itsix.bank_deposit.controller.impl.ClientsController;
import itsix.bank_deposit.controller.impl.DateController;
import itsix.bank_deposit.controller.impl.ProductsController;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.impl.Date;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IDepositRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.repository.impl.ClientRepository;
import itsix.bank_deposit.repository.impl.CurrencyRepository;
import itsix.bank_deposit.repository.impl.DepositRepository;
import itsix.bank_deposit.repository.impl.ProductRepository;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IDepositValidator;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.impl.ClientValidator;
import itsix.bank_deposit.validator.impl.DepositValidator;
import itsix.bank_deposit.validator.impl.ProductValidator;
import itsix.bank_deposit.views.IBankAccountView;
import itsix.bank_deposit.views.IDayChangerView;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewAccountView;
import itsix.bank_deposit.views.INewClientView;
import itsix.bank_deposit.views.INewDepositView;
import itsix.bank_deposit.views.INewProductView;
import itsix.bank_deposit.views.IUpdateProductView;
import itsix.bank_deposit.views.impl.BankAccountView;
import itsix.bank_deposit.views.impl.DayChangerView;
import itsix.bank_deposit.views.impl.MainView;
import itsix.bank_deposit.views.impl.NewAccountView;
import itsix.bank_deposit.views.impl.NewClientView;
import itsix.bank_deposit.views.impl.NewDepositView;
import itsix.bank_deposit.views.impl.NewProductView;
import itsix.bank_deposit.views.impl.UpdateProductView;

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
		// e.printStackTrace();
		// }

		IInnerPublisherBuilder innerPublisherBuilder = new InnerPublisherBuilder();

		IProductRepository productsRepository = new ProductRepository(innerPublisherBuilder.build());
		// IProductRepository productsRepository =
		// mainRepository.getProductRepository();
		ICurrencyRepository currencyRepository = new CurrencyRepository();
		// ICurrencyRepository currencyRepository =
		// mainRepository.getCurrencyRepository();

		IDepositGeneratorBuilder depositGeneratorBuilder = new DepositGeneratorBuilder();
		IInterestCalculatorBuilder interestCalculatorBuilder = new InterestCalculatorBuilder();
		IInnerProductBuilder innerProductBuilder = new InnerProductBuilder(depositGeneratorBuilder,
				interestCalculatorBuilder);
		IProductBuilder fixedInterestProductBuilder = new FixedInterestProductBuilder(innerProductBuilder);
		IProductBuilder variableInterestProductBuilder = new VariableInterestProductBuilder(innerProductBuilder);

		IValidationResultBuilder validationResultBuilder = new ValidationResultBuilder();
		IValidatorBuilder validatorBuilder = new ValidatorBuilder(validationResultBuilder);
		IProductValidator productValidator = new ProductValidator(validatorBuilder);
		IDepositValidator depositValidator = new DepositValidator(validatorBuilder);

		IProductsController productsController = new ProductsController(fixedInterestProductBuilder,
				variableInterestProductBuilder, productsRepository, currencyRepository, productValidator);

		IClientRepository clientRepository = new ClientRepository();
		// IClientRepository clientRepository =
		// mainRepository.getClientRepository();
		IClientInformationBuilder clientInformationBuilder = new ClientInformationBuilder();

		// TODO: this must be retrieved from the repository
		IDate currentDate = new Date(1);
		IDateBuilder dateBuilder = new DateBuilder(currentDate);
		IOperationBuilder operationBuilder = new OperationBuilder(dateBuilder);
		IAccountBuilder accountBuilder = new AccountBuilder(currencyRepository, innerPublisherBuilder,
				operationBuilder);
		IClientBuilder clientBuilder = new ClientBuilder(clientInformationBuilder, accountBuilder, currencyRepository);

		IClientValidator clientValidator = new ClientValidator(validatorBuilder);

		IDepositRepository depositsRepository = new DepositRepository();
		IClientsController clientsController = new ClientsController(depositsRepository, clientRepository,
				productsRepository, clientBuilder, clientValidator, depositValidator, accountBuilder);

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

		IDateController dateController = new DateController(currentDate);
		IDayChangerView dayChangerView = new DayChangerView(dateController);
		dateController.setDateChangerView(dayChangerView);

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
