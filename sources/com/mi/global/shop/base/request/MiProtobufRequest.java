package com.mi.global.shop.base.request;

import android.webkit.CookieSyncManager;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.service.CookieUtilService;
import java.util.Map;

public class MiProtobufRequest extends com.mi.volley.MiProtobufRequest {
    private static final String TAG = "MiProtobufRequest";
    @Autowired
    CookieUtilService cookieUtilService;

    public MiProtobufRequest(int i, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(i, str, (String) null, listener, errorListener);
    }

    public MiProtobufRequest(int i, String str, String str2, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        headers.put("Mi-Info", SimpleProtobufRequest.getDeviceInfo());
        return headers;
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
}
