package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.IOperation;

/**
 * Created by Geo on 24.08.2016.
 */
public class DepositOperation implements IOperation {

    private int debitBefore;

    private int debitAfter;

    private IDate date;

    public DepositOperation(int debitBefore, int debitAfter, IDate date) {
        this.debitBefore = debitBefore;
        this.debitAfter = debitAfter;
        this.date = date;
    }
}
