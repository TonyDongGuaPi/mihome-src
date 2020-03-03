package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.ColumnUtils;
import com.lidroid.xutils.db.table.Foreign;
import com.lidroid.xutils.db.table.Table;
import com.lidroid.xutils.exception.DbException;
import java.util.List;

public class ForeignLazyLoader<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Foreign f6319a;
    private Object b;

    public ForeignLazyLoader(Foreign foreign, Object obj) {
        this.f6319a = foreign;
        this.b = ColumnUtils.a(obj);
    }

    public List<T> a() throws DbException {
        Table a2 = this.f6319a.a();
        if (a2 != null) {
            return a2.f6328a.b(Selector.a(this.f6319a.i()).a(this.f6319a.h(), "=", this.b));
        }
        return null;
    }

    public T b() throws DbException {
        Table a2 = this.f6319a.a();
        if (a2 != null) {
            return a2.f6328a.a(Selector.a(this.f6319a.i()).a(this.f6319a.h(), "=", this.b));
        }
        return null;
    }

    public void a(Object obj) {
        this.b = ColumnUtils.a(obj);
    }

    public Object c() {
        return this.b;
    }
}
