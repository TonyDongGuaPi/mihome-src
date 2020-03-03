package org.greenrobot.greendao.test;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

public abstract class AbstractDaoSessionTest<T extends AbstractDaoMaster, S extends AbstractDaoSession> extends DbTest {

    /* renamed from: a  reason: collision with root package name */
    protected T f3571a;
    protected S b;
    private final Class<T> c;

    public AbstractDaoSessionTest(Class<T> cls) {
        this(cls, true);
    }

    public AbstractDaoSessionTest(Class<T> cls, boolean z) {
        super(z);
        this.c = cls;
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        try {
            this.f3571a = (AbstractDaoMaster) this.c.getConstructor(new Class[]{Database.class}).newInstance(new Object[]{this.j});
            this.c.getMethod("createAllTables", new Class[]{Database.class, Boolean.TYPE}).invoke((Object) null, new Object[]{this.j, false});
            this.b = this.f3571a.newSession();
        } catch (Exception e) {
            throw new RuntimeException("Could not prepare DAO session test", e);
        }
    }
}
