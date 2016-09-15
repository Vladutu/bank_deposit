package itsix.bank_deposit.repository;

import java.util.List;

import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.IPublisher;

public interface IProductRepository extends IPublisher {

	void save(IProduct product);

	List<IProduct> getProducts();

	void remove(IProduct product);

	void setInnerPublisher(IInnerPublisher build);

}
