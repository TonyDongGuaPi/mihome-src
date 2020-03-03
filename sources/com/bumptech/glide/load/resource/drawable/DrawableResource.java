package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.Preconditions;

public abstract class DrawableResource<T extends Drawable> implements Initializable, Resource<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final T f5016a;

    public DrawableResource(T t) {
        this.f5016a = (Drawable) Preconditions.a(t);
    }

    @NonNull
    /* renamed from: b */
    public final T d() {
        Drawable.ConstantState constantState = this.f5016a.getConstantState();
        if (constantState == null) {
            return this.f5016a;
        }
        return constantState.newDrawable();
    }

    public void a() {
        if (this.f5016a instanceof BitmapDrawable) {
            ((BitmapDrawable) this.f5016a).getBitmap().prepareToDraw();
        } else if (this.f5016a instanceof GifDrawable) {
            ((GifDrawable) this.f5016a).b().prepareToDraw();
        }
    }
}
