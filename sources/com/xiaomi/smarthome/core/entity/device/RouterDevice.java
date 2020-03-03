package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;

public class RouterDevice extends MiioDevice {
    public static final Parcelable.Creator<RouterDevice> CREATOR = new Parcelable.Creator<RouterDevice>() {
        /* renamed from: a */
        public RouterDevice createFromParcel(Parcel parcel) {
            return new RouterDevice(parcel);
        }

        /* renamed from: a */
        public RouterDevice[] newArray(int i) {
            return new RouterDevice[i];
        }
    };

    protected RouterDevice(Parcel parcel) {
        super(parcel);
    }

    public RouterDevice() {
    }
}
