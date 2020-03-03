package com.xiaomi.miot;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceDescInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceDescInfo> CREATOR = new Parcelable.Creator<DeviceDescInfo>() {
        /* renamed from: a */
        public DeviceDescInfo createFromParcel(Parcel parcel) {
            return new DeviceDescInfo(parcel);
        }

        /* renamed from: a */
        public DeviceDescInfo[] newArray(int i) {
            return new DeviceDescInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f11362a = 1;
    public static final int b = 2;
    public int c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public long j;

    public int describeContents() {
        return 0;
    }

    public DeviceDescInfo() {
    }

    public DeviceDescInfo(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeLong(this.j);
    }
}
