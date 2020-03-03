package org.greenrobot.greendao.test;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoLog;

public abstract class AbstractDaoTestLongPk<D extends AbstractDao<T, Long>, T> extends AbstractDaoTestSinglePk<D, T, Long> {
    public AbstractDaoTestLongPk(Class<D> cls) {
        super(cls);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Long f() {
        return Long.valueOf(this.h.nextLong());
    }

    public void e() {
        if (this.c.b()) {
            Object a2 = a(null);
            if (a2 != null) {
                Object a3 = a(null);
                this.b.insert(a2);
                this.b.insert(a3);
                Long l = (Long) this.c.a(a2);
                assertNotNull(l);
                Long l2 = (Long) this.c.a(a3);
                assertNotNull(l2);
                assertFalse(l.equals(l2));
                assertNotNull(this.b.load(l));
                assertNotNull(this.b.load(l2));
                return;
            }
            DaoLog.b("Skipping testAssignPk for " + this.f3572a + " (createEntity returned null for null key)");
            return;
        }
        DaoLog.b("Skipping testAssignPk for not updateable " + this.f3572a);
    }
}
