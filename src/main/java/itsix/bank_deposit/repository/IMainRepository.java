package itsix.bank_deposit.repository;

public interface IMainRepository {

	void setProductRepository(IProductRepository productsRepository);

	void setCurrencyRepository(ICurrencyRepository currencyRepository);

	void setClientRepository(IClientRepository clientRepository);

	IClientRepository getClientRepository();

	ICurrencyRepository getCurrencyRepository();

	IProductRepository getProductRepository();

}
