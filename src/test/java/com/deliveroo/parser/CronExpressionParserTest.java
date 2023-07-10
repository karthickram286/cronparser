package com.deliveroo.parser;

import com.deliveroo.exceptions.InvalidCronStringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronExpressionParserTest {

    @Test
    public void should_throw_invalid_cron_string_exception() {
        CronExpressionParser expressionParser = new CronExpressionParser("3 * * 3 1");
        Throwable exception = assertThrows(InvalidCronStringException.class, expressionParser::parse);
        assertEquals("Cron string should be of format [minute] [hour] [day of month] [month] [day of week] [command]", exception.getMessage());
    }

    @Test
    public void should_not_throw_exception_for_valid_string() {
        CronExpressionParser expressionParser = new CronExpressionParser("3 * * 3 1 /usr/bin/find");
        assertDoesNotThrow(expressionParser::parse);
        assertEquals("""
                minute        3
                hours         0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
                day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month         3
                day of week   1
                command       /usr/bin/find
                """, expressionParser.toString());
    }

    @Test
    public void should_not_throw_exception_for_string_with_extra_spaces() {
        CronExpressionParser expressionParser = new CronExpressionParser(" 3 *  * 3  1 /usr/bin/find ");
        assertDoesNotThrow(expressionParser::parse);
        assertEquals("""
                minute        3
                hours         0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
                day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month         3
                day of week   1
                command       /usr/bin/find
                """, expressionParser.toString());
    }

    @Test
    public void should_join_commands_which_has_spaces() {
        CronExpressionParser expressionParser = new CronExpressionParser("3 * * 3 1 /usr/bin/find && ./upgrade.sh");
        assertDoesNotThrow(expressionParser::parse);
        assertEquals("""
                minute        3
                hours         0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
                day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month         3
                day of week   1
                command       /usr/bin/find && ./upgrade.sh
                """, expressionParser.toString());
    }
}