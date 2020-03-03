package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallPluginResult implements Parcelable {
    public static final Parcelable.Creator<InstallPluginResult> CREATOR = new Parcelable.Creator<InstallPluginResult>() {
        /* renamed from: a */
        public InstallPluginResult createFromParcel(Parcel parcel) {
            return new InstallPluginResult(parcel);
        }

        /* renamed from: a */
        public InstallPluginResult[] newArray(int i) {
            return new InstallPluginResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13985a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public int e;
    public PluginRecord f;

    public int describeContents() {
        return 0;
    }

    public InstallPluginResult() {
    }

    protected InstallPluginResult(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = (PluginRecord) parcel.readParcelable(PluginRecord.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeParcelable(this.f, i);
    }
}
