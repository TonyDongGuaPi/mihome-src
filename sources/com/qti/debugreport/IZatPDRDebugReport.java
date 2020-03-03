package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatPDRDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatPDRDebugReport> CREATOR = new Parcelable.Creator<IZatPDRDebugReport>() {
        /* renamed from: a */
        public IZatPDRDebugReport createFromParcel(Parcel parcel) {
            return new IZatPDRDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatPDRDebugReport[] newArray(int i) {
            return new IZatPDRDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8582a = "IZatPDR";
    private static final boolean b = Log.isLoggable(f8582a, 2);
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 4;
    private static final int f = 8;
    private int g;
    private IZatUtcSpec h;
    private IZatUtcSpec i;

    public int describeContents() {
        return 0;
    }

    public IZatPDRDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2) {
        this.h = iZatUtcSpec;
        this.i = iZatUtcSpec2;
        this.g = i2;
    }

    public IZatPDRDebugReport(Parcel parcel) {
        this.h = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.i = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.g = parcel.readInt();
    }

    public boolean a() {
        return (this.g & 4) != 0;
    }

    public boolean b() {
        return (this.g & 8) != 0;
    }

    public boolean c() {
        return (this.g & 1) != 0;
    }

    public boolean d() {
        return (this.g & 2) != 0;
    }

    public IZatUtcSpec e() {
        return this.h;
    }

    public IZatUtcSpec f() {
        return this.i;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.h, 0);
        parcel.writeParcelable(this.i, 0);
        parcel.writeInt(this.g);
    }
}
