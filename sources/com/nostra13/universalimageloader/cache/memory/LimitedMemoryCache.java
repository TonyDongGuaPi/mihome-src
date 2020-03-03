package com.nostra13.universalimageloader.cache.memory;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.L;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LimitedMemoryCache extends BaseMemoryCache {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8446a = 16;
    private static final int b = 16777216;
    private final int c;
    private final AtomicInteger d;
    private final List<Bitmap> e = Collections.synchronizedList(new LinkedList());

    /* access modifiers changed from: protected */
    public abstract int b(Bitmap bitmap);

    /* access modifiers changed from: protected */
    public abstract Bitmap d();

    public LimitedMemoryCache(int i) {
        this.c = i;
        this.d = new AtomicInteger();
        if (i > 16777216) {
            L.c("You set too large memory cache size (more than %1$d Mb)", 16);
        }
    }

    public boolean a(String str, Bitmap bitmap) {
        boolean z;
        int b2 = b(bitmap);
        int c2 = c();
        int i = this.d.get();
        if (b2 < c2) {
            while (i + b2 > c2) {
                Bitmap d2 = d();
                if (this.e.remove(d2)) {
                    i = this.d.addAndGet(-b(d2));
                }
            }
            this.e.add(bitmap);
            this.d.addAndGet(b2);
            z = true;
        } else {
            z = false;
        }
        super.a(str, bitmap);
        return z;
    }

    public Bitmap b(String str) {
        Bitmap a2 = super.a(str);
        if (a2 != null && this.e.remove(a2)) {
            this.d.addAndGet(-b(a2));
        }
        return super.b(str);
    }

    public void b() {
        this.e.clear();
        this.d.set(0);
        super.b();
    }

    /* access modifiers changed from: protected */
    public int c() {
        return this.c;
    }
}
