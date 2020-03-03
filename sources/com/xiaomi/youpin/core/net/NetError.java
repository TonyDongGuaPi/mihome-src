package com.xiaomi.youpin.core.net;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.youpin.core.Error;

public class NetError extends Error {
    public static final Parcelable.Creator<NetError> CREATOR = new Parcelable.Creator<NetError>() {
        /* renamed from: a */
        public NetError createFromParcel(Parcel parcel) {
            return new NetError(parcel);
        }

        /* renamed from: a */
        public NetError[] newArray(int i) {
            return new NetError[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public NetError(int i, String str) {
        super(i, str);
    }

    protected NetError(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
