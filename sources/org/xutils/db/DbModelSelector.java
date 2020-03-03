package org.xutils.db;

import android.database.Cursor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.IOUtil;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public final class DbModelSelector {

    /* renamed from: a  reason: collision with root package name */
    private String[] f4238a;
    private String b;
    private WhereBuilder c;
    private Selector<?> d;

    private DbModelSelector(TableEntity<?> tableEntity) {
        this.d = Selector.a(tableEntity);
    }

    protected DbModelSelector(Selector<?> selector, String str) {
        this.d = selector;
        this.b = str;
    }

    protected DbModelSelector(Selector<?> selector, String[] strArr) {
        this.d = selector;
        this.f4238a = strArr;
    }

    static DbModelSelector a(TableEntity<?> tableEntity) {
        return new DbModelSelector(tableEntity);
    }

    public DbModelSelector a(WhereBuilder whereBuilder) {
        this.d.a(whereBuilder);
        return this;
    }

    public DbModelSelector a(String str, String str2, Object obj) {
        this.d.a(str, str2, obj);
        return this;
    }

    public DbModelSelector b(String str, String str2, Object obj) {
        this.d.b(str, str2, obj);
        return this;
    }

    public DbModelSelector b(WhereBuilder whereBuilder) {
        this.d.b(whereBuilder);
        return this;
    }

    public DbModelSelector c(String str, String str2, Object obj) {
        this.d.c(str, str2, obj);
        return this;
    }

    public DbModelSelector c(WhereBuilder whereBuilder) {
        this.d.c(whereBuilder);
        return this;
    }

    public DbModelSelector a(String str) {
        this.d.a(str);
        return this;
    }

    public DbModelSelector b(String str) {
        this.b = str;
        return this;
    }

    public DbModelSelector d(WhereBuilder whereBuilder) {
        this.c = whereBuilder;
        return this;
    }

    public DbModelSelector a(String... strArr) {
        this.f4238a = strArr;
        return this;
    }

    public DbModelSelector c(String str) {
        this.d.c(str);
        return this;
    }

    public DbModelSelector a(String str, boolean z) {
        this.d.a(str, z);
        return this;
    }

    public DbModelSelector a(int i) {
        this.d.a(i);
        return this;
    }

    public DbModelSelector b(int i) {
        this.d.b(i);
        return this;
    }

    public TableEntity<?> a() {
        return this.d.a();
    }

    public DbModel b() throws DbException {
        TableEntity<?> a2 = this.d.a();
        if (!a2.b()) {
            return null;
        }
        a(1);
        Cursor c2 = a2.c().c(toString());
        if (c2 != null) {
            try {
                if (c2.moveToNext()) {
                    DbModel a3 = CursorUtils.a(c2);
                    IOUtil.a(c2);
                    return a3;
                }
                IOUtil.a(c2);
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return null;
    }

    public List<DbModel> c() throws DbException {
        TableEntity<?> a2 = this.d.a();
        ArrayList arrayList = null;
        if (!a2.b()) {
            return null;
        }
        Cursor c2 = a2.c().c(toString());
        if (c2 != null) {
            try {
                arrayList = new ArrayList();
                while (c2.moveToNext()) {
                    arrayList.add(CursorUtils.a(c2));
                }
                IOUtil.a(c2);
            } catch (Throwable th) {
                IOUtil.a(c2);
                throw th;
            }
        }
        return arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (this.f4238a != null && this.f4238a.length > 0) {
            for (String append : this.f4238a) {
                sb.append(append);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        } else if (!TextUtils.isEmpty(this.b)) {
            sb.append(this.b);
        } else {
            sb.append("*");
        }
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(this.d.a().d());
        sb.append("\"");
        WhereBuilder b2 = this.d.b();
        if (b2 != null && b2.b() > 0) {
            sb.append(" WHERE ");
            sb.append(b2.toString());
        }
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(" GROUP BY ");
            sb.append("\"");
            sb.append(this.b);
            sb.append("\"");
            if (this.c != null && this.c.b() > 0) {
                sb.append(" HAVING ");
                sb.append(this.c.toString());
            }
        }
        List<Selector.OrderBy> c2 = this.d.c();
        if (c2 != null && c2.size() > 0) {
            for (int i = 0; i < c2.size(); i++) {
                sb.append(" ORDER BY ");
                sb.append(c2.get(i).toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.d.d() > 0) {
            sb.append(" LIMIT ");
            sb.append(this.d.d());
            sb.append(" OFFSET ");
            sb.append(this.d.e());
        }
        return sb.toString();
    }
}
