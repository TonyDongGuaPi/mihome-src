package com.tencent.a.a.a.a;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.paytm.pgsdk.PaytmConstants;

public final class e extends f {
    public e(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "write mid to Settings.System");
            Settings.System.putString(this.f8975a.getContentResolver(), h.c("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return h.a(this.f8975a, "android.permission.WRITE_SETTINGS");
    }

    /* access modifiers changed from: protected */
    public final String b() {
        String string;
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "read mid from Settings.System");
            string = Settings.System.getString(this.f8975a.getContentResolver(), h.c("4kU71lN96TJUomD1vOU9lgj9Tw=="));
        }
        return string;
    }
}
