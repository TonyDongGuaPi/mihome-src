package com.alipay.deviceid;

import android.content.Context;
import com.alipay.deviceid.module.senative.DeviceIdUtil;
import com.alipay.deviceid.module.x.a;
import com.alipay.deviceid.module.x.b;
import com.alipay.deviceid.module.x.by;
import com.alipay.deviceid.module.x.cc;
import com.alipay.deviceid.module.x.e;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceTokenClient {
    private static String appKeyClientCache = "";
    private static String appNameCache = "";
    private static DeviceTokenClient deviceTokenClientInstance;
    /* access modifiers changed from: private */
    public Context context = null;
    private AtomicBoolean isRepInitializing = new AtomicBoolean(false);

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    public static class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
    }

    private DeviceTokenClient(Context context2) {
        if (context2 != null) {
            this.context = context2;
            return;
        }
        throw new IllegalArgumentException("DeviceTokenClient initialization error: context is null.");
    }

    public static DeviceTokenClient getInstance(Context context2) {
        if (deviceTokenClientInstance == null) {
            synchronized (DeviceTokenClient.class) {
                if (deviceTokenClientInstance == null) {
                    deviceTokenClientInstance = new DeviceTokenClient(context2);
                }
            }
        }
        return deviceTokenClientInstance;
    }

    private void initializeSo() {
        if (!this.isRepInitializing.getAndSet(true)) {
            new Thread(new Runnable() {
                public final void run() {
                    DeviceIdUtil.getInstance(DeviceTokenClient.this.context).initialize();
                }
            }).start();
        }
    }

    public void initToken(final String str, String str2, final InitResultListener initResultListener) {
        initializeSo();
        if (e.a(str)) {
            if (initResultListener != null) {
                initResultListener.onResult("", 2);
            }
        } else if (!e.a(str2)) {
            appKeyClientCache = str2;
            appNameCache = str;
            final HashMap hashMap = new HashMap();
            hashMap.put("appName", str);
            hashMap.put("appKeyClient", str2);
            hashMap.put("rpcVersion", "6");
            hashMap.put("appchannel", "openapi");
            cc.a().a((Runnable) new Runnable() {
                public final void run() {
                    int a2 = new a(DeviceTokenClient.this.context).a(hashMap);
                    if (initResultListener != null) {
                        if (a2 == 0) {
                            initResultListener.onResult(a.a(DeviceTokenClient.this.context, str), 0);
                            return;
                        }
                        initResultListener.onResult("", a2);
                    }
                }
            });
        } else if (initResultListener != null) {
            initResultListener.onResult("", 3);
        }
    }

    public TokenResult getTokenResult() {
        TokenResult tokenResult = new TokenResult();
        tokenResult.apdid = "";
        tokenResult.clientKey = "";
        tokenResult.apdidToken = "";
        tokenResult.apdidToken = by.b(this.context, appNameCache);
        if (tokenResult.apdidToken != null && !"".equals(tokenResult.apdidToken)) {
            return tokenResult;
        }
        tokenResult.apdidToken = by.b(this.context, "public");
        if (tokenResult.apdidToken != null && !"".equals(tokenResult.apdidToken)) {
            return tokenResult;
        }
        if (appNameCache == null || appKeyClientCache == null || "".equals(appNameCache) || "".equals(appKeyClientCache)) {
            tokenResult.apdidToken = "";
            return tokenResult;
        }
        initToken(appNameCache, appKeyClientCache, new InitResultListener() {
            public final void onResult(String str, int i) {
            }
        });
        return tokenResult;
    }

    public static void setEnvConfig(int i) {
        b.a().f887a = i;
    }
}
