package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.ThreadMode;

public class SubscriberMethodInfo {

    /* renamed from: a  reason: collision with root package name */
    final String f3500a;
    final ThreadMode b;
    final Class<?> c;
    final int d;
    final boolean e;

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.f3500a = str;
        this.b = threadMode;
        this.c = cls;
        this.d = i;
        this.e = z;
    }

    public SubscriberMethodInfo(String str, Class<?> cls) {
        this(str, cls, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode) {
        this(str, cls, threadMode, 0, false);
    }
}
