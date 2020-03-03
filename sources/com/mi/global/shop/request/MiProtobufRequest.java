package com.mi.global.shop.request;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.sys.a;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import java.util.Map;

public class MiProtobufRequest extends com.mi.volley.MiProtobufRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6943a = "MiProtobufRequest";

    public MiProtobufRequest(int i, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(i, str, (String) null, listener, errorListener);
    }

    public MiProtobufRequest(int i, String str, String str2, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        headers.put("Mi-Info", a());
        return headers;
    }

    public String getCookies() {
        try {
            CookieSyncManager.createInstance(ShopApp.g());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CookieUtil.a();
    }

    public static String a() {
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
        String str = f6943a;
        LogUtil.b(str, "device:" + sb.toString());
        return sb.toString();
    }
}
