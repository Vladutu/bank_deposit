package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDecimalFormatter;

/**
 * Created by Geo on 31.08.2016.
 */
public class DecimalFormatter implements IDecimalFormatter {

    @Override
    public float format(float value) {
        return (float) Math.round(value * 100) / 100;
    }
}
