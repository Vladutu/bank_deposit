package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

public interface IProductBuilder {

	IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum);

}
