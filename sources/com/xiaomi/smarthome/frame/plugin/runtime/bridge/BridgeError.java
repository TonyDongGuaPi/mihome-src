package com.xiaomi.smarthome.frame.plugin.runtime.bridge;

import android.os.Parcel;
import android.os.Parcelable;

public class BridgeError implements Parcelable {
    public static final Parcelable.Creator<BridgeError> CREATOR = new Parcelable.Creator<BridgeError>() {
        public BridgeError createFromParcel(Parcel parcel) {
            return new BridgeError(parcel);
        }

        public BridgeError[] newArray(int i) {
            return new BridgeError[i];
        }
    };
    private int mCode;
    private String mDetail;

    public int describeContents() {
        return 0;
    }

    public BridgeError(int i, String str) {
        this.mCode = i;
        this.mDetail = str;
    }

    protected BridgeError(Parcel parcel) {
        this.mCode = parcel.readInt();
        this.mDetail = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCode);
        parcel.writeString(this.mDetail);
    }

    public final int getCode() {
        return this.mCode;
    }

    public final String getDetail() {
        return this.mDetail;
    }
}
