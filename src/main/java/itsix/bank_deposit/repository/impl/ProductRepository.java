package itsix.bank_deposit.repository.impl;

import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.ISubscriber;
import itsix.bank_deposit.repository.IProductRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository implements IProductRepository, Serializable {

    private List<IProduct> products;

    private IInnerPublisher publisher;

    public ProductRepository(IInnerPublisher publisher) {
        this.publisher = publisher;
        products = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        publisher.subscribe(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        publisher.unsubscribe(subscriber);
    }

    @Override
    public void save(IProduct product) {
        products.add(product);
        publisher.notifySubscribers();
    }

    @Override
    public List<IProduct> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void remove(IProduct product) {
        products.remove(product);
        publisher.notifySubscribers();

    }

}
