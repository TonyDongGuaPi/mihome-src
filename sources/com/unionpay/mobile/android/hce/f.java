package com.unionpay.mobile.android.hce;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.unionpay.mobile.android.fully.a;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.c;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.data.ResultCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static final Object w = new Object();

    /* renamed from: a  reason: collision with root package name */
    private Context f9565a;
    private a b;
    private Handler c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public int f = 0;
    private int g = 0;
    /* access modifiers changed from: private */
    public int h = 10;
    /* access modifiers changed from: private */
    public int i = 1000;
    private String j = "hce";
    private String k = "";
    /* access modifiers changed from: private */
    public String l;
    private String m = "";
    private HashMap<String, k> n = new HashMap<>(0);
    private List<k> o = new ArrayList(0);
    private HashMap<Integer, k> p = new HashMap<>(0);
    private int q = 0;
    private int r = 1;
    private int s = 0;
    private int t = 5;
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, d> u = new ConcurrentHashMap<>(0);
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, l> v = new ConcurrentHashMap<>(0);
    private final Handler.Callback x = new g(this);
    /* access modifiers changed from: private */
    public Handler y;

    public f(Context context) {
        this.f9565a = context;
        this.y = new Handler(this.x);
        this.l = "20150801000000000000";
        this.b = (a) ((BaseActivity) context).a(UPPayEngine.class.toString());
    }

    static /* synthetic */ void a(f fVar) {
        for (Map.Entry<String, k> value : fVar.n.entrySet()) {
            k kVar = (k) value.getValue();
            if (!kVar.a()) {
                String b2 = kVar.b();
                String e2 = kVar.e();
                if (c.a(fVar.f9565a, b2) && e2.equalsIgnoreCase(c.b(fVar.f9565a, b2))) {
                    fVar.q++;
                    fVar.p.put(Integer.valueOf(fVar.q), kVar);
                    fVar.v.put(b2, new l(b2));
                    fVar.u.put(b2, new d(b2));
                }
            }
        }
    }

    static /* synthetic */ void a(f fVar, String str) {
        f fVar2 = fVar;
        String str2 = str;
        d dVar = fVar2.u.get(str2);
        l lVar = fVar2.v.get(str2);
        if (dVar.a() && lVar.a()) {
            fVar2.y.removeMessages(2006, str2);
            String c2 = lVar.c();
            com.unionpay.mobile.android.hce.service.a b2 = lVar.b();
            String c3 = dVar.c();
            ServiceConnection d2 = dVar.d();
            if (c2 != null && !TextUtils.isEmpty(c2) && c3 != null && !TextUtils.isEmpty(c3)) {
                String a2 = a.a(c3, c2);
                k.c("uppay-hce", str2 + " card after: " + a2);
                try {
                    JSONArray jSONArray = new JSONArray(a2);
                    int length = jSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        Object obj = jSONArray.get(i2);
                        if (obj != null) {
                            JSONObject jSONObject = (JSONObject) obj;
                            if (b.bb == null) {
                                b.bb = new ArrayList(1);
                            }
                            c cVar = new c(jSONObject, c2, b2, d2);
                            int i3 = 0;
                            boolean z = false;
                            while (b.bb != null && i3 < b.bb.size()) {
                                if (!TextUtils.isEmpty(cVar.a()) && cVar.a().equalsIgnoreCase(b.bb.get(i3).a())) {
                                    z = true;
                                }
                                i3++;
                            }
                            if (!z) {
                                b.bb.add(cVar);
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            dVar.f();
            fVar2.u.put(str2, dVar);
            lVar.e();
            fVar2.v.put(str2, lVar);
            synchronized (w) {
                fVar2.s--;
            }
            fVar.b();
        }
        for (Map.Entry<String, d> value : fVar2.u.entrySet()) {
            if (!((d) value.getValue()).b()) {
                return;
            }
        }
        k.c("uppay", "hce finished !!!!!!!!!!!!");
        b.bl = true;
        if (fVar2.c != null) {
            fVar2.c.sendMessage(fVar2.c.obtainMessage(0));
        }
    }

    private boolean a(Bundle bundle) {
        String string = bundle.getString("action_resp_code");
        String string2 = bundle.getString("action_resp_message");
        if ("0000".equalsIgnoreCase(string) && string2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(string2);
                String a2 = j.a(jSONObject, "resp");
                j.a(jSONObject, "msg");
                JSONObject c2 = j.c(jSONObject, "params");
                if (a2.equalsIgnoreCase("00")) {
                    this.d = j.c(c2, "signature").toString();
                    JSONArray d2 = j.d(c2, "configs");
                    if (d2 != null) {
                        for (int i2 = 0; i2 < d2.length(); i2++) {
                            this.o.add(new k(d2.getJSONObject(i2)));
                            this.m = j.a(c2, "version");
                        }
                    }
                    int intValue = Integer.decode(j.a(c2, "total_count")).intValue();
                    if (intValue > this.h) {
                        this.g = intValue / this.h;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean a(String str, String str2) {
        i iVar = new i(this, str, str2);
        d dVar = new d(str);
        dVar.a((ServiceConnection) iVar);
        this.u.put(str, dVar);
        try {
            Intent intent = new Intent();
            intent.setAction("com.unionpay.uppay.action.HCE");
            intent.setPackage(str);
            this.f9565a.startService(intent);
            return this.f9565a.bindService(intent, iVar, 1);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public Bundle d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", "1.9");
            jSONObject.put("cmd", this.j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("params", jSONObject2);
            jSONObject2.put("base_version", str);
            bundle.putString("action_resp_message", this.b.a(jSONObject.toString()));
            a(bundle);
            this.f++;
            if (this.f <= this.g) {
                d(this.l);
            }
            return bundle;
        } catch (JSONException unused) {
            bundle.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return bundle;
        }
    }

    static /* synthetic */ void k(f fVar) {
        if (!TextUtils.isEmpty(fVar.m)) {
            for (k next : fVar.o) {
                fVar.n.put(next.b(), next);
            }
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, k> value : fVar.n.entrySet()) {
                jSONArray.put(((k) value.getValue()).f());
            }
            PreferenceUtils.a(fVar.f9565a, fVar.m, "hce_version");
            PreferenceUtils.a(fVar.f9565a, jSONArray.toString(), "hce_info");
        }
        if (!TextUtils.isEmpty(fVar.k) && fVar.n != null && fVar.n.size() > 0) {
            Iterator<Map.Entry<String, k>> it = fVar.n.entrySet().iterator();
            while (it.hasNext()) {
                if (!fVar.k.equals(((k) it.next().getValue()).c())) {
                    it.remove();
                }
            }
        }
    }

    public final void a() {
        this.f = 1;
    }

    public final void a(int i2) {
        this.h = i2;
    }

    public final void a(Handler handler) {
        this.c = handler;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public final void b() {
        if (this.q == 0) {
            b.bl = true;
            if (this.c != null) {
                this.c.sendMessage(this.c.obtainMessage(0));
                return;
            }
            return;
        }
        synchronized (w) {
            for (int i2 = this.r; i2 <= this.q && this.s < this.t; i2++) {
                k kVar = this.p.get(Integer.valueOf(i2));
                String b2 = kVar.b();
                String d2 = kVar.d();
                this.s++;
                new h(this, b2, d2).start();
                this.r++;
            }
        }
    }

    public final void b(int i2) {
        if (i2 > 0) {
            this.i = i2;
        }
    }

    public final void b(String str) {
        this.e = str;
    }

    public final void c() {
        if (b.bb != null) {
            b.bb.clear();
            b.bb = null;
        }
        String a2 = PreferenceUtils.a(this.f9565a, "hce_version");
        if (!TextUtils.isEmpty(a2)) {
            this.l = a2;
        }
        String a3 = PreferenceUtils.a(this.f9565a, "hce_info");
        if (TextUtils.isEmpty(a3)) {
            a3 = "[{\"package\":\"com.yitong.mbank0408\",\"issuer\":\"64083300\",\"syn_key\":\"0123456789ABCDEF1010101010101010\",\"pub_key\":\"268576AF6F50DA40196E18D6E059D2A721373638\",\"status\":\"I\",\"priority\":\"1\"}]";
        }
        try {
            JSONArray jSONArray = new JSONArray(a3);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                k kVar = new k(jSONArray.getJSONObject(i2));
                this.n.put(kVar.b(), kVar);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        new Thread(new j(this)).start();
    }

    public final void c(int i2) {
        if (i2 > 0) {
            this.t = i2;
        }
    }

    public final void c(String str) {
        this.k = str;
    }
}
