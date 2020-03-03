package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;

public interface Transition<R> {

    public interface ViewAdapter {
        @Nullable
        Drawable b();

        void e(Drawable drawable);

        View j();
    }

    boolean a(R r, ViewAdapter viewAdapter);
}
