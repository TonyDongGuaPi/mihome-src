package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.TableUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

public class Selector {

    /* renamed from: a  reason: collision with root package name */
    protected Class<?> f6320a;
    protected String b;
    protected WhereBuilder c;
    protected List<OrderBy> d;
    protected int e = 0;
    protected int f = 0;

    private Selector(Class<?> cls) {
        this.f6320a = cls;
        this.b = TableUtils.a(cls);
    }

    public static Selector a(Class<?> cls) {
        return new Selector(cls);
    }

    public Selector a(WhereBuilder whereBuilder) {
        this.c = whereBuilder;
        return this;
    }

    public Selector a(String str, String str2, Object obj) {
        this.c = WhereBuilder.a(str, str2, obj);
        return this;
    }

    public Selector b(String str, String str2, Object obj) {
        this.c.b(str, str2, obj);
        return this;
    }

    public Selector b(WhereBuilder whereBuilder) {
        WhereBuilder whereBuilder2 = this.c;
        whereBuilder2.a("AND (" + whereBuilder.toString() + Operators.BRACKET_END_STR);
        return this;
    }

    public Selector c(String str, String str2, Object obj) {
        this.c.c(str, str2, obj);
        return this;
    }

    public Selector c(WhereBuilder whereBuilder) {
        WhereBuilder whereBuilder2 = this.c;
        whereBuilder2.a("OR (" + whereBuilder.toString() + Operators.BRACKET_END_STR);
        return this;
    }

    public Selector a(String str) {
        if (this.c == null) {
            this.c = WhereBuilder.a();
        }
        this.c.a(str);
        return this;
    }

    public Selector d(String str, String str2, Object obj) {
        if (this.c == null) {
            this.c = WhereBuilder.a();
        }
        this.c.d(str, str2, obj);
        return this;
    }

    public DbModelSelector b(String str) {
        return new DbModelSelector(this, str);
    }

    public DbModelSelector a(String... strArr) {
        return new DbModelSelector(this, strArr);
    }

    public Selector c(String str) {
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(new OrderBy(str));
        return this;
    }

    public Selector a(String str, boolean z) {
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(new OrderBy(str, z));
        return this;
    }

    public Selector a(int i) {
        this.e = i;
        return this;
    }

    public Selector b(int i) {
        this.f = i;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append(this.b);
        if (this.c != null && this.c.b() > 0) {
            sb.append(" WHERE ");
            sb.append(this.c.toString());
        }
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); i++) {
                sb.append(" ORDER BY ");
                sb.append(this.d.get(i).toString());
            }
        }
        if (this.e > 0) {
            sb.append(" LIMIT ");
            sb.append(this.e);
            sb.append(" OFFSET ");
            sb.append(this.f);
        }
        return sb.toString();
    }

    public Class<?> a() {
        return this.f6320a;
    }

    protected class OrderBy {
        private String b;
        private boolean c;

        public OrderBy(String str) {
            this.b = str;
        }

        public OrderBy(String str, boolean z) {
            this.b = str;
            this.c = z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(String.valueOf(this.b));
            sb.append(this.c ? " DESC" : " ASC");
            return sb.toString();
        }
    }
}
