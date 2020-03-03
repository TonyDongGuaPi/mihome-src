package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.TimePicker;

public class TimerPickerHourToHour extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private TimePicker f18965a;
    private TimePicker b;
    private TextView c;
    /* access modifiers changed from: private */
    public int d;
    /* access modifiers changed from: private */
    public int e;

    public TimerPickerHourToHour(Context context, int i, int i2) {
        super(context);
        this.d = i;
        this.e = i2;
        a();
    }

    public TimerPickerHourToHour(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TimerPickerHourToHour(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.timer_picker_hour_to_hour, this);
        this.c = (TextView) findViewById(R.id.hint);
        this.f18965a = (TimePicker) findViewById(R.id.timer_from);
        this.f18965a.setAmPmSpinnerVisibility(8);
        this.f18965a.setMinuteSpinnerVisibility(8);
        this.f18965a.setIs24HourView(true);
        this.f18965a.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                int unused = TimerPickerHourToHour.this.d = i;
                TimerPickerHourToHour.this.b();
            }
        });
        this.b = (TimePicker) findViewById(R.id.timer_to);
        this.b.setAmPmSpinnerVisibility(8);
        this.b.setMinuteSpinnerVisibility(8);
        this.b.setIs24HourView(true);
        this.b.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                int unused = TimerPickerHourToHour.this.e = i;
                TimerPickerHourToHour.this.b();
            }
        });
        this.b.setCurrentHour(Integer.valueOf(this.e));
        this.f18965a.setCurrentHour(Integer.valueOf(this.d));
    }

    public int[] getHourFromTo() {
        return new int[]{this.d, this.e};
    }

    /* access modifiers changed from: private */
    public void b() {
        this.c.setVisibility(0);
        TextView textView = this.c;
        textView.setText(this.d + ":00~" + this.e + ":00");
    }
}
