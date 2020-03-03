package org.greenrobot.greendao.query;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

public class DeleteQuery<T> extends AbstractQuery<T> {
    private final QueryData<T> f;

    public /* bridge */ /* synthetic */ AbstractQuery a(int i, Object obj) {
        return super.a(i, obj);
    }

    private static final class QueryData<T2> extends AbstractQueryData<T2, DeleteQuery<T2>> {
        private QueryData(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public DeleteQuery<T2> b() {
            return new DeleteQuery(this, this.b, this.f3534a, (String[]) this.c.clone());
        }
    }

    static <T2> DeleteQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return (DeleteQuery) new QueryData(abstractDao, str, a(objArr)).a();
    }

    private DeleteQuery(QueryData<T> queryData, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.f = queryData;
    }

    public DeleteQuery<T> b() {
        return (DeleteQuery) this.f.a(this);
    }

    public void c() {
        a();
        Database database = this.f3533a.getDatabase();
        if (database.e()) {
            this.f3533a.getDatabase().a(this.c, (Object[]) this.d);
            return;
        }
        database.a();
        try {
            this.f3533a.getDatabase().a(this.c, (Object[]) this.d);
            database.d();
        } finally {
            database.b();
        }
    }
}
