package com.tmall.wireless.vaf.virtualview.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.tmall.wireless.vaf.virtualview.view.image.ImageBase;

public class ImageLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9368a = "ImageLoader_TMTEST";
    private Context b;
    private IImageLoaderAdapter c;

    public interface IImageLoaderAdapter {
        void a(String str, int i, int i2, Listener listener);

        void a(String str, ImageBase imageBase, int i, int i2);
    }

    public interface Listener {
        void a();

        void a(Bitmap bitmap);

        void a(Drawable drawable);
    }

    private ImageLoader(Context context) {
        this.b = context.getApplicationContext();
    }

    public void a(IImageLoaderAdapter iImageLoaderAdapter) {
        this.c = iImageLoaderAdapter;
    }

    public static ImageLoader a(Context context) {
        return new ImageLoader(context);
    }

    public void a(String str, int i, int i2, Listener listener) {
        if (this.c != null) {
            this.c.a(str, i, i2, listener);
        }
    }

    public void a(String str, ImageBase imageBase, int i, int i2) {
        if (this.c != null) {
            this.c.a(str, imageBase, i, i2);
        }
    }
}
