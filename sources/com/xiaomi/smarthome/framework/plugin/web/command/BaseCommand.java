package com.xiaomi.smarthome.framework.plugin.web.command;

import android.os.Handler;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.smarthome.framework.plugin.web.PluginWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseCommand {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17651a = "invokeEvent";
    public static final String b = "callbackEvent";
    public static final String c = "param";
    public static final String d = "responseData";
    protected Handler e;
    protected IPluginRequest f;
    protected PluginWebView g = null;
    private String h = null;
    private String i = null;
    private String j;
    private String k;

    public abstract void c();

    public void a(PluginWebView pluginWebView, String str, Handler handler, IPluginRequest iPluginRequest) {
        JSONObject jSONObject;
        this.g = pluginWebView;
        this.h = str;
        try {
            jSONObject = new JSONObject(this.h);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject != null) {
            this.e = handler;
            this.f = iPluginRequest;
            this.j = jSONObject.optString(f17651a);
            this.i = jSONObject.optString(b);
            this.k = jSONObject.optString("param");
        }
    }

    /* access modifiers changed from: protected */
    public void a(Object obj) {
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("responseData", obj);
                jSONObject.put(b, this.i);
                this.e.sendMessage(this.e.obtainMessage(200, jSONObject.toString()));
            } catch (JSONException unused) {
            }
        }
    }

    public static String a(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optString(f17651a);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public String b() {
        return this.j;
    }
}
