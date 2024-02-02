package com.javarush.domain;

public enum Status {
    IN_PROGRESS(0),
    DONE(1),
    PAUSED(2);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}