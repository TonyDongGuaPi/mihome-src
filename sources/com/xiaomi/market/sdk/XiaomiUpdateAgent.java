package com.xiaomi.market.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.exoplayer2.C;
import com.xiaomi.market.sdk.Connection;
import com.xiaomi.market.sdk.Constants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class XiaomiUpdateAgent {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11118a = false;
    /* access modifiers changed from: private */
    public static boolean b = false;
    private static final String c = "MarketUpdateAgent";
    /* access modifiers changed from: private */
    public static WeakReference<Context> d = new WeakReference<>((Object) null);
    /* access modifiers changed from: private */
    public static boolean e = false;
    /* access modifiers changed from: private */
    public static boolean f = false;
    /* access modifiers changed from: private */
    public static LocalAppInfo g;
    /* access modifiers changed from: private */
    public static UpdateInfo h;
    /* access modifiers changed from: private */
    public static WeakReference<XiaomiUpdateListener> i = new WeakReference<>((Object) null);
    private static Constants.UpdateMethod j = (Utils.b() ? Constants.UpdateMethod.DOWNLOAD_MANAGER : Constants.UpdateMethod.MARKET);
    private static boolean k;
    /* access modifiers changed from: private */
    public static boolean l;
    /* access modifiers changed from: private */
    public static AbTestIdentifier m = AbTestIdentifier.ANDROID_ID;

    public static int b() {
        return 8;
    }

    public static void a(boolean z) {
        k = z;
        Utils.b = k;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r3, boolean r4) {
        /*
            java.lang.Class<com.xiaomi.market.sdk.XiaomiUpdateAgent> r0 = com.xiaomi.market.sdk.XiaomiUpdateAgent.class
            monitor-enter(r0)
            if (r3 == 0) goto L_0x003e
            boolean r1 = b     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x000a
            goto L_0x003e
        L_0x000a:
            com.xiaomi.market.sdk.AppGlobal.a(r3)     // Catch:{ all -> 0x003b }
            r1 = 1
            b = r1     // Catch:{ all -> 0x003b }
            android.content.Context r2 = r3.getApplicationContext()     // Catch:{ all -> 0x003b }
            com.xiaomi.market.sdk.Client.a(r2)     // Catch:{ all -> 0x003b }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x003b }
            r2.<init>(r3)     // Catch:{ all -> 0x003b }
            d = r2     // Catch:{ all -> 0x003b }
            l = r4     // Catch:{ all -> 0x003b }
            boolean r3 = f11118a     // Catch:{ all -> 0x003b }
            r4 = 0
            if (r3 != 0) goto L_0x002e
            g = r4     // Catch:{ all -> 0x003b }
            h = r4     // Catch:{ all -> 0x003b }
            com.xiaomi.market.sdk.Constants.a()     // Catch:{ all -> 0x003b }
            f11118a = r1     // Catch:{ all -> 0x003b }
        L_0x002e:
            com.xiaomi.market.sdk.XiaomiUpdateAgent$CheckUpdateTask r3 = new com.xiaomi.market.sdk.XiaomiUpdateAgent$CheckUpdateTask     // Catch:{ all -> 0x003b }
            r3.<init>()     // Catch:{ all -> 0x003b }
            r4 = 0
            java.lang.Void[] r4 = new java.lang.Void[r4]     // Catch:{ all -> 0x003b }
            r3.execute(r4)     // Catch:{ all -> 0x003b }
            monitor-exit(r0)
            return
        L_0x003b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x003e:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.XiaomiUpdateAgent.a(android.content.Context, boolean):void");
    }

    public static void b(boolean z) {
        Constants.a(z);
    }

    public static void a(ServerType serverType) {
        Constants.a(serverType);
    }

    public static boolean a() {
        Context context = (Context) d.get();
        if (context == null) {
            return false;
        }
        Client.a(context);
        return g();
    }

    public static void c(boolean z) {
        e = z;
    }

    public static void a(XiaomiUpdateListener xiaomiUpdateListener) {
        if (xiaomiUpdateListener != null) {
            i = new WeakReference<>(xiaomiUpdateListener);
        } else {
            i = null;
        }
    }

    public static void a(Constants.UpdateMethod updateMethod) {
        j = updateMethod;
    }

    public static long c() {
        return DownloadInstallManager.a(AppGlobal.a()).a();
    }

    @Deprecated
    public static void d(boolean z) {
        m = z ? AbTestIdentifier.IMEI_MD5 : AbTestIdentifier.ANDROID_ID;
    }

    public static void a(AbTestIdentifier abTestIdentifier) {
        m = abTestIdentifier;
    }

    public static AbTestIdentifier d() {
        return m;
    }

    public static boolean e() {
        return f;
    }

    static Context f() {
        return (Context) d.get();
    }

    static LocalAppInfo a(Context context) {
        PackageInfo packageInfo;
        LocalAppInfo a2 = LocalAppInfo.a(context.getPackageName());
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(a2.f11109a, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.b(c, "get package info failed");
            packageInfo = null;
        }
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return null;
        }
        a2.b = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
        a2.c = packageInfo.versionCode;
        a2.d = packageInfo.versionName;
        a2.e = Coder.a(String.valueOf(packageInfo.signatures[0].toChars()));
        a2.f = packageInfo.applicationInfo.sourceDir;
        a2.g = Coder.a(new File(a2.f));
        return a2;
    }

    static class UpdateInfo {

        /* renamed from: a  reason: collision with root package name */
        String f11119a;
        int b;
        int c;
        String d;
        int e;
        String f;
        String g;
        String h;
        long i;
        String j = "";
        String k = "";
        long l;

        UpdateInfo() {
        }

        public String toString() {
            return "UpdateInfo:\nhost = " + this.f11119a + "\nfitness = " + this.c + "\nupdateLog = " + this.d + "\nversionCode = " + this.e + "\nversionName = " + this.f + "\napkUrl = " + this.g + "\napkHash = " + this.h + "\napkSize = " + this.i + "\ndiffUrl = " + this.j + "\ndiffHash = " + this.k + "\ndiffSize = " + this.l;
        }
    }

    private static class CheckUpdateTask extends AsyncTask<Void, Void, Integer> {
        private CheckUpdateTask() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            Log.a(XiaomiUpdateAgent.c, "start to check update");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(Void... voidArr) {
            Context context = (Context) XiaomiUpdateAgent.d.get();
            if (context == null) {
                return 4;
            }
            if (!Utils.b(context)) {
                return 3;
            }
            if (!Utils.c(context) && XiaomiUpdateAgent.e) {
                return 2;
            }
            LocalAppInfo unused = XiaomiUpdateAgent.g = XiaomiUpdateAgent.a(context);
            if (XiaomiUpdateAgent.g == null) {
                return 5;
            }
            Connection connection = new Connection(Constants.c);
            connection.getClass();
            Connection.Parameter parameter = new Connection.Parameter(connection);
            parameter.a("info", a());
            parameter.a("packageName", XiaomiUpdateAgent.g.f11109a);
            parameter.a("versionCode", XiaomiUpdateAgent.g.c + "");
            parameter.a("apkHash", XiaomiUpdateAgent.g.g);
            parameter.a("signature", XiaomiUpdateAgent.g.e);
            parameter.a("sdk", String.valueOf(Client.k));
            parameter.a("os", Client.l);
            parameter.a("la", Client.b());
            parameter.a("co", Client.a());
            parameter.a("lo", Client.c());
            parameter.a("androidId", Client.n);
            parameter.a("device", Client.i());
            parameter.a("deviceType", String.valueOf(Client.j()));
            parameter.a(Constants.t, Client.l());
            parameter.a("model", Client.h());
            parameter.a(Constants.n, "8");
            parameter.a("debug", XiaomiUpdateAgent.l ? "1" : "0");
            parameter.a(Constants.p, Client.g());
            parameter.a(Constants.q, Client.d());
            parameter.a(Constants.J, String.valueOf(XiaomiUpdateAgent.m.ordinal()));
            if (XiaomiUpdateAgent.f || XiaomiUpdateAgent.m == AbTestIdentifier.IMEI_MD5) {
                parameter.a("imei", Client.m());
            }
            if (Connection.NetworkError.OK == connection.d()) {
                UpdateInfo unused2 = XiaomiUpdateAgent.h = a(connection.a());
                if (XiaomiUpdateAgent.h != null) {
                    Log.d(XiaomiUpdateAgent.c, XiaomiUpdateAgent.h.toString());
                    return Integer.valueOf(XiaomiUpdateAgent.h.c == 0 ? 0 : 1);
                }
            }
            return 4;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            boolean unused = XiaomiUpdateAgent.b = false;
            if (num.intValue() == 0) {
                new CheckDownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else if (XiaomiUpdateAgent.i != null) {
                UpdateResponse updateResponse = new UpdateResponse();
                if (XiaomiUpdateAgent.i.get() != null) {
                    ((XiaomiUpdateListener) XiaomiUpdateAgent.i.get()).a(num.intValue(), updateResponse);
                }
            }
        }

        private String a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("screenSize", Client.b + "*" + Client.c);
                jSONObject.put(Constants.x, Client.d);
                jSONObject.put(Constants.y, Client.e);
                jSONObject.put(Constants.z, Client.f);
                jSONObject.put(Constants.A, Client.g);
                jSONObject.put("feature", Client.h);
                jSONObject.put(Constants.C, Client.i);
                jSONObject.put(Constants.D, Client.j);
                jSONObject.put("sdk", Client.k);
                jSONObject.put("version", Client.l);
                jSONObject.put("release", Client.m);
                return jSONObject.toString();
            } catch (JSONException unused) {
                return "";
            }
        }

        private UpdateInfo a(JSONObject jSONObject) {
            if (jSONObject == null) {
                Log.b(XiaomiUpdateAgent.c, "update info json obj null");
                return null;
            }
            if (Utils.b) {
                Log.a(XiaomiUpdateAgent.c, "updateInfo : " + jSONObject.toString());
            }
            UpdateInfo updateInfo = new UpdateInfo();
            try {
                updateInfo.f11119a = jSONObject.getString("host");
                updateInfo.c = jSONObject.getInt(Constants.L);
                updateInfo.b = jSONObject.getInt("source");
                updateInfo.d = jSONObject.getString(Constants.N);
                updateInfo.e = jSONObject.getInt("versionCode");
                updateInfo.f = jSONObject.getString("versionName");
                updateInfo.g = jSONObject.getString("apk");
                updateInfo.h = jSONObject.getString("apkHash");
                updateInfo.i = jSONObject.getLong(Constants.S);
                if (Patcher.b()) {
                    updateInfo.j = jSONObject.optString(Constants.T, "");
                    updateInfo.k = jSONObject.optString(Constants.U, "");
                    updateInfo.l = jSONObject.optLong(Constants.V, 0);
                }
                return updateInfo;
            } catch (JSONException e) {
                Log.b(XiaomiUpdateAgent.c, "get update info failed : " + e.toString());
                Log.b(XiaomiUpdateAgent.c, "original content : " + jSONObject.toString());
                return null;
            }
        }

        private static class CheckDownloadTask extends AsyncTask<Void, Void, Boolean> {
            private CheckDownloadTask() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Void... voidArr) {
                Context context = (Context) XiaomiUpdateAgent.d.get();
                if (context == null) {
                    return false;
                }
                return Boolean.valueOf(DownloadInstallManager.a(context).a(XiaomiUpdateAgent.g));
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (!bool.booleanValue() && XiaomiUpdateAgent.i != null) {
                    UpdateResponse updateResponse = new UpdateResponse();
                    updateResponse.f11115a = XiaomiUpdateAgent.h.d;
                    updateResponse.c = XiaomiUpdateAgent.h.e;
                    updateResponse.b = XiaomiUpdateAgent.h.f;
                    updateResponse.e = XiaomiUpdateAgent.h.i;
                    updateResponse.f = XiaomiUpdateAgent.h.h;
                    updateResponse.g = XiaomiUpdateAgent.h.l;
                    updateResponse.d = Connection.a(XiaomiUpdateAgent.h.f11119a, XiaomiUpdateAgent.h.g);
                    if (XiaomiUpdateAgent.i.get() != null) {
                        ((XiaomiUpdateListener) XiaomiUpdateAgent.i.get()).a(0, updateResponse);
                    }
                }
            }
        }
    }

    static boolean g() {
        Context context = (Context) d.get();
        if (context == null || h == null || g == null) {
            return false;
        }
        if (j.equals(Constants.UpdateMethod.MARKET) && h.b != 1 && Utils.a(context)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?ref=update_sdk&back=true&id=" + g.f11109a));
                intent.setPackage(Utils.a());
                List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                if (queryIntentActivities != null && queryIntentActivities.size() == 1) {
                    ResolveInfo resolveInfo = queryIntentActivities.get(0);
                    if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.exported && resolveInfo.activityInfo.enabled) {
                        if (!(context instanceof Activity)) {
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        }
                        context.startActivity(intent);
                        return false;
                    }
                }
            } catch (ActivityNotFoundException e2) {
                Log.b(c, "Exception thrown when start SuperMarket - " + e2.toString());
            }
        }
        DownloadInstallManager.a(context).a(g, h);
        return true;
    }
}
