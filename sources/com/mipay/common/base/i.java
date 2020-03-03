package com.mipay.common.base;

import android.os.Parcel;
import android.os.Parcelable;

final class i implements Parcelable.Creator<h> {
    i() {
    }

    /* renamed from: a */
    public h createFromParcel(Parcel parcel) {
        return new h(parcel, (i) null);
    }

    /* renamed from: a */
    public h[] newArray(int i) {
        return new h[i];
    }
}
