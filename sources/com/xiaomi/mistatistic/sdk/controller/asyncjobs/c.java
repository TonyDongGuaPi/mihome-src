package com.xiaomi.mistatistic.sdk.controller.asyncjobs;

import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.controller.b;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.controller.e;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.j;
import com.xiaomi.mistatistic.sdk.controller.l;
import com.xiaomi.mistatistic.sdk.controller.o;
import com.xiaomi.mistatistic.sdk.controller.p;
import com.xiaomi.mistatistic.sdk.controller.q;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class c implements d.a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public a f12013a;
    private String b;
    private int c;

    public interface a {
        void a(boolean z);
    }

    public c(String str, a aVar, int i) {
        this.f12013a = aVar;
        this.b = str;
        this.c = i;
    }

    public void a() {
        try {
            int g = p.a().g();
            long f = p.a().f();
            TreeMap treeMap = new TreeMap();
            treeMap.put("app_id", com.xiaomi.mistatistic.sdk.controller.c.b());
            treeMap.put("app_key", com.xiaomi.mistatistic.sdk.controller.c.c());
            treeMap.put("device_id", new e().a());
            treeMap.put("channel", com.xiaomi.mistatistic.sdk.controller.c.d());
            treeMap.put("policy", String.valueOf(g));
            treeMap.put(Constants.Name.INTERVAL, String.valueOf(f));
            h.a(String.format("upload policy:%d, upload interval:%d, mistat upload version:%d, upload %d events.", new Object[]{Integer.valueOf(g), Long.valueOf(f), 3, Integer.valueOf(this.c)}));
            String e = com.xiaomi.mistatistic.sdk.controller.c.e();
            if (!TextUtils.isEmpty(e)) {
                treeMap.put("version", e);
            }
            h.b("raw upload content:" + this.b);
            treeMap.put("stat_value", b.a(this.b, a(com.xiaomi.mistatistic.sdk.controller.c.b(), com.xiaomi.mistatistic.sdk.controller.c.c(), new e().a())));
            treeMap.put("mistatv", String.valueOf(3));
            treeMap.put("size", String.valueOf(this.c));
            j.a(BuildSetting.a() ? "http://10.235.124.13:8097/mistats" : "http://data.mistat.xiaomi.com/mistats/v2", treeMap, new j.b() {
                public void a(String str) {
                    boolean z;
                    try {
                        z = c.this.a(str);
                    } catch (Exception e) {
                        h.a("Upload MiStat data failed: ", (Throwable) e);
                        z = false;
                    }
                    c.this.f12013a.a(z);
                }
            });
        } catch (Exception e2) {
            h.d("RDUJ", "RemoteDataUploadJob exception: " + e2.getMessage());
            this.f12013a.a(false);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(String str) throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("Upload complete, result: ");
        sb.append(str == null ? "" : str.trim());
        h.c("RDUJ", sb.toString());
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("status");
            a(jSONObject);
            if (!"ok".equals(string)) {
                h.c("result status isn't ok, " + string);
                return false;
            } else if ("test ok".equals(jSONObject.optString("reason"))) {
                o.a().c();
                h.a("test ok, enable shake detector");
                return true;
            } else {
                o.a().d();
                h.a("test not ok, disable shake detector");
                return true;
            }
        } catch (Exception e) {
            h.a("parseUploadingResult exception ", (Throwable) e);
            return false;
        }
    }

    private void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has("delay")) {
                l.a(jSONObject2.getLong("delay"));
            }
        }
    }

    private String a(String str, String str2, String str3) {
        return q.b(String.format("seed:%s-%s-%s", new Object[]{str, str2, str3})).substring(0, 16);
    }
}
