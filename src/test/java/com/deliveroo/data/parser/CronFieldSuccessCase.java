package com.deliveroo.data.parser;

import com.deliveroo.models.CronField;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CronFieldSuccessCase {
    public String cronFieldString;
    public CronField cronField;
    public String result;
}
