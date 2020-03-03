package com.squareup.picasso.mishop;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.mishop.Picasso;

public class ImageViewAction extends Action<ImageView> {
    Callback callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageViewAction(Picasso picasso, ImageView imageView, Request request, int i, int i2, int i3, Drawable drawable, String str, Object obj, Callback callback2, boolean z, float f) {
        super(picasso, imageView, request, i, i2, i3, drawable, str, obj, z, f);
        this.callback = callback2;
        if (MishopWeaker.mishopEnabled(i)) {
            ImageView imageView2 = imageView;
            MishopWeaker.getInstance(picasso).onImageViewRequest(imageView, this, this.key);
        }
    }

    public void complete(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        if (bitmap != null) {
            ImageView imageView = (ImageView) this.target.get();
            if (imageView != null) {
                if (MishopWeaker.mishopEnabled(this.memoryPolicy)) {
                    MishopWeaker instance = MishopWeaker.getInstance(this.picasso);
                    if (instance.isActionExpired(imageView, this)) {
                        instance.reloadImage(imageView);
                        return;
                    }
                }
                ImageView imageView2 = imageView;
                Bitmap bitmap2 = bitmap;
                Picasso.LoadedFrom loadedFrom2 = loadedFrom;
                PicassoDrawable.setBitmap(imageView2, this.picasso.context, bitmap2, loadedFrom2, this.noFade, this.fade, this.picasso.indicatorsEnabled);
                if (MishopWeaker.mishopEnabled(this.memoryPolicy)) {
                    MishopWeaker.getInstance(this.picasso).onImageViewBind(imageView);
                }
                if (this.callback != null) {
                    this.callback.onSuccess();
                    return;
                }
                return;
            }
            return;
        }
        throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[]{this}));
    }

    public void error() {
        ImageView imageView = (ImageView) this.target.get();
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            if (this.errorResId != 0) {
                imageView.setImageResource(this.errorResId);
            } else if (this.errorDrawable != null) {
                imageView.setImageDrawable(this.errorDrawable);
            }
            if (this.callback != null) {
                this.callback.onError();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        super.cancel();
        if (this.callback != null) {
            this.callback = null;
        }
    }
}
