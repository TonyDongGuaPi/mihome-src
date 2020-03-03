package com.sina.weibo.sdk.sso;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.weibo.ssosdk.MfpBuilder;
import com.weibo.ssosdk.WeiboSsoSdk;
import com.weibo.ssosdk.WeiboSsoSdkConfig;

public final class WeiboSsoManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8826a = "WeiboSsoManager";
    private String b;

    private static class Instance {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final WeiboSsoManager f8827a = new WeiboSsoManager();

        private Instance() {
        }
    }

    private WeiboSsoManager() {
    }

    public static synchronized WeiboSsoManager a() {
        WeiboSsoManager a2;
        synchronized (WeiboSsoManager.class) {
            a2 = Instance.f8827a;
        }
        return a2;
    }

    public void a(Context context, String str) {
        LogUtil.a(f8826a, "init config");
        WeiboSsoSdkConfig weiboSsoSdkConfig = new WeiboSsoSdkConfig();
        weiboSsoSdkConfig.a(context);
        weiboSsoSdkConfig.e(str);
        weiboSsoSdkConfig.d("1478195010");
        weiboSsoSdkConfig.b("1000_0001");
        WeiboSsoSdk.a(weiboSsoSdkConfig);
        b();
    }

    private void b() {
        try {
            this.b = WeiboSsoSdk.a().c();
            if (TextUtils.isEmpty(this.b)) {
                this.b = WeiboSsoSdk.a().b().a();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.c(f8826a, e.getMessage());
        }
    }

    public String b(Context context, String str) {
        LogUtil.a(f8826a, "getAid()");
        if (TextUtils.isEmpty(this.b)) {
            a(context, str);
        }
        return this.b;
    }

    public String a(Context context) {
        return MfpBuilder.b(context);
    }
}
