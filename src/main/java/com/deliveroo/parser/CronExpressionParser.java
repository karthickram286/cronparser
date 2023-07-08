package com.deliveroo.parser;

import com.deliveroo.exceptions.InvalidCronFieldException;
import com.deliveroo.exceptions.InvalidCronStringException;
import com.deliveroo.models.CronField;

public class CronExpressionParser {

    private final int CRON_STRING_COUNT = 6;
    private final String cronString;

    private String minutes;
    private String hours;
    private String daysOfMonth;
    private String months;
    private String daysOfWeek;
    private String command;


    public CronExpressionParser(String cronString) {
        this.cronString = cronString;
    }

    public void parse() throws InvalidCronStringException {
        String[] cronFields = cronString.split(" ");

        if (cronFields.length != CRON_STRING_COUNT) {
            throw new InvalidCronStringException(
                    String.format("Cron string should have exactly {%s} values", CRON_STRING_COUNT)
            );
        }

        try {
            minutes = new CronFieldParser(cronFields[0], CronField.MINUTES).parseField();
            hours = new CronFieldParser(cronFields[1], CronField.HOURS).parseField();
            daysOfMonth = new CronFieldParser(cronFields[2], CronField.DAY_OF_MONTH).parseField();
            months = new CronFieldParser(cronFields[3], CronField.MONTH).parseField();
            daysOfWeek = new CronFieldParser(cronFields[4], CronField.DAY_OF_WEEK).parseField();
            command = cronFields[5];
        } catch (InvalidCronFieldException e) {
            throw new InvalidCronStringException(e.getMessage());
        }
    }

    public String toString() {
        String builder;
        builder = String.format("minute        %s\n", minutes) +
                  String.format("hours         %s\n", hours) +
                  String.format("day of month  %s\n", daysOfMonth) +
                  String.format("month         %s\n", months) +
                  String.format("day of week   %s\n", daysOfWeek) +
                  String.format("command       %s\n", command);
        return builder;
    }
}
