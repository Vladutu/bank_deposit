package itsix.bank_deposit.repository;

import itsix.bank_deposit.logic.IDate;

public interface IMainRepository {

	void setProductRepository(IProductRepository productsRepository);

	void setCurrencyRepository(ICurrencyRepository currencyRepository);

	void setClientRepository(IClientRepository clientRepository);

	IClientRepository getClientRepository();

	ICurrencyRepository getCurrencyRepository();

	IProductRepository getProductRepository();

	IDate getCurrentDate();

	void setCurrentDate(IDate currentDate);

}
