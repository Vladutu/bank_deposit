package itsix.bank_deposit.builder.impl;

import java.io.Serializable;

import itsix.bank_deposit.builder.IDepositGeneratorBuilder;
import itsix.bank_deposit.builder.IInnerProductBuilder;
import itsix.bank_deposit.builder.IInterestCalculatorBuilder;
import itsix.bank_deposit.logic.ICurrency;
import itsix.bank_deposit.logic.IInnerProduct;
import itsix.bank_deposit.logic.impl.InnerProduct;
import itsix.bank_deposit.repository.IDepositRepository;

public class InnerProductBuilder implements IInnerProductBuilder, Serializable {

	private IDepositGeneratorBuilder depositGeneratorBuilder;

	private IInterestCalculatorBuilder interestCalculatorBuilder;

	private IDepositRepository depositRepository;

	public InnerProductBuilder(IDepositGeneratorBuilder depositGeneratorBuilder,
			IInterestCalculatorBuilder interestCalculatorBuilder, IDepositRepository depositRepository) {
		this.depositGeneratorBuilder = depositGeneratorBuilder;
		this.interestCalculatorBuilder = interestCalculatorBuilder;
		this.depositRepository = depositRepository;
	}

	@Override
	public IInnerProduct build(String name, float interestRate, int period, ICurrency currency, int minSum,
			int maxSum) {
		return new InnerProduct(name, interestRate, period, currency, minSum, maxSum, depositGeneratorBuilder.build(),
				interestCalculatorBuilder.build(interestRate), depositRepository, interestCalculatorBuilder);
	}

}
