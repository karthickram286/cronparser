package com.deliveroo.models;

public enum CronField {

    MINUTES(0, 59),
    HOURS(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(0, 6);

    public final int min;
    public final int max;


    CronField(int min, int max) {
        this.min = min;
        this.max = max;
    }
}