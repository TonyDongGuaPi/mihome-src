package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public enum PluginDeviceInfoStatus implements Parcelable {
    INIT(0),
    WHITE_LIST(1),
    PREVIEW(2),
    RELEASE(3);
    
    public static final Parcelable.Creator<PluginDeviceInfoStatus> CREATOR = null;
    private int mValue;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<PluginDeviceInfoStatus>() {
            /* renamed from: a */
            public PluginDeviceInfoStatus createFromParcel(Parcel parcel) {
                return PluginDeviceInfoStatus.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public PluginDeviceInfoStatus[] newArray(int i) {
                return new PluginDeviceInfoStatus[i];
            }
        };
    }

    private PluginDeviceInfoStatus(int i) {
        this.mValue = i;
    }

    public static PluginDeviceInfoStatus valueOf(int i) {
        for (PluginDeviceInfoStatus pluginDeviceInfoStatus : values()) {
            if (i == pluginDeviceInfoStatus.getValue()) {
                return pluginDeviceInfoStatus;
            }
        }
        return INIT;
    }

    public int getValue() {
        return this.mValue;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
