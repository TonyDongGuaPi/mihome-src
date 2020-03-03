package com.mipay.common.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

class h implements Parcelable {
    public static final Parcelable.Creator<h> CREATOR = new i();

    /* renamed from: a  reason: collision with root package name */
    int f8116a;
    Bundle b;
    String c;
    boolean d;

    public h(int i, Bundle bundle, String str, boolean z) {
        this.f8116a = i;
        this.b = bundle;
        this.c = str;
        this.d = z;
    }

    private h(Parcel parcel) {
        this.f8116a = parcel.readInt();
        this.b = parcel.readInt() != 0 ? parcel.readBundle() : null;
        this.c = parcel.readString();
        this.d = parcel.readByte() != 0;
    }

    /* synthetic */ h(Parcel parcel, i iVar) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8116a);
        if (this.b != null) {
            parcel.writeInt(1);
            this.b.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : 0);
    }
}
