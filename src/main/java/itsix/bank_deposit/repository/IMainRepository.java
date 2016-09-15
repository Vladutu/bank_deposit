package itsix.bank_deposit.repository;

import itsix.bank_deposit.logic.IDate;

public interface IMainRepository {

	IDate getCurrentDate();

	void setCurrentDate(IDate currentDate);

	IClientRepository getClientRepository();

	void setClientRepository(IClientRepository clientRepository);

	ICurrencyRepository getCurrencyRepository();

	void setCurrencyRepository(ICurrencyRepository currencyRepository);

	IProductRepository getProductRepository();

	void setProductRepository(IProductRepository productRepository);

	IDepositRepository getDepositRepository();

	void setDepositRepository(IDepositRepository depositRepository);

}
