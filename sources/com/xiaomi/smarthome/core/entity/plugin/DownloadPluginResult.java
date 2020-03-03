package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadPluginResult implements Parcelable {
    public static final Parcelable.Creator<DownloadPluginResult> CREATOR = new Parcelable.Creator<DownloadPluginResult>() {
        /* renamed from: a */
        public DownloadPluginResult createFromParcel(Parcel parcel) {
            return new DownloadPluginResult(parcel);
        }

        /* renamed from: a */
        public DownloadPluginResult[] newArray(int i) {
            return new DownloadPluginResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13984a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public int h;
    public PluginRecord i;
    public PluginDownloadTask j;
    public float k;

    public int describeContents() {
        return 0;
    }

    public DownloadPluginResult() {
    }

    protected DownloadPluginResult(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = (PluginRecord) parcel.readParcelable(PluginRecord.class.getClassLoader());
        this.j = (PluginDownloadTask) parcel.readParcelable(PluginDownloadTask.class.getClassLoader());
        this.k = parcel.readFloat();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.h);
        parcel.writeParcelable(this.i, i2);
        parcel.writeParcelable(this.j, i2);
        parcel.writeFloat(this.k);
    }
}
