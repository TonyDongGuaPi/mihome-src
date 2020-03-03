package com.xiaomi.ai;

import android.content.SharedPreferences;
import com.xiaomi.ai.mibrain.MibrainOauthManagerCallbacks;
import com.xiaomi.ai.utils.Log;

class n implements MibrainOauthManagerCallbacks {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ m f9931a;

    n(m mVar) {
        this.f9931a = mVar;
    }

    public String getOauthCode() {
        Log.f(m.b, "getOauthCode");
        if (this.f9931a.c != null) {
            return this.f9931a.c.a();
        }
        return null;
    }

    public String getOauthData(String str) {
        return this.f9931a.d.getSharedPreferences(m.f9929a, 0).getString(str, (String) null);
    }

    public boolean putOauthData(String str, String str2) {
        SharedPreferences.Editor edit = this.f9931a.d.getSharedPreferences(m.f9929a, 0).edit();
        edit.putString(str, str2);
        edit.apply();
        return true;
    }
}
