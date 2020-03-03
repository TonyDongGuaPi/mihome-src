package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

public class ImageLoader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8458a = "ImageLoader";
    static final String b = "Initialize ImageLoader with configuration";
    static final String c = "Destroy ImageLoader";
    static final String d = "Load image from memory cache [%s]";
    private static final String e = "Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.";
    private static final String f = "Wrong arguments were passed to displayImage() method (ImageView reference must not be null)";
    private static final String g = "ImageLoader must be init with configuration before using";
    private static final String h = "ImageLoader configuration can not be initialized with null";
    private static volatile ImageLoader l;
    private ImageLoaderConfiguration i;
    private ImageLoaderEngine j;
    private ImageLoadingListener k = new SimpleImageLoadingListener();

    public static ImageLoader a() {
        if (l == null) {
            synchronized (ImageLoader.class) {
                if (l == null) {
                    l = new ImageLoader();
                }
            }
        }
        return l;
    }

    protected ImageLoader() {
    }

    public synchronized void a(ImageLoaderConfiguration imageLoaderConfiguration) {
        if (imageLoaderConfiguration == null) {
            throw new IllegalArgumentException(h);
        } else if (this.i == null) {
            L.a(b, new Object[0]);
            this.j = new ImageLoaderEngine(imageLoaderConfiguration);
            this.i = imageLoaderConfiguration;
        } else {
            L.c(e, new Object[0]);
        }
    }

    public boolean b() {
        return this.i != null;
    }

    public void a(String str, ImageAware imageAware) {
        a(str, imageAware, (DisplayImageOptions) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageAware imageAware, ImageLoadingListener imageLoadingListener) {
        a(str, imageAware, (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions) {
        a(str, imageAware, displayImageOptions, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        a(str, imageAware, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        a(str, imageAware, displayImageOptions, (ImageSize) null, imageLoadingListener, imageLoadingProgressListener);
    }

    public void a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageSize imageSize, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        m();
        if (imageAware != null) {
            if (imageLoadingListener == null) {
                imageLoadingListener = this.k;
            }
            ImageLoadingListener imageLoadingListener2 = imageLoadingListener;
            if (displayImageOptions == null) {
                displayImageOptions = this.i.r;
            }
            if (TextUtils.isEmpty(str)) {
                this.j.b(imageAware);
                imageLoadingListener2.onLoadingStarted(str, imageAware.d());
                if (displayImageOptions.b()) {
                    imageAware.a(displayImageOptions.b(this.i.f8460a));
                } else {
                    imageAware.a((Drawable) null);
                }
                imageLoadingListener2.onLoadingComplete(str, imageAware.d(), (Bitmap) null);
                return;
            }
            if (imageSize == null) {
                imageSize = ImageSizeUtils.a(imageAware, this.i.a());
            }
            ImageSize imageSize2 = imageSize;
            String a2 = MemoryCacheUtils.a(str, imageSize2);
            this.j.a(imageAware, a2);
            imageLoadingListener2.onLoadingStarted(str, imageAware.d());
            Bitmap a3 = this.i.n.a(a2);
            if (a3 == null || a3.isRecycled()) {
                if (displayImageOptions.a()) {
                    imageAware.a(displayImageOptions.a(this.i.f8460a));
                } else if (displayImageOptions.g()) {
                    imageAware.a((Drawable) null);
                }
                LoadAndDisplayImageTask loadAndDisplayImageTask = new LoadAndDisplayImageTask(this.j, new ImageLoadingInfo(str, imageAware, imageSize2, a2, displayImageOptions, imageLoadingListener2, imageLoadingProgressListener, this.j.a(str)), a(displayImageOptions));
                if (displayImageOptions.s()) {
                    loadAndDisplayImageTask.run();
                } else {
                    this.j.a(loadAndDisplayImageTask);
                }
            } else {
                L.a(d, a2);
                if (displayImageOptions.e()) {
                    ProcessAndDisplayImageTask processAndDisplayImageTask = new ProcessAndDisplayImageTask(this.j, a3, new ImageLoadingInfo(str, imageAware, imageSize2, a2, displayImageOptions, imageLoadingListener2, imageLoadingProgressListener, this.j.a(str)), a(displayImageOptions));
                    if (displayImageOptions.s()) {
                        processAndDisplayImageTask.run();
                    } else {
                        this.j.a(processAndDisplayImageTask);
                    }
                } else {
                    displayImageOptions.q().a(a3, imageAware, LoadedFrom.MEMORY_CACHE);
                    imageLoadingListener2.onLoadingComplete(str, imageAware.d(), a3);
                }
            }
        } else {
            throw new IllegalArgumentException(f);
        }
    }

    public void a(String str, ImageView imageView) {
        a(str, (ImageAware) new ImageViewAware(imageView), (DisplayImageOptions) null, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageView imageView, ImageSize imageSize) {
        a(str, new ImageViewAware(imageView), (DisplayImageOptions) null, imageSize, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageView imageView, DisplayImageOptions displayImageOptions) {
        a(str, (ImageAware) new ImageViewAware(imageView), displayImageOptions, (ImageLoadingListener) null, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        a(str, (ImageAware) new ImageViewAware(imageView), (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        a(str, imageView, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        a(str, (ImageAware) new ImageViewAware(imageView), displayImageOptions, imageLoadingListener, imageLoadingProgressListener);
    }

    public void a(String str, ImageLoadingListener imageLoadingListener) {
        a(str, (ImageSize) null, (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageSize imageSize, ImageLoadingListener imageLoadingListener) {
        a(str, imageSize, (DisplayImageOptions) null, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        a(str, (ImageSize) null, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        a(str, imageSize, displayImageOptions, imageLoadingListener, (ImageLoadingProgressListener) null);
    }

    public void a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        m();
        if (imageSize == null) {
            imageSize = this.i.a();
        }
        if (displayImageOptions == null) {
            displayImageOptions = this.i.r;
        }
        String str2 = str;
        a(str2, (ImageAware) new NonViewAware(str, imageSize, ViewScaleType.CROP), displayImageOptions, imageLoadingListener, imageLoadingProgressListener);
    }

    public Bitmap a(String str) {
        return a(str, (ImageSize) null, (DisplayImageOptions) null);
    }

    public Bitmap a(String str, DisplayImageOptions displayImageOptions) {
        return a(str, (ImageSize) null, displayImageOptions);
    }

    public Bitmap a(String str, ImageSize imageSize) {
        return a(str, imageSize, (DisplayImageOptions) null);
    }

    public Bitmap a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions) {
        if (displayImageOptions == null) {
            displayImageOptions = this.i.r;
        }
        DisplayImageOptions d2 = new DisplayImageOptions.Builder().a(displayImageOptions).f(true).d();
        SyncImageLoadingListener syncImageLoadingListener = new SyncImageLoadingListener();
        a(str, imageSize, d2, (ImageLoadingListener) syncImageLoadingListener);
        return syncImageLoadingListener.a();
    }

    private void m() {
        if (this.i == null) {
            throw new IllegalStateException(g);
        }
    }

    public void a(ImageLoadingListener imageLoadingListener) {
        if (imageLoadingListener == null) {
            imageLoadingListener = new SimpleImageLoadingListener();
        }
        this.k = imageLoadingListener;
    }

    public MemoryCache c() {
        m();
        return this.i.n;
    }

    public void d() {
        m();
        this.i.n.b();
    }

    @Deprecated
    public DiskCache e() {
        return f();
    }

    public DiskCache f() {
        m();
        return this.i.o;
    }

    @Deprecated
    public void g() {
        h();
    }

    public void h() {
        m();
        this.i.o.c();
    }

    public String a(ImageAware imageAware) {
        return this.j.a(imageAware);
    }

    public String a(ImageView imageView) {
        return this.j.a((ImageAware) new ImageViewAware(imageView));
    }

    public void b(ImageAware imageAware) {
        this.j.b(imageAware);
    }

    public void b(ImageView imageView) {
        this.j.b((ImageAware) new ImageViewAware(imageView));
    }

    public void a(boolean z) {
        this.j.a(z);
    }

    public void b(boolean z) {
        this.j.b(z);
    }

    public void i() {
        this.j.a();
    }

    public void j() {
        this.j.b();
    }

    public void k() {
        this.j.c();
    }

    public void l() {
        if (this.i != null) {
            L.a(c, new Object[0]);
        }
        k();
        this.i.o.b();
        this.j = null;
        this.i = null;
    }

    private static Handler a(DisplayImageOptions displayImageOptions) {
        Handler r = displayImageOptions.r();
        if (displayImageOptions.s()) {
            return null;
        }
        return (r == null && Looper.myLooper() == Looper.getMainLooper()) ? new Handler() : r;
    }

    private static class SyncImageLoadingListener extends SimpleImageLoadingListener {

        /* renamed from: a  reason: collision with root package name */
        private Bitmap f8459a;

        private SyncImageLoadingListener() {
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            this.f8459a = bitmap;
        }

        public Bitmap a() {
            return this.f8459a;
        }
    }
}
