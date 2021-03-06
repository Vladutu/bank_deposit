package itsix.bank_deposit;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import itsix.bank_deposit.builder.IAccountBuilder;
import itsix.bank_deposit.builder.ICapitalizationButtonStateBuilder;
import itsix.bank_deposit.builder.IClientBuilder;
import itsix.bank_deposit.builder.IClientInformationBuilder;
import itsix.bank_deposit.builder.IDateBuilder;
import itsix.bank_deposit.builder.IDepositBuilder;
import itsix.bank_deposit.builder.IDepositGeneratorBuilder;
import itsix.bank_deposit.builder.IInnerDepositBuilder;
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
import itsix.bank_deposit.builder.impl.DepositBuilder;
import itsix.bank_deposit.builder.impl.DepositGeneratorBuilder;
import itsix.bank_deposit.builder.impl.FixedInterestProductBuilder;
import itsix.bank_deposit.builder.impl.InnerDepositBuilder;
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
import itsix.bank_deposit.controller.impl.ClientsController;
import itsix.bank_deposit.controller.impl.DateController;
import itsix.bank_deposit.controller.impl.ProductsController;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IDecimalFormatter;
import itsix.bank_deposit.logic.ITimeScheduler;
import itsix.bank_deposit.logic.impl.DecimalFormatter;
import itsix.bank_deposit.logic.impl.TimeScheduler;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IDepositRepository;
import itsix.bank_deposit.repository.IMainRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.serialization.IRepositorySerializator;
import itsix.bank_deposit.serialization.impl.RepositorySerializator;
import itsix.bank_deposit.validator.IClientValidator;
import itsix.bank_deposit.validator.IDepositValidator;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.impl.ClientValidator;
import itsix.bank_deposit.validator.impl.DepositValidator;
import itsix.bank_deposit.validator.impl.ProductValidator;
import itsix.bank_deposit.views.IBankAccountView;
import itsix.bank_deposit.views.ICheckDepositsView;
import itsix.bank_deposit.views.IDayChangerView;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewAccountView;
import itsix.bank_deposit.views.INewClientView;
import itsix.bank_deposit.views.INewDepositView;
import itsix.bank_deposit.views.INewProductView;
import itsix.bank_deposit.views.IUpdateProductView;
import itsix.bank_deposit.views.impl.BankAccountView;
import itsix.bank_deposit.views.impl.CheckDepositsView;
import itsix.bank_deposit.views.impl.DayChangerView;
import itsix.bank_deposit.views.impl.MainView;
import itsix.bank_deposit.views.impl.NewAccountView;
import itsix.bank_deposit.views.impl.NewClientView;
import itsix.bank_deposit.views.impl.NewDepositView;
import itsix.bank_deposit.views.impl.NewProductView;
import itsix.bank_deposit.views.impl.UpdateProductView;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		IRepositorySerializator repositorySerializator = new RepositorySerializator();
		IMainRepository mainRepository = repositorySerializator.deserialize();

		// TODO: this must be retrieved from the repository
		IDate currentDate = mainRepository.getCurrentDate();
		IDateBuilder dateBuilder = new DateBuilder(currentDate);

		IDepositRepository depositsRepository = mainRepository.getDepositRepository();

		IInnerPublisherBuilder innerPublisherBuilder = new InnerPublisherBuilder();

		IProductRepository productsRepository = mainRepository.getProductRepository();
		productsRepository.setInnerPublisher(innerPublisherBuilder.build());

		ICurrencyRepository currencyRepository = mainRepository.getCurrencyRepository();

		IDecimalFormatter decimalFormatter = new DecimalFormatter();
		IInnerDepositBuilder innerDepositBuilder = new InnerDepositBuilder(innerPublisherBuilder, decimalFormatter);
		IDepositBuilder depositBuilder = new DepositBuilder(innerDepositBuilder, currentDate);
		IDepositGeneratorBuilder depositGeneratorBuilder = new DepositGeneratorBuilder(depositBuilder);
		IInterestCalculatorBuilder interestCalculatorBuilder = new InterestCalculatorBuilder();
		IInnerProductBuilder innerProductBuilder = new InnerProductBuilder(depositGeneratorBuilder,
				interestCalculatorBuilder, depositsRepository);
		IProductBuilder fixedInterestProductBuilder = new FixedInterestProductBuilder(innerProductBuilder);
		IProductBuilder variableInterestProductBuilder = new VariableInterestProductBuilder(innerProductBuilder);

		IValidationResultBuilder validationResultBuilder = new ValidationResultBuilder();
		IValidatorBuilder validatorBuilder = new ValidatorBuilder(validationResultBuilder);
		IProductValidator productValidator = new ProductValidator(validatorBuilder);
		IDepositValidator depositValidator = new DepositValidator(validatorBuilder);

		IProductsController productsController = new ProductsController(fixedInterestProductBuilder,
				variableInterestProductBuilder, productsRepository, currencyRepository, productValidator);

		IClientRepository clientRepository = mainRepository.getClientRepository();
		IClientInformationBuilder clientInformationBuilder = new ClientInformationBuilder();

		IOperationBuilder operationBuilder = new OperationBuilder(dateBuilder);
		IAccountBuilder accountBuilder = new AccountBuilder(currencyRepository, innerPublisherBuilder,
				operationBuilder);
		IClientBuilder clientBuilder = new ClientBuilder(clientInformationBuilder, accountBuilder, currencyRepository);

		IClientValidator clientValidator = new ClientValidator(validatorBuilder);

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

		ICheckDepositsView checkDepositsView = new CheckDepositsView(clientsController);
		clientsController.setCheckDepositsView(checkDepositsView);

		ITimeScheduler timeScheduler = new TimeScheduler(depositsRepository.getDeposits());
		IDateController dateController = new DateController(currentDate, timeScheduler);
		IDayChangerView dayChangerView = new DayChangerView(dateController);
		dateController.setDateChangerView(dayChangerView);

		ICapitalizationButtonStateBuilder capitalizationButtonStateBuilder = new CapitalizationStateBuilder(
				newDepositView);
		clientsController.setCapitalizationButtonState(capitalizationButtonStateBuilder.build());

		SwingUtilities.invokeLater(() -> {
			IMainView mainView = new MainView(repositorySerializator, productsController, clientsController,
					productsRepository.getProducts());
			productsRepository.subscribe(mainView);
			productsController.setMainView(mainView);
			clientsController.setMainView(mainView);
		});
	}
}
