package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class ThumbnailImageViewTarget<T> extends ImageViewTarget<T> {
    /* access modifiers changed from: protected */
    public abstract Drawable b(T t);

    public ThumbnailImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public ThumbnailImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* access modifiers changed from: protected */
    public void a(@Nullable T t) {
        ViewGroup.LayoutParams layoutParams = ((ImageView) this.f5075a).getLayoutParams();
        Drawable b = b(t);
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            b = new FixedSizeDrawable(b, layoutParams.width, layoutParams.height);
        }
        ((ImageView) this.f5075a).setImageDrawable(b);
    }
}
