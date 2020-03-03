package com.xiaomi.youpin.login.entity.account;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONException;
import org.json.JSONObject;

public class MiServiceTokenInfo implements Parcelable {
    public static final Parcelable.Creator<MiServiceTokenInfo> CREATOR = new Parcelable.Creator<MiServiceTokenInfo>() {
        /* renamed from: a */
        public MiServiceTokenInfo createFromParcel(Parcel parcel) {
            return new MiServiceTokenInfo(parcel);
        }

        /* renamed from: a */
        public MiServiceTokenInfo[] newArray(int i) {
            return new MiServiceTokenInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f23514a;
    public String b;
    public String c;
    public String d;
    public long e;
    public String f;

    public int describeContents() {
        return 0;
    }

    public MiServiceTokenInfo() {
    }

    public MiServiceTokenInfo(String str, String str2, String str3, String str4, String str5, long j) {
        this.f23514a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = j;
        this.f = str5;
    }

    protected MiServiceTokenInfo(Parcel parcel) {
        this.f23514a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = parcel.readString();
    }

    public static MiServiceTokenInfo a(String str) {
        MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            miServiceTokenInfo.f23514a = jSONObject.optString("sid");
            miServiceTokenInfo.b = jSONObject.optString("cUserId");
            miServiceTokenInfo.c = jSONObject.optString("serviceToken");
            miServiceTokenInfo.d = jSONObject.optString("ssecurity");
            miServiceTokenInfo.e = jSONObject.optLong("timeDiff");
            miServiceTokenInfo.f = jSONObject.optString("domain");
            return miServiceTokenInfo;
        } catch (JSONException unused) {
            return null;
        }
    }

    public String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sid", this.f23514a);
            jSONObject.put("cUserId", this.b);
            jSONObject.put("serviceToken", this.c);
            jSONObject.put("ssecurity", this.d);
            jSONObject.put("timeDiff", this.e);
            jSONObject.put("domain", this.f);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23514a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeString(this.f);
    }

    public String toString() {
        return "MiServiceTokenInfo{sid='" + this.f23514a + Operators.SINGLE_QUOTE + ", cUserId='" + this.b + Operators.SINGLE_QUOTE + ", serviceToken='" + this.c + Operators.SINGLE_QUOTE + ", ssecurity='" + this.d + Operators.SINGLE_QUOTE + ", timeDiff=" + this.e + ", domain='" + this.f + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
