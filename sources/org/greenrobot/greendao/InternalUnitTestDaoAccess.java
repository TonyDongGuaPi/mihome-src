package org.greenrobot.greendao;

import android.database.Cursor;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScope;
import org.greenrobot.greendao.internal.DaoConfig;

public class InternalUnitTestDaoAccess<T, K> {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractDao<T, K> f3514a;

    public InternalUnitTestDaoAccess(Database database, Class<AbstractDao<T, K>> cls, IdentityScope<?, ?> identityScope) throws Exception {
        DaoConfig daoConfig = new DaoConfig(database, cls);
        daoConfig.a(identityScope);
        this.f3514a = cls.getConstructor(new Class[]{DaoConfig.class}).newInstance(new Object[]{daoConfig});
    }

    public K a(T t) {
        return this.f3514a.getKey(t);
    }

    public Property[] a() {
        return this.f3514a.getProperties();
    }

    public boolean b() {
        return this.f3514a.isEntityUpdateable();
    }

    public T a(Cursor cursor, int i) {
        return this.f3514a.readEntity(cursor, i);
    }

    public K b(Cursor cursor, int i) {
        return this.f3514a.readKey(cursor, i);
    }

    public AbstractDao<T, K> c() {
        return this.f3514a;
    }
}
