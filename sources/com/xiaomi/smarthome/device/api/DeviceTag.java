package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceTag implements Parcelable {
    public static final Parcelable.Creator<DeviceTag> CREATOR = new Parcelable.Creator<DeviceTag>() {
        public DeviceTag createFromParcel(Parcel parcel) {
            return new DeviceTag(parcel);
        }

        public DeviceTag[] newArray(int i) {
            return new DeviceTag[i];
        }
    };
    public static final int DEVICE_TAG_CATEGORY = 0;
    public static final int DEVICE_TAG_CUSTOM = 4;
    public static final int DEVICE_TAG_ROUTER = 2;
    public String infoJson;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        writeString(this.infoJson, parcel);
    }

    public DeviceTag() {
    }

    private DeviceTag(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: package-private */
    public void readFromParcel(Parcel parcel) {
        this.infoJson = parcel.readString();
    }

    /* access modifiers changed from: package-private */
    public void writeString(String str, Parcel parcel) {
        if (str == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(str);
        }
    }
}
