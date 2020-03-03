package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.al;
import com.xiaomi.push.service.bb;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class dk {
    private static volatile dk c;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentLinkedQueue<b> f12691a = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public Context b;

    class a extends b {
        a() {
            super();
        }

        public void b() {
            dk.this.b();
        }
    }

    class b extends al.b {
        long b = System.currentTimeMillis();

        b() {
        }

        public void b() {
        }

        public boolean d() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean e() {
            return System.currentTimeMillis() - this.b > 172800000;
        }
    }

    class c extends b {

        /* renamed from: a  reason: collision with root package name */
        String f12693a;
        String d;
        File e;
        int f;
        boolean g;
        boolean h;

        c(String str, String str2, File file, boolean z) {
            super();
            this.f12693a = str;
            this.d = str2;
            this.e = file;
            this.h = z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean f() {
            /*
                r10 = this;
                com.xiaomi.push.dk r0 = com.xiaomi.push.dk.this
                android.content.Context r0 = r0.b
                java.lang.String r1 = "log.timestamp"
                r2 = 0
                android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
                java.lang.String r1 = "log.requst"
                java.lang.String r3 = ""
                java.lang.String r1 = r0.getString(r1, r3)
                long r3 = java.lang.System.currentTimeMillis()
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002b }
                r5.<init>(r1)     // Catch:{ JSONException -> 0x002b }
                java.lang.String r1 = "time"
                long r6 = r5.getLong(r1)     // Catch:{ JSONException -> 0x002b }
                java.lang.String r1 = "times"
                int r1 = r5.getInt(r1)     // Catch:{ JSONException -> 0x002c }
                goto L_0x002d
            L_0x002b:
                r6 = r3
            L_0x002c:
                r1 = 0
            L_0x002d:
                long r3 = java.lang.System.currentTimeMillis()
                long r3 = r3 - r6
                r8 = 86400000(0x5265c00, double:4.2687272E-316)
                int r5 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r5 >= 0) goto L_0x003e
                r3 = 10
                if (r1 <= r3) goto L_0x0043
                return r2
            L_0x003e:
                long r6 = java.lang.System.currentTimeMillis()
                r1 = 0
            L_0x0043:
                org.json.JSONObject r2 = new org.json.JSONObject
                r2.<init>()
                r3 = 1
                java.lang.String r4 = "time"
                r2.put(r4, r6)     // Catch:{ JSONException -> 0x0066 }
                java.lang.String r4 = "times"
                int r1 = r1 + r3
                r2.put(r4, r1)     // Catch:{ JSONException -> 0x0066 }
                android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ JSONException -> 0x0066 }
                java.lang.String r1 = "log.requst"
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0066 }
                android.content.SharedPreferences$Editor r0 = r0.putString(r1, r2)     // Catch:{ JSONException -> 0x0066 }
                r0.commit()     // Catch:{ JSONException -> 0x0066 }
                goto L_0x007f
            L_0x0066:
                r0 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "JSONException on put "
                r1.append(r2)
                java.lang.String r0 = r0.getMessage()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                com.xiaomi.channel.commonutils.logger.b.c(r0)
            L_0x007f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.dk.c.f():boolean");
        }

        public void b() {
            try {
                if (f()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", bb.a());
                    hashMap.put("token", this.d);
                    hashMap.put("net", az.k(dk.this.b));
                    az.a(this.f12693a, hashMap, this.e, "file");
                }
                this.g = true;
            } catch (IOException unused) {
            }
        }

        public void c() {
            if (!this.g) {
                this.f++;
                if (this.f < 3) {
                    dk.this.f12691a.add(this);
                }
            }
            if (this.g || this.f >= 3) {
                this.e.delete();
            }
            dk.this.a((long) ((1 << this.f) * 1000));
        }

        public boolean d() {
            return az.e(dk.this.b) || (this.h && az.c(dk.this.b));
        }
    }

    private dk(Context context) {
        this.b = context;
        this.f12691a.add(new a());
        b(0);
    }

    public static dk a(Context context) {
        if (c == null) {
            synchronized (dk.class) {
                if (c == null) {
                    c = new dk(context);
                }
            }
        }
        c.b = context;
        return c;
    }

    /* access modifiers changed from: private */
    public void a(long j) {
        b peek = this.f12691a.peek();
        if (peek != null && peek.d()) {
            b(j);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!aa.b() && !aa.a()) {
            try {
                File file = new File(this.b.getExternalFilesDir((String) null) + "/.logcache");
                if (file.exists() && file.isDirectory()) {
                    for (File delete : file.listFiles()) {
                        delete.delete();
                    }
                }
            } catch (NullPointerException unused) {
            }
        }
    }

    private void b(long j) {
        if (!this.f12691a.isEmpty()) {
            gx.a(new dm(this), j);
        }
    }

    private void c() {
        while (!this.f12691a.isEmpty()) {
            b peek = this.f12691a.peek();
            if (peek != null) {
                if (peek.e() || this.f12691a.size() > 6) {
                    com.xiaomi.channel.commonutils.logger.b.c("remove Expired task");
                    this.f12691a.remove(peek);
                } else {
                    return;
                }
            }
        }
    }

    public void a() {
        c();
        a(0);
    }

    public void a(String str, String str2, Date date, Date date2, int i, boolean z) {
        this.f12691a.add(new dl(this, i, date, date2, str, str2, z));
        b(0);
    }
}
