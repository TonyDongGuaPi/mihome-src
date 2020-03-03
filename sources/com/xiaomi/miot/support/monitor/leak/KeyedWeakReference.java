package com.xiaomi.miot.support.monitor.leak;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class KeyedWeakReference extends WeakReference<Object> {

    /* renamed from: a  reason: collision with root package name */
    public final String f11486a;
    public final String b;

    KeyedWeakReference(Object obj, String str, String str2, ReferenceQueue<Object> referenceQueue) {
        super(Preconditions.a(obj, "referent"), (ReferenceQueue) Preconditions.a(referenceQueue, "referenceQueue"));
        this.f11486a = (String) Preconditions.a(str, "key");
        this.b = (String) Preconditions.a(str2, "name");
    }
}
