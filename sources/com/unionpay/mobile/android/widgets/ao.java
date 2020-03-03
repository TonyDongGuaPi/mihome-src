package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

public final class ao extends aa {
    public ao(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(129);
    }

    public final boolean b() {
        String a2 = a();
        return a2 != null && a2.length() > 0;
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "login_pwd";
    }
}
