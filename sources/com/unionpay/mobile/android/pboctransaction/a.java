package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable;

final class a implements Parcelable.Creator<AppIdentification> {
    a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        AppIdentification appIdentification = new AppIdentification((byte) 0);
        String unused = appIdentification.f9641a = parcel.readString();
        String unused2 = appIdentification.b = parcel.readString();
        return appIdentification;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AppIdentification[i];
    }
}
