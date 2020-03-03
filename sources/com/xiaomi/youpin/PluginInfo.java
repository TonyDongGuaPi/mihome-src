package com.xiaomi.youpin;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class PluginInfo implements Parcelable {
    public static final Parcelable.Creator<PluginInfo> CREATOR = new Parcelable.Creator<PluginInfo>() {
        /* renamed from: a */
        public PluginInfo createFromParcel(Parcel parcel) {
            return new PluginInfo(parcel);
        }

        /* renamed from: a */
        public PluginInfo[] newArray(int i) {
            return new PluginInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f23161a;
    public long b;
    public String c;
    public int d;
    public int e;
    public long f;
    public String g;
    public List<String> h = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public PluginInfo() {
    }

    protected PluginInfo(Parcel parcel) {
        this.f23161a = parcel.readString();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readLong();
        this.g = parcel.readString();
        this.h = parcel.createStringArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23161a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        parcel.writeString(this.g);
        parcel.writeStringList(this.h);
    }
}
