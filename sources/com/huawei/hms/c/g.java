package com.huawei.hms.c;

import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.hms.support.log.a;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Locale;

public class g {
    public static boolean a() {
        String str = "";
        String str2 = "";
        try {
            Object a2 = a("android.os.SystemProperties", "get", new Class[]{String.class}, new Object[]{"ro.product.locale.language"});
            Object a3 = a("android.os.SystemProperties", "get", new Class[]{String.class}, new Object[]{"ro.product.locale.region"});
            if (a2 != null) {
                str = (String) a2;
            }
            if (a3 != null) {
                str2 = (String) a3;
            }
        } catch (Exception e) {
            a.d("Util", "can not get language and region:" + e.getMessage());
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return b();
        }
        if (!"zh".equalsIgnoreCase(str) || !"cn".equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    public static boolean b() {
        return "cn".equalsIgnoreCase(Locale.getDefault().getCountry());
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e A[SYNTHETIC, Splitter:B:19:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object a(java.lang.String r5, java.lang.String r6, java.lang.Class<?>[] r7, java.lang.Object[] r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x00a7
            if (r8 == 0) goto L_0x00a7
            int r1 = r7.length
            int r2 = r8.length
            if (r1 == r2) goto L_0x000b
            goto L_0x00a7
        L_0x000b:
            java.lang.Object r1 = a((java.lang.String) r5)
            if (r1 != 0) goto L_0x0012
            return r0
        L_0x0012:
            java.lang.Class r2 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0017 }
            goto L_0x002e
        L_0x0017:
            java.lang.String r2 = "Util"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "can not find class:"
            r3.append(r4)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.huawei.hms.support.log.a.d(r2, r5)
            r2 = r0
        L_0x002e:
            if (r2 == 0) goto L_0x004b
            java.lang.reflect.Method r5 = r2.getMethod(r6, r7)     // Catch:{ NoSuchMethodException -> 0x0035 }
            goto L_0x004c
        L_0x0035:
            java.lang.String r5 = "Util"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "can not find method:"
            r7.append(r2)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.huawei.hms.support.log.a.d(r5, r6)
        L_0x004b:
            r5 = r0
        L_0x004c:
            if (r5 == 0) goto L_0x00a6
            java.lang.Object r5 = r5.invoke(r1, r8)     // Catch:{ IllegalAccessException -> 0x008b, IllegalArgumentException -> 0x006f, InvocationTargetException -> 0x0053 }
            return r5
        L_0x0053:
            r5 = move-exception
            java.lang.String r6 = "Util"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "method can not invoke:"
            r7.append(r8)
            java.lang.String r5 = r5.getMessage()
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.huawei.hms.support.log.a.d(r6, r5)
            goto L_0x00a6
        L_0x006f:
            r5 = move-exception
            java.lang.String r6 = "Util"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "method can not invoke:"
            r7.append(r8)
            java.lang.String r5 = r5.getMessage()
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.huawei.hms.support.log.a.d(r6, r5)
            goto L_0x00a6
        L_0x008b:
            r5 = move-exception
            java.lang.String r6 = "Util"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "method can not invoke:"
            r7.append(r8)
            java.lang.String r5 = r5.getMessage()
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.huawei.hms.support.log.a.d(r6, r5)
        L_0x00a6:
            return r0
        L_0x00a7:
            java.lang.String r5 = "Util"
            java.lang.String r6 = "invokeFun params invalid"
            com.huawei.hms.support.log.a.a(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.c.g.a(java.lang.String, java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.Object");
    }

    public static Object a(String str) {
        Class<?> cls;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException unused) {
            a.d("Util", "can not find class:" + str);
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            a.d("Util", "class creat instance error :" + e.getMessage());
            return null;
        } catch (IllegalAccessException e2) {
            a.d("Util", "class creat instance error :" + e2.getMessage());
            return null;
        }
    }

    public static String a(Context context) {
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            a.d("Util", "In getMetaDataAppId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get(Constants.K)) != null) {
                return String.valueOf(obj);
            }
            a.d("Util", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (PackageManager.NameNotFoundException unused) {
            a.d("Util", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        }
    }

    public static String a(Context context, String str) {
        if (context == null) {
            a.d("Util", "In getAppName, context is null.");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            a.d("Util", "In getAppName, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = context.getPackageName();
            }
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
            if (applicationLabel == null) {
                return "";
            }
            return applicationLabel.toString();
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            a.d("Util", "In getAppName, Failed to get app name.");
            return "";
        }
    }

    public static void a(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (Exception e) {
            a.d("Util", "On unBindServiceException:" + e.getMessage());
        }
    }
}
