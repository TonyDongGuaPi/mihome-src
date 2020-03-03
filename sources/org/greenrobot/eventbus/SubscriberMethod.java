package org.greenrobot.eventbus;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;

public class SubscriberMethod {

    /* renamed from: a  reason: collision with root package name */
    final Method f3494a;
    final ThreadMode b;
    final Class<?> c;
    final int d;
    final boolean e;
    String f;

    public SubscriberMethod(Method method, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.f3494a = method;
        this.b = threadMode;
        this.c = cls;
        this.d = i;
        this.e = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        a();
        SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
        subscriberMethod.a();
        return this.f.equals(subscriberMethod.f);
    }

    private synchronized void a() {
        if (this.f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f3494a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f3494a.getName());
            sb.append(Operators.BRACKET_START);
            sb.append(this.c.getName());
            this.f = sb.toString();
        }
    }

    public int hashCode() {
        return this.f3494a.hashCode();
    }
}
