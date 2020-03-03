package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class FuzzyKeyMemoryCache implements MemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final MemoryCache f8448a;
    private final Comparator<String> b;

    public FuzzyKeyMemoryCache(MemoryCache memoryCache, Comparator<String> comparator) {
        this.f8448a = memoryCache;
        this.b = comparator;
    }

    public boolean a(String str, Bitmap bitmap) {
        synchronized (this.f8448a) {
            String str2 = null;
            Iterator<String> it = this.f8448a.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (this.b.compare(str, next) == 0) {
                    str2 = next;
                    break;
                }
            }
            if (str2 != null) {
                this.f8448a.b(str2);
            }
        }
        return this.f8448a.a(str, bitmap);
    }

    public Bitmap a(String str) {
        return this.f8448a.a(str);
    }

    public Bitmap b(String str) {
        return this.f8448a.b(str);
    }

    public void b() {
        this.f8448a.b();
    }

    public Collection<String> a() {
        return this.f8448a.a();
    }
}
