package com.bumptech.glide.request.transition;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.transition.Transition;

public class DrawableCrossFadeTransition implements Transition<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    private final int f5083a;
    private final boolean b;

    public DrawableCrossFadeTransition(int i, boolean z) {
        this.f5083a = i;
        this.b = z;
    }

    public boolean a(Drawable drawable, Transition.ViewAdapter viewAdapter) {
        Drawable b2 = viewAdapter.b();
        if (b2 == null) {
            b2 = new ColorDrawable(0);
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{b2, drawable});
        transitionDrawable.setCrossFadeEnabled(this.b);
        transitionDrawable.startTransition(this.f5083a);
        viewAdapter.e(transitionDrawable);
        return true;
    }
}
