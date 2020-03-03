package com.xiaomi.ai;

import android.text.TextUtils;
import com.xiaomi.ai.utils.Log;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NlpRequest {
    private static final String r = "NlpRequest";

    /* renamed from: a  reason: collision with root package name */
    String f9898a;
    String b;
    boolean c;
    String d;
    Float e;
    Float f;
    ConcurrentHashMap<String, String> g;
    ConcurrentHashMap<String, String> h;
    String i;
    UserInfo j;
    String k;
    JSONObject l;
    JSONObject m;
    JSONObject n;
    boolean o;
    int p = 0;
    boolean q = true;

    public static class SoundboxContext {

        /* renamed from: a  reason: collision with root package name */
        public static final int f9899a = 3;
        public static final int b = 4;
        public static final int c = 0;
        public static final int d = 1;
        public static final int e = 2;
        public static final int f = 0;
        public static final int g = 1;
        public static final int h = 2;
        public static final int i = 3;
        int j = -1;
        int k = -1;
        int l = -1;
        int m = -1;
        int n = -1;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.j != -1) {
                    jSONObject.put("player_status", this.j);
                }
                if (this.k != -1) {
                    jSONObject.put("player_mode", this.k);
                }
                if (this.l != -1) {
                    jSONObject.put("player_volume", this.l);
                }
                if (this.m != -1) {
                    jSONObject.put("player_type", this.m);
                }
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                if (this.n != -1) {
                    jSONObject3.put("offset", this.n);
                }
                if (!TextUtils.isEmpty(this.o)) {
                    jSONObject3.put("list_id", this.o);
                }
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject4 = new JSONObject();
                String str = "";
                switch (this.m) {
                    case 3:
                        if (!TextUtils.isEmpty(this.s)) {
                            jSONObject4.put("song", this.s);
                        }
                        if (!TextUtils.isEmpty(this.t)) {
                            jSONObject4.put("artist_name", this.t);
                        }
                        if (!TextUtils.isEmpty(this.p)) {
                            jSONObject4.put("id", this.p);
                        }
                        if (!TextUtils.isEmpty(this.r)) {
                            jSONObject4.put("origin", this.r);
                        }
                        if (!TextUtils.isEmpty(this.q)) {
                            jSONObject4.put("global_id", this.q);
                        }
                        str = "musics";
                        break;
                    case 4:
                        if (!TextUtils.isEmpty(this.s)) {
                            jSONObject4.put("title", this.s);
                        }
                        if (!TextUtils.isEmpty(this.p)) {
                            jSONObject4.put("id", this.p);
                        }
                        if (!TextUtils.isEmpty(this.u)) {
                            jSONObject4.put(DTransferConstants.ad, this.u);
                        }
                        if (!TextUtils.isEmpty(this.v)) {
                            jSONObject4.put("albumName", this.v);
                        }
                        str = "stations";
                        break;
                }
                jSONArray.put(jSONObject4);
                jSONObject3.put(str, jSONArray);
                jSONObject2.put("detail", jSONObject3);
                jSONObject.put("player_detail", jSONObject2);
            } catch (JSONException e2) {
                Log.a(NlpRequest.r, "JSONException", e2);
            }
            return jSONObject;
        }

        public void a(int i2) {
            this.j = i2;
        }

        public void a(String str) {
            this.o = str;
        }

        public void b(int i2) {
            this.k = i2;
        }

        public void b(String str) {
            this.q = str;
        }

        public void c(int i2) {
            this.l = i2;
        }

        public void c(String str) {
            this.p = str;
        }

        public void d(int i2) {
            this.m = i2;
        }

        public void d(String str) {
            this.r = str;
        }

        public void e(int i2) {
            this.n = i2;
        }

        public void e(String str) {
            this.s = str;
        }

        public void f(String str) {
            this.u = str;
        }

        public void g(String str) {
            this.v = str;
        }

        public void h(String str) {
            this.t = str;
        }
    }

    public static class UserInfo {

        /* renamed from: a  reason: collision with root package name */
        String f9900a;
        String b;
        String c;

        public void a(String str) {
            this.f9900a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    public String a() {
        return this.f9898a;
    }

    public void a(float f2) {
        this.e = Float.valueOf(f2);
    }

    public void a(int i2) {
        this.p = i2;
    }

    public void a(SoundboxContext soundboxContext) {
        if (soundboxContext != null) {
            a("device_player_status", soundboxContext.a().toString());
        }
    }

    public void a(UserInfo userInfo) {
        this.j = userInfo;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(String str, String str2) {
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>();
        }
        this.g.put(str, str2);
    }

    public void a(JSONObject jSONObject) {
        this.n = jSONObject;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public void b() {
        b(true);
    }

    public void b(float f2) {
        this.f = Float.valueOf(f2);
    }

    public void b(String str) {
        this.i = str;
    }

    public void b(String str, String str2) {
        if (this.h == null) {
            this.h = new ConcurrentHashMap<>();
        }
        this.h.put(str, str2);
    }

    public void b(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public String c() {
        return TextUtils.isEmpty(this.b) ? "zh-CN" : this.b;
    }

    public void c(String str) {
        this.f9898a = str;
    }

    public void c(JSONObject jSONObject) {
        this.m = jSONObject;
    }

    public void c(boolean z) {
        this.q = z;
    }

    public void d(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.k = str;
    }
}
