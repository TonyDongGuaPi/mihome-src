package com.mipay.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;

final class e implements Parcelable.Creator<d> {
    e() {
    }

    /* renamed from: a */
    public d createFromParcel(Parcel parcel) {
        d dVar = new d();
        dVar.f8114a = parcel.readString();
        dVar.b = (HashSet) parcel.readSerializable();
        boolean z = false;
        dVar.c = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        dVar.d = z;
        dVar.e = parcel.readString();
        dVar.f = parcel.readInt();
        return dVar;
    }

    /* renamed from: a */
    public d[] newArray(int i) {
        return new d[i];
    }
}
