package com.lidroid.xutils.db.sqlite;

import android.database.Cursor;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.table.Column;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.db.table.Finder;
import com.lidroid.xutils.db.table.Id;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.util.LogUtils;
import java.util.concurrent.ConcurrentHashMap;

public class CursorUtils {
    public static <T> T a(DbUtils dbUtils, Cursor cursor, Class<T> cls, long j) {
        if (dbUtils == null || cursor == null) {
            return null;
        }
        EntityTempCache.a(j);
        try {
            Table a2 = Table.a(dbUtils, (Class<?>) cls);
            Id id = a2.c;
            String c = id.c();
            int b = id.b();
            if (b < 0) {
                b = cursor.getColumnIndex(c);
            }
            Object b2 = id.f().b(cursor, b);
            T a3 = EntityTempCache.a(cls, b2);
            if (a3 != null) {
                return a3;
            }
            T newInstance = cls.newInstance();
            id.a(newInstance, cursor, b);
            EntityTempCache.a(cls, b2, newInstance);
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                Column column = a2.d.get(cursor.getColumnName(i));
                if (column != null) {
                    column.a(newInstance, cursor, i);
                }
            }
            for (Finder a4 : a2.e.values()) {
                a4.a(newInstance, (Cursor) null, 0);
            }
            return newInstance;
        } catch (Throwable th) {
            LogUtils.b(th.getMessage(), th);
            return null;
        }
    }

    public static DbModel a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        DbModel dbModel = new DbModel();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            dbModel.a(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }

    public static class FindCacheSequence {

        /* renamed from: a  reason: collision with root package name */
        private static long f6316a;
        private static final String b = ForeignLazyLoader.class.getName();
        private static final String c = FinderLazyLoader.class.getName();

        private FindCacheSequence() {
        }

        public static long a() {
            String className = Thread.currentThread().getStackTrace()[4].getClassName();
            if (!className.equals(b) && !className.equals(c)) {
                f6316a++;
            }
            return f6316a;
        }
    }

    private static class EntityTempCache {

        /* renamed from: a  reason: collision with root package name */
        private static final ConcurrentHashMap<String, Object> f6315a = new ConcurrentHashMap<>();
        private static long b = 0;

        private EntityTempCache() {
        }

        public static <T> void a(Class<T> cls, Object obj, Object obj2) {
            ConcurrentHashMap<String, Object> concurrentHashMap = f6315a;
            concurrentHashMap.put(String.valueOf(cls.getName()) + "#" + obj, obj2);
        }

        public static <T> T a(Class<T> cls, Object obj) {
            ConcurrentHashMap<String, Object> concurrentHashMap = f6315a;
            return concurrentHashMap.get(String.valueOf(cls.getName()) + "#" + obj);
        }

        public static void a(long j) {
            if (b != j) {
                f6315a.clear();
                b = j;
            }
        }
    }
}
