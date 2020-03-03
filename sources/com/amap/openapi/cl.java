package com.amap.openapi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.amap.location.common.log.ALLog;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;

public class cl {

    /* renamed from: a  reason: collision with root package name */
    private static volatile cl f4662a;
    private a b;
    private Object c = new Object();

    static class a extends SQLiteOpenHelper {
        a(Context context) {
            super(context, "OffEvent.db", (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            cm.a(sQLiteDatabase);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private cl() {
    }

    public static cl a() {
        if (f4662a == null) {
            synchronized (cl.class) {
                if (f4662a == null) {
                    f4662a = new cl();
                }
            }
        }
        return f4662a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dd A[SYNTHETIC, Splitter:B:54:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e8 A[SYNTHETIC, Splitter:B:60:0x00e8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(int r17) {
        /*
            r16 = this;
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
            r3 = r16
            com.amap.openapi.cl$a r4 = r3.b     // Catch:{ Exception -> 0x0014 }
            android.database.sqlite.SQLiteDatabase r4 = r4.getWritableDatabase()     // Catch:{ Exception -> 0x0014 }
            goto L_0x0015
        L_0x0014:
            r4 = r2
        L_0x0015:
            if (r4 != 0) goto L_0x001c
            java.lang.String r0 = r1.toString()
            return r0
        L_0x001c:
            r4.beginTransaction()     // Catch:{ Throwable -> 0x00c3 }
            java.lang.String r6 = "ACL"
            r5 = 2
            java.lang.String[] r7 = new java.lang.String[r5]     // Catch:{ Throwable -> 0x00c3 }
            java.lang.String r5 = com.amap.openapi.cm.f4663a     // Catch:{ Throwable -> 0x00c3 }
            r14 = 0
            r7[r14] = r5     // Catch:{ Throwable -> 0x00c3 }
            java.lang.String r5 = com.amap.openapi.cm.b     // Catch:{ Throwable -> 0x00c3 }
            r15 = 1
            r7[r15] = r5     // Catch:{ Throwable -> 0x00c3 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.lang.String r12 = "frequency DESC"
            java.lang.String r13 = java.lang.String.valueOf(r17)     // Catch:{ Throwable -> 0x00c3 }
            r5 = r4
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Throwable -> 0x00c3 }
            if (r5 == 0) goto L_0x00b4
            boolean r2 = r5.moveToFirst()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r2 == 0) goto L_0x00b4
        L_0x0045:
            boolean r2 = r5.isAfterLast()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r2 != 0) goto L_0x0093
            java.lang.String r2 = r5.getString(r14)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            int r6 = r5.getInt(r15)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r2 == 0) goto L_0x008f
            java.lang.String r7 = "_"
            java.lang.String[] r7 = r2.split(r7)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r7 == 0) goto L_0x008f
            int r8 = r7.length     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r9 = 3
            r10 = 4
            if (r8 == r9) goto L_0x0066
            int r8 = r7.length     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r8 == r10) goto L_0x0066
            goto L_0x008f
        L_0x0066:
            int r7 = r7.length     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r7 != r10) goto L_0x006b
            r7 = 0
            goto L_0x006c
        L_0x006b:
            r7 = 1
        L_0x006c:
            int r8 = r1.length()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r8 == 0) goto L_0x0077
            java.lang.String r8 = "#"
            r1.append(r8)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
        L_0x0077:
            r1.append(r7)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            java.lang.String r7 = "|"
            r1.append(r7)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r1.append(r2)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            java.lang.String r7 = "|"
            r1.append(r7)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r1.append(r6)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r0.add(r2)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
        L_0x008f:
            r5.moveToNext()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            goto L_0x0045
        L_0x0093:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
        L_0x0097:
            boolean r2 = r0.hasNext()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r2 == 0) goto L_0x00b4
            java.lang.Object r2 = r0.next()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            java.lang.String r6 = "ACL"
            java.lang.String r7 = "id=?"
            java.lang.String[] r8 = new java.lang.String[r15]     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r8[r14] = r2     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            r4.delete(r6, r7, r8)     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            goto L_0x0097
        L_0x00af:
            r0 = move-exception
            goto L_0x00e6
        L_0x00b1:
            r0 = move-exception
            r2 = r5
            goto L_0x00c4
        L_0x00b4:
            r4.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00b1, all -> 0x00af }
            if (r5 == 0) goto L_0x00bc
            r5.close()     // Catch:{ Throwable -> 0x00e1 }
        L_0x00bc:
            r4.endTransaction()     // Catch:{ Throwable -> 0x00e1 }
            goto L_0x00e1
        L_0x00c0:
            r0 = move-exception
            r5 = r2
            goto L_0x00e6
        L_0x00c3:
            r0 = move-exception
        L_0x00c4:
            java.lang.String r5 = "@_18_7_@"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            java.lang.String r7 = "@_18_7_3_@"
            r6.<init>(r7)     // Catch:{ all -> 0x00c0 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x00c0 }
            r6.append(r0)     // Catch:{ all -> 0x00c0 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00c0 }
            com.amap.location.common.log.ALLog.d(r5, r0)     // Catch:{ all -> 0x00c0 }
            if (r2 == 0) goto L_0x00bc
            r2.close()     // Catch:{ Throwable -> 0x00e1 }
            goto L_0x00bc
        L_0x00e1:
            java.lang.String r0 = r1.toString()
            return r0
        L_0x00e6:
            if (r5 == 0) goto L_0x00eb
            r5.close()     // Catch:{ Throwable -> 0x00ee }
        L_0x00eb:
            r4.endTransaction()     // Catch:{ Throwable -> 0x00ee }
        L_0x00ee:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.cl.a(int):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[Catch:{ Throwable -> 0x002e, all -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d A[Catch:{ Throwable -> 0x002e, all -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0069 A[SYNTHETIC, Splitter:B:17:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[SYNTHETIC, Splitter:B:27:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0090 A[SYNTHETIC, Splitter:B:31:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r13) {
        /*
            r12 = this;
            r0 = 0
            com.amap.openapi.cl$a r1 = r12.b     // Catch:{ Throwable -> 0x0070 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0070 }
            java.lang.String r3 = "ACL"
            r10 = 1
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x0070 }
            java.lang.String r2 = com.amap.openapi.cm.b     // Catch:{ Throwable -> 0x0070 }
            r11 = 0
            r4[r11] = r2     // Catch:{ Throwable -> 0x0070 }
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x0070 }
            r6[r11] = r13     // Catch:{ Throwable -> 0x0070 }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r1
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0070 }
            if (r2 == 0) goto L_0x0031
            boolean r3 = r2.moveToFirst()     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            if (r3 == 0) goto L_0x0031
            int r3 = r2.getInt(r11)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            goto L_0x0032
        L_0x002c:
            r13 = move-exception
            goto L_0x008e
        L_0x002e:
            r13 = move-exception
            r0 = r2
            goto L_0x0071
        L_0x0031:
            r3 = 0
        L_0x0032:
            if (r3 != 0) goto L_0x004d
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r3.<init>()     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            java.lang.String r4 = "id"
            r3.put(r4, r13)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            java.lang.String r13 = "frequency"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r3.put(r13, r4)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            java.lang.String r13 = "ACL"
            r1.insert(r13, r0, r3)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            goto L_0x0067
        L_0x004d:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r0.<init>()     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            java.lang.String r4 = "frequency"
            int r3 = r3 + r10
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r0.put(r4, r3)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            java.lang.String r3 = "ACL"
            java.lang.String r4 = "id=?"
            java.lang.String[] r5 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r5[r11] = r13     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
            r1.update(r3, r0, r4, r5)     // Catch:{ Throwable -> 0x002e, all -> 0x002c }
        L_0x0067:
            if (r2 == 0) goto L_0x008d
            r2.close()     // Catch:{ Throwable -> 0x006c }
        L_0x006c:
            return
        L_0x006d:
            r13 = move-exception
            r2 = r0
            goto L_0x008e
        L_0x0070:
            r13 = move-exception
        L_0x0071:
            java.lang.String r1 = "@_18_7_@"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            java.lang.String r3 = "@_18_7_4_@"
            r2.<init>(r3)     // Catch:{ all -> 0x006d }
            java.lang.String r13 = android.util.Log.getStackTraceString(r13)     // Catch:{ all -> 0x006d }
            r2.append(r13)     // Catch:{ all -> 0x006d }
            java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x006d }
            com.amap.location.common.log.ALLog.d(r1, r13)     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x008d
            r0.close()     // Catch:{ Throwable -> 0x008d }
        L_0x008d:
            return
        L_0x008e:
            if (r2 == 0) goto L_0x0093
            r2.close()     // Catch:{ Throwable -> 0x0093 }
        L_0x0093:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.cl.a(java.lang.String):void");
    }

    private void b(Context context) {
        if (this.b == null) {
            this.b = new a(context);
        }
    }

    public void a(Context context) {
        String a2;
        synchronized (this.c) {
            b(context);
        }
        if (TextUtils.isEmpty(com.amap.location.common.a.c(context))) {
            ALLog.d("@_18_7_@", "@_18_7_5_@");
            return;
        }
        boolean a3 = cp.a(context, 500);
        int b2 = cp.b(context, 500);
        ALLog.d("@_18_7_@", "@_18_7_1_@(" + a3 + "," + b2 + Operators.BRACKET_END_STR);
        if (a3 && b2 > 0 && (a2 = a(b2)) != null && a2.length() > 0) {
            com.amap.location.offline.upload.a.a(800002, a2.getBytes());
            int i = 0;
            String[] split = a2.split("#");
            if (split != null && split.length > 0) {
                i = split.length;
            }
            cp.c(context, i);
            ALLog.d("@_18_7_@", "@_18_7_2_@" + i);
        }
    }

    public void a(Context context, bs bsVar) {
        synchronized (this.c) {
            b(context);
        }
        if (!TextUtils.isEmpty(bsVar.f)) {
            a(bsVar.f.replace(":", JSMethod.NOT_SET));
        }
    }
}
