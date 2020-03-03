package cn.fraudmetrix.octopus.aspirit.main;

import android.os.Build;
import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.b.b;
import cn.fraudmetrix.octopus.aspirit.b.f;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.e;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import cn.fraudmetrix.octopus.aspirit.utils.k;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class a {
    /* access modifiers changed from: private */
    public static String d;
    private static a f;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f646a;
    /* access modifiers changed from: private */
    public String b = "";
    private String c = null;
    /* access modifiers changed from: private */
    public final HashMap<String, Object> e = new HashMap<>();
    /* access modifiers changed from: private */
    public e g;
    private Runnable h = new Runnable() {
        public synchronized void run() {
            if (!(a.this.g == null || a.this.e == null || a.this.e.size() <= 0)) {
                if (!a.this.e.containsKey("partner_code")) {
                    a.this.e.put("partner_code", OctopusManager.b().d());
                    a.this.e.put("device_name", Build.MODEL);
                    a.this.e.put("channel_code", a.this.f646a);
                    a.this.e.put("channel_type", a.this.b);
                }
                HashMap b = a.this.e;
                b.put(DTransferConstants.l, "android_" + OctopusManager.b().h());
                if (!a.this.e.containsKey("device_id")) {
                    if (TextUtils.isEmpty(a.d) && k.a(OctopusManager.b().c()) != null) {
                        String unused = a.d = k.a(OctopusManager.b().c()).device_id;
                    }
                    if (!TextUtils.isEmpty(a.d)) {
                        a.this.e.put("device_id", a.d);
                    }
                }
                a.this.g.a(a.this.d(), JSON.toJSONString(a.this.e));
            }
        }
    };

    /* renamed from: cn.fraudmetrix.octopus.aspirit.main.a$a  reason: collision with other inner class name */
    public static class C0022a {
        public static synchronized void a() {
            synchronized (C0022a.class) {
                e eVar = new e(OctopusManager.b().c());
                for (Map.Entry next : eVar.a().entrySet()) {
                    if (((String) next.getKey()).endsWith("_octopus_sdk_event_log")) {
                        String str = (String) eVar.b((String) next.getKey(), "");
                        if (!TextUtils.isEmpty(str)) {
                            JSONObject parseObject = JSON.parseObject(str);
                            if (!parseObject.containsKey("status_code")) {
                                parseObject.put("status_code", "-11");
                            }
                            a((String) next.getKey(), parseObject, eVar);
                        }
                    }
                }
            }
        }

        private static void a(final String str, Map<String, Object> map, final e eVar) {
            String replace = str.replace("_octopus_sdk_event_log", "");
            if (TextUtils.isEmpty(replace)) {
                replace = j.a();
            }
            try {
                String encode = URLEncoder.encode(j.b(JSON.toJSONString(map)), "utf-8");
                f.a.a().a(String.format("https://badc.tongdun.cn/1.gif?si=fk9lblc7&lt=3&ea=click&ec=sdk&pi=%s&ex=%s", new Object[]{replace, encode}), (Map<String, Object>) null, (b<InputStream, ?>) new b.a<InputStream, InputStream>() {
                    public InputStream a(InputStream inputStream) {
                        return inputStream;
                    }

                    public void a(Throwable th) {
                    }

                    public void b(InputStream inputStream) {
                        eVar.a(str);
                    }
                });
            } catch (Exception unused) {
            }
        }
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f == null) {
                f = new a();
            }
            aVar = f;
        }
        return aVar;
    }

    private void f() {
        OctopusManager.b().a().execute(this.h);
    }

    public void a(int i) {
        if (22 == i) {
            i = -10;
        }
        this.e.put("status_code", String.valueOf(i));
        this.h.run();
        this.e.clear();
        this.c = null;
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.g = eVar;
        }
    }

    public void a(String str) {
        this.f646a = str;
    }

    public void a(String str, String str2) {
        JSONArray jSONArray;
        HashMap<String, Object> hashMap;
        if (str != null) {
            try {
                if (!str.isEmpty() && str2 != null && !str2.isEmpty()) {
                    if (str.equals("authorize_submit") && this.e.containsKey("submit_order")) {
                        JSONArray jSONArray2 = (JSONArray) this.e.get("submit_order");
                        if (jSONArray2.size() > 0) {
                            JSONArray jSONArray3 = this.e.containsKey("submit_type") ? (JSONArray) this.e.get("submit_type") : new JSONArray();
                            jSONArray3.add(jSONArray2.get(jSONArray2.size() - 1));
                            this.e.put("submit_type", jSONArray3);
                        }
                    }
                    if (this.e.containsKey(str)) {
                        jSONArray = (JSONArray) this.e.get(str);
                        jSONArray.add(str2);
                        hashMap = this.e;
                    } else {
                        jSONArray = new JSONArray();
                        jSONArray.add(str2);
                        hashMap = this.e;
                    }
                    hashMap.put(str, jSONArray);
                    f();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(boolean z) {
        this.e.put("is_install", Boolean.toString(z));
        f();
    }

    public void b() {
        this.e.put("pages_out", Long.toString(System.currentTimeMillis()));
        f();
    }

    public void b(String str) {
        if (str != null && !str.isEmpty()) {
            this.b = str;
        }
    }

    public void c() {
        this.e.put("login_success", Long.toString(System.currentTimeMillis()));
        f();
    }

    public void c(String str) {
        this.e.put(OctopusConstants.x, str);
        f();
    }

    public String d() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = j.a();
            this.c += "_octopus_sdk_event_log";
        }
        return this.c;
    }
}
