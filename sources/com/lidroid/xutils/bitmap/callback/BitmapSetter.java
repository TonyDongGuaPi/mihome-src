package com.lidroid.xutils.bitmap.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface BitmapSetter<T extends View> {
    Drawable a(T t);

    void a(T t, Bitmap bitmap);

    void a(T t, Drawable drawable);
}
