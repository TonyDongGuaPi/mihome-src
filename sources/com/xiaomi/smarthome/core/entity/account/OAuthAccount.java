package com.xiaomi.smarthome.core.entity.account;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.miui.tsmclient.account.OAuthAccountManager;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthAccount implements Parcelable {
    public static final Parcelable.Creator<OAuthAccount> CREATOR = new Parcelable.Creator<OAuthAccount>() {
        /* renamed from: a */
        public OAuthAccount createFromParcel(Parcel parcel) {
            return new OAuthAccount(parcel);
        }

        /* renamed from: a */
        public OAuthAccount[] newArray(int i) {
            return new OAuthAccount[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13964a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public OAuthAccount() {
    }

    protected OAuthAccount(Parcel parcel) {
        this.f13964a = parcel.readString();
        this.b = parcel.readString();
    }

    public static OAuthAccount a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        OAuthAccount oAuthAccount = new OAuthAccount();
        try {
            JSONObject jSONObject = new JSONObject(str);
            oAuthAccount.f13964a = jSONObject.optString("userId");
            oAuthAccount.b = jSONObject.optString(OAuthAccountManager.MiOAuthConstant.TOKEN);
            return oAuthAccount;
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized String a() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", this.f13964a);
            jSONObject.put(OAuthAccountManager.MiOAuthConstant.TOKEN, this.b);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized void a(String str, String str2) {
        this.f13964a = str;
        this.b = str2;
    }

    public synchronized String b() {
        return this.f13964a;
    }

    public synchronized String c() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13964a);
        parcel.writeString(this.b);
    }
}
