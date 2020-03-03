package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;

public class IZatErrorRecoveryReport implements Parcelable {
    public static final Parcelable.Creator<IZatErrorRecoveryReport> CREATOR = new Parcelable.Creator<IZatErrorRecoveryReport>() {
        /* renamed from: a */
        public IZatErrorRecoveryReport createFromParcel(Parcel parcel) {
            return new IZatErrorRecoveryReport(parcel);
        }

        /* renamed from: a */
        public IZatErrorRecoveryReport[] newArray(int i) {
            return new IZatErrorRecoveryReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private IZatUtcSpec f8578a;
    private IZatUtcSpec b;

    public int describeContents() {
        return 0;
    }

    public IZatErrorRecoveryReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2) {
        this.f8578a = iZatUtcSpec;
        this.b = iZatUtcSpec2;
    }

    public IZatErrorRecoveryReport(Parcel parcel) {
        this.f8578a = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.b = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
    }

    public IZatUtcSpec a() {
        return this.f8578a;
    }

    public IZatUtcSpec b() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f8578a, 0);
        parcel.writeParcelable(this.b, 0);
    }
}
