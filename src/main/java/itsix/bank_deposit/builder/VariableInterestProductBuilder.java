package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.logic.VariableInterestProduct;

public class VariableInterestProductBuilder implements IProductBuilder {

	@Override
	public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		return new VariableInterestProduct(name, interestRate, period, currency, minSum, maxSum);
	}

}