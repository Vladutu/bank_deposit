package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.logic.impl.Product;

public class InnerProductBuilder implements IInnerProductBuilder, Serializable {

	@Override
	public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		return new Product(name, interestRate, period, currency, minSum, maxSum);
	}

}
