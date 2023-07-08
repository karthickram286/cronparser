package com.deliveroo.models;

public enum DayOfWeek {

    SUN(0),
    MON(1),
    TUE(2),
    WED(3),
    THU(4),
    FRI(5),
    SAT(6);

    public final int dayOfWeekValue;

    DayOfWeek(int dayOfWeekValue) {
        this.dayOfWeekValue = dayOfWeekValue;
    }
}
