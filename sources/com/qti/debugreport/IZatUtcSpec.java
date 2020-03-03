package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class IZatUtcSpec implements Parcelable {
    public static final Parcelable.Creator<IZatUtcSpec> CREATOR = new Parcelable.Creator<IZatUtcSpec>() {
        /* renamed from: a */
        public IZatUtcSpec createFromParcel(Parcel parcel) {
            return new IZatUtcSpec(parcel);
        }

        /* renamed from: a */
        public IZatUtcSpec[] newArray(int i) {
            return new IZatUtcSpec[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static String f8585a = "IZatUtcSpec";
    private static final boolean b = Log.isLoggable(f8585a, 2);
    private long c;
    private long d;

    public int describeContents() {
        return 0;
    }

    public IZatUtcSpec(long j, long j2) {
        this.c = j;
        this.d = j2;
    }

    public IZatUtcSpec(Parcel parcel) {
        this.c = parcel.readLong();
        this.d = parcel.readLong();
    }

    public long a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
    }
}
