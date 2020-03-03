package com.mipay.common.base;

import android.os.Parcel;
import android.os.Parcelable;

final class k implements Parcelable.Creator<j> {
    k() {
    }

    /* renamed from: a */
    public j createFromParcel(Parcel parcel) {
        return new j(parcel, (k) null);
    }

    /* renamed from: a */
    public j[] newArray(int i) {
        return new j[i];
    }
}
