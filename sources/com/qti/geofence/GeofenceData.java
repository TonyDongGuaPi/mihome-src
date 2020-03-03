package com.qti.geofence;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class GeofenceData implements Parcelable {
    public static final Parcelable.Creator<GeofenceData> CREATOR = new Parcelable.Creator<GeofenceData>() {
        /* renamed from: a */
        public GeofenceData createFromParcel(Parcel parcel) {
            return new GeofenceData(parcel);
        }

        /* renamed from: a */
        public GeofenceData[] newArray(int i) {
            return new GeofenceData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f8593a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    public static final int e = 16;
    public static final int f = 32;
    public static final int g = 64;
    private static String h = "GeofenceData";
    private static final boolean i = Log.isLoggable(h, 2);
    private int j;
    private double k;
    private double l;
    private double m;
    private GeofenceTransitionTypes n;
    private GeofenceConfidence o;
    private DwellTypes p;
    private int q;
    private String r;
    private Bundle s;
    private int t;
    private boolean u;

    public int describeContents() {
        return 0;
    }

    public enum GeofenceTransitionTypes {
        UNKNOWN(0),
        ENTERED_ONLY(1),
        EXITED_ONLY(2),
        ENTERED_AND_EXITED(3);
        
        private final int mValue;

        private GeofenceTransitionTypes(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum GeofenceConfidence {
        LOW(1),
        MEDIUM(2),
        HIGH(3);
        
        private final int mValue;

        private GeofenceConfidence(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum DwellTypes {
        UNKNOWN(0),
        DWELL_TYPE_INSIDE(1),
        DWELL_TYPE_OUTSIDE(2),
        DWELL_TYPE_INSIDE_OUTSIDE(3);
        
        private final int mValue;

        private DwellTypes(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public GeofenceData(Parcel parcel) {
        this.j = parcel.readInt();
        this.k = parcel.readDouble();
        this.l = parcel.readDouble();
        this.m = parcel.readDouble();
        try {
            this.n = GeofenceTransitionTypes.valueOf(parcel.readString());
        } catch (IllegalArgumentException unused) {
            this.n = null;
        }
        try {
            this.o = GeofenceConfidence.valueOf(parcel.readString());
        } catch (IllegalArgumentException unused2) {
            this.o = null;
        }
        try {
            this.p = DwellTypes.valueOf(parcel.readString());
        } catch (IllegalArgumentException unused3) {
            this.p = null;
        }
        this.q = parcel.readInt();
        try {
            this.r = parcel.readString();
        } catch (IllegalArgumentException unused4) {
            this.r = null;
        }
        this.s = parcel.readBundle();
        this.t = parcel.readInt();
        this.u = parcel.readBoolean();
    }

    public GeofenceData(int i2, double d2, double d3, double d4, int i3, int i4, int i5, int i6, String str, Bundle bundle, int i7) {
        this.j = i2;
        this.k = d2;
        this.l = d3;
        this.m = d4;
        b(i3);
        c(i4);
        d(i5);
        this.q = i6;
        this.r = str;
        this.s = bundle;
        this.t = i7;
        this.u = false;
    }

    public int a() {
        return this.j;
    }

    public double b() {
        return this.k;
    }

    public double c() {
        return this.l;
    }

    public double d() {
        return this.m;
    }

    public GeofenceTransitionTypes e() {
        return this.n;
    }

    public GeofenceConfidence f() {
        return this.o;
    }

    public DwellTypes g() {
        return this.p;
    }

    public int h() {
        return this.q;
    }

    public String i() {
        return this.r;
    }

    public Bundle j() {
        return this.s;
    }

    public int k() {
        return this.t;
    }

    public boolean l() {
        return this.u;
    }

    public void a(double d2) {
        this.k = d2;
    }

    public void b(double d2) {
        this.l = d2;
    }

    public void c(double d2) {
        this.m = d2;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public void b(int i2) {
        this.n = GeofenceTransitionTypes.UNKNOWN;
        for (GeofenceTransitionTypes geofenceTransitionTypes : GeofenceTransitionTypes.values()) {
            if (geofenceTransitionTypes.getValue() == i2) {
                this.n = geofenceTransitionTypes;
                return;
            }
        }
    }

    public void c(int i2) {
        this.o = GeofenceConfidence.LOW;
        for (GeofenceConfidence geofenceConfidence : GeofenceConfidence.values()) {
            if (geofenceConfidence.getValue() == i2) {
                this.o = geofenceConfidence;
                return;
            }
        }
    }

    public void d(int i2) {
        this.p = DwellTypes.UNKNOWN;
        for (DwellTypes dwellTypes : DwellTypes.values()) {
            if (dwellTypes.getValue() == i2) {
                this.p = dwellTypes;
                return;
            }
        }
    }

    public void e(int i2) {
        this.q = i2;
    }

    public void f(int i2) {
        this.t = i2;
    }

    public void a(boolean z) {
        this.u = z;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        Parcel parcel2 = parcel;
        if (i) {
            String str = h;
            int i3 = this.j;
            double d2 = this.k;
            double d3 = this.l;
            double d4 = this.m;
            String name = this.n.name();
            String name2 = this.o.name();
            String name3 = this.p.name();
            int i4 = this.q;
            String str2 = this.r;
            int i5 = this.t;
            Log.v(str, "in GeofenceData: writeToParcel(); responsiveness is " + i3 + "; latitude is " + d2 + "; longitude is " + d3 + "; radius is " + d4 + "; transitionTypes is " + name + "; confidence is " + name2 + "; dwellTimeMask is " + name3 + "; dwellTime is " + i4 + "; AppTextData is " + str2 + "; Geofence id is " + i5);
        }
        Parcel parcel3 = parcel;
        parcel3.writeInt(this.j);
        parcel3.writeDouble(this.k);
        parcel3.writeDouble(this.l);
        parcel3.writeDouble(this.m);
        parcel3.writeString(this.n == null ? "" : this.n.name());
        parcel3.writeString(this.o == null ? "" : this.o.name());
        parcel3.writeString(this.p == null ? "" : this.p.name());
        parcel3.writeInt(this.q);
        parcel3.writeString(this.r);
        parcel3.writeBundle(this.s);
        parcel3.writeInt(this.t);
        parcel3.writeBoolean(this.u);
    }
}
