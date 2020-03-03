package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UsingFreqLimitedMemoryCache extends LimitedMemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Bitmap, Integer> f8453a = Collections.synchronizedMap(new HashMap());

    public UsingFreqLimitedMemoryCache(int i) {
        super(i);
    }

    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.f8453a.put(bitmap, 0);
        return true;
    }

    public Bitmap a(String str) {
        Integer num;
        Bitmap a2 = super.a(str);
        if (!(a2 == null || (num = this.f8453a.get(a2)) == null)) {
            this.f8453a.put(a2, Integer.valueOf(num.intValue() + 1));
        }
        return a2;
    }

    public Bitmap b(String str) {
        Bitmap a2 = super.a(str);
        if (a2 != null) {
            this.f8453a.remove(a2);
        }
        return super.b(str);
    }

    public void b() {
        this.f8453a.clear();
        super.b();
    }

    /* access modifiers changed from: protected */
    public int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* access modifiers changed from: protected */
    public Bitmap d() {
        Bitmap bitmap;
        Set<Map.Entry<Bitmap, Integer>> entrySet = this.f8453a.entrySet();
        synchronized (this.f8453a) {
            bitmap = null;
            Integer num = null;
            for (Map.Entry next : entrySet) {
                if (bitmap == null) {
                    bitmap = (Bitmap) next.getKey();
                    num = (Integer) next.getValue();
                } else {
                    Integer num2 = (Integer) next.getValue();
                    if (num2.intValue() < num.intValue()) {
                        bitmap = (Bitmap) next.getKey();
                        num = num2;
                    }
                }
            }
        }
        this.f8453a.remove(bitmap);
        return bitmap;
    }

    /* access modifiers changed from: protected */
    public Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
