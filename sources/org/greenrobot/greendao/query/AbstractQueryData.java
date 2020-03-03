package org.greenrobot.greendao.query;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.AbstractQuery;

abstract class AbstractQueryData<T, Q extends AbstractQuery<T>> {

    /* renamed from: a  reason: collision with root package name */
    final String f3534a;
    final AbstractDao<T, ?> b;
    final String[] c;
    final Map<Long, WeakReference<Q>> d = new HashMap();

    /* access modifiers changed from: protected */
    public abstract Q b();

    AbstractQueryData(AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        this.b = abstractDao;
        this.f3534a = str;
        this.c = strArr;
    }

    /* access modifiers changed from: package-private */
    public Q a(Q q) {
        if (Thread.currentThread() != q.e) {
            return a();
        }
        System.arraycopy(this.c, 0, q.d, 0, this.c.length);
        return q;
    }

    /* access modifiers changed from: package-private */
    public Q a() {
        Q q;
        long id = Thread.currentThread().getId();
        synchronized (this.d) {
            WeakReference weakReference = this.d.get(Long.valueOf(id));
            q = weakReference != null ? (AbstractQuery) weakReference.get() : null;
            if (q == null) {
                c();
                q = b();
                this.d.put(Long.valueOf(id), new WeakReference(q));
            } else {
                System.arraycopy(this.c, 0, q.d, 0, this.c.length);
            }
        }
        return q;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        synchronized (this.d) {
            Iterator<Map.Entry<Long, WeakReference<Q>>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                if (((WeakReference) it.next().getValue()).get() == null) {
                    it.remove();
                }
            }
        }
    }
}
