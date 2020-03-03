package com.xiaomi.mistatistic.sdk.controller;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.a;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import com.xiaomi.stat.a.l;
import java.util.ArrayList;
import java.util.List;

public class f {

    /* renamed from: a  reason: collision with root package name */
    public static String f12024a = "";
    public static boolean b = false;
    private static i c;
    /* access modifiers changed from: private */
    public a d = null;
    /* access modifiers changed from: private */
    public boolean e = false;
    private ServiceConnection f = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = f.this.e = true;
            a unused2 = f.this.d = a.C0085a.a(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = f.this.e = false;
            a unused2 = f.this.d = null;
        }
    };

    public static void a() {
        c = new i(c.a());
    }

    private void f() {
        if (!this.e) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                c.a().startService(intent);
                if (this.d != null) {
                    h.b("unbind service before bind it again!");
                    c.a().unbindService(this.f);
                }
                c.a().bindService(intent, this.f, 1);
            } catch (Exception e2) {
                h.a("ensureServiceBinded", (Throwable) e2);
            }
        }
    }

    public StatEventPojo a(String str, String str2) {
        if (!b) {
            return b(str, str2);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.e) {
                    if (this.d != null) {
                        StatEventPojo a2 = this.d.a(str, str2);
                        h.b("process query, result is: " + a2);
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return null;
        } catch (Exception e2) {
            h.a("queryCustomEvent", (Throwable) e2);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.xiaomi.mistatistic.sdk.data.StatEventPojo} */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005d, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:13:0x003e, B:21:0x004f] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054 A[SYNTHETIC, Splitter:B:24:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0060 A[Catch:{ all -> 0x005c, all -> 0x0064 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.mistatistic.sdk.data.StatEventPojo b(java.lang.String r17, java.lang.String r18) {
        /*
            r16 = this;
            java.lang.String r2 = "EventDAO"
            java.lang.String r3 = "queryCustomEvent key: %s, category: %s"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 0
            r5[r6] = r18
            r7 = 1
            r5[r7] = r17
            com.xiaomi.mistatistic.sdk.controller.h.b((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r5)
            com.xiaomi.mistatistic.sdk.controller.i r2 = c
            monitor-enter(r2)
            r3 = 0
            com.xiaomi.mistatistic.sdk.controller.i r5 = c     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            android.database.sqlite.SQLiteDatabase r8 = r5.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            java.lang.String r9 = "mistat_event"
            r10 = 0
            java.lang.String r11 = "category=? AND key=?"
            java.lang.String[] r12 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            r12[r6] = r17     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            r12[r7] = r18     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            r13 = 0
            r14 = 0
            r15 = 0
            android.database.Cursor r1 = r8.query(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ SQLiteException -> 0x0049, all -> 0x0047 }
            if (r1 == 0) goto L_0x003c
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x003a }
            if (r0 == 0) goto L_0x003c
            com.xiaomi.mistatistic.sdk.data.StatEventPojo r0 = a((android.database.Cursor) r1)     // Catch:{ SQLiteException -> 0x003a }
            r3 = r0
            goto L_0x003c
        L_0x003a:
            r0 = move-exception
            goto L_0x004b
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ all -> 0x0064 }
        L_0x0041:
            com.xiaomi.mistatistic.sdk.controller.i r0 = c     // Catch:{ all -> 0x0064 }
        L_0x0043:
            r0.close()     // Catch:{ all -> 0x0064 }
            goto L_0x005a
        L_0x0047:
            r0 = move-exception
            goto L_0x005e
        L_0x0049:
            r0 = move-exception
            r1 = r3
        L_0x004b:
            java.lang.String r4 = "EventDAO"
            java.lang.String r5 = "queryCustomEvent exception"
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r4, (java.lang.String) r5, (java.lang.Throwable) r0)     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch:{ all -> 0x0064 }
        L_0x0057:
            com.xiaomi.mistatistic.sdk.controller.i r0 = c     // Catch:{ all -> 0x0064 }
            goto L_0x0043
        L_0x005a:
            monitor-exit(r2)     // Catch:{ all -> 0x0064 }
            return r3
        L_0x005c:
            r0 = move-exception
            r3 = r1
        L_0x005e:
            if (r3 == 0) goto L_0x0066
            r3.close()     // Catch:{ all -> 0x0064 }
            goto L_0x0066
        L_0x0064:
            r0 = move-exception
            goto L_0x006c
        L_0x0066:
            com.xiaomi.mistatistic.sdk.controller.i r1 = c     // Catch:{ all -> 0x0064 }
            r1.close()     // Catch:{ all -> 0x0064 }
            throw r0     // Catch:{ all -> 0x0064 }
        L_0x006c:
            monitor-exit(r2)     // Catch:{ all -> 0x0064 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.f.b(java.lang.String, java.lang.String):com.xiaomi.mistatistic.sdk.data.StatEventPojo");
    }

    public void a(StatEventPojo statEventPojo) {
        if (b) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                intent.putExtra("type", 1);
                intent.putExtra(BaseService.STAT_EVENT_POJO, statEventPojo);
                c.a().startService(intent);
            } catch (Exception e2) {
                h.a("insertNewEvent", (Throwable) e2);
            }
        } else {
            b(statEventPojo);
        }
    }

    public void b(StatEventPojo statEventPojo) {
        i iVar;
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", statEventPojo.f12065a);
        contentValues.put("key", TextUtils.isEmpty(statEventPojo.c) ? "" : statEventPojo.c);
        contentValues.put("ts", Long.valueOf(statEventPojo.b));
        contentValues.put("type", TextUtils.isEmpty(statEventPojo.d) ? "" : statEventPojo.d);
        contentValues.put("value", TextUtils.isEmpty(statEventPojo.e) ? "" : statEventPojo.e);
        contentValues.put("extra", TextUtils.isEmpty(statEventPojo.f) ? "" : statEventPojo.f);
        synchronized (c) {
            try {
                c.getWritableDatabase().insert("mistat_event", "", contentValues);
                iVar = c;
            } catch (SQLiteException e2) {
                try {
                    h.a("EventDAO", "Error to insert data into DB, key=" + statEventPojo.c, (Throwable) e2);
                    iVar = c;
                } catch (Throwable th) {
                    c.close();
                    throw th;
                }
            }
            iVar.close();
        }
    }

    public void a(String str, String str2, String str3) {
        if (b) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                intent.putExtra("type", 2);
                intent.putExtra("key", str);
                intent.putExtra("category", str2);
                intent.putExtra(BaseService.NEW_VALUE, str3);
                c.a().startService(intent);
            } catch (Exception e2) {
                h.a("updateEventByKeyAndCategory", (Throwable) e2);
            }
        } else {
            b(str, str2, str3);
        }
    }

    public void b(String str, String str2, String str3) {
        i iVar;
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", str3);
        synchronized (c) {
            try {
                c.getWritableDatabase().update("mistat_event", contentValues, "category=? AND key=?", new String[]{str2, str});
                iVar = c;
            } catch (SQLiteException e2) {
                try {
                    h.a("EventDAO", "Error to update data from DB, key=" + str, (Throwable) e2);
                    iVar = c;
                } catch (Throwable th) {
                    c.close();
                    throw th;
                }
            }
            iVar.close();
        }
    }

    public List<StatEventPojo> a(long j) {
        if (!b) {
            return b(j);
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.e) {
                    if (this.d != null) {
                        List<StatEventPojo> a2 = this.d.a(j);
                        StringBuilder sb = new StringBuilder();
                        sb.append("process getAll, result size is :");
                        sb.append(a2 == null ? 0 : a2.size());
                        h.b(sb.toString());
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return new ArrayList();
        } catch (Exception e2) {
            h.a("getAllEventOrderByTimestampDescend", (Throwable) e2);
            return new ArrayList();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075 A[SYNTHETIC, Splitter:B:22:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.xiaomi.mistatistic.sdk.data.StatEventPojo> b(long r17) {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.xiaomi.mistatistic.sdk.controller.i r2 = c
            monitor-enter(r2)
            com.xiaomi.mistatistic.sdk.controller.i r0 = c     // Catch:{ all -> 0x00c7 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getReadableDatabase()     // Catch:{ all -> 0x00c7 }
            if (r0 != 0) goto L_0x0012
            monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
            return r1
        L_0x0012:
            r12 = 2
            r13 = 0
            r14 = 1
            r15 = 0
            java.lang.String r4 = "mistat_event"
            r5 = 0
            java.lang.String r6 = "ts<?"
            java.lang.String[] r7 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0093 }
            java.lang.String r3 = java.lang.String.valueOf(r17)     // Catch:{ SQLiteException -> 0x0093 }
            r7[r13] = r3     // Catch:{ SQLiteException -> 0x0093 }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "ts DESC"
            r3 = 500(0x1f4, float:7.0E-43)
            java.lang.String r11 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x0093 }
            r3 = r0
            android.database.Cursor r11 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0093 }
            if (r11 == 0) goto L_0x0072
            boolean r3 = r11.moveToLast()     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            if (r3 == 0) goto L_0x0072
            java.lang.String r3 = "ts"
            int r3 = r11.getColumnIndex(r3)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            long r3 = r11.getLong(r3)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            r11.close()     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            java.lang.String r5 = "mistat_event"
            r6 = 0
            java.lang.String r7 = "ts<? AND ts>=?"
            java.lang.String[] r8 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            java.lang.String r9 = java.lang.String.valueOf(r17)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            r8[r13] = r9     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            r8[r14] = r3     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            r9 = 0
            r10 = 0
            java.lang.String r15 = "ts DESC"
            r3 = r0
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r15
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006c }
            r15 = r0
            goto L_0x0073
        L_0x006c:
            r0 = move-exception
            r15 = r11
            goto L_0x00be
        L_0x006f:
            r0 = move-exception
            r15 = r11
            goto L_0x0094
        L_0x0072:
            r15 = r11
        L_0x0073:
            if (r15 == 0) goto L_0x0088
            boolean r0 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0093 }
            if (r0 == 0) goto L_0x0088
        L_0x007b:
            com.xiaomi.mistatistic.sdk.data.StatEventPojo r0 = a((android.database.Cursor) r15)     // Catch:{ SQLiteException -> 0x0093 }
            r1.add(r0)     // Catch:{ SQLiteException -> 0x0093 }
            boolean r0 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0093 }
            if (r0 != 0) goto L_0x007b
        L_0x0088:
            r15.close()     // Catch:{ all -> 0x00c7 }
            com.xiaomi.mistatistic.sdk.controller.i r0 = c     // Catch:{ all -> 0x00c7 }
        L_0x008d:
            r0.close()     // Catch:{ all -> 0x00c7 }
            goto L_0x00a1
        L_0x0091:
            r0 = move-exception
            goto L_0x00be
        L_0x0093:
            r0 = move-exception
        L_0x0094:
            java.lang.String r3 = "EventDAO"
            java.lang.String r4 = "Error while reading data from DB"
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0091 }
            r15.close()     // Catch:{ all -> 0x00c7 }
            com.xiaomi.mistatistic.sdk.controller.i r0 = c     // Catch:{ all -> 0x00c7 }
            goto L_0x008d
        L_0x00a1:
            if (r15 == 0) goto L_0x00bc
            java.lang.String r0 = "EventDAO"
            java.lang.String r3 = "get %d DB events by timestamp %d"
            java.lang.Object[] r4 = new java.lang.Object[r12]     // Catch:{ all -> 0x00c7 }
            int r5 = r15.getCount()     // Catch:{ all -> 0x00c7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00c7 }
            r4[r13] = r5     // Catch:{ all -> 0x00c7 }
            java.lang.Long r5 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x00c7 }
            r4[r14] = r5     // Catch:{ all -> 0x00c7 }
            com.xiaomi.mistatistic.sdk.controller.h.b((java.lang.String) r0, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x00c7 }
        L_0x00bc:
            monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
            return r1
        L_0x00be:
            r15.close()     // Catch:{ all -> 0x00c7 }
            com.xiaomi.mistatistic.sdk.controller.i r1 = c     // Catch:{ all -> 0x00c7 }
            r1.close()     // Catch:{ all -> 0x00c7 }
            throw r0     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.f.b(long):java.util.List");
    }

    public void b() {
        if (b) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                intent.putExtra("type", 3);
                c.a().startService(intent);
            } catch (Exception e2) {
                h.a("deleteOldEvents", (Throwable) e2);
            }
        } else {
            c();
        }
    }

    public void c() {
        i iVar;
        long currentTimeMillis = System.currentTimeMillis() - 259200000;
        synchronized (c) {
            try {
                int delete = c.getWritableDatabase().delete("mistat_event", "ts<=? and category <> ?", new String[]{String.valueOf(currentTimeMillis), l.a.h});
                if (delete > 0) {
                    MiStatInterface.a("quality_monitor", "delete_old_events", (long) delete);
                }
                iVar = c;
            } catch (SQLiteException e2) {
                try {
                    h.a("EventDAO", "Error while deleting out-of-date data from DB", (Throwable) e2);
                    iVar = c;
                } catch (Throwable th) {
                    c.close();
                    throw th;
                }
            }
            iVar.close();
        }
    }

    public int d() {
        if (!b) {
            return e();
        }
        f();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            do {
                if (this.e) {
                    if (this.d != null) {
                        int a2 = this.d.a();
                        h.b("process getCount , result is:" + a2);
                        return a2;
                    }
                }
            } while (System.currentTimeMillis() - currentTimeMillis <= 1000);
            return 0;
        } catch (Exception e2) {
            h.a("getEventCount", (Throwable) e2);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0054 A[SYNTHETIC, Splitter:B:30:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005e A[Catch:{ SQLiteException -> 0x0038, all -> 0x0035, all -> 0x0062 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e() {
        /*
            r13 = this;
            com.xiaomi.mistatistic.sdk.controller.i r0 = c
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            com.xiaomi.mistatistic.sdk.controller.i r3 = c     // Catch:{ SQLiteException -> 0x004a }
            android.database.sqlite.SQLiteDatabase r4 = r3.getReadableDatabase()     // Catch:{ SQLiteException -> 0x004a }
            java.lang.String r5 = "mistat_event"
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x004a }
            java.lang.String r3 = "count(*)"
            r6[r1] = r3     // Catch:{ SQLiteException -> 0x004a }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x004a }
            if (r3 == 0) goto L_0x003d
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0038, all -> 0x0035 }
            if (r2 == 0) goto L_0x003d
            int r2 = r3.getInt(r1)     // Catch:{ SQLiteException -> 0x0038, all -> 0x0035 }
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ all -> 0x0062 }
        L_0x002e:
            com.xiaomi.mistatistic.sdk.controller.i r1 = c     // Catch:{ all -> 0x0062 }
            r1.close()     // Catch:{ all -> 0x0062 }
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r2
        L_0x0035:
            r1 = move-exception
            r2 = r3
            goto L_0x005c
        L_0x0038:
            r2 = move-exception
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x004b
        L_0x003d:
            if (r3 == 0) goto L_0x0042
            r3.close()     // Catch:{ all -> 0x0062 }
        L_0x0042:
            com.xiaomi.mistatistic.sdk.controller.i r2 = c     // Catch:{ all -> 0x0062 }
        L_0x0044:
            r2.close()     // Catch:{ all -> 0x0062 }
            goto L_0x005a
        L_0x0048:
            r1 = move-exception
            goto L_0x005c
        L_0x004a:
            r3 = move-exception
        L_0x004b:
            java.lang.String r4 = "EventDAO"
            java.lang.String r5 = "Error while getting count from DB"
            com.xiaomi.mistatistic.sdk.controller.h.a((java.lang.String) r4, (java.lang.String) r5, (java.lang.Throwable) r3)     // Catch:{ all -> 0x0048 }
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ all -> 0x0062 }
        L_0x0057:
            com.xiaomi.mistatistic.sdk.controller.i r2 = c     // Catch:{ all -> 0x0062 }
            goto L_0x0044
        L_0x005a:
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r1
        L_0x005c:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ all -> 0x0062 }
            goto L_0x0064
        L_0x0062:
            r1 = move-exception
            goto L_0x006a
        L_0x0064:
            com.xiaomi.mistatistic.sdk.controller.i r2 = c     // Catch:{ all -> 0x0062 }
            r2.close()     // Catch:{ all -> 0x0062 }
            throw r1     // Catch:{ all -> 0x0062 }
        L_0x006a:
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mistatistic.sdk.controller.f.e():int");
    }

    public static StatEventPojo a(Cursor cursor) {
        StatEventPojo statEventPojo = new StatEventPojo();
        long j = cursor.getLong(2);
        String string = cursor.getString(4);
        String string2 = cursor.getString(5);
        String string3 = cursor.getString(1);
        String string4 = cursor.getString(3);
        String string5 = cursor.getString(6);
        statEventPojo.f12065a = string3;
        statEventPojo.c = string4;
        statEventPojo.e = string;
        statEventPojo.b = j;
        statEventPojo.d = string2;
        statEventPojo.f = string5;
        return statEventPojo;
    }

    public void c(long j) {
        if (b) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                intent.putExtra("type", 4);
                intent.putExtra(BaseService.TIME_STAMP, j);
                c.a().startService(intent);
            } catch (Exception e2) {
                h.a("deleteEventsByTS", (Throwable) e2);
            }
        } else {
            d(j);
        }
    }

    public void d(long j) {
        i iVar;
        synchronized (c) {
            try {
                h.b("EventDAO", "deleteEventsByTS, ts:%d", Long.valueOf(j));
                c.getWritableDatabase().delete("mistat_event", "ts<=?", new String[]{String.valueOf(j)});
                iVar = c;
            } catch (SQLiteException e2) {
                try {
                    h.a("EventDAO", "Error while deleting event by ts from DB", (Throwable) e2);
                    iVar = c;
                } catch (Throwable th) {
                    c.close();
                    throw th;
                }
            }
            iVar.close();
        }
    }

    public void a(long j, long j2) {
        if (b) {
            try {
                Intent intent = new Intent(c.a(), Class.forName(f12024a));
                intent.putExtra("type", 5);
                intent.putExtra("startTime", j);
                intent.putExtra("endTime", j2);
                c.a().startService(intent);
            } catch (Exception e2) {
                h.a("deleteEventsByStartAndEndTS", (Throwable) e2);
            }
        } else {
            b(j, j2);
        }
    }

    public void b(long j, long j2) {
        i iVar;
        synchronized (c) {
            try {
                h.b("EventDAO", "deleteEventsByStartAndEndTS, start:%d, end:%d", Long.valueOf(j), Long.valueOf(j2));
                c.getWritableDatabase().delete("mistat_event", "ts<=? AND ts>=?", new String[]{String.valueOf(j2), String.valueOf(j)});
                iVar = c;
            } catch (SQLiteException e2) {
                try {
                    h.a("EventDAO", "Error while deleting event by ts from DB", (Throwable) e2);
                    iVar = c;
                } catch (Throwable th) {
                    c.close();
                    throw th;
                }
            }
            iVar.close();
        }
    }
}
