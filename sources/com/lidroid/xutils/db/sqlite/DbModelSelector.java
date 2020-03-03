package com.lidroid.xutils.db.sqlite;

import android.text.TextUtils;

public class DbModelSelector {

    /* renamed from: a  reason: collision with root package name */
    private String[] f6317a;
    private String b;
    private WhereBuilder c;
    private Selector d;

    private DbModelSelector(Class<?> cls) {
        this.d = Selector.a(cls);
    }

    protected DbModelSelector(Selector selector, String str) {
        this.d = selector;
        this.b = str;
    }

    protected DbModelSelector(Selector selector, String[] strArr) {
        this.d = selector;
        this.f6317a = strArr;
    }

    public static DbModelSelector a(Class<?> cls) {
        return new DbModelSelector(cls);
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

    public DbModelSelector d(String str, String str2, Object obj) {
        this.d.d(str, str2, obj);
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
        this.f6317a = strArr;
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

    public Class<?> a() {
        return this.d.a();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT ");
        if (this.f6317a != null && this.f6317a.length > 0) {
            for (String append : this.f6317a) {
                stringBuffer.append(append);
                stringBuffer.append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } else if (!TextUtils.isEmpty(this.b)) {
            stringBuffer.append(this.b);
        } else {
            stringBuffer.append("*");
        }
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.d.b);
        if (this.d.c != null && this.d.c.b() > 0) {
            stringBuffer.append(" WHERE ");
            stringBuffer.append(this.d.c.toString());
        }
        if (!TextUtils.isEmpty(this.b)) {
            stringBuffer.append(" GROUP BY ");
            stringBuffer.append(this.b);
            if (this.c != null && this.c.b() > 0) {
                stringBuffer.append(" HAVING ");
                stringBuffer.append(this.c.toString());
            }
        }
        if (this.d.d != null) {
            for (int i = 0; i < this.d.d.size(); i++) {
                stringBuffer.append(" ORDER BY ");
                stringBuffer.append(this.d.d.get(i).toString());
            }
        }
        if (this.d.e > 0) {
            stringBuffer.append(" LIMIT ");
            stringBuffer.append(this.d.e);
            stringBuffer.append(" OFFSET ");
            stringBuffer.append(this.d.f);
        }
        return stringBuffer.toString();
    }
}
