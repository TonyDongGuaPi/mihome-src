package com.facebook.yoga;

public enum YogaLayoutType {
    LAYOUT(0),
    MEASURE(1),
    CACHED_LAYOUT(2),
    CACHED_MEASURE(3);
    
    private final int mIntValue;

    private YogaLayoutType(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaLayoutType fromInt(int i) {
        switch (i) {
            case 0:
                return LAYOUT;
            case 1:
                return MEASURE;
            case 2:
                return CACHED_LAYOUT;
            case 3:
                return CACHED_MEASURE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }
}
