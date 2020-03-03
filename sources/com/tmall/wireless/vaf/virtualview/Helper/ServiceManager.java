package com.tmall.wireless.vaf.virtualview.Helper;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.util.Map;

public class ServiceManager {

    /* renamed from: a  reason: collision with root package name */
    private Map<Class<?>, Object> f9370a = new ArrayMap();

    public <S> void a(@NonNull Class<S> cls, @NonNull S s) {
        this.f9370a.put(cls, cls.cast(s));
    }

    public <S> S a(@NonNull Class<S> cls) {
        Object obj = this.f9370a.get(cls);
        if (obj == null) {
            return null;
        }
        return cls.cast(obj);
    }
}
