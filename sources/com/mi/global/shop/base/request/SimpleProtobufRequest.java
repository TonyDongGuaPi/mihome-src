package com.mi.global.shop.base.request;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.sys.a;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.BaseResult;
import com.mi.global.shop.base.MiShopStatInterface;
import com.mi.global.shop.base.request.HostManager;
import com.mi.global.shop.base.service.AppVersionService;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.global.shop.base.service.GlobalConfigService;
import com.mi.global.shop.base.service.LocaleService;
import com.mi.global.shop.base.utils.NetworkUtil;
import com.mi.util.Device;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SimpleProtobufRequest<T extends BaseResult> extends Request<T> {
    public static final int ENCRYPT = 2;
    public static final String TAG = "SimpleProtobufRequest";
    public static final int UNENCRYPT = 1;
    @Autowired
    static AppVersionService appVersionService;
    @Autowired
    static GlobalConfigService globalConfigService;
    @Autowired
    static LocaleService localeService;
    @Autowired
    CookieUtilService cookieUtilService;
    private SimpleCallback<T> mCallback;
    private Class<T> mClass;
    private Map<String, String> mMap;
    private String mUrl;

    public SimpleProtobufRequest(String str, Class<T> cls, SimpleCallback<T> simpleCallback) {
        super(0, str, simpleCallback);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService == null || localeService == null || globalConfigService == null || appVersionService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
        this.mCallback = simpleCallback;
        this.mClass = cls;
        this.mUrl = str;
    }

    public SimpleProtobufRequest(String str, Class<T> cls, Map<String, String> map, SimpleCallback<T> simpleCallback) {
        super(1, str, simpleCallback);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService == null || localeService == null || globalConfigService == null || appVersionService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
        this.mCallback = simpleCallback;
        this.mClass = cls;
        this.mMap = map;
        this.mUrl = str;
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
        String deviceInfo = getDeviceInfo();
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

    public static String getDeviceInfo() {
        if (appVersionService == null) {
            appVersionService = (AppVersionService) ARouter.a().a(AppVersionService.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("version");
        sb.append("=");
        sb.append(appVersionService.a());
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
        double d = (double) Device.b;
        double d2 = (double) Device.f1349a;
        Double.isNaN(d);
        Double.isNaN(d2);
        double doubleValue = new BigDecimal(d / d2).setScale(2, 4).doubleValue();
        sb.append(HostManager.Parameters.Keys.DEVICE_RATIO);
        sb.append("=");
        sb.append(doubleValue);
        sb.append(a.b);
        if (localeService == null) {
            localeService = (LocaleService) ARouter.a().a(LocaleService.class);
        }
        if (!localeService.a()) {
            if (TextUtils.isEmpty(MiShopStatInterface.a((Context) ApplicationAgent.f5586a))) {
                sb.append("DEVICEID");
                sb.append("=");
                sb.append(Device.C);
                sb.append(a.b);
            } else {
                sb.append("DEVICEID");
                sb.append("=");
                sb.append(MiShopStatInterface.a((Context) ApplicationAgent.f5586a));
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
        } else {
            sb.append("DEVICEID");
            sb.append("=");
            sb.append(MiShopStatInterface.b((Context) ApplicationAgent.f5586a));
            sb.append(a.b);
        }
        if (globalConfigService == null) {
            globalConfigService = (GlobalConfigService) ARouter.a().a(GlobalConfigService.class);
        }
        if (!TextUtils.isEmpty(globalConfigService.a())) {
            sb.append("request_from");
            sb.append("=");
            sb.append(globalConfigService.a());
            sb.append(a.b);
        }
        return sb.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object[]} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.Response<T> parseNetworkResponse(com.android.volley.NetworkResponse r8) {
        /*
            r7 = this;
            byte[] r0 = r8.data     // Catch:{ Exception -> 0x0068 }
            byte[] r1 = r8.data     // Catch:{ Exception -> 0x0068 }
            int r1 = r1.length     // Catch:{ Exception -> 0x0068 }
            r2 = 1
            int r1 = r1 - r2
            byte r0 = r0[r1]     // Catch:{ Exception -> 0x0068 }
            byte[] r1 = r8.data     // Catch:{ Exception -> 0x0068 }
            int r1 = r1.length     // Catch:{ Exception -> 0x0068 }
            int r1 = r1 - r2
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0068 }
            byte[] r3 = r8.data     // Catch:{ Exception -> 0x0068 }
            byte[] r4 = r8.data     // Catch:{ Exception -> 0x0068 }
            int r4 = r4.length     // Catch:{ Exception -> 0x0068 }
            int r4 = r4 - r2
            r5 = 0
            java.lang.System.arraycopy(r3, r5, r1, r5, r4)     // Catch:{ Exception -> 0x0068 }
            r3 = 2
            if (r3 == r0) goto L_0x0040
            if (r2 == r0) goto L_0x0040
            com.android.volley.ParseError r8 = new com.android.volley.ParseError     // Catch:{ Exception -> 0x0068 }
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x0068 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068 }
            r1.<init>()     // Catch:{ Exception -> 0x0068 }
            java.lang.String r2 = "Protobuf Format Incorrect!"
            r1.append(r2)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r2 = r7.mUrl     // Catch:{ Exception -> 0x0068 }
            r1.append(r2)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0068 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0068 }
            r8.<init>((java.lang.Throwable) r0)     // Catch:{ Exception -> 0x0068 }
            com.android.volley.Response r8 = com.android.volley.Response.error(r8)     // Catch:{ Exception -> 0x0068 }
            return r8
        L_0x0040:
            if (r3 != r0) goto L_0x0046
            byte[] r1 = com.mi.util.AesEncryptionUtil.a((byte[]) r1)     // Catch:{ Exception -> 0x0068 }
        L_0x0046:
            java.lang.Class<T> r0 = r7.mClass     // Catch:{ Exception -> 0x0068 }
            java.lang.String r3 = "decode"
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0068 }
            java.lang.Class<byte[]> r6 = byte[].class
            r4[r5] = r6     // Catch:{ Exception -> 0x0068 }
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch:{ Exception -> 0x0068 }
            r3 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0068 }
            r2[r5] = r1     // Catch:{ Exception -> 0x0068 }
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch:{ Exception -> 0x0068 }
            com.mi.global.shop.base.BaseResult r0 = (com.mi.global.shop.base.BaseResult) r0     // Catch:{ Exception -> 0x0068 }
            com.android.volley.Cache$Entry r8 = com.android.volley.toolbox.HttpHeaderParser.parseCacheHeaders(r8)     // Catch:{ Exception -> 0x0068 }
            com.android.volley.Response r8 = com.android.volley.Response.success(r0, r8)     // Catch:{ Exception -> 0x0068 }
            return r8
        L_0x0068:
            r8 = move-exception
            com.android.volley.ParseError r0 = new com.android.volley.ParseError
            r0.<init>((java.lang.Throwable) r8)
            com.android.volley.Response r8 = com.android.volley.Response.error(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.base.request.SimpleProtobufRequest.parseNetworkResponse(com.android.volley.NetworkResponse):com.android.volley.Response");
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(T t) {
        this.mCallback.onResponse(t);
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }
}
