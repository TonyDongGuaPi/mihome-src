package com.weibo.ssosdk;

import android.content.Context;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class WeiboSsoSdkConfig implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private Context f9863a;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private HashMap<String, String> i = new HashMap<>();

    private String h(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public void a(String str, String str2) {
        this.i.put(str, str2);
    }

    public String a(boolean z) {
        if (this.i.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : this.i.entrySet()) {
            try {
                jSONObject.put((String) next.getKey(), next.getValue());
            } catch (JSONException unused) {
                return "";
            }
        }
        if (z) {
            return h(jSONObject.toString());
        }
        return jSONObject.toString();
    }

    public String b(boolean z) {
        if (z) {
            return h(this.g);
        }
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String c(boolean z) {
        if (z) {
            return h(this.e);
        }
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String d(boolean z) {
        if (z) {
            return h(this.f);
        }
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public String e(boolean z) {
        if (z) {
            return h(this.d);
        }
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String f(boolean z) {
        if (z) {
            return h(this.b);
        }
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public Context a() {
        return this.f9863a;
    }

    public void a(Context context) {
        this.f9863a = context.getApplicationContext();
    }

    public String g(boolean z) {
        if (z) {
            return h(this.c);
        }
        return this.c;
    }

    public void f(String str) {
        this.c = str;
    }

    public void g(String str) {
        this.h = str;
    }

    public String b() {
        return this.h;
    }

    public boolean c() {
        return this.f9863a != null && !TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e);
    }

    public Object clone() {
        try {
            WeiboSsoSdkConfig weiboSsoSdkConfig = (WeiboSsoSdkConfig) super.clone();
            HashMap<String, String> hashMap = new HashMap<>();
            for (Map.Entry next : weiboSsoSdkConfig.i.entrySet()) {
                hashMap.put(next.getKey(), next.getValue());
            }
            weiboSsoSdkConfig.i = hashMap;
            return weiboSsoSdkConfig;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
