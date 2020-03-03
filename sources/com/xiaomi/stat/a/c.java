package com.xiaomi.stat.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.stat.MiStatParams;
import com.xiaomi.stat.a;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.d.k;
import com.xiaomi.stat.d.m;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23000a = "EventManager";
    private static final int b = 10;
    private static final int c = 0;
    private static final int d = 300;
    private static final int e = 122880;
    private static final int f = 55;
    private static final int g = 2;
    private static final String h = "priority DESC, _id ASC";
    private static final int i = 7;
    private static final long j = 52428800;
    private static c k;
    /* access modifiers changed from: private */
    public a l;
    private File m;

    public static c a() {
        if (k == null) {
            synchronized (c.class) {
                if (k == null) {
                    k = new c();
                }
            }
        }
        return k;
    }

    private c() {
        Context a2 = ak.a();
        this.l = new a(a2);
        this.m = a2.getDatabasePath(j.f23007a);
    }

    public void a(l lVar) {
        com.xiaomi.stat.c.a(new d(this, lVar));
        k.c(f23000a, "add event: name=" + lVar.f23009a);
    }

    /* access modifiers changed from: private */
    public void b(l lVar) {
        d();
        SQLiteDatabase writableDatabase = this.l.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("e", lVar.f23009a);
        contentValues.put("eg", lVar.b);
        contentValues.put("tp", lVar.c);
        contentValues.put("ts", Long.valueOf(lVar.e));
        if (c(lVar)) {
            a((MiStatParams) lVar.d);
        }
        contentValues.put("ps", lVar.d.toJsonString());
        contentValues.put(j.i, lVar.f);
        contentValues.put(j.j, Integer.valueOf(lVar.g ? 1 : 0));
        contentValues.put("priority", Integer.valueOf(TextUtils.equals(lVar.b, l.a.h) ? 10 : 0));
        writableDatabase.insert(j.b, (String) null, contentValues);
    }

    private boolean c(l lVar) {
        return !lVar.c.startsWith(l.a.w);
    }

    private void a(MiStatParams miStatParams) {
        miStatParams.putString(l.a.n, com.xiaomi.stat.d.c.b());
        miStatParams.putString(l.a.o, a.g);
        miStatParams.putString(l.a.p, m.c());
        miStatParams.putString(l.a.q, m.d());
        miStatParams.putString(l.a.r, com.xiaomi.stat.d.l.b(ak.a()));
        miStatParams.putString(l.a.s, m.a(ak.a()));
        miStatParams.putString(l.a.t, Build.MANUFACTURER);
        miStatParams.putString(l.a.u, Build.MODEL);
        miStatParams.putString(l.a.v, m.b());
    }

    public k a(b[] bVarArr) {
        FutureTask futureTask = new FutureTask(new e(this, bVarArr));
        com.xiaomi.stat.c.a(futureTask);
        try {
            return (k) futureTask.get();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:28|29|30|31|32|33|(2:64|35)(2:36|67)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0109 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0116 A[Catch:{ Exception -> 0x0147, all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0111 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.stat.a.k b(com.xiaomi.stat.a.b[] r28) {
        /*
            r27 = this;
            r1 = r27
            r0 = r28
            int r3 = r0.length     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            r4 = 0
            r5 = 1
            if (r3 != r5) goto L_0x0012
            r3 = r0[r4]     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            java.lang.String r3 = r3.a()     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            r9 = r3
            r3 = 0
            goto L_0x0014
        L_0x0012:
            r3 = 1
            r9 = 0
        L_0x0014:
            com.xiaomi.stat.a.a r6 = r1.l     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            android.database.sqlite.SQLiteDatabase r6 = r6.getReadableDatabase()     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            java.lang.String r7 = "events"
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "priority DESC, _id ASC"
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x014d, all -> 0x014a }
            java.lang.String r7 = "_id"
            int r7 = r6.getColumnIndex(r7)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r8 = "e"
            int r8 = r6.getColumnIndex(r8)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r9 = "eg"
            int r9 = r6.getColumnIndex(r9)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r10 = "tp"
            int r10 = r6.getColumnIndex(r10)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r11 = "ts"
            int r11 = r6.getColumnIndex(r11)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r12 = "ps"
            int r12 = r6.getColumnIndex(r12)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r13 = "sub"
            int r13 = r6.getColumnIndex(r13)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r14 = "is_am"
            int r14 = r6.getColumnIndex(r14)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            org.json.JSONArray r15 = new org.json.JSONArray     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r15.<init>()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r4.<init>()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r16 = 0
        L_0x0062:
            boolean r17 = r6.moveToNext()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r17 == 0) goto L_0x012d
            r18 = r3
            long r2 = r6.getLong(r7)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r5 = r6.getString(r8)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r19 = r7
            java.lang.String r7 = r6.getString(r9)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r20 = r8
            java.lang.String r8 = r6.getString(r10)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r21 = r9
            r22 = r10
            long r9 = r6.getLong(r11)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r23 = r11
            java.lang.String r11 = r6.getString(r12)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r24 = r12
            java.lang.String r12 = r6.getString(r13)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r25 = r13
            int r13 = r6.getInt(r14)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r26 = r14
            r14 = 1
            if (r13 != r14) goto L_0x009f
            r13 = 1
            goto L_0x00a0
        L_0x009f:
            r13 = 0
        L_0x00a0:
            if (r18 == 0) goto L_0x00a8
            boolean r12 = r1.a(r0, r12, r7, r13)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r12 == 0) goto L_0x0118
        L_0x00a8:
            int r12 = r11.length()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            int r12 = r12 * 2
            int r12 = r12 + 55
            int r16 = r16 + r12
            boolean r12 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r12 != 0) goto L_0x00c0
            int r12 = r5.length()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            int r12 = r12 * 2
            int r16 = r16 + r12
        L_0x00c0:
            boolean r12 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r12 != 0) goto L_0x00ce
            int r12 = r7.length()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            int r12 = r12 * 2
            int r16 = r16 + r12
        L_0x00ce:
            r12 = r16
            r13 = 122880(0x1e000, float:1.72192E-40)
            if (r12 <= r13) goto L_0x00d7
            r0 = 0
            goto L_0x012e
        L_0x00d7:
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r13.<init>()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            java.lang.String r14 = "e"
            r13.put(r14, r5)     // Catch:{ JSONException -> 0x0109 }
            java.lang.String r5 = "eg"
            r13.put(r5, r7)     // Catch:{ JSONException -> 0x0109 }
            java.lang.String r5 = "tp"
            r13.put(r5, r8)     // Catch:{ JSONException -> 0x0109 }
            java.lang.String r5 = "ts"
            r13.put(r5, r9)     // Catch:{ JSONException -> 0x0109 }
            java.lang.String r5 = "eid"
            r13.put(r5, r2)     // Catch:{ JSONException -> 0x0109 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0109 }
            r5.<init>(r11)     // Catch:{ JSONException -> 0x0109 }
            java.lang.String r7 = "ps"
            r13.put(r7, r5)     // Catch:{ JSONException -> 0x0109 }
            r15.put(r13)     // Catch:{ JSONException -> 0x0109 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ JSONException -> 0x0109 }
            r4.add(r2)     // Catch:{ JSONException -> 0x0109 }
        L_0x0109:
            int r2 = r4.size()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r3 = 300(0x12c, float:4.2E-43)
            if (r2 < r3) goto L_0x0116
            boolean r0 = r6.isLast()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            goto L_0x012e
        L_0x0116:
            r16 = r12
        L_0x0118:
            r3 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r11 = r23
            r12 = r24
            r13 = r25
            r14 = r26
            r5 = 1
            goto L_0x0062
        L_0x012d:
            r0 = 1
        L_0x012e:
            int r2 = r4.size()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r2 <= 0) goto L_0x013f
            com.xiaomi.stat.a.k r2 = new com.xiaomi.stat.a.k     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            r2.<init>(r15, r4, r0)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
            if (r6 == 0) goto L_0x013e
            r6.close()
        L_0x013e:
            return r2
        L_0x013f:
            if (r6 == 0) goto L_0x015d
            r6.close()
            goto L_0x015d
        L_0x0145:
            r0 = move-exception
            goto L_0x0161
        L_0x0147:
            r0 = move-exception
            r2 = r6
            goto L_0x014f
        L_0x014a:
            r0 = move-exception
            r6 = 0
            goto L_0x0161
        L_0x014d:
            r0 = move-exception
            r2 = 0
        L_0x014f:
            java.lang.String r3 = "EventManager"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x015f }
            com.xiaomi.stat.d.k.b(r3, r0)     // Catch:{ all -> 0x015f }
            if (r2 == 0) goto L_0x015d
            r2.close()
        L_0x015d:
            r2 = 0
            return r2
        L_0x015f:
            r0 = move-exception
            r6 = r2
        L_0x0161:
            if (r6 == 0) goto L_0x0166
            r6.close()
        L_0x0166:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.a.c.b(com.xiaomi.stat.a.b[]):com.xiaomi.stat.a.k");
    }

    private boolean a(b[] bVarArr, String str, String str2, boolean z) {
        for (b a2 : bVarArr) {
            if (a2.a(str, str2, z)) {
                return true;
            }
        }
        return false;
    }

    public void a(ArrayList<Long> arrayList) {
        FutureTask futureTask = new FutureTask(new f(this, arrayList), (Object) null);
        com.xiaomi.stat.c.a(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void b(ArrayList<Long> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            try {
                SQLiteDatabase writableDatabase = this.l.getWritableDatabase();
                StringBuilder sb = new StringBuilder(((Long.toString(arrayList.get(0).longValue()).length() + 1) * arrayList.size()) + 16);
                sb.append("_id");
                sb.append(" in (");
                sb.append(arrayList.get(0));
                int size = arrayList.size();
                for (int i2 = 1; i2 < size; i2++) {
                    sb.append(",");
                    sb.append(arrayList.get(i2));
                }
                sb.append(Operators.BRACKET_END_STR);
                int delete = writableDatabase.delete(j.b, sb.toString(), (String[]) null);
                k.c(f23000a, "deleted events number " + delete);
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        com.xiaomi.stat.c.a(new g(this));
    }

    private void d() {
        if (this.m.exists() && this.m.length() >= 52428800) {
            k.e(f23000a, "database too big: " + this.m.length());
            this.l.getWritableDatabase().delete(j.b, (String) null, (String[]) null);
        }
    }

    public void a(String str) {
        com.xiaomi.stat.c.a(new h(this, str));
    }

    public long c() {
        FutureTask futureTask = new FutureTask(new i(this));
        com.xiaomi.stat.c.a(futureTask);
        try {
            return ((Long) futureTask.get()).longValue();
        } catch (InterruptedException | ExecutionException unused) {
            return -1;
        }
    }
}
