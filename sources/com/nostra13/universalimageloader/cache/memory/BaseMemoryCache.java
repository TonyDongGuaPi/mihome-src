package com.nostra13.universalimageloader.cache.memory;

import android.graphics.Bitmap;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class BaseMemoryCache implements MemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Reference<Bitmap>> f8445a = Collections.synchronizedMap(new HashMap());

    /* access modifiers changed from: protected */
    public abstract Reference<Bitmap> a(Bitmap bitmap);

    public Bitmap a(String str) {
        Reference reference = this.f8445a.get(str);
        if (reference != null) {
            return (Bitmap) reference.get();
        }
        return null;
    }

    public boolean a(String str, Bitmap bitmap) {
        this.f8445a.put(str, a(bitmap));
        return true;
    }

    public Bitmap b(String str) {
        Reference remove = this.f8445a.remove(str);
        if (remove == null) {
            return null;
        }
        return (Bitmap) remove.get();
    }

    public Collection<String> a() {
        HashSet hashSet;
        synchronized (this.f8445a) {
            hashSet = new HashSet(this.f8445a.keySet());
        }
        return hashSet;
    }

    public void b() {
        this.f8445a.clear();
    }
}
