package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdatePluginConfigResult implements Parcelable {
    public static final Parcelable.Creator<UpdatePluginConfigResult> CREATOR = new Parcelable.Creator<UpdatePluginConfigResult>() {
        /* renamed from: a */
        public UpdatePluginConfigResult createFromParcel(Parcel parcel) {
            return new UpdatePluginConfigResult(parcel);
        }

        /* renamed from: a */
        public UpdatePluginConfigResult[] newArray(int i) {
            return new UpdatePluginConfigResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public boolean f13994a;
    public boolean b;

    public int describeContents() {
        return 0;
    }

    public UpdatePluginConfigResult() {
    }

    protected UpdatePluginConfigResult(Parcel parcel) {
        boolean z = false;
        this.f13994a = parcel.readByte() != 0;
        this.b = parcel.readByte() != 0 ? true : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f13994a ? (byte) 1 : 0);
        parcel.writeByte(this.b ? (byte) 1 : 0);
    }
}
