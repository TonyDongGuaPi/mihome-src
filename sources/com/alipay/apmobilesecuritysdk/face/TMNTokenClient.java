package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.security.mobile.module.a.a;
import com.miui.tsmclient.net.TSMAuthContants;
import java.util.HashMap;
import java.util.Map;

public class TMNTokenClient {

    /* renamed from: a  reason: collision with root package name */
    private static TMNTokenClient f861a;
    /* access modifiers changed from: private */
    public Context b = null;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        if (context != null) {
            this.b = context;
            return;
        }
        throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f861a == null) {
            synchronized (TMNTokenClient.class) {
                if (f861a == null) {
                    f861a = new TMNTokenClient(context);
                }
            }
        }
        return f861a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (a.a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (a.a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", UtdidWrapper.getUtdid(this.b));
        hashMap.put("tid", "");
        hashMap.put("userId", "");
        hashMap.put("appName", str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put(TSMAuthContants.PARAM_SESSION_ID, str3);
        hashMap.put("rpcVersion", "8");
        b.a().a((Runnable) new Runnable() {
            public void run() {
                int a2 = new com.alipay.apmobilesecuritysdk.a.a(TMNTokenClient.this.b).a((Map<String, String>) hashMap);
                if (initResultListener != null) {
                    if (a2 == 0) {
                        initResultListener.onResult(com.alipay.apmobilesecuritysdk.a.a.a(TMNTokenClient.this.b, str), 0);
                        return;
                    }
                    initResultListener.onResult("", a2);
                }
            }
        });
    }
}
