package itsix.bank_deposit.builder;

import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.Publisher;

import java.io.Serializable;
import java.util.ArrayList;

public class InnerPublisherBuilder implements IInnerPublisherBuilder, Serializable {

    @Override
    public IInnerPublisher build() {
        return new Publisher(new ArrayList<>());
    }

}
