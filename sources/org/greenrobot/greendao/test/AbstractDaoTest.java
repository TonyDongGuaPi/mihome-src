package org.greenrobot.greendao.test;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.InternalUnitTestDaoAccess;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScope;

public abstract class AbstractDaoTest<D extends AbstractDao<T, K>, T, K> extends DbTest {

    /* renamed from: a  reason: collision with root package name */
    protected final Class<D> f3572a;
    protected D b;
    protected InternalUnitTestDaoAccess<T, K> c;
    protected Property d;
    protected IdentityScope<K, T> e;

    public AbstractDaoTest(Class<D> cls) {
        this(cls, true);
    }

    public AbstractDaoTest(Class<D> cls, boolean z) {
        super(z);
        this.f3572a = cls;
    }

    public void a(IdentityScope<K, T> identityScope) {
        this.e = identityScope;
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        try {
            a();
            this.c = new InternalUnitTestDaoAccess<>(this.j, this.f3572a, this.e);
            this.b = this.c.c();
        } catch (Exception e2) {
            throw new RuntimeException("Could not prepare DAO Test", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void a() throws Exception {
        try {
            this.f3572a.getMethod("createTable", new Class[]{Database.class, Boolean.TYPE}).invoke((Object) null, new Object[]{this.j, false});
        } catch (NoSuchMethodException unused) {
            DaoLog.c("No createTable method");
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.e != null) {
            this.e.a();
            DaoLog.b("Identity scope cleared");
            return;
        }
        DaoLog.b("No identity scope to clear");
    }

    /* access modifiers changed from: protected */
    public void c() {
        a(this.b.getTablename());
    }
}
