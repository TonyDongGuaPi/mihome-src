package org.greenrobot.greendao;

import android.database.Cursor;
import java.util.List;
import org.greenrobot.greendao.internal.TableStatements;

public final class InternalQueryDaoAccess<T> {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractDao<T, ?> f3513a;

    public InternalQueryDaoAccess(AbstractDao<T, ?> abstractDao) {
        this.f3513a = abstractDao;
    }

    public T a(Cursor cursor, int i, boolean z) {
        return this.f3513a.loadCurrent(cursor, i, z);
    }

    public List<T> a(Cursor cursor) {
        return this.f3513a.loadAllAndCloseCursor(cursor);
    }

    public T b(Cursor cursor) {
        return this.f3513a.loadUniqueAndCloseCursor(cursor);
    }

    public TableStatements a() {
        return this.f3513a.getStatements();
    }

    public static <T2> TableStatements a(AbstractDao<T2, ?> abstractDao) {
        return abstractDao.getStatements();
    }
}
