package com.gcasey.spotfinder.features.songs;

public enum Popularity {
    LOW(0, 33),
    MEDIUM(33, 66),
    HIGH(66, 100);

    private final int min;
    private final int max;

    Popularity(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
