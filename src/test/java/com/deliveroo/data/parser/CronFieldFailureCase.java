package com.deliveroo.data.parser;

import com.deliveroo.models.CronField;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CronFieldFailureCase {
    public String cronFieldString;
    public CronField cronField;
    public String expectedErrorMessage;
}
