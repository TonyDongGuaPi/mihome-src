package com.squareup.picasso.mishop;

import android.graphics.Bitmap;

public interface Transformation {
    String key();

    Bitmap transform(Bitmap bitmap);
}
