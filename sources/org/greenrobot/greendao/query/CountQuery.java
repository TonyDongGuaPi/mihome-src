package org.greenrobot.greendao.query;

import android.database.Cursor;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;

public class CountQuery<T> extends AbstractQuery<T> {
    private final QueryData<T> f;

    public /* bridge */ /* synthetic */ AbstractQuery a(int i, Object obj) {
        return super.a(i, obj);
    }

    private static final class QueryData<T2> extends AbstractQueryData<T2, CountQuery<T2>> {
        private QueryData(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public CountQuery<T2> b() {
            return new CountQuery(this, this.b, this.f3534a, (String[]) this.c.clone());
        }
    }

    static <T2> CountQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return (CountQuery) new QueryData(abstractDao, str, a(objArr)).a();
    }

    private CountQuery(QueryData<T> queryData, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.f = queryData;
    }

    public CountQuery<T> b() {
        return (CountQuery) this.f.a(this);
    }

    public long c() {
        a();
        Cursor a2 = this.f3533a.getDatabase().a(this.c, this.d);
        try {
            if (!a2.moveToNext()) {
                throw new DaoException("No result for count");
            } else if (!a2.isLast()) {
                throw new DaoException("Unexpected row count: " + a2.getCount());
            } else if (a2.getColumnCount() == 1) {
                return a2.getLong(0);
            } else {
                throw new DaoException("Unexpected column count: " + a2.getColumnCount());
            }
        } finally {
            a2.close();
        }
    }
}
