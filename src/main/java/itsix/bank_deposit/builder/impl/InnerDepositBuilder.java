package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IInnerDepositBuilder;
import itsix.bank_deposit.builder.IInnerPublisherBuilder;
import itsix.bank_deposit.logic.*;
import itsix.bank_deposit.logic.impl.InnerDeposit;

/**
 * Created by Geo on 30.08.2016.
 */
public class InnerDepositBuilder implements IInnerDepositBuilder {

    private IInnerPublisherBuilder innerPublisherBuilder;

    private IDecimalFormatter decimalFormatter;

    public InnerDepositBuilder(IInnerPublisherBuilder innerPublisherBuilder, IDecimalFormatter decimalFormatter) {
        this.innerPublisherBuilder = innerPublisherBuilder;
        this.decimalFormatter = decimalFormatter;
    }

    @Override
    public IInnerDeposit build(ICurrency currency, IInterestCalculator interestCalculator, int money, IDate clone,
                               int period) {
        return new InnerDeposit(decimalFormatter, innerPublisherBuilder.build(), currency, interestCalculator, clone, money, period);
    }
}
