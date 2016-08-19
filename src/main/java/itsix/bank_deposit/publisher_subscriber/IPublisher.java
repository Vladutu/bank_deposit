package itsix.bank_deposit.publisher_subscriber;

public interface IPublisher {
	public void subscribe(ISubscriber subscriber);

	public void unsubscribe(ISubscriber subscriber);
}
