package com.amap.openapi;

import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.common.util.e;
import com.amap.location.uptunnel.core.db.DBProvider;
import com.amap.openapi.bi;
import com.amap.openapi.bj;
import com.loc.fc;
import java.util.concurrent.Executor;

public class ec {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Uri f4715a;
    /* access modifiers changed from: private */
    public DBProvider b;
    /* access modifiers changed from: private */
    public dt c;
    /* access modifiers changed from: private */
    public dp d;
    /* access modifiers changed from: private */
    public IHttpClient e;
    /* access modifiers changed from: private */
    public int f;
    private bj g;
    private bi h;
    /* access modifiers changed from: private */
    public SparseIntArray i = new SparseIntArray();
    /* access modifiers changed from: private */
    public volatile long j;

    class a implements bi.b<c> {
        a() {
        }

        private boolean b(long j) {
            int e = e();
            if (e > 0) {
                j -= (long) (e * 24);
            }
            long j2 = j / 24;
            int i = ((j % 24) > 0 ? 1 : ((j % 24) == 0 ? 0 : -1));
            boolean z = true;
            long j3 = j2 + ((long) (i > 0 ? 1 : 0));
            if (j3 <= 0) {
                return true;
            }
            try {
                Cursor a2 = ec.this.b.a(ec.this.f4715a, new String[]{"ID"}, (String) null, (String[]) null, (String) null, (j3 - 1) + ", 1");
                if (a2 != null) {
                    try {
                        if (a2.moveToFirst()) {
                            if (ec.this.b.a(ec.this.f4715a, "ID <= ?", new String[]{String.valueOf(a2.getLong(0))}) <= 0) {
                                z = false;
                            }
                            return z;
                        }
                    } catch (Exception unused) {
                        return false;
                    } finally {
                        e.a(a2);
                    }
                }
                e.a(a2);
            } catch (Exception unused2) {
            }
            return false;
        }

        private int e() {
            return ec.this.b.a(ec.this.f4715a, "time < ?", new String[]{String.valueOf(System.currentTimeMillis() - ec.this.d.h())});
        }

        public void a() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x00ce, code lost:
            if (r8.a(r10, r9, "ID = " + r6, (java.lang.String[]) null) >= 0) goto L_0x00dc;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.util.ArrayList<com.amap.openapi.ec.c> r14) {
            /*
                r13 = this;
                com.amap.openapi.ec r14 = com.amap.openapi.ec.this
                android.util.SparseIntArray r14 = r14.i
                int r14 = r14.size()
                r0 = 0
                r1 = 0
            L_0x000c:
                if (r1 >= r14) goto L_0x0122
                com.amap.openapi.ec r2 = com.amap.openapi.ec.this
                android.util.SparseIntArray r2 = r2.i
                int r2 = r2.keyAt(r1)
                com.amap.openapi.ec r3 = com.amap.openapi.ec.this
                android.util.SparseIntArray r3 = r3.i
                int r3 = r3.valueAt(r1)
                com.amap.openapi.ec r4 = com.amap.openapi.ec.this
                com.amap.location.uptunnel.core.db.DBProvider r5 = r4.b
                com.amap.openapi.ec r4 = com.amap.openapi.ec.this
                android.net.Uri r6 = r4.f4715a
                java.lang.String[] r7 = com.amap.openapi.dw.f4712a
                java.lang.String r8 = "type = ? "
                r4 = 1
                java.lang.String[] r9 = new java.lang.String[r4]
                java.lang.String r10 = java.lang.String.valueOf(r2)
                r9[r0] = r10
                java.lang.String r10 = "time DESC"
                java.lang.String r11 = "0, 1"
                android.database.Cursor r5 = r5.b(r6, r7, r8, r9, r10, r11)
                if (r5 == 0) goto L_0x00db
                boolean r6 = r5.moveToFirst()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                if (r6 == 0) goto L_0x00db
                java.lang.String r6 = "time"
                int r6 = r5.getColumnIndex(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                long r6 = r5.getLong(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r10 = 0
                long r8 = r8 - r6
                r6 = 0
                int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r10 <= 0) goto L_0x00db
                com.amap.openapi.ec r6 = com.amap.openapi.ec.this     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                com.amap.openapi.dp r6 = r6.d     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                long r6 = r6.a()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r10 >= 0) goto L_0x00db
                java.lang.String r6 = "ID"
                int r6 = r5.getColumnIndex(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                long r6 = r5.getLong(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                com.amap.openapi.ec r8 = com.amap.openapi.ec.this     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                long r8 = r8.j     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r10 > 0) goto L_0x0088
                r8 = 1
                goto L_0x0089
            L_0x0088:
                r8 = 0
            L_0x0089:
                if (r8 != 0) goto L_0x00db
                java.lang.String r8 = "value"
                int r8 = r5.getColumnIndex(r8)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                int r8 = r5.getInt(r8)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                android.content.ContentValues r9 = new android.content.ContentValues     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r9.<init>()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                java.lang.String r10 = "ID"
                java.lang.Long r11 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r9.put(r10, r11)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                java.lang.String r10 = "value"
                int r8 = r8 + r3
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r9.put(r10, r8)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                com.amap.openapi.ec r8 = com.amap.openapi.ec.this     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                com.amap.location.uptunnel.core.db.DBProvider r8 = r8.b     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                com.amap.openapi.ec r10 = com.amap.openapi.ec.this     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                android.net.Uri r10 = r10.f4715a     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                java.lang.String r12 = "ID = "
                r11.<init>(r12)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r11.append(r6)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                java.lang.String r6 = r11.toString()     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                r7 = 0
                int r6 = r8.a(r10, r9, r6, r7)     // Catch:{ Exception -> 0x00d6, all -> 0x00d1 }
                if (r6 < 0) goto L_0x00db
                goto L_0x00dc
            L_0x00d1:
                r14 = move-exception
                com.amap.location.common.util.e.a((android.database.Cursor) r5)
                throw r14
            L_0x00d6:
                com.amap.location.common.util.e.a((android.database.Cursor) r5)
                r4 = 0
                goto L_0x00df
            L_0x00db:
                r4 = 0
            L_0x00dc:
                com.amap.location.common.util.e.a((android.database.Cursor) r5)
            L_0x00df:
                if (r4 != 0) goto L_0x011e
                long r4 = java.lang.System.currentTimeMillis()
                android.content.ContentValues r6 = new android.content.ContentValues
                r6.<init>()
                java.lang.String r7 = "type"
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                r6.put(r7, r2)
                java.lang.String r2 = "time"
                java.lang.Long r4 = java.lang.Long.valueOf(r4)
                r6.put(r2, r4)
                java.lang.String r2 = "value"
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r6.put(r2, r3)
                com.amap.openapi.ec r2 = com.amap.openapi.ec.this
                com.amap.location.uptunnel.core.db.DBProvider r2 = r2.b
                com.amap.openapi.ec r3 = com.amap.openapi.ec.this
                android.net.Uri r3 = r3.f4715a
                long r2 = r2.a((android.net.Uri) r3, (android.content.ContentValues) r6)
                r4 = -1
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 == 0) goto L_0x0122
            L_0x011e:
                int r1 = r1 + 1
                goto L_0x000c
            L_0x0122:
                com.amap.openapi.ec r14 = com.amap.openapi.ec.this
                android.util.SparseIntArray r14 = r14.i
                r14.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.ec.a.a(java.util.ArrayList):void");
        }

        public boolean a(long j) {
            try {
                long a2 = (ec.this.b.a(ec.this.f4715a) * 24) + (j * 24);
                if (a2 > ec.this.d.g()) {
                    return b(a2 - ec.this.d.g());
                }
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public void b() {
        }

        public long c() {
            return ec.this.d.c();
        }

        public long d() {
            return ec.this.d.e();
        }
    }

    class b implements bj.a {
        b() {
        }

        public Object a(long j) {
            d dVar = new d();
            long j2 = j / 24;
            if (j2 <= 0) {
                return null;
            }
            DBProvider c = ec.this.b;
            Uri b = ec.this.f4715a;
            String[] strArr = dw.f4712a;
            Cursor b2 = c.b(b, strArr, (String) null, (String[]) null, (String) null, "0, " + j2);
            if (b2 != null) {
                try {
                    if (b2.getCount() > 0) {
                        fc fcVar = new fc();
                        int a2 = bk.a(fcVar, ec.this.c.a());
                        int count = b2.getCount();
                        int[] iArr = new int[count];
                        long j3 = -1;
                        int i = 0;
                        while (b2.moveToNext()) {
                            j3 = b2.getLong(b2.getColumnIndex("ID"));
                            iArr[i] = dx.a(fcVar, b2.getInt(b2.getColumnIndex("type")), b2.getInt(b2.getColumnIndex("value")), b2.getLong(b2.getColumnIndex("time")));
                            i++;
                        }
                        int a3 = dz.a(fcVar, iArr);
                        dz.a(fcVar);
                        dz.a(fcVar, (byte) 0);
                        dz.a(fcVar, a2);
                        dz.b(fcVar, a3);
                        dz.d(fcVar, dz.b(fcVar));
                        dVar.f4719a = fcVar.f();
                        dVar.b = j3;
                        dVar.c = (long) (count * 24);
                        long unused = ec.this.j = j3;
                        e.a(b2);
                        return dVar;
                    }
                } catch (Exception unused2) {
                } catch (Throwable th) {
                    e.a(b2);
                    throw th;
                }
            }
            e.a(b2);
            return null;
        }

        public void a() {
        }

        public void a(int i) {
            long unused = ec.this.j = -1;
        }

        public void a(int i, Object obj) {
            if (obj instanceof d) {
                ec.this.c.a(ec.this.f, i, ((d) obj).c);
            }
        }

        public boolean a(Object obj) {
            if (obj instanceof d) {
                return ea.a(ec.this.e, ec.this.c.a(ec.this.f), ((d) obj).f4719a, ec.this.d.f());
            }
            return false;
        }

        public void b() {
        }

        public void b(Object obj) {
            if (obj instanceof d) {
                ec.this.b.a(ec.this.f4715a, "ID <= ? ", new String[]{String.valueOf(((d) obj).b)});
            }
        }

        public boolean b(int i) {
            return ec.this.d.c(i);
        }

        public long c() {
            try {
                return ec.this.b.a(ec.this.f4715a) * 24;
            } catch (Exception unused) {
                return 0;
            }
        }

        public long c(int i) {
            return ec.this.d.b(i) - ec.this.c.a(ec.this.f, i);
        }

        public int d() {
            return 3;
        }

        public long d(int i) {
            return ec.this.d.a(i);
        }

        public long e() {
            return ec.this.d.d();
        }

        public int f() {
            return ec.this.d.f();
        }

        public void g() {
            long unused = ec.this.j = -1;
        }

        public Executor h() {
            return null;
        }
    }

    static class c implements bi.a {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final c f4718a = new c();

        c() {
        }

        public long a() {
            return 1;
        }
    }

    static class d {

        /* renamed from: a  reason: collision with root package name */
        byte[] f4719a;
        long b;
        long c;

        d() {
        }
    }

    public void a() {
        this.h.a();
        this.g.a();
    }

    public void a(int i2) {
        this.i.put(i2, this.i.get(i2) + 1);
        this.d.b();
        this.h.a(c.f4718a);
    }

    public void a(@NonNull dt dtVar, @NonNull dp dpVar, @NonNull IHttpClient iHttpClient, @NonNull Looper looper) {
        this.c = dtVar;
        this.f = 1;
        this.d = new eb(dpVar);
        this.e = iHttpClient;
        this.b = dtVar.b();
        this.f4715a = dt.b(this.f);
        this.h = new bi();
        this.h.a(new a(), looper);
        this.g = new bj();
        this.g.a(dtVar.a(), (bj.a) new b(), looper);
        this.g.a(20000);
    }

    public void b(int i2) {
        if (i2 != -1) {
            this.d.b();
            this.g.a(20000);
        }
    }
}
