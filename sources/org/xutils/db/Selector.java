package org.xutils.db;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.IOUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public final class Selector<T> {

    /* renamed from: a  reason: collision with root package name */
    private final TableEntity<T> f4239a;
    private WhereBuilder b;
    private List<OrderBy> c;
    private int d = 0;
    private int e = 0;

    private Selector(TableEntity<T> tableEntity) {
        this.f4239a = tableEntity;
    }

    static <T> Selector<T> a(TableEntity<T> tableEntity) {
        return new Selector<>(tableEntity);
    }

    public Selector<T> a(WhereBuilder whereBuilder) {
        this.b = whereBuilder;
        return this;
    }

    public Selector<T> a(String str, String str2, Object obj) {
        this.b = WhereBuilder.a(str, str2, obj);
        return this;
    }

    public Selector<T> b(String str, String str2, Object obj) {
        this.b.b(str, str2, obj);
        return this;
    }

    public Selector<T> b(WhereBuilder whereBuilder) {
        this.b.a(whereBuilder);
        return this;
    }

    public Selector<T> c(String str, String str2, Object obj) {
        this.b.c(str, str2, obj);
        return this;
    }

    public Selector c(WhereBuilder whereBuilder) {
        this.b.b(whereBuilder);
        return this;
    }

    public Selector<T> a(String str) {
        if (this.b == null) {
            this.b = WhereBuilder.a();
        }
        this.b.a(str);
        return this;
    }

    public DbModelSelector b(String str) {
        return new DbModelSelector((Selector<?>) this, str);
    }

    public DbModelSelector a(String... strArr) {
        return new DbModelSelector((Selector<?>) this, strArr);
    }

    public Selector<T> c(String str) {
        if (this.c == null) {
            this.c = new ArrayList(5);
        }
        this.c.add(new OrderBy(str));
        return this;
    }

    public Selector<T> a(String str, boolean z) {
        if (this.c == null) {
            this.c = new ArrayList(5);
        }
        this.c.add(new OrderBy(str, z));
        return this;
    }

    public Selector<T> a(int i) {
        this.d = i;
        return this;
    }

    public Selector<T> b(int i) {
        this.e = i;
        return this;
    }

    public TableEntity<T> a() {
        return this.f4239a;
    }

    public WhereBuilder b() {
        return this.b;
    }

    public List<OrderBy> c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public T f() throws DbException {
        if (!this.f4239a.b()) {
            return null;
        }
        a(1);
        Cursor c2 = this.f4239a.c().c(toString());
        if (c2 != null) {
            try {
                if (c2.moveToNext()) {
                    T a2 = CursorUtils.a(this.f4239a, c2);
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

    public List<T> g() throws DbException {
        ArrayList arrayList = null;
        if (!this.f4239a.b()) {
            return null;
        }
        Cursor c2 = this.f4239a.c().c(toString());
        if (c2 != null) {
            try {
                arrayList = new ArrayList();
                while (c2.moveToNext()) {
                    arrayList.add(CursorUtils.a(this.f4239a, c2));
                }
                IOUtil.a(c2);
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return arrayList;
    }

    public long h() throws DbException {
        if (!this.f4239a.b()) {
            return 0;
        }
        DbModel b2 = a("count(\"" + this.f4239a.g().a() + "\") as count").b();
        if (b2 != null) {
            return b2.f("count");
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(this.f4239a.d());
        sb.append("\"");
        if (this.b != null && this.b.b() > 0) {
            sb.append(" WHERE ");
            sb.append(this.b.toString());
        }
        if (this.c != null && this.c.size() > 0) {
            sb.append(" ORDER BY ");
            for (OrderBy orderBy : this.c) {
                sb.append(orderBy.toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.d > 0) {
            sb.append(" LIMIT ");
            sb.append(this.d);
            sb.append(" OFFSET ");
            sb.append(this.e);
        }
        return sb.toString();
    }

    public static class OrderBy {

        /* renamed from: a  reason: collision with root package name */
        private String f4240a;
        private boolean b;

        public OrderBy(String str) {
            this.f4240a = str;
        }

        public OrderBy(String str, boolean z) {
            this.f4240a = str;
            this.b = z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(this.f4240a);
            sb.append("\"");
            sb.append(this.b ? " DESC" : " ASC");
            return sb.toString();
        }
    }
}
