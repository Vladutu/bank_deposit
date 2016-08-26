package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.logic.impl.VariableInterestProduct;

public class VariableInterestProductBuilder implements IProductBuilder, Serializable {

	private IInnerProductBuilder innerProductBuilder;

	public VariableInterestProductBuilder(IInnerProductBuilder innerProductBuilder) {
		this.innerProductBuilder = innerProductBuilder;
	}

	@Override
	public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		return new VariableInterestProduct(innerProductBuilder, name, interestRate, period, currency, minSum, maxSum);
	}

}
