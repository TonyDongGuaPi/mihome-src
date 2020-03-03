package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import java.util.ArrayList;
import java.util.List;

public class PluginPackageInfo implements Parcelable {
    public static final Parcelable.Creator<PluginPackageInfo> CREATOR = new Parcelable.Creator<PluginPackageInfo>() {
        /* renamed from: a */
        public PluginPackageInfo createFromParcel(Parcel parcel) {
            return new PluginPackageInfo(parcel);
        }

        /* renamed from: a */
        public PluginPackageInfo[] newArray(int i) {
            return new PluginPackageInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private long f13990a;
    private long b;
    private String c;
    private int d;
    private int e;
    private long f;
    private String g;
    private String h;
    private String i;
    private List<String> j;
    private boolean k;
    private boolean l = true;
    private long m;

    public int describeContents() {
        return 0;
    }

    public PluginPackageInfo() {
    }

    public PluginPackageInfo(Parcel parcel) {
        a(parcel);
    }

    public synchronized long a() {
        return this.f13990a;
    }

    public synchronized void a(long j2) {
        this.f13990a = j2;
    }

    public synchronized long b() {
        return this.b;
    }

    public synchronized void b(long j2) {
        this.b = j2;
    }

    public synchronized boolean c() {
        return this.k;
    }

    public synchronized void a(boolean z) {
        this.k = z;
    }

    public synchronized void b(boolean z) {
        this.l = z;
    }

    public synchronized boolean d() {
        return this.l;
    }

    public synchronized long e() {
        return this.m;
    }

    public synchronized void c(long j2) {
        this.m = j2;
    }

    public synchronized String f() {
        if (TextUtils.isEmpty(this.c)) {
            return "";
        }
        return this.c;
    }

    public synchronized void a(String str) {
        this.c = str;
    }

    public synchronized int g() {
        return this.d;
    }

    public synchronized void a(int i2) {
        this.d = i2;
    }

    public synchronized String h() {
        if (TextUtils.isEmpty(this.i)) {
            return "";
        }
        return this.i;
    }

    public synchronized void b(String str) {
        this.i = str;
    }

    public synchronized String i() {
        if (TextUtils.isEmpty(this.g)) {
            return "";
        }
        return this.g;
    }

    public synchronized void c(String str) {
        this.g = str;
    }

    public synchronized int j() {
        return this.e;
    }

    public synchronized void b(int i2) {
        this.e = i2;
    }

    public synchronized long k() {
        return this.f;
    }

    public synchronized void d(long j2) {
        this.f = j2;
    }

    public synchronized String l() {
        return this.h;
    }

    public synchronized void d(String str) {
        this.h = str;
    }

    public synchronized List<String> m() {
        return this.j;
    }

    public synchronized void a(List<String> list) {
        this.j = list;
    }

    public synchronized boolean n() {
        if (TextUtils.isEmpty(this.g) || !this.g.equalsIgnoreCase("apk")) {
            return false;
        }
        return true;
    }

    public synchronized boolean o() {
        if (TextUtils.isEmpty(this.g) || !this.g.equalsIgnoreCase(PluginManager.h)) {
            return false;
        }
        return true;
    }

    public synchronized boolean p() {
        if (TextUtils.isEmpty(this.g) || !this.g.equalsIgnoreCase(PluginManager.i)) {
            return false;
        }
        return true;
    }

    public synchronized boolean q() {
        if (TextUtils.isEmpty(this.g) || !this.g.equalsIgnoreCase("rn")) {
            return false;
        }
        return true;
    }

    public synchronized String r() {
        return "PluginPackageInfo[" + "pluginId:" + this.f13990a + " " + "packageId:" + this.b + " " + "packagePath:" + this.c + " " + "version:" + this.d + " " + "minApiLevel:" + this.e + " " + "developerId:" + this.f + " " + "packageType:" + this.g + " " + "platform:" + this.h + " " + "packageName:" + this.i + " " + "modelList:" + this.j + " " + "packageLastModified" + this.m + " " + "mIsSupportWidget" + this.k + " " + "mIsSupportAppAV" + this.l + " " + Operators.ARRAY_END_STR;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Parcel parcel) {
        if (str == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(str);
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f13990a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        a(this.g, parcel);
        a(this.h, parcel);
        a(this.i, parcel);
        if (this.j == null) {
            this.j = new ArrayList();
        }
        parcel.writeStringList(this.j);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeLong(this.m);
    }

    /* access modifiers changed from: package-private */
    public void a(Parcel parcel) {
        this.f13990a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readLong();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = new ArrayList();
        parcel.readStringList(this.j);
        boolean z = false;
        this.k = parcel.readInt() != 0;
        if (parcel.readInt() != 0) {
            z = true;
        }
        this.l = z;
        this.m = parcel.readLong();
    }
}
