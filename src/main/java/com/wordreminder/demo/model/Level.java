package com.wordreminder.demo.model;

public enum Level {
    /**
     * level ONE : 30min
     */
    ONE(1800),
    /**
     * level TWO : 12h
     */
    TWO(43200),
    /**
     * level THREE : 24h
     */
    THREE(86400),
    /**
     * level FOUR : 2d
     */
    FOUR(86400*2),
    /**
     * level FIVE : 4d
     */
    FIVE(86400*4),
    /**
     * level SIX : 7d
     */
    SIX(86400*7),
    /**
     * level SEVEN : 15d
     */
    SEVEN(86400*15);

    public int delaySecond;
    Level (int delaySecond) {
        this.delaySecond = delaySecond;
    }
}
