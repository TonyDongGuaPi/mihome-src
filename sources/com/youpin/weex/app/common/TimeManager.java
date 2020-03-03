package com.youpin.weex.app.common;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.log.LogUtils;
import com.youpin.weex.app.util.HttpManager;
import com.youpin.weex.app.util.OpenUtils;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimeManager {
    private static final String e = "/weex/v1.gif";

    /* renamed from: a  reason: collision with root package name */
    private long f2498a;
    private volatile boolean b = false;
    private String c;
    private Hashtable<String, RecordPair> d = new Hashtable<>();

    private static class RecordPair {

        /* renamed from: a  reason: collision with root package name */
        long f2501a;
        long b;
        String c;

        private RecordPair() {
            this.f2501a = 0;
            this.b = 0;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            if (this.f2501a <= 0 || this.b <= 0) {
                return -1;
            }
            return this.b - this.f2501a;
        }
    }

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static TimeManager f2500a = new TimeManager();

        private Holder() {
        }
    }

    public static TimeManager a() {
        return Holder.f2500a;
    }

    public void a(String str) {
        this.c = str;
        this.f2498a = System.currentTimeMillis();
        this.b = true;
    }

    public void b() {
        if (this.b) {
            this.b = false;
            long currentTimeMillis = System.currentTimeMillis() - this.f2498a;
            if (this.d.size() > 0) {
                RecordPair recordPair = new RecordPair();
                recordPair.c = String.valueOf(currentTimeMillis);
                this.d.put("total", recordPair);
                Set<Map.Entry<String, RecordPair>> entrySet = this.d.entrySet();
                HashMap hashMap = new HashMap();
                hashMap.put("event", this.c);
                for (Map.Entry next : entrySet) {
                    String str = (String) next.getKey();
                    RecordPair recordPair2 = (RecordPair) next.getValue();
                    String str2 = recordPair2.c;
                    if (TextUtils.isEmpty(str2)) {
                        long a2 = recordPair2.a();
                        str2 = a2 != -1 ? String.valueOf(a2) : "";
                    }
                    if (str2 != null) {
                        hashMap.put(str, str2);
                    }
                }
                a((Map) hashMap, (ICallback) null);
                this.d.clear();
            }
        }
    }

    public void a(String str, String str2) {
        if (this.b) {
            RecordPair recordPair = new RecordPair();
            recordPair.c = str2;
            this.d.put(str, recordPair);
        }
    }

    public void b(String str) {
        if (this.b) {
            RecordPair recordPair = new RecordPair();
            recordPair.f2501a = System.currentTimeMillis();
            this.d.put(str, recordPair);
        }
    }

    public void c(String str) {
        if (this.b) {
            RecordPair recordPair = this.d.get(str);
            if (recordPair == null) {
                Log.e("Weex", "no record !!");
            } else {
                recordPair.b = System.currentTimeMillis();
            }
        }
    }

    public void d(String str) {
        if (this.b) {
            this.d.remove(str);
        }
    }

    public void c() {
        this.b = false;
        this.d.clear();
    }

    private String d() {
        return OpenUtils.a() + e;
    }

    public void a(Map map, ICallback iCallback) {
        if (map != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                for (Map.Entry entry : map.entrySet()) {
                    jSONObject.put((String) entry.getKey(), String.valueOf(entry.getValue()));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a(jSONObject, iCallback);
        }
    }

    public void a(JSONObject jSONObject, final ICallback iCallback) {
        LogUtils.d("Weex", jSONObject);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        HttpManager.a().a(d(), jSONObject2.toString(), (Class) null, new HttpManager.HttpCallback() {
            public void a(String str, Object obj) {
                LogUtils.d("Weex", "打点上传成功");
                if (iCallback != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("result", 0);
                    iCallback.callback(hashMap);
                }
            }

            public void a(String str) {
                LogUtils.d("Weex", "打点上传失败 " + str);
                if (iCallback != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("result", 1);
                    hashMap.put("msg", str);
                    iCallback.callback(hashMap);
                }
            }
        });
    }
}
