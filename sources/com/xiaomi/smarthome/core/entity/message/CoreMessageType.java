package com.xiaomi.smarthome.core.entity.message;

import android.os.Parcel;
import android.os.Parcelable;

public enum CoreMessageType implements Parcelable {
    UNKNOWN,
    UPDATE_DEVICE_LIST,
    UPNP_DEVICE_CHANGED,
    UPNP_DEVICE_EVENT;
    
    public static final Parcelable.Creator<CoreMessageType> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<CoreMessageType>() {
            /* renamed from: a */
            public CoreMessageType createFromParcel(Parcel parcel) {
                return CoreMessageType.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public CoreMessageType[] newArray(int i) {
                return new CoreMessageType[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
