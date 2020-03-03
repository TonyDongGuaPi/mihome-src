package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public class FadeInBitmapDisplayer implements BitmapDisplayer {

    /* renamed from: a  reason: collision with root package name */
    private final int f8486a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public FadeInBitmapDisplayer(int i) {
        this(i, true, true, true);
    }

    public FadeInBitmapDisplayer(int i, boolean z, boolean z2, boolean z3) {
        this.f8486a = i;
        this.b = z;
        this.c = z2;
        this.d = z3;
    }

    public void a(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.a(bitmap);
        if ((this.b && loadedFrom == LoadedFrom.NETWORK) || ((this.c && loadedFrom == LoadedFrom.DISC_CACHE) || (this.d && loadedFrom == LoadedFrom.MEMORY_CACHE))) {
            a(imageAware.d(), this.f8486a);
        }
    }

    public static void a(View view, int i) {
        if (view != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            view.startAnimation(alphaAnimation);
        }
    }
}
