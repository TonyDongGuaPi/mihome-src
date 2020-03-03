package com.qti.wifidbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class APLocationData implements Parcelable {
    public static final Parcelable.Creator<APLocationData> CREATOR = new Parcelable.Creator<APLocationData>() {
        /* renamed from: a */
        public APLocationData createFromParcel(Parcel parcel) {
            return new APLocationData(parcel);
        }

        /* renamed from: a */
        public APLocationData[] newArray(int i) {
            return new APLocationData[i];
        }
    };
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 4;

    /* renamed from: a  reason: collision with root package name */
    public String f8637a;
    public float b;
    public float c;
    public float d;
    public float e;
    public int f;
    public int k;

    public int describeContents() {
        return 0;
    }

    public APLocationData() {
    }

    private APLocationData(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f8637a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.k);
    }

    public void a(Parcel parcel) {
        this.f8637a = parcel.readString();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.f = parcel.readInt();
        this.k = parcel.readInt();
    }
}
