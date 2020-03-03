package com.squareup.picasso.mishop;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.mishop.Picasso;

public interface Target {
    void onBitmapFailed(Drawable drawable);

    void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom);

    void onPrepareLoad(Drawable drawable);
}
