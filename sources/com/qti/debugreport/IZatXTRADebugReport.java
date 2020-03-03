package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatXTRADebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatXTRADebugReport> CREATOR = new Parcelable.Creator<IZatXTRADebugReport>() {
        /* renamed from: a */
        public IZatXTRADebugReport createFromParcel(Parcel parcel) {
            return new IZatXTRADebugReport(parcel);
        }

        /* renamed from: a */
        public IZatXTRADebugReport[] newArray(int i) {
            return new IZatXTRADebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8586a = "IZatXTRAReport";
    private static final boolean b = Log.isLoggable(f8586a, 2);
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 4;
    private static final int n = 8;
    private static final int o = 16;
    private GpsXtraValidityInfo c;
    private GlonassXtraValidityInfo d;
    private BdsXtraValidityInfo e;
    private GalXtraValidityInfo f;
    private QzssXtraValidityInfo g;
    private byte h;
    private IZatUtcSpec i;
    private IZatUtcSpec j;

    public int describeContents() {
        return 0;
    }

    public class GpsXtraValidityInfo {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;

        public GpsXtraValidityInfo() {
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }
    }

    public class GlonassXtraValidityInfo {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;

        public GlonassXtraValidityInfo() {
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }
    }

    public class BdsXtraValidityInfo {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public long c;

        public BdsXtraValidityInfo() {
        }

        public int a() {
            return this.b;
        }

        public long b() {
            return this.c;
        }
    }

    public class GalXtraValidityInfo {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public long c;

        public GalXtraValidityInfo() {
        }

        public int a() {
            return this.b;
        }

        public long b() {
            return this.c;
        }
    }

    public class QzssXtraValidityInfo {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public byte c;

        public QzssXtraValidityInfo() {
        }

        public int a() {
            return this.b;
        }

        public byte b() {
            return this.c;
        }
    }

    public IZatXTRADebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, byte b2, int i2, int i3, int i4, int i5, long j2, int i6, long j3, int i7, byte b3, int i8) {
        this.i = iZatUtcSpec;
        this.j = iZatUtcSpec2;
        this.h = b2;
        if ((this.h & 1) != 0) {
            this.c = new GpsXtraValidityInfo();
            int unused = this.c.c = i2;
            int unused2 = this.c.b = i3;
        }
        if ((this.h & 2) != 0) {
            this.d = new GlonassXtraValidityInfo();
            int unused3 = this.d.c = i4;
            int unused4 = this.d.b = i5;
        }
        if ((this.h & 4) != 0) {
            this.e = new BdsXtraValidityInfo();
            long unused5 = this.e.c = j2;
            int unused6 = this.e.b = i6;
        }
        if ((this.h & 8) != 0) {
            this.f = new GalXtraValidityInfo();
            long unused7 = this.f.c = j3;
            int unused8 = this.f.b = i7;
        }
        if ((this.h & 16) != 0) {
            this.g = new QzssXtraValidityInfo();
            byte unused9 = this.g.c = b3;
            int unused10 = this.g.b = i8;
        }
    }

    public IZatXTRADebugReport(Parcel parcel) {
        this.i = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.j = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.h = parcel.readByte();
        if ((this.h & 1) != 0) {
            this.c = new GpsXtraValidityInfo();
            int unused = this.c.c = parcel.readInt();
            int unused2 = this.c.b = parcel.readInt();
        }
        if ((this.h & 2) != 0) {
            this.d = new GlonassXtraValidityInfo();
            int unused3 = this.d.c = parcel.readInt();
            int unused4 = this.d.b = parcel.readInt();
        }
        if ((this.h & 4) != 0) {
            this.e = new BdsXtraValidityInfo();
            long unused5 = this.e.c = parcel.readLong();
            int unused6 = this.e.b = parcel.readInt();
        }
        if ((this.h & 8) != 0) {
            this.f = new GalXtraValidityInfo();
            long unused7 = this.f.c = parcel.readLong();
            int unused8 = this.f.b = parcel.readInt();
        }
        if ((this.h & 16) != 0) {
            this.g = new QzssXtraValidityInfo();
            byte unused9 = this.g.c = parcel.readByte();
            int unused10 = this.g.b = parcel.readInt();
        }
    }

    public boolean a() {
        return (this.h & 1) != 0;
    }

    public boolean b() {
        return (this.h & 2) != 0;
    }

    public boolean c() {
        return (this.h & 4) != 0;
    }

    public boolean d() {
        return (this.h & 8) != 0;
    }

    public boolean e() {
        return (this.h & 16) != 0;
    }

    public GpsXtraValidityInfo f() {
        return this.c;
    }

    public GlonassXtraValidityInfo g() {
        return this.d;
    }

    public BdsXtraValidityInfo h() {
        return this.e;
    }

    public GalXtraValidityInfo i() {
        return this.f;
    }

    public QzssXtraValidityInfo j() {
        return this.g;
    }

    public IZatUtcSpec k() {
        return this.i;
    }

    public IZatUtcSpec l() {
        return this.j;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.i, 0);
        parcel.writeParcelable(this.j, 0);
        parcel.writeByte(this.h);
        if ((this.h & 1) != 0) {
            parcel.writeInt(this.c.c);
            parcel.writeInt(this.c.b);
        }
        if ((this.h & 2) != 0) {
            parcel.writeInt(this.d.c);
            parcel.writeInt(this.d.b);
        }
        if ((this.h & 4) != 0) {
            parcel.writeLong(this.e.c);
            parcel.writeInt(this.e.b);
        }
        if ((this.h & 8) != 0) {
            parcel.writeLong(this.f.c);
            parcel.writeInt(this.f.b);
        }
        if ((this.h & 16) != 0) {
            parcel.writeByte(this.g.c);
            parcel.writeInt(this.g.b);
        }
    }
}
