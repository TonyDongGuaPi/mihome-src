package com.xiaomi.smarthome.camera.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import java.util.Formatter;
import java.util.Locale;

public class HourNumberPicker extends NumberPicker {
    public static final NumberPicker.Formatter HOUR_MINUTE_DIGIT_FORMATTER = new NumberPicker.Formatter() {
        final Object[] mArgs = new Object[1];
        final StringBuilder mBuilder = new StringBuilder();
        final StringBuilder mBuilder1 = new StringBuilder();
        final Formatter mFmt = new Formatter(this.mBuilder, Locale.US);
        final Formatter mFmt1 = new Formatter(this.mBuilder1, Locale.US);

        public String format(int i) {
            this.mBuilder.delete(0, this.mBuilder.length());
            this.mBuilder1.delete(0, this.mBuilder1.length());
            this.mFmt.format("%02d", new Object[]{Integer.valueOf(i / 6)});
            this.mFmt1.format("%02d", new Object[]{Integer.valueOf((i % 6) * 10)});
            return this.mFmt.toString() + ":" + this.mFmt1.toString();
        }
    };
    public static final int INTERVAL = 10;

    public HourNumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public HourNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HourNumberPicker(Context context) {
        super(context);
    }
}
