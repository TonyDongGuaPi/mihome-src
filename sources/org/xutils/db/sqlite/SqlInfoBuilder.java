package org.xutils.db.sqlite;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.hawkeye.storage.DBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.common.util.KeyValue;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public final class SqlInfoBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<TableEntity<?>, String> f4244a = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<TableEntity<?>, String> b = new ConcurrentHashMap<>();

    private SqlInfoBuilder() {
    }

    public static SqlInfo a(TableEntity<?> tableEntity, Object obj) throws DbException {
        List<KeyValue> e = e(tableEntity, obj);
        if (e.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        String str = f4244a.get(tableEntity);
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append("\"");
            sb.append(tableEntity.d());
            sb.append("\"");
            sb.append(" (");
            for (KeyValue keyValue : e) {
                sb.append("\"");
                sb.append(keyValue.f4233a);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            int size = e.size();
            for (int i = 0; i < size; i++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(Operators.BRACKET_END_STR);
            String sb2 = sb.toString();
            sqlInfo.a(sb2);
            sqlInfo.a(e);
            f4244a.put(tableEntity, sb2);
        } else {
            sqlInfo.a(str);
            sqlInfo.a(e);
        }
        return sqlInfo;
    }

    public static SqlInfo b(TableEntity<?> tableEntity, Object obj) throws DbException {
        List<KeyValue> e = e(tableEntity, obj);
        if (e.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        String str = b.get(tableEntity);
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("REPLACE INTO ");
            sb.append("\"");
            sb.append(tableEntity.d());
            sb.append("\"");
            sb.append(" (");
            for (KeyValue keyValue : e) {
                sb.append("\"");
                sb.append(keyValue.f4233a);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            int size = e.size();
            for (int i = 0; i < size; i++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(Operators.BRACKET_END_STR);
            String sb2 = sb.toString();
            sqlInfo.a(sb2);
            sqlInfo.a(e);
            b.put(tableEntity, sb2);
        } else {
            sqlInfo.a(str);
            sqlInfo.a(e);
        }
        return sqlInfo;
    }

    public static SqlInfo c(TableEntity<?> tableEntity, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        ColumnEntity g = tableEntity.g();
        Object a2 = g.a(obj);
        if (a2 != null) {
            sqlInfo.a("DELETE FROM " + "\"" + tableEntity.d() + "\"" + " WHERE " + WhereBuilder.a(g.a(), "=", a2));
            return sqlInfo;
        }
        throw new DbException("this entity[" + tableEntity.e() + "]'s id value is null");
    }

    public static SqlInfo d(TableEntity<?> tableEntity, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        ColumnEntity g = tableEntity.g();
        if (obj != null) {
            sqlInfo.a("DELETE FROM " + "\"" + tableEntity.d() + "\"" + " WHERE " + WhereBuilder.a(g.a(), "=", obj));
            return sqlInfo;
        }
        throw new DbException("this entity[" + tableEntity.e() + "]'s id value is null");
    }

    public static SqlInfo a(TableEntity<?> tableEntity, WhereBuilder whereBuilder) throws DbException {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append("\"");
        sb.append(tableEntity.d());
        sb.append("\"");
        if (whereBuilder != null && whereBuilder.b() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        return new SqlInfo(sb.toString());
    }

    public static SqlInfo a(TableEntity<?> tableEntity, Object obj, String... strArr) throws DbException {
        List<KeyValue> e = e(tableEntity, obj);
        HashSet hashSet = null;
        if (e.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        ColumnEntity g = tableEntity.g();
        Object a2 = g.a(obj);
        if (a2 != null) {
            SqlInfo sqlInfo = new SqlInfo();
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append("\"");
            sb.append(tableEntity.d());
            sb.append("\"");
            sb.append(" SET ");
            for (KeyValue next : e) {
                if (hashSet == null || hashSet.contains(next.f4233a)) {
                    sb.append("\"");
                    sb.append(next.f4233a);
                    sb.append("\"");
                    sb.append("=?,");
                    sqlInfo.a(next);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" WHERE ");
            sb.append(WhereBuilder.a(g.a(), "=", a2));
            sqlInfo.a(sb.toString());
            return sqlInfo;
        }
        throw new DbException("this entity[" + tableEntity.e() + "]'s id value is null");
    }

    public static SqlInfo a(TableEntity<?> tableEntity, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException {
        if (keyValueArr == null || keyValueArr.length == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append("\"");
        sb.append(tableEntity.d());
        sb.append("\"");
        sb.append(" SET ");
        for (KeyValue keyValue : keyValueArr) {
            sb.append("\"");
            sb.append(keyValue.f4233a);
            sb.append("\"");
            sb.append("=?,");
            sqlInfo.a(keyValue);
        }
        sb.deleteCharAt(sb.length() - 1);
        if (whereBuilder != null && whereBuilder.b() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        sqlInfo.a(sb.toString());
        return sqlInfo;
    }

    public static SqlInfo a(TableEntity<?> tableEntity) throws DbException {
        ColumnEntity g = tableEntity.g();
        StringBuilder sb = new StringBuilder();
        sb.append(DBHelper.d);
        sb.append("\"");
        sb.append(tableEntity.d());
        sb.append("\"");
        sb.append(" ( ");
        if (g.d()) {
            sb.append("\"");
            sb.append(g.a());
            sb.append("\"");
            sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        } else {
            sb.append("\"");
            sb.append(g.a());
            sb.append("\"");
            sb.append(g.g());
            sb.append(" PRIMARY KEY, ");
        }
        for (ColumnEntity next : tableEntity.h().values()) {
            if (!next.c()) {
                sb.append("\"");
                sb.append(next.a());
                sb.append("\"");
                sb.append(' ');
                sb.append(next.g());
                sb.append(' ');
                sb.append(next.b());
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" )");
        return new SqlInfo(sb.toString());
    }

    public static List<KeyValue> e(TableEntity<?> tableEntity, Object obj) {
        Collection<ColumnEntity> values = tableEntity.h().values();
        ArrayList arrayList = new ArrayList(values.size());
        for (ColumnEntity a2 : values) {
            KeyValue a3 = a(obj, a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        return arrayList;
    }

    private static KeyValue a(Object obj, ColumnEntity columnEntity) {
        if (columnEntity.d()) {
            return null;
        }
        return new KeyValue(columnEntity.a(), columnEntity.b(obj));
    }
}
