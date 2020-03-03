package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.core.entity.Error;

public class PluginError extends Error {
    public static final Parcelable.Creator<PluginError> CREATOR = new Parcelable.Creator<PluginError>() {
        /* renamed from: a */
        public PluginError createFromParcel(Parcel parcel) {
            return new PluginError(parcel);
        }

        /* renamed from: a */
        public PluginError[] newArray(int i) {
            return new PluginError[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public PluginError(int i, String str) {
        super(i, str);
    }

    protected PluginError(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
