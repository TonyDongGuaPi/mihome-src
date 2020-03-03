package com.xiaomi.mishopsdk.widget.wheel.adapters;

import android.content.Context;

public class NumericWheelAdapter extends AbstractWheelTextAdapter {
    public static final int DEFAULT_MAX_VALUE = 9;
    private static final int DEFAULT_MIN_VALUE = 0;
    private String format;
    private int maxValue;
    private int minValue;

    public NumericWheelAdapter(Context context) {
        this(context, 0, 9);
    }

    public NumericWheelAdapter(Context context, int i, int i2) {
        this(context, i, i2, (String) null);
    }

    public NumericWheelAdapter(Context context, int i, int i2, String str) {
        super(context);
        this.minValue = i;
        this.maxValue = i2;
        this.format = str;
    }

    public CharSequence getItemText(int i) {
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        int i2 = this.minValue + i;
        if (this.format == null) {
            return Integer.toString(i2);
        }
        return String.format(this.format, new Object[]{Integer.valueOf(i2)});
    }

    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }
}
