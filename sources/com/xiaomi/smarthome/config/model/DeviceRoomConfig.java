package com.xiaomi.smarthome.config.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceRoomConfig implements Parcelable {
    public static final Parcelable.Creator<DeviceRoomConfig> CREATOR = new Parcelable.Creator<DeviceRoomConfig>() {
        /* renamed from: a */
        public DeviceRoomConfig createFromParcel(Parcel parcel) {
            return new DeviceRoomConfig(parcel);
        }

        /* renamed from: a */
        public DeviceRoomConfig[] newArray(int i) {
            return new DeviceRoomConfig[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13956a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private final int d;
    private final int e;

    public int describeContents() {
        return 0;
    }

    public DeviceRoomConfig(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
    }

    protected DeviceRoomConfig(Parcel parcel) {
        this.d = parcel.readInt();
        this.e = parcel.readInt();
    }
}
