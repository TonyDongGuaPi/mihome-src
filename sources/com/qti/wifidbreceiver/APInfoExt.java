package com.qti.wifidbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class APInfoExt implements Parcelable {
    public static final Parcelable.Creator<APInfoExt> CREATOR = new Parcelable.Creator<APInfoExt>() {
        /* renamed from: a */
        public APInfoExt createFromParcel(Parcel parcel) {
            return new APInfoExt(parcel);
        }

        /* renamed from: a */
        public APInfoExt[] newArray(int i) {
            return new APInfoExt[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f8636a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public byte[] g;
    public int h;
    public int i;

    public int describeContents() {
        return 0;
    }

    public APInfoExt() {
    }

    private APInfoExt(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f8636a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByteArray(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
    }

    public void a(Parcel parcel) {
        this.f8636a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.createByteArray();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
    }
}
