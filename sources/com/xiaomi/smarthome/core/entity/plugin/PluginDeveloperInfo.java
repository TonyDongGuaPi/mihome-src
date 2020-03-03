package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginDeveloperInfo implements Parcelable {
    public static final Parcelable.Creator<PluginDeveloperInfo> CREATOR = new Parcelable.Creator<PluginDeveloperInfo>() {
        /* renamed from: a */
        public PluginDeveloperInfo createFromParcel(Parcel parcel) {
            return new PluginDeveloperInfo(parcel);
        }

        /* renamed from: a */
        public PluginDeveloperInfo[] newArray(int i) {
            return new PluginDeveloperInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private long f13986a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public PluginDeveloperInfo() {
    }

    protected PluginDeveloperInfo(Parcel parcel) {
        this.f13986a = parcel.readLong();
        this.b = parcel.readString();
    }

    public static PluginDeveloperInfo a(String str, String str2) {
        PluginDeveloperInfo pluginDeveloperInfo = new PluginDeveloperInfo();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            pluginDeveloperInfo.f13986a = Long.parseLong(str);
            pluginDeveloperInfo.b = jSONObject.optString("key");
            return pluginDeveloperInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public synchronized String a() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", this.b);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized long b() {
        return this.f13986a;
    }

    public synchronized void a(long j) {
        this.f13986a = j;
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.b)) {
            return "";
        }
        return this.b;
    }

    public synchronized void a(String str) {
        this.b = str;
    }

    public synchronized String d() {
        return "PluginDeveloperInfo[" + "developerId:" + this.f13986a + " " + "key:" + this.b + " " + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f13986a);
        parcel.writeString(this.b);
    }
}
