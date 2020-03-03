package com.xiaomi.jr.web.staticresource;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.Pools;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ResourceChecker {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11076a = "ResourceChecker";
    /* access modifiers changed from: private */
    public static Map<String, CheckRunnable> b = new HashMap();
    private static Executor c = Executors.newCachedThreadPool();

    public interface Listener {
        void onResult(boolean z);
    }

    static class CheckRunnable implements Runnable {
        private static final Pools.SynchronizedPool<CheckRunnable> b = new Pools.SynchronizedPool<>(5);

        /* renamed from: a  reason: collision with root package name */
        String f11077a;
        private Context c;
        /* access modifiers changed from: private */
        public List<WeakReference<Listener>> d = new CopyOnWriteArrayList();

        CheckRunnable() {
        }

        static CheckRunnable a() {
            CheckRunnable acquire = b.acquire();
            return acquire != null ? acquire : new CheckRunnable();
        }

        public void a(Context context) {
            this.c = context;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.f11077a = null;
            this.d.clear();
            b.release(this);
        }

        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            String b2 = StaticResourceUtils.b(this.f11077a);
            Context context = this.c;
            String d2 = PreferenceUtils.d(context, ResourceConstants.f11078a, "static_resource_last_modified_" + b2);
            if (TextUtils.isEmpty(d2)) {
                String host = Uri.parse(this.f11077a).getHost();
                Context context2 = this.c;
                d2 = PreferenceUtils.d(context2, ResourceConstants.f11078a, "static_resource_last_modified_" + host);
            }
            boolean a2 = ResourceChecker.b(this.c, this.f11077a, d2);
            MifiLog.b("TestModified", "check " + this.f11077a + ", last-modified: " + d2 + ". modified: " + a2 + ", takes: " + (System.currentTimeMillis() - currentTimeMillis));
            for (WeakReference next : this.d) {
                if (next.get() != null) {
                    ((Listener) next.get()).onResult(a2);
                }
            }
            ResourceChecker.b.remove(this.f11077a);
            b();
        }
    }

    public static void a(Context context, String str, Listener listener) {
        CheckRunnable checkRunnable;
        String a2 = StaticResourceUtils.a(str);
        if (!b.containsKey(a2)) {
            checkRunnable = CheckRunnable.a();
            checkRunnable.a(context);
            checkRunnable.f11077a = a2;
            b.put(a2, checkRunnable);
            c.execute(checkRunnable);
        } else {
            checkRunnable = b.get(a2);
        }
        checkRunnable.d.add(new WeakReference(listener));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
            com.xiaomi.jr.common.utils.Utils.b()
            r0 = 1
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x00c3 }
            r2.<init>(r9)     // Catch:{ IOException -> 0x00c3 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ IOException -> 0x00c3 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x00c3 }
            if (r10 == 0) goto L_0x001f
            java.lang.String r1 = "If-Modified-Since"
            r2.setRequestProperty(r1, r10)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            goto L_0x001f
        L_0x0018:
            r8 = move-exception
            goto L_0x00e4
        L_0x001b:
            r8 = move-exception
            r1 = r2
            goto L_0x00c4
        L_0x001f:
            int r10 = r2.getResponseCode()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r10 != r1) goto L_0x00b4
            java.lang.String r9 = com.xiaomi.jr.web.staticresource.StaticResourceUtils.b(r9)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.io.File r1 = r8.getFilesDir()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = r1.getCanonicalPath()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r3.<init>()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r3.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r3.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = "static_resource"
            r3.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r3.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r3.append(r9)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            if (r3 == 0) goto L_0x00b4
            byte[] r4 = com.xiaomi.jr.common.utils.FileUtils.a((java.io.InputStream) r3)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            if (r4 == 0) goto L_0x00b1
            java.lang.String r5 = "TestModified"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r6.<init>()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r7 = "update cache "
            r6.append(r7)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r6.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            com.xiaomi.jr.common.utils.MifiLog.a((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            boolean r4 = com.xiaomi.jr.common.utils.FileUtils.a((java.lang.String) r1, (byte[]) r4)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            if (r4 == 0) goto L_0x0096
            java.lang.String r1 = "Last-Modified"
            java.lang.String r1 = r2.getHeaderField(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r4 = "static_resource_record"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r5.<init>()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r6 = "static_resource_last_modified_"
            r5.append(r6)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r5.append(r9)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r9 = r5.toString()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            com.xiaomi.jr.common.utils.PreferenceUtils.a((android.content.Context) r8, (java.lang.String) r4, (java.lang.String) r9, (java.lang.String) r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            goto L_0x00b1
        L_0x0096:
            java.lang.String r8 = "ResourceChecker"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r9.<init>()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r4 = "update cache "
            r9.append(r4)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            r9.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r1 = " FAIL."
            r9.append(r1)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
            com.xiaomi.jr.common.utils.MifiLog.d(r8, r9)     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
        L_0x00b1:
            r3.close()     // Catch:{ IOException -> 0x001b, all -> 0x0018 }
        L_0x00b4:
            r8 = 304(0x130, float:4.26E-43)
            if (r10 == r8) goto L_0x00b9
            goto L_0x00ba
        L_0x00b9:
            r0 = 0
        L_0x00ba:
            if (r2 == 0) goto L_0x00bf
            r2.disconnect()
        L_0x00bf:
            return r0
        L_0x00c0:
            r8 = move-exception
            r2 = r1
            goto L_0x00e4
        L_0x00c3:
            r8 = move-exception
        L_0x00c4:
            java.lang.String r9 = "ResourceChecker"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            r10.<init>()     // Catch:{ all -> 0x00c0 }
            java.lang.String r2 = "checkResourceModified exception: "
            r10.append(r2)     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00c0 }
            r10.append(r8)     // Catch:{ all -> 0x00c0 }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x00c0 }
            com.xiaomi.jr.common.utils.MifiLog.e(r9, r8)     // Catch:{ all -> 0x00c0 }
            if (r1 == 0) goto L_0x00e3
            r1.disconnect()
        L_0x00e3:
            return r0
        L_0x00e4:
            if (r2 == 0) goto L_0x00e9
            r2.disconnect()
        L_0x00e9:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.ResourceChecker.b(android.content.Context, java.lang.String, java.lang.String):boolean");
    }
}
