package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.IAccount;
import itsix.bank_deposit.logic.ICurrency;

public interface IAccountBuilder {

    IAccount buildDefaultAccount();

    IAccount build(ICurrency currency, int initialDeposit);
}
