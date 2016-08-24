package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IProductBuilder;
import itsix.bank_deposit.logic.impl.FixedInterestProduct;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IProduct;

import java.io.Serializable;

public class FixedInterestProductBuilder implements IProductBuilder, Serializable {

    @Override
    public IProduct build(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
        return new FixedInterestProduct(name, interestRate, period, currency, minSum, maxSum);
    }

}