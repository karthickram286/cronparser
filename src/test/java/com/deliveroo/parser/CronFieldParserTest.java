package com.deliveroo.parser;

import com.deliveroo.data.parser.CronFieldTestData;
import com.deliveroo.exceptions.InvalidCronFieldException;
import com.deliveroo.exceptions.InvalidCronStringException;
import com.deliveroo.models.CronField;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CronFieldParserTest {

    private static Stream<Arguments> provideListValuesTestCases() {
        return CronFieldTestData.LIST_VALUE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideListValuesWithAltSingleValuesTestCases() {
        return CronFieldTestData.LIST_VALUE_WITH_ALT_SINGLE_VALUES_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideStepValueTestCases() {
        return CronFieldTestData.STEP_VALUE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideStepValueWithAltSingleValuesTestCases() {
        return CronFieldTestData.STEP_VALUE_WITH_ALT_SINGLE_VALUE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideRangeTestCases() {
        return CronFieldTestData.RANGE_VALUE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideRangeWithAltSingleValuesTestCases() {
        return CronFieldTestData.RANGE_VALUE_WITH_ALT_SINGLE_VALUE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.result));
    }

    private static Stream<Arguments> provideFailureTestCases() {
        return CronFieldTestData.FAILURE_TEST_CASES
                .stream()
                .map(testCase -> Arguments.of(testCase.cronFieldString, testCase.cronField, testCase.expectedErrorMessage));
    }

    @ParameterizedTest
    @MethodSource("provideListValuesTestCases")
    public void should_parse_list_values(String cronFieldString, CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideListValuesWithAltSingleValuesTestCases")
    public void should_parse_list_values_with_alternative_single_values(String cronFieldString,
                                                                        CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideStepValueTestCases")
    public void should_parse_step_values(String cronFieldString, CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideStepValueWithAltSingleValuesTestCases")
    public void should_parse_step_values_with_alternative_single_values(String cronFieldString,
                                                                        CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideRangeTestCases")
    public void should_parse_range_values(String cronFieldString, CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideRangeWithAltSingleValuesTestCases")
    public void should_parse_range_values_with_alternative_single_values(String cronFieldString,
                                                                         CronField cronField, String expected)
            throws InvalidCronFieldException {
        assertEquals(expected, new CronFieldParser(cronFieldString, cronField).parseField());
    }

    @ParameterizedTest
    @MethodSource("provideFailureTestCases")
    public void should_throw_exception(String cronFieldString, CronField cronField, String expectedErrorMessage) {
        Throwable exception = assertThrows(InvalidCronFieldException.class,
                () -> new CronFieldParser(cronFieldString, cronField).parseField());
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}