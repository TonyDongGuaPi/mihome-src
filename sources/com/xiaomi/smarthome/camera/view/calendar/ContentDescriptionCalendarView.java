package com.xiaomi.smarthome.camera.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ContentDescriptionCalendarView extends RelativeLayout {
    public CalendarDate date;

    public ContentDescriptionCalendarView(Context context) {
        super(context);
    }

    public ContentDescriptionCalendarView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ContentDescriptionCalendarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @SuppressLint({"GetContentDescriptionOverride"})
    public CharSequence getContentDescription() {
        return this.date.getDateFormat("yyyy月M日d");
    }
}
