package org.greenrobot.greendao.query;

import java.util.Date;
import org.greenrobot.greendao.AbstractDao;

abstract class AbstractQueryWithLimit<T> extends AbstractQuery<T> {
    protected final int f;
    protected final int g;

    protected AbstractQueryWithLimit(AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr);
        this.f = i;
        this.g = i2;
    }

    /* renamed from: b */
    public AbstractQueryWithLimit<T> a(int i, Object obj) {
        if (i < 0 || (i != this.f && i != this.g)) {
            return (AbstractQueryWithLimit) super.a(i, obj);
        }
        throw new IllegalArgumentException("Illegal parameter index: " + i);
    }

    public AbstractQueryWithLimit<T> a(int i, Date date) {
        return a(i, date != null ? Long.valueOf(date.getTime()) : null);
    }

    public AbstractQueryWithLimit<T> a(int i, Boolean bool) {
        return a(i, bool != null ? Integer.valueOf(bool.booleanValue() ? 1 : 0) : null);
    }

    public void a(int i) {
        a();
        if (this.f != -1) {
            this.d[this.f] = Integer.toString(i);
            return;
        }
        throw new IllegalStateException("Limit must be set with QueryBuilder before it can be used here");
    }

    public void b(int i) {
        a();
        if (this.g != -1) {
            this.d[this.g] = Integer.toString(i);
            return;
        }
        throw new IllegalStateException("Offset must be set with QueryBuilder before it can be used here");
    }
}
