package org.xutils.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.File;
import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.x;

public final class ImageManagerImpl implements ImageManager {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f11932a = new Object();
    private static volatile ImageManagerImpl b;

    private ImageManagerImpl() {
    }

    public static void c() {
        if (b == null) {
            synchronized (f11932a) {
                if (b == null) {
                    b = new ImageManagerImpl();
                }
            }
        }
        x.Ext.a((ImageManager) b);
    }

    public void a(final ImageView imageView, final String str) {
        x.c().a((Runnable) new Runnable() {
            public void run() {
                ImageLoader.a(imageView, str, (ImageOptions) null, (Callback.CommonCallback<Drawable>) null);
            }
        });
    }

    public void a(final ImageView imageView, final String str, final ImageOptions imageOptions) {
        x.c().a((Runnable) new Runnable() {
            public void run() {
                ImageLoader.a(imageView, str, imageOptions, (Callback.CommonCallback<Drawable>) null);
            }
        });
    }

    public void a(final ImageView imageView, final String str, final Callback.CommonCallback<Drawable> commonCallback) {
        x.c().a((Runnable) new Runnable() {
            public void run() {
                ImageLoader.a(imageView, str, (ImageOptions) null, (Callback.CommonCallback<Drawable>) commonCallback);
            }
        });
    }

    public void a(ImageView imageView, String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback) {
        final ImageView imageView2 = imageView;
        final String str2 = str;
        final ImageOptions imageOptions2 = imageOptions;
        final Callback.CommonCallback<Drawable> commonCallback2 = commonCallback;
        x.c().a((Runnable) new Runnable() {
            public void run() {
                ImageLoader.a(imageView2, str2, imageOptions2, (Callback.CommonCallback<Drawable>) commonCallback2);
            }
        });
    }

    public Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback) {
        return ImageLoader.a(str, imageOptions, commonCallback);
    }

    public Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CacheCallback<File> cacheCallback) {
        return ImageLoader.a(str, imageOptions, cacheCallback);
    }

    public void a() {
        ImageLoader.g();
    }

    public void b() {
        ImageLoader.h();
        ImageDecoder.a();
    }
}
