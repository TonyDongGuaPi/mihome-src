package org.greenrobot.greendao.query;

import android.database.Cursor;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;

public class CursorQuery<T> extends AbstractQueryWithLimit<T> {
    private final QueryData<T> h;

    public /* bridge */ /* synthetic */ AbstractQueryWithLimit a(int i, Boolean bool) {
        return super.a(i, bool);
    }

    public /* bridge */ /* synthetic */ AbstractQueryWithLimit a(int i, Date date) {
        return super.a(i, date);
    }

    public /* bridge */ /* synthetic */ void a(int i) {
        super.a(i);
    }

    public /* bridge */ /* synthetic */ AbstractQueryWithLimit b(int i, Object obj) {
        return super.a(i, obj);
    }

    public /* bridge */ /* synthetic */ void b(int i) {
        super.b(i);
    }

    private static final class QueryData<T2> extends AbstractQueryData<T2, CursorQuery<T2>> {
        private final int e;
        private final int f;

        QueryData(AbstractDao abstractDao, String str, String[] strArr, int i, int i2) {
            super(abstractDao, str, strArr);
            this.e = i;
            this.f = i2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public CursorQuery<T2> b() {
            return new CursorQuery(this, this.b, this.f3534a, (String[]) this.c.clone(), this.e, this.f);
        }
    }

    public static <T2> CursorQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return a(abstractDao, str, objArr, -1, -1);
    }

    static <T2> CursorQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr, int i, int i2) {
        return (CursorQuery) new QueryData(abstractDao, str, a(objArr), i, i2).a();
    }

    private CursorQuery(QueryData<T> queryData, AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr, i, i2);
        this.h = queryData;
    }

    public CursorQuery b() {
        return (CursorQuery) this.h.a(this);
    }

    public Cursor c() {
        a();
        return this.f3533a.getDatabase().a(this.c, this.d);
    }
}
