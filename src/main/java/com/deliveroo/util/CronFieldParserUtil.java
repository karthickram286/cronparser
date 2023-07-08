package com.deliveroo.util;

import com.deliveroo.exceptions.InvalidCronFieldException;
import com.deliveroo.models.CronField;
import com.deliveroo.models.DayOfWeek;
import com.deliveroo.models.Month;

import java.util.stream.Stream;

public class CronFieldParserUtil {

    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidAlternativeSingleValue(CronField cronField, String value) {
        if (cronField.equals(CronField.MONTH)) {
            return Stream.of(Month.values()).anyMatch(month -> month.name().equals(value));
        } else if (cronField.equals(CronField.DAY_OF_WEEK)) {
            return Stream.of(DayOfWeek.values()).anyMatch(dayOfWeek -> dayOfWeek.name().equals(value));
        }
        return false;
    }

    public static int retrieveAlternativeSingleValue(CronField cronField, String value) throws InvalidCronFieldException {
        try {
            if (cronField.equals(CronField.MONTH)) {
                return Month.valueOf(value).monthValue;
            } else if (cronField.equals(CronField.DAY_OF_WEEK)) {
                return DayOfWeek.valueOf(value).dayOfWeekValue;
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCronFieldException(String.format("Invalid alternative single value %s", value));
        }
        throw new InvalidCronFieldException(String.format("Invalid alternative single value %s", value));
    }
}
