package com.xiaomi.router.miio.miioplugin;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.api.DeviceStat;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceStatus implements Parcelable {
    public static final Parcelable.Creator<DeviceStatus> CREATOR = new Parcelable.Creator<DeviceStatus>() {
        /* renamed from: a */
        public DeviceStatus createFromParcel(Parcel parcel) {
            return new DeviceStatus(parcel);
        }

        /* renamed from: a */
        public DeviceStatus[] newArray(int i) {
            return new DeviceStatus[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13050a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public String A;
    public JSONObject B;
    public String C;
    public Bundle D;
    public int E;
    public String F;
    public int G;
    public int H;
    public String I;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public int l;
    public int m;
    public String n;
    public String o;
    public int p;
    public double q;
    public double r;
    public String s;
    public long t;
    public int u;
    public int v;
    public boolean w;
    public int x;
    public String y;
    public String z;

    public int describeContents() {
        return 0;
    }

    public DeviceStatus() {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.o = "";
        this.D = new Bundle();
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.F = "";
    }

    public DeviceStatus(Parcel parcel) {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.o = "";
        this.D = new Bundle();
        a(parcel);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceStatus)) {
            return false;
        }
        DeviceStatus deviceStatus = (DeviceStatus) obj;
        if (this.d != null) {
            return this.d.equals(deviceStatus.d);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(Parcel parcel) {
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readDouble();
        this.r = parcel.readDouble();
        this.s = parcel.readString();
        this.t = parcel.readLong();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt() != 0;
        this.x = parcel.readInt();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        String readString = parcel.readString();
        this.B = null;
        if (!TextUtils.isEmpty(readString)) {
            try {
                this.B = new JSONObject(readString);
            } catch (JSONException unused) {
            }
        }
        this.C = parcel.readString();
        this.D = parcel.readBundle();
        this.E = parcel.readInt();
        this.F = parcel.readString();
        this.G = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readString();
    }

    public int hashCode() {
        return this.d.hashCode();
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
        a(this.d, parcel);
        a(this.e, parcel);
        a(this.f, parcel);
        a(this.g, parcel);
        a(this.h, parcel);
        a(this.i, parcel);
        a(this.j, parcel);
        a(this.k, parcel);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        a(this.n, parcel);
        a(this.o, parcel);
        parcel.writeInt(this.p);
        parcel.writeDouble(this.q);
        parcel.writeDouble(this.r);
        a(this.s, parcel);
        parcel.writeLong(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w ? 1 : 0);
        parcel.writeInt(this.x);
        a(this.y, parcel);
        a(this.z, parcel);
        a(this.A, parcel);
        if (this.B == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(this.B.toString());
        }
        a(this.C, parcel);
        if (this.D == null) {
            this.D = new Bundle();
        }
        parcel.writeBundle(this.D);
        parcel.writeInt(this.E);
        a(this.F, parcel);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        a(this.I, parcel);
    }

    public DeviceStat a() {
        DeviceStat deviceStat = new DeviceStat();
        deviceStat.did = this.d;
        deviceStat.name = this.e;
        deviceStat.mac = this.f;
        deviceStat.model = this.g;
        deviceStat.extrainfo = this.h;
        deviceStat.ip = this.i;
        deviceStat.parentId = this.j;
        deviceStat.parentModel = this.k;
        deviceStat.bindFlag = this.l;
        deviceStat.authFlag = this.m;
        deviceStat.token = this.n;
        deviceStat.userId = this.o;
        deviceStat.location = this.p;
        deviceStat.latitude = this.q;
        deviceStat.longitude = this.r;
        deviceStat.bssid = this.s;
        deviceStat.lastModified = this.t;
        deviceStat.pid = this.u;
        deviceStat.rssi = this.v;
        deviceStat.isOnline = this.w;
        deviceStat.resetFlag = this.x;
        deviceStat.ssid = this.y;
        deviceStat.ownerName = this.z;
        deviceStat.ownerId = this.A;
        deviceStat.propInfo = this.B;
        deviceStat.version = this.C;
        deviceStat.property = new Bundle();
        deviceStat.property.putAll(this.D);
        deviceStat.showMode = this.E;
        deviceStat.event = this.F;
        deviceStat.permitLevel = this.G;
        deviceStat.isSetPinCode = this.H;
        deviceStat.deviceIconReal = DeviceUtils.c(this.g);
        return deviceStat;
    }

    @NonNull
    public String toString() {
        return "did:" + this.d + " name:" + this.e + " mac:" + this.f + " model:" + this.g + " extrainfo:" + this.h + " ip:" + this.i + " parentId:" + this.j + " parentModel:" + this.k + " property:" + this.D + " ownerName:" + this.z + " event:" + this.F;
    }
}
