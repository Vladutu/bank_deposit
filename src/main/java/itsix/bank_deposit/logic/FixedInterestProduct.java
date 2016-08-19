package itsix.bank_deposit.logic;

import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.builder.InnerProductBuilder;

public class FixedInterestProduct implements IProduct {

	private IProduct product;

	private IInnerProductBuilder innerProductBuilder = new InnerProductBuilder();

	public FixedInterestProduct(String name, float interestRate, int period, ICurrency currency, int minSum,
			int maxSum) {
		product = innerProductBuilder.build(name, interestRate, period, currency, minSum, maxSum);
	}

	@Override
	public String toString() {
		return product.toString();
	}

	@Override
	public String description() {
		String description = product.description();
		description += "Interest type: Fixed Interest\n";

		return description;
	}

	@Override
	public String getName() {
		return product.getName();
	}

	@Override
	public float getInterestRate() {
		return product.getInterestRate();
	}

	@Override
	public int getPeriod() {
		return product.getPeriod();
	}

	@Override
	public ICurrency getCurrency() {
		return product.getCurrency();
	}

	@Override
	public int getMinSum() {
		return product.getMinSum();
	}

	@Override
	public int getMaxSum() {
		return product.getMaxSum();
	}

	@Override
	public void update(String name, float interestRate, int period, ICurrency currency, int minSum, int maxSum) {
		product.update(name, interestRate, period, currency, minSum, maxSum);

	}
}
