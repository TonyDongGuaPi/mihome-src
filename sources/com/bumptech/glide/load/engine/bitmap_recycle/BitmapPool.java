package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface BitmapPool {
    long a();

    @NonNull
    Bitmap a(int i, int i2, Bitmap.Config config);

    void a(float f);

    void a(int i);

    void a(Bitmap bitmap);

    @NonNull
    Bitmap b(int i, int i2, Bitmap.Config config);

    void b();
}
