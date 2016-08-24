package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class WithdrawOperation implements IOperation {

    private int creditBefore;
    private int creditAfter;
    private IDate iDate;

    public WithdrawOperation(int creditBefore, int creditAfter, IDate iDate) {
        this.creditBefore = creditBefore;
        this.creditAfter = creditAfter;
        this.iDate = iDate;
    }
}
