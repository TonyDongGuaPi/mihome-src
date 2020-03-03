package com.qti.wifidbprovider;

import android.os.Parcel;
import android.os.Parcelable;

public final class APScan implements Parcelable {
    public static final Parcelable.Creator<APScan> CREATOR = new Parcelable.Creator<APScan>() {
        /* renamed from: a */
        public APScan createFromParcel(Parcel parcel) {
            return new APScan(parcel);
        }

        /* renamed from: a */
        public APScan[] newArray(int i) {
            return new APScan[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f8634a;
    public float b;
    public int c;
    public byte[] d;
    public int e;

    public int describeContents() {
        return 0;
    }

    public APScan() {
    }

    private APScan(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8634a);
        parcel.writeFloat(this.b);
        parcel.writeInt(this.c);
        parcel.writeByteArray(this.d);
        parcel.writeInt(this.e);
    }

    public void a(Parcel parcel) {
        this.f8634a = parcel.readString();
        this.b = parcel.readFloat();
        this.c = parcel.readInt();
        this.d = parcel.createByteArray();
        this.e = parcel.readInt();
    }
}
