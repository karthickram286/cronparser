package com.deliveroo.data.parser;

import com.deliveroo.models.CronField;
import com.deliveroo.testutil.CronFieldFailureCase;
import com.deliveroo.testutil.CronFieldSuccessCase;

import java.util.List;

public class CronFieldTestData {

    /**
     * Success test cases
     */

    public static final List<CronFieldSuccessCase> LIST_VALUE_TEST_CASES = List.of(
            new CronFieldSuccessCase("5,8,14,17", CronField.MINUTES, "5 8 14 17"),
            new CronFieldSuccessCase("3,59", CronField.MINUTES, "3 59"),
            new CronFieldSuccessCase("8,9,13", CronField.HOURS, "8 9 13"),
            new CronFieldSuccessCase("1,*", CronField.HOURS,"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23"),
            new CronFieldSuccessCase("5,8,27,19", CronField.DAY_OF_MONTH, "5 8 19 27"),
            new CronFieldSuccessCase("3,8,7,12", CronField.MONTH, "3 7 8 12"),
            new CronFieldSuccessCase("3,4,0", CronField.DAY_OF_WEEK, "0 3 4"),
            new CronFieldSuccessCase("1,4,*", CronField.MONTH, "1 2 3 4 5 6 7 8 9 10 11 12")
    );

    public static final List<CronFieldSuccessCase> LIST_VALUE_WITH_ALT_SINGLE_VALUES_TEST_CASES = List.of(
            new CronFieldSuccessCase("3,SEP,7,DEC", CronField.MONTH, "3 7 9 12"),
            new CronFieldSuccessCase("0,SUN",CronField.DAY_OF_WEEK,"0"),
            new CronFieldSuccessCase("OCT,*", CronField.MONTH, "1 2 3 4 5 6 7 8 9 10 11 12"),
            new CronFieldSuccessCase("AUG,3,OCT,1", CronField.MONTH, "1 3 8 10"),
            new CronFieldSuccessCase("WED,4,SUN", CronField.DAY_OF_WEEK, "0 3 4"),
            new CronFieldSuccessCase("4,SUN,1,FRI", CronField.DAY_OF_WEEK, "0 1 4 5"),
            new CronFieldSuccessCase("7,*,OCT", CronField.MONTH, "1 2 3 4 5 6 7 8 9 10 11 12")
    );

    public static final List<CronFieldSuccessCase> STEP_VALUE_TEST_CASES = List.of(
            new CronFieldSuccessCase("10/8", CronField.MINUTES, "10 18 26 34 42 50 58"),
            new CronFieldSuccessCase("23/7,10/6", CronField.MINUTES, "10 16 22 23 28 30 34 37 40 44 46 51 52 58"),
            new CronFieldSuccessCase("16/3", CronField.HOURS, "16 19 22"),
            new CronFieldSuccessCase("3/8,9/4,7/8", CronField.HOURS, "3 7 9 11 13 15 17 19 21 23"),
            new CronFieldSuccessCase("5/6", CronField.DAY_OF_MONTH, "5 11 17 23 29"),
            new CronFieldSuccessCase("5/6,8/5", CronField.DAY_OF_MONTH, "5 8 11 13 17 18 23 28 29"),
            new CronFieldSuccessCase("3/4", CronField.MONTH, "3 7 11"),
            new CronFieldSuccessCase("3/4,2/3", CronField.MONTH, "2 3 5 7 8 11"),
            new CronFieldSuccessCase("*/4", CronField.MONTH, "1 5 9"),
            new CronFieldSuccessCase("1/3", CronField.DAY_OF_WEEK, "1 4"),
            new CronFieldSuccessCase("0/2,3/3", CronField.DAY_OF_WEEK, "0 2 3 4 6")
    );

    public static final List<CronFieldSuccessCase> STEP_VALUE_WITH_ALT_SINGLE_VALUE_TEST_CASES = List.of(
            new CronFieldSuccessCase("AUG/2", CronField.MONTH, "8 10 12"),
            new CronFieldSuccessCase("MAR/3,APR/4", CronField.MONTH, "3 4 6 8 9 12"),
            new CronFieldSuccessCase("SUN/3", CronField.DAY_OF_WEEK, "0 3 6"),
            new CronFieldSuccessCase("SUN/1",CronField.DAY_OF_WEEK,"0 1 2 3 4 5 6"),
            new CronFieldSuccessCase("SUN/3,MON/4", CronField.DAY_OF_WEEK, "0 1 3 5 6")
    );

    public static final List<CronFieldSuccessCase> RANGE_VALUE_TEST_CASES = List.of(
            new CronFieldSuccessCase("10-16", CronField.MINUTES, "10 11 12 13 14 15 16"),
            new CronFieldSuccessCase("10-16,30-34", CronField.MINUTES, "10 11 12 13 14 15 16 30 31 32 33 34"),
            new CronFieldSuccessCase("7-10", CronField.HOURS, "7 8 9 10"),
            new CronFieldSuccessCase("3-8,7-11", CronField.HOURS, "3 4 5 6 7 8 9 10 11"),
            new CronFieldSuccessCase("5-13", CronField.DAY_OF_MONTH, "5 6 7 8 9 10 11 12 13"),
            new CronFieldSuccessCase("5-8,17-23", CronField.DAY_OF_MONTH, "5 6 7 8 17 18 19 20 21 22 23"),
            new CronFieldSuccessCase("3-6", CronField.MONTH, "3 4 5 6"),
            new CronFieldSuccessCase("*", CronField.MONTH, "1 2 3 4 5 6 7 8 9 10 11 12"),
            new CronFieldSuccessCase("2-4,8-11", CronField.MONTH, "2 3 4 8 9 10 11"),
            new CronFieldSuccessCase("1-3", CronField.DAY_OF_WEEK, "1 2 3"),
            new CronFieldSuccessCase("*", CronField.DAY_OF_WEEK, "0 1 2 3 4 5 6"),
            new CronFieldSuccessCase("0-2,4-5", CronField.DAY_OF_WEEK, "0 1 2 4 5")
    );

    public static final List<CronFieldSuccessCase> RANGE_VALUE_WITH_ALT_SINGLE_VALUE_TEST_CASES = List.of(
            new CronFieldSuccessCase("MAR-7", CronField.MONTH, "3 4 5 6 7"),
            new CronFieldSuccessCase("MAR-MAY", CronField.MONTH, "3 4 5"),
            new CronFieldSuccessCase("MAR-7,2-JUN", CronField.MONTH, "2 3 4 5 6 7"),
            new CronFieldSuccessCase("MAR-MAY,SEP-NOV", CronField.MONTH, "3 4 5 9 10 11"),
            new CronFieldSuccessCase("SUN-MON,FRI-6",CronField.DAY_OF_WEEK, "0 1 5 6"),
            new CronFieldSuccessCase("SUN-1,TUE/2",CronField.DAY_OF_WEEK, "0 1 2 4 6")
    );

    /**
     * Failure test cases
     */

    public static final List<CronFieldFailureCase> FAILURE_TEST_CASES = List.of(
            new CronFieldFailureCase("4 2", CronField.MINUTES, "Invalid cron field string"),
            new CronFieldFailureCase("60,*",CronField.MINUTES,"Invalid cron field string"),
            new CronFieldFailureCase("7,19",CronField.MONTH,"Invalid cron field string"),
            new CronFieldFailureCase("7/2,60",CronField.HOURS,"Invalid cron field string"),
            new CronFieldFailureCase("7",CronField.DAY_OF_WEEK,"Invalid cron field string"),
            new CronFieldFailureCase("7,32",CronField.DAY_OF_MONTH,"Invalid cron field string"),
            new CronFieldFailureCase("deliveroo", CronField.MINUTES, "Invalid cron field string"),
            new CronFieldFailureCase("3-4-2", CronField.MINUTES, "Only two values are expected for range values"),
            new CronFieldFailureCase("aul-10", CronField.MONTH, "Start value and end value should be numbers or alternative single values"),
            new CronFieldFailureCase("4/*/2", CronField.MINUTES, "Only two values are expected for step values"),
            new CronFieldFailureCase("2/*", CronField.MINUTES, "Step value is expected to be a number"),
            new CronFieldFailureCase("january/4", CronField.MONTH, "Start value for step values should either be *, a number or a valid alternative single value"),
            new CronFieldFailureCase("2/0", CronField.MONTH, "Step number should be greater than 0"),
            new CronFieldFailureCase("7-13", CronField.MONTH, "Interval is out of range"),
            new CronFieldFailureCase("6-3", CronField.MONTH, "End should be greater than start value")
    );
}
