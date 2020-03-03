package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.al;
import com.xiaomi.stat.c.c;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.cybergarage.http.HTTP;

public class gz {

    /* renamed from: a  reason: collision with root package name */
    private static al f12763a = new al(true);
    private static volatile int b = -1;
    private static long c = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public static final Object d = new Object();
    /* access modifiers changed from: private */
    public static List<a> e = Collections.synchronizedList(new ArrayList());
    private static String f = "";
    private static com.xiaomi.push.providers.a g = null;

    static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f12764a = "";
        public long b = 0;
        public int c = -1;
        public int d = -1;
        public String e = "";
        public long f = 0;

        public a(String str, long j, int i, int i2, String str2, long j2) {
            this.f12764a = str;
            this.b = j;
            this.c = i;
            this.d = i2;
            this.e = str2;
            this.f = j2;
        }

        public boolean a(a aVar) {
            return TextUtils.equals(aVar.f12764a, this.f12764a) && TextUtils.equals(aVar.e, this.e) && aVar.c == this.c && aVar.d == this.d && Math.abs(aVar.b - this.b) <= 5000;
        }
    }

    private static long a(int i, long j, boolean z, long j2, boolean z2) {
        if (z && z2) {
            long j3 = c;
            c = j2;
            if (j2 - j3 > 30000 && j > 1024) {
                return j * 2;
            }
        }
        return (j * ((long) (i == 0 ? 13 : 11))) / 10;
    }

    public static void a(Context context) {
        b = c(context);
    }

    private static void a(Context context, String str, long j, boolean z, long j2) {
        int b2;
        boolean isEmpty;
        if (context != null && !TextUtils.isEmpty(str) && c.f23036a.equals(context.getPackageName())) {
            String str2 = str;
            if (!c.f23036a.equals(str) && -1 != (b2 = b(context))) {
                synchronized (d) {
                    isEmpty = e.isEmpty();
                    a(new a(str, j2, b2, z ? 1 : 0, b2 == 0 ? d(context) : "", j));
                }
                if (isEmpty) {
                    f12763a.a((al.b) new ha(context), 5000);
                }
            }
        }
    }

    public static void a(Context context, String str, long j, boolean z, boolean z2, long j2) {
        a(context, str, a(b(context), j, z, j2, z2), z, j2);
    }

    private static void a(a aVar) {
        for (a next : e) {
            if (next.a(aVar)) {
                next.f += aVar.f;
                return;
            }
        }
        e.add(aVar);
    }

    public static synchronized void a(String str) {
        synchronized (gz.class) {
            if (!l.g() && !TextUtils.isEmpty(str)) {
                f = str;
            }
        }
    }

    public static int b(Context context) {
        if (b == -1) {
            b = c(context);
        }
        return b;
    }

    public static int b(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes().length;
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, List<a> list) {
        try {
            synchronized (com.xiaomi.push.providers.a.f12841a) {
                SQLiteDatabase writableDatabase = e(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (a next : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("package_name", next.f12764a);
                        contentValues.put("message_ts", Long.valueOf(next.b));
                        contentValues.put("network_type", Integer.valueOf(next.c));
                        contentValues.put(HTTP.CONTENT_RANGE_BYTES, Long.valueOf(next.f));
                        contentValues.put("rcv", Integer.valueOf(next.d));
                        contentValues.put("imsi", next.e);
                        writableDatabase.insert("traffic", (String) null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e2) {
            b.a((Throwable) e2);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int c(android.content.Context r2) {
        /*
            r0 = -1
            java.lang.String r1 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r1)     // Catch:{ Exception -> 0x0018 }
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch:{ Exception -> 0x0018 }
            if (r2 != 0) goto L_0x000c
            return r0
        L_0x000c:
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()     // Catch:{  }
            if (r2 != 0) goto L_0x0013
            return r0
        L_0x0013:
            int r2 = r2.getType()
            return r2
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gz.c(android.content.Context):int");
    }

    private static synchronized String d(Context context) {
        synchronized (gz.class) {
            if (TextUtils.isEmpty(f)) {
                return "";
            }
            String str = f;
            return str;
        }
    }

    private static com.xiaomi.push.providers.a e(Context context) {
        if (g != null) {
            return g;
        }
        g = new com.xiaomi.push.providers.a(context);
        return g;
    }
}
