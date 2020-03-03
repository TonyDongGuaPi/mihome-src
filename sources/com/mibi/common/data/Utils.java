package com.mibi.common.data;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.mibi.common.R;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static final long f7549a = 300000;
    public static final long b = 1000;
    public static final String c = "400-100-3399";
    private static final String d = "Utils";
    private static int e = 1;

    public static class ValueDivided {

        /* renamed from: a  reason: collision with root package name */
        public String f7550a;
        public String b;
    }

    private Utils() {
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.charAt(0) == '/') {
            str2 = str2.substring(1);
        }
        return str + "/" + str2;
    }

    public static synchronized int a() {
        int i;
        synchronized (Utils.class) {
            i = e;
            e = i + 1;
        }
        return i;
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.isActiveNetworkMetered();
    }

    public static int e(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    public static String a(long j) {
        if (j % 100 == 0) {
            return String.format("%d", new Object[]{Long.valueOf(j / 100)});
        } else if (j % 10 == 0) {
            double d2 = (double) j;
            Double.isNaN(d2);
            return String.format("%.1f", new Object[]{Double.valueOf(d2 / 100.0d)});
        } else {
            double d3 = (double) j;
            Double.isNaN(d3);
            return String.format("%.2f", new Object[]{Double.valueOf(d3 / 100.0d)});
        }
    }

    public static String b(long j) {
        double d2 = (double) j;
        Double.isNaN(d2);
        return String.format("%.2f", new Object[]{Double.valueOf(d2 / 100.0d)});
    }

    public static void a(Context context, View view, boolean z) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getApplicationContext().getSystemService("input_method");
            if (z) {
                inputMethodManager.showSoftInput(view, 1);
            } else {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static ValueDivided c(long j) {
        ValueDivided valueDivided = new ValueDivided();
        String a2 = a(j);
        int indexOf = a2.indexOf(".");
        if (indexOf == -1) {
            valueDivided.f7550a = a2;
            valueDivided.b = "";
        } else {
            valueDivided.f7550a = a2.substring(0, indexOf);
            valueDivided.b = a2.substring(indexOf + 1);
        }
        return valueDivided;
    }

    public static void a(Context context, View view) {
        if (view != null && context != null) {
            ((InputMethodManager) context.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean a(Context context, String str, boolean z) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static void b(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static String a(Context context, String str, String str2) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static long a(Context context, String str, long j) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j);
    }

    public static void b(Context context, String str, long j) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static void f(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.clear();
        edit.apply();
    }

    public static long[] a(List<Long> list) {
        long[] jArr = new long[list.size()];
        int i = 0;
        for (Long longValue : list) {
            jArr[i] = longValue.longValue();
            i++;
        }
        return jArr;
    }

    public static long[] a(Set<Long> set) {
        long[] jArr = new long[set.size()];
        int i = 0;
        for (Long longValue : set) {
            jArr[i] = longValue.longValue();
            i++;
        }
        return jArr;
    }

    public static boolean g(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        if (r5 != null) goto L_0x002b;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047 A[SYNTHETIC, Splitter:B:30:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0053 A[SYNTHETIC, Splitter:B:39:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0058 A[Catch:{ IOException -> 0x005b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            java.io.InputStream r3 = r3.open(r4)     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x0035, all -> 0x0033 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0035, all -> 0x0033 }
            r4.createNewFile()     // Catch:{ IOException -> 0x0035, all -> 0x0033 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0035, all -> 0x0033 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0035, all -> 0x0033 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0031, all -> 0x002f }
        L_0x001b:
            int r0 = r3.read(r4)     // Catch:{ IOException -> 0x0031, all -> 0x002f }
            if (r0 <= 0) goto L_0x0025
            r5.write(r4, r1, r0)     // Catch:{ IOException -> 0x0031, all -> 0x002f }
            goto L_0x001b
        L_0x0025:
            r1 = 1
            if (r3 == 0) goto L_0x002b
            r3.close()     // Catch:{ IOException -> 0x004d }
        L_0x002b:
            r5.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x004d
        L_0x002f:
            r4 = move-exception
            goto L_0x0050
        L_0x0031:
            r4 = move-exception
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            goto L_0x0051
        L_0x0035:
            r4 = move-exception
            r5 = r0
        L_0x0037:
            r0 = r3
            goto L_0x003e
        L_0x0039:
            r4 = move-exception
            r3 = r0
            goto L_0x0051
        L_0x003c:
            r4 = move-exception
            r5 = r0
        L_0x003e:
            java.lang.String r3 = "Utils"
            java.lang.String r2 = "Utils retrieveFileFromAssets IOException "
            android.util.Log.e(r3, r2, r4)     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004a
            r0.close()     // Catch:{ IOException -> 0x004d }
        L_0x004a:
            if (r5 == 0) goto L_0x004d
            goto L_0x002b
        L_0x004d:
            return r1
        L_0x004e:
            r4 = move-exception
            r3 = r0
        L_0x0050:
            r0 = r5
        L_0x0051:
            if (r3 == 0) goto L_0x0056
            r3.close()     // Catch:{ IOException -> 0x005b }
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Utils.c(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034 A[SYNTHETIC, Splitter:B:18:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003b A[SYNTHETIC, Splitter:B:26:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IOException -> 0x0038, all -> 0x0030 }
            java.io.InputStream r3 = r3.open(r4)     // Catch:{ IOException -> 0x0038, all -> 0x0030 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            r4.<init>()     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            java.util.Scanner r1 = new java.util.Scanner     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0039, all -> 0x002e }
        L_0x0013:
            boolean r2 = r1.hasNextLine()     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            if (r2 == 0) goto L_0x0021
            java.lang.String r2 = r1.nextLine()     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            r4.append(r2)     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            goto L_0x0013
        L_0x0021:
            r1.close()     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0039, all -> 0x002e }
            if (r3 == 0) goto L_0x002d
            r3.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            return r4
        L_0x002e:
            r4 = move-exception
            goto L_0x0032
        L_0x0030:
            r4 = move-exception
            r3 = r0
        L_0x0032:
            if (r3 == 0) goto L_0x0037
            r3.close()     // Catch:{ IOException -> 0x0037 }
        L_0x0037:
            throw r4
        L_0x0038:
            r3 = r0
        L_0x0039:
            if (r3 == 0) goto L_0x003e
            r3.close()     // Catch:{ IOException -> 0x003e }
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Utils.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static boolean a(Object... objArr) {
        for (Object assertNotNull : objArr) {
            Assert.assertNotNull(assertNotNull);
        }
        return true;
    }

    public static boolean a(String... strArr) {
        for (String isEmpty : strArr) {
            if (TextUtils.isEmpty(isEmpty)) {
                return false;
            }
        }
        return true;
    }

    public static String a(CharSequence charSequence, Object... objArr) {
        return TextUtils.join(charSequence, objArr);
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().getLaunchIntentForPackage(str) != null;
        } catch (Exception e2) {
            Log.e("Utils", "Utils isAppExists Exception ", e2);
            return false;
        }
    }

    public static boolean c(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            try {
                if (!(context instanceof Activity)) {
                    launchIntentForPackage.setFlags(C.ENCODING_PCM_MU_LAW);
                }
                context.startActivity(launchIntentForPackage);
                return true;
            } catch (Exception e2) {
                Log.e("Utils", "Utils launchApp Exception ", e2);
                return false;
            }
        } catch (Exception e3) {
            Log.e("Utils", "Utils launchApp getLaunchIntentForPackage exception ", e3);
            return false;
        }
    }

    public static void a(Context context, String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        a(context, str, str2, pendingIntent, pendingIntent2, (String) null, a(), 0);
    }

    public static void a(Context context, String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str3, int i, int i2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Notification.Builder autoCancel = new Notification.Builder(context).setSmallIcon(R.drawable.mibi_ic_milicenter_status_bar).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.mibi_ic_milicenter)).setTicker(str).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setDeleteIntent(pendingIntent2).setAutoCancel(true);
            if (i2 == 2) {
                autoCancel.setOngoing(true);
            } else if (i2 == 8) {
                autoCancel.setOnlyAlertOnce(true);
            } else if (i2 == 16) {
                autoCancel.setAutoCancel(true);
            }
            ((NotificationManager) context.getApplicationContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(str3, i, autoCancel.build());
        }
    }

    public static void d(Context context, String str) {
        ((NotificationManager) context.getApplicationContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancel(str, 0);
    }

    public static boolean a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!PhoneNumberUtils.isDialable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b() {
        return MiuiBuild.d();
    }

    public static boolean h(Context context) {
        try {
            return ((Boolean) Class.forName("android.app.Activity").getMethod("isInMultiWindowMode", new Class[0]).invoke(context, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static void a(Activity activity, String str) {
        activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static void a(Activity activity) {
        a(activity, c);
    }

    public static boolean a(Context context, Intent intent) {
        ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(context.getPackageManager(), 0);
        if (resolveActivityInfo == null) {
            return false;
        }
        return TextUtils.equals(context.getPackageName(), resolveActivityInfo.packageName);
    }

    public static Intent b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Intent.parseUri(str, 1);
            } catch (URISyntaxException e2) {
                Log.e("Utils", "Utils parseIntentUri URISyntaxException ", e2);
            }
        }
        return null;
    }

    public static boolean e(Context context, String str) {
        if (context == null) {
            return false;
        }
        Intent b2 = b(str);
        if (!b(context, b2)) {
            return false;
        }
        context.startActivity(b2);
        return true;
    }

    public static String i(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            return telephonyManager.getLine1Number();
        }
        return null;
    }

    public static boolean a(Session session) {
        return session.l().getBoolean(CommonConstants.aV, false);
    }

    public static void b(Session session) {
        SharedPreferences.Editor edit = session.l().edit();
        edit.remove(CommonConstants.aV);
        edit.apply();
    }

    public static void c() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("current thread is not main");
        }
    }

    public static boolean b(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }
}
