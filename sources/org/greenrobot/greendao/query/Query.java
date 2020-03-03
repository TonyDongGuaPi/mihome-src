package org.greenrobot.greendao.query;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.apihint.Internal;
import org.greenrobot.greendao.rx.RxQuery;
import rx.schedulers.Schedulers;

public class Query<T> extends AbstractQueryWithLimit<T> {
    private final QueryData<T> h;
    private volatile RxQuery i;
    private volatile RxQuery j;

    public /* bridge */ /* synthetic */ void a(int i2) {
        super.a(i2);
    }

    public /* bridge */ /* synthetic */ void b(int i2) {
        super.b(i2);
    }

    private static final class QueryData<T2> extends AbstractQueryData<T2, Query<T2>> {
        private final int e;
        private final int f;

        QueryData(AbstractDao<T2, ?> abstractDao, String str, String[] strArr, int i, int i2) {
            super(abstractDao, str, strArr);
            this.e = i;
            this.f = i2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public Query<T2> b() {
            return new Query(this, this.b, this.f3534a, (String[]) this.c.clone(), this.e, this.f);
        }
    }

    public static <T2> Query<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return a(abstractDao, str, objArr, -1, -1);
    }

    static <T2> Query<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr, int i2, int i3) {
        return (Query) new QueryData(abstractDao, str, a(objArr), i2, i3).a();
    }

    private Query(QueryData<T> queryData, AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i2, int i3) {
        super(abstractDao, str, strArr, i2, i3);
        this.h = queryData;
    }

    public Query<T> b() {
        return (Query) this.h.a(this);
    }

    public List<T> c() {
        a();
        return this.b.a(this.f3533a.getDatabase().a(this.c, this.d));
    }

    public LazyList<T> d() {
        a();
        return new LazyList<>(this.b, this.f3533a.getDatabase().a(this.c, this.d), true);
    }

    public LazyList<T> e() {
        a();
        return new LazyList<>(this.b, this.f3533a.getDatabase().a(this.c, this.d), false);
    }

    public CloseableListIterator<T> f() {
        return e().g();
    }

    public T g() {
        a();
        return this.b.b(this.f3533a.getDatabase().a(this.c, this.d));
    }

    public T h() {
        T g = g();
        if (g != null) {
            return g;
        }
        throw new DaoException("No entity found for query");
    }

    /* renamed from: c */
    public Query<T> b(int i2, Object obj) {
        return (Query) super.a(i2, obj);
    }

    /* renamed from: b */
    public Query<T> a(int i2, Date date) {
        return (Query) super.a(i2, date);
    }

    /* renamed from: b */
    public Query<T> a(int i2, Boolean bool) {
        return (Query) super.a(i2, bool);
    }

    @Internal
    public RxQuery i() {
        if (this.i == null) {
            this.i = new RxQuery(this);
        }
        return this.i;
    }

    @Internal
    public RxQuery j() {
        if (this.j == null) {
            this.j = new RxQuery(this, Schedulers.io());
        }
        return this.j;
    }
}
