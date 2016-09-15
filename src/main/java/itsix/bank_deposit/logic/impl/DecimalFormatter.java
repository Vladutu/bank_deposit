package itsix.bank_deposit.logic.impl;

import java.io.Serializable;

import itsix.bank_deposit.logic.IDecimalFormatter;

/**
 * Created by Geo on 31.08.2016.
 */
public class DecimalFormatter implements IDecimalFormatter, Serializable {

	@Override
	public float format(float value) {
		return (float) Math.round(value * 100) / 100;
	}
}
