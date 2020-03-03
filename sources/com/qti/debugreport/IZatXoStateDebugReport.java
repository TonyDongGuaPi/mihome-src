package com.qti.debugreport;

import android.os.Parcel;
import android.os.Parcelable;

public class IZatXoStateDebugReport implements Parcelable {
    public static final Parcelable.Creator<IZatXoStateDebugReport> CREATOR = new Parcelable.Creator<IZatXoStateDebugReport>() {
        /* renamed from: a */
        public IZatXoStateDebugReport createFromParcel(Parcel parcel) {
            return new IZatXoStateDebugReport(parcel);
        }

        /* renamed from: a */
        public IZatXoStateDebugReport[] newArray(int i) {
            return new IZatXoStateDebugReport[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private IZatUtcSpec f8592a;
    private IZatUtcSpec b;
    private IZatXoState c;

    public int describeContents() {
        return 0;
    }

    public IZatXoStateDebugReport(IZatUtcSpec iZatUtcSpec, IZatUtcSpec iZatUtcSpec2, int i) {
        this.f8592a = iZatUtcSpec;
        this.b = iZatUtcSpec2;
        try {
            this.c = IZatXoState.values()[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            this.c = IZatXoState.FAILED;
        }
    }

    public IZatXoStateDebugReport(Parcel parcel) {
        this.f8592a = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.b = (IZatUtcSpec) parcel.readParcelable(IZatUtcSpec.class.getClassLoader());
        this.c = IZatXoState.values()[parcel.readInt()];
    }

    public IZatUtcSpec a() {
        return this.f8592a;
    }

    public IZatUtcSpec b() {
        return this.b;
    }

    public IZatXoState c() {
        return this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f8592a, 0);
        parcel.writeParcelable(this.b, 0);
        parcel.writeInt(this.c.getValue());
    }

    public enum IZatXoState {
        FAILED(0),
        NOT_CAL(1),
        FAC1(2),
        DEFAULT(3),
        WIDE_BINS(4),
        COARSE(5),
        IFC(6),
        FINE(7),
        FT1(8),
        OLD_RGS(9),
        INTERP(10),
        FT2(11),
        EXACT(12),
        RGS(13),
        RGS_RECENT(14),
        VCO_LAST(15);
        
        private final int mValue;

        private IZatXoState(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
