package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;

public class MiTVDevice extends MiioDevice {
    public static final Parcelable.Creator<MiTVDevice> CREATOR = new Parcelable.Creator<MiTVDevice>() {
        /* renamed from: a */
        public MiTVDevice createFromParcel(Parcel parcel) {
            return new MiTVDevice(parcel);
        }

        /* renamed from: a */
        public MiTVDevice[] newArray(int i) {
            return new MiTVDevice[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private boolean f13974a;

    protected MiTVDevice(Parcel parcel) {
        super(parcel);
    }

    public MiTVDevice() {
    }

    public synchronized void f(boolean z) {
        this.f13974a = z;
    }

    public void e(boolean z) {
        if (z) {
            d(p() | 4);
        } else {
            d(p() & -5);
        }
    }
}
