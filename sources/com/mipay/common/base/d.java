package com.mipay.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;

class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new e();

    /* renamed from: a  reason: collision with root package name */
    String f8114a;
    HashSet<String> b = new HashSet<>();
    boolean c;
    boolean d;
    String e;
    int f;

    d() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8114a);
        parcel.writeSerializable(this.b);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }
}
