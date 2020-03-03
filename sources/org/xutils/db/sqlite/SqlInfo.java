package org.xutils.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;

public final class SqlInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f4242a;
    private List<KeyValue> b;

    public SqlInfo() {
    }

    public SqlInfo(String str) {
        this.f4242a = str;
    }

    public String a() {
        return this.f4242a;
    }

    public void a(String str) {
        this.f4242a = str;
    }

    public void a(KeyValue keyValue) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(keyValue);
    }

    public void a(List<KeyValue> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
    }

    public SQLiteStatement a(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(this.f4242a);
        if (this.b != null) {
            for (int i = 1; i < this.b.size() + 1; i++) {
                Object a2 = ColumnUtils.a(this.b.get(i - 1).b);
                if (a2 != null) {
                    switch (ColumnConverterFactory.a(a2.getClass()).a()) {
                        case INTEGER:
                            compileStatement.bindLong(i, ((Number) a2).longValue());
                            break;
                        case REAL:
                            compileStatement.bindDouble(i, ((Number) a2).doubleValue());
                            break;
                        case TEXT:
                            compileStatement.bindString(i, a2.toString());
                            break;
                        case BLOB:
                            compileStatement.bindBlob(i, (byte[]) a2);
                            break;
                        default:
                            compileStatement.bindNull(i);
                            break;
                    }
                } else {
                    compileStatement.bindNull(i);
                }
            }
        }
        return compileStatement;
    }

    public Object[] b() {
        if (this.b == null) {
            return null;
        }
        Object[] objArr = new Object[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            objArr[i] = ColumnUtils.a(this.b.get(i).b);
        }
        return objArr;
    }

    public String[] c() {
        String str;
        if (this.b == null) {
            return null;
        }
        String[] strArr = new String[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            Object a2 = ColumnUtils.a(this.b.get(i).b);
            if (a2 == null) {
                str = null;
            } else {
                str = a2.toString();
            }
            strArr[i] = str;
        }
        return strArr;
    }
}
