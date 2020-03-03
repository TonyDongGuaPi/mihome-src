package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import com.huawei.hms.api.HuaweiApiAvailability;

class ap implements ar {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12632a;
    private Context b;
    private ServiceConnection c;
    /* access modifiers changed from: private */
    public volatile int d = 0;
    /* access modifiers changed from: private */
    public volatile String e = null;
    /* access modifiers changed from: private */
    public volatile boolean f = false;
    private volatile String g = null;
    /* access modifiers changed from: private */
    public final Object h = new Object();

    private class a implements ServiceConnection {
        private a() {
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0073 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0030 */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r3, android.os.IBinder r4) {
            /*
                r2 = this;
                r3 = 2
                com.xiaomi.push.ap r0 = com.xiaomi.push.ap.this     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                java.lang.String r1 = com.xiaomi.push.ap.b.a(r4)     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                java.lang.String unused = r0.e = r1     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                com.xiaomi.push.ap r0 = com.xiaomi.push.ap.this     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                boolean r4 = com.xiaomi.push.ap.b.b(r4)     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                boolean unused = r0.f = r4     // Catch:{ Exception -> 0x0056, all -> 0x0034 }
                com.xiaomi.push.ap r4 = com.xiaomi.push.ap.this
                r4.g()
                com.xiaomi.push.ap r4 = com.xiaomi.push.ap.this
                int unused = r4.d = r3
                com.xiaomi.push.ap r3 = com.xiaomi.push.ap.this
                java.lang.Object r4 = r3.h
                monitor-enter(r4)
                com.xiaomi.push.ap r3 = com.xiaomi.push.ap.this     // Catch:{ Exception -> 0x0030 }
                java.lang.Object r3 = r3.h     // Catch:{ Exception -> 0x0030 }
                r3.notifyAll()     // Catch:{ Exception -> 0x0030 }
                goto L_0x0030
            L_0x002e:
                r3 = move-exception
                goto L_0x0032
            L_0x0030:
                monitor-exit(r4)     // Catch:{ all -> 0x002e }
                goto L_0x0074
            L_0x0032:
                monitor-exit(r4)     // Catch:{ all -> 0x002e }
                throw r3
            L_0x0034:
                r4 = move-exception
                com.xiaomi.push.ap r0 = com.xiaomi.push.ap.this
                r0.g()
                com.xiaomi.push.ap r0 = com.xiaomi.push.ap.this
                int unused = r0.d = r3
                com.xiaomi.push.ap r3 = com.xiaomi.push.ap.this
                java.lang.Object r0 = r3.h
                monitor-enter(r0)
                com.xiaomi.push.ap r3 = com.xiaomi.push.ap.this     // Catch:{ Exception -> 0x0052 }
                java.lang.Object r3 = r3.h     // Catch:{ Exception -> 0x0052 }
                r3.notifyAll()     // Catch:{ Exception -> 0x0052 }
                goto L_0x0052
            L_0x0050:
                r3 = move-exception
                goto L_0x0054
            L_0x0052:
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                throw r4
            L_0x0054:
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                throw r3
            L_0x0056:
                com.xiaomi.push.ap r4 = com.xiaomi.push.ap.this
                r4.g()
                com.xiaomi.push.ap r4 = com.xiaomi.push.ap.this
                int unused = r4.d = r3
                com.xiaomi.push.ap r3 = com.xiaomi.push.ap.this
                java.lang.Object r3 = r3.h
                monitor-enter(r3)
                com.xiaomi.push.ap r4 = com.xiaomi.push.ap.this     // Catch:{ Exception -> 0x0073 }
                java.lang.Object r4 = r4.h     // Catch:{ Exception -> 0x0073 }
                r4.notifyAll()     // Catch:{ Exception -> 0x0073 }
                goto L_0x0073
            L_0x0071:
                r4 = move-exception
                goto L_0x0075
            L_0x0073:
                monitor-exit(r3)     // Catch:{ all -> 0x0071 }
            L_0x0074:
                return
            L_0x0075:
                monitor-exit(r3)     // Catch:{ all -> 0x0071 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ap.a.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private static class b {
        static String a(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        static boolean b(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                boolean z = false;
                iBinder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public ap(Context context) {
        this.b = context;
        f();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r3.d
            r1 = 1
            if (r0 != r1) goto L_0x0039
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 == r1) goto L_0x0039
            java.lang.Object r0 = r3.h
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0035 }
            r1.<init>()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r2 = "huawei's "
            r1.append(r2)     // Catch:{ Exception -> 0x0035 }
            r1.append(r4)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r4 = " wait..."
            r1.append(r4)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r4 = r1.toString()     // Catch:{ Exception -> 0x0035 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r4)     // Catch:{ Exception -> 0x0035 }
            java.lang.Object r4 = r3.h     // Catch:{ Exception -> 0x0035 }
            r1 = 3000(0xbb8, double:1.482E-320)
            r4.wait(r1)     // Catch:{ Exception -> 0x0035 }
            goto L_0x0035
        L_0x0033:
            r4 = move-exception
            goto L_0x0037
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            goto L_0x0039
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ap.a(java.lang.String):void");
    }

    public static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(HuaweiApiAvailability.SERVICES_PACKAGE, 128);
            boolean z = (packageInfo.applicationInfo.flags & 1) != 0;
            f12632a = packageInfo.versionCode >= 20602000;
            return z;
        } catch (Exception unused) {
        }
    }

    private static String b(Context context) {
        String str;
        String str2;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                str2 = context.createDeviceProtectedStorageContext().getSharedPreferences("aaid", 0).getString("aaid", (String) null);
                if (str2 != null) {
                    return str2;
                }
            } else {
                str2 = null;
            }
            try {
                str = context.getSharedPreferences("aaid", 0).getString("aaid", (String) null);
            } catch (Exception unused) {
                str = str2;
            }
        } catch (Exception unused2) {
            str = null;
        }
        return str == null ? "" : str;
    }

    private void f() {
        boolean z;
        this.c = new a();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage(HuaweiApiAvailability.SERVICES_PACKAGE);
        int i = 1;
        try {
            z = this.b.bindService(intent, this.c, 1);
        } catch (Exception unused) {
            z = false;
        }
        if (!z) {
            i = 2;
        }
        this.d = i;
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.c != null) {
            try {
                this.b.unbindService(this.c);
            } catch (Exception unused) {
            }
        }
    }

    public boolean a() {
        return f12632a;
    }

    public String b() {
        return null;
    }

    public String c() {
        a("getOAID");
        return this.e;
    }

    public String d() {
        return null;
    }

    public String e() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = b(this.b);
                }
            }
        }
        return this.g;
    }
}
