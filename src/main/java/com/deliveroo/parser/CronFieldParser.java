package com.deliveroo.parser;

import com.deliveroo.exceptions.InvalidCronFieldException;
import com.deliveroo.models.CronField;

import static com.deliveroo.util.CronFieldParserUtil.*;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CronFieldParser {

    private static final String LIST_SEPARATOR = ",";
    private static final String ANY_VALUE = "*";
    private static final String RANGE_SEPARATOR = "-";
    private static final String STEP_VALUE_SEPARATOR = "/";

    private final String fieldString;
    private final CronField cronField;
    private final Set<Integer> cronValues;

    public CronFieldParser(String fieldString, CronField cronField) {
        this.fieldString = fieldString;
        this.cronField = cronField;
        this.cronValues = new TreeSet<>();
    }

    public String parseField() throws InvalidCronFieldException {
        parseListValues(fieldString);
        return cronValues
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    /**
     * Parses the cron string and separates the values with delimiter "," to parse further
     *
     * @param fieldString
     * @throws InvalidCronFieldException
     */
    private void parseListValues(String fieldString) throws InvalidCronFieldException {
        String[] listValues = fieldString.split(LIST_SEPARATOR);

        for (String value : listValues) {
            if (value.equals(ANY_VALUE)) {
                fillCronValues(cronField.min, cronField.max, 1);
            } else if (isNumber(value) && Integer.parseInt(value) >= cronField.min && Integer.parseInt(value) <= cronField.max) {
                cronValues.add(Integer.parseInt(value));
            } else if (isValidAlternativeSingleValue(cronField, value)) {
                cronValues.add(retrieveAlternativeSingleValue(cronField, value));
            } else if (value.contains(STEP_VALUE_SEPARATOR)) {
                parseStepValues(value);
            } else if (value.contains(RANGE_SEPARATOR)) {
                parseRangeValues(value);
            } else {
                throw new InvalidCronFieldException("Invalid cron field string");
            }
        }
    }

    /**
     * Parses the cron strings which has range values "-".
     *
     * @param value
     * @throws InvalidCronFieldException
     */
    private void parseRangeValues(String value) throws InvalidCronFieldException {
        String[] rangeValues = value.split(RANGE_SEPARATOR);

        if (rangeValues.length != 2) {
            throw new InvalidCronFieldException("Only two values are expected for range values");
        }

        String startValue = rangeValues[0];
        String endValue = rangeValues[1];
        boolean isStartAlternativeSingleValue = isValidAlternativeSingleValue(cronField, startValue);
        boolean isEndAlternativeSingleValue = isValidAlternativeSingleValue(cronField, endValue);

        validateRangeValues(startValue, endValue, isStartAlternativeSingleValue, isEndAlternativeSingleValue);

        if (isStartAlternativeSingleValue) {
            startValue = String.valueOf(retrieveAlternativeSingleValue(cronField, startValue));
        }
        if (isEndAlternativeSingleValue) {
            endValue = String.valueOf(retrieveAlternativeSingleValue(cronField, endValue));
        }

        fillCronValues(Integer.parseInt(startValue), Integer.parseInt(endValue), 1);
    }

    private void validateRangeValues(String startValue, String endValue, boolean isStartAlternativeSingleValue, boolean isEndAlternativeSingleValue) throws InvalidCronFieldException {
        if ((!isNumber(startValue) && !isStartAlternativeSingleValue) || (!isNumber(endValue) && !isEndAlternativeSingleValue)) {
            throw new InvalidCronFieldException("Start value and end value should be numbers or alternative single values");
        }
    }

    /**
     * Parses the cron strings with step values "/"
     *
     * @param value
     * @throws InvalidCronFieldException
     */
    private void parseStepValues(String value) throws InvalidCronFieldException {
        String[] stepValues = value.split(STEP_VALUE_SEPARATOR);

        if (stepValues.length != 2) {
            throw new InvalidCronFieldException("Only two values are expected for step values");
        }

        String startValue = stepValues[0];
        String stepValue = stepValues[1];
        boolean isStartAlternativeSingleValue = isValidAlternativeSingleValue(cronField, startValue);

        validateStepValues(startValue, stepValue, isStartAlternativeSingleValue);

        if (isStartAlternativeSingleValue) {
            startValue = String.valueOf(retrieveAlternativeSingleValue(cronField, startValue));
        }

        if (startValue.equals(ANY_VALUE)) {
            fillCronValues(cronField.min, cronField.max, Integer.parseInt(stepValue));
        } else {
            fillCronValues(Integer.parseInt(startValue), cronField.max, Integer.parseInt(stepValue));
        }
    }

    private void validateStepValues(String startValue, String stepValue, boolean isStartAlternativeSingleValue) throws InvalidCronFieldException {

        if (!isNumber(stepValue)) {
            throw new InvalidCronFieldException("Step value is expected to be a number");
        }

        if (!isNumber(startValue) && !startValue.equals(ANY_VALUE) && !isStartAlternativeSingleValue) {
            throw new InvalidCronFieldException("Start value for step values should either be *, a number or a valid alternative single value");
        }

    }

    /**
     * Fills the Set with the range and the step provided
     *
     * @param start
     * @param end
     * @param step
     * @throws InvalidCronFieldException
     */
    private void fillCronValues(int start, int end, int step) throws InvalidCronFieldException {

        if (step == 0) {
            throw new InvalidCronFieldException("Step number should be greater than 0");
        }

        if (start < cronField.min || end > cronField.max) {
            throw new InvalidCronFieldException("Interval is out of range");
        }

        if (end <= start) {
            throw new InvalidCronFieldException("End should be greater than start value");
        }

        for (int i = start; i <= end; i += step) {
            cronValues.add(i);
        }
    }
}
