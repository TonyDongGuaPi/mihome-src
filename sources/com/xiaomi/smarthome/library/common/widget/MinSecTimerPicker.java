package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import java.util.Calendar;
import java.util.Locale;

public class MinSecTimerPicker extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f18891a = true;
    private static final int b = 12;
    private static final OnTimeChangedListener c = new OnTimeChangedListener() {
        public void a(MinSecTimerPicker minSecTimerPicker, int i, int i2) {
        }
    };
    private final NumberPicker d;
    private final NumberPicker e;
    private boolean f;
    private OnTimeChangedListener g;
    private Calendar h;
    private Locale i;

    public interface OnTimeChangedListener {
        void a(MinSecTimerPicker minSecTimerPicker, int i, int i2);
    }

    /* access modifiers changed from: package-private */
    public void callOnTimeChanged() {
        b();
    }

    class OnMinuteChangeListener implements NumberPicker.OnValueChangeListener {
        OnMinuteChangeListener() {
        }

        public void onValueChange(NumberPicker numberPicker, int i, int i2) {
            MinSecTimerPicker.this.callOnTimeChanged();
        }
    }

    public MinSecTimerPicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public MinSecTimerPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MinSecTimerPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = true;
        setCurrentLocale(Locale.getDefault());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.min_sec_timer_picker, this, true);
        this.d = (NumberPicker) findViewById(R.id.min);
        this.d.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                MinSecTimerPicker.this.b();
            }
        });
        this.e = (NumberPicker) findViewById(R.id.sec);
        this.e.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                MinSecTimerPicker.this.b();
            }
        });
        a();
        setOnTimeChangedListener(c);
        setCurrentMin(0);
        setCurrentSec(0);
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public void setEnabled(boolean z) {
        if (this.f != z) {
            super.setEnabled(z);
            this.d.setEnabled(z);
            this.e.setEnabled(z);
            this.f = z;
        }
    }

    public boolean isEnabled() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.i)) {
            this.i = locale;
            this.h = Calendar.getInstance(locale);
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
        private final int f18895a;
        private final int b;

        private SavedState(Parcelable parcelable, int i, int i2) {
            super(parcelable);
            this.f18895a = i;
            this.b = i2;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f18895a = parcel.readInt();
            this.b = parcel.readInt();
        }

        public int a() {
            return this.f18895a;
        }

        public int b() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f18895a);
            parcel.writeInt(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getCurrentMin().intValue(), getCurrentSec().intValue());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentMin(Integer.valueOf(savedState.a()));
        setCurrentSec(Integer.valueOf(savedState.b()));
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.g = onTimeChangedListener;
    }

    public Integer getCurrentMin() {
        return Integer.valueOf(this.d.getValue());
    }

    public Integer getCurrentSec() {
        return Integer.valueOf(this.e.getValue());
    }

    public void setCurrentMin(Integer num) {
        if (num != null && num != getCurrentMin()) {
            this.d.setValue(num.intValue());
            b();
        }
    }

    public void setCurrentSec(Integer num) {
        if (num != null && num != getCurrentSec()) {
            this.e.setValue(num.intValue());
            b();
        }
    }

    public int getBaseline() {
        return this.d.getBaseline();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(HourSpanPicker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(HourSpanPicker.class.getName());
    }

    private void a() {
        this.d.setMinValue(0);
        this.d.setMaxValue(59);
        this.d.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        this.e.setMinValue(0);
        this.e.setMaxValue(59);
        this.e.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
    }

    /* access modifiers changed from: private */
    public void b() {
        sendAccessibilityEvent(4);
        if (this.g != null) {
            this.g.a(this, getCurrentMin().intValue(), getCurrentMin().intValue());
        }
    }

    private void a(View view, int i2, int i3) {
        View findViewById = view.findViewById(i2);
        if (findViewById != null) {
            findViewById.setContentDescription(getContext().getString(i3));
        }
    }
}
