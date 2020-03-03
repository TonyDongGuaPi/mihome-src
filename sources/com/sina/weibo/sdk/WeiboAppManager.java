package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WbAppInfo;
import java.util.List;

public class WeiboAppManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8818a = "com.sina.weibo.action.sdkidentity";
    private static final String b = "com.sina.weibo.sdk.WeiboAppManager";
    private static final String c = "weibo_for_sdk.json";
    private static final String d = "com.sina.weibo";
    private static final String e = "com.sina.weibog3";
    private static WeiboAppManager f;
    private Context g;
    private WbAppInfo h;

    private WeiboAppManager(Context context) {
        this.g = context.getApplicationContext();
    }

    public static synchronized WeiboAppManager a(Context context) {
        WeiboAppManager weiboAppManager;
        synchronized (WeiboAppManager.class) {
            if (f == null) {
                f = new WeiboAppManager(context);
            }
            weiboAppManager = f;
        }
        return weiboAppManager;
    }

    public synchronized WbAppInfo a() {
        return b(this.g);
    }

    public static WbAppInfo b(Context context) {
        return c(context);
    }

    @Deprecated
    public boolean b() {
        Intent intent = new Intent(f8818a);
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = this.g.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return false;
        }
        return true;
    }

    private static WbAppInfo c(Context context) {
        Intent intent = new Intent(f8818a);
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        WbAppInfo wbAppInfo = null;
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        for (ResolveInfo next : queryIntentServices) {
            if (!(next.serviceInfo == null || next.serviceInfo.applicationInfo == null || TextUtils.isEmpty(next.serviceInfo.packageName))) {
                String str = next.serviceInfo.packageName;
                WbAppInfo a2 = a(context, str);
                if (a2 != null) {
                    wbAppInfo = a2;
                }
                if ("com.sina.weibo".equals(str) || e.equals(str)) {
                    break;
                }
            }
        }
        return wbAppInfo;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0092 A[SYNTHETIC, Splitter:B:40:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009e A[SYNTHETIC, Splitter:B:47:0x009e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.sina.weibo.sdk.auth.WbAppInfo a(android.content.Context r8, java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 2
            android.content.Context r0 = r8.createPackageContext(r9, r0)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.lang.String r4 = "weibo_for_sdk.json"
            java.io.InputStream r0 = r0.open(r4)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0080 }
            r4.<init>()     // Catch:{ Exception -> 0x0080 }
        L_0x0020:
            r5 = 0
            int r6 = r0.read(r3, r5, r2)     // Catch:{ Exception -> 0x0080 }
            r7 = -1
            if (r6 == r7) goto L_0x0031
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x0080 }
            r7.<init>(r3, r5, r6)     // Catch:{ Exception -> 0x0080 }
            r4.append(r7)     // Catch:{ Exception -> 0x0080 }
            goto L_0x0020
        L_0x0031:
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0080 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0080 }
            if (r2 != 0) goto L_0x003e
            com.sina.weibo.sdk.ApiUtils.a((android.content.Context) r8, (java.lang.String) r9)     // Catch:{ Exception -> 0x0080 }
        L_0x003e:
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ Exception -> 0x0080 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0080 }
            r8.<init>(r2)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r2 = "support_api"
            int r2 = r8.optInt(r2, r7)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "authActivityName"
            java.lang.String r8 = r8.optString(r3, r1)     // Catch:{ Exception -> 0x0080 }
            if (r2 == r7) goto L_0x0075
            boolean r3 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x0080 }
            if (r3 == 0) goto L_0x005c
            goto L_0x0075
        L_0x005c:
            com.sina.weibo.sdk.auth.WbAppInfo r3 = new com.sina.weibo.sdk.auth.WbAppInfo     // Catch:{ Exception -> 0x0080 }
            r3.<init>()     // Catch:{ Exception -> 0x0080 }
            r3.setPackageName(r9)     // Catch:{ Exception -> 0x0080 }
            r3.setSupportVersion(r2)     // Catch:{ Exception -> 0x0080 }
            r3.setAuthActivityName(r8)     // Catch:{ Exception -> 0x0080 }
            if (r0 == 0) goto L_0x0074
            r0.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0074:
            return r3
        L_0x0075:
            if (r0 == 0) goto L_0x007f
            r0.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r8 = move-exception
            r8.printStackTrace()
        L_0x007f:
            return r1
        L_0x0080:
            r8 = move-exception
            goto L_0x0087
        L_0x0082:
            r8 = move-exception
            r0 = r1
            goto L_0x009c
        L_0x0085:
            r8 = move-exception
            r0 = r1
        L_0x0087:
            java.lang.String r9 = b     // Catch:{ all -> 0x009b }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x009b }
            com.sina.weibo.sdk.utils.LogUtil.c(r9, r8)     // Catch:{ all -> 0x009b }
            if (r0 == 0) goto L_0x009a
            r0.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r8 = move-exception
            r8.printStackTrace()
        L_0x009a:
            return r1
        L_0x009b:
            r8 = move-exception
        L_0x009c:
            if (r0 == 0) goto L_0x00a6
            r0.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x00a6
        L_0x00a2:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00a6:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.WeiboAppManager.a(android.content.Context, java.lang.String):com.sina.weibo.sdk.auth.WbAppInfo");
    }
}
