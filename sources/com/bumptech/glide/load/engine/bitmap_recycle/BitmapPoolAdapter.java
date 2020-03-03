package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BitmapPoolAdapter implements BitmapPool {
    public long a() {
        return 0;
    }

    public void a(float f) {
    }

    public void a(int i) {
    }

    public void b() {
    }

    public void a(Bitmap bitmap) {
        bitmap.recycle();
    }

    @NonNull
    public Bitmap a(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @NonNull
    public Bitmap b(int i, int i2, Bitmap.Config config) {
        return a(i, i2, config);
    }
}
