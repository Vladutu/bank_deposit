package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.logic.impl.FixedInterestProduct;

public class FixedInterestProductBuilder implements IProductBuilder, Serializable {

	private IInnerProductBuilder innerProductBuilder;

	public FixedInterestProductBuilder(IInnerProductBuilder innerProductBuilder) {
		this.innerProductBuilder = innerProductBuilder;
	}

	@Override
	public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		return new FixedInterestProduct(
				innerProductBuilder.build(name, interestRate, period, currency, minSum, maxSum));
	}

}
