package com.xiaomi.miot;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() {
        /* renamed from: a */
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        /* renamed from: a */
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f11363a;
    public String b;
    public String c = "";
    public String d = "";
    public String e = "";
    public Bundle f = new Bundle();
    public boolean g;
    public boolean h;
    public boolean i;

    public int describeContents() {
        return 0;
    }

    public DeviceInfo(Parcel parcel) {
        this.f11363a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.e = parcel.readString();
        boolean z = false;
        this.g = parcel.readInt() != 0;
        this.f = parcel.readBundle(ClassLoader.getSystemClassLoader());
        this.h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0 ? true : z;
        this.d = parcel.readString();
    }

    public DeviceInfo() {
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f11363a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeBundle(this.f);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeString(this.d);
    }

    public String toString() {
        return "DeviceInfo{did='" + this.f11363a + Operators.SINGLE_QUOTE + ", deviceName='" + this.b + Operators.SINGLE_QUOTE + ", icon='" + this.c + Operators.SINGLE_QUOTE + ", model='" + this.d + Operators.SINGLE_QUOTE + ", subtitle='" + this.e + Operators.SINGLE_QUOTE + ", subtitleMap=" + this.f + ", isOnline=" + this.g + ", showSlideButton=" + this.h + ", currentStatus=" + this.i + Operators.BLOCK_END;
    }
}
