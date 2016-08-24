package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;
import itsix.bank_deposit.logic.impl.VariableInterestProduct;

import java.io.Serializable;

public class VariableInterestProductBuilder implements IProductBuilder, Serializable {

    @Override
    public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
        return new VariableInterestProduct(name, interestRate, period, currency, minSum, maxSum);
    }

}
