package com.deliveroo;

import com.deliveroo.exceptions.InvalidCronStringException;
import com.deliveroo.parser.CronExpressionParser;

public class CronParser {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Error: Cron string is the only required parameter");
        }

        String cronString = args[0];

        try {
            CronExpressionParser cronExpressionParser = new CronExpressionParser(cronString);
            cronExpressionParser.parse();
            System.out.println(cronExpressionParser);
        } catch (InvalidCronStringException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}