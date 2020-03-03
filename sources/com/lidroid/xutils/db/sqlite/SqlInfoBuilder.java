package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.table.Column;
import com.lidroid.xutils.db.table.ColumnUtils;
import com.lidroid.xutils.db.table.Finder;
import com.lidroid.xutils.db.table.Id;
import com.lidroid.xutils.db.table.KeyValue;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.db.table.TableUtils;
import com.lidroid.xutils.exception.DbException;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.hawkeye.storage.DBHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SqlInfoBuilder {
    private SqlInfoBuilder() {
    }

    public static SqlInfo a(DbUtils dbUtils, Object obj) throws DbException {
        List<KeyValue> d = d(dbUtils, obj);
        if (d.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(TableUtils.a(obj.getClass()));
        stringBuffer.append(" (");
        for (KeyValue next : d) {
            stringBuffer.append(next.f6327a);
            stringBuffer.append(",");
            sqlInfo.b(next.b);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = d.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(Operators.BRACKET_END_STR);
        sqlInfo.a(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo b(DbUtils dbUtils, Object obj) throws DbException {
        List<KeyValue> d = d(dbUtils, obj);
        if (d.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("REPLACE INTO ");
        stringBuffer.append(TableUtils.a(obj.getClass()));
        stringBuffer.append(" (");
        for (KeyValue next : d) {
            stringBuffer.append(next.f6327a);
            stringBuffer.append(",");
            sqlInfo.b(next.b);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = d.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(Operators.BRACKET_END_STR);
        sqlInfo.a(stringBuffer.toString());
        return sqlInfo;
    }

    private static String a(String str) {
        return "DELETE FROM " + str;
    }

    public static SqlInfo c(DbUtils dbUtils, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        Table a2 = Table.a(dbUtils, obj.getClass());
        Id id = a2.c;
        Object a3 = id.a(obj);
        if (a3 != null) {
            sqlInfo.a(a(a2.b) + " WHERE " + WhereBuilder.a(id.c(), "=", a3));
            return sqlInfo;
        }
        throw new DbException("this entity[" + obj.getClass() + "]'s id value is null");
    }

    public static SqlInfo a(DbUtils dbUtils, Class<?> cls, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        Table a2 = Table.a(dbUtils, cls);
        Id id = a2.c;
        if (obj != null) {
            sqlInfo.a(a(a2.b) + " WHERE " + WhereBuilder.a(id.c(), "=", obj));
            return sqlInfo;
        }
        throw new DbException("this entity[" + cls + "]'s id value is null");
    }

    public static SqlInfo a(DbUtils dbUtils, Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        StringBuilder sb = new StringBuilder(a(Table.a(dbUtils, cls).b));
        if (whereBuilder != null && whereBuilder.b() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        return new SqlInfo(sb.toString());
    }

    public static SqlInfo a(DbUtils dbUtils, Object obj, String... strArr) throws DbException {
        List<KeyValue> d = d(dbUtils, obj);
        HashSet hashSet = null;
        if (d.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        Table a2 = Table.a(dbUtils, obj.getClass());
        Id id = a2.c;
        Object a3 = id.a(obj);
        if (a3 != null) {
            SqlInfo sqlInfo = new SqlInfo();
            StringBuffer stringBuffer = new StringBuffer("UPDATE ");
            stringBuffer.append(a2.b);
            stringBuffer.append(" SET ");
            for (KeyValue next : d) {
                if (hashSet == null || hashSet.contains(next.f6327a)) {
                    stringBuffer.append(next.f6327a);
                    stringBuffer.append("=?,");
                    sqlInfo.b(next.b);
                }
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(" WHERE ");
            stringBuffer.append(WhereBuilder.a(id.c(), "=", a3));
            sqlInfo.a(stringBuffer.toString());
            return sqlInfo;
        }
        throw new DbException("this entity[" + obj.getClass() + "]'s id value is null");
    }

    public static SqlInfo a(DbUtils dbUtils, Object obj, WhereBuilder whereBuilder, String... strArr) throws DbException {
        List<KeyValue> d = d(dbUtils, obj);
        HashSet hashSet = null;
        if (d.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        String a2 = TableUtils.a(obj.getClass());
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(a2);
        stringBuffer.append(" SET ");
        for (KeyValue next : d) {
            if (hashSet == null || hashSet.contains(next.f6327a)) {
                stringBuffer.append(next.f6327a);
                stringBuffer.append("=?,");
                sqlInfo.b(next.b);
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        if (whereBuilder != null && whereBuilder.b() > 0) {
            stringBuffer.append(" WHERE ");
            stringBuffer.append(whereBuilder.toString());
        }
        sqlInfo.a(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo a(DbUtils dbUtils, Class<?> cls) throws DbException {
        Table a2 = Table.a(dbUtils, cls);
        Id id = a2.c;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DBHelper.d);
        stringBuffer.append(a2.b);
        stringBuffer.append(" ( ");
        if (id.h()) {
            stringBuffer.append("\"");
            stringBuffer.append(id.c());
            stringBuffer.append("\"  ");
            stringBuffer.append("INTEGER PRIMARY KEY AUTOINCREMENT,");
        } else {
            stringBuffer.append("\"");
            stringBuffer.append(id.c());
            stringBuffer.append("\"  ");
            stringBuffer.append(id.g());
            stringBuffer.append(" PRIMARY KEY,");
        }
        for (Column next : a2.d.values()) {
            if (!(next instanceof Finder)) {
                stringBuffer.append("\"");
                stringBuffer.append(next.c());
                stringBuffer.append("\"  ");
                stringBuffer.append(next.g());
                if (ColumnUtils.g(next.e())) {
                    stringBuffer.append(" UNIQUE");
                }
                if (ColumnUtils.h(next.e())) {
                    stringBuffer.append(" NOT NULL");
                }
                String i = ColumnUtils.i(next.e());
                if (i != null) {
                    stringBuffer.append(" CHECK(");
                    stringBuffer.append(i);
                    stringBuffer.append(Operators.BRACKET_END_STR);
                }
                stringBuffer.append(",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" )");
        return new SqlInfo(stringBuffer.toString());
    }

    private static KeyValue a(Object obj, Column column) {
        String c = column.c();
        if (c == null) {
            return null;
        }
        Object a2 = column.a(obj);
        if (a2 == null) {
            a2 = column.d();
        }
        return new KeyValue(c, a2);
    }

    public static List<KeyValue> d(DbUtils dbUtils, Object obj) {
        KeyValue a2;
        ArrayList arrayList = new ArrayList();
        Table a3 = Table.a(dbUtils, obj.getClass());
        Id id = a3.c;
        if (!id.h()) {
            arrayList.add(new KeyValue(id.c(), id.a(obj)));
        }
        for (Column next : a3.d.values()) {
            if (!(next instanceof Finder) && (a2 = a(obj, next)) != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
