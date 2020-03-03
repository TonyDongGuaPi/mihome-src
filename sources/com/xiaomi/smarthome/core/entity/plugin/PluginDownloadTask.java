package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;

public class PluginDownloadTask implements Parcelable {
    public static final Parcelable.Creator<PluginDownloadTask> CREATOR = new Parcelable.Creator<PluginDownloadTask>() {
        /* renamed from: a */
        public PluginDownloadTask createFromParcel(Parcel parcel) {
            return new PluginDownloadTask(parcel);
        }

        /* renamed from: a */
        public PluginDownloadTask[] newArray(int i) {
            return new PluginDownloadTask[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public long f13989a;
    public int b;
    public String c;

    public int describeContents() {
        return 0;
    }

    public PluginDownloadTask() {
    }

    protected PluginDownloadTask(Parcel parcel) {
        this.f13989a = parcel.readLong();
        this.b = parcel.readInt();
        this.c = parcel.readString();
    }

    public long a() {
        return this.f13989a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void a(PluginDownloadTask pluginDownloadTask) {
        pluginDownloadTask.f13989a = this.f13989a;
        pluginDownloadTask.b = this.b;
        pluginDownloadTask.c = this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f13989a);
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
    }
}
