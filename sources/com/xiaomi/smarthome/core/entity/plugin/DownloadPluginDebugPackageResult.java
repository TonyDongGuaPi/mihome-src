package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadPluginDebugPackageResult implements Parcelable {
    public static final Parcelable.Creator<DownloadPluginDebugPackageResult> CREATOR = new Parcelable.Creator<DownloadPluginDebugPackageResult>() {
        /* renamed from: a */
        public DownloadPluginDebugPackageResult createFromParcel(Parcel parcel) {
            return new DownloadPluginDebugPackageResult(parcel);
        }

        /* renamed from: a */
        public DownloadPluginDebugPackageResult[] newArray(int i) {
            return new DownloadPluginDebugPackageResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13983a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public int e;
    public String f;

    public int describeContents() {
        return 0;
    }

    public DownloadPluginDebugPackageResult() {
    }

    protected DownloadPluginDebugPackageResult(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
    }
}
