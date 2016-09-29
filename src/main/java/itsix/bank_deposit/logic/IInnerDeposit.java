package itsix.bank_deposit.logic;

import itsix.bank_deposit.publisher_subscriber.IPublisher;

public interface IInnerDeposit extends IPublisher {

    void update();

    IDate getCreationDate();

    int getPeriod();

    int getDaysLeft();

    float getInterest();

    float getDepositAmount();

    float getMoneyGained();

    ICurrency getCurrency();

    boolean hasMaturated();

    void depositAllMoney(IClient client);

    void depositInterest(IClient client);

    void restart(IInterestCalculator interestCalculator);

    void accumulateInterest();
}
