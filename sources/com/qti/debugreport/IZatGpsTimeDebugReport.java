package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatGpsTimeDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatGpsTimeDebugReport> CREATOR = new Parcelable.Creator<IZatGpsTimeDebugReport>() {
        /* renamed from: a */
        public IZatGpsTimeDebugReport createFromParcel(Parcel parcel) {
            return new IZatGpsTimeDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatGpsTimeDebugReport[] newArray(int i) {
            return new IZatGpsTimeDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8580a = "IZatGpsTimeReport";
    private static final boolean b = Log.isLoggable(f8580a, 2);
    private IZatUtcSpec c;
    private IZatUtcSpec d;
    private int e;
    private long f;
    private boolean g;
    private IZatTimeSource h;
    private int i;
    private int j;
    private int k;

    public int describeContents() {
        return 0;
    }

    public enum IZatTimeSource {
        TIME_SOURCE_ESTIMATE_INVALID(0),
        TIME_SOURCE_ESTIMATE_NETWORK_TIME_TRANSFER(1),
        TIME_SOURCE_ESTIMATE_NETWORK_TIME_TAGGING(2),
        TIME_SOURCE_ESTIMATE_EXTERNAL_INPUT(3),
        TIME_SOURCE_ESTIMATE_TOW_DECODE(4),
        TIME_SOURCE_ESTIMATE_TOW_CONFIRMED(5),
        TIME_SOURCE_ESTIMATE_TOW_AND_WEEK_CONFIRMED(6),
        TIME_SOURCE_ESTIMATE_TIME_ALIGNMENT(7),
        TIME_SOURCE_ESTIMATE_NAV_SOLUTION(8),
        TIME_SOURCE_ESTIMATE_SOLVE_FOR_TIME(9),
        TIME_SOURCE_ESTIMATE_GLO_TOD_DECODE(10),
        TIME_SOURCE_ESTIMATE_TIME_CONVERSION(11),
        TIME_SOURCE_ESTIMATE_SLEEP_CLOCK(12),
        TIME_SOURCE_ESTIMATE_SLEEP_CLOCK_TIME_TRANSFER(13),
        TIME_SOURCE_ESTIMATE_UNKNOWN(14),
        TIME_SOURCE_ESTIMATE_WCDMA_SLEEP_TIME_TAGGING(15),
        TIME_SOURCE_ESTIMATE_GSM_SLEEP_TIME_TAGGING(16),
        TIME_SOURCE_ESTIMATE_GAL_TOW_DECODE(17),
        TIME_SOURCE_ESTIMATE_BDS_SOW_DECODE(18),
        TIME_SOURCE_ESTIMATE_QZSS_TOW_DECODE(19);
        
        private final int mValue;

        private IZatTimeSource(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public IZatGpsTimeDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, long j2, boolean z, int i3, int i4, int i5, int i6) {
        this.c = iZatUtcSpec;
        this.d = iZatUtcSpec2;
        this.e = i2;
        this.f = j2;
        this.g = z;
        try {
            this.h = IZatTimeSource.values()[i3];
        } catch (ArrayIndexOutOfBoundsException unused) {
            this.h = IZatTimeSource.TIME_SOURCE_ESTIMATE_INVALID;
        }
        this.i = i4;
        this.j = i5;
        this.k = i6;
    }

    public IZatGpsTimeDebugReport(Parcel parcel) {
        this.c = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.d = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.e = parcel.readInt();
        this.f = parcel.readLong();
        this.g = parcel.readInt() != 1 ? false : true;
        this.h = IZatTimeSource.values()[parcel.readInt()];
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
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

    public boolean e() {
        return this.g;
    }

    public IZatTimeSource f() {
        return this.h;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    public int i() {
        return this.k;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.c, 0);
        parcel.writeParcelable(this.d, 0);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h.getValue());
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
    }
}
