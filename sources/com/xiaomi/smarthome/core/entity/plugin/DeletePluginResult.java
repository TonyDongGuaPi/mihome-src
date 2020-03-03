package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class DeletePluginResult implements Parcelable {
    public static final Parcelable.Creator<DeletePluginResult> CREATOR = new Parcelable.Creator<DeletePluginResult>() {
        /* renamed from: a */
        public DeletePluginResult createFromParcel(Parcel parcel) {
            return new DeletePluginResult(parcel);
        }

        /* renamed from: a */
        public DeletePluginResult[] newArray(int i) {
            return new DeletePluginResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13982a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public int d;
    public PluginRecord e;
    public float f;

    public int describeContents() {
        return 0;
    }

    public DeletePluginResult() {
    }

    protected DeletePluginResult(Parcel parcel) {
        this.d = parcel.readInt();
        this.e = (PluginRecord) parcel.readParcelable(PluginRecord.class.getClassLoader());
        this.f = parcel.readFloat();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeParcelable(this.e, i);
        parcel.writeFloat(this.f);
    }
}
