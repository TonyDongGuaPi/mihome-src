package org.xutils.db.table;

import android.database.Cursor;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import org.xutils.DbManager;
import org.xutils.common.util.IOUtil;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

public final class TableEntity<T> {

    /* renamed from: a  reason: collision with root package name */
    private final DbManager f4250a;
    private final String b;
    private final String c;
    private ColumnEntity d;
    private Class<T> e;
    private Constructor<T> f;
    private volatile boolean g;
    private final LinkedHashMap<String, ColumnEntity> h;

    TableEntity(DbManager dbManager, Class<T> cls) throws Throwable {
        this.f4250a = dbManager;
        this.e = cls;
        this.f = cls.getConstructor(new Class[0]);
        this.f.setAccessible(true);
        Table table = (Table) cls.getAnnotation(Table.class);
        this.b = table.name();
        this.c = table.onCreated();
        this.h = TableUtils.a(cls);
        for (ColumnEntity next : this.h.values()) {
            if (next.c()) {
                this.d = next;
                return;
            }
        }
    }

    public T a() throws Throwable {
        return this.f.newInstance(new Object[0]);
    }

    public boolean b() throws DbException {
        if (i()) {
            return true;
        }
        DbManager dbManager = this.f4250a;
        Cursor c2 = dbManager.c("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + this.b + "'");
        if (c2 != null) {
            try {
                if (!c2.moveToNext() || c2.getInt(0) <= 0) {
                    IOUtil.a(c2);
                } else {
                    a(true);
                    IOUtil.a(c2);
                    return true;
                }
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return false;
    }

    public DbManager c() {
        return this.f4250a;
    }

    public String d() {
        return this.b;
    }

    public Class<T> e() {
        return this.e;
    }

    public String f() {
        return this.c;
    }

    public ColumnEntity g() {
        return this.d;
    }

    public LinkedHashMap<String, ColumnEntity> h() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.g = z;
    }

    public String toString() {
        return this.b;
    }
}
