package com.qti.wifidbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class APInfo implements Parcelable {
    public static final Parcelable.Creator<APInfo> CREATOR = new Parcelable.Creator<APInfo>() {
        /* renamed from: a */
        public APInfo createFromParcel(Parcel parcel) {
            return new APInfo(parcel);
        }

        /* renamed from: a */
        public APInfo[] newArray(int i) {
            return new APInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f8635a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public byte[] g;

    public int describeContents() {
        return 0;
    }

    public APInfo() {
    }

    private APInfo(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8635a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByteArray(this.g);
    }

    public void a(Parcel parcel) {
        this.f8635a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.createByteArray();
    }
}
