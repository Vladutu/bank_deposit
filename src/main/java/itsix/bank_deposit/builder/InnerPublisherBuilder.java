package itsix.bank_deposit.builder;

import java.util.ArrayList;

import itsix.bank_deposit.publisher_subscriber.IInnerPublisher;
import itsix.bank_deposit.publisher_subscriber.Publisher;

public class InnerPublisherBuilder implements IInnerPublisherBuilder {

	@Override
	public IInnerPublisher build() {
		return new Publisher(new ArrayList<>());
	}

}
