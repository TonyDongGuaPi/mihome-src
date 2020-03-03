package com.nostra13.universalimageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.FuzzyKeyMemoryCache;
import com.nostra13.universalimageloader.core.assist.FlushedInputStream;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public final class ImageLoaderConfiguration {

    /* renamed from: a  reason: collision with root package name */
    final Resources f8460a;
    final int b;
    final int c;
    final int d;
    final int e;
    final BitmapProcessor f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final QueueProcessingType m;
    final MemoryCache n;
    final DiskCache o;
    final ImageDownloader p;
    final ImageDecoder q;
    final DisplayImageOptions r;
    final ImageDownloader s;
    final ImageDownloader t;

    private ImageLoaderConfiguration(Builder builder) {
        this.f8460a = builder.h.getResources();
        this.b = builder.i;
        this.c = builder.j;
        this.d = builder.k;
        this.e = builder.l;
        this.f = builder.m;
        this.g = builder.n;
        this.h = builder.o;
        this.k = builder.r;
        this.l = builder.s;
        this.m = builder.u;
        this.o = builder.z;
        this.n = builder.y;
        this.r = builder.D;
        this.p = builder.B;
        this.q = builder.C;
        this.i = builder.p;
        this.j = builder.q;
        this.s = new NetworkDeniedImageDownloader(this.p);
        this.t = new SlowNetworkImageDownloader(this.p);
        L.a(builder.E);
    }

    public static ImageLoaderConfiguration a(Context context) {
        return new Builder(context).c();
    }

    /* access modifiers changed from: package-private */
    public ImageSize a() {
        DisplayMetrics displayMetrics = this.f8460a.getDisplayMetrics();
        int i2 = this.b;
        if (i2 <= 0) {
            i2 = displayMetrics.widthPixels;
        }
        int i3 = this.c;
        if (i3 <= 0) {
            i3 = displayMetrics.heightPixels;
        }
        return new ImageSize(i2, i3);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8462a = 3;
        public static final int b = 3;
        public static final QueueProcessingType c = QueueProcessingType.FIFO;
        private static final String d = "diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other";
        private static final String e = "diskCache() and diskCacheFileNameGenerator() calls overlap each other";
        private static final String f = "memoryCache() and memoryCacheSize() calls overlap each other";
        private static final String g = "threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.";
        private FileNameGenerator A = null;
        /* access modifiers changed from: private */
        public ImageDownloader B = null;
        /* access modifiers changed from: private */
        public ImageDecoder C;
        /* access modifiers changed from: private */
        public DisplayImageOptions D = null;
        /* access modifiers changed from: private */
        public boolean E = false;
        /* access modifiers changed from: private */
        public Context h;
        /* access modifiers changed from: private */
        public int i = 0;
        /* access modifiers changed from: private */
        public int j = 0;
        /* access modifiers changed from: private */
        public int k = 0;
        /* access modifiers changed from: private */
        public int l = 0;
        /* access modifiers changed from: private */
        public BitmapProcessor m = null;
        /* access modifiers changed from: private */
        public Executor n = null;
        /* access modifiers changed from: private */
        public Executor o = null;
        /* access modifiers changed from: private */
        public boolean p = false;
        /* access modifiers changed from: private */
        public boolean q = false;
        /* access modifiers changed from: private */
        public int r = 3;
        /* access modifiers changed from: private */
        public int s = 3;
        private boolean t = false;
        /* access modifiers changed from: private */
        public QueueProcessingType u = c;
        private int v = 0;
        private long w = 0;
        private int x = 0;
        /* access modifiers changed from: private */
        public MemoryCache y = null;
        /* access modifiers changed from: private */
        public DiskCache z = null;

        public Builder(Context context) {
            this.h = context.getApplicationContext();
        }

        public Builder a(int i2, int i3) {
            this.i = i2;
            this.j = i3;
            return this;
        }

        @Deprecated
        public Builder a(int i2, int i3, BitmapProcessor bitmapProcessor) {
            return b(i2, i3, bitmapProcessor);
        }

        public Builder b(int i2, int i3, BitmapProcessor bitmapProcessor) {
            this.k = i2;
            this.l = i3;
            this.m = bitmapProcessor;
            return this;
        }

        public Builder a(Executor executor) {
            if (!(this.r == 3 && this.s == 3 && this.u == c)) {
                L.c(g, new Object[0]);
            }
            this.n = executor;
            return this;
        }

        public Builder b(Executor executor) {
            if (!(this.r == 3 && this.s == 3 && this.u == c)) {
                L.c(g, new Object[0]);
            }
            this.o = executor;
            return this;
        }

        public Builder a(int i2) {
            if (!(this.n == null && this.o == null)) {
                L.c(g, new Object[0]);
            }
            this.r = i2;
            return this;
        }

        public Builder b(int i2) {
            if (!(this.n == null && this.o == null)) {
                L.c(g, new Object[0]);
            }
            if (i2 < 1) {
                this.s = 1;
            } else if (i2 > 10) {
                this.s = 10;
            } else {
                this.s = i2;
            }
            return this;
        }

        public Builder a() {
            this.t = true;
            return this;
        }

        public Builder a(QueueProcessingType queueProcessingType) {
            if (!(this.n == null && this.o == null)) {
                L.c(g, new Object[0]);
            }
            this.u = queueProcessingType;
            return this;
        }

        public Builder c(int i2) {
            if (i2 > 0) {
                if (this.y != null) {
                    L.c(f, new Object[0]);
                }
                this.v = i2;
                return this;
            }
            throw new IllegalArgumentException("memoryCacheSize must be a positive number");
        }

        public Builder d(int i2) {
            if (i2 <= 0 || i2 >= 100) {
                throw new IllegalArgumentException("availableMemoryPercent must be in range (0 < % < 100)");
            }
            if (this.y != null) {
                L.c(f, new Object[0]);
            }
            this.v = (int) (((float) Runtime.getRuntime().maxMemory()) * (((float) i2) / 100.0f));
            return this;
        }

        public Builder a(MemoryCache memoryCache) {
            if (this.v != 0) {
                L.c(f, new Object[0]);
            }
            this.y = memoryCache;
            return this;
        }

        @Deprecated
        public Builder e(int i2) {
            return f(i2);
        }

        public Builder f(int i2) {
            if (i2 > 0) {
                if (this.z != null) {
                    L.c(d, new Object[0]);
                }
                this.w = (long) i2;
                return this;
            }
            throw new IllegalArgumentException("maxCacheSize must be a positive number");
        }

        @Deprecated
        public Builder g(int i2) {
            return h(i2);
        }

        public Builder h(int i2) {
            if (i2 > 0) {
                if (this.z != null) {
                    L.c(d, new Object[0]);
                }
                this.x = i2;
                return this;
            }
            throw new IllegalArgumentException("maxFileCount must be a positive number");
        }

        @Deprecated
        public Builder a(FileNameGenerator fileNameGenerator) {
            return b(fileNameGenerator);
        }

        public Builder b(FileNameGenerator fileNameGenerator) {
            if (this.z != null) {
                L.c(e, new Object[0]);
            }
            this.A = fileNameGenerator;
            return this;
        }

        @Deprecated
        public Builder a(DiskCache diskCache) {
            return b(diskCache);
        }

        public Builder b(DiskCache diskCache) {
            if (this.w > 0 || this.x > 0) {
                L.c(d, new Object[0]);
            }
            if (this.A != null) {
                L.c(e, new Object[0]);
            }
            this.z = diskCache;
            return this;
        }

        public Builder a(ImageDownloader imageDownloader) {
            this.B = imageDownloader;
            return this;
        }

        public Builder a(ImageDecoder imageDecoder) {
            this.C = imageDecoder;
            return this;
        }

        public Builder a(DisplayImageOptions displayImageOptions) {
            this.D = displayImageOptions;
            return this;
        }

        public Builder b() {
            this.E = true;
            return this;
        }

        public ImageLoaderConfiguration c() {
            d();
            return new ImageLoaderConfiguration(this);
        }

        private void d() {
            if (this.n == null) {
                this.n = DefaultConfigurationFactory.a(this.r, this.s, this.u);
            } else {
                this.p = true;
            }
            if (this.o == null) {
                this.o = DefaultConfigurationFactory.a(this.r, this.s, this.u);
            } else {
                this.q = true;
            }
            if (this.z == null) {
                if (this.A == null) {
                    this.A = DefaultConfigurationFactory.b();
                }
                this.z = DefaultConfigurationFactory.a(this.h, this.A, this.w, this.x);
            }
            if (this.y == null) {
                this.y = DefaultConfigurationFactory.a(this.h, this.v);
            }
            if (this.t) {
                this.y = new FuzzyKeyMemoryCache(this.y, MemoryCacheUtils.a());
            }
            if (this.B == null) {
                this.B = DefaultConfigurationFactory.a(this.h);
            }
            if (this.C == null) {
                this.C = DefaultConfigurationFactory.a(this.E);
            }
            if (this.D == null) {
                this.D = DisplayImageOptions.t();
            }
        }
    }

    private static class NetworkDeniedImageDownloader implements ImageDownloader {

        /* renamed from: a  reason: collision with root package name */
        private final ImageDownloader f8463a;

        public NetworkDeniedImageDownloader(ImageDownloader imageDownloader) {
            this.f8463a = imageDownloader;
        }

        public InputStream getStream(String str, Object obj) throws IOException {
            switch (ImageDownloader.Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.f8463a.getStream(str, obj);
            }
        }
    }

    private static class SlowNetworkImageDownloader implements ImageDownloader {

        /* renamed from: a  reason: collision with root package name */
        private final ImageDownloader f8464a;

        public SlowNetworkImageDownloader(ImageDownloader imageDownloader) {
            this.f8464a = imageDownloader;
        }

        public InputStream getStream(String str, Object obj) throws IOException {
            InputStream stream = this.f8464a.getStream(str, obj);
            switch (ImageDownloader.Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    return new FlushedInputStream(stream);
                default:
                    return stream;
            }
        }
    }
}
