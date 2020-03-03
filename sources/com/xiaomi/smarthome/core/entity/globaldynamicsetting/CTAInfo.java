package com.xiaomi.smarthome.core.entity.globaldynamicsetting;

import android.os.Parcel;
import android.os.Parcelable;

public class CTAInfo implements Parcelable {
    public static final Parcelable.Creator<CTAInfo> CREATOR = new Parcelable.Creator<CTAInfo>() {
        /* renamed from: a */
        public CTAInfo createFromParcel(Parcel parcel) {
            return new CTAInfo(parcel);
        }

        /* renamed from: a */
        public CTAInfo[] newArray(int i) {
            return new CTAInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private boolean f13977a;
    private boolean b;

    public int describeContents() {
        return 0;
    }

    public CTAInfo() {
    }

    protected CTAInfo(Parcel parcel) {
        boolean z = false;
        this.f13977a = parcel.readByte() != 0;
        this.b = parcel.readByte() != 0 ? true : z;
    }

    public synchronized boolean a() {
        return this.f13977a;
    }

    public synchronized void a(boolean z) {
        this.f13977a = z;
    }

    public synchronized boolean b() {
        return this.b;
    }

    public synchronized void b(boolean z) {
        this.b = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f13977a ? (byte) 1 : 0);
        parcel.writeByte(this.b ? (byte) 1 : 0);
    }
}
