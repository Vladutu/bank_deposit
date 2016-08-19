package itsix.bank_deposit.builder;

import itsix.bank_deposit.logic.FixedInterestProduct;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

public class FixedInterestProductBuilder implements IProductBuilder {
	
	@Override
	public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		return new FixedInterestProduct(name, interestRate, period, currency, minSum, maxSum);
	}

}
