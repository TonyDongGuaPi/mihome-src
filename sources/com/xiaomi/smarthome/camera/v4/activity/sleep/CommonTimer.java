package com.xiaomi.smarthome.camera.v4.activity.sleep;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.constants.AppConstants;

public final class CommonTimer implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CommonTimer> CREATOR = new Parcelable.Creator<CommonTimer>() {
        public CommonTimer createFromParcel(Parcel parcel) {
            return new CommonTimer(parcel);
        }

        public CommonTimer[] newArray(int i) {
            return new CommonTimer[i];
        }
    };
    public static final int TIMER_STATUS_ERROR_LOCAL_SCENE_SYNC_DEL_UNXDEL_CODE = -1;
    public static final int TIMER_STATUS_ERROR_LOCAL_SCENE_SYNC_SET_ABLE_DID_UNXDEL_CODE = 2;
    public static final int TIMER_STATUS_ERROR_LOCAL_SCENE_SYNC_SET_ABLE_DID_UNXSET_CODE = 3;
    public static final int TIMER_STATUS_ERROR_LOCAL_SCENE_SYNC_SET_DISABLE_UNXDEL_CODE = 1;
    public static final int TIMER_STATUS_NORMAL = 0;
    public boolean enable;
    public boolean enablePush;
    public String name;
    public boolean off;
    public String offMethod;
    public String offParams;
    public AutoSleepBean offTime;
    public boolean on;
    public String onMethod;
    public String onParams;
    public AutoSleepBean onTime;
    public int status;
    public int us_id;

    public int describeContents() {
        return 0;
    }

    public CommonTimer() {
        this.us_id = 0;
        this.enablePush = true;
        this.enable = true;
        this.on = false;
        this.onMethod = AppConstants.B;
        this.onParams = "off";
        this.onTime = new AutoSleepBean();
        this.off = false;
        this.offMethod = AppConstants.B;
        this.offParams = "on";
        this.offTime = new AutoSleepBean();
        this.name = null;
        this.status = 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.us_id);
        parcel.writeByte(this.enablePush ? (byte) 1 : 0);
        parcel.writeByte(this.enable ? (byte) 1 : 0);
        parcel.writeByte(this.on ? (byte) 1 : 0);
        parcel.writeString(this.onMethod);
        parcel.writeString(this.onParams);
        parcel.writeInt(this.onTime.minute);
        parcel.writeInt(this.onTime.hour);
        parcel.writeInt(this.onTime.day);
        parcel.writeInt(this.onTime.month);
        parcel.writeBooleanArray(this.onTime.weeks);
        parcel.writeByte(this.off ? (byte) 1 : 0);
        parcel.writeString(this.offMethod);
        parcel.writeString(this.offParams);
        parcel.writeInt(this.offTime.minute);
        parcel.writeInt(this.offTime.hour);
        parcel.writeInt(this.offTime.day);
        parcel.writeInt(this.offTime.month);
        parcel.writeBooleanArray(this.offTime.weeks);
        parcel.writeString(this.name);
        parcel.writeInt(this.status);
    }

    public Object clone() {
        CommonTimer commonTimer;
        CloneNotSupportedException e;
        try {
            commonTimer = (CommonTimer) super.clone();
            try {
                commonTimer.us_id = this.us_id;
                commonTimer.enable = this.enable;
                commonTimer.on = this.on;
                commonTimer.onMethod = this.onMethod;
                commonTimer.onParams = this.onParams;
                commonTimer.onTime = (AutoSleepBean) this.onTime.clone();
                commonTimer.off = this.off;
                commonTimer.offMethod = this.offMethod;
                commonTimer.offParams = this.offParams;
                commonTimer.offTime = (AutoSleepBean) this.offTime.clone();
                commonTimer.name = this.name;
                commonTimer.status = this.status;
            } catch (CloneNotSupportedException e2) {
                e = e2;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            commonTimer = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return commonTimer;
        }
        return commonTimer;
    }

    private CommonTimer(Parcel parcel) {
        this.us_id = parcel.readInt();
        boolean z = false;
        this.enablePush = parcel.readByte() != 0;
        this.enable = parcel.readByte() != 0;
        this.on = parcel.readByte() != 0;
        this.onMethod = parcel.readString();
        this.onParams = parcel.readString();
        this.onTime = new AutoSleepBean();
        this.onTime.minute = parcel.readInt();
        this.onTime.hour = parcel.readInt();
        this.onTime.day = parcel.readInt();
        this.onTime.month = parcel.readInt();
        parcel.readBooleanArray(this.onTime.weeks);
        this.off = parcel.readByte() != 0 ? true : z;
        this.offMethod = parcel.readString();
        this.offParams = parcel.readString();
        this.offTime = new AutoSleepBean();
        this.offTime.minute = parcel.readInt();
        this.offTime.hour = parcel.readInt();
        this.offTime.day = parcel.readInt();
        this.offTime.month = parcel.readInt();
        parcel.readBooleanArray(this.offTime.weeks);
        this.name = parcel.readString();
        this.status = parcel.readInt();
    }

    public static String formatTime(int i, int i2) {
        return formatDigits(i) + ":" + formatDigits(i2);
    }

    public static String formatDigits(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        }
        return "0" + i;
    }
}
