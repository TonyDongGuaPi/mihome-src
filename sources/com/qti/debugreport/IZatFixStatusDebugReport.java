package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatFixStatusDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatFixStatusDebugReport> CREATOR = new Parcelable.Creator<IZatFixStatusDebugReport>() {
        /* renamed from: a */
        public IZatFixStatusDebugReport createFromParcel(Parcel parcel) {
            return new IZatFixStatusDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatFixStatusDebugReport[] newArray(int i) {
            return new IZatFixStatusDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8579a = "IZatFixStatus";
    private static final boolean b = Log.isLoggable(f8579a, 2);
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 4;
    private static final int f = 8;
    private int g;
    private IzatFixStatus h;
    private long i;
    private IZatUtcSpec j;
    private IZatUtcSpec k;

    public int describeContents() {
        return 0;
    }

    public enum IzatFixStatus {
        FINAL_FIX_SUCCESSFUL(0),
        TOO_FEW_SV(1),
        HEPE_CHECK_FAIL(2),
        VERY_LOW_RELAIBILITY_FIX(3);
        
        private final int mFixStatus;

        private IzatFixStatus(int i) {
            this.mFixStatus = i;
        }

        public int getValue() {
            return this.mFixStatus;
        }
    }

    public IZatFixStatusDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i2, long j2) {
        this.j = iZatUtcSpec;
        this.k = iZatUtcSpec2;
        this.g = i2;
        if ((this.g & 1) != 0) {
            this.h = IzatFixStatus.values()[0];
        } else if ((this.g & 2) != 0) {
            this.h = IzatFixStatus.values()[1];
        } else if ((this.g & 4) != 0) {
            this.h = IzatFixStatus.values()[2];
        } else if ((this.g & 8) != 0) {
            this.h = IzatFixStatus.values()[3];
        }
        this.i = j2;
    }

    public IZatFixStatusDebugReport(Parcel parcel) {
        this.j = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.k = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.g = parcel.readInt();
        if ((this.g & 1) != 0) {
            this.h = IzatFixStatus.values()[0];
        } else if ((this.g & 2) != 0) {
            this.h = IzatFixStatus.values()[1];
        } else if ((this.g & 4) != 0) {
            this.h = IzatFixStatus.values()[2];
        } else if ((this.g & 8) != 0) {
            this.h = IzatFixStatus.values()[3];
        }
        this.i = parcel.readLong();
    }

    public IzatFixStatus a() {
        return this.h;
    }

    public long b() {
        return this.i;
    }

    public IZatUtcSpec c() {
        return this.j;
    }

    public IZatUtcSpec d() {
        return this.k;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.j, 0);
        parcel.writeParcelable(this.k, 0);
        parcel.writeInt(this.g);
        parcel.writeLong(this.i);
    }
}
