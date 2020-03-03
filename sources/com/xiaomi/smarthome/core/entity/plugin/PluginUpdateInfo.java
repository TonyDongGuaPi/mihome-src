package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class PluginUpdateInfo implements Parcelable {
    public static final Parcelable.Creator<PluginUpdateInfo> CREATOR = new Parcelable.Creator<PluginUpdateInfo>() {
        /* renamed from: a */
        public PluginUpdateInfo createFromParcel(Parcel parcel) {
            return new PluginUpdateInfo(parcel);
        }

        /* renamed from: a */
        public PluginUpdateInfo[] newArray(int i) {
            return new PluginUpdateInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13992a;
    private long b;
    private long c;
    private boolean d = false;
    private int e = 0;
    private String f;
    private String g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public PluginUpdateInfo() {
    }

    protected PluginUpdateInfo(Parcel parcel) {
        boolean z = false;
        this.f13992a = parcel.readString();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readByte() != 0 ? true : z;
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
    }

    public synchronized String a() {
        return this.f13992a;
    }

    public synchronized void a(String str) {
        this.f13992a = str;
    }

    public synchronized long b() {
        return this.b;
    }

    public synchronized void a(long j) {
        this.b = j;
    }

    public synchronized long c() {
        return this.c;
    }

    public synchronized void b(long j) {
        this.c = j;
    }

    public synchronized boolean d() {
        return this.d;
    }

    public synchronized void a(boolean z) {
        this.d = z;
    }

    public synchronized int e() {
        return this.e;
    }

    public synchronized void a(int i) {
        this.e = i;
    }

    public synchronized String f() {
        return this.f;
    }

    public synchronized void b(String str) {
        this.f = str;
    }

    public synchronized String g() {
        return this.g;
    }

    public synchronized void c(String str) {
        this.g = str;
    }

    public synchronized String h() {
        return this.h;
    }

    public synchronized void d(String str) {
        this.h = str;
    }

    public synchronized String i() {
        return "PluginUpdateInfo[" + "model:" + this.f13992a + " " + "pluginId:" + this.b + " " + "packageId:" + this.c + " " + "force:" + this.d + " " + "version:" + this.e + " " + "changeLog:" + this.f + " " + "downloadUrl:" + this.g + " " + "packageType:" + this.h + " " + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13992a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
    }
}
