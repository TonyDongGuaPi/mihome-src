package com.unionpay.mobile.android.nocard.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.unionpay.mobile.android.net.c;
import com.unionpay.mobile.android.net.d;
import com.unionpay.mobile.android.nocard.views.bh;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.k;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class UPPayEngine implements Handler.Callback, com.unionpay.mobile.android.fully.a, Runnable {

    /* renamed from: a  reason: collision with root package name */
    protected c f9582a = null;
    private d b = null;
    private String c = null;
    private String d = null;
    private Context e = null;
    private Handler f = null;
    private a g = null;
    private com.unionpay.mobile.android.model.b h = null;
    private long i = 0;

    public interface a {
        void a(int i, String str);
    }

    class b {

        /* renamed from: a  reason: collision with root package name */
        public int f9583a;
        public String b;

        public b(int i, String str) {
            this.f9583a = i;
            this.b = str;
        }
    }

    public UPPayEngine(Context context) {
        this.e = context;
        this.f = new Handler(this);
    }

    private native String commonMessage(long j, String str, String str2, String str3);

    private native String decryptResponse(long j, String str);

    private native String desEncryptMessage(long j, String str, String str2);

    private native String encryptMessage(long j, String str);

    private native String followRulesMessage(long j, String str, String str2);

    private native String getServerUrl(int i2, int i3, int i4);

    private native String getUserInfo(long j, String str, String str2);

    protected static String i() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(System.currentTimeMillis()));
    }

    private native String initMessage(long j, String str, String str2);

    private void n(String str) {
        new Thread(this, str).start();
    }

    private native String openupgradeMessage(long j, String str, String str2);

    private native String payingMessage(long j, String str, String str2, String str3, String str4, String str5);

    private native String retrieveInitializeKey(long j);

    private native String rsaEncryptMessageForHFT(long j, String str);

    private native String rsaPrivateEncryptMessage(long j, String str);

    private native String ruleMessage(long j, String str, String str2);

    private native void setSessionKey(long j, String str);

    private native String unBoundMessage(long j, String str, String str2);

    public String a(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("reqtm", i());
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            str2 = str;
        }
        k.c("uppay", "post message = " + str);
        this.b.a(encryptMessage(this.i, str2));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        g();
        if (this.f9582a == null) {
            this.f9582a = new c(this.b, this.e);
        }
        int a2 = this.f9582a.a();
        String c2 = this.f9582a.c();
        if (a2 == 0) {
            String decryptResponse = decryptResponse(this.i, c2);
            k.a("uppay", "[ response msg ] " + decryptResponse);
            return decryptResponse;
        }
        Message obtainMessage = this.f.obtainMessage(2);
        obtainMessage.arg1 = a2;
        this.f.sendMessage(obtainMessage);
        return null;
    }

    public final String a(String str, String str2) {
        return desEncryptMessage(this.i, str, str2);
    }

    public final void a() {
        String str;
        StringBuilder sb;
        String str2;
        if (!TextUtils.isEmpty(this.h.bk)) {
            if (this.h.f) {
                sb = new StringBuilder();
                sb.append(this.h.bk);
                str2 = "/app/mobile/hft";
            } else if (this.h.c) {
                sb = new StringBuilder();
                sb.append(this.h.bk);
                str2 = "/app/mobile/json";
            } else {
                sb = new StringBuilder();
                sb.append(this.h.bk);
                str2 = "/gateway/mobile/json";
            }
            sb.append(str2);
            str = sb.toString();
        } else {
            int i2 = 2;
            int i3 = "01".equalsIgnoreCase(this.h.I.c) ? 1 : "02".equalsIgnoreCase(this.h.I.c) ? 2 : "98".equalsIgnoreCase(this.h.I.c) ? 98 : "99".equalsIgnoreCase(this.h.I.c) ? 99 : "95".equalsIgnoreCase(this.h.I.c) ? 95 : 0;
            k.a("uppay", "idx  is : " + i3 + ", isNewTypeTn :" + this.h.c);
            if (!this.h.f) {
                i2 = this.h.c ? 1 : 0;
            }
            str = getServerUrl(i2, i3, this.h.aO);
        }
        k.a("uppay", "url  is : " + str);
        this.b = new d(str);
    }

    public final void a(long j) {
        this.i = j;
    }

    public final void a(com.unionpay.mobile.android.model.b bVar) {
        if (this.h == null || this.h != bVar) {
            this.h = bVar;
        }
    }

    public final void a(a aVar) {
        this.g = aVar;
    }

    public final void a(String str, String str2, int i2) {
        this.b.a(commonMessage(this.i, str, str2, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        if (i2 <= 0) {
            n(str);
            return;
        }
        this.f.sendMessageDelayed(this.f.obtainMessage(1, str), (long) (i2 * 1000));
    }

    public final void a(String str, String str2, String str3, String str4) {
        this.b.a(payingMessage(this.i, str, str2, str3, str4, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n("pay");
    }

    public final String b() {
        return this.d;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final void b(String str, String str2) {
        String str3;
        if (this.h.f) {
            str3 = bh.b(this.e, str, "android", this.h.a(), this.h.g, this.h.d);
        } else {
            str3 = bh.a(this.e, str, "android", this.h.a(), this.h.g, str2);
        }
        this.b.a(initMessage(this.i, str3, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("secret", retrieveInitializeKey(this.i));
        this.b.a((HashMap<String, String>) hashMap);
        n("init");
    }

    public final long c() {
        return this.i;
    }

    public final void c(String str) {
        this.d = str;
    }

    public final void c(String str, String str2) {
        a(str, str2, 0);
    }

    public final d d() {
        return this.b;
    }

    public final boolean d(String str) {
        setSessionKey(this.i, str);
        return true;
    }

    public final Handler e() {
        return this.f;
    }

    public final String e(String str) {
        return encryptMessage(this.i, str);
    }

    public final String f() {
        return this.c;
    }

    public final String f(String str) {
        return decryptResponse(this.i, str);
    }

    public final String g(String str) {
        return rsaPrivateEncryptMessage(this.i, str);
    }

    /* access modifiers changed from: protected */
    public final void g() {
        String c2 = this.b.c();
        if (!TextUtils.isEmpty(c2)) {
            try {
                JSONObject jSONObject = new JSONObject(f(c2));
                String string = jSONObject.getString("cmd");
                String string2 = jSONObject.getString("reqtm");
                d dVar = this.b;
                Context context = this.e;
                dVar.a(context, string, this.h.b + string2 + f.d(this.e));
            } catch (JSONException unused) {
                this.b.a(this.e, "uppay", "1234567890");
            }
        }
    }

    public final String h(String str) {
        return rsaEncryptMessageForHFT(this.i, str);
    }

    public final void h() {
        this.e = null;
        this.f.removeCallbacksAndMessages((Object) null);
        this.f = null;
        this.b = null;
        this.h = null;
        this.f9582a = null;
    }

    public boolean handleMessage(Message message) {
        String str = null;
        if (message.what == 0) {
            b bVar = (b) message.obj;
            if (bVar.f9583a == 0) {
                str = decryptResponse(this.i, bVar.b);
                k.a("uppay", "resp is:" + str);
            }
            if (this.g != null) {
                this.g.a(bVar.f9583a, str);
                k.b("uppayEx", "UPPayEngine:" + this.g.toString());
            }
        } else if (message.what == 1) {
            n((String) message.obj);
        } else if (message.what == 2 && this.g != null) {
            this.g.a(message.arg1, (String) null);
        }
        return true;
    }

    public final void i(String str) {
        this.b.a(ruleMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n(com.coloros.mcssdk.mode.Message.RULE);
    }

    public native long initJNIEnv(Activity activity, int i2, int i3, boolean z, String str, int i4, String str2);

    public final void j(String str) {
        this.b.a(followRulesMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n("followRule");
    }

    public final void k(String str) {
        this.b.a(openupgradeMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n("openupgrade");
    }

    public final void l(String str) {
        this.b.a(unBoundMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n("unbindcard");
    }

    public final void m(String str) {
        String userInfo = getUserInfo(this.i, str, i());
        k.a("uppay", "actEntrust msg:" + userInfo);
        this.b.a(userInfo);
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a((HashMap<String, String>) hashMap);
        n("getuserinfo");
    }

    public void run() {
        HashMap<String, String> d2;
        String str;
        String str2;
        try {
            if (this.h == null || this.h.aO <= 0 || this.h.aO > 5) {
                d2 = this.b.d();
                str = "magic_number";
                str2 = "20131120";
            } else {
                d2 = this.b.d();
                str = "magic_number";
                str2 = "20150423";
            }
            d2.put(str, str2);
            g();
            if (this.f9582a == null) {
                this.f9582a = new c(this.b, this.e);
            }
            b bVar = new b(this.f9582a.a(), this.f9582a.c());
            if (this.f != null) {
                Message obtainMessage = this.f.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = bVar;
                this.f.sendMessage(obtainMessage);
            }
        } catch (Exception | NullPointerException unused) {
        }
    }
}
