package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FIFOLimitedMemoryCache extends LimitedMemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private final List<Bitmap> f8447a = Collections.synchronizedList(new LinkedList());

    public FIFOLimitedMemoryCache(int i) {
        super(i);
    }

    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.f8447a.add(bitmap);
        return true;
    }

    public Bitmap b(String str) {
        Bitmap a2 = super.a(str);
        if (a2 != null) {
            this.f8447a.remove(a2);
        }
        return super.b(str);
    }

    public void b() {
        this.f8447a.clear();
        super.b();
    }

    /* access modifiers changed from: protected */
    public int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* access modifiers changed from: protected */
    public Bitmap d() {
        return this.f8447a.remove(0);
    }

    /* access modifiers changed from: protected */
    public Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
