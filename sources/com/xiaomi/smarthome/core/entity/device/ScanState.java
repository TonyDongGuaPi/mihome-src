package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;

public enum ScanState implements Parcelable {
    START_SUCCESS,
    LOAD_CACHE_SUCCESS,
    SYNC_SERVER_SUCCESS,
    SYNC_SERVER_FAILED,
    SCAN_LOCAL_SUCCESS;
    
    public static final Parcelable.Creator<ScanState> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    static {
        CREATOR = new Parcelable.Creator<ScanState>() {
            /* renamed from: a */
            public ScanState createFromParcel(Parcel parcel) {
                return ScanState.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public ScanState[] newArray(int i) {
                return new ScanState[i];
            }
        };
    }
}
