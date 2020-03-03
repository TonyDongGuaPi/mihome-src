package com.xiaomi.youpin.core.net;

import android.os.Parcel;
import android.os.Parcelable;

public enum Crypto implements Parcelable {
    NONE,
    AES,
    RC4,
    HTTPS;
    
    public static final Parcelable.Creator<Crypto> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<Crypto>() {
            /* renamed from: a */
            public Crypto createFromParcel(Parcel parcel) {
                return Crypto.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public Crypto[] newArray(int i) {
                return new Crypto[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
