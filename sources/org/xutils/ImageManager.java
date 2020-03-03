package org.xutils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.File;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;

public interface ImageManager {
    Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CacheCallback<File> cacheCallback);

    Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback);

    void a();

    void a(ImageView imageView, String str);

    void a(ImageView imageView, String str, Callback.CommonCallback<Drawable> commonCallback);

    void a(ImageView imageView, String str, ImageOptions imageOptions);

    void a(ImageView imageView, String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback);

    void b();
}
