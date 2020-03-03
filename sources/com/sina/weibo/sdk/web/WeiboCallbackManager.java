package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WbAuthListener;
import java.util.HashMap;
import java.util.Map;

public class WeiboCallbackManager {

    /* renamed from: a  reason: collision with root package name */
    private static WeiboCallbackManager f8853a;
    private Map<String, WbAuthListener> b = new HashMap();

    private WeiboCallbackManager() {
    }

    public static synchronized WeiboCallbackManager a() {
        WeiboCallbackManager weiboCallbackManager;
        synchronized (WeiboCallbackManager.class) {
            if (f8853a == null) {
                f8853a = new WeiboCallbackManager();
            }
            weiboCallbackManager = f8853a;
        }
        return weiboCallbackManager;
    }

    public synchronized WbAuthListener a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.b.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r2, com.sina.weibo.sdk.auth.WbAuthListener r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x0011
            if (r3 != 0) goto L_0x000a
            goto L_0x0011
        L_0x000a:
            java.util.Map<java.lang.String, com.sina.weibo.sdk.auth.WbAuthListener> r0 = r1.b     // Catch:{ all -> 0x0013 }
            r0.put(r2, r3)     // Catch:{ all -> 0x0013 }
            monitor-exit(r1)
            return
        L_0x0011:
            monitor-exit(r1)
            return
        L_0x0013:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.web.WeiboCallbackManager.a(java.lang.String, com.sina.weibo.sdk.auth.WbAuthListener):void");
    }

    public synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.remove(str);
        }
    }

    public String b() {
        return String.valueOf(System.currentTimeMillis());
    }
}
