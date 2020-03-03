package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatLocationReport implements Parcelable {
    public static final Parcelable.Creator<IZatLocationReport> CREATOR = new Parcelable.Creator<IZatLocationReport>() {
        /* renamed from: a */
        public IZatLocationReport createFromParcel(Parcel parcel) {
            return new IZatLocationReport(parcel);
        }

        /* renamed from: a */
        public IZatLocationReport[] newArray(int i) {
            return new IZatLocationReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8581a = "IZatLocationReport";
    private static final boolean b = Log.isLoggable(f8581a, 2);
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 4;
    private int c;
    private double d;
    private double e;
    private float f;
    private double g;
    private float h;
    private IzatLocationSource i;
    private IZatUtcSpec j;
    private IZatUtcSpec k;

    public int describeContents() {
        return 0;
    }

    public enum IzatLocationSource {
        POSITION_SOURCE_UNKNOWN(0),
        POSITION_SOURCE_CPI(1),
        POSITION_SOURCE_REFERENCE_LOCATION(2),
        POSITION_SOURCE_TLE(3);
        
        private final int mValue;

        private IzatLocationSource(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public IZatLocationReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, double d2, double d3, float f2, double d4, float f3, int i3) {
        this.j = iZatUtcSpec;
        this.k = iZatUtcSpec2;
        this.c = i2;
        if ((this.c & 1) != 0) {
            this.d = d2;
            this.e = d3;
            this.f = f2;
        }
        if ((this.c & 2) != 0) {
            this.g = d4;
            this.h = f3;
        }
        if ((this.c & 4) != 0) {
            try {
                this.i = IzatLocationSource.values()[i3];
            } catch (ArrayIndexOutOfBoundsException unused) {
                this.i = IzatLocationSource.POSITION_SOURCE_UNKNOWN;
            }
        }
    }

    public IZatLocationReport(Parcel parcel) {
        this.j = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.k = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.c = parcel.readInt();
        if ((this.c & 1) != 0) {
            this.d = parcel.readDouble();
            this.e = parcel.readDouble();
            this.f = parcel.readFloat();
        }
        if ((this.c & 2) != 0) {
            this.g = parcel.readDouble();
            this.h = parcel.readFloat();
        }
        if ((this.c & 4) != 0) {
            try {
                this.i = IzatLocationSource.values()[parcel.readInt()];
            } catch (ArrayIndexOutOfBoundsException unused) {
                this.i = IzatLocationSource.POSITION_SOURCE_UNKNOWN;
            }
        }
    }

    public boolean a() {
        return (this.c & 1) != 0;
    }

    public boolean b() {
        return (this.c & 2) != 0;
    }

    public boolean c() {
        return (this.c & 4) != 0;
    }

    public double d() {
        return this.d;
    }

    public double e() {
        return this.e;
    }

    public double f() {
        return this.g;
    }

    public float g() {
        return this.f;
    }

    public float h() {
        return this.h;
    }

    public IzatLocationSource i() {
        return this.i;
    }

    public IZatUtcSpec j() {
        return this.j;
    }

    public IZatUtcSpec k() {
        return this.k;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.j, 0);
        parcel.writeParcelable(this.k, 0);
        parcel.writeInt(this.c);
        if ((this.c & 1) != 0) {
            parcel.writeDouble(this.d);
            parcel.writeDouble(this.e);
            parcel.writeFloat(this.f);
        }
        if ((this.c & 2) != 0) {
            parcel.writeDouble(this.g);
            parcel.writeFloat(this.h);
        }
        if ((this.c & 4) != 0) {
            parcel.writeInt(this.i.getValue());
        }
    }
}
