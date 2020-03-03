package com.loc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.List;

public final class d {
    private static d e;

    /* renamed from: a  reason: collision with root package name */
    private List<String> f6551a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public final Context c;
    private final Handler d;

    private static final class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<d> f6553a;

        a(Looper looper, d dVar) {
            super(looper);
            this.f6553a = new WeakReference<>(dVar);
        }

        a(d dVar) {
            this.f6553a = new WeakReference<>(dVar);
        }

        public final void handleMessage(Message message) {
            d dVar = (d) this.f6553a.get();
            if (dVar != null && message != null && message.obj != null) {
                dVar.a((String) message.obj, message.what);
            }
        }
    }

    private d(Context context) {
        this.c = context.getApplicationContext();
        this.d = Looper.myLooper() == null ? new a(Looper.getMainLooper(), this) : new a(this);
    }

    public static d a(Context context) {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d(context);
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(final java.lang.String r3, final int r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.os.Looper r0 = android.os.Looper.myLooper()     // Catch:{ all -> 0x006f }
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x006f }
            if (r0 != r1) goto L_0x0015
            com.loc.d$1 r0 = new com.loc.d$1     // Catch:{ all -> 0x006f }
            r0.<init>(r3, r4)     // Catch:{ all -> 0x006f }
            r0.start()     // Catch:{ all -> 0x006f }
            monitor-exit(r2)
            return
        L_0x0015:
            java.lang.String r3 = com.loc.i.b((java.lang.String) r3)     // Catch:{ all -> 0x006f }
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x006f }
            if (r0 != 0) goto L_0x006d
            r0 = r4 & 1
            if (r0 <= 0) goto L_0x003e
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x003e }
            r1 = 23
            if (r0 < r1) goto L_0x0035
            android.content.Context r0 = r2.c     // Catch:{ Exception -> 0x003e }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x003e }
            java.lang.String r1 = r2.b     // Catch:{ Exception -> 0x003e }
        L_0x0031:
            android.provider.Settings.System.putString(r0, r1, r3)     // Catch:{ Exception -> 0x003e }
            goto L_0x003e
        L_0x0035:
            android.content.Context r0 = r2.c     // Catch:{ Exception -> 0x003e }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x003e }
            java.lang.String r1 = r2.b     // Catch:{ Exception -> 0x003e }
            goto L_0x0031
        L_0x003e:
            r0 = r4 & 16
            if (r0 <= 0) goto L_0x0049
            android.content.Context r0 = r2.c     // Catch:{ all -> 0x006f }
            java.lang.String r1 = r2.b     // Catch:{ all -> 0x006f }
            com.loc.e.a(r0, r1, r3)     // Catch:{ all -> 0x006f }
        L_0x0049:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 <= 0) goto L_0x006d
            android.content.Context r4 = r2.c     // Catch:{ all -> 0x006f }
            java.lang.String r0 = "SharedPreferenceAdiu"
            r1 = 0
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r0, r1)     // Catch:{ all -> 0x006f }
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r2.b     // Catch:{ all -> 0x006f }
            r4.putString(r0, r3)     // Catch:{ all -> 0x006f }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x006f }
            r0 = 9
            if (r3 < r0) goto L_0x006a
            r4.apply()     // Catch:{ all -> 0x006f }
            monitor-exit(r2)
            return
        L_0x006a:
            r4.commit()     // Catch:{ all -> 0x006f }
        L_0x006d:
            monitor-exit(r2)
            return
        L_0x006f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.d.a(java.lang.String, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> b() {
        /*
            r7 = this;
            java.lang.String r0 = ""
            android.content.Context r1 = r7.c     // Catch:{ Exception -> 0x0019 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ Exception -> 0x0019 }
            java.lang.String r2 = r7.b     // Catch:{ Exception -> 0x0019 }
            java.lang.String r1 = android.provider.Settings.System.getString(r1, r2)     // Catch:{ Exception -> 0x0019 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0019 }
            if (r2 != 0) goto L_0x0019
            java.lang.String r1 = com.loc.i.c(r1)     // Catch:{ Exception -> 0x0019 }
            r0 = r1
        L_0x0019:
            java.lang.String r1 = ""
            android.content.Context r2 = r7.c
            java.lang.String r3 = r7.b
            java.lang.String r2 = com.loc.e.a(r2, r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x002d
            java.lang.String r1 = com.loc.i.c(r2)
        L_0x002d:
            java.lang.String r2 = ""
            android.content.Context r3 = r7.c
            java.lang.String r4 = "SharedPreferenceAdiu"
            r5 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r4, r5)
            java.lang.String r4 = r7.b
            r6 = 0
            java.lang.String r3 = r3.getString(r4, r6)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0049
            java.lang.String r2 = com.loc.i.c(r3)
        L_0x0049:
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 3
            r3.<init>(r4)
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x008e
            r3.add(r0)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x0067
            boolean r4 = android.text.TextUtils.equals(r1, r0)
            if (r4 != 0) goto L_0x0069
            r3.add(r1)
        L_0x0067:
            r5 = 16
        L_0x0069:
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x007e
            boolean r4 = android.text.TextUtils.equals(r2, r0)
            if (r4 != 0) goto L_0x0080
            boolean r1 = android.text.TextUtils.equals(r2, r1)
            if (r1 != 0) goto L_0x007e
            r3.add(r2)
        L_0x007e:
            r5 = r5 | 256(0x100, float:3.59E-43)
        L_0x0080:
            if (r5 <= 0) goto L_0x008d
            android.os.Handler r1 = r7.d
            android.os.Handler r2 = r7.d
            android.os.Message r0 = r2.obtainMessage(r5, r0)
            r1.sendMessage(r0)
        L_0x008d:
            return r3
        L_0x008e:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x00b6
            r3.add(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x00a6
            boolean r0 = android.text.TextUtils.equals(r2, r1)
            if (r0 != 0) goto L_0x00a8
            r3.add(r2)
        L_0x00a6:
            r5 = 256(0x100, float:3.59E-43)
        L_0x00a8:
            r0 = r5 | 1
            android.os.Handler r2 = r7.d
            android.os.Handler r4 = r7.d
            android.os.Message r0 = r4.obtainMessage(r0, r1)
            r2.sendMessage(r0)
            return r3
        L_0x00b6:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x00cd
            r3.add(r2)
            android.os.Handler r0 = r7.d
            android.os.Handler r1 = r7.d
            r4 = 17
            android.os.Message r1 = r1.obtainMessage(r4, r2)
            r0.sendMessage(r1)
            return r3
        L_0x00cd:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.d.b():java.util.List");
    }

    public final List<String> a() {
        if (this.f6551a != null && this.f6551a.size() > 0 && !TextUtils.isEmpty(this.f6551a.get(0))) {
            return this.f6551a;
        }
        this.f6551a = b();
        return this.f6551a;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final void b(String str) {
        if (this.f6551a != null) {
            this.f6551a.clear();
            this.f6551a.add(str);
        }
        a(str, 273);
    }
}
