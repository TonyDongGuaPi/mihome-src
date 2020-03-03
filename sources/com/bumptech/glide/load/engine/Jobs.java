package com.bumptech.glide.load.engine;

import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class Jobs {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Key, EngineJob<?>> f4880a = new HashMap();
    private final Map<Key, EngineJob<?>> b = new HashMap();

    Jobs() {
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Map<Key, EngineJob<?>> a() {
        return Collections.unmodifiableMap(this.f4880a);
    }

    /* access modifiers changed from: package-private */
    public EngineJob<?> a(Key key, boolean z) {
        return a(z).get(key);
    }

    /* access modifiers changed from: package-private */
    public void a(Key key, EngineJob<?> engineJob) {
        a(engineJob.a()).put(key, engineJob);
    }

    /* access modifiers changed from: package-private */
    public void b(Key key, EngineJob<?> engineJob) {
        Map<Key, EngineJob<?>> a2 = a(engineJob.a());
        if (engineJob.equals(a2.get(key))) {
            a2.remove(key);
        }
    }

    private Map<Key, EngineJob<?>> a(boolean z) {
        return z ? this.b : this.f4880a;
    }
}
