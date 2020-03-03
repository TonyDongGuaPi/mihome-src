package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class IZatSVHealthDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatSVHealthDebugReport> CREATOR = new Parcelable.Creator<IZatSVHealthDebugReport>() {
        /* renamed from: a */
        public IZatSVHealthDebugReport createFromParcel(Parcel parcel) {
            return new IZatSVHealthDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatSVHealthDebugReport[] newArray(int i) {
            return new IZatSVHealthDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8584a = "IZatSVHealthReport";
    private static final boolean b = Log.isLoggable(f8584a, 2);
    private List<IzatSVHealthState> c = new ArrayList();
    private List<IzatSVHealthState> d = new ArrayList();
    private List<IzatSVHealthState> e = new ArrayList();
    private List<IzatSVHealthState> f = new ArrayList();
    private List<IzatSVHealthState> g = new ArrayList();
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private byte t;
    private byte u;
    private byte v;
    private IZatUtcSpec w;
    private IZatUtcSpec x;

    public int describeContents() {
        return 0;
    }

    public enum IzatSVHealthState {
        SV_HEALTH_UNKNOWN(0),
        SV_HEALTH_GOOD(1),
        SV_HEALTH_BAD(2);
        
        private final int mValue;

        private IzatSVHealthState(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public List<IzatSVHealthState> a() {
        return this.c;
    }

    public List<IzatSVHealthState> b() {
        return this.d;
    }

    public List<IzatSVHealthState> c() {
        return this.e;
    }

    public List<IzatSVHealthState> d() {
        return this.f;
    }

    public List<IzatSVHealthState> e() {
        return this.g;
    }

    public IZatSVHealthDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, int i3, int i4, int i5, int i6, int i7, long j2, long j3, long j4, long j5, long j6, long j7, byte b2, byte b3, byte b4) {
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        int i12 = i6;
        int i13 = i7;
        byte b5 = b2;
        byte b6 = b3;
        byte b7 = b4;
        this.w = iZatUtcSpec;
        this.x = iZatUtcSpec2;
        this.h = i8;
        this.i = i9;
        this.j = i10;
        this.k = i11;
        this.l = i12;
        this.m = i13;
        this.n = j2;
        this.o = j3;
        this.p = j4;
        this.q = j5;
        this.r = j6;
        this.s = j7;
        this.t = b5;
        this.u = b6;
        this.v = b7;
        a(this.c, i8, i9, i10);
        a(this.d, i11, i12, i13);
        a(this.e, j2, j3, j4);
        a(this.f, j5, j6, j7);
        a(this.g, b5, b6, b7);
    }

    public IZatSVHealthDebugReport(Parcel parcel) {
        this.w = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.x = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readLong();
        this.o = parcel.readLong();
        this.p = parcel.readLong();
        this.q = parcel.readLong();
        this.r = parcel.readLong();
        this.s = parcel.readLong();
        this.t = parcel.readByte();
        this.u = parcel.readByte();
        this.v = parcel.readByte();
        a(this.c, this.h, this.i, this.j);
        a(this.d, this.k, this.l, this.m);
        a(this.e, this.n, this.o, this.p);
        a(this.f, this.q, this.r, this.s);
        a(this.g, this.t, this.u, this.v);
    }

    private void a(List<IzatSVHealthState> list, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < 32; i5++) {
            int i6 = 1 << i5;
            if ((i6 & i2) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_GOOD);
            } else if ((i6 & i3) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_BAD);
            } else {
                list.add(IzatSVHealthState.SV_HEALTH_UNKNOWN);
            }
        }
    }

    private void a(List<IzatSVHealthState> list, long j2, long j3, long j4) {
        List<IzatSVHealthState> list2 = list;
        for (long j5 = 0; j5 < 64; j5++) {
            long j6 = 1 << ((int) j5);
            if ((j6 & j2) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_GOOD);
            } else if ((j6 & j3) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_BAD);
            } else {
                list.add(IzatSVHealthState.SV_HEALTH_UNKNOWN);
            }
        }
    }

    private void a(List<IzatSVHealthState> list, byte b2, byte b3, byte b4) {
        for (byte b5 = 0; b5 < 8; b5 = (byte) (b5 + 1)) {
            int i2 = 1 << b5;
            if ((i2 & b2) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_GOOD);
            } else if ((i2 & b3) != 0) {
                list.add(IzatSVHealthState.SV_HEALTH_BAD);
            } else {
                list.add(IzatSVHealthState.SV_HEALTH_UNKNOWN);
            }
        }
    }

    public IZatUtcSpec f() {
        return this.w;
    }

    public IZatUtcSpec g() {
        return this.x;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.w, 0);
        parcel.writeParcelable(this.x, 0);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeLong(this.n);
        parcel.writeLong(this.o);
        parcel.writeLong(this.p);
        parcel.writeLong(this.q);
        parcel.writeLong(this.r);
        parcel.writeLong(this.s);
        parcel.writeByte(this.t);
        parcel.writeByte(this.u);
        parcel.writeByte(this.v);
    }
}
