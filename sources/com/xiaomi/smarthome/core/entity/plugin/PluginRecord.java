package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginRecord implements Parcelable {
    public static final Parcelable.Creator<PluginRecord> CREATOR = new Parcelable.Creator<PluginRecord>() {
        /* renamed from: a */
        public PluginRecord createFromParcel(Parcel parcel) {
            return new PluginRecord(parcel);
        }

        /* renamed from: a */
        public PluginRecord[] newArray(int i) {
            return new PluginRecord[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13991a;
    private PluginDeviceInfo b;
    private long c;
    private long d;
    private PluginPackageInfo e;
    private PluginDeveloperInfo f;
    private long g;
    private long h;
    private PluginPackageInfo i;
    private PluginDeveloperInfo j;
    private PluginUpdateInfo k;

    public int describeContents() {
        return 0;
    }

    public PluginRecord() {
    }

    protected PluginRecord(Parcel parcel) {
        this.f13991a = parcel.readString();
        this.b = (PluginDeviceInfo) parcel.readParcelable(PluginDeviceInfo.class.getClassLoader());
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = (PluginPackageInfo) parcel.readParcelable(PluginPackageInfo.class.getClassLoader());
        this.f = (PluginDeveloperInfo) parcel.readParcelable(PluginDeveloperInfo.class.getClassLoader());
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = (PluginPackageInfo) parcel.readParcelable(PluginPackageInfo.class.getClassLoader());
        this.j = (PluginDeveloperInfo) parcel.readParcelable(PluginDeveloperInfo.class.getClassLoader());
        this.k = (PluginUpdateInfo) parcel.readParcelable(PluginUpdateInfo.class.getClassLoader());
    }

    public static PluginRecord a(String str, String str2) {
        PluginRecord pluginRecord = new PluginRecord();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            pluginRecord.f13991a = str;
            pluginRecord.c = jSONObject.optLong("installed_plugin_id");
            pluginRecord.d = jSONObject.optLong("installed_package_id");
            pluginRecord.g = jSONObject.optLong("downloaded_plugin_id");
            pluginRecord.h = jSONObject.optLong("downloaded_package_id");
            return pluginRecord;
        } catch (JSONException unused) {
            return null;
        }
    }

    public synchronized String a() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("installed_plugin_id", this.c);
            jSONObject.put("installed_package_id", this.d);
            jSONObject.put("downloaded_plugin_id", this.g);
            jSONObject.put("downloaded_package_id", this.h);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized void a(long j2, long j3, PluginPackageInfo pluginPackageInfo, PluginDeveloperInfo pluginDeveloperInfo) {
        this.c = j2;
        this.d = j3;
        this.e = pluginPackageInfo;
        this.f = pluginDeveloperInfo;
    }

    public synchronized void b(long j2, long j3, PluginPackageInfo pluginPackageInfo, PluginDeveloperInfo pluginDeveloperInfo) {
        this.g = j2;
        this.h = j3;
        this.i = pluginPackageInfo;
        this.j = pluginDeveloperInfo;
    }

    public synchronized boolean b() {
        if (!l() || !k() || this.e.b() == this.i.b()) {
            return false;
        }
        return true;
    }

    public synchronized PluginDeviceInfo c() {
        return this.b;
    }

    public synchronized void a(PluginDeviceInfo pluginDeviceInfo) {
        this.f13991a = pluginDeviceInfo.c();
        this.b = pluginDeviceInfo;
    }

    public synchronized long d() {
        return this.c;
    }

    public synchronized long e() {
        return this.d;
    }

    public synchronized long f() {
        return this.g;
    }

    public synchronized long g() {
        return this.h;
    }

    public synchronized PluginPackageInfo h() {
        return this.e;
    }

    public synchronized PluginPackageInfo i() {
        return this.i;
    }

    public synchronized PluginUpdateInfo j() {
        return this.k;
    }

    public synchronized void a(PluginUpdateInfo pluginUpdateInfo) {
        this.k = pluginUpdateInfo;
    }

    public synchronized boolean k() {
        if (this.h > 0) {
            return true;
        }
        return false;
    }

    public synchronized boolean l() {
        if (this.d > 0) {
            return true;
        }
        return false;
    }

    public synchronized boolean m() {
        if (!l() || this.e == null) {
            return false;
        }
        return true;
    }

    public synchronized boolean n() {
        if (this.k != null) {
            return true;
        }
        return false;
    }

    public synchronized String o() {
        if (this.b == null) {
            return "";
        }
        return this.b.c();
    }

    public synchronized String p() {
        if (this.b == null) {
            return "";
        }
        return this.b.k();
    }

    public synchronized String q() {
        if (this.b == null) {
            return "";
        }
        return this.b.l();
    }

    public synchronized String r() {
        if (this.b == null) {
            return "";
        }
        return this.b.m();
    }

    public synchronized String s() {
        if (this.b == null) {
            return "";
        }
        return this.b.n();
    }

    public synchronized String t() {
        if (this.b == null) {
            return "";
        }
        return this.b.p();
    }

    public synchronized boolean u() {
        return this.b != null && this.b.q();
    }

    public synchronized int v() {
        if (this.b == null) {
            return -1;
        }
        return this.b.j();
    }

    public synchronized String w() {
        if (l()) {
            if (this.e != null) {
                return this.e.f();
            }
        }
        return "";
    }

    public synchronized String x() {
        if (k()) {
            if (this.i != null) {
                return this.i.f();
            }
        }
        return "";
    }

    public synchronized boolean y() {
        if (!l() || !this.e.n()) {
            return false;
        }
        return true;
    }

    public synchronized boolean z() {
        if (!l() || !this.e.o()) {
            return false;
        }
        return true;
    }

    public synchronized boolean A() {
        if (!l() || !this.e.p()) {
            return false;
        }
        return true;
    }

    public synchronized boolean B() {
        if (!k() || !this.i.n()) {
            return false;
        }
        return true;
    }

    public synchronized boolean C() {
        if (!k() || !this.i.o()) {
            return false;
        }
        return true;
    }

    public synchronized boolean D() {
        if (!k() || !this.i.p()) {
            return false;
        }
        return true;
    }

    public synchronized String E() {
        if (!l()) {
            return "";
        }
        return this.e.h();
    }

    public synchronized String F() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("PluginRecord[");
        sb.append("model:");
        sb.append(this.f13991a);
        sb.append(" ");
        if (this.b != null) {
            sb.append("deviceInfo:");
            sb.append(this.b.Q());
            sb.append(" ");
        }
        if (this.e != null) {
            sb.append("installedPackageInfo:");
            sb.append(this.e.r());
            sb.append(" ");
        }
        if (this.i != null) {
            sb.append("downloadedPackageInfo:");
            sb.append(this.i.r());
            sb.append(" ");
        }
        if (this.k != null) {
            sb.append("updateInfo:");
            sb.append(this.k.i());
            sb.append(" ");
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f13991a);
        parcel.writeParcelable(this.b, i2);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeParcelable(this.e, i2);
        parcel.writeParcelable(this.f, i2);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeParcelable(this.i, i2);
        parcel.writeParcelable(this.j, i2);
        parcel.writeParcelable(this.k, i2);
    }

    public List<String> G() {
        return this.b.L;
    }

    public String toString() {
        return "PluginRecord{mModel='" + this.f13991a + Operators.SINGLE_QUOTE + ", mInstalledPluginId=" + this.c + ", mInstalledPackageId=" + this.d + ", mDownloadedPluginId=" + this.g + ", mDownloadedPackageId=" + this.h + ", mUpdateInfo=" + this.k + Operators.BLOCK_END;
    }
}
