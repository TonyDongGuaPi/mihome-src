package com.mipay.common.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new k();

    /* renamed from: a  reason: collision with root package name */
    String f8117a;
    int b;
    int c;
    Bundle d;

    private j(Parcel parcel) {
        this.f8117a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt() != 0 ? parcel.readBundle() : null;
    }

    /* synthetic */ j(Parcel parcel, k kVar) {
        this(parcel);
    }

    public j(String str, int i, int i2, Bundle bundle) {
        this.f8117a = str;
        this.b = i;
        this.c = i2;
        this.d = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8117a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        if (this.d != null) {
            parcel.writeInt(1);
            this.d.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }
}
