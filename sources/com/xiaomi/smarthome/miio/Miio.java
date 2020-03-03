package com.xiaomi.smarthome.miio;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Miio {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11572a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final boolean g = (GlobalSetting.q || GlobalSetting.s);
    public static final boolean h = true;
    public static final boolean i = false;
    private static int j = 0;
    private static final String k = "224.126.0.1";
    private static final int l = 5007;
    private static final String m = "mi";

    public static void a(String str) {
        a(0, str);
    }

    public static void b(String str) {
        a(1, str);
    }

    public static void c(String str) {
        a(2, str);
    }

    public static void d(String str) {
        a(3, str);
    }

    public static void e(String str) {
        a(4, str);
    }

    public static void f(String str) {
        a(5, str);
    }

    public static void a(String str, String str2) {
        a(0, str, str2);
    }

    public static void b(String str, String str2) {
        a(1, str, str2);
    }

    public static void c(String str, String str2) {
        a(2, str, str2);
    }

    public static void d(String str, String str2) {
        a(3, str, str2);
    }

    public static void e(String str, String str2) {
        a(4, str, str2);
    }

    public static void f(String str, String str2) {
        a(5, str, str2);
    }

    public static void g(final String str, final String str2) {
        if (g) {
            Log.d(str, str2);
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Removed duplicated region for block: B:12:0x0067 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x0075 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:30:0x007f A[SYNTHETIC] */
                /* renamed from: a */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Void doInBackground(java.lang.Void... r9) {
                    /*
                        r8 = this;
                        r9 = 0
                        android.content.Context r0 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        java.lang.String r1 = "log"
                        java.io.File r0 = r0.getExternalFilesDir(r1)     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        boolean r1 = r0.exists()     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        if (r1 != 0) goto L_0x001a
                        boolean r1 = r0.mkdirs()     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        if (r1 == 0) goto L_0x0018
                        goto L_0x001a
                    L_0x0018:
                        r0 = r9
                        goto L_0x0065
                    L_0x001a:
                        java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        java.lang.String r2 = "%s.txt"
                        r3 = 1
                        java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        r5 = 0
                        java.lang.String r6 = r1     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        r4[r5] = r6     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        r1.<init>(r0, r2)     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        r0.<init>(r1, r3)     // Catch:{ Exception -> 0x007c, all -> 0x006f }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        r1.<init>()     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r3 = "yyyy-MM-dd HH:mm:ss"
                        r2.<init>(r3)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.util.Date r3 = new java.util.Date     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        r3.<init>()     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r2 = r2.format(r3)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        r1.append(r2)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r2 = "-->"
                        r1.append(r2)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r2 = r2     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        r1.append(r2)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r2 = "\r\n\r\n"
                        r1.append(r2)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        byte[] r1 = r1.getBytes()     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                        r0.write(r1)     // Catch:{ Exception -> 0x007d, all -> 0x006d }
                    L_0x0065:
                        if (r0 == 0) goto L_0x0085
                        r0.close()     // Catch:{ Exception -> 0x0085, all -> 0x006b }
                        goto L_0x0085
                    L_0x006b:
                        r9 = move-exception
                        throw r9
                    L_0x006d:
                        r9 = move-exception
                        goto L_0x0073
                    L_0x006f:
                        r0 = move-exception
                        r7 = r0
                        r0 = r9
                        r9 = r7
                    L_0x0073:
                        if (r0 == 0) goto L_0x007b
                        r0.close()     // Catch:{ Exception -> 0x007b, all -> 0x0079 }
                        goto L_0x007b
                    L_0x0079:
                        r9 = move-exception
                        throw r9
                    L_0x007b:
                        throw r9
                    L_0x007c:
                        r0 = r9
                    L_0x007d:
                        if (r0 == 0) goto L_0x0085
                        r0.close()     // Catch:{ Exception -> 0x0085, all -> 0x0083 }
                        goto L_0x0085
                    L_0x0083:
                        r9 = move-exception
                        throw r9
                    L_0x0085:
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.Miio.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Void");
                }
            }, new Void[0]);
        }
    }

    public static void g(String str) {
        if (g && !TextUtils.isEmpty(str)) {
            a(0, str);
        }
    }

    public static void h(String str, String str2) {
        if (g) {
            a(0, str, str2);
        }
    }

    public static void a(long j2) {
        if (g) {
            a(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(j2)));
        }
    }

    public static void a(int i2, String str) {
        if (g && i2 >= j) {
            Log.e("miio-app", str);
        }
    }

    public static void a(int i2, String str, String str2) {
        if (g && i2 >= j) {
            Log.e(str, str2);
        }
    }

    public static void a(int i2) {
        if (i2 < 2 || i2 > 5) {
            a(3, "set log level as " + i2);
            return;
        }
        j = i2;
    }

    public static void a() {
        new Thread(new Runnable() {
            public void run() {
                int i = 200;
                while (true) {
                    int i2 = i - 1;
                    if (i > 0) {
                        try {
                            InetAddress byName = InetAddress.getByName(Miio.k);
                            MulticastSocket multicastSocket = new MulticastSocket(Miio.l);
                            multicastSocket.joinGroup(byName);
                            multicastSocket.send(new DatagramPacket(Miio.m.getBytes(), Miio.m.length(), byName, Miio.l));
                            Thread.sleep(10);
                            i = i2;
                        } catch (Exception e) {
                            MyLog.a("IGMP ip error", (Throwable) e);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }).start();
    }
}
