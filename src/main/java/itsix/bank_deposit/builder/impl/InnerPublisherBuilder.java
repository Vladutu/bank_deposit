package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.impl.Publisher;

import java.io.Serializable;
import java.util.ArrayList;

public class InnerPublisherBuilder implements IInnerPublisherBuilder, Serializable {

    @Override
    public IInnerPublisher build() {
        return new Publisher(new ArrayList<>());
    }

}
