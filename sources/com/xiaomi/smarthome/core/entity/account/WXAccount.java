package com.xiaomi.smarthome.core.entity.account;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WXAccount implements Parcelable {
    public static final Parcelable.Creator<WXAccount> CREATOR = new Parcelable.Creator<WXAccount>() {
        /* renamed from: a */
        public WXAccount createFromParcel(Parcel parcel) {
            return new WXAccount(parcel);
        }

        /* renamed from: a */
        public WXAccount[] newArray(int i) {
            return new WXAccount[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13966a;
    private String b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public WXAccount() {
    }

    protected WXAccount(Parcel parcel) {
        this.f13966a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public static WXAccount a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        WXAccount wXAccount = new WXAccount();
        try {
            JSONObject jSONObject = new JSONObject(str);
            wXAccount.f13966a = jSONObject.optString("homeId");
            wXAccount.b = jSONObject.optString("extId");
            wXAccount.c = jSONObject.optString("serviceToken");
            return wXAccount;
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized String a() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("homeId", this.f13966a);
            jSONObject.put("extId", this.b);
            jSONObject.put("serviceToken", this.c);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized String b() {
        return this.f13966a;
    }

    public synchronized String c() {
        return this.b;
    }

    public synchronized String d() {
        return this.c;
    }

    public synchronized void a(String str, String str2, String str3) {
        this.f13966a = str;
        this.b = str2;
        this.c = str3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13966a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
