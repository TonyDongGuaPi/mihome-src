package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatRfStateDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatRfStateDebugReport> CREATOR = new Parcelable.Creator<IZatRfStateDebugReport>() {
        /* renamed from: a */
        public IZatRfStateDebugReport createFromParcel(Parcel parcel) {
            return new IZatRfStateDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatRfStateDebugReport[] newArray(int i) {
            return new IZatRfStateDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8583a = "IZatRfStateReport";
    private static final boolean b = Log.isLoggable(f8583a, 2);
    private IZatUtcSpec c;
    private IZatUtcSpec d;
    private int e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;

    public int describeContents() {
        return 0;
    }

    public IZatRfStateDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15) {
        this.c = iZatUtcSpec;
        this.d = iZatUtcSpec2;
        this.e = i2;
        this.f = j2;
        this.g = j3;
        this.h = j4;
        this.i = j5;
        this.j = j6;
        this.k = j7;
        this.m = j8;
        this.n = j9;
        this.o = j10;
        this.p = j11;
        this.q = j12;
        this.r = j13;
        this.s = j14;
        this.t = j15;
    }

    public IZatRfStateDebugReport(Parcel parcel) {
        this.c = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.d = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.e = parcel.readInt();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readLong();
        this.k = parcel.readLong();
        this.m = parcel.readLong();
        this.n = parcel.readLong();
        this.o = parcel.readLong();
        this.p = parcel.readLong();
        this.q = parcel.readLong();
        this.r = parcel.readLong();
        this.s = parcel.readLong();
        this.t = parcel.readLong();
    }

    public IZatUtcSpec a() {
        return this.c;
    }

    public IZatUtcSpec b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public long d() {
        return this.f;
    }

    public long e() {
        return this.g;
    }

    public long f() {
        return this.h;
    }

    public long g() {
        return this.i;
    }

    public long h() {
        return this.j;
    }

    public long i() {
        return this.k;
    }

    public long j() {
        return this.m;
    }

    public long k() {
        return this.n;
    }

    public long l() {
        return this.o;
    }

    public long m() {
        return this.p;
    }

    public long n() {
        return this.q;
    }

    public long o() {
        return this.r;
    }

    public long p() {
        return this.s;
    }

    public long q() {
        return this.t;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.c, 0);
        parcel.writeParcelable(this.d, 0);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeLong(this.j);
        parcel.writeLong(this.k);
        parcel.writeLong(this.m);
        parcel.writeLong(this.n);
        parcel.writeLong(this.o);
        parcel.writeLong(this.p);
        parcel.writeLong(this.q);
        parcel.writeLong(this.r);
        parcel.writeLong(this.s);
        parcel.writeLong(this.t);
    }
}
