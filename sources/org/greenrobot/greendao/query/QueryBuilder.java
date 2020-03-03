package org.greenrobot.greendao.query;

import android.database.sqlite.SQLiteDatabase;
import android.support.media.ExifInterface;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;
import miuipub.reflect.Field;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.rx.RxQuery;

public class QueryBuilder<T> {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f3538a;
    public static boolean b;
    private final WhereCollector<T> c;
    private StringBuilder d;
    private final List<Object> e;
    private final List<Join<T, ?>> f;
    private final AbstractDao<T, ?> g;
    private final String h;
    private Integer i;
    private Integer j;
    private boolean k;
    private String l;

    public static <T2> QueryBuilder<T2> a(AbstractDao<T2, ?> abstractDao) {
        return new QueryBuilder<>(abstractDao);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao) {
        this(abstractDao, ExifInterface.GPS_DIRECTION_TRUE);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao, String str) {
        this.g = abstractDao;
        this.h = str;
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.c = new WhereCollector<>(abstractDao, str);
        this.l = " COLLATE NOCASE";
    }

    private void p() {
        if (this.d == null) {
            this.d = new StringBuilder();
        } else if (this.d.length() > 0) {
            this.d.append(",");
        }
    }

    public QueryBuilder<T> a() {
        this.k = true;
        return this;
    }

    public QueryBuilder<T> b() {
        if (this.g.getDatabase().g() instanceof SQLiteDatabase) {
            this.l = " COLLATE LOCALIZED";
        }
        return this;
    }

    public QueryBuilder<T> a(String str) {
        if (this.g.getDatabase().g() instanceof SQLiteDatabase) {
            if (str != null && !str.startsWith(" ")) {
                str = " " + str;
            }
            this.l = str;
        }
        return this;
    }

    public QueryBuilder<T> a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.c.a(whereCondition, whereConditionArr);
        return this;
    }

    public QueryBuilder<T> a(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.c.a(b(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }

    public WhereCondition b(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.c.a(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public WhereCondition c(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.c.a(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public <J> Join<T, J> a(Class<J> cls, Property property) {
        return a(this.g.getPkProperty(), cls, property);
    }

    public <J> Join<T, J> a(Property property, Class<J> cls) {
        AbstractDao<?, ?> dao = this.g.getSession().getDao(cls);
        return a(this.h, property, dao, dao.getPkProperty());
    }

    public <J> Join<T, J> a(Property property, Class<J> cls, Property property2) {
        return a(this.h, property, this.g.getSession().getDao(cls), property2);
    }

    public <J> Join<T, J> a(Join<?, T> join, Property property, Class<J> cls, Property property2) {
        return a(join.e, property, this.g.getSession().getDao(cls), property2);
    }

    private <J> Join<T, J> a(String str, Property property, AbstractDao<J, ?> abstractDao, Property property2) {
        Join join = new Join(str, property, abstractDao, property2, Field.f + (this.f.size() + 1));
        this.f.add(join);
        return join;
    }

    public QueryBuilder<T> a(Property... propertyArr) {
        a(" ASC", propertyArr);
        return this;
    }

    public QueryBuilder<T> b(Property... propertyArr) {
        a(" DESC", propertyArr);
        return this;
    }

    private void a(String str, Property... propertyArr) {
        for (Property property : propertyArr) {
            p();
            a(this.d, property);
            if (String.class.equals(property.b) && this.l != null) {
                this.d.append(this.l);
            }
            this.d.append(str);
        }
    }

    public QueryBuilder<T> a(Property property, String str) {
        p();
        a(this.d, property).append(' ');
        this.d.append(str);
        return this;
    }

    public QueryBuilder<T> b(String str) {
        p();
        this.d.append(str);
        return this;
    }

    /* access modifiers changed from: protected */
    public StringBuilder a(StringBuilder sb, Property property) {
        this.c.a(property);
        sb.append(this.h);
        sb.append('.');
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(property.e);
        sb.append(Operators.SINGLE_QUOTE);
        return sb;
    }

    public QueryBuilder<T> a(int i2) {
        this.i = Integer.valueOf(i2);
        return this;
    }

    public QueryBuilder<T> b(int i2) {
        this.j = Integer.valueOf(i2);
        return this;
    }

    public Query<T> c() {
        StringBuilder q = q();
        int a2 = a(q);
        int b2 = b(q);
        String sb = q.toString();
        c(sb);
        return Query.a(this.g, sb, this.e.toArray(), a2, b2);
    }

    public CursorQuery d() {
        StringBuilder q = q();
        int a2 = a(q);
        int b2 = b(q);
        String sb = q.toString();
        c(sb);
        return CursorQuery.a(this.g, sb, this.e.toArray(), a2, b2);
    }

    private StringBuilder q() {
        StringBuilder sb = new StringBuilder(SqlUtils.a(this.g.getTablename(), this.h, this.g.getAllColumns(), this.k));
        a(sb, this.h);
        if (this.d != null && this.d.length() > 0) {
            sb.append(" ORDER BY ");
            sb.append(this.d);
        }
        return sb;
    }

    private int a(StringBuilder sb) {
        if (this.i == null) {
            return -1;
        }
        sb.append(" LIMIT ?");
        this.e.add(this.i);
        return this.e.size() - 1;
    }

    private int b(StringBuilder sb) {
        if (this.j == null) {
            return -1;
        }
        if (this.i != null) {
            sb.append(" OFFSET ?");
            this.e.add(this.j);
            return this.e.size() - 1;
        }
        throw new IllegalStateException("Offset cannot be set without limit");
    }

    public DeleteQuery<T> e() {
        if (this.f.isEmpty()) {
            String tablename = this.g.getTablename();
            StringBuilder sb = new StringBuilder(SqlUtils.a(tablename, (String[]) null));
            a(sb, this.h);
            String replace = sb.toString().replace(this.h + ".\"", '\"' + tablename + "\".\"");
            c(replace);
            return DeleteQuery.a(this.g, replace, this.e.toArray());
        }
        throw new DaoException("JOINs are not supported for DELETE queries");
    }

    public CountQuery<T> f() {
        StringBuilder sb = new StringBuilder(SqlUtils.a(this.g.getTablename(), this.h));
        a(sb, this.h);
        String sb2 = sb.toString();
        c(sb2);
        return CountQuery.a(this.g, sb2, this.e.toArray());
    }

    private void c(String str) {
        if (f3538a) {
            DaoLog.b("Built SQL for query: " + str);
        }
        if (b) {
            DaoLog.b("Values for query: " + this.e);
        }
    }

    private void a(StringBuilder sb, String str) {
        this.e.clear();
        for (Join next : this.f) {
            sb.append(" JOIN ");
            sb.append(next.b.getTablename());
            sb.append(' ');
            sb.append(next.e);
            sb.append(" ON ");
            SqlUtils.a(sb, next.f3535a, next.c).append('=');
            SqlUtils.a(sb, next.e, next.d);
        }
        boolean z = !this.c.a();
        if (z) {
            sb.append(" WHERE ");
            this.c.a(sb, str, this.e);
        }
        for (Join next2 : this.f) {
            if (!next2.f.a()) {
                if (!z) {
                    sb.append(" WHERE ");
                    z = true;
                } else {
                    sb.append(" AND ");
                }
                next2.f.a(sb, next2.e, this.e);
            }
        }
    }

    public List<T> g() {
        return c().c();
    }

    @Experimental
    public RxQuery<T> h() {
        return c().j();
    }

    @Experimental
    public RxQuery<T> i() {
        return c().i();
    }

    public LazyList<T> j() {
        return c().d();
    }

    public LazyList<T> k() {
        return c().e();
    }

    public CloseableListIterator<T> l() {
        return c().f();
    }

    public T m() {
        return c().g();
    }

    public T n() {
        return c().h();
    }

    public long o() {
        return f().c();
    }
}
