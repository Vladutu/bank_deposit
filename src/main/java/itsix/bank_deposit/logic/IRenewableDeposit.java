package itsix.bank_deposit.logic;

public interface IRenewableDeposit extends IDeposit {

    void restart(IInterestCalculator interestCalculator);

    IDeposit createNoRenewalClone(IDeposit parent);
}
