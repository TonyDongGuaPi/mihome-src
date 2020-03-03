package com.mi.global.shop.base.request;

import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.BaseResult;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.util.AesEncryptionUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SimpleJsonRequest<T extends BaseResult> extends Request<T> {
    public static final String TAG = "SimpleJsonRequest";
    @Autowired
    CookieUtilService cookieUtilService;
    private final Gson gson = new Gson();
    private SimpleCallback<T> mCallback;
    private Class<T> mClass;
    private Map<String, String> mMap;
    private String mUrl;

    public SimpleJsonRequest(String str, Class<T> cls, SimpleCallback<T> simpleCallback) {
        super(0, str, simpleCallback);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService != null) {
            this.mCallback = simpleCallback;
            this.mClass = cls;
            this.mUrl = str;
            return;
        }
        throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
    }

    public SimpleJsonRequest(String str, Class<T> cls, Map<String, String> map, SimpleCallback<T> simpleCallback) {
        super(1, str, simpleCallback);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService != null) {
            this.mCallback = simpleCallback;
            this.mClass = cls;
            this.mMap = map;
            this.mUrl = str;
            return;
        }
        throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() throws AuthFailureError {
        return this.mMap;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap hashMap = new HashMap();
        String cookies = getCookies();
        if (!TextUtils.isEmpty(cookies)) {
            hashMap.put("Cookie", cookies);
        }
        String deviceInfo = SimpleProtobufRequest.getDeviceInfo();
        if (!TextUtils.isEmpty(deviceInfo)) {
            hashMap.put("Mi-Info", deviceInfo);
        }
        return hashMap;
    }

    public String getCookies() {
        try {
            CookieSyncManager.createInstance(ApplicationAgent.f5586a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.cookieUtilService == null) {
            this.cookieUtilService = (CookieUtilService) ARouter.a().a(CookieUtilService.class);
        }
        return this.cookieUtilService.a();
    }

    /* access modifiers changed from: protected */
    public Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            JSONObject jSONObject = new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers)));
            if (jSONObject.optBoolean("security")) {
                String b = AesEncryptionUtil.b(jSONObject.optString("data"));
                if (!TextUtils.isEmpty(b)) {
                    jSONObject.put("data", new JSONObject(b));
                }
            }
            return Response.success(this.gson.fromJson(jSONObject.toString(), this.mClass), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length + 1)];
            System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, stackTrace.length);
            stackTraceElementArr[stackTrace.length] = new StackTraceElement("Exception url:", this.mUrl, "", 0);
            e.setStackTrace(stackTraceElementArr);
            return Response.error(new ParseError((Throwable) e));
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(T t) {
        this.mCallback.onResponse(t);
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }
}
