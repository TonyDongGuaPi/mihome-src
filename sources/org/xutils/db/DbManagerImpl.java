package org.xutils.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.DbBase;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

public final class DbManagerImpl extends DbBase {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<DbManager.DaoConfig, DbManagerImpl> f4237a = new HashMap<>();
    private SQLiteDatabase b;
    private DbManager.DaoConfig c;
    private boolean d;

    private DbManagerImpl(DbManager.DaoConfig daoConfig) {
        if (daoConfig != null) {
            this.c = daoConfig;
            this.d = daoConfig.d();
            this.b = b(daoConfig);
            DbManager.DbOpenListener e = daoConfig.e();
            if (e != null) {
                e.a(this);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("daoConfig may not be null");
    }

    public static synchronized DbManager a(DbManager.DaoConfig daoConfig) {
        DbManagerImpl dbManagerImpl;
        synchronized (DbManagerImpl.class) {
            if (daoConfig == null) {
                try {
                    daoConfig = new DbManager.DaoConfig();
                } catch (DbException e) {
                    LogUtil.b(e.getMessage(), e);
                } catch (Throwable th) {
                    throw th;
                }
            }
            dbManagerImpl = f4237a.get(daoConfig);
            if (dbManagerImpl == null) {
                dbManagerImpl = new DbManagerImpl(daoConfig);
                f4237a.put(daoConfig, dbManagerImpl);
            } else {
                dbManagerImpl.c = daoConfig;
            }
            SQLiteDatabase sQLiteDatabase = dbManagerImpl.b;
            int version = sQLiteDatabase.getVersion();
            int c2 = daoConfig.c();
            if (version != c2) {
                if (version != 0) {
                    DbManager.DbUpgradeListener f = daoConfig.f();
                    if (f != null) {
                        f.a(dbManagerImpl, version, c2);
                    } else {
                        dbManagerImpl.c();
                    }
                }
                sQLiteDatabase.setVersion(c2);
            }
        }
        return dbManagerImpl;
    }

    public SQLiteDatabase b() {
        return this.b;
    }

    public DbManager.DaoConfig a() {
        return this.c;
    }

    public void b(Object obj) throws DbException {
        try {
            d();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    TableEntity<?> e = e(list.get(0).getClass());
                    a(e);
                    for (Object a2 : list) {
                        a(e, a2);
                    }
                } else {
                    return;
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                a(e2);
                a(e2, obj);
            }
            e();
            f();
        } finally {
            f();
        }
    }

    public void d(Object obj) throws DbException {
        try {
            d();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    TableEntity<?> e = e(list.get(0).getClass());
                    a(e);
                    for (Object b2 : list) {
                        d(SqlInfoBuilder.b(e, b2));
                    }
                } else {
                    return;
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                a(e2);
                d(SqlInfoBuilder.b(e2, obj));
            }
            e();
            f();
        } finally {
            f();
        }
    }

    public void c(Object obj) throws DbException {
        try {
            d();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    TableEntity<?> e = e(list.get(0).getClass());
                    a(e);
                    for (Object a2 : list) {
                        d(SqlInfoBuilder.a(e, a2));
                    }
                } else {
                    return;
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                a(e2);
                d(SqlInfoBuilder.a(e2, obj));
            }
            e();
            f();
        } finally {
            f();
        }
    }

    public boolean a(Object obj) throws DbException {
        try {
            d();
            boolean z = false;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (list.isEmpty()) {
                    return false;
                }
                TableEntity<?> e = e(list.get(0).getClass());
                a(e);
                for (Object b2 : list) {
                    if (!b(e, b2)) {
                        throw new DbException("saveBindingId error, transaction will not commit!");
                    }
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                a(e2);
                z = b(e2, obj);
            }
            e();
            f();
            return z;
        } finally {
            f();
        }
    }

    public void a(Class<?> cls, Object obj) throws DbException {
        TableEntity<?> e = e(cls);
        if (e.b()) {
            try {
                d();
                d(SqlInfoBuilder.d(e, obj));
                e();
            } finally {
                f();
            }
        }
    }

    public void e(Object obj) throws DbException {
        try {
            d();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    TableEntity<?> e = e(list.get(0).getClass());
                    if (!e.b()) {
                        f();
                        return;
                    }
                    for (Object c2 : list) {
                        d(SqlInfoBuilder.c(e, c2));
                    }
                } else {
                    return;
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                if (!e2.b()) {
                    f();
                    return;
                }
                d(SqlInfoBuilder.c(e2, obj));
            }
            e();
            f();
        } finally {
            f();
        }
    }

    public void a(Class<?> cls) throws DbException {
        a(cls, (WhereBuilder) null);
    }

    public int a(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        TableEntity<?> e = e(cls);
        if (!e.b()) {
            return 0;
        }
        try {
            d();
            int c2 = c(SqlInfoBuilder.a(e, whereBuilder));
            e();
            return c2;
        } finally {
            f();
        }
    }

    public void a(Object obj, String... strArr) throws DbException {
        try {
            d();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    TableEntity<?> e = e(list.get(0).getClass());
                    if (!e.b()) {
                        f();
                        return;
                    }
                    for (Object a2 : list) {
                        d(SqlInfoBuilder.a(e, a2, strArr));
                    }
                } else {
                    return;
                }
            } else {
                TableEntity<?> e2 = e(obj.getClass());
                if (!e2.b()) {
                    f();
                    return;
                }
                d(SqlInfoBuilder.a(e2, obj, strArr));
            }
            e();
            f();
        } finally {
            f();
        }
    }

    public int a(Class<?> cls, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException {
        TableEntity<?> e = e(cls);
        if (!e.b()) {
            return 0;
        }
        try {
            d();
            int c2 = c(SqlInfoBuilder.a(e, whereBuilder, keyValueArr));
            e();
            return c2;
        } finally {
            f();
        }
    }

    public <T> T b(Class<T> cls, Object obj) throws DbException {
        Cursor c2;
        TableEntity<T> e = e(cls);
        if (e.b() && (c2 = c(Selector.a(e).a(e.g().a(), "=", obj).a(1).toString())) != null) {
            try {
                if (c2.moveToNext()) {
                    T a2 = CursorUtils.a(e, c2);
                    IOUtil.a(c2);
                    return a2;
                }
                IOUtil.a(c2);
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return null;
    }

    public <T> T b(Class<T> cls) throws DbException {
        return d(cls).f();
    }

    public <T> List<T> c(Class<T> cls) throws DbException {
        return d(cls).g();
    }

    public <T> Selector<T> d(Class<T> cls) throws DbException {
        return Selector.a(e(cls));
    }

    public DbModel a(SqlInfo sqlInfo) throws DbException {
        Cursor e = e(sqlInfo);
        if (e == null) {
            return null;
        }
        try {
            if (e.moveToNext()) {
                DbModel a2 = CursorUtils.a(e);
                IOUtil.a(e);
                return a2;
            }
            IOUtil.a(e);
            return null;
        } catch (Throwable th) {
            IOUtil.a(e);
            throw th;
        }
    }

    public List<DbModel> b(SqlInfo sqlInfo) throws DbException {
        ArrayList arrayList = new ArrayList();
        Cursor e = e(sqlInfo);
        if (e != null) {
            while (e.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.a(e));
                } catch (Throwable th) {
                    IOUtil.a(e);
                    throw th;
                }
            }
            IOUtil.a(e);
        }
        return arrayList;
    }

    private SQLiteDatabase b(DbManager.DaoConfig daoConfig) {
        File a2 = daoConfig.a();
        if (a2 == null || (!a2.exists() && !a2.mkdirs())) {
            return x.b().openOrCreateDatabase(daoConfig.b(), 0, (SQLiteDatabase.CursorFactory) null);
        }
        return SQLiteDatabase.openOrCreateDatabase(new File(a2, daoConfig.b()), (SQLiteDatabase.CursorFactory) null);
    }

    private void a(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity g = tableEntity.g();
        if (!g.d()) {
            d(SqlInfoBuilder.b(tableEntity, obj));
        } else if (g.a(obj) != null) {
            d(SqlInfoBuilder.a(tableEntity, obj, new String[0]));
        } else {
            b(tableEntity, obj);
        }
    }

    private boolean b(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity g = tableEntity.g();
        if (g.d()) {
            d(SqlInfoBuilder.a(tableEntity, obj));
            long d2 = d(tableEntity.d());
            if (d2 == -1) {
                return false;
            }
            g.a(obj, d2);
            return true;
        }
        d(SqlInfoBuilder.a(tableEntity, obj));
        return true;
    }

    private long d(String str) throws DbException {
        Cursor c2 = c("SELECT seq FROM sqlite_sequence WHERE name='" + str + "' LIMIT 1");
        long j = -1;
        if (c2 != null) {
            try {
                if (c2.moveToNext()) {
                    j = c2.getLong(0);
                }
                IOUtil.a(c2);
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return j;
    }

    public void close() throws IOException {
        if (f4237a.containsKey(this.c)) {
            f4237a.remove(this.c);
            this.b.close();
        }
    }

    private void d() {
        if (!this.d) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16 || !this.b.isWriteAheadLoggingEnabled()) {
            this.b.beginTransaction();
        } else {
            this.b.beginTransactionNonExclusive();
        }
    }

    private void e() {
        if (this.d) {
            this.b.setTransactionSuccessful();
        }
    }

    private void f() {
        if (this.d) {
            this.b.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A[SYNTHETIC, Splitter:B:21:0x002f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c(org.xutils.db.sqlite.SqlInfo r4) throws org.xutils.ex.DbException {
        /*
            r3 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.b     // Catch:{ Throwable -> 0x0026 }
            android.database.sqlite.SQLiteStatement r4 = r4.a((android.database.sqlite.SQLiteDatabase) r1)     // Catch:{ Throwable -> 0x0026 }
            int r0 = r4.executeUpdateDelete()     // Catch:{ Throwable -> 0x001f, all -> 0x001a }
            if (r4 == 0) goto L_0x0019
            r4.releaseReference()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0019
        L_0x0011:
            r4 = move-exception
            java.lang.String r1 = r4.getMessage()
            org.xutils.common.util.LogUtil.b(r1, r4)
        L_0x0019:
            return r0
        L_0x001a:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x002d
        L_0x001f:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x0027
        L_0x0024:
            r4 = move-exception
            goto L_0x002d
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            org.xutils.ex.DbException r1 = new org.xutils.ex.DbException     // Catch:{ all -> 0x0024 }
            r1.<init>((java.lang.Throwable) r4)     // Catch:{ all -> 0x0024 }
            throw r1     // Catch:{ all -> 0x0024 }
        L_0x002d:
            if (r0 == 0) goto L_0x003b
            r0.releaseReference()     // Catch:{ Throwable -> 0x0033 }
            goto L_0x003b
        L_0x0033:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            org.xutils.common.util.LogUtil.b(r1, r0)
        L_0x003b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.c(org.xutils.db.sqlite.SqlInfo):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A[SYNTHETIC, Splitter:B:21:0x002f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r4) throws org.xutils.ex.DbException {
        /*
            r3 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.b     // Catch:{ Throwable -> 0x0026 }
            android.database.sqlite.SQLiteStatement r4 = r1.compileStatement(r4)     // Catch:{ Throwable -> 0x0026 }
            int r0 = r4.executeUpdateDelete()     // Catch:{ Throwable -> 0x001f, all -> 0x001a }
            if (r4 == 0) goto L_0x0019
            r4.releaseReference()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x0019
        L_0x0011:
            r4 = move-exception
            java.lang.String r1 = r4.getMessage()
            org.xutils.common.util.LogUtil.b(r1, r4)
        L_0x0019:
            return r0
        L_0x001a:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x002d
        L_0x001f:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x0027
        L_0x0024:
            r4 = move-exception
            goto L_0x002d
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            org.xutils.ex.DbException r1 = new org.xutils.ex.DbException     // Catch:{ all -> 0x0024 }
            r1.<init>((java.lang.Throwable) r4)     // Catch:{ all -> 0x0024 }
            throw r1     // Catch:{ all -> 0x0024 }
        L_0x002d:
            if (r0 == 0) goto L_0x003b
            r0.releaseReference()     // Catch:{ Throwable -> 0x0033 }
            goto L_0x003b
        L_0x0033:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            org.xutils.common.util.LogUtil.b(r1, r0)
        L_0x003b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.a(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A[SYNTHETIC, Splitter:B:20:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(org.xutils.db.sqlite.SqlInfo r4) throws org.xutils.ex.DbException {
        /*
            r3 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.b     // Catch:{ Throwable -> 0x0025 }
            android.database.sqlite.SQLiteStatement r4 = r4.a((android.database.sqlite.SQLiteDatabase) r1)     // Catch:{ Throwable -> 0x0025 }
            r4.execute()     // Catch:{ Throwable -> 0x001e, all -> 0x0019 }
            if (r4 == 0) goto L_0x0018
            r4.releaseReference()     // Catch:{ Throwable -> 0x0010 }
            goto L_0x0018
        L_0x0010:
            r4 = move-exception
            java.lang.String r0 = r4.getMessage()
            org.xutils.common.util.LogUtil.b(r0, r4)
        L_0x0018:
            return
        L_0x0019:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x002c
        L_0x001e:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L_0x0026
        L_0x0023:
            r4 = move-exception
            goto L_0x002c
        L_0x0025:
            r4 = move-exception
        L_0x0026:
            org.xutils.ex.DbException r1 = new org.xutils.ex.DbException     // Catch:{ all -> 0x0023 }
            r1.<init>((java.lang.Throwable) r4)     // Catch:{ all -> 0x0023 }
            throw r1     // Catch:{ all -> 0x0023 }
        L_0x002c:
            if (r0 == 0) goto L_0x003a
            r0.releaseReference()     // Catch:{ Throwable -> 0x0032 }
            goto L_0x003a
        L_0x0032:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            org.xutils.common.util.LogUtil.b(r1, r0)
        L_0x003a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.d(org.xutils.db.sqlite.SqlInfo):void");
    }

    public void b(String str) throws DbException {
        try {
            this.b.execSQL(str);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor e(SqlInfo sqlInfo) throws DbException {
        try {
            return this.b.rawQuery(sqlInfo.a(), sqlInfo.c());
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor c(String str) throws DbException {
        try {
            return this.b.rawQuery(str, (String[]) null);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }
}
