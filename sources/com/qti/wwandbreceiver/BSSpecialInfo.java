package com.qti.wwandbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class BSSpecialInfo implements Parcelable {
    public static final Parcelable.Creator<BSSpecialInfo> CREATOR = new Parcelable.Creator<BSSpecialInfo>() {
        /* renamed from: a */
        public BSSpecialInfo createFromParcel(Parcel parcel) {
            return new BSSpecialInfo(parcel);
        }

        /* renamed from: a */
        public BSSpecialInfo[] newArray(int i) {
            return new BSSpecialInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f8642a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public int describeContents() {
        return 0;
    }

    public BSSpecialInfo() {
    }

    private BSSpecialInfo(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8642a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
    }

    public void a(Parcel parcel) {
        this.f8642a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
    }
}
