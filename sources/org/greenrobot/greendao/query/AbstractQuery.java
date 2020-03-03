package org.greenrobot.greendao.query;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.InternalQueryDaoAccess;

abstract class AbstractQuery<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final AbstractDao<T, ?> f3533a;
    protected final InternalQueryDaoAccess<T> b;
    protected final String c;
    protected final String[] d;
    protected final Thread e = Thread.currentThread();

    protected static String[] a(Object[] objArr) {
        int length = objArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            if (obj != null) {
                strArr[i] = obj.toString();
            } else {
                strArr[i] = null;
            }
        }
        return strArr;
    }

    protected AbstractQuery(AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        this.f3533a = abstractDao;
        this.b = new InternalQueryDaoAccess<>(abstractDao);
        this.c = str;
        this.d = strArr;
    }

    public AbstractQuery<T> a(int i, Object obj) {
        a();
        if (obj != null) {
            this.d[i] = obj.toString();
        } else {
            this.d[i] = null;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (Thread.currentThread() != this.e) {
            throw new DaoException("Method may be called only in owner thread, use forCurrentThread to get an instance for this thread");
        }
    }
}
