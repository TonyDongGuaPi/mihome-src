package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.fully.a;
import com.unionpay.mobile.android.net.c;
import com.unionpay.mobile.android.net.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class n extends UPPayEngine implements a {
    private Context b;

    public n(Context context) {
        super(context);
        this.b = context;
    }

    public final String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("reqtm", i());
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        k.c("uppay", "post message = " + str);
        String e = e(str);
        d d = d();
        if (d != null) {
            try {
                d.a(e);
                HashMap hashMap = new HashMap(1);
                hashMap.put("sid", f());
                d.a((HashMap<String, String>) hashMap);
                g();
                if (this.f9582a == null) {
                    this.f9582a = new c(d, this.b);
                }
                int a2 = this.f9582a.a();
                String c = this.f9582a.c();
                if (a2 == 0) {
                    String f = f(c);
                    k.a("uppay", "[ response msg ] " + f);
                    return f;
                }
                Handler e2 = e();
                if (e2 != null) {
                    Message obtainMessage = e2.obtainMessage(2);
                    obtainMessage.arg1 = a2;
                    e2.sendMessage(obtainMessage);
                }
            } catch (Exception unused2) {
            }
        }
        return null;
    }
}
