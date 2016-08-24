package itsix.bank_deposit.logic.impl;

import itsix.bank_deposit.logic.IDate;

import java.time.Month;

/**
 * Created by Geo on 24.08.2016.
 */
public class Date implements IDate {

    private int hour;
    private int minute;
    private int second;
    private int dayOfMonth;
    private Month month;
    private int year;

    public Date(int hour, int minute, int second, int dayOfMonth, Month month, int year) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }
}
