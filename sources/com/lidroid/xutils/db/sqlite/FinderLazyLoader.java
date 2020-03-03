package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.ColumnUtils;
import com.lidroid.xutils.db.table.Finder;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.exception.DbException;
import java.util.List;

public class FinderLazyLoader<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Finder f6318a;
    private final Object b;

    public FinderLazyLoader(Finder finder, Object obj) {
        this.f6318a = finder;
        this.b = ColumnUtils.a(obj);
    }

    public List<T> a() throws DbException {
        Table a2 = this.f6318a.a();
        if (a2 != null) {
            return a2.f6328a.b(Selector.a(this.f6318a.h()).a(this.f6318a.i(), "=", this.b));
        }
        return null;
    }

    public T b() throws DbException {
        Table a2 = this.f6318a.a();
        if (a2 != null) {
            return a2.f6328a.a(Selector.a(this.f6318a.h()).a(this.f6318a.i(), "=", this.b));
        }
        return null;
    }
}
