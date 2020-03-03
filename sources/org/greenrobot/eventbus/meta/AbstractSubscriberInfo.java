package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.SubscriberMethod;
import org.greenrobot.eventbus.ThreadMode;

public abstract class AbstractSubscriberInfo implements SubscriberInfo {

    /* renamed from: a  reason: collision with root package name */
    private final Class f3498a;
    private final Class<? extends SubscriberInfo> b;
    private final boolean c;

    protected AbstractSubscriberInfo(Class cls, Class<? extends SubscriberInfo> cls2, boolean z) {
        this.f3498a = cls;
        this.b = cls2;
        this.c = z;
    }

    public Class a() {
        return this.f3498a;
    }

    public SubscriberInfo b() {
        if (this.b == null) {
            return null;
        }
        try {
            return (SubscriberInfo) this.b.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean c() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public SubscriberMethod a(String str, Class<?> cls) {
        return a(str, cls, ThreadMode.POSTING, 0, false);
    }

    /* access modifiers changed from: protected */
    public SubscriberMethod a(String str, Class<?> cls, ThreadMode threadMode) {
        return a(str, cls, threadMode, 0, false);
    }

    /* access modifiers changed from: protected */
    public SubscriberMethod a(String str, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        try {
            return new SubscriberMethod(this.f3498a.getDeclaredMethod(str, new Class[]{cls}), cls, threadMode, i, z);
        } catch (NoSuchMethodException e) {
            throw new EventBusException("Could not find subscriber method in " + this.f3498a + ". Maybe a missing ProGuard rule?", e);
        }
    }
}
