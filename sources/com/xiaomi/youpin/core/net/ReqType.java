package com.xiaomi.youpin.core.net;

import android.os.Parcel;
import android.os.Parcelable;

public enum ReqType implements Parcelable {
    AUTH,
    OPEN,
    OPEN_ST;
    
    public static final Parcelable.Creator<ReqType> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<ReqType>() {
            /* renamed from: a */
            public ReqType createFromParcel(Parcel parcel) {
                return ReqType.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public ReqType[] newArray(int i) {
                return new ReqType[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
