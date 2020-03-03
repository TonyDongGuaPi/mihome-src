package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.SubscriberMethod;

public class SimpleSubscriberInfo extends AbstractSubscriberInfo {

    /* renamed from: a  reason: collision with root package name */
    private final SubscriberMethodInfo[] f3499a;

    public SimpleSubscriberInfo(Class cls, boolean z, SubscriberMethodInfo[] subscriberMethodInfoArr) {
        super(cls, (Class<? extends SubscriberInfo>) null, z);
        this.f3499a = subscriberMethodInfoArr;
    }

    public synchronized SubscriberMethod[] d() {
        SubscriberMethod[] subscriberMethodArr;
        int length = this.f3499a.length;
        subscriberMethodArr = new SubscriberMethod[length];
        for (int i = 0; i < length; i++) {
            SubscriberMethodInfo subscriberMethodInfo = this.f3499a[i];
            subscriberMethodArr[i] = a(subscriberMethodInfo.f3500a, subscriberMethodInfo.c, subscriberMethodInfo.b, subscriberMethodInfo.d, subscriberMethodInfo.e);
        }
        return subscriberMethodArr;
    }
}
