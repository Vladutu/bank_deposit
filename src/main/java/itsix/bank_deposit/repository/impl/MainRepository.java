package itsix.bank_deposit.repository.impl;

import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IMainRepository;
import itsix.bank_deposit.repository.IProductRepository;

import java.io.Serializable;

public class MainRepository implements IMainRepository, Serializable {

	private ICurrencyRepository currencyRepository;

	private IClientRepository clientRepository;

	private IProductRepository productRepository;

	@Override
	public void setProductRepository(IProductRepository productsRepository) {
		this.productRepository = productsRepository;

	}

	@Override
	public void setCurrencyRepository(ICurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public void setClientRepository(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public IClientRepository getClientRepository() {
		return clientRepository;
	}

	@Override
	public ICurrencyRepository getCurrencyRepository() {
		return currencyRepository;
	}

	@Override
	public IProductRepository getProductRepository() {
		return productRepository;
	}

}
