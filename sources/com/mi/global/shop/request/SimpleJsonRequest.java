package com.mi.global.shop.request;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.sys.a;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.newmodel.BaseResult;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.util.AesEncryptionUtil;
import com.mi.util.Device;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SimpleJsonRequest<T extends BaseResult> extends Request<T> {
    public static final String d = "SimpleJsonRequest";

    /* renamed from: a  reason: collision with root package name */
    private final Gson f6955a = new Gson();
    private SimpleCallback<T> b;
    private Class<T> c;
    private Map<String, String> e;
    private String f;

    public SimpleJsonRequest(String str, Class<T> cls, SimpleCallback<T> simpleCallback) {
        super(0, str, simpleCallback);
        this.b = simpleCallback;
        this.c = cls;
        this.f = str;
    }

    public SimpleJsonRequest(String str, Class<T> cls, Map<String, String> map, SimpleCallback<T> simpleCallback) {
        super(1, str, simpleCallback);
        this.b = simpleCallback;
        this.c = cls;
        this.e = map;
        this.f = str;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() throws AuthFailureError {
        return this.e;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap hashMap = new HashMap();
        String a2 = a();
        if (!TextUtils.isEmpty(a2)) {
            hashMap.put("Cookie", a2);
        }
        String b2 = b();
        if (!TextUtils.isEmpty(b2)) {
            hashMap.put("Mi-Info", b2);
        }
        return hashMap;
    }

    public String a() {
        try {
            CookieSyncManager.createInstance(ShopApp.g());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return CookieUtil.a();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("version");
        sb.append("=");
        sb.append(Device.r);
        sb.append(a.b);
        sb.append("phone_model");
        sb.append("=");
        sb.append(Device.e);
        sb.append(a.b);
        sb.append("networkType");
        sb.append("=");
        sb.append(NetworkUtil.a());
        sb.append(a.b);
        sb.append("appname");
        sb.append("=");
        sb.append("shop");
        sb.append(a.b);
        sb.append("android_sdk_version");
        sb.append("=");
        sb.append(Device.m);
        sb.append(a.b);
        sb.append("android_version");
        sb.append("=");
        sb.append(Device.o);
        sb.append(a.b);
        if (TextUtils.isEmpty(MiShopStatInterface.a((Context) ShopApp.g()))) {
            sb.append("DEVICEID");
            sb.append("=");
            sb.append(Device.C);
            sb.append(a.b);
        } else {
            sb.append("DEVICEID");
            sb.append("=");
            sb.append(MiShopStatInterface.a((Context) ShopApp.g()));
            sb.append(a.b);
        }
        sb.append("device_width");
        sb.append("=");
        sb.append(Device.f1349a);
        sb.append(a.b);
        sb.append("device_height");
        sb.append("=");
        sb.append(Device.b);
        sb.append(a.b);
        if (ShopApp.h != null) {
            if (ShopApp.h.f.equals("community_sdk")) {
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
                sb.append(a.b);
            } else if (ShopApp.h.f.equals("mihome_sdk")) {
                sb.append("request_from");
                sb.append("=");
                sb.append("mihome_sdk");
                sb.append(a.b);
            } else {
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
                sb.append(a.b);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            JSONObject jSONObject = new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers)));
            if (jSONObject.optBoolean("security")) {
                String b2 = AesEncryptionUtil.b(jSONObject.optString("data"));
                if (!TextUtils.isEmpty(b2)) {
                    jSONObject.put("data", new JSONObject(b2));
                }
            }
            return Response.success(this.f6955a.fromJson(jSONObject.toString(), this.c), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (Exception e2) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length + 1)];
            System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, stackTrace.length);
            stackTraceElementArr[stackTrace.length] = new StackTraceElement("Exception url:", this.f, "", 0);
            e2.setStackTrace(stackTraceElementArr);
            return Response.error(new ParseError((Throwable) e2));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void deliverResponse(T t) {
        this.b.onResponse(t);
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }
}
