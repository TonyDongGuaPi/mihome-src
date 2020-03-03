package com.xiaomi.youpin.login.entity.account;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginMiAccount implements Parcelable {
    public static final Parcelable.Creator<LoginMiAccount> CREATOR = new Parcelable.Creator<LoginMiAccount>() {
        /* renamed from: a */
        public LoginMiAccount createFromParcel(Parcel parcel) {
            return new LoginMiAccount(parcel);
        }

        /* renamed from: a */
        public LoginMiAccount[] newArray(int i) {
            return new LoginMiAccount[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f23512a;
    private String b;
    private boolean c;
    private List<MiServiceTokenInfo> d = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public LoginMiAccount() {
    }

    protected LoginMiAccount(Parcel parcel) {
        this.f23512a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readByte() != 0;
        this.d = parcel.createTypedArrayList(MiServiceTokenInfo.CREATOR);
    }

    public synchronized void a(String str, String str2, boolean z) {
        this.f23512a = str;
        this.b = str2;
        this.c = z;
    }

    public synchronized String a() {
        return this.f23512a;
    }

    public synchronized boolean b() {
        return this.c;
    }

    public synchronized String c() {
        return this.b;
    }

    public synchronized MiServiceTokenInfo a(String str) {
        MiServiceTokenInfo miServiceTokenInfo;
        miServiceTokenInfo = null;
        int i = 0;
        while (true) {
            if (i >= this.d.size()) {
                break;
            } else if (str.equalsIgnoreCase(this.d.get(i).f23514a)) {
                miServiceTokenInfo = this.d.get(i);
                break;
            } else {
                i++;
            }
        }
        return miServiceTokenInfo;
    }

    public synchronized void a(MiServiceTokenInfo miServiceTokenInfo) {
        e(miServiceTokenInfo.f23514a);
        this.d.add(miServiceTokenInfo);
    }

    private synchronized void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            while (i < this.d.size()) {
                MiServiceTokenInfo miServiceTokenInfo = this.d.get(i);
                if (miServiceTokenInfo.f23514a != null && miServiceTokenInfo.f23514a.equalsIgnoreCase(str)) {
                    int i2 = i - 1;
                    this.d.remove(i);
                    i = i2;
                }
                i++;
            }
        }
    }

    public synchronized List<MiServiceTokenInfo> d() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23512a);
        parcel.writeString(this.b);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeTypedList(this.d);
    }

    public void b(String str) {
        this.f23512a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(List<MiServiceTokenInfo> list) {
        this.d = list;
    }

    public static LoginMiAccount d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LoginMiAccount loginMiAccount = new LoginMiAccount();
        try {
            JSONObject jSONObject = new JSONObject(str);
            loginMiAccount.f23512a = jSONObject.optString("userId");
            loginMiAccount.b = jSONObject.optString("passToken");
            loginMiAccount.c = jSONObject.optBoolean("isSystemAccount");
            JSONObject optJSONObject = jSONObject.optJSONObject("serviceTokens");
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                loginMiAccount.d.add(MiServiceTokenInfo.a(optJSONObject.optString(keys.next())));
            }
            return loginMiAccount;
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized String e() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", this.f23512a);
            jSONObject.put("passToken", this.b);
            jSONObject.put("isSystemAccount", this.c);
            JSONObject jSONObject2 = new JSONObject();
            for (MiServiceTokenInfo next : this.d) {
                jSONObject2.put(next.f23514a, next.a());
            }
            jSONObject.put("serviceTokens", jSONObject2);
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public String toString() {
        return "LoginMiAccount{userId='" + this.f23512a + Operators.SINGLE_QUOTE + ", passToken='" + this.b + Operators.SINGLE_QUOTE + ", isSystemAccount=" + this.c + ", mServiceTokenList=" + this.d + Operators.BLOCK_END;
    }
}
