package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IInnerProduct;

public interface IInnerProductBuilder {

	IInnerProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum);

}
