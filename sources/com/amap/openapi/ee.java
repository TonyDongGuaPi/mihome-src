package com.amap.openapi;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.common.util.e;
import com.amap.location.uptunnel.core.db.DBProvider;
import com.amap.openapi.bi;
import com.amap.openapi.bj;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class ee {

    /* renamed from: a  reason: collision with root package name */
    private String f4721a = "DataTunnel";
    /* access modifiers changed from: private */
    public String b = null;
    /* access modifiers changed from: private */
    public Uri c;
    /* access modifiers changed from: private */
    public DBProvider d;
    /* access modifiers changed from: private */
    public dt e;
    /* access modifiers changed from: private */
    public dq f;
    /* access modifiers changed from: private */
    public IHttpClient g;
    /* access modifiers changed from: private */
    public int h;
    private bi<c> i;
    private bj j;

    class a implements bi.b<c> {
        a() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00dc, code lost:
            com.amap.location.common.util.e.a(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00df, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e0, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e1, code lost:
            com.amap.location.common.util.e.a(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e4, code lost:
            throw r10;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00e0 A[ExcHandler:  FINALLY, Splitter:B:25:0x0087] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean a(long r10, long r12) {
            /*
                r9 = this;
                int r0 = r9.e()
                r1 = 1
                r2 = 0
                if (r0 <= 0) goto L_0x0042
                com.amap.openapi.ee r0 = com.amap.openapi.ee.this
                com.amap.location.uptunnel.core.db.DBProvider r3 = r0.d
                com.amap.openapi.ee r0 = com.amap.openapi.ee.this
                android.net.Uri r4 = r0.c
                java.lang.String[] r5 = new java.lang.String[r1]
                java.lang.String r0 = "sum(size)"
                r5[r2] = r0
                r6 = 0
                r7 = 0
                r8 = 0
                android.database.Cursor r0 = r3.a(r4, r5, r6, r7, r8)
                if (r0 == 0) goto L_0x003e
                boolean r3 = r0.moveToFirst()     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                if (r3 == 0) goto L_0x003e
                long r3 = r0.getLong(r2)     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
                r5 = 0
                long r10 = r10 - r3
                long r12 = r12 - r10
                com.amap.location.common.util.e.a((android.database.Cursor) r0)
                goto L_0x0042
            L_0x0035:
                r10 = move-exception
                com.amap.location.common.util.e.a((android.database.Cursor) r0)
                throw r10
            L_0x003a:
                com.amap.location.common.util.e.a((android.database.Cursor) r0)
                return r2
            L_0x003e:
                com.amap.location.common.util.e.a((android.database.Cursor) r0)
                return r2
            L_0x0042:
                com.amap.openapi.ee r10 = com.amap.openapi.ee.this
                com.amap.location.uptunnel.core.db.DBProvider r10 = r10.d
                android.database.sqlite.SQLiteDatabase r10 = r10.c()
                if (r10 != 0) goto L_0x004f
                return r2
            L_0x004f:
                r3 = 0
                int r11 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
                if (r11 <= 0) goto L_0x00ed
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ec }
                java.lang.String r0 = "select sum(size) from (select * from "
                r11.<init>(r0)     // Catch:{ Exception -> 0x00ec }
                com.amap.openapi.ee r0 = com.amap.openapi.ee.this     // Catch:{ Exception -> 0x00ec }
                java.lang.String r0 = r0.b     // Catch:{ Exception -> 0x00ec }
                r11.append(r0)     // Catch:{ Exception -> 0x00ec }
                java.lang.String r0 = " limit 0, "
                r11.append(r0)     // Catch:{ Exception -> 0x00ec }
                com.amap.openapi.ee r0 = com.amap.openapi.ee.this     // Catch:{ Exception -> 0x00ec }
                com.amap.openapi.dq r0 = r0.f     // Catch:{ Exception -> 0x00ec }
                int r0 = r0.a()     // Catch:{ Exception -> 0x00ec }
                r11.append(r0)     // Catch:{ Exception -> 0x00ec }
                java.lang.String r0 = ")"
                r11.append(r0)     // Catch:{ Exception -> 0x00ec }
                java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x00ec }
                r0 = 0
                android.database.Cursor r11 = r10.rawQuery(r11, r0)     // Catch:{ Exception -> 0x00ec }
                if (r11 == 0) goto L_0x00e9
                boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x00e5, all -> 0x00e0 }
                if (r0 == 0) goto L_0x00e9
                long r5 = r11.getLong(r2)     // Catch:{ Exception -> 0x00e5, all -> 0x00e0 }
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 > 0) goto L_0x0099
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
                return r2
            L_0x0099:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = "delete from "
                r0.<init>(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                com.amap.openapi.ee r3 = com.amap.openapi.ee.this     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = r3.b     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = " where ID < ( select ID from "
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                com.amap.openapi.ee r3 = com.amap.openapi.ee.this     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = r3.b     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = " limit "
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                com.amap.openapi.ee r3 = com.amap.openapi.ee.this     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                com.amap.openapi.dq r3 = r3.f     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                int r3 = r3.a()     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r3 = ", 1)"
                r0.append(r3)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                r10.execSQL(r0)     // Catch:{ Exception -> 0x00dc, all -> 0x00e0 }
                r0 = 0
                long r12 = r12 - r5
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
                goto L_0x004f
            L_0x00dc:
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
                return r2
            L_0x00e0:
                r10 = move-exception
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
                throw r10
            L_0x00e5:
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
                return r2
            L_0x00e9:
                com.amap.location.common.util.e.a((android.database.Cursor) r11)
            L_0x00ec:
                return r2
            L_0x00ed:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.ee.a.a(long, long):boolean");
        }

        private int e() {
            return ee.this.d.a(ee.this.c, "time < ?", new String[]{String.valueOf(System.currentTimeMillis() - ee.this.f.h())});
        }

        public void a() {
        }

        public void a(ArrayList<c> arrayList) {
            ContentValues[] contentValuesArr = new ContentValues[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                c cVar = arrayList.get(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("type", Integer.valueOf(cVar.f4724a));
                contentValues.put("time", Long.valueOf(cVar.b));
                contentValues.put("size", Long.valueOf(cVar.a()));
                contentValues.put("value", cVar.c);
                contentValuesArr[i] = contentValues;
            }
            ee.this.d.a(ee.this.c, contentValuesArr);
        }

        public boolean a(long j) {
            Cursor a2 = ee.this.d.a(ee.this.c, new String[]{"sum(size)"}, (String) null, (String[]) null, (String) null);
            if (a2 != null) {
                try {
                    if (a2.moveToFirst()) {
                        long j2 = a2.getLong(0);
                        long j3 = j + j2;
                        if (j3 > ee.this.f.g()) {
                            return a(j2, j3 - ee.this.f.g());
                        }
                        e.a(a2);
                        return true;
                    }
                } catch (Exception unused) {
                    return false;
                } finally {
                    e.a(a2);
                }
            }
            e.a(a2);
            return false;
        }

        public void b() {
        }

        public long c() {
            return ee.this.f.c();
        }

        public long d() {
            return ee.this.f.e();
        }
    }

    class b implements bj.a {
        b() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00cf, code lost:
            r6 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            com.amap.location.common.util.e.a(r6);
         */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00dd A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00de  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object a(long r29) {
            /*
                r28 = this;
                r1 = r28
                com.amap.openapi.ee$d r0 = new com.amap.openapi.ee$d
                r0.<init>()
                com.loc.fc r4 = new com.loc.fc
                r4.<init>()
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                r8 = -1
                r12 = r8
                r8 = 0
                r10 = 0
            L_0x0018:
                int r14 = (r8 > r29 ? 1 : (r8 == r29 ? 0 : -1))
                r15 = 0
                r16 = 0
                if (r14 >= 0) goto L_0x00d7
                com.amap.openapi.ee r7 = com.amap.openapi.ee.this
                com.amap.location.uptunnel.core.db.DBProvider r17 = r7.d
                com.amap.openapi.ee r7 = com.amap.openapi.ee.this
                android.net.Uri r18 = r7.c
                java.lang.String[] r19 = com.amap.openapi.dv.f4711a
                r20 = 0
                r21 = 0
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                java.lang.String r14 = "ID limit "
                r7.<init>(r14)
                r7.append(r10)
                java.lang.String r14 = ", "
                r7.append(r14)
                com.amap.openapi.ee r14 = com.amap.openapi.ee.this
                com.amap.openapi.dq r14 = r14.f
                int r14 = r14.a()
                r7.append(r14)
                java.lang.String r22 = r7.toString()
                android.database.Cursor r7 = r17.a(r18, r19, r20, r21, r22)
                if (r7 == 0) goto L_0x00cf
                int r14 = r7.getCount()     // Catch:{ Exception -> 0x00cd }
                if (r14 != 0) goto L_0x005f
                goto L_0x00cf
            L_0x005f:
                com.amap.openapi.ee r14 = com.amap.openapi.ee.this
                com.amap.openapi.dq r14 = r14.f
                int r14 = r14.a()
                r23 = r7
                long r6 = (long) r14
                long r10 = r10 + r6
            L_0x006d:
                boolean r6 = r23.moveToNext()     // Catch:{ Exception -> 0x00c7, all -> 0x00c0 }
                if (r6 == 0) goto L_0x00b1
                r6 = r23
                long r17 = r6.getLong(r15)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r7 = 1
                int r14 = r6.getInt(r7)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r7 = 2
                byte[] r7 = r6.getBlob(r7)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r15 = 3
                r24 = r10
                long r10 = r6.getLong(r15)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r15 = 4
                int r15 = r6.getInt(r15)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r26 = r12
                long r12 = (long) r15     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                long r12 = r12 + r8
                int r15 = (r12 > r29 ? 1 : (r12 == r29 ? 0 : -1))
                if (r15 > 0) goto L_0x00b7
                int r7 = com.amap.openapi.dy.a((com.loc.fc) r4, (byte[]) r7)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                int r7 = com.amap.openapi.dy.a(r4, r14, r7, r10)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r5.add(r7)     // Catch:{ Exception -> 0x00c9, all -> 0x00af }
                r23 = r6
                r8 = r12
                r12 = r17
                r10 = r24
                r15 = 0
                goto L_0x006d
            L_0x00af:
                r0 = move-exception
                goto L_0x00c3
            L_0x00b1:
                r24 = r10
                r26 = r12
                r6 = r23
            L_0x00b7:
                com.amap.location.common.util.e.a((android.database.Cursor) r6)
                r10 = r24
                r12 = r26
                goto L_0x0018
            L_0x00c0:
                r0 = move-exception
                r6 = r23
            L_0x00c3:
                com.amap.location.common.util.e.a((android.database.Cursor) r6)
                throw r0
            L_0x00c7:
                r6 = r23
            L_0x00c9:
                com.amap.location.common.util.e.a((android.database.Cursor) r6)
                return r16
            L_0x00cd:
                r6 = r7
                goto L_0x00d4
            L_0x00cf:
                r6 = r7
                com.amap.location.common.util.e.a((android.database.Cursor) r6)     // Catch:{ Exception -> 0x00d4 }
                goto L_0x00d7
            L_0x00d4:
                com.amap.location.common.util.e.a((android.database.Cursor) r6)
            L_0x00d7:
                r2 = 0
                int r6 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
                if (r6 >= 0) goto L_0x00de
                return r16
            L_0x00de:
                int r2 = r5.size()
                int[] r2 = new int[r2]
                r3 = 0
            L_0x00e5:
                int r6 = r5.size()
                if (r3 >= r6) goto L_0x00fa
                java.lang.Object r6 = r5.get(r3)
                java.lang.Integer r6 = (java.lang.Integer) r6
                int r6 = r6.intValue()
                r2[r3] = r6
                int r3 = r3 + 1
                goto L_0x00e5
            L_0x00fa:
                com.amap.openapi.ee r3 = com.amap.openapi.ee.this
                com.amap.openapi.dt r3 = r3.e
                android.content.Context r3 = r3.a()
                int r3 = com.amap.openapi.bk.a(r4, r3)
                int r2 = com.amap.openapi.dz.b((com.loc.fc) r4, (int[]) r2)
                com.amap.openapi.dz.a(r4)
                r5 = 1
                com.amap.openapi.dz.a((com.loc.fc) r4, (byte) r5)
                com.amap.openapi.dz.a((com.loc.fc) r4, (int) r3)
                com.amap.openapi.dz.c(r4, r2)
                int r2 = com.amap.openapi.dz.b(r4)
                com.amap.openapi.dz.d(r4, r2)
                byte[] r2 = r4.f()
                r0.f4725a = r2
                r0.b = r12
                r0.c = r8
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.ee.b.a(long):java.lang.Object");
        }

        public void a() {
        }

        public void a(int i) {
        }

        public void a(int i, Object obj) {
            if (obj instanceof d) {
                ee.this.e.a(ee.this.h, i, ((d) obj).c);
            }
        }

        public boolean a(Object obj) {
            if (obj instanceof d) {
                return ea.a(ee.this.g, ee.this.e.a(ee.this.h), ((d) obj).f4725a, ee.this.f.f());
            }
            return false;
        }

        public void b() {
        }

        public void b(Object obj) {
            if (obj instanceof d) {
                ee.this.d.a(ee.this.c, "ID <= ? ", new String[]{String.valueOf(((d) obj).b)});
            }
        }

        public boolean b(int i) {
            return ee.this.f.c(i);
        }

        public long c() {
            Cursor a2 = ee.this.d.a(ee.this.c, new String[]{"sum(size)"}, (String) null, (String[]) null, (String) null);
            long j = 0;
            if (a2 != null) {
                try {
                    if (a2.moveToFirst()) {
                        j = a2.getLong(0);
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    e.a(a2);
                    throw th;
                }
            }
            e.a(a2);
            return j;
        }

        public long c(int i) {
            return ee.this.f.b(i) - ee.this.e.a(ee.this.h, i);
        }

        public int d() {
            return 3;
        }

        public long d(int i) {
            return ee.this.f.a(i);
        }

        public long e() {
            return ee.this.f.d();
        }

        public int f() {
            return ee.this.f.f();
        }

        public void g() {
        }

        public Executor h() {
            return null;
        }
    }

    static class c implements bi.a {

        /* renamed from: a  reason: collision with root package name */
        int f4724a;
        long b;
        byte[] c;

        c() {
        }

        public long a() {
            return (long) ((this.c == null ? 0 : this.c.length) + 24);
        }
    }

    static class d {

        /* renamed from: a  reason: collision with root package name */
        byte[] f4725a;
        long b;
        long c;

        d() {
        }
    }

    public void a() {
        this.i.a();
        this.j.a();
    }

    public void a(int i2) {
        if (i2 != -1) {
            this.f.b();
            this.j.a(20000);
        }
    }

    public void a(int i2, byte[] bArr) {
        this.f.b();
        c cVar = new c();
        cVar.f4724a = i2;
        cVar.b = System.currentTimeMillis();
        cVar.c = bArr;
        this.i.a(cVar);
    }

    public void a(@NonNull dt dtVar, @NonNull dq dqVar, @NonNull IHttpClient iHttpClient, int i2, @NonNull Looper looper) {
        this.b = dt.c(i2);
        this.f4721a += this.b;
        this.e = dtVar;
        this.h = i2;
        this.f = new ed(dqVar);
        this.g = iHttpClient;
        this.d = dtVar.b();
        this.c = dt.b(i2);
        this.i = new bi<>();
        this.j = new bj();
        this.i.a(new a(), looper);
        this.j.a(dtVar.a(), (bj.a) new b(), looper);
        this.j.a(20000);
    }

    public void b() {
        this.i.b();
    }
}
