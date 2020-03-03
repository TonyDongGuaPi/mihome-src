package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class TimePicker extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f18958a = true;
    private static final int b = 12;
    private static final OnTimeChangedListener c = new OnTimeChangedListener() {
        public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        }
    };
    private boolean d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public final NumberPicker f;
    /* access modifiers changed from: private */
    public final NumberPicker g;
    private final NumberPicker h;
    private final Button i;
    private final String[] j;
    private boolean k;
    private OnTimeChangedListener l;
    private Calendar m;
    private Locale n;

    public interface OnTimeChangedListener {
        void onTimeChanged(TimePicker timePicker, int i, int i2);
    }

    /* access modifiers changed from: package-private */
    public void callOnTimeChanged() {
        c();
    }

    class OnMinuteChangeListener implements NumberPicker.OnValueChangeListener {
        OnMinuteChangeListener() {
        }

        public void onValueChange(NumberPicker numberPicker, int i, int i2) {
            TimePicker.this.callOnTimeChanged();
        }
    }

    public TimePicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = true;
        setCurrentLocale(Locale.getDefault());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.time_picker, this, true);
        this.f = (NumberPicker) findViewById(R.id.hour);
        this.f.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                if (!TimePicker.this.is24HourView() && ((i == 11 && i2 == 12) || (i == 12 && i2 == 11))) {
                    boolean unused = TimePicker.this.e = !TimePicker.this.e;
                    TimePicker.this.b();
                }
                TimePicker.this.c();
            }
        });
        this.g = (NumberPicker) findViewById(R.id.minute);
        this.g.setMinValue(0);
        this.g.setMaxValue(59);
        this.g.setOnLongPressUpdateInterval(100);
        this.g.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        this.g.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int minValue = TimePicker.this.g.getMinValue();
                int maxValue = TimePicker.this.g.getMaxValue();
                if (i == maxValue && i2 == minValue) {
                    int value = TimePicker.this.f.getValue() + 1;
                    if (!TimePicker.this.is24HourView() && value == 12) {
                        boolean unused = TimePicker.this.e = !TimePicker.this.e;
                        TimePicker.this.b();
                    }
                    TimePicker.this.f.setValue(value);
                } else if (i == minValue && i2 == maxValue) {
                    int value2 = TimePicker.this.f.getValue() - 1;
                    if (!TimePicker.this.is24HourView() && value2 == 11) {
                        boolean unused2 = TimePicker.this.e = !TimePicker.this.e;
                        TimePicker.this.b();
                    }
                    TimePicker.this.f.setValue(value2);
                }
                TimePicker.this.c();
            }
        });
        this.g.setOnValueChangedListener(new OnMinuteChangeListener());
        this.j = new DateFormatSymbols().getAmPmStrings();
        View findViewById = findViewById(R.id.amPm);
        if (findViewById instanceof Button) {
            this.h = null;
            this.i = (Button) findViewById;
            this.i.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    view.requestFocus();
                    boolean unused = TimePicker.this.e = !TimePicker.this.e;
                    TimePicker.this.b();
                    TimePicker.this.c();
                }
            });
        } else {
            this.i = null;
            this.h = (NumberPicker) findViewById;
            this.h.setMinValue(0);
            this.h.setMaxValue(1);
            this.h.setDisplayedValues(this.j);
            this.h.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                    numberPicker.requestFocus();
                    boolean unused = TimePicker.this.e = !TimePicker.this.e;
                    TimePicker.this.b();
                    TimePicker.this.c();
                }
            });
        }
        a();
        b();
        setOnTimeChangedListener(c);
        setCurrentHour(Integer.valueOf(this.m.get(11)));
        setCurrentMinute(Integer.valueOf(this.m.get(12)));
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public void setEnabled(boolean z) {
        if (this.k != z) {
            super.setEnabled(z);
            this.g.setEnabled(z);
            this.f.setEnabled(z);
            if (this.h != null) {
                this.h.setEnabled(z);
            } else {
                this.i.setEnabled(z);
            }
            this.k = z;
        }
    }

    public boolean isEnabled() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.n)) {
            this.n = locale;
            this.m = Calendar.getInstance(locale);
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final int f18964a;
        private final int b;

        private SavedState(Parcelable parcelable, int i, int i2) {
            super(parcelable);
            this.f18964a = i;
            this.b = i2;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f18964a = parcel.readInt();
            this.b = parcel.readInt();
        }

        public int a() {
            return this.f18964a;
        }

        public int b() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f18964a);
            parcel.writeInt(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getCurrentHour().intValue(), getCurrentMinute().intValue());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentHour(Integer.valueOf(savedState.a()));
        setCurrentMinute(Integer.valueOf(savedState.b()));
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.l = onTimeChangedListener;
    }

    public Integer getCurrentHour() {
        int value = this.f.getValue();
        if (is24HourView()) {
            return Integer.valueOf(value);
        }
        if (this.e) {
            return Integer.valueOf(value % 12);
        }
        return Integer.valueOf((value % 12) + 12);
    }

    public void setCurrentHour(Integer num) {
        if (num != null && num != getCurrentHour()) {
            if (!is24HourView()) {
                if (num.intValue() >= 12) {
                    this.e = false;
                    if (num.intValue() > 12) {
                        num = Integer.valueOf(num.intValue() - 12);
                    }
                } else {
                    this.e = true;
                    if (num.intValue() == 0) {
                        num = 12;
                    }
                }
                b();
            }
            this.f.setValue(num.intValue());
            c();
        }
    }

    public void setIs24HourView(Boolean bool) {
        if (this.d != bool.booleanValue()) {
            this.d = bool.booleanValue();
            int intValue = getCurrentHour().intValue();
            a();
            setCurrentHour(Integer.valueOf(intValue));
            b();
        }
    }

    public boolean is24HourView() {
        return this.d;
    }

    public Integer getCurrentMinute() {
        return Integer.valueOf(this.g.getValue());
    }

    public void setCurrentMinute(Integer num) {
        if (num != getCurrentMinute()) {
            this.g.setValue(num.intValue());
            c();
        }
    }

    public int getBaseline() {
        return this.f.getBaseline();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        int i2 = this.d ? 129 : 65;
        this.m.set(11, getCurrentHour().intValue());
        this.m.set(12, getCurrentMinute().intValue());
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.m.getTimeInMillis(), i2));
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TimePicker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TimePicker.class.getName());
    }

    public void setHourMinutesStrings(String[] strArr, String[] strArr2) {
        this.f.setMinValue(0);
        this.f.setMaxValue(strArr.length - 1);
        this.f.setDisplayedValues(strArr);
        this.g.setMinValue(0);
        this.g.setMaxValue(strArr2.length - 1);
        this.g.setDisplayedValues(strArr2);
    }

    private void a() {
        if (is24HourView()) {
            this.f.setMinValue(0);
            this.f.setMaxValue(23);
            this.f.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
            return;
        }
        this.f.setMinValue(1);
        this.f.setMaxValue(12);
        this.f.setFormatter((NumberPicker.Formatter) null);
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!is24HourView()) {
            int i2 = !this.e;
            if (this.h != null) {
                this.h.setValue(i2);
                this.h.setVisibility(0);
            } else {
                this.i.setText(this.j[i2]);
                this.i.setVisibility(0);
            }
        } else if (this.h != null) {
            this.h.setVisibility(8);
        } else {
            this.i.setVisibility(8);
        }
        sendAccessibilityEvent(4);
    }

    public void setAmPmSpinnerVisibility(int i2) {
        if (this.h != null) {
            this.h.setVisibility(i2);
        }
    }

    public void setMinuteSpinnerVisibility(int i2) {
        if (this.g != null) {
            this.g.setVisibility(i2);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        sendAccessibilityEvent(4);
        if (this.l != null) {
            this.l.onTimeChanged(this, getCurrentHour().intValue(), getCurrentMinute().intValue());
        }
    }

    private void a(View view, int i2, int i3) {
        View findViewById = view.findViewById(i2);
        if (findViewById != null) {
            findViewById.setContentDescription(getContext().getString(i3));
        }
    }
}
