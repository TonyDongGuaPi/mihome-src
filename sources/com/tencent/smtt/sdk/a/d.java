package com.tencent.smtt.sdk.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.tencent.smtt.sdk.TbsConfig;
import com.xiaomi.youpin.share.ShareObject;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class d {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f9122a = -1;
        public int b = -1;
        public String c = "";
        public String d = "0";
        public String e = null;
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        public String f9123a;
        public String b;

        private b() {
            this.f9123a = "";
            this.b = "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0082, code lost:
        if (android.text.TextUtils.isEmpty(r0.f9123a) == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0084, code lost:
        r2.setClassName(r0.b, r0.f9123a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00cc, code lost:
        if (android.text.TextUtils.isEmpty(r0.f9123a) == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e2, code lost:
        if (android.text.TextUtils.isEmpty(r0.f9123a) == false) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0120 A[Catch:{ ActivityNotFoundException -> 0x0149 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r6, java.lang.String r7, java.util.HashMap<java.lang.String, java.lang.String> r8, com.tencent.smtt.sdk.WebView r9) {
        /*
            if (r6 != 0) goto L_0x0004
            r6 = 3
            return r6
        L_0x0004:
            boolean r0 = a((java.lang.String) r7)
            if (r0 != 0) goto L_0x001b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http://"
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
        L_0x001b:
            r0 = 2
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ Exception -> 0x014a }
            if (r7 != 0) goto L_0x0023
            return r0
        L_0x0023:
            com.tencent.smtt.sdk.a.d$a r1 = a((android.content.Context) r6)
            int r2 = r1.f9122a
            r3 = -1
            r4 = 4
            if (r2 != r3) goto L_0x002e
            return r4
        L_0x002e:
            int r2 = r1.f9122a
            r3 = 33
            if (r2 != r0) goto L_0x003a
            int r2 = r1.b
            if (r2 >= r3) goto L_0x003a
            r6 = 5
            return r6
        L_0x003a:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r5 = "android.intent.action.VIEW"
            r2.<init>(r5)
            int r5 = r1.f9122a
            if (r5 != r0) goto L_0x008c
            int r0 = r1.b
            if (r0 < r3) goto L_0x0058
            int r0 = r1.b
            r3 = 39
            if (r0 > r3) goto L_0x0058
            java.lang.String r0 = "com.tencent.mtt"
            java.lang.String r1 = "com.tencent.mtt.MainActivity"
        L_0x0053:
            r2.setClassName(r0, r1)
            goto L_0x00e5
        L_0x0058:
            int r0 = r1.b
            r3 = 40
            if (r0 < r3) goto L_0x0069
            int r0 = r1.b
            r3 = 45
            if (r0 > r3) goto L_0x0069
            java.lang.String r0 = "com.tencent.mtt"
            java.lang.String r1 = "com.tencent.mtt.SplashActivity"
            goto L_0x0053
        L_0x0069:
            int r0 = r1.b
            r1 = 46
            if (r0 < r1) goto L_0x00e5
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r0 = "com.tencent.QQBrowser.action.VIEW"
            r2.<init>(r0)
            com.tencent.smtt.sdk.a.d$b r0 = a(r6, r7)
            if (r0 == 0) goto L_0x00e5
            java.lang.String r1 = r0.f9123a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e5
        L_0x0084:
            java.lang.String r1 = r0.b
            java.lang.String r0 = r0.f9123a
            r2.setClassName(r1, r0)
            goto L_0x00e5
        L_0x008c:
            int r3 = r1.f9122a
            r5 = 1
            if (r3 != r5) goto L_0x00a3
            int r3 = r1.b
            if (r3 != r5) goto L_0x009a
            java.lang.String r0 = "com.tencent.qbx5"
            java.lang.String r1 = "com.tencent.qbx5.MainActivity"
            goto L_0x0053
        L_0x009a:
            int r1 = r1.b
            if (r1 != r0) goto L_0x00e5
            java.lang.String r0 = "com.tencent.qbx5"
            java.lang.String r1 = "com.tencent.qbx5.SplashActivity"
            goto L_0x0053
        L_0x00a3:
            int r0 = r1.f9122a
            if (r0 != 0) goto L_0x00cf
            int r0 = r1.b
            r3 = 6
            if (r0 < r4) goto L_0x00b5
            int r0 = r1.b
            if (r0 > r3) goto L_0x00b5
            java.lang.String r0 = "com.tencent.qbx"
            java.lang.String r1 = "com.tencent.qbx.SplashActivity"
            goto L_0x0053
        L_0x00b5:
            int r0 = r1.b
            if (r0 <= r3) goto L_0x00e5
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r0 = "com.tencent.QQBrowser.action.VIEW"
            r2.<init>(r0)
            com.tencent.smtt.sdk.a.d$b r0 = a(r6, r7)
            if (r0 == 0) goto L_0x00e5
            java.lang.String r1 = r0.f9123a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e5
            goto L_0x0084
        L_0x00cf:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r0 = "com.tencent.QQBrowser.action.VIEW"
            r2.<init>(r0)
            com.tencent.smtt.sdk.a.d$b r0 = a(r6, r7)
            if (r0 == 0) goto L_0x00e5
            java.lang.String r1 = r0.f9123a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e5
            goto L_0x0084
        L_0x00e5:
            r2.setData(r7)
            if (r8 == 0) goto L_0x0110
            java.util.Set r7 = r8.keySet()
            if (r7 == 0) goto L_0x0110
            java.util.Iterator r7 = r7.iterator()
        L_0x00f4:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x0110
            java.lang.Object r0 = r7.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r8.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x00f4
            r2.putExtra(r0, r1)
            goto L_0x00f4
        L_0x0110:
            java.lang.String r7 = "loginType"
            int r8 = d(r6)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            r2.putExtra(r7, r8)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            r7 = 268435456(0x10000000, float:2.5243549E-29)
            r2.addFlags(r7)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            if (r9 == 0) goto L_0x0144
            android.graphics.Point r7 = new android.graphics.Point     // Catch:{ ActivityNotFoundException -> 0x0149 }
            int r8 = r9.getScrollX()     // Catch:{ ActivityNotFoundException -> 0x0149 }
            int r0 = r9.getScrollY()     // Catch:{ ActivityNotFoundException -> 0x0149 }
            r7.<init>(r8, r0)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            java.lang.String r8 = "AnchorPoint"
            r2.putExtra(r8, r7)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            android.graphics.Point r7 = new android.graphics.Point     // Catch:{ ActivityNotFoundException -> 0x0149 }
            int r8 = r9.getContentWidth()     // Catch:{ ActivityNotFoundException -> 0x0149 }
            int r9 = r9.getContentHeight()     // Catch:{ ActivityNotFoundException -> 0x0149 }
            r7.<init>(r8, r9)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            java.lang.String r8 = "ContentSize"
            r2.putExtra(r8, r7)     // Catch:{ ActivityNotFoundException -> 0x0149 }
        L_0x0144:
            r6.startActivity(r2)     // Catch:{ ActivityNotFoundException -> 0x0149 }
            r6 = 0
            return r6
        L_0x0149:
            return r4
        L_0x014a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.d.a(android.content.Context, java.lang.String, java.util.HashMap, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r4, java.lang.String r5, java.util.HashMap<java.lang.String, java.lang.String> r6, java.lang.String r7, com.tencent.smtt.sdk.WebView r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.pm.PackageManager r2 = r4.getPackageManager()     // Catch:{ Throwable -> 0x001d }
            if (r2 == 0) goto L_0x001d
            java.lang.String r3 = "com.tencent.mtt"
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch:{ Throwable -> 0x001d }
            if (r2 == 0) goto L_0x001d
            int r2 = r2.versionCode     // Catch:{ Throwable -> 0x001d }
            r3 = 601000(0x92ba8, float:8.4218E-40)
            if (r2 <= r3) goto L_0x001d
            r2 = 1
            goto L_0x001e
        L_0x001d:
            r2 = 0
        L_0x001e:
            java.lang.String r3 = "UTF-8"
            java.lang.String r3 = java.net.URLEncoder.encode(r5, r3)     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x0027
            r5 = r3
        L_0x0027:
            r1 = r2
        L_0x0028:
            if (r1 == 0) goto L_0x002d
            java.lang.String r1 = ",encoded=1"
            goto L_0x002f
        L_0x002d:
            java.lang.String r1 = ""
        L_0x002f:
            java.lang.String r2 = "mttbrowser://url="
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = ",product="
            r0.append(r5)
            java.lang.String r5 = "TBS"
            r0.append(r5)
            java.lang.String r5 = ",packagename="
            r0.append(r5)
            java.lang.String r5 = r4.getPackageName()
            r0.append(r5)
            java.lang.String r5 = ",from="
            r0.append(r5)
            r0.append(r7)
            java.lang.String r5 = ",version="
            r0.append(r5)
            java.lang.String r5 = "3.6.0.1249"
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            int r4 = a(r4, r5, r6, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.d.a(android.content.Context, java.lang.String, java.util.HashMap, java.lang.String, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d8 A[SYNTHETIC, Splitter:B:67:0x00d8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.smtt.sdk.a.d.a a(android.content.Context r9) {
        /*
            android.content.Context r0 = r9.getApplicationContext()
            java.lang.String r1 = "x5_proxy_setting"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            java.lang.String r1 = "qb_install_status"
            boolean r0 = r0.getBoolean(r1, r2)
            com.tencent.smtt.sdk.a.d$a r1 = new com.tencent.smtt.sdk.a.d$a
            r1.<init>()
            if (r0 == 0) goto L_0x0019
            return r1
        L_0x0019:
            android.content.pm.PackageManager r0 = r9.getPackageManager()     // Catch:{ Exception -> 0x0105 }
            r3 = 0
            r4 = 2
            java.lang.String r5 = "com.tencent.mtt"
            android.content.pm.PackageInfo r5 = r0.getPackageInfo(r5, r2)     // Catch:{ NameNotFoundException -> 0x0066 }
            r1.f9122a = r4     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r3 = "com.tencent.mtt"
            r1.e = r3     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r3 = "ADRQB_"
            r1.c = r3     // Catch:{ NameNotFoundException -> 0x0067 }
            if (r5 == 0) goto L_0x0067
            int r3 = r5.versionCode     // Catch:{ NameNotFoundException -> 0x0067 }
            r6 = 420000(0x668a0, float:5.88545E-40)
            if (r3 <= r6) goto L_0x0067
            int r3 = r5.versionCode     // Catch:{ NameNotFoundException -> 0x0067 }
            r1.b = r3     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0067 }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r6 = r1.c     // Catch:{ NameNotFoundException -> 0x0067 }
            r3.append(r6)     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r6 = r5.versionName     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r7 = "\\."
            java.lang.String r8 = ""
            java.lang.String r6 = r6.replaceAll(r7, r8)     // Catch:{ NameNotFoundException -> 0x0067 }
            r3.append(r6)     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r3 = r3.toString()     // Catch:{ NameNotFoundException -> 0x0067 }
            r1.c = r3     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r3 = r5.versionName     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r6 = "\\."
            java.lang.String r7 = ""
            java.lang.String r3 = r3.replaceAll(r6, r7)     // Catch:{ NameNotFoundException -> 0x0067 }
            r1.d = r3     // Catch:{ NameNotFoundException -> 0x0067 }
            return r1
        L_0x0066:
            r5 = r3
        L_0x0067:
            java.lang.String r3 = "com.tencent.qbx"
            android.content.pm.PackageInfo r3 = r0.getPackageInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x0078 }
            r1.f9122a = r2     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.String r5 = "com.tencent.qbx"
            r1.e = r5     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.String r5 = "ADRQBX_"
            r1.c = r5     // Catch:{ NameNotFoundException -> 0x0079 }
            goto L_0x00d6
        L_0x0078:
            r3 = r5
        L_0x0079:
            java.lang.String r5 = "com.tencent.qbx5"
            android.content.pm.PackageInfo r5 = r0.getPackageInfo(r5, r2)     // Catch:{ NameNotFoundException -> 0x008c }
            r3 = 1
            r1.f9122a = r3     // Catch:{ NameNotFoundException -> 0x008d }
            java.lang.String r3 = "com.tencent.qbx5"
            r1.e = r3     // Catch:{ NameNotFoundException -> 0x008d }
            java.lang.String r3 = "ADRQBX5_"
            r1.c = r3     // Catch:{ NameNotFoundException -> 0x008d }
        L_0x008a:
            r3 = r5
            goto L_0x00d6
        L_0x008c:
            r5 = r3
        L_0x008d:
            java.lang.String r3 = "com.tencent.mtt"
            android.content.pm.PackageInfo r3 = r0.getPackageInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x009e }
            java.lang.String r5 = "com.tencent.mtt"
            r1.e = r5     // Catch:{ NameNotFoundException -> 0x009f }
            r1.f9122a = r4     // Catch:{ NameNotFoundException -> 0x009f }
            java.lang.String r5 = "ADRQB_"
            r1.c = r5     // Catch:{ NameNotFoundException -> 0x009f }
            goto L_0x00d6
        L_0x009e:
            r3 = r5
        L_0x009f:
            java.lang.String r5 = "com.tencent.mtt.x86"
            android.content.pm.PackageInfo r5 = r0.getPackageInfo(r5, r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r3 = "com.tencent.mtt.x86"
            r1.e = r3     // Catch:{ Exception -> 0x00b1 }
            r1.f9122a = r4     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r3 = "ADRQB_"
            r1.c = r3     // Catch:{ Exception -> 0x00b1 }
            goto L_0x008a
        L_0x00b0:
            r5 = r3
        L_0x00b1:
            java.lang.String r3 = "http://mdc.html5.qq.com/mh?channel_id=50079&u="
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x008a }
            com.tencent.smtt.sdk.a.d$b r9 = a(r9, r3)     // Catch:{ Exception -> 0x008a }
            if (r9 == 0) goto L_0x008a
            java.lang.String r3 = r9.b     // Catch:{ Exception -> 0x008a }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x008a }
            if (r3 != 0) goto L_0x008a
            java.lang.String r3 = r9.b     // Catch:{ Exception -> 0x008a }
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r2)     // Catch:{ Exception -> 0x008a }
            java.lang.String r9 = r9.b     // Catch:{ Exception -> 0x00d5 }
            r1.e = r9     // Catch:{ Exception -> 0x00d5 }
            r1.f9122a = r4     // Catch:{ Exception -> 0x00d5 }
            java.lang.String r9 = "ADRQB_"
            r1.c = r9     // Catch:{ Exception -> 0x00d5 }
        L_0x00d5:
            r3 = r0
        L_0x00d6:
            if (r3 == 0) goto L_0x0105
            int r9 = r3.versionCode     // Catch:{ Exception -> 0x0105 }
            r1.b = r9     // Catch:{ Exception -> 0x0105 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0105 }
            r9.<init>()     // Catch:{ Exception -> 0x0105 }
            java.lang.String r0 = r1.c     // Catch:{ Exception -> 0x0105 }
            r9.append(r0)     // Catch:{ Exception -> 0x0105 }
            java.lang.String r0 = r3.versionName     // Catch:{ Exception -> 0x0105 }
            java.lang.String r2 = "\\."
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replaceAll(r2, r4)     // Catch:{ Exception -> 0x0105 }
            r9.append(r0)     // Catch:{ Exception -> 0x0105 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0105 }
            r1.c = r9     // Catch:{ Exception -> 0x0105 }
            java.lang.String r9 = r3.versionName     // Catch:{ Exception -> 0x0105 }
            java.lang.String r0 = "\\."
            java.lang.String r2 = ""
            java.lang.String r9 = r9.replaceAll(r0, r2)     // Catch:{ Exception -> 0x0105 }
            r1.d = r9     // Catch:{ Exception -> 0x0105 }
        L_0x0105:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.d.a(android.content.Context):com.tencent.smtt.sdk.a.d$a");
    }

    private static b a(Context context, Uri uri) {
        Intent intent = new Intent("com.tencent.QQBrowser.action.VIEW");
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        b bVar = new b();
        for (ResolveInfo next : queryIntentActivities) {
            String str = next.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                bVar.f9123a = next.activityInfo.name;
                bVar.b = next.activityInfo.packageName;
                return bVar;
            } else if (str.contains("com.tencent.qbx")) {
                bVar.f9123a = next.activityInfo.name;
                bVar.b = next.activityInfo.packageName;
            }
        }
        return bVar;
    }

    public static boolean a(Context context, long j, long j2) {
        a a2 = a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a2.d).longValue() >= j) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (((long) a2.b) >= j2) {
            return true;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0079 A[Catch:{ Exception -> 0x0082 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5, java.lang.String r6, int r7, java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, android.os.Bundle r10) {
        /*
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "com.tencent.QQBrowser.action.sdk.document"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0082 }
            if (r9 == 0) goto L_0x002f
            java.util.Set r1 = r9.keySet()     // Catch:{ Exception -> 0x0082 }
            if (r1 == 0) goto L_0x002f
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x0082 }
        L_0x0013:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x0082 }
            if (r2 == 0) goto L_0x002f
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0082 }
            java.lang.Object r3 = r9.get(r2)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0082 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0082 }
            if (r4 != 0) goto L_0x0013
            r0.putExtra(r2, r3)     // Catch:{ Exception -> 0x0082 }
            goto L_0x0013
        L_0x002f:
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x0082 }
            r9.<init>(r6)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "key_reader_sdk_id"
            r2 = 3
            r0.putExtra(r1, r2)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "key_reader_sdk_type"
            r0.putExtra(r1, r7)     // Catch:{ Exception -> 0x0082 }
            r1 = 1
            if (r7 != 0) goto L_0x0048
            java.lang.String r7 = "key_reader_sdk_path"
        L_0x0044:
            r0.putExtra(r7, r6)     // Catch:{ Exception -> 0x0082 }
            goto L_0x004d
        L_0x0048:
            if (r7 != r1) goto L_0x004d
            java.lang.String r7 = "key_reader_sdk_url"
            goto L_0x0044
        L_0x004d:
            java.lang.String r6 = "key_reader_sdk_format"
            r0.putExtra(r6, r8)     // Catch:{ Exception -> 0x0082 }
            android.net.Uri r6 = android.net.Uri.fromFile(r9)     // Catch:{ Exception -> 0x0082 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082 }
            r7.<init>()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r9 = "mtt/"
            r7.append(r9)     // Catch:{ Exception -> 0x0082 }
            r7.append(r8)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0082 }
            r0.setDataAndType(r6, r7)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r6 = "loginType"
            android.content.Context r7 = r5.getApplicationContext()     // Catch:{ Exception -> 0x0082 }
            int r7 = d(r7)     // Catch:{ Exception -> 0x0082 }
            r0.putExtra(r6, r7)     // Catch:{ Exception -> 0x0082 }
            if (r10 == 0) goto L_0x007e
            java.lang.String r6 = "key_reader_sdk_extrals"
            r0.putExtra(r6, r10)     // Catch:{ Exception -> 0x0082 }
        L_0x007e:
            r5.startActivity(r0)     // Catch:{ Exception -> 0x0082 }
            return r1
        L_0x0082:
            r5 = move-exception
            r5.printStackTrace()
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.d.a(android.content.Context, java.lang.String, int, java.lang.String, java.util.HashMap, android.os.Bundle):boolean");
    }

    public static boolean a(Context context, String str, HashMap<String, String> hashMap) {
        boolean z;
        Set<String> keySet;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.setDataAndType(parse, ShareObject.e);
        if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
            for (String next : keySet) {
                String str2 = hashMap.get(next);
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra(next, str2);
                }
            }
        }
        try {
            intent.putExtra("loginType", d(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (!z) {
            try {
                intent.setComponent((ComponentName) null);
                context.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf("://");
        int indexOf2 = trim.toLowerCase().indexOf(46);
        if (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) {
            return trim.toLowerCase().contains("://");
        }
        return false;
    }

    public static boolean b(Context context) {
        return a(context).f9122a != -1;
    }

    public static boolean c(Context context) {
        a a2 = a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a2.d).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (a2.b >= 601500) {
            return true;
        }
        return z;
    }

    private static int d(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals(TbsConfig.APP_QQ)) {
            return 13;
        }
        if (str.equals(TbsConfig.APP_QZONE)) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        return str.equals("com.tencent.mm") ? 24 : 26;
    }
}
