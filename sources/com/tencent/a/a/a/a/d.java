package com.tencent.a.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.paytm.pgsdk.PaytmConstants;

final class d extends f {
    public d(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "write mid to sharedPreferences");
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f8975a).edit();
            edit.putString(h.c("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
            edit.commit();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final String b() {
        String string;
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "read mid from sharedPreferences");
            string = PreferenceManager.getDefaultSharedPreferences(this.f8975a).getString(h.c("4kU71lN96TJUomD1vOU9lgj9Tw=="), (String) null);
        }
        return string;
    }
}
