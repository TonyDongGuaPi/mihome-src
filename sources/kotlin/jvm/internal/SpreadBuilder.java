package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SpreadBuilder {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Object> f2834a;

    public SpreadBuilder(int i) {
        this.f2834a = new ArrayList<>(i);
    }

    public void a(Object obj) {
        if (obj != null) {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    this.f2834a.ensureCapacity(this.f2834a.size() + objArr.length);
                    for (Object add : objArr) {
                        this.f2834a.add(add);
                    }
                }
            } else if (obj instanceof Collection) {
                this.f2834a.addAll((Collection) obj);
            } else if (obj instanceof Iterable) {
                for (Object add2 : (Iterable) obj) {
                    this.f2834a.add(add2);
                }
            } else if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                while (it.hasNext()) {
                    this.f2834a.add(it.next());
                }
            } else {
                throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
            }
        }
    }

    public int a() {
        return this.f2834a.size();
    }

    public void b(Object obj) {
        this.f2834a.add(obj);
    }

    public Object[] a(Object[] objArr) {
        return this.f2834a.toArray(objArr);
    }
}
