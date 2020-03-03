package com.bumptech.glide.load.resource.gif;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.resource.drawable.DrawableResource;

public class GifDrawableResource extends DrawableResource<GifDrawable> implements Initializable {
    public GifDrawableResource(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @NonNull
    public Class<GifDrawable> c() {
        return GifDrawable.class;
    }

    public int e() {
        return ((GifDrawable) this.f5016a).a();
    }

    public void f() {
        ((GifDrawable) this.f5016a).stop();
        ((GifDrawable) this.f5016a).i();
    }

    public void a() {
        ((GifDrawable) this.f5016a).b().prepareToDraw();
    }
}
