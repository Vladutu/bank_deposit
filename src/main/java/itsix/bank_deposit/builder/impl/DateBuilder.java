package itsix.bank_deposit.builder.impl;

import itsix.bank_deposit.builder.IDateBuilder;
import itsix.bank_deposit.logic.IDate;
import itsix.bank_deposit.logic.impl.Date;

import java.time.LocalDateTime;

/**
 * Created by Geo on 24.08.2016.
 */
public class DateBuilder implements IDateBuilder {

    @Override
    public IDate buildCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();

        return new Date(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getDayOfMonth(), localDateTime.getMonth(), localDateTime.getYear());
    }
}
