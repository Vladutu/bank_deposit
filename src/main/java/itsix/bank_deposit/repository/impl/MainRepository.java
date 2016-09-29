package itsix.bank_deposit.repository.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.repository.IClientRepository;
import itsix.bank_deposit.repository.ICurrencyRepository;
import itsix.bank_deposit.repository.IDepositRepository;
import itsix.bank_deposit.repository.IMainRepository;
import itsix.bank_deposit.repository.IProductRepository;

public class MainRepository implements IMainRepository, Serializable {

	private IDate currentDate;

	private IClientRepository clientRepository;

	private ICurrencyRepository currencyRepository;

	private IProductRepository productRepository;

	private IDepositRepository depositRepository;

	@Override
	public IDate getCurrentDate() {
		return currentDate;
	}

	@Override
	public void setCurrentDate(IDate currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public IClientRepository getClientRepository() {
		return clientRepository;
	}

	@Override
	public void setClientRepository(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public ICurrencyRepository getCurrencyRepository() {
		return currencyRepository;
	}

	@Override
	public void setCurrencyRepository(ICurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public IProductRepository getProductRepository() {
		return productRepository;
	}

	@Override
	public void setProductRepository(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public IDepositRepository getDepositRepository() {
		return depositRepository;
	}

	@Override
	public void setDepositRepository(IDepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}

}
