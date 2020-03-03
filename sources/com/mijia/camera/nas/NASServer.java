package com.mijia.camera.nas;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class NASServer implements Parcelable {
    public static final Parcelable.Creator<NASServer> CREATOR = new Parcelable.Creator<NASServer>() {
        /* renamed from: a */
        public NASServer createFromParcel(Parcel parcel) {
            return new NASServer(parcel);
        }

        /* renamed from: a */
        public NASServer[] newArray(int i) {
            return new NASServer[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f7946a;
    private String b;
    private String c;
    private int d;
    private long e;
    private String f;
    private String g;
    private String h;
    private String i = "";

    public int describeContents() {
        return 0;
    }

    protected NASServer(Parcel parcel) {
        this.f7946a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
    }

    public NASServer() {
    }

    public void a(String str) {
        this.f7946a = str;
    }

    public String a() {
        return this.f7946a;
    }

    public void a(long j) {
        this.e = j;
    }

    public long b() {
        return this.e;
    }

    public void b(String str) {
        this.f = str;
    }

    public String c() {
        return this.f;
    }

    public void c(String str) {
        this.i = str;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        if (TextUtils.isEmpty(this.i)) {
            return "";
        }
        return this.i.lastIndexOf("/") > 0 ? this.i.substring(this.i.lastIndexOf("/") + 1, this.i.length()) : this.i;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public int f() {
        return this.d;
    }

    public String g() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public static NASServer a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        NASServer nASServer = new NASServer();
        nASServer.a(jSONObject.optInt("type"));
        nASServer.b(jSONObject.optString("group"));
        nASServer.a(jSONObject.optLong("addr"));
        nASServer.a(jSONObject.optString("name"));
        nASServer.c(jSONObject.optString(SharePatchInfo.g));
        nASServer.e(jSONObject.optString("pass"));
        nASServer.d(jSONObject.optString("user"));
        return nASServer;
    }

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.d);
            jSONObject.put("group", this.f);
            jSONObject.put("addr", this.e);
            jSONObject.put("name", this.f7946a);
            jSONObject.put(SharePatchInfo.g, this.i);
            jSONObject.put("user", this.g);
            jSONObject.put("pass", this.h);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f7946a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
    }
}
