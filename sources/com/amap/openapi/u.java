package com.amap.openapi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.util.e;
import java.util.Locale;

public class u {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f4745a = {"id", "type", "data", "size"};
    private a b;
    private long c = a(true);
    private long d = a(false);

    private static class a extends SQLiteOpenHelper {
        a(Context context, String str, int i) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS base (id INTEGER PRIMARY KEY AUTOINCREMENT , type SMALLINT, data BLOB, size INTEGER, time INTEGER);");
            } catch (Exception unused) {
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS base");
                onCreate(sQLiteDatabase);
            } catch (Exception e) {
                ALLog.a("DbManager", "", (Throwable) e, true);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS base");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS byte_base");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS extend");
                onCreate(sQLiteDatabase);
            } catch (Exception e) {
                ALLog.a("DbManager", "", (Throwable) e, true);
            }
        }
    }

    public u(Context context) {
        this.b = new a(context, "aloccoll.db", 4);
    }

    private long a(boolean z) {
        Cursor cursor;
        SystemClock.elapsedRealtime();
        long j = 0;
        Cursor cursor2 = null;
        try {
            cursor = this.b.getReadableDatabase().query("base", new String[]{"SUM(size)"}, z ? "type=?" : "type!=?", new String[]{"0"}, (String) null, (String) null, (String) null);
            try {
                if (cursor.moveToFirst()) {
                    j = cursor.getLong(0);
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                cursor2 = cursor;
                th = th;
                e.a(cursor2);
                throw th;
            }
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            e.a(cursor2);
            throw th;
        }
        e.a(cursor);
        return j;
    }

    private long a(boolean z, long j) throws Exception {
        Cursor cursor;
        String str = z ? "type=0" : "type!=0";
        long j2 = -2147483648L;
        long j3 = 0;
        while (true) {
            if (j3 >= j) {
                break;
            }
            try {
                Cursor query = this.b.getReadableDatabase().query("base", new String[]{"id", "type", "size"}, "id>? AND " + str, new String[]{String.valueOf(j2)}, (String) null, (String) null, "id ASC", "100");
                try {
                    boolean moveToNext = query.moveToNext();
                    if (!moveToNext) {
                        e.a(query);
                        break;
                    }
                    while (moveToNext) {
                        j2 = query.getLong(0);
                        j3 += (long) query.getInt(2);
                        if (j3 >= j) {
                            break;
                        }
                        moveToNext = query.moveToNext();
                    }
                    e.a(query);
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    e.a(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                e.a(cursor);
                throw th;
            }
        }
        if (j3 > 0) {
            if (this.b.getWritableDatabase().delete("base", "id<=? AND " + str, new String[]{String.valueOf(j2)}) > 0) {
                if (z) {
                    this.c -= j3;
                    if (this.c < 0) {
                        this.c = 0;
                    }
                } else {
                    this.d -= j3;
                    if (this.d < 0) {
                        this.d = 0;
                    }
                }
            }
        }
        return j3;
    }

    public au a(boolean z, int i, long j) {
        Cursor cursor;
        String str;
        String format;
        au auVar = new au();
        au auVar2 = null;
        try {
            cursor = this.b.getReadableDatabase().query("base", f4745a, z ? "type=0" : "type!=0", (String[]) null, (String) null, (String) null, "id ASC", String.valueOf(i));
            int i2 = 0;
            while (cursor.moveToNext()) {
                try {
                    int i3 = cursor.getInt(3);
                    if (((long) auVar.c) >= j || ((long) (auVar.c + i3)) > j || i2 >= i) {
                        break;
                    }
                    auVar.f4612a = cursor.getLong(0);
                    auVar.b.add(new s(cursor.getInt(1), cursor.getBlob(2)));
                    auVar.c += i3;
                    i2++;
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                    e.a(cursor);
                    throw th;
                }
            }
            if (z) {
                str = "@_3_2_@";
                format = String.format(Locale.getDefault(), "@_3_2_1_@%d，%d, %d", new Object[]{Long.valueOf(auVar.f4612a), Integer.valueOf(i2), Integer.valueOf(auVar.c)});
            } else {
                str = "@_3_2_@";
                format = String.format(Locale.getDefault(), "@_3_2_2_@%d，%d, %d", new Object[]{Long.valueOf(auVar.f4612a), Integer.valueOf(i2), Integer.valueOf(auVar.c)});
            }
            ALLog.d(str, format);
            if (auVar.c != 0) {
                auVar2 = auVar;
            }
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            e.a(cursor);
            throw th;
        }
        e.a(cursor);
        return auVar2;
    }

    public void a() {
        try {
            if (this.b != null) {
                this.b.close();
                this.b = null;
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cb A[SYNTHETIC, Splitter:B:30:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d0 A[SYNTHETIC, Splitter:B:34:0x00d0] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d7 A[SYNTHETIC, Splitter:B:42:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00dc A[SYNTHETIC, Splitter:B:46:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.List<com.amap.openapi.s> r17) {
        /*
            r16 = this;
            r1 = r16
            r2 = 0
            com.amap.openapi.u$a r0 = r1.b     // Catch:{ Exception -> 0x00d4, all -> 0x00c6 }
            android.database.sqlite.SQLiteDatabase r3 = r0.getWritableDatabase()     // Catch:{ Exception -> 0x00d4, all -> 0x00c6 }
            r3.beginTransaction()     // Catch:{ Exception -> 0x00d5, all -> 0x00c3 }
            java.lang.String r0 = "INSERT INTO base(type,data,size,time) VALUES(?,?,?,?)"
            android.database.sqlite.SQLiteStatement r4 = r3.compileStatement(r0)     // Catch:{ Exception -> 0x00d5, all -> 0x00c3 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.util.Iterator r0 = r17.iterator()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r7 = 0
            r9 = r7
            r11 = 0
        L_0x001e:
            boolean r12 = r0.hasNext()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r2 = 1
            if (r12 == 0) goto L_0x0057
            java.lang.Object r12 = r0.next()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            com.amap.openapi.s r12 = (com.amap.openapi.s) r12     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            int r13 = r12.b()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            long r14 = (long) r13     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r4.bindLong(r2, r14)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            byte[] r2 = r12.c()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r13 = 2
            r4.bindBlob(r13, r2)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            long r13 = r12.a()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r2 = 3
            r4.bindLong(r2, r13)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r2 = 4
            r4.bindLong(r2, r5)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r4.executeInsert()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            int r2 = r12.b()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            if (r2 != 0) goto L_0x0054
            long r7 = r7 + r13
            int r11 = r11 + 1
            goto L_0x001e
        L_0x0054:
            r2 = 0
            long r9 = r9 + r13
            goto L_0x001e
        L_0x0057:
            r3.setTransactionSuccessful()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            long r12 = r1.c     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r0 = 0
            long r12 = r12 + r7
            r1.c = r12     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            long r12 = r1.d     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r0 = 0
            long r12 = r12 + r9
            r1.d = r12     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.String r0 = "@_3_2_@"
            java.util.Locale r12 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.String r14 = "@_3_2_3_@"
            r13.<init>(r14)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r13.append(r5)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.String r5 = ";@_3_2_4_@%d，%d;@_3_2_5_@%d，%d"
            r13.append(r5)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.String r5 = r13.toString()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r6 = 5
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            int r13 = r17.size()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r14 = 0
            r6[r14] = r13     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r6[r2] = r7     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r7 = 2
            r6[r7] = r2     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.Long r2 = java.lang.Long.valueOf(r9)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r7 = 3
            r6[r7] = r2     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            int r2 = r17.size()     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            int r2 = r2 - r11
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            r7 = 4
            r6[r7] = r2     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            java.lang.String r2 = java.lang.String.format(r12, r5, r6)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            com.amap.location.common.log.ALLog.d(r0, r2)     // Catch:{ Exception -> 0x00c1, all -> 0x00bf }
            if (r4 == 0) goto L_0x00b9
            r4.close()     // Catch:{ Throwable -> 0x00b9 }
        L_0x00b9:
            if (r3 == 0) goto L_0x00df
            r3.endTransaction()     // Catch:{ Exception -> 0x00be }
        L_0x00be:
            return
        L_0x00bf:
            r0 = move-exception
            goto L_0x00c9
        L_0x00c1:
            r2 = r4
            goto L_0x00d5
        L_0x00c3:
            r0 = move-exception
            r4 = r2
            goto L_0x00c9
        L_0x00c6:
            r0 = move-exception
            r3 = r2
            r4 = r3
        L_0x00c9:
            if (r4 == 0) goto L_0x00ce
            r4.close()     // Catch:{ Throwable -> 0x00ce }
        L_0x00ce:
            if (r3 == 0) goto L_0x00d3
            r3.endTransaction()     // Catch:{ Exception -> 0x00d3 }
        L_0x00d3:
            throw r0
        L_0x00d4:
            r3 = r2
        L_0x00d5:
            if (r2 == 0) goto L_0x00da
            r2.close()     // Catch:{ Throwable -> 0x00da }
        L_0x00da:
            if (r3 == 0) goto L_0x00df
            r3.endTransaction()     // Catch:{ Exception -> 0x00df }
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.u.a(java.util.List):void");
    }

    public boolean a(long j) {
        if (j < 4611686018427387903L && this.c + this.d + j < 10485760) {
            return true;
        }
        long max = Math.max(204800, j);
        try {
            long a2 = a(false, max);
            if (a2 < max) {
                a(true, max - a2);
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean a(au auVar) {
        String str;
        String format;
        boolean z = true;
        if (!(auVar == null || auVar.b.size() == 0)) {
            try {
                boolean z2 = auVar.b.get(0).b() == 0;
                int delete = this.b.getWritableDatabase().delete("base", z2 ? "type=0 AND id<=?" : "type!=0 AND id<=?", new String[]{String.valueOf(auVar.f4612a)});
                if (delete > 0) {
                    if (z2) {
                        this.c -= (long) auVar.c;
                        if (this.c < 0) {
                            this.c = 0;
                        }
                    } else {
                        this.d -= (long) auVar.c;
                        if (this.d < 0) {
                            this.d = 0;
                        }
                    }
                }
                if (z2) {
                    str = "@_3_2_@";
                    try {
                        format = String.format(Locale.getDefault(), "@_3_2_6_@%d，%d，%d", new Object[]{Long.valueOf(auVar.f4612a), Integer.valueOf(delete), Integer.valueOf(auVar.c)});
                    } catch (Exception e) {
                        e = e;
                        ALLog.d("@_3_2_@", "@_3_2_8_@" + Log.getStackTraceString(e));
                        return z;
                    }
                } else {
                    str = "@_3_2_@";
                    format = String.format(Locale.getDefault(), "@_3_2_7_@%d，%d，%d", new Object[]{Long.valueOf(auVar.f4612a), Integer.valueOf(delete), Integer.valueOf(auVar.c)});
                }
                ALLog.d(str, format);
            } catch (Exception e2) {
                e = e2;
                z = false;
                ALLog.d("@_3_2_@", "@_3_2_8_@" + Log.getStackTraceString(e));
                return z;
            }
        }
        return z;
    }

    public int b() {
        return (int) this.c;
    }

    public int c() {
        return (int) this.d;
    }
}
