package com.bumptech.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.util.Util;

public class BitmapDrawableResource extends DrawableResource<BitmapDrawable> implements Initializable {
    private final BitmapPool b;

    public BitmapDrawableResource(BitmapDrawable bitmapDrawable, BitmapPool bitmapPool) {
        super(bitmapDrawable);
        this.b = bitmapPool;
    }

    @NonNull
    public Class<BitmapDrawable> c() {
        return BitmapDrawable.class;
    }

    public int e() {
        return Util.b(((BitmapDrawable) this.f5016a).getBitmap());
    }

    public void f() {
        this.b.a(((BitmapDrawable) this.f5016a).getBitmap());
    }

    public void a() {
        ((BitmapDrawable) this.f5016a).getBitmap().prepareToDraw();
    }
}
