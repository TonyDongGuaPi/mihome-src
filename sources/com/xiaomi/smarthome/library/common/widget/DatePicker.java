package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePicker extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18810a = "DatePicker";
    private static final String b = "MM/dd/yyyy";
    private static final int c = 1900;
    private static final int d = 2100;
    private static final boolean e = false;
    private static final boolean f = true;
    private static final boolean g = true;
    private final LinearLayout h;
    /* access modifiers changed from: private */
    public final NumberPicker i;
    /* access modifiers changed from: private */
    public final NumberPicker j;
    /* access modifiers changed from: private */
    public final NumberPicker k;
    private Locale l;
    private OnDateChangedListener m;
    private String[] n;
    private final DateFormat o;
    private int p;
    /* access modifiers changed from: private */
    public Calendar q;
    private Calendar r;
    private Calendar s;
    /* access modifiers changed from: private */
    public Calendar t;
    private boolean u;

    public interface OnDateChangedListener {
        void a(DatePicker datePicker, int i, int i2, int i3);
    }

    /* access modifiers changed from: private */
    public void d() {
    }

    public CalendarView getCalendarView() {
        return null;
    }

    public boolean getCalendarViewShown() {
        return false;
    }

    public void setCalendarViewShown(boolean z) {
    }

    public DatePicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.o = new SimpleDateFormat(b);
        this.u = true;
        this.q = Calendar.getInstance();
        this.r = Calendar.getInstance();
        this.s = Calendar.getInstance();
        this.t = Calendar.getInstance();
        setCurrentLocale(Locale.getDefault());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.date_picker, this, true);
        AnonymousClass1 r10 = new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                DatePicker.this.q.setTimeInMillis(DatePicker.this.t.getTimeInMillis());
                if (numberPicker == DatePicker.this.i) {
                    DatePicker.this.q.add(5, i2 - i);
                } else if (numberPicker == DatePicker.this.j) {
                    DatePicker.this.q.add(2, i2 - i);
                } else if (numberPicker == DatePicker.this.k) {
                    DatePicker.this.q.set(1, i2);
                } else {
                    throw new IllegalArgumentException();
                }
                DatePicker.this.b(DatePicker.this.q.get(1), DatePicker.this.q.get(2), DatePicker.this.q.get(5));
                DatePicker.this.c();
                DatePicker.this.d();
                DatePicker.this.e();
            }
        };
        this.h = (LinearLayout) findViewById(R.id.pickers);
        this.i = (NumberPicker) findViewById(R.id.day);
        this.i.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        this.i.setOnLongPressUpdateInterval(100);
        this.i.setOnValueChangedListener(r10);
        this.j = (NumberPicker) findViewById(R.id.month);
        this.j.setMinValue(0);
        this.j.setMaxValue(this.p - 1);
        this.j.setDisplayedValues(this.n);
        this.j.setOnLongPressUpdateInterval(200);
        this.j.setOnValueChangedListener(r10);
        this.k = (NumberPicker) findViewById(R.id.year);
        this.k.setOnLongPressUpdateInterval(100);
        this.k.setOnValueChangedListener(r10);
        setSpinnersShown(true);
        setCalendarViewShown(false);
        this.q.setTimeInMillis(0);
        this.q.set(1900, 0, 1, 0, 0, 0);
        setMinDate(this.q.getTimeInMillis());
        this.q.setTimeInMillis(0);
        this.q.set(2100, 11, 31, 0, 0, 0);
        setMaxDate(this.q.getTimeInMillis());
        this.t.setTimeInMillis(System.currentTimeMillis());
        init(this.t.get(1), this.t.get(2), this.t.get(5), (OnDateChangedListener) null);
        b();
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public long getMinDate() {
        return this.r.getTimeInMillis();
    }

    public void setMinDate(long j2) {
        this.q.setTimeInMillis(j2);
        if (this.q.get(1) != this.r.get(1) || this.q.get(6) == this.r.get(6)) {
            this.r.setTimeInMillis(j2);
            if (this.t.before(this.r)) {
                this.t.setTimeInMillis(this.r.getTimeInMillis());
                d();
            }
            c();
        }
    }

    public long getMaxDate() {
        return this.s.getTimeInMillis();
    }

    public void setMaxDate(long j2) {
        this.q.setTimeInMillis(j2);
        if (this.q.get(1) != this.s.get(1) || this.q.get(6) == this.s.get(6)) {
            this.s.setTimeInMillis(j2);
            if (this.t.after(this.s)) {
                this.t.setTimeInMillis(this.s.getTimeInMillis());
                d();
            }
            c();
        }
    }

    public void setEnabled(boolean z) {
        if (this.u != z) {
            super.setEnabled(z);
            this.i.setEnabled(z);
            this.j.setEnabled(z);
            this.k.setEnabled(z);
            this.u = z;
        }
    }

    public boolean isEnabled() {
        return this.u;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.t.getTimeInMillis(), 16));
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DatePicker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DatePicker.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    public boolean getSpinnersShown() {
        return this.h.isShown();
    }

    public void setSpinnersShown(boolean z) {
        this.h.setVisibility(z ? 0 : 8);
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.l)) {
            this.l = locale;
            this.q = a(this.q, locale);
            this.r = a(this.r, locale);
            this.s = a(this.s, locale);
            this.t = a(this.t, locale);
            this.p = this.q.getActualMaximum(2) + 1;
            this.n = new DateFormatSymbols().getShortMonths();
            if (a()) {
                this.n = new String[this.p];
                int i2 = 0;
                while (i2 < this.p) {
                    int i3 = i2 + 1;
                    this.n[i2] = String.format("%d", new Object[]{Integer.valueOf(i3)});
                    i2 = i3;
                }
            }
        }
    }

    private boolean a() {
        return Character.isDigit(this.n[0].charAt(0));
    }

    private Calendar a(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar instance = Calendar.getInstance(locale);
        instance.setTimeInMillis(timeInMillis);
        return instance;
    }

    private void b() {
        this.h.removeAllViews();
        char[] dateFormatOrder = android.text.format.DateFormat.getDateFormatOrder(getContext());
        int length = dateFormatOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = dateFormatOrder[i2];
            if (c2 == 'M') {
                this.h.addView(this.j);
                a(this.j, length, i2);
            } else if (c2 == 'd') {
                this.h.addView(this.i);
                a(this.i, length, i2);
            } else if (c2 == 'y') {
                this.h.addView(this.k);
                a(this.k, length, i2);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void updateDate(int i2, int i3, int i4) {
        if (a(i2, i3, i4)) {
            b(i2, i3, i4);
            c();
            d();
            e();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getYear(), getMonth(), getDayOfMonth());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        b(savedState.f18812a, savedState.b, savedState.c);
        c();
        d();
    }

    public void init(int i2, int i3, int i4, OnDateChangedListener onDateChangedListener) {
        b(i2, i3, i4);
        c();
        d();
        this.m = onDateChangedListener;
    }

    private boolean a(String str, Calendar calendar) {
        try {
            calendar.setTimeInMillis(this.o.parse(str).getTime());
            return true;
        } catch (ParseException unused) {
            String str2 = f18810a;
            Log.w(str2, "Date: " + str + " not in format: " + b);
            return false;
        }
    }

    private boolean a(int i2, int i3, int i4) {
        if (this.t.get(1) == i2 && this.t.get(2) == i4 && this.t.get(5) == i3) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void b(int i2, int i3, int i4) {
        this.t.set(i2, i3, i4, 0, 0, 0);
        if (this.t.before(this.r)) {
            this.t.setTimeInMillis(this.r.getTimeInMillis());
        } else if (this.t.after(this.s)) {
            this.t.setTimeInMillis(this.s.getTimeInMillis());
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.i.setDisplayedValues((String[]) null);
        this.i.setMinValue(1);
        this.i.setMaxValue(this.t.getActualMaximum(5));
        this.i.setWrapSelectorWheel(true);
        this.j.setDisplayedValues((String[]) null);
        this.j.setMinValue(0);
        this.j.setMaxValue(11);
        this.j.setWrapSelectorWheel(true);
        if (this.t.get(1) == this.r.get(1)) {
            this.j.setMinValue(this.r.get(2));
            this.j.setWrapSelectorWheel(false);
            if (this.t.get(2) == this.r.get(2)) {
                this.i.setMinValue(this.r.get(5));
                this.i.setWrapSelectorWheel(false);
            }
        }
        if (this.t.get(1) == this.s.get(1)) {
            this.j.setMaxValue(this.s.get(2));
            this.j.setWrapSelectorWheel(false);
            this.j.setDisplayedValues((String[]) null);
            if (this.t.get(2) == this.s.get(2)) {
                this.i.setMaxValue(this.s.get(5));
                this.i.setWrapSelectorWheel(false);
            }
        }
        this.j.setDisplayedValues(this.n);
        this.k.setMinValue(this.r.get(1));
        this.k.setMaxValue(this.s.get(1));
        this.k.setWrapSelectorWheel(false);
        this.k.setValue(this.t.get(1));
        this.j.setValue(this.t.get(2));
        this.i.setValue(this.t.get(5));
    }

    public int getYear() {
        return this.t.get(1);
    }

    public int getMonth() {
        return this.t.get(2);
    }

    public int getDayOfMonth() {
        return this.t.get(5);
    }

    /* access modifiers changed from: private */
    public void e() {
        sendAccessibilityEvent(4);
        if (this.m != null) {
            this.m.a(this, getYear(), getMonth(), getDayOfMonth());
        }
    }

    private void a(NumberPicker numberPicker, int i2, int i3) {
        ((TextView) numberPicker.findViewById(R.id.numberpicker_input)).setImeOptions(i3 < i2 + -1 ? 5 : 6);
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
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final int f18812a;
        /* access modifiers changed from: private */
        public final int b;
        /* access modifiers changed from: private */
        public final int c;

        private SavedState(Parcelable parcelable, int i, int i2, int i3) {
            super(parcelable);
            this.f18812a = i;
            this.b = i2;
            this.c = i3;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f18812a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f18812a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }
    }
}
