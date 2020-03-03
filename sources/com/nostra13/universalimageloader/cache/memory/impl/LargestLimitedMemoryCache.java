package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LargestLimitedMemoryCache extends LimitedMemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Bitmap, Integer> f8450a = Collections.synchronizedMap(new HashMap());

    public LargestLimitedMemoryCache(int i) {
        super(i);
    }

    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.f8450a.put(bitmap, Integer.valueOf(b(bitmap)));
        return true;
    }

    public Bitmap b(String str) {
        Bitmap a2 = super.a(str);
        if (a2 != null) {
            this.f8450a.remove(a2);
        }
        return super.b(str);
    }

    public void b() {
        this.f8450a.clear();
        super.b();
    }

    /* access modifiers changed from: protected */
    public int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* access modifiers changed from: protected */
    public Bitmap d() {
        Bitmap bitmap;
        Set<Map.Entry<Bitmap, Integer>> entrySet = this.f8450a.entrySet();
        synchronized (this.f8450a) {
            bitmap = null;
            Integer num = null;
            for (Map.Entry next : entrySet) {
                if (bitmap == null) {
                    bitmap = (Bitmap) next.getKey();
                    num = (Integer) next.getValue();
                } else {
                    Integer num2 = (Integer) next.getValue();
                    if (num2.intValue() > num.intValue()) {
                        bitmap = (Bitmap) next.getKey();
                        num = num2;
                    }
                }
            }
        }
        this.f8450a.remove(bitmap);
        return bitmap;
    }

    /* access modifiers changed from: protected */
    public Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
