package com.deliveroo.parser;

import com.deliveroo.exceptions.InvalidCronStringException;
import org.junit.jupiter.api.Test;

class CronParserTest {

    @Test
    public void test1() throws InvalidCronStringException {
        CronExpressionParser cronExpressionParser = new CronExpressionParser("0-5,8,14/13,*/17 * 31 2 * /sh");
        cronExpressionParser.parse();
        System.out.println(cronExpressionParser);
    }

    @Test
    public void test2() throws InvalidCronStringException {
        CronExpressionParser cronExpressionParser = new CronExpressionParser("*/15 0 1,15,23-28 * 1-5 /usr/bin/find");
        cronExpressionParser.parse();
        System.out.println(cronExpressionParser);
    }

    @Test
    public void test3() throws InvalidCronStringException {
        CronExpressionParser cronExpressionParser = new CronExpressionParser("*/3,3/4 * 31 3,SEP * /usr/bin/find && upgrade.sh");
        cronExpressionParser.parse();
        System.out.println(cronExpressionParser);
    }


}