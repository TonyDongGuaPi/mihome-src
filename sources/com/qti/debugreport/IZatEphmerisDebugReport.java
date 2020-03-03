package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatEphmerisDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatEphmerisDebugReport> CREATOR = new Parcelable.Creator<IZatEphmerisDebugReport>() {
        /* renamed from: a */
        public IZatEphmerisDebugReport createFromParcel(Parcel parcel) {
            return new IZatEphmerisDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatEphmerisDebugReport[] newArray(int i) {
            return new IZatEphmerisDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8577a = "IZatEphmeris";
    private static final boolean b = Log.isLoggable(f8577a, 2);
    private int c;
    private int d;
    private long e;
    private long f;
    private byte g;
    private IZatUtcSpec h;
    private IZatUtcSpec i;

    public int describeContents() {
        return 0;
    }

    public IZatEphmerisDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, int i3, long j, long j2, byte b2) {
        this.h = iZatUtcSpec;
        this.i = iZatUtcSpec2;
        this.c = i2;
        this.d = i3;
        this.e = j;
        this.f = j2;
        this.g = b2;
    }

    public IZatEphmerisDebugReport(Parcel parcel) {
        this.h = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.i = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readByte();
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public long d() {
        return this.f;
    }

    public byte e() {
        return this.g;
    }

    public IZatUtcSpec f() {
        return this.h;
    }

    public IZatUtcSpec g() {
        return this.i;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.h, 0);
        parcel.writeParcelable(this.i, 0);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeByte(this.g);
    }
}
