package com.amap.openapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.amap.location.common.log.ALLog;
import com.mi.global.shop.model.Tags;
import com.xiaomi.verificationsdk.internal.Constants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class by {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f4646a = {"id", Tags.Nearby.LAT, "lng", "acc", "conf", "timestamp"};
    private static final String[] b = {"id", "originid", Constants.Q};
    private static volatile by c = null;
    private bx d;
    private cb e;
    private ReadWriteLock f = new ReentrantReadWriteLock();

    private by(Context context) {
        this.d = new bx(context);
        this.e = new cb(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a A[SYNTHETIC, Splitter:B:20:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13, long r14) {
        /*
            r11 = this;
            r0 = 1
            r1 = 0
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            java.lang.String r2 = "frequency"
            r10 = 0
            r4[r10] = r2     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            r6[r10] = r14     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            r3 = r13
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x003e, all -> 0x0037 }
            if (r12 == 0) goto L_0x0031
            boolean r13 = r12.moveToFirst()     // Catch:{ Throwable -> 0x003f, all -> 0x002e }
            if (r13 == 0) goto L_0x0031
            int r13 = r12.getInt(r10)     // Catch:{ Throwable -> 0x003f, all -> 0x002e }
            if (r12 == 0) goto L_0x002d
            r12.close()     // Catch:{ Throwable -> 0x002d }
        L_0x002d:
            return r13
        L_0x002e:
            r13 = move-exception
            r1 = r12
            goto L_0x0038
        L_0x0031:
            if (r12 == 0) goto L_0x0042
        L_0x0033:
            r12.close()     // Catch:{ Throwable -> 0x0042 }
            goto L_0x0042
        L_0x0037:
            r13 = move-exception
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ Throwable -> 0x003d }
        L_0x003d:
            throw r13
        L_0x003e:
            r12 = r1
        L_0x003f:
            if (r12 == 0) goto L_0x0042
            goto L_0x0033
        L_0x0042:
            r12 = -1
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(android.database.sqlite.SQLiteDatabase, java.lang.String, long):int");
    }

    private ContentValues a(ContentValues contentValues, long j, String str, long j2, int i) {
        contentValues.clear();
        contentValues.put("id", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            contentValues.put("originid", str);
        } else {
            contentValues.put("originid", Long.valueOf(j2));
        }
        contentValues.put(Constants.Q, Integer.valueOf(i));
        return contentValues;
    }

    private ContentValues a(ContentValues contentValues, ci ciVar) {
        contentValues.clear();
        contentValues.put("id", Long.valueOf(ciVar.a()));
        contentValues.put(Tags.Nearby.LAT, Integer.valueOf(ciVar.b()));
        contentValues.put("lng", Integer.valueOf(ciVar.c()));
        contentValues.put("acc", Short.valueOf(ciVar.d()));
        contentValues.put("conf", Byte.valueOf(ciVar.e()));
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        return contentValues;
    }

    public static by a(@NonNull Context context) {
        if (c == null) {
            synchronized (by.class) {
                if (c == null) {
                    c = new by(context);
                }
            }
        }
        return c;
    }

    private HashSet<Long> a(List<Long> list) {
        HashSet<Long> hashSet = new HashSet<>();
        if (list != null) {
            for (Long longValue : list) {
                hashSet.add(Long.valueOf(cn.a(longValue.longValue())));
            }
        }
        return hashSet;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            sQLiteDatabase.replace(str, (String) null, contentValues);
        } catch (Throwable unused) {
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, boolean z) {
        sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, z ? 4 : 5);
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, HashSet<Long> hashSet, ContentValues contentValues) {
        contentValues.clear();
        contentValues.put(Tags.Nearby.LAT, 0);
        contentValues.put("lng", 0);
        contentValues.put("acc", 0);
        contentValues.put("conf", -1);
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        Iterator<Long> it = hashSet.iterator();
        while (it.hasNext()) {
            contentValues.put("id", Long.valueOf(it.next().longValue()));
            a(sQLiteDatabase, str, contentValues);
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, HashSet<Long> hashSet, HashSet<Long> hashSet2) {
        if (hashSet != null) {
            Iterator<Long> it = hashSet.iterator();
            while (it.hasNext()) {
                sQLiteDatabase.delete("CL", "id=?", new String[]{String.valueOf(it.next().longValue())});
            }
        }
        if (hashSet2 != null) {
            Iterator<Long> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                sQLiteDatabase.delete("AP", "id=?", new String[]{String.valueOf(it2.next())});
            }
        }
    }

    private void a(String str, ContentValues contentValues, long j) {
        contentValues.clear();
        contentValues.put("conf", 0);
        this.d.getWritableDatabase().update(str, contentValues, "id=?", new String[]{String.valueOf(j)});
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, long j, ContentValues contentValues) {
        int a2 = a(sQLiteDatabase, str, j);
        if (a2 < 0) {
            return false;
        }
        contentValues.clear();
        contentValues.put(Constants.Q, Integer.valueOf(a2 + 1));
        sQLiteDatabase.update(str, contentValues, "id=?", new String[]{String.valueOf(j)});
        return true;
    }

    private HashSet<Long> b(List<String> list) {
        HashSet<Long> hashSet = new HashSet<>();
        if (list != null) {
            for (String a2 : list) {
                long a3 = cn.a(a2);
                if (a3 != -1) {
                    hashSet.add(Long.valueOf(a3));
                }
            }
        }
        return hashSet;
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str, long j, ContentValues contentValues) {
        try {
            contentValues.clear();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis() / 1000));
            sQLiteDatabase.update(str, contentValues, "id=?", new String[]{String.valueOf(j)});
        } catch (Throwable unused) {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.amap.openapi.bs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.amap.openapi.bs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: com.amap.openapi.bs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.amap.openapi.bs} */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0081, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0082, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9 A[SYNTHETIC, Splitter:B:35:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c3 A[SYNTHETIC, Splitter:B:44:0x00c3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amap.openapi.bs a(java.lang.String r25, long r26) {
        /*
            r24 = this;
            r1 = 0
            r2 = r24
            com.amap.openapi.bx r0 = r2.d     // Catch:{ Throwable -> 0x008e }
            android.database.sqlite.SQLiteDatabase r0 = r0.getReadableDatabase()     // Catch:{ Throwable -> 0x008e }
            java.lang.String r4 = "CL"
            java.lang.String[] r5 = f4646a     // Catch:{ Throwable -> 0x008e }
            java.lang.String r6 = "id=?"
            r11 = 1
            java.lang.String[] r7 = new java.lang.String[r11]     // Catch:{ Throwable -> 0x008e }
            java.lang.String r3 = java.lang.String.valueOf(r26)     // Catch:{ Throwable -> 0x008e }
            r12 = 0
            r7[r12] = r3     // Catch:{ Throwable -> 0x008e }
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x008e }
            if (r3 == 0) goto L_0x0085
            boolean r4 = r3.moveToFirst()     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            if (r4 == 0) goto L_0x0085
            r4 = 5
            long r4 = r3.getLong(r4)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r6 = 15552000(0xed4e00, double:7.683709E-317)
            long r6 = r6 + r4
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r13 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r13
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x006f
            int r20 = r3.getInt(r11)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r0 = 2
            int r21 = r3.getInt(r0)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r0 = 3
            int r22 = r3.getInt(r0)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r0 = 4
            int r23 = r3.getInt(r0)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            com.amap.openapi.bs r6 = new com.amap.openapi.bs     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r16 = 1
            r15 = r6
            r17 = r25
            r18 = r26
            r15.<init>(r16, r17, r18, r20, r21, r22, r23)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r0 = 604800(0x93a80, double:2.98811E-318)
            long r4 = r4 + r0
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x006d, all -> 0x007f }
            long r0 = r0 / r13
            int r7 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r7 >= 0) goto L_0x006b
            r6.h = r11     // Catch:{ Throwable -> 0x006d, all -> 0x007f }
        L_0x006b:
            r1 = r6
            goto L_0x0085
        L_0x006d:
            r0 = move-exception
            goto L_0x0083
        L_0x006f:
            java.lang.String r4 = "CL"
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r6[r12] = r7     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            r0.delete(r4, r5, r6)     // Catch:{ Throwable -> 0x0081, all -> 0x007f }
            goto L_0x0085
        L_0x007f:
            r0 = move-exception
            goto L_0x00c1
        L_0x0081:
            r0 = move-exception
            r6 = r1
        L_0x0083:
            r1 = r3
            goto L_0x0090
        L_0x0085:
            if (r3 == 0) goto L_0x00ad
            r3.close()     // Catch:{ Throwable -> 0x00ad }
            goto L_0x00ad
        L_0x008b:
            r0 = move-exception
            r3 = r1
            goto L_0x00c1
        L_0x008e:
            r0 = move-exception
            r6 = r1
        L_0x0090:
            java.lang.String r3 = "@_18_4_@"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            java.lang.String r5 = "@_18_4_1_@"
            r4.<init>(r5)     // Catch:{ all -> 0x008b }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x008b }
            r4.append(r0)     // Catch:{ all -> 0x008b }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x008b }
            com.amap.location.common.log.ALLog.d(r3, r0)     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ Throwable -> 0x00ac }
        L_0x00ac:
            r1 = r6
        L_0x00ad:
            if (r1 != 0) goto L_0x00bf
            com.amap.openapi.bs r0 = new com.amap.openapi.bs
            r4 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = -1
            r3 = r0
            r5 = r25
            r6 = r26
            r3.<init>(r4, r5, r6, r8, r9, r10, r11)
            goto L_0x00c0
        L_0x00bf:
            r0 = r1
        L_0x00c0:
            return r0
        L_0x00c1:
            if (r3 == 0) goto L_0x00c6
            r3.close()     // Catch:{ Throwable -> 0x00c6 }
        L_0x00c6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(java.lang.String, long):com.amap.openapi.bs");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080 A[SYNTHETIC, Splitter:B:23:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[SYNTHETIC, Splitter:B:28:0x0086] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> a(int r13, int r14) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.amap.openapi.cb r2 = r12.e     // Catch:{ Throwable -> 0x0066 }
            android.database.sqlite.SQLiteDatabase r3 = r2.getReadableDatabase()     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r4 = "CL"
            java.lang.String[] r5 = b     // Catch:{ Throwable -> 0x0066 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r6 = "frequency>="
            r2.<init>(r6)     // Catch:{ Throwable -> 0x0066 }
            r2.append(r13)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r13 = " AND time<"
            r2.append(r13)     // Catch:{ Throwable -> 0x0066 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0066 }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            r8 = 86400(0x15180, double:4.26873E-319)
            long r6 = r6 - r8
            r2.append(r6)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r6 = r2.toString()     // Catch:{ Throwable -> 0x0066 }
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "frequency DESC"
            java.lang.String r11 = java.lang.String.valueOf(r14)     // Catch:{ Throwable -> 0x0066 }
            android.database.Cursor r13 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x0066 }
            if (r13 == 0) goto L_0x005e
            boolean r14 = r13.moveToFirst()     // Catch:{ Throwable -> 0x005b, all -> 0x0058 }
            if (r14 == 0) goto L_0x005e
        L_0x0046:
            boolean r14 = r13.isAfterLast()     // Catch:{ Throwable -> 0x005b, all -> 0x0058 }
            if (r14 != 0) goto L_0x005e
            r14 = 1
            java.lang.String r14 = r13.getString(r14)     // Catch:{ Throwable -> 0x005b, all -> 0x0058 }
            r0.add(r14)     // Catch:{ Throwable -> 0x005b, all -> 0x0058 }
            r13.moveToNext()     // Catch:{ Throwable -> 0x005b, all -> 0x0058 }
            goto L_0x0046
        L_0x0058:
            r14 = move-exception
            r1 = r13
            goto L_0x0084
        L_0x005b:
            r14 = move-exception
            r1 = r13
            goto L_0x0067
        L_0x005e:
            if (r13 == 0) goto L_0x0083
            r13.close()     // Catch:{ Throwable -> 0x0083 }
            goto L_0x0083
        L_0x0064:
            r14 = move-exception
            goto L_0x0084
        L_0x0066:
            r14 = move-exception
        L_0x0067:
            java.lang.String r13 = "@_18_4_@"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            java.lang.String r3 = "@_18_4_3_@"
            r2.<init>(r3)     // Catch:{ all -> 0x0064 }
            java.lang.String r14 = android.util.Log.getStackTraceString(r14)     // Catch:{ all -> 0x0064 }
            r2.append(r14)     // Catch:{ all -> 0x0064 }
            java.lang.String r14 = r2.toString()     // Catch:{ all -> 0x0064 }
            com.amap.location.common.log.ALLog.d(r13, r14)     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch:{ Throwable -> 0x0083 }
        L_0x0083:
            return r0
        L_0x0084:
            if (r1 == 0) goto L_0x0089
            r1.close()     // Catch:{ Throwable -> 0x0089 }
        L_0x0089:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(int, int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f5 A[SYNTHETIC, Splitter:B:50:0x00f5] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fa A[Catch:{ Throwable -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104 A[SYNTHETIC, Splitter:B:58:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0109 A[Catch:{ Throwable -> 0x010f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r13 = this;
            r0 = 0
            com.amap.openapi.bx r1 = r13.d     // Catch:{ Throwable -> 0x0008 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0008 }
            goto L_0x0009
        L_0x0008:
            r1 = r0
        L_0x0009:
            if (r1 != 0) goto L_0x000c
            return
        L_0x000c:
            r1.beginTransaction()     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            r6 = 7776000(0x76a700, double:3.8418545E-317)
            long r2 = r2 - r6
            java.lang.String r8 = "AP"
            java.lang.String r9 = "timestamp<?"
            r10 = 1
            java.lang.String[] r11 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r12 = 0
            r11[r12] = r2     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r1.delete(r8, r9, r11)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            long r2 = r2 / r4
            r4 = 0
            long r2 = r2 - r6
            java.lang.String r4 = "CL"
            java.lang.String r5 = "timestamp<?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r6[r12] = r2     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r1.delete(r4, r5, r6)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            java.lang.String r3 = "AP"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            java.lang.String r2 = "id"
            r4[r12] = r2     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "timestamp DESC,frequency DESC LIMIT 200000,-1"
            r2 = r1
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x00da, all -> 0x00d7 }
            if (r11 == 0) goto L_0x0085
            boolean r2 = r11.moveToFirst()     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            if (r2 == 0) goto L_0x0085
        L_0x005f:
            boolean r2 = r11.isAfterLast()     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            if (r2 != 0) goto L_0x0085
            long r2 = r11.getLong(r12)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            java.lang.String r4 = "AP"
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r6[r12] = r2     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r1.delete(r4, r5, r6)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r11.moveToNext()     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            goto L_0x005f
        L_0x007c:
            r2 = move-exception
            r3 = r0
        L_0x007e:
            r0 = r11
            goto L_0x0102
        L_0x0081:
            r2 = move-exception
            r3 = r0
        L_0x0083:
            r0 = r11
            goto L_0x00dc
        L_0x0085:
            java.lang.String r3 = "CL"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            java.lang.String r2 = "id"
            r4[r12] = r2     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "timestamp DESC,frequency DESC LIMIT 200000,-1"
            r2 = r1
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0081, all -> 0x007c }
            if (r2 == 0) goto L_0x00c6
            boolean r0 = r2.moveToFirst()     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            if (r0 == 0) goto L_0x00c6
        L_0x00a1:
            boolean r0 = r2.isAfterLast()     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            if (r0 != 0) goto L_0x00c6
            long r3 = r2.getLong(r12)     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            java.lang.String r0 = "CL"
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            r6[r12] = r3     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            r1.delete(r0, r5, r6)     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            r2.moveToNext()     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            goto L_0x00a1
        L_0x00be:
            r0 = move-exception
            r3 = r2
            r2 = r0
            goto L_0x007e
        L_0x00c2:
            r0 = move-exception
            r3 = r2
            r2 = r0
            goto L_0x0083
        L_0x00c6:
            r1.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00c2, all -> 0x00be }
            if (r11 == 0) goto L_0x00ce
            r11.close()     // Catch:{ Throwable -> 0x00d6 }
        L_0x00ce:
            if (r2 == 0) goto L_0x00d3
            r2.close()     // Catch:{ Throwable -> 0x00d6 }
        L_0x00d3:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00d6 }
        L_0x00d6:
            return
        L_0x00d7:
            r2 = move-exception
            r3 = r0
            goto L_0x0102
        L_0x00da:
            r2 = move-exception
            r3 = r0
        L_0x00dc:
            java.lang.String r4 = "@_18_4_@"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0101 }
            java.lang.String r6 = "@_18_4_11_@"
            r5.<init>(r6)     // Catch:{ all -> 0x0101 }
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch:{ all -> 0x0101 }
            r5.append(r2)     // Catch:{ all -> 0x0101 }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x0101 }
            com.amap.location.common.log.ALLog.d(r4, r2)     // Catch:{ all -> 0x0101 }
            if (r0 == 0) goto L_0x00f8
            r0.close()     // Catch:{ Throwable -> 0x0100 }
        L_0x00f8:
            if (r3 == 0) goto L_0x00fd
            r3.close()     // Catch:{ Throwable -> 0x0100 }
        L_0x00fd:
            r1.endTransaction()     // Catch:{ Throwable -> 0x0100 }
        L_0x0100:
            return
        L_0x0101:
            r2 = move-exception
        L_0x0102:
            if (r0 == 0) goto L_0x0107
            r0.close()     // Catch:{ Throwable -> 0x010f }
        L_0x0107:
            if (r3 == 0) goto L_0x010c
            r3.close()     // Catch:{ Throwable -> 0x010f }
        L_0x010c:
            r1.endTransaction()     // Catch:{ Throwable -> 0x010f }
        L_0x010f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a():void");
    }

    public void a(bs bsVar) {
        bs bsVar2 = bsVar;
        long j = bsVar2.g;
        String str = bsVar2.f;
        if (!TextUtils.isEmpty(str)) {
            ContentValues contentValues = new ContentValues();
            this.f.readLock().lock();
            try {
                SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
                SQLiteDatabase writableDatabase2 = this.e.getWritableDatabase();
                if (bsVar2.f4641a) {
                    if (bsVar2.e > 60) {
                        a(writableDatabase, "CL", j, contentValues);
                    }
                    if (bsVar2.h) {
                        a(contentValues, j, str, 0, 100000);
                        a(writableDatabase2, "CL", contentValues, true);
                    }
                } else {
                    a(contentValues, j, str, 0, 0);
                    a(writableDatabase2, "CL", contentValues, true);
                    a(writableDatabase2, "CL", j, contentValues);
                }
            } catch (Throwable th) {
                this.f.readLock().unlock();
                throw th;
            }
            this.f.readLock().unlock();
        }
    }

    public void a(bu buVar) {
        SQLiteDatabase sQLiteDatabase;
        if (buVar.b != null && buVar.b.size() > 0) {
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase = this.d.getWritableDatabase();
                try {
                    sQLiteDatabase2 = this.e.getWritableDatabase();
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                sQLiteDatabase = null;
            }
            if (sQLiteDatabase != null && sQLiteDatabase2 != null) {
                new StringBuilder();
                new StringBuilder();
                new StringBuilder();
                ContentValues contentValues = new ContentValues();
                this.f.readLock().lock();
                try {
                    sQLiteDatabase.beginTransaction();
                    sQLiteDatabase2.beginTransaction();
                    for (Map.Entry<Long, bt> value : buVar.b.entrySet()) {
                        bt btVar = (bt) value.getValue();
                        if (btVar != null) {
                            if (btVar.d) {
                                if (btVar.g > 60) {
                                    a(sQLiteDatabase, "AP", btVar.f4642a, contentValues);
                                }
                                if (btVar.h) {
                                    a(contentValues, btVar.f4642a, (String) null, btVar.b, 100000);
                                    a(sQLiteDatabase2, "AP", contentValues, true);
                                }
                            } else {
                                a(contentValues, btVar.f4642a, (String) null, btVar.b, 0);
                                a(sQLiteDatabase2, "AP", contentValues, true);
                                a(sQLiteDatabase2, "AP", btVar.f4642a, contentValues);
                            }
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase2.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                } catch (Throwable unused3) {
                }
                sQLiteDatabase2.endTransaction();
                this.f.readLock().unlock();
                return;
            }
            return;
        }
        return;
        this.f.readLock().unlock();
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[ADDED_TO_REGION, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.amap.openapi.bu r20, com.amap.location.common.model.AmapLoc r21) {
        /*
            r19 = this;
            r9 = r19
            r0 = 0
            com.amap.openapi.bx r1 = r9.d     // Catch:{ Throwable -> 0x0012 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0012 }
            com.amap.openapi.cb r2 = r9.e     // Catch:{ Throwable -> 0x0013 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ Throwable -> 0x0013 }
            r10 = r1
            r11 = r2
            goto L_0x0015
        L_0x0012:
            r1 = r0
        L_0x0013:
            r11 = r0
            r10 = r1
        L_0x0015:
            if (r10 == 0) goto L_0x00bc
            if (r11 != 0) goto L_0x001b
            goto L_0x00bc
        L_0x001b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0 = r20
            java.util.HashMap<java.lang.Long, com.amap.openapi.bt> r0 = r0.b
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            android.content.ContentValues r12 = new android.content.ContentValues
            r12.<init>()
            r10.beginTransaction()     // Catch:{ Throwable -> 0x0096 }
            r11.beginTransaction()     // Catch:{ Throwable -> 0x0096 }
        L_0x0037:
            boolean r1 = r0.hasNext()     // Catch:{ Throwable -> 0x0096 }
            if (r1 == 0) goto L_0x0087
            java.lang.Object r1 = r0.next()     // Catch:{ Throwable -> 0x0096 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Throwable -> 0x0096 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Throwable -> 0x0096 }
            com.amap.openapi.bt r1 = (com.amap.openapi.bt) r1     // Catch:{ Throwable -> 0x0096 }
            if (r1 == 0) goto L_0x0037
            boolean r2 = r1.d     // Catch:{ Throwable -> 0x0096 }
            if (r2 == 0) goto L_0x0037
            int r2 = r1.g     // Catch:{ Throwable -> 0x0096 }
            r3 = 60
            if (r2 <= r3) goto L_0x0037
            int r13 = r1.e     // Catch:{ Throwable -> 0x0096 }
            int r14 = r1.f     // Catch:{ Throwable -> 0x0096 }
            double r15 = r21.d()     // Catch:{ Throwable -> 0x0096 }
            double r17 = r21.c()     // Catch:{ Throwable -> 0x0096 }
            double r2 = com.amap.location.security.Core.gd(r13, r14, r15, r17)     // Catch:{ Throwable -> 0x0096 }
            r4 = 4636737291354636288(0x4059000000000000, double:100.0)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0037
            java.lang.String r2 = "AP"
            long r3 = r1.f4642a     // Catch:{ Throwable -> 0x0096 }
            r9.a((java.lang.String) r2, (android.content.ContentValues) r12, (long) r3)     // Catch:{ Throwable -> 0x0096 }
            long r3 = r1.f4642a     // Catch:{ Throwable -> 0x0096 }
            r5 = 0
            long r6 = r1.b     // Catch:{ Throwable -> 0x0096 }
            r8 = 100000(0x186a0, float:1.4013E-40)
            r1 = r19
            r2 = r12
            r1.a(r2, r3, r5, r6, r8)     // Catch:{ Throwable -> 0x0096 }
            java.lang.String r1 = "AP"
            r2 = 0
            r9.a((android.database.sqlite.SQLiteDatabase) r11, (java.lang.String) r1, (android.content.ContentValues) r12, (boolean) r2)     // Catch:{ Throwable -> 0x0096 }
            goto L_0x0037
        L_0x0087:
            r10.setTransactionSuccessful()     // Catch:{ Throwable -> 0x0096 }
            r11.setTransactionSuccessful()     // Catch:{ Throwable -> 0x0096 }
            r10.endTransaction()     // Catch:{ Throwable -> 0x0093 }
            r11.endTransaction()     // Catch:{ Throwable -> 0x0093 }
        L_0x0093:
            return
        L_0x0094:
            r0 = move-exception
            goto L_0x00b5
        L_0x0096:
            r0 = move-exception
            java.lang.String r1 = "@_18_4_@"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            java.lang.String r3 = "@_18_4_8_@"
            r2.<init>(r3)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0094 }
            r2.append(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0094 }
            com.amap.location.common.log.ALLog.d(r1, r0)     // Catch:{ all -> 0x0094 }
            r10.endTransaction()     // Catch:{ Throwable -> 0x00b4 }
            r11.endTransaction()     // Catch:{ Throwable -> 0x00b4 }
        L_0x00b4:
            return
        L_0x00b5:
            r10.endTransaction()     // Catch:{ Throwable -> 0x00bb }
            r11.endTransaction()     // Catch:{ Throwable -> 0x00bb }
        L_0x00bb:
            throw r0
        L_0x00bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(com.amap.openapi.bu, com.amap.location.common.model.AmapLoc):void");
    }

    public void a(ck ckVar) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = this.d.getWritableDatabase();
        } catch (Throwable unused) {
            sQLiteDatabase = null;
        }
        if (sQLiteDatabase != null) {
            ContentValues contentValues = new ContentValues();
            try {
                sQLiteDatabase.beginTransaction();
                for (int i = 0; i < ckVar.b(); i++) {
                    ci b2 = ckVar.b(i);
                    if (b2 != null) {
                        a(contentValues, b2);
                        a(sQLiteDatabase, "AP", contentValues);
                    }
                }
                for (int i2 = 0; i2 < ckVar.a(); i2++) {
                    ci a2 = ckVar.a(i2);
                    if (a2 != null) {
                        a(contentValues, a2);
                        a(sQLiteDatabase, "CL", contentValues);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                try {
                    sQLiteDatabase.endTransaction();
                    return;
                } catch (Throwable unused2) {
                    return;
                }
            } catch (Throwable unused3) {
                return;
            }
        } else {
            return;
        }
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0019 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[ADDED_TO_REGION, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.amap.openapi.ck r17, java.util.List<java.lang.Long> r18, java.util.List<java.lang.String> r19, android.content.Context r20) {
        /*
            r16 = this;
            r7 = r16
            r0 = r17
            r1 = 0
            com.amap.openapi.bx r2 = r7.d     // Catch:{ Throwable -> 0x0014 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ Throwable -> 0x0014 }
            com.amap.openapi.cb r3 = r7.e     // Catch:{ Throwable -> 0x0015 }
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch:{ Throwable -> 0x0015 }
            r8 = r2
            r9 = r3
            goto L_0x0017
        L_0x0014:
            r2 = r1
        L_0x0015:
            r9 = r1
            r8 = r2
        L_0x0017:
            if (r8 == 0) goto L_0x0123
            if (r9 != 0) goto L_0x001d
            goto L_0x0123
        L_0x001d:
            android.content.ContentValues r10 = new android.content.ContentValues
            r10.<init>()
            java.util.concurrent.locks.ReadWriteLock r1 = r7.f
            java.util.concurrent.locks.Lock r1 = r1.writeLock()
            r1.lock()
            r8.beginTransaction()     // Catch:{ Throwable -> 0x00f7 }
            r9.beginTransaction()     // Catch:{ Throwable -> 0x00f7 }
            r1 = r18
            java.util.HashSet r11 = r7.a((java.util.List<java.lang.Long>) r1)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Object r1 = r11.clone()     // Catch:{ Throwable -> 0x00f7 }
            r12 = r1
            java.util.HashSet r12 = (java.util.HashSet) r12     // Catch:{ Throwable -> 0x00f7 }
            r13 = 0
            r14 = 0
        L_0x0040:
            int r1 = r17.b()     // Catch:{ Throwable -> 0x00f7 }
            r15 = 60
            if (r14 >= r1) goto L_0x0082
            com.amap.openapi.ci r1 = r0.b(r14)     // Catch:{ Throwable -> 0x00f7 }
            if (r1 == 0) goto L_0x007f
            long r2 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00f7 }
            r11.remove(r2)     // Catch:{ Throwable -> 0x00f7 }
            r7.a((android.content.ContentValues) r10, (com.amap.openapi.ci) r1)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r2 = "AP"
            r7.a((android.database.sqlite.SQLiteDatabase) r8, (java.lang.String) r2, (android.content.ContentValues) r10)     // Catch:{ Throwable -> 0x00f7 }
            byte r2 = r1.e()     // Catch:{ Throwable -> 0x00f7 }
            if (r2 > r15) goto L_0x007f
            long r2 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00f7 }
            r12.remove(r2)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r3 = "AP"
            long r4 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            r1 = r16
            r2 = r9
            r6 = r10
            r1.b(r2, r3, r4, r6)     // Catch:{ Throwable -> 0x00f7 }
        L_0x007f:
            int r14 = r14 + 1
            goto L_0x0040
        L_0x0082:
            java.lang.String r1 = "AP"
            r7.a((android.database.sqlite.SQLiteDatabase) r8, (java.lang.String) r1, (java.util.HashSet<java.lang.Long>) r11, (android.content.ContentValues) r10)     // Catch:{ Throwable -> 0x00f7 }
            r1 = r19
            java.util.HashSet r11 = r7.b((java.util.List<java.lang.String>) r1)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Object r1 = r11.clone()     // Catch:{ Throwable -> 0x00f7 }
            r14 = r1
            java.util.HashSet r14 = (java.util.HashSet) r14     // Catch:{ Throwable -> 0x00f7 }
        L_0x0094:
            int r1 = r17.a()     // Catch:{ Throwable -> 0x00f7 }
            if (r13 >= r1) goto L_0x00d4
            com.amap.openapi.ci r1 = r0.a((int) r13)     // Catch:{ Throwable -> 0x00f7 }
            if (r1 == 0) goto L_0x00d1
            long r2 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00f7 }
            r11.remove(r2)     // Catch:{ Throwable -> 0x00f7 }
            r7.a((android.content.ContentValues) r10, (com.amap.openapi.ci) r1)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r2 = "CL"
            r7.a((android.database.sqlite.SQLiteDatabase) r8, (java.lang.String) r2, (android.content.ContentValues) r10)     // Catch:{ Throwable -> 0x00f7 }
            byte r2 = r1.e()     // Catch:{ Throwable -> 0x00f7 }
            if (r2 > r15) goto L_0x00d1
            long r2 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x00f7 }
            r14.remove(r2)     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r3 = "CL"
            long r4 = r1.a()     // Catch:{ Throwable -> 0x00f7 }
            r1 = r16
            r2 = r9
            r6 = r10
            r1.b(r2, r3, r4, r6)     // Catch:{ Throwable -> 0x00f7 }
        L_0x00d1:
            int r13 = r13 + 1
            goto L_0x0094
        L_0x00d4:
            java.lang.String r0 = "CL"
            r7.a((android.database.sqlite.SQLiteDatabase) r8, (java.lang.String) r0, (java.util.HashSet<java.lang.Long>) r11, (android.content.ContentValues) r10)     // Catch:{ Throwable -> 0x00f7 }
            r7.a((android.database.sqlite.SQLiteDatabase) r9, (java.util.HashSet<java.lang.Long>) r14, (java.util.HashSet<java.lang.Long>) r12)     // Catch:{ Throwable -> 0x00f7 }
            r16.a()     // Catch:{ Throwable -> 0x00f7 }
            r8.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00f7 }
            r9.setTransactionSuccessful()     // Catch:{ Throwable -> 0x00f7 }
            r8.endTransaction()     // Catch:{ Throwable -> 0x00eb }
        L_0x00e8:
            r9.endTransaction()     // Catch:{ Throwable -> 0x00eb }
        L_0x00eb:
            java.util.concurrent.locks.ReadWriteLock r0 = r7.f
            java.util.concurrent.locks.Lock r0 = r0.writeLock()
            r0.unlock()
            return
        L_0x00f5:
            r0 = move-exception
            goto L_0x0113
        L_0x00f7:
            r0 = move-exception
            java.lang.String r1 = "@_18_4_@"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "@_18_4_10_@"
            r2.<init>(r3)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x00f5 }
            r2.append(r0)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x00f5 }
            com.amap.location.common.log.ALLog.d(r1, r0)     // Catch:{ all -> 0x00f5 }
            r8.endTransaction()     // Catch:{ Throwable -> 0x00eb }
            goto L_0x00e8
        L_0x0113:
            r8.endTransaction()     // Catch:{ Throwable -> 0x0119 }
            r9.endTransaction()     // Catch:{ Throwable -> 0x0119 }
        L_0x0119:
            java.util.concurrent.locks.ReadWriteLock r1 = r7.f
            java.util.concurrent.locks.Lock r1 = r1.writeLock()
            r1.unlock()
            throw r0
        L_0x0123:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(com.amap.openapi.ck, java.util.List, java.util.List, android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x011e A[SYNTHETIC, Splitter:B:42:0x011e] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0124 A[SYNTHETIC, Splitter:B:46:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r18, com.amap.openapi.bu r19) {
        /*
            r17 = this;
            r0 = r19
            r1 = 0
            r2 = r17
            com.amap.openapi.bx r3 = r2.d     // Catch:{ Throwable -> 0x0104 }
            android.database.sqlite.SQLiteDatabase r3 = r3.getReadableDatabase()     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r5 = "AP"
            java.lang.String[] r6 = f4646a     // Catch:{ Throwable -> 0x0104 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r7 = "id IN ("
            r4.<init>(r7)     // Catch:{ Throwable -> 0x0104 }
            r7 = r18
            r4.append(r7)     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r7 = ")"
            r4.append(r7)     // Catch:{ Throwable -> 0x0104 }
            java.lang.String r7 = r4.toString()     // Catch:{ Throwable -> 0x0104 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r3
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x0104 }
            if (r4 == 0) goto L_0x00fb
            boolean r1 = r4.moveToFirst()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            if (r1 == 0) goto L_0x00fb
        L_0x0035:
            boolean r1 = r4.isAfterLast()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r5 = 1
            if (r1 != 0) goto L_0x00e1
            r1 = 0
            long r6 = r4.getLong(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r8 = 5
            long r8 = r4.getLong(r8)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r10 = 7776000(0x76a700, double:3.8418545E-317)
            long r10 = r10 + r8
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r14 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 / r14
            int r16 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r16 >= 0) goto L_0x0068
            java.lang.String r8 = "AP"
            java.lang.String r9 = "id=?"
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r5[r1] = r6     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r3.delete(r8, r9, r5)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
        L_0x0064:
            r4.moveToNext()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            goto L_0x0035
        L_0x0068:
            int r1 = r4.getInt(r5)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r10 = 2
            int r10 = r4.getInt(r10)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r11 = 3
            int r11 = r4.getInt(r11)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r12 = 4
            int r12 = r4.getInt(r12)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.util.HashMap<java.lang.Long, com.amap.openapi.bt> r13 = r0.b     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.Object r6 = r13.get(r6)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            com.amap.openapi.bt r6 = (com.amap.openapi.bt) r6     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            if (r6 != 0) goto L_0x008a
            goto L_0x0064
        L_0x008a:
            r6.d = r5     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r6.g = r12     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r6.e = r1     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r6.f = r10     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7 = 60
            if (r12 <= r7) goto L_0x00d1
            if (r11 <= 0) goto L_0x00d1
            r7 = 2000(0x7d0, float:2.803E-42)
            if (r11 >= r7) goto L_0x00d1
            int r7 = r0.c     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            int r7 = r7 + r5
            r0.c = r7     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.StringBuilder r7 = r0.d     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            long r12 = r6.b     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7.append(r12)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r12 = ";"
            r7.append(r12)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.StringBuilder r7 = r0.e     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7.append(r10)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r10 = ","
            r7.append(r10)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7.append(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r1 = ","
            r7.append(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7.append(r11)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r1 = ","
            r7.append(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            int r1 = r6.c     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            r7.append(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.String r1 = ";"
            r7.append(r1)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
        L_0x00d1:
            r10 = 604800(0x93a80, double:2.98811E-318)
            long r8 = r8 + r10
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            long r10 = r10 / r14
            int r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r1 >= 0) goto L_0x0064
            r6.h = r5     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            goto L_0x0064
        L_0x00e1:
            java.lang.StringBuilder r1 = r0.e     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            int r1 = r1.length()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            if (r1 <= 0) goto L_0x00fb
            java.lang.StringBuilder r1 = r0.e     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            java.lang.StringBuilder r0 = r0.e     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            int r0 = r0 - r5
            r1.deleteCharAt(r0)     // Catch:{ Throwable -> 0x00f8, all -> 0x00f6 }
            goto L_0x00fb
        L_0x00f6:
            r0 = move-exception
            goto L_0x0122
        L_0x00f8:
            r0 = move-exception
            r1 = r4
            goto L_0x0105
        L_0x00fb:
            if (r4 == 0) goto L_0x0121
            r4.close()     // Catch:{ Throwable -> 0x0100 }
        L_0x0100:
            return
        L_0x0101:
            r0 = move-exception
            r4 = r1
            goto L_0x0122
        L_0x0104:
            r0 = move-exception
        L_0x0105:
            java.lang.String r3 = "@_18_4_@"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0101 }
            java.lang.String r5 = "@_18_4_2_@"
            r4.<init>(r5)     // Catch:{ all -> 0x0101 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0101 }
            r4.append(r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0101 }
            com.amap.location.common.log.ALLog.d(r3, r0)     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x0121
            r1.close()     // Catch:{ Throwable -> 0x0121 }
        L_0x0121:
            return
        L_0x0122:
            if (r4 == 0) goto L_0x0127
            r4.close()     // Catch:{ Throwable -> 0x0127 }
        L_0x0127:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.a(java.lang.String, com.amap.openapi.bu):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A[SYNTHETIC, Splitter:B:23:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A[SYNTHETIC, Splitter:B:28:0x008a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> b(int r13, int r14) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.amap.openapi.cb r2 = r12.e     // Catch:{ Throwable -> 0x006a }
            android.database.sqlite.SQLiteDatabase r3 = r2.getReadableDatabase()     // Catch:{ Throwable -> 0x006a }
            java.lang.String r4 = "AP"
            java.lang.String[] r5 = b     // Catch:{ Throwable -> 0x006a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006a }
            java.lang.String r6 = "frequency>="
            r2.<init>(r6)     // Catch:{ Throwable -> 0x006a }
            r2.append(r13)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r13 = " AND time<"
            r2.append(r13)     // Catch:{ Throwable -> 0x006a }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x006a }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            r8 = 86400(0x15180, double:4.26873E-319)
            long r6 = r6 - r8
            r2.append(r6)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r6 = r2.toString()     // Catch:{ Throwable -> 0x006a }
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "frequency DESC"
            java.lang.String r11 = java.lang.String.valueOf(r14)     // Catch:{ Throwable -> 0x006a }
            android.database.Cursor r13 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x006a }
            if (r13 == 0) goto L_0x0062
            boolean r14 = r13.moveToFirst()     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            if (r14 == 0) goto L_0x0062
        L_0x0046:
            boolean r14 = r13.isAfterLast()     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            if (r14 != 0) goto L_0x0062
            r14 = 1
            long r1 = r13.getLong(r14)     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            java.lang.Long r14 = java.lang.Long.valueOf(r1)     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            r0.add(r14)     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            r13.moveToNext()     // Catch:{ Throwable -> 0x005f, all -> 0x005c }
            goto L_0x0046
        L_0x005c:
            r14 = move-exception
            r1 = r13
            goto L_0x0088
        L_0x005f:
            r14 = move-exception
            r1 = r13
            goto L_0x006b
        L_0x0062:
            if (r13 == 0) goto L_0x0087
            r13.close()     // Catch:{ Throwable -> 0x0087 }
            goto L_0x0087
        L_0x0068:
            r14 = move-exception
            goto L_0x0088
        L_0x006a:
            r14 = move-exception
        L_0x006b:
            java.lang.String r13 = "@_18_4_@"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            java.lang.String r3 = "@_18_4_4_@"
            r2.<init>(r3)     // Catch:{ all -> 0x0068 }
            java.lang.String r14 = android.util.Log.getStackTraceString(r14)     // Catch:{ all -> 0x0068 }
            r2.append(r14)     // Catch:{ all -> 0x0068 }
            java.lang.String r14 = r2.toString()     // Catch:{ all -> 0x0068 }
            com.amap.location.common.log.ALLog.d(r13, r14)     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x0087
            r1.close()     // Catch:{ Throwable -> 0x0087 }
        L_0x0087:
            return r0
        L_0x0088:
            if (r1 == 0) goto L_0x008d
            r1.close()     // Catch:{ Throwable -> 0x008d }
        L_0x008d:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.b(int, int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c0 A[SYNTHETIC, Splitter:B:49:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c5 A[Catch:{ Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d1 A[SYNTHETIC, Splitter:B:58:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d6 A[Catch:{ Throwable -> 0x00dc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r14 = this;
            r0 = 0
            com.amap.openapi.cb r1 = r14.e     // Catch:{ Throwable -> 0x0008 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ Throwable -> 0x0008 }
            goto L_0x0009
        L_0x0008:
            r1 = r0
        L_0x0009:
            if (r1 != 0) goto L_0x000c
            return
        L_0x000c:
            r1.beginTransaction()     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            java.lang.String r3 = "AP"
            r10 = 1
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            java.lang.String r2 = "id"
            r11 = 0
            r4[r11] = r2     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "frequency DESC LIMIT 10000,-1"
            r2 = r1
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            if (r12 == 0) goto L_0x0050
            boolean r2 = r12.moveToFirst()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            if (r2 == 0) goto L_0x0050
        L_0x002c:
            boolean r2 = r12.isAfterLast()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            if (r2 != 0) goto L_0x0050
            long r2 = r12.getLong(r11)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            java.lang.String r4 = "AP"
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r6[r11] = r2     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r1.delete(r4, r5, r6)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r12.moveToNext()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            goto L_0x002c
        L_0x0049:
            r2 = move-exception
            goto L_0x00cf
        L_0x004c:
            r2 = move-exception
            r3 = r0
        L_0x004e:
            r0 = r12
            goto L_0x00a7
        L_0x0050:
            java.lang.String r3 = "CL"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            java.lang.String r2 = "id"
            r4[r11] = r2     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "frequency DESC LIMIT 10000,-1"
            r2 = r1
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            if (r2 == 0) goto L_0x0091
            boolean r0 = r2.moveToFirst()     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            if (r0 == 0) goto L_0x0091
        L_0x006b:
            boolean r0 = r2.isAfterLast()     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            if (r0 != 0) goto L_0x0091
            long r3 = r2.getLong(r11)     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            java.lang.String r0 = "CL"
            java.lang.String r5 = "id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r6[r11] = r3     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r1.delete(r0, r5, r6)     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            r2.moveToNext()     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            goto L_0x006b
        L_0x0088:
            r0 = move-exception
            r13 = r2
            r2 = r0
            r0 = r13
            goto L_0x00cf
        L_0x008d:
            r0 = move-exception
            r3 = r2
            r2 = r0
            goto L_0x004e
        L_0x0091:
            r1.setTransactionSuccessful()     // Catch:{ Throwable -> 0x008d, all -> 0x0088 }
            if (r12 == 0) goto L_0x0099
            r12.close()     // Catch:{ Throwable -> 0x00a1 }
        L_0x0099:
            if (r2 == 0) goto L_0x009e
            r2.close()     // Catch:{ Throwable -> 0x00a1 }
        L_0x009e:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00a1 }
        L_0x00a1:
            return
        L_0x00a2:
            r2 = move-exception
            r12 = r0
            goto L_0x00cf
        L_0x00a5:
            r2 = move-exception
            r3 = r0
        L_0x00a7:
            java.lang.String r4 = "@_18_4_@"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = "@_18_4_12_@"
            r5.<init>(r6)     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch:{ all -> 0x00cc }
            r5.append(r2)     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00cc }
            com.amap.location.common.log.ALLog.d(r4, r2)     // Catch:{ all -> 0x00cc }
            if (r0 == 0) goto L_0x00c3
            r0.close()     // Catch:{ Throwable -> 0x00cb }
        L_0x00c3:
            if (r3 == 0) goto L_0x00c8
            r3.close()     // Catch:{ Throwable -> 0x00cb }
        L_0x00c8:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00cb }
        L_0x00cb:
            return
        L_0x00cc:
            r2 = move-exception
            r12 = r0
            r0 = r3
        L_0x00cf:
            if (r12 == 0) goto L_0x00d4
            r12.close()     // Catch:{ Throwable -> 0x00dc }
        L_0x00d4:
            if (r0 == 0) goto L_0x00d9
            r0.close()     // Catch:{ Throwable -> 0x00dc }
        L_0x00d9:
            r1.endTransaction()     // Catch:{ Throwable -> 0x00dc }
        L_0x00dc:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.by.b():void");
    }

    public void b(bs bsVar) {
        long j = bsVar.g;
        String str = bsVar.f;
        ContentValues contentValues = new ContentValues();
        try {
            a("CL", contentValues, j);
            a(contentValues, j, str, 0, 100000);
            a(this.e.getWritableDatabase(), "CL", contentValues, false);
        } catch (Throwable th) {
            ALLog.d("@_18_4_@", "@_18_4_7_@" + Log.getStackTraceString(th));
        }
    }

    public void c() {
        try {
            SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
            SQLiteDatabase writableDatabase2 = this.e.getWritableDatabase();
            writableDatabase.delete("CL", (String) null, (String[]) null);
            writableDatabase.delete("AP", (String) null, (String[]) null);
            writableDatabase2.delete("CL", (String) null, (String[]) null);
            writableDatabase2.delete("AP", (String) null, (String[]) null);
        } catch (Throwable unused) {
        }
    }
}
