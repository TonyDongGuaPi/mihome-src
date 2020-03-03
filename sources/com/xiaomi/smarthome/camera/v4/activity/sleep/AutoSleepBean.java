package com.xiaomi.smarthome.camera.v4.activity.sleep;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;

public class AutoSleepBean implements Parcelable, Cloneable {
    public static final String COMMON_TIMER = "auto_sleep_add";
    public static final Parcelable.Creator<AutoSleepBean> CREATOR = new Parcelable.Creator<AutoSleepBean>() {
        public AutoSleepBean createFromParcel(Parcel parcel) {
            return new AutoSleepBean(parcel);
        }

        public AutoSleepBean[] newArray(int i) {
            return new AutoSleepBean[i];
        }
    };
    public static final long DAY = 86400000;
    public static final int EVERYDAY = 127;
    public static final int ONCE = 0;
    public static final String SEPRATOR = " ";
    public static final int TIMER_TYPE_EVERYDAY = 1;
    public static final int TIMER_TYPE_ONCE = 0;
    public static final int TIMER_TYPE_SELF_DEFINE = 4;
    public static final int TIMER_TYPE_WEEKEND = 3;
    public static final int TIMER_TYPE_WORKDAY = 2;
    public static final int WEEKEND = 65;
    public static final int WORKDAY = 62;
    public int day;
    public int hour;
    public int minute;
    public int month;
    public boolean[] weeks = new boolean[7];

    public int describeContents() {
        return 0;
    }

    public AutoSleepBean() {
        Calendar instance = Calendar.getInstance();
        this.day = instance.get(5);
        this.month = instance.get(2) + 1;
        this.hour = instance.get(11);
        this.minute = instance.get(12);
        Arrays.fill(this.weeks, false);
    }

    protected AutoSleepBean(Parcel parcel) {
        this.minute = parcel.readInt();
        this.hour = parcel.readInt();
        this.day = parcel.readInt();
        this.month = parcel.readInt();
        this.weeks = parcel.createBooleanArray();
    }

    public int getRepeatType() {
        if (this.day != -1) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.weeks.length; i2++) {
            if (this.weeks[i2]) {
                i |= 1 << i2;
            }
        }
        if (i == 62) {
            return 2;
        }
        if (i == 65) {
            return 3;
        }
        if (i != 127) {
            return 4;
        }
        return 1;
    }

    public void setRepeat(int i) {
        if (i == 0) {
            Calendar instance = Calendar.getInstance();
            this.day = instance.get(5);
            this.month = instance.get(2) + 1;
            Arrays.fill(this.weeks, false);
            return;
        }
        this.day = -1;
        this.month = -1;
        for (int i2 = 0; i2 < this.weeks.length; i2++) {
            this.weeks[i2] = ((1 << i2) & i) > 0;
        }
    }

    public void setWeekday(int i, boolean z) {
        this.day = -1;
        this.month = -1;
        this.weeks[i] = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.minute);
        parcel.writeInt(this.hour);
        parcel.writeInt(this.day);
        parcel.writeInt(this.month);
        parcel.writeBooleanArray(this.weeks);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AutoSleepBean)) {
            return false;
        }
        AutoSleepBean autoSleepBean = (AutoSleepBean) obj;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.weeks.length; i3++) {
            if (this.weeks[i3]) {
                i |= 1 << i3;
            }
            if (autoSleepBean.weeks[i3]) {
                i2 |= 1 << i3;
            }
        }
        if (this.minute == autoSleepBean.minute && this.hour == autoSleepBean.hour && this.day == autoSleepBean.day && this.month == autoSleepBean.month && i == i2) {
            return true;
        }
        return false;
    }

    public void shiftWeekday(int i) {
        if (this.day == -1 && this.month == -1) {
            int length = i % this.weeks.length;
            int i2 = 0;
            int i3 = 0;
            for (int length2 = this.weeks.length - 1; i3 < length2; length2--) {
                boolean z = this.weeks[i3];
                this.weeks[i3] = this.weeks[length2];
                this.weeks[length2] = z;
                i3++;
            }
            for (int i4 = length - 1; i2 < i4; i4--) {
                boolean z2 = this.weeks[i2];
                this.weeks[i2] = this.weeks[i4];
                this.weeks[i4] = z2;
                i2++;
            }
            for (int length3 = this.weeks.length - 1; length < length3; length3--) {
                boolean z3 = this.weeks[length];
                this.weeks[length] = this.weeks[length3];
                this.weeks[length3] = z3;
                length++;
            }
        }
    }

    public Object clone() {
        AutoSleepBean autoSleepBean;
        CloneNotSupportedException e;
        try {
            autoSleepBean = (AutoSleepBean) super.clone();
            try {
                autoSleepBean.minute = this.minute;
                autoSleepBean.hour = this.hour;
                autoSleepBean.day = this.day;
                autoSleepBean.month = this.month;
                autoSleepBean.weeks = new boolean[7];
                System.arraycopy(this.weeks, 0, autoSleepBean.weeks, 0, this.weeks.length);
            } catch (CloneNotSupportedException e2) {
                e = e2;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            autoSleepBean = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return autoSleepBean;
        }
        return autoSleepBean;
    }
}
