package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRULimitedMemoryCache extends LimitedMemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8449a = 10;
    private static final float b = 1.1f;
    private final Map<String, Bitmap> c = Collections.synchronizedMap(new LinkedHashMap(10, b, true));

    public LRULimitedMemoryCache(int i) {
        super(i);
    }

    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.c.put(str, bitmap);
        return true;
    }

    public Bitmap a(String str) {
        this.c.get(str);
        return super.a(str);
    }

    public Bitmap b(String str) {
        this.c.remove(str);
        return super.b(str);
    }

    public void b() {
        this.c.clear();
        super.b();
    }

    /* access modifiers changed from: protected */
    public int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* access modifiers changed from: protected */
    public Bitmap d() {
        Bitmap bitmap;
        synchronized (this.c) {
            Iterator<Map.Entry<String, Bitmap>> it = this.c.entrySet().iterator();
            if (it.hasNext()) {
                bitmap = (Bitmap) it.next().getValue();
                it.remove();
            } else {
                bitmap = null;
            }
        }
        return bitmap;
    }

    /* access modifiers changed from: protected */
    public Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
