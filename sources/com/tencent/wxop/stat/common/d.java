package com.tencent.wxop.stat.common;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mobikwik.sdk.lib.Constants;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.a;
import com.tencent.wxop.stat.au;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

class d {

    /* renamed from: a  reason: collision with root package name */
    String f9317a;
    String b;
    DisplayMetrics c;
    int d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    int l;
    String m;
    String n;
    Context o;
    private String p;
    private String q;
    private String r;
    private String s;

    private d(Context context) {
        this.b = StatConstants.f9313a;
        this.d = Build.VERSION.SDK_INT;
        this.e = Build.MODEL;
        this.f = Build.MANUFACTURER;
        this.g = Locale.getDefault().getLanguage();
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.o = context.getApplicationContext();
        this.c = k.d(this.o);
        this.f9317a = k.j(this.o);
        this.h = StatConfig.c(this.o);
        this.i = k.i(this.o);
        this.j = TimeZone.getDefault().getID();
        this.l = k.o(this.o);
        this.k = k.p(this.o);
        this.m = this.o.getPackageName();
        if (this.d >= 14) {
            this.p = k.v(this.o);
        }
        this.q = k.u(this.o).toString();
        this.r = k.t(this.o);
        this.s = k.d();
        this.n = k.C(this.o);
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject, Thread thread) {
        String str;
        String g2;
        if (thread == null) {
            if (this.c != null) {
                jSONObject.put(com.xiaomi.stat.d.Y, this.c.widthPixels + "*" + this.c.heightPixels);
                jSONObject.put("dpi", this.c.xdpi + "*" + this.c.ydpi);
            }
            if (a.a(this.o).e()) {
                JSONObject jSONObject2 = new JSONObject();
                q.a(jSONObject2, "bs", q.d(this.o));
                q.a(jSONObject2, "ss", q.e(this.o));
                if (jSONObject2.length() > 0) {
                    q.a(jSONObject, "wf", jSONObject2.toString());
                }
            }
            JSONArray a2 = q.a(this.o, 10);
            if (a2 != null && a2.length() > 0) {
                q.a(jSONObject, "wflist", a2.toString());
            }
            str = "sen";
            g2 = this.p;
        } else {
            q.a(jSONObject, "thn", thread.getName());
            q.a(jSONObject, AccountIntent.QQ_SNS_TYPE, StatConfig.d(this.o));
            q.a(jSONObject, "cui", StatConfig.e(this.o));
            if (k.c(this.r) && this.r.split("/").length == 2) {
                q.a(jSONObject, "fram", this.r.split("/")[0]);
            }
            if (k.c(this.s) && this.s.split("/").length == 2) {
                q.a(jSONObject, "from", this.s.split("/")[0]);
            }
            if (au.a(this.o).b(this.o) != null) {
                jSONObject.put(BioDetector.EXT_KEY_UI, au.a(this.o).b(this.o).b());
            }
            str = Constants.MID;
            g2 = StatConfig.g(this.o);
        }
        q.a(jSONObject, str, g2);
        q.a(jSONObject, "pcn", k.q(this.o));
        q.a(jSONObject, "osn", Build.VERSION.RELEASE);
        q.a(jSONObject, "av", this.f9317a);
        q.a(jSONObject, "ch", this.h);
        q.a(jSONObject, com.xiaomi.stat.d.X, this.f);
        q.a(jSONObject, "sv", this.b);
        q.a(jSONObject, "osd", Build.DISPLAY);
        q.a(jSONObject, "prod", Build.PRODUCT);
        q.a(jSONObject, CommandMessage.TYPE_TAGS, Build.TAGS);
        q.a(jSONObject, "id", Build.ID);
        q.a(jSONObject, "fng", Build.FINGERPRINT);
        q.a(jSONObject, "lch", this.n);
        q.a(jSONObject, com.xiaomi.stat.d.R, Integer.toString(this.d));
        jSONObject.put("os", 1);
        q.a(jSONObject, "op", this.i);
        q.a(jSONObject, com.xiaomi.stat.d.W, this.g);
        q.a(jSONObject, com.xiaomi.stat.d.G, this.e);
        q.a(jSONObject, com.xiaomi.stat.d.o, this.j);
        if (this.l != 0) {
            jSONObject.put("jb", this.l);
        }
        q.a(jSONObject, AreaPropInfo.j, this.k);
        q.a(jSONObject, "apn", this.m);
        q.a(jSONObject, "cpu", this.q);
        q.a(jSONObject, "abi", Build.CPU_ABI);
        q.a(jSONObject, "abi2", Build.CPU_ABI2);
        q.a(jSONObject, "ram", this.r);
        q.a(jSONObject, "rom", this.s);
    }
}
