package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdatePluginResult implements Parcelable {
    public static final Parcelable.Creator<UpdatePluginResult> CREATOR = new Parcelable.Creator<UpdatePluginResult>() {
        /* renamed from: a */
        public UpdatePluginResult createFromParcel(Parcel parcel) {
            return new UpdatePluginResult(parcel);
        }

        /* renamed from: a */
        public UpdatePluginResult[] newArray(int i) {
            return new UpdatePluginResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13995a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public int k;
    public PluginRecord l;
    public float m;
    public PluginUpdateInfo n;

    public int describeContents() {
        return 0;
    }

    public UpdatePluginResult() {
    }

    protected UpdatePluginResult(Parcel parcel) {
        this.k = parcel.readInt();
        this.l = (PluginRecord) parcel.readParcelable(PluginRecord.class.getClassLoader());
        this.m = parcel.readFloat();
        this.n = (PluginUpdateInfo) parcel.readParcelable(PluginUpdateInfo.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.k);
        parcel.writeParcelable(this.l, i2);
        parcel.writeFloat(this.m);
        parcel.writeParcelable(this.n, i2);
    }
}
