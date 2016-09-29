package itsix.bank_deposit.controller.impl;

import java.io.Serializable;

import javax.swing.JOptionPane;

import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.controller.IProductsController;
import itsix.bank_deposit.exception.InvalidFieldsException;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IProductRepository;
import itsix.bank_deposit.validator.IProductValidator;
import itsix.bank_deposit.validator.IValidationResult;
import itsix.bank_deposit.views.IMainView;
import itsix.bank_deposit.views.INewProductView;
import itsix.bank_deposit.views.IUpdateProductView;

public class ProductsController implements IProductsController, Serializable {

	private transient IMainView mainView;
	private transient INewProductView newProductView;
	private transient IUpdateProductView updateProductView;

	private IProductBuilder fixedInterestProductBuilder;
	private IProductBuilder variableInterestProductBuilder;
	private IProductBuilder currentProductBuilder;

	private IProductRepository productsRepository;
	private ICurrencyRepository currenciesRepository;

	private IProduct selectedProduct;

	private IProductValidator productValidator;

	public ProductsController(IProductBuilder fixedInterestProductBuilder,
			IProductBuilder variableInterestProductBuilder, IProductRepository productsRepository,
			ICurrencyRepository currenciesRepository, IProductValidator productValidator) {
		this.fixedInterestProductBuilder = fixedInterestProductBuilder;
		this.variableInterestProductBuilder = variableInterestProductBuilder;
		this.productsRepository = productsRepository;
		this.currenciesRepository = currenciesRepository;
		this.productValidator = productValidator;

		changeToFixedInterestProduct();
	}

	@Override
	public void openNewProductView() {
		newProductView.show(currenciesRepository.getCurrencies());
	}

	@Override
	public void changeToFixedInterestProduct() {
		currentProductBuilder = fixedInterestProductBuilder;

	}

	@Override
	public void changeToVariableInterestProduct() {
		currentProductBuilder = variableInterestProductBuilder;

	}

	@Override
	public void saveProduct() {
		IProduct product = null;
		try {
			product = createProduct();
			productsRepository.save(product);
			newProductView.closeWindow();
		} catch (InvalidFieldsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void setMainView(IMainView mainView) {
		this.mainView = mainView;
	}

	private IProduct createProduct() throws InvalidFieldsException {
		String name = newProductView.getProductName();
		float interestRate = newProductView.getProductInterestRate();
		int period = newProductView.getProductPeriod();
		ICurrency currency = newProductView.getProductCurrency();
		int minSum = newProductView.getMinSum();
		int maxSum = newProductView.getMaxSum();

		IValidationResult result = productValidator.validate(name, interestRate, period, currency, minSum, maxSum);
		if (!result.isValid()) {
			throw new InvalidFieldsException(result.getErrorDescription());
		}

		IProduct product = currentProductBuilder.build(name, interestRate, period, currency, minSum, maxSum);

		return product;
	}

	@Override
	public void updateProductInformation() {
		IProduct product = mainView.getSelectedProduct();
		if (product == null) {
			return;
		}

		mainView.updateProductDescription(product.description());
	}

	@Override
	public void deleteSelectedProduct() {
		IProduct product = mainView.getSelectedProduct();

		productsRepository.remove(product);

		mainView.clearScreen();
	}

	@Override
	public void openUpdateProductView() {
		selectedProduct = mainView.getSelectedProduct();

		String name = selectedProduct.getName();
		float interestRate = selectedProduct.getInterestRate();
		int period = selectedProduct.getPeriod();
		ICurrency currency = selectedProduct.getCurrency();
		int minSum = selectedProduct.getMinSum();
		int maxSum = selectedProduct.getMaxSum();

		updateProductView.show(currenciesRepository.getCurrencies(), name, interestRate, period, currency, minSum,
				maxSum);
	}

	@Override
	public void updateProduct() {
		String name = updateProductView.getProductName();
		float interestRate = updateProductView.getProductInterestRate();
		int period = updateProductView.getProductPeriod();
		ICurrency currency = updateProductView.getProductCurrency();
		int minSum = updateProductView.getMinSum();
		int maxSum = updateProductView.getMaxSum();

		IValidationResult result = productValidator.validate(name, interestRate, period, currency, minSum, maxSum);
		if (!result.isValid()) {
			JOptionPane.showMessageDialog(null, result.getErrorDescription());
			return;
		}

		selectedProduct.update(name, interestRate, period, currency, minSum, maxSum);

		mainView.clearScreen();
		updateProductView.closeWindow();
	}

	@Override
	public void setNewProductView(INewProductView newProductView) {
		this.newProductView = newProductView;

	}

	@Override
	public void setUpdateProductView(IUpdateProductView updateProductView) {
		this.updateProductView = updateProductView;

	}

}
