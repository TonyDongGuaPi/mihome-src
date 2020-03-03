package org.aspectj.lang.reflect;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import org.aspectj.internal.lang.reflect.AjTypeImpl;

public class AjTypeSystem {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Class, WeakReference<AjType>> f3456a = Collections.synchronizedMap(new WeakHashMap());

    public static <T> AjType<T> a(Class<T> cls) {
        WeakReference weakReference = f3456a.get(cls);
        if (weakReference != null) {
            AjType<T> ajType = (AjType) weakReference.get();
            if (ajType != null) {
                return ajType;
            }
            AjTypeImpl ajTypeImpl = new AjTypeImpl(cls);
            f3456a.put(cls, new WeakReference(ajTypeImpl));
            return ajTypeImpl;
        }
        AjTypeImpl ajTypeImpl2 = new AjTypeImpl(cls);
        f3456a.put(cls, new WeakReference(ajTypeImpl2));
        return ajTypeImpl2;
    }
}
