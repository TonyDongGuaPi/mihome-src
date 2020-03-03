package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdateAllPluginResult implements Parcelable {
    public static final Parcelable.Creator<UpdateAllPluginResult> CREATOR = new Parcelable.Creator<UpdateAllPluginResult>() {
        /* renamed from: a */
        public UpdateAllPluginResult createFromParcel(Parcel parcel) {
            return new UpdateAllPluginResult(parcel);
        }

        /* renamed from: a */
        public UpdateAllPluginResult[] newArray(int i) {
            return new UpdateAllPluginResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13993a = 1;
    public static final int b = 2;
    public int c;

    public int describeContents() {
        return 0;
    }

    public UpdateAllPluginResult() {
    }

    protected UpdateAllPluginResult(Parcel parcel) {
        this.c = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
    }
}
