package com.mi.mistatistic.sdk.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.mi.mistatistic.sdk.data.CustomDataEvent;

public class EventDAO {

    /* renamed from: a  reason: collision with root package name */
    public static String f7329a = "";
    public static boolean b = false;
    private static final String c = "EventDAO";
    private static final int d = 1000;
    private static final long e = 259200000;
    private static MiStatDatabaseHelper f;

    public static void a() {
        f = new MiStatDatabaseHelper(ApplicationContextHolder.a());
    }

    public static void a(CustomDataEvent customDataEvent) {
        MiStatDatabaseHelper miStatDatabaseHelper;
        long b2 = TimeUtil.a().b();
        customDataEvent.a(SessionManager.a().b());
        customDataEvent.a(b2);
        if (customDataEvent.f()) {
            SessionManager.a().c();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", customDataEvent.a());
        contentValues.put("data", customDataEvent.b());
        contentValues.put("ts", Long.valueOf(b2));
        synchronized (f) {
            try {
                f.a("mistat_data", contentValues);
                miStatDatabaseHelper = f;
            } catch (SQLiteException e2) {
                try {
                    Logger.a(c, "Error to createSession ", (Throwable) e2);
                    miStatDatabaseHelper = f;
                } catch (Throwable th) {
                    f.close();
                    throw th;
                }
            }
            miStatDatabaseHelper.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A[SYNTHETIC, Splitter:B:26:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b A[Catch:{ all -> 0x006f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.mi.mistatistic.sdk.data.CustomDataEvent> b() {
        /*
            r14 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.mi.mistatistic.sdk.controller.TimeUtil r1 = com.mi.mistatistic.sdk.controller.TimeUtil.a()
            long r1 = r1.b()
            com.mi.mistatistic.sdk.controller.MiStatDatabaseHelper r3 = f
            monitor-enter(r3)
            r4 = 0
            com.mi.mistatistic.sdk.controller.MiStatDatabaseHelper r5 = f     // Catch:{ SQLiteException -> 0x0057 }
            android.database.sqlite.SQLiteDatabase r6 = r5.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0057 }
            java.lang.String r7 = "mistat_data"
            r8 = 0
            java.lang.String r9 = "ts  <= ? "
            r5 = 1
            java.lang.String[] r10 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0057 }
            r5 = 0
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ SQLiteException -> 0x0057 }
            r10[r5] = r1     // Catch:{ SQLiteException -> 0x0057 }
            r11 = 0
            r12 = 0
            r13 = 0
            android.database.Cursor r1 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x0057 }
            if (r1 == 0) goto L_0x004a
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0047, all -> 0x0044 }
            if (r2 == 0) goto L_0x004a
        L_0x0036:
            com.mi.mistatistic.sdk.data.CustomDataEvent r2 = a((android.database.Cursor) r1)     // Catch:{ SQLiteException -> 0x0047, all -> 0x0044 }
            r0.add(r2)     // Catch:{ SQLiteException -> 0x0047, all -> 0x0044 }
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0047, all -> 0x0044 }
            if (r2 != 0) goto L_0x0036
            goto L_0x004a
        L_0x0044:
            r0 = move-exception
            r4 = r1
            goto L_0x0069
        L_0x0047:
            r2 = move-exception
            r4 = r1
            goto L_0x0058
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ all -> 0x006f }
        L_0x004f:
            com.mi.mistatistic.sdk.controller.MiStatDatabaseHelper r1 = f     // Catch:{ all -> 0x006f }
        L_0x0051:
            r1.close()     // Catch:{ all -> 0x006f }
            goto L_0x0067
        L_0x0055:
            r0 = move-exception
            goto L_0x0069
        L_0x0057:
            r2 = move-exception
        L_0x0058:
            java.lang.String r1 = "EventDAO"
            java.lang.String r5 = "queryCustomEvent exception"
            com.mi.mistatistic.sdk.controller.Logger.a((java.lang.String) r1, (java.lang.String) r5, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ all -> 0x006f }
        L_0x0064:
            com.mi.mistatistic.sdk.controller.MiStatDatabaseHelper r1 = f     // Catch:{ all -> 0x006f }
            goto L_0x0051
        L_0x0067:
            monitor-exit(r3)     // Catch:{ all -> 0x006f }
            return r0
        L_0x0069:
            if (r4 == 0) goto L_0x0071
            r4.close()     // Catch:{ all -> 0x006f }
            goto L_0x0071
        L_0x006f:
            r0 = move-exception
            goto L_0x0077
        L_0x0071:
            com.mi.mistatistic.sdk.controller.MiStatDatabaseHelper r1 = f     // Catch:{ all -> 0x006f }
            r1.close()     // Catch:{ all -> 0x006f }
            throw r0     // Catch:{ all -> 0x006f }
        L_0x0077:
            monitor-exit(r3)     // Catch:{ all -> 0x006f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.controller.EventDAO.b():java.util.ArrayList");
    }

    public static CustomDataEvent a(Cursor cursor) {
        CustomDataEvent customDataEvent = new CustomDataEvent();
        long j = cursor.getLong(1);
        String string = cursor.getString(2);
        String string2 = cursor.getString(3);
        customDataEvent.a(j);
        customDataEvent.c(string);
        customDataEvent.b(string2);
        return customDataEvent;
    }

    public void a(long j) {
        MiStatDatabaseHelper miStatDatabaseHelper;
        synchronized (f) {
            try {
                Logger.c(c, "deleteEventsByTS, ts:%d", Long.valueOf(j));
                f.getWritableDatabase().delete("mistat_data", "ts <=?", new String[]{String.valueOf(j)});
                miStatDatabaseHelper = f;
            } catch (SQLiteException e2) {
                try {
                    Logger.a(c, "Error while deleting event by ts from DB", (Throwable) e2);
                    miStatDatabaseHelper = f;
                } catch (Throwable th) {
                    f.close();
                    throw th;
                }
            }
            miStatDatabaseHelper.close();
        }
    }
}
