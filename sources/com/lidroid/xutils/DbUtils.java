package com.lidroid.xutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.CursorUtils;
import com.lidroid.xutils.db.sqlite.DbModelSelector;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.sqlite.SqlInfoBuilder;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.db.table.Id;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.db.table.TableUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DbUtils {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, DbUtils> f6284a = new HashMap<>();
    private SQLiteDatabase b;
    private DaoConfig c;
    private boolean d = false;
    private boolean e = false;
    private Lock f = new ReentrantLock();
    private volatile boolean g = false;
    private final FindTempCache h = new FindTempCache(this, (FindTempCache) null);

    public interface DbUpgradeListener {
        void onUpgrade(DbUtils dbUtils, int i, int i2);
    }

    private DbUtils(DaoConfig daoConfig) {
        if (daoConfig != null) {
            this.b = c(daoConfig);
            this.c = daoConfig;
            return;
        }
        throw new IllegalArgumentException("daoConfig may not be null");
    }

    private static synchronized DbUtils b(DaoConfig daoConfig) {
        DbUtils dbUtils;
        synchronized (DbUtils.class) {
            dbUtils = f6284a.get(daoConfig.b());
            if (dbUtils == null) {
                dbUtils = new DbUtils(daoConfig);
                f6284a.put(daoConfig.b(), dbUtils);
            } else {
                dbUtils.c = daoConfig;
            }
            SQLiteDatabase sQLiteDatabase = dbUtils.b;
            int version = sQLiteDatabase.getVersion();
            int c2 = daoConfig.c();
            if (version != c2) {
                if (version != 0) {
                    DbUpgradeListener d2 = daoConfig.d();
                    if (d2 != null) {
                        d2.onUpgrade(dbUtils, version, c2);
                    } else {
                        try {
                            dbUtils.c();
                        } catch (DbException e2) {
                            LogUtils.b(e2.getMessage(), e2);
                        }
                    }
                }
                sQLiteDatabase.setVersion(c2);
            }
        }
        return dbUtils;
    }

    public static DbUtils a(Context context) {
        return b(new DaoConfig(context));
    }

    public static DbUtils a(Context context, String str) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.a(str);
        return b(daoConfig);
    }

    public static DbUtils a(Context context, String str, String str2) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.b(str);
        daoConfig.a(str2);
        return b(daoConfig);
    }

    public static DbUtils a(Context context, String str, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.a(str);
        daoConfig.a(i);
        daoConfig.a(dbUpgradeListener);
        return b(daoConfig);
    }

    public static DbUtils a(Context context, String str, String str2, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.b(str);
        daoConfig.a(str2);
        daoConfig.a(i);
        daoConfig.a(dbUpgradeListener);
        return b(daoConfig);
    }

    public static DbUtils a(DaoConfig daoConfig) {
        return b(daoConfig);
    }

    public DbUtils a(boolean z) {
        this.d = z;
        return this;
    }

    public DbUtils b(boolean z) {
        this.e = z;
        return this;
    }

    public SQLiteDatabase a() {
        return this.b;
    }

    public DaoConfig b() {
        return this.c;
    }

    public void a(Object obj) throws DbException {
        try {
            e();
            e(obj.getClass());
            f(obj);
            f();
        } finally {
            g();
        }
    }

    public void a(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                e();
                e(list.get(0).getClass());
                for (Object f2 : list) {
                    f((Object) f2);
                }
                f();
            } finally {
                g();
            }
        }
    }

    public void b(Object obj) throws DbException {
        try {
            e();
            e(obj.getClass());
            c(SqlInfoBuilder.b(this, obj));
            f();
        } finally {
            g();
        }
    }

    public void b(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                e();
                e(list.get(0).getClass());
                for (Object b2 : list) {
                    c(SqlInfoBuilder.b(this, b2));
                }
                f();
            } finally {
                g();
            }
        }
    }

    public void c(Object obj) throws DbException {
        try {
            e();
            e(obj.getClass());
            c(SqlInfoBuilder.a(this, obj));
            f();
        } finally {
            g();
        }
    }

    public void c(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                e();
                e(list.get(0).getClass());
                for (Object a2 : list) {
                    c(SqlInfoBuilder.a(this, (Object) a2));
                }
                f();
            } finally {
                g();
            }
        }
    }

    public boolean d(Object obj) throws DbException {
        try {
            e();
            e(obj.getClass());
            boolean g2 = g(obj);
            f();
            return g2;
        } finally {
            g();
        }
    }

    public void d(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                e();
                e(list.get(0).getClass());
                for (Object g2 : list) {
                    if (!g((Object) g2)) {
                        throw new DbException("saveBindingId error, transaction will not commit!");
                    }
                }
                f();
            } finally {
                g();
            }
        }
    }

    public void a(Class<?> cls, Object obj) throws DbException {
        if (f(cls)) {
            try {
                e();
                c(SqlInfoBuilder.a(this, cls, obj));
                f();
            } finally {
                g();
            }
        }
    }

    public void e(Object obj) throws DbException {
        if (f(obj.getClass())) {
            try {
                e();
                c(SqlInfoBuilder.c(this, obj));
                f();
            } finally {
                g();
            }
        }
    }

    public void a(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        if (f(cls)) {
            try {
                e();
                c(SqlInfoBuilder.a(this, cls, whereBuilder));
                f();
            } finally {
                g();
            }
        }
    }

    public void e(List<?> list) throws DbException {
        if (list != null && list.size() != 0 && f(list.get(0).getClass())) {
            try {
                e();
                for (Object c2 : list) {
                    c(SqlInfoBuilder.c(this, c2));
                }
                f();
            } finally {
                g();
            }
        }
    }

    public void a(Class<?> cls) throws DbException {
        a(cls, (WhereBuilder) null);
    }

    public void a(Object obj, String... strArr) throws DbException {
        if (f(obj.getClass())) {
            try {
                e();
                c(SqlInfoBuilder.a(this, obj, strArr));
                f();
            } finally {
                g();
            }
        }
    }

    public void a(Object obj, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (f(obj.getClass())) {
            try {
                e();
                c(SqlInfoBuilder.a(this, obj, whereBuilder, strArr));
                f();
            } finally {
                g();
            }
        }
    }

    public void a(List<?> list, String... strArr) throws DbException {
        if (list != null && list.size() != 0 && f(list.get(0).getClass())) {
            try {
                e();
                for (Object a2 : list) {
                    c(SqlInfoBuilder.a(this, (Object) a2, strArr));
                }
                f();
            } finally {
                g();
            }
        }
    }

    public void a(List<?> list, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (list != null && list.size() != 0 && f(list.get(0).getClass())) {
            try {
                e();
                for (Object a2 : list) {
                    c(SqlInfoBuilder.a(this, a2, whereBuilder, strArr));
                }
                f();
            } finally {
                g();
            }
        }
    }

    public <T> T b(Class<T> cls, Object obj) throws DbException {
        if (!f((Class<?>) cls)) {
            return null;
        }
        String selector = Selector.a((Class<?>) cls).a(Table.a(this, (Class<?>) cls).c.c(), "=", obj).a(1).toString();
        long a2 = CursorUtils.FindCacheSequence.a();
        this.h.a(a2);
        T a3 = this.h.a(selector);
        if (a3 != null) {
            return a3;
        }
        Cursor b2 = b(selector);
        if (b2 != null) {
            try {
                if (b2.moveToNext()) {
                    T a4 = CursorUtils.a(this, b2, cls, a2);
                    this.h.a(selector, a4);
                    IOUtils.a(b2);
                    return a4;
                }
                IOUtils.a(b2);
            } catch (Throwable th) {
                IOUtils.a(b2);
                throw th;
            }
        }
        return null;
    }

    public <T> T a(Selector selector) throws DbException {
        if (!f(selector.a())) {
            return null;
        }
        String selector2 = selector.a(1).toString();
        long a2 = CursorUtils.FindCacheSequence.a();
        this.h.a(a2);
        T a3 = this.h.a(selector2);
        if (a3 != null) {
            return a3;
        }
        Cursor b2 = b(selector2);
        if (b2 != null) {
            try {
                if (b2.moveToNext()) {
                    T a4 = CursorUtils.a(this, b2, selector.a(), a2);
                    this.h.a(selector2, a4);
                    IOUtils.a(b2);
                    return a4;
                }
                IOUtils.a(b2);
            } catch (Throwable th) {
                IOUtils.a(b2);
                throw th;
            }
        }
        return null;
    }

    public <T> T b(Class<T> cls) throws DbException {
        return a(Selector.a((Class<?>) cls));
    }

    public <T> List<T> b(Selector selector) throws DbException {
        if (!f(selector.a())) {
            return null;
        }
        String selector2 = selector.toString();
        long a2 = CursorUtils.FindCacheSequence.a();
        this.h.a(a2);
        Object a3 = this.h.a(selector2);
        if (a3 != null) {
            return (List) a3;
        }
        ArrayList arrayList = new ArrayList();
        Cursor b2 = b(selector2);
        if (b2 != null) {
            while (b2.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.a(this, b2, selector.a(), a2));
                } catch (Throwable th) {
                    IOUtils.a(b2);
                    throw th;
                }
            }
            this.h.a(selector2, arrayList);
            IOUtils.a(b2);
        }
        return arrayList;
    }

    public <T> List<T> c(Class<T> cls) throws DbException {
        return b(Selector.a((Class<?>) cls));
    }

    public DbModel a(SqlInfo sqlInfo) throws DbException {
        Cursor d2 = d(sqlInfo);
        if (d2 == null) {
            return null;
        }
        try {
            if (d2.moveToNext()) {
                DbModel a2 = CursorUtils.a(d2);
                IOUtils.a(d2);
                return a2;
            }
            IOUtils.a(d2);
            return null;
        } catch (Throwable th) {
            IOUtils.a(d2);
            throw th;
        }
    }

    public DbModel a(DbModelSelector dbModelSelector) throws DbException {
        Cursor b2;
        if (f(dbModelSelector.a()) && (b2 = b(dbModelSelector.a(1).toString())) != null) {
            try {
                if (b2.moveToNext()) {
                    DbModel a2 = CursorUtils.a(b2);
                    IOUtils.a(b2);
                    return a2;
                }
                IOUtils.a(b2);
            } catch (Throwable th) {
                IOUtils.a(b2);
                throw th;
            }
        }
        return null;
    }

    public List<DbModel> b(SqlInfo sqlInfo) throws DbException {
        ArrayList arrayList = new ArrayList();
        Cursor d2 = d(sqlInfo);
        if (d2 != null) {
            while (d2.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.a(d2));
                } catch (Throwable th) {
                    IOUtils.a(d2);
                    throw th;
                }
            }
            IOUtils.a(d2);
        }
        return arrayList;
    }

    public List<DbModel> b(DbModelSelector dbModelSelector) throws DbException {
        if (!f(dbModelSelector.a())) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor b2 = b(dbModelSelector.toString());
        if (b2 != null) {
            while (b2.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.a(b2));
                } catch (Throwable th) {
                    IOUtils.a(b2);
                    throw th;
                }
            }
            IOUtils.a(b2);
        }
        return arrayList;
    }

    public long c(Selector selector) throws DbException {
        Class<?> a2 = selector.a();
        if (!f(a2)) {
            return 0;
        }
        Table a3 = Table.a(this, a2);
        return a(selector.a("count(" + a3.c.c() + ") as count")).f("count");
    }

    public long d(Class<?> cls) throws DbException {
        return c(Selector.a(cls));
    }

    public static class DaoConfig {

        /* renamed from: a  reason: collision with root package name */
        private Context f6285a;
        private String b = "xUtils.db";
        private int c = 1;
        private DbUpgradeListener d;
        private String e;

        public DaoConfig(Context context) {
            this.f6285a = context.getApplicationContext();
        }

        public Context a() {
            return this.f6285a;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.b = str;
            }
        }

        public int c() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public DbUpgradeListener d() {
            return this.d;
        }

        public void a(DbUpgradeListener dbUpgradeListener) {
            this.d = dbUpgradeListener;
        }

        public String e() {
            return this.e;
        }

        public void b(String str) {
            this.e = str;
        }
    }

    private SQLiteDatabase c(DaoConfig daoConfig) {
        String e2 = daoConfig.e();
        if (TextUtils.isEmpty(e2)) {
            return daoConfig.a().openOrCreateDatabase(daoConfig.b(), 0, (SQLiteDatabase.CursorFactory) null);
        }
        File file = new File(e2);
        if (file.exists() || file.mkdirs()) {
            return SQLiteDatabase.openOrCreateDatabase(new File(e2, daoConfig.b()), (SQLiteDatabase.CursorFactory) null);
        }
        return null;
    }

    private void f(Object obj) throws DbException {
        Id id = Table.a(this, obj.getClass()).c;
        if (!id.h()) {
            c(SqlInfoBuilder.b(this, obj));
        } else if (id.a(obj) != null) {
            c(SqlInfoBuilder.a(this, obj, new String[0]));
        } else {
            g(obj);
        }
    }

    private boolean g(Object obj) throws DbException {
        Table a2 = Table.a(this, obj.getClass());
        Id id = a2.c;
        if (id.h()) {
            c(SqlInfoBuilder.a(this, obj));
            long c2 = c(a2.b);
            if (c2 == -1) {
                return false;
            }
            id.a(obj, c2);
            return true;
        }
        c(SqlInfoBuilder.a(this, obj));
        return true;
    }

    private long c(String str) throws DbException {
        Cursor b2 = b("SELECT seq FROM sqlite_sequence WHERE name='" + str + "'");
        long j = -1;
        if (b2 != null) {
            try {
                if (b2.moveToNext()) {
                    j = b2.getLong(0);
                }
                IOUtils.a(b2);
            } catch (Throwable th) {
                IOUtils.a(b2);
                throw th;
            }
        }
        return j;
    }

    public void e(Class<?> cls) throws DbException {
        if (!f(cls)) {
            c(SqlInfoBuilder.a(this, cls));
            String b2 = TableUtils.b(cls);
            if (!TextUtils.isEmpty(b2)) {
                a(b2);
            }
        }
    }

    public boolean f(Class<?> cls) throws DbException {
        Table a2 = Table.a(this, cls);
        if (a2.a()) {
            return true;
        }
        Cursor b2 = b("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + a2.b + "'");
        if (b2 != null) {
            try {
                if (!b2.moveToNext() || b2.getInt(0) <= 0) {
                    IOUtils.a(b2);
                } else {
                    a2.a(true);
                    IOUtils.a(b2);
                    return true;
                }
            } catch (Throwable th) {
                IOUtils.a(b2);
                throw th;
            }
        }
        return false;
    }

    public void c() throws DbException {
        Cursor b2 = b("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (b2 != null) {
            while (b2.moveToNext()) {
                try {
                    String string = b2.getString(0);
                    a("DROP TABLE " + string);
                    Table.a(this, string);
                } catch (Throwable th) {
                    try {
                        throw new DbException(th);
                    } catch (Throwable th2) {
                        IOUtils.a(b2);
                        throw th2;
                    }
                }
            }
            IOUtils.a(b2);
        }
    }

    public void g(Class<?> cls) throws DbException {
        if (f(cls)) {
            String a2 = TableUtils.a(cls);
            a("DROP TABLE " + a2);
            Table.b(this, cls);
        }
    }

    public void d() {
        String b2 = this.c.b();
        if (f6284a.containsKey(b2)) {
            f6284a.remove(b2);
            this.b.close();
        }
    }

    private void d(String str) {
        if (this.d) {
            LogUtils.a(str);
        }
    }

    private void e() {
        if (this.e) {
            this.b.beginTransaction();
            return;
        }
        this.f.lock();
        this.g = true;
    }

    private void f() {
        if (this.e) {
            this.b.setTransactionSuccessful();
        }
    }

    private void g() {
        if (this.e) {
            this.b.endTransaction();
        }
        if (this.g) {
            this.f.unlock();
            this.g = false;
        }
    }

    public void c(SqlInfo sqlInfo) throws DbException {
        d(sqlInfo.a());
        try {
            if (sqlInfo.b() != null) {
                this.b.execSQL(sqlInfo.a(), sqlInfo.c());
            } else {
                this.b.execSQL(sqlInfo.a());
            }
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public void a(String str) throws DbException {
        d(str);
        try {
            this.b.execSQL(str);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor d(SqlInfo sqlInfo) throws DbException {
        d(sqlInfo.a());
        try {
            return this.b.rawQuery(sqlInfo.a(), sqlInfo.d());
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor b(String str) throws DbException {
        d(str);
        try {
            return this.b.rawQuery(str, (String[]) null);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    private class FindTempCache {
        private final ConcurrentHashMap<String, Object> b;
        private long c;

        private FindTempCache() {
            this.b = new ConcurrentHashMap<>();
            this.c = 0;
        }

        /* synthetic */ FindTempCache(DbUtils dbUtils, FindTempCache findTempCache) {
            this();
        }

        public void a(String str, Object obj) {
            if (str != null && obj != null) {
                this.b.put(str, obj);
            }
        }

        public Object a(String str) {
            return this.b.get(str);
        }

        public void a(long j) {
            if (this.c != j) {
                this.b.clear();
                this.c = j;
            }
        }
    }
}
