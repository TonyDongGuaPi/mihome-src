package org.xutils.db.table;

import android.database.Cursor;
import android.text.TextUtils;
import java.util.HashMap;
import org.xutils.DbManager;
import org.xutils.common.util.IOUtil;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.ex.DbException;

public abstract class DbBase implements DbManager {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<Class<?>, TableEntity<?>> f4248a = new HashMap<>();

    public <T> TableEntity<T> e(Class<T> cls) throws DbException {
        TableEntity<T> tableEntity;
        synchronized (this.f4248a) {
            tableEntity = this.f4248a.get(cls);
            if (tableEntity == null) {
                try {
                    tableEntity = new TableEntity<>(this, cls);
                    this.f4248a.put(cls, tableEntity);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            }
        }
        return tableEntity;
    }

    public void f(Class<?> cls) throws DbException {
        TableEntity<?> e = e(cls);
        if (e.b()) {
            b("DROP TABLE \"" + e.d() + "\"");
            e.a(false);
            g(cls);
        }
    }

    public void c() throws DbException {
        Cursor c = c("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    String string = c.getString(0);
                    b("DROP TABLE " + string);
                } catch (Throwable th) {
                    try {
                        throw new DbException(th);
                    } catch (Throwable th2) {
                        IOUtil.a(c);
                        throw th2;
                    }
                }
            }
            synchronized (this.f4248a) {
                for (TableEntity<?> a2 : this.f4248a.values()) {
                    a2.a(false);
                }
                this.f4248a.clear();
            }
            IOUtil.a(c);
        }
    }

    public void a(Class<?> cls, String str) throws DbException {
        TableEntity<?> e = e(cls);
        ColumnEntity columnEntity = e.h().get(str);
        if (columnEntity != null) {
            b("ALTER TABLE " + "\"" + e.d() + "\"" + " ADD COLUMN " + "\"" + columnEntity.a() + "\"" + " " + columnEntity.g() + " " + columnEntity.b());
        }
    }

    /* access modifiers changed from: protected */
    public void a(TableEntity<?> tableEntity) throws DbException {
        if (!tableEntity.b()) {
            synchronized (tableEntity.getClass()) {
                if (!tableEntity.b()) {
                    d(SqlInfoBuilder.a(tableEntity));
                    String f = tableEntity.f();
                    if (!TextUtils.isEmpty(f)) {
                        b(f);
                    }
                    tableEntity.a(true);
                    DbManager.TableCreateListener g = a().g();
                    if (g != null) {
                        g.a(this, tableEntity);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void g(Class<?> cls) {
        synchronized (this.f4248a) {
            this.f4248a.remove(cls);
        }
    }
}
