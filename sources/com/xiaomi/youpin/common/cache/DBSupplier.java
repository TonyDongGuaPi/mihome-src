package com.xiaomi.youpin.common.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.HashMap;
import java.util.Map;

public class DBSupplier extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    static final String f23228a = "DBSupplier";
    static final String b = "KeyValueStorage";
    static final String c = "key";
    static final String d = "value";
    static final String e = "CREATE TABLE KeyValueStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)";
    private static final int f = 1;
    private static final int g = 30;
    private static Map<String, DBSupplier> l = new HashMap();
    private Context h;
    private SQLiteDatabase i;
    private long j = 6291456;
    private String k;

    private DBSupplier(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.k = str;
        this.h = context;
    }

    public static synchronized DBSupplier a(Context context, String str) {
        DBSupplier dBSupplier;
        synchronized (DBSupplier.class) {
            dBSupplier = l.get(str);
            if (dBSupplier == null) {
                dBSupplier = new DBSupplier(context.getApplicationContext(), str);
                l.put(str, dBSupplier);
            }
        }
        return dBSupplier;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(e);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 != i3) {
            e();
            onCreate(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:14|15|17|18|19|20|21|35|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0031, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.database.sqlite.SQLiteDatabase r0 = r5.i     // Catch:{ all -> 0x0042 }
            r1 = 1
            if (r0 == 0) goto L_0x0010
            android.database.sqlite.SQLiteDatabase r0 = r5.i     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.isOpen()     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r5)
            return r1
        L_0x0010:
            r0 = 0
            r2 = 0
        L_0x0012:
            r3 = 2
            if (r2 >= r3) goto L_0x0034
            if (r2 <= 0) goto L_0x001d
            r5.e()     // Catch:{ SQLiteException -> 0x001b }
            goto L_0x001d
        L_0x001b:
            r0 = move-exception
            goto L_0x0024
        L_0x001d:
            android.database.sqlite.SQLiteDatabase r3 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x001b }
            r5.i = r3     // Catch:{ SQLiteException -> 0x001b }
            goto L_0x0034
        L_0x0024:
            r3 = 30
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x002a }
            goto L_0x0031
        L_0x002a:
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0042 }
            r3.interrupt()     // Catch:{ all -> 0x0042 }
        L_0x0031:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0034:
            android.database.sqlite.SQLiteDatabase r2 = r5.i     // Catch:{ all -> 0x0042 }
            if (r2 == 0) goto L_0x0041
            android.database.sqlite.SQLiteDatabase r0 = r5.i     // Catch:{ all -> 0x0042 }
            long r2 = r5.j     // Catch:{ all -> 0x0042 }
            r0.setMaximumSize(r2)     // Catch:{ all -> 0x0042 }
            monitor-exit(r5)
            return r1
        L_0x0041:
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.cache.DBSupplier.a():boolean");
    }

    public synchronized SQLiteDatabase b() {
        a();
        return this.i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0042, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        throw new java.lang.RuntimeException("Clearing and deleting database " + r3.k + " failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        if (e() != false) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        r0 = f23228a;
        com.xiaomi.youpin.log.LogUtils.d(r0, "Deleted Local Database " + r3.k);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() throws java.lang.RuntimeException {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.d()     // Catch:{ Exception -> 0x0023 }
            r3.f()     // Catch:{ Exception -> 0x0023 }
            java.lang.String r0 = f23228a     // Catch:{ Exception -> 0x0023 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0023 }
            r1.<init>()     // Catch:{ Exception -> 0x0023 }
            java.lang.String r2 = "Cleaned "
            r1.append(r2)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r2 = r3.k     // Catch:{ Exception -> 0x0023 }
            r1.append(r2)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0023 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0023 }
            monitor-exit(r3)
            return
        L_0x0021:
            r0 = move-exception
            goto L_0x0061
        L_0x0023:
            boolean r0 = r3.e()     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0043
            java.lang.String r0 = f23228a     // Catch:{ all -> 0x0021 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0021 }
            r1.<init>()     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = "Deleted Local Database "
            r1.append(r2)     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = r3.k     // Catch:{ all -> 0x0021 }
            r1.append(r2)     // Catch:{ all -> 0x0021 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0021 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0021 }
            monitor-exit(r3)
            return
        L_0x0043:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0021 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0021 }
            r1.<init>()     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = "Clearing and deleting database "
            r1.append(r2)     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = r3.k     // Catch:{ all -> 0x0021 }
            r1.append(r2)     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = " failed"
            r1.append(r2)     // Catch:{ all -> 0x0021 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0021 }
            r0.<init>(r1)     // Catch:{ all -> 0x0021 }
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0061:
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.cache.DBSupplier.c():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        b().delete(b, (String) null, (String[]) null);
    }

    public synchronized void a(long j2) {
        this.j = j2;
        if (this.i != null) {
            this.i.setMaximumSize(this.j);
        }
    }

    private synchronized boolean e() {
        f();
        return this.h.deleteDatabase(this.k);
    }

    private synchronized void f() {
        if (this.i != null && this.i.isOpen()) {
            this.i.close();
            this.i = null;
        }
    }
}
