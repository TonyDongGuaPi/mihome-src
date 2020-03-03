package com.squareup.picasso.mishop;

import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class DeferredRequestCreator implements ViewTreeObserver.OnPreDrawListener {
    Callback callback;
    final RequestCreator creator;
    final WeakReference<ImageView> target;

    DeferredRequestCreator(RequestCreator requestCreator, ImageView imageView) {
        this(requestCreator, imageView, (Callback) null);
    }

    DeferredRequestCreator(RequestCreator requestCreator, ImageView imageView, Callback callback2) {
        this.creator = requestCreator;
        this.target = new WeakReference<>(imageView);
        this.callback = callback2;
        imageView.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public boolean onPreDraw() {
        ImageView imageView = (ImageView) this.target.get();
        if (imageView == null) {
            return true;
        }
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width <= 0 || height <= 0) {
            return true;
        }
        viewTreeObserver.removeOnPreDrawListener(this);
        this.creator.unfit().resize(width, height).into(imageView, this.callback);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.creator.clearTag();
        this.callback = null;
        ImageView imageView = (ImageView) this.target.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object getTag() {
        return this.creator.getTag();
    }
}
