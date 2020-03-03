package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.miui.tsmclient.net.TSMAuthContants;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cq implements cv {

    /* renamed from: a  reason: collision with root package name */
    private static cq f12679a;
    private cp b;
    private HashMap<String, co> c;
    private String d;
    private Context e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private int k;

    public static synchronized cq a() {
        cq cqVar;
        synchronized (cq.class) {
            cqVar = f12679a;
        }
        return cqVar;
    }

    private String a(ArrayList<cn> arrayList, String str) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("imei", ct.a(this.d));
        }
        jSONObject.put(TSMAuthContants.PARAM_ACTION_TYPE, str);
        jSONObject.put("actionTime", System.currentTimeMillis());
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            JSONObject jSONObject2 = null;
            if (TextUtils.isEmpty(arrayList.get(i2).c)) {
                jSONObject2 = new JSONObject();
            } else {
                try {
                    jSONObject2 = new JSONObject(arrayList.get(i2).c);
                } catch (Exception unused) {
                    Log.e("com.xiaomi.miui.ads.pushsdk", "content 不是json串");
                }
            }
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            jSONObject2.put("adId", arrayList.get(i2).f12676a);
            arrayList2.add(jSONObject2);
        }
        jSONObject.put("adList", new JSONArray(arrayList2));
        return Base64.encodeToString(jSONObject.toString().getBytes(), 2);
    }

    private void a(co coVar) {
        if (!this.c.containsKey(coVar.c)) {
            this.i++;
            ct.b("send: " + this.i);
            cr crVar = new cr(this, this.f, this.g, coVar);
            this.c.put(coVar.c, coVar);
            crVar.execute(new String[0]);
        }
    }

    private void a(ArrayList<cn> arrayList, String str, int i2) {
        try {
            String a2 = a(arrayList, str);
            String a3 = ct.a(a2);
            if (b(new co(i2, a2, a3))) {
                a(new co(i2, a2, a3));
            }
        } catch (JSONException unused) {
        }
    }

    private boolean b(co coVar) {
        if (cs.a(this.e)) {
            return true;
        }
        c(coVar);
        return false;
    }

    private void c(co coVar) {
        this.k++;
        ct.b("cacheCount: " + this.k);
        this.b.a(coVar);
        this.b.a();
    }

    public void a(cn cnVar) {
        if (cnVar.f12676a > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(cnVar);
            a(arrayList, "click", cnVar.b);
        }
    }

    public void a(Integer num, co coVar) {
        if (this.c.containsKey(coVar.c)) {
            if (num.intValue() != 0) {
                this.j++;
                ct.b("faild: " + this.j + " " + coVar.c + "  " + this.c.size());
                c(coVar);
            } else {
                this.h++;
                ct.b("success: " + this.h);
            }
            this.c.remove(coVar.c);
        }
    }

    public void b(cn cnVar) {
        if (cnVar.f12676a > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(cnVar);
            a(arrayList, "remove", cnVar.b);
        }
    }

    public void c(cn cnVar) {
        if (cnVar.f12676a > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(cnVar);
            a(arrayList, "received", cnVar.b);
        }
    }
}
