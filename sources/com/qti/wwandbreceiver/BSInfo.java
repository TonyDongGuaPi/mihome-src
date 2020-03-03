package com.qti.wwandbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class BSInfo implements Parcelable {
    public static final Parcelable.Creator<BSInfo> CREATOR = new Parcelable.Creator<BSInfo>() {
        /* renamed from: a */
        public BSInfo createFromParcel(Parcel parcel) {
            return new BSInfo(parcel);
        }

        /* renamed from: a */
        public BSInfo[] newArray(int i) {
            return new BSInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f8640a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public int describeContents() {
        return 0;
    }

    public BSInfo() {
    }

    private BSInfo(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8640a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
    }

    public void a(Parcel parcel) {
        this.f8640a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
    }
}
