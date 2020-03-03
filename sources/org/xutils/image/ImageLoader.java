package org.xutils.image;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.cache.LruCache;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.FileLockedException;
import org.xutils.http.RequestParams;
import org.xutils.x;

final class ImageLoader implements Callback.CacheCallback<Drawable>, Callback.Cancelable, Callback.PrepareCallback<File, Drawable>, Callback.ProgressCallback<Drawable>, Callback.TypedCallback<Drawable> {
    private static final AtomicLong d = new AtomicLong(0);
    private static final String m = "xUtils_img";
    private static final Executor n = new PriorityExecutor(10, false);
    private static final int o = 4194304;
    private static final LruCache<MemCacheKey, Drawable> p = new LruCache<MemCacheKey, Drawable>(4194304) {

        /* renamed from: a  reason: collision with root package name */
        private boolean f11929a = false;

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int b(MemCacheKey memCacheKey, Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null) {
                    return 0;
                }
                return bitmap.getByteCount();
            } else if (drawable instanceof GifDrawable) {
                return ((GifDrawable) drawable).c();
            } else {
                return super.b(memCacheKey, drawable);
            }
        }

        public void b(int i) {
            if (i < 0) {
                this.f11929a = true;
            }
            super.b(i);
            this.f11929a = false;
        }

        /* access modifiers changed from: protected */
        public void a(boolean z, MemCacheKey memCacheKey, Drawable drawable, Drawable drawable2) {
            super.a(z, memCacheKey, drawable, drawable2);
            if (z && this.f11929a && (drawable instanceof ReusableDrawable)) {
                ((ReusableDrawable) drawable).a((MemCacheKey) null);
            }
        }
    };
    private static final HashMap<String, FakeImageView> q = new HashMap<>();
    private static final Type r = File.class;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public MemCacheKey f11928a;
    /* access modifiers changed from: private */
    public ImageOptions b;
    /* access modifiers changed from: private */
    public WeakReference<ImageView> c;
    private final long e = d.incrementAndGet();
    private volatile boolean f = false;
    private volatile boolean g = false;
    private Callback.Cancelable h;
    /* access modifiers changed from: private */
    public Callback.CommonCallback<Drawable> i;
    private Callback.PrepareCallback<File, Drawable> j;
    private Callback.CacheCallback<Drawable> k;
    private Callback.ProgressCallback<Drawable> l;
    private boolean s = false;

    static {
        int memoryClass = (((ActivityManager) x.b().getSystemService("activity")).getMemoryClass() * 1048576) / 8;
        if (memoryClass < 4194304) {
            memoryClass = 4194304;
        }
        p.a(memoryClass);
    }

    private ImageLoader() {
    }

    static void g() {
        p.a();
    }

    static void h() {
        LruDiskCache.a(m).a();
    }

    static Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback) {
        FakeImageView fakeImageView;
        if (TextUtils.isEmpty(str)) {
            a((ImageView) null, imageOptions, "url is null", (Callback.CommonCallback<?>) commonCallback);
            return null;
        }
        synchronized (q) {
            fakeImageView = q.get(str);
            if (fakeImageView == null) {
                fakeImageView = new FakeImageView();
            }
        }
        return a((ImageView) fakeImageView, str, imageOptions, commonCallback);
    }

    static Callback.Cancelable a(String str, ImageOptions imageOptions, Callback.CacheCallback<File> cacheCallback) {
        if (TextUtils.isEmpty(str)) {
            a((ImageView) null, imageOptions, "url is null", (Callback.CommonCallback<?>) cacheCallback);
            return null;
        }
        return x.d().a(a(str, imageOptions), cacheCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0079, code lost:
        if (r2.isRecycled() != false) goto L_0x007b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.xutils.common.Callback.Cancelable a(android.widget.ImageView r5, java.lang.String r6, org.xutils.image.ImageOptions r7, org.xutils.common.Callback.CommonCallback<android.graphics.drawable.Drawable> r8) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0009
            java.lang.String r5 = "view is null"
            a((android.widget.ImageView) r0, (org.xutils.image.ImageOptions) r7, (java.lang.String) r5, (org.xutils.common.Callback.CommonCallback<?>) r8)
            return r0
        L_0x0009:
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L_0x0015
            java.lang.String r6 = "url is null"
            a((android.widget.ImageView) r5, (org.xutils.image.ImageOptions) r7, (java.lang.String) r6, (org.xutils.common.Callback.CommonCallback<?>) r8)
            return r0
        L_0x0015:
            if (r7 != 0) goto L_0x0019
            org.xutils.image.ImageOptions r7 = org.xutils.image.ImageOptions.f11937a
        L_0x0019:
            r7.a(r5)
            org.xutils.image.MemCacheKey r1 = new org.xutils.image.MemCacheKey
            r1.<init>(r6, r7)
            android.graphics.drawable.Drawable r2 = r5.getDrawable()
            boolean r3 = r2 instanceof org.xutils.image.AsyncDrawable
            if (r3 == 0) goto L_0x0042
            org.xutils.image.AsyncDrawable r2 = (org.xutils.image.AsyncDrawable) r2
            org.xutils.image.ImageLoader r2 = r2.a()
            if (r2 == 0) goto L_0x005a
            boolean r3 = r2.f
            if (r3 != 0) goto L_0x005a
            org.xutils.image.MemCacheKey r3 = r2.f11928a
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x003e
            return r0
        L_0x003e:
            r2.a()
            goto L_0x005a
        L_0x0042:
            boolean r3 = r2 instanceof org.xutils.image.ReusableDrawable
            if (r3 == 0) goto L_0x005a
            r3 = r2
            org.xutils.image.ReusableDrawable r3 = (org.xutils.image.ReusableDrawable) r3
            org.xutils.image.MemCacheKey r3 = r3.a()
            if (r3 == 0) goto L_0x005a
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x005a
            org.xutils.cache.LruCache<org.xutils.image.MemCacheKey, android.graphics.drawable.Drawable> r3 = p
            r3.a(r1, r2)
        L_0x005a:
            boolean r2 = r7.r()
            if (r2 == 0) goto L_0x007b
            org.xutils.cache.LruCache<org.xutils.image.MemCacheKey, android.graphics.drawable.Drawable> r2 = p
            java.lang.Object r1 = r2.a(r1)
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            boolean r2 = r1 instanceof android.graphics.drawable.BitmapDrawable
            if (r2 == 0) goto L_0x007c
            r2 = r1
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            android.graphics.Bitmap r2 = r2.getBitmap()
            if (r2 == 0) goto L_0x007b
            boolean r2 = r2.isRecycled()
            if (r2 == 0) goto L_0x007c
        L_0x007b:
            r1 = r0
        L_0x007c:
            if (r1 == 0) goto L_0x0105
            r2 = 0
            boolean r3 = r8 instanceof org.xutils.common.Callback.ProgressCallback     // Catch:{ Throwable -> 0x00df }
            if (r3 == 0) goto L_0x0089
            r3 = r8
            org.xutils.common.Callback$ProgressCallback r3 = (org.xutils.common.Callback.ProgressCallback) r3     // Catch:{ Throwable -> 0x00df }
            r3.d()     // Catch:{ Throwable -> 0x00df }
        L_0x0089:
            android.widget.ImageView$ScaleType r3 = r7.p()     // Catch:{ Throwable -> 0x00df }
            r5.setScaleType(r3)     // Catch:{ Throwable -> 0x00df }
            r5.setImageDrawable(r1)     // Catch:{ Throwable -> 0x00df }
            r3 = 1
            boolean r4 = r8 instanceof org.xutils.common.Callback.CacheCallback     // Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
            if (r4 == 0) goto L_0x00c1
            r4 = r8
            org.xutils.common.Callback$CacheCallback r4 = (org.xutils.common.Callback.CacheCallback) r4     // Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
            boolean r1 = r4.a(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
            if (r1 != 0) goto L_0x00c7
            org.xutils.image.ImageLoader r0 = new org.xutils.image.ImageLoader     // Catch:{ Throwable -> 0x00be, all -> 0x00bb }
            r0.<init>()     // Catch:{ Throwable -> 0x00be, all -> 0x00bb }
            org.xutils.common.Callback$Cancelable r0 = r0.b(r5, r6, r7, r8)     // Catch:{ Throwable -> 0x00be, all -> 0x00bb }
            if (r1 == 0) goto L_0x00ba
            if (r8 == 0) goto L_0x00ba
            r8.c()     // Catch:{ Throwable -> 0x00b2 }
            goto L_0x00ba
        L_0x00b2:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            org.xutils.common.util.LogUtil.b(r6, r5)
        L_0x00ba:
            return r0
        L_0x00bb:
            r5 = move-exception
            r2 = r1
            goto L_0x00f4
        L_0x00be:
            r0 = move-exception
            r3 = r1
            goto L_0x00e1
        L_0x00c1:
            if (r8 == 0) goto L_0x00c6
            r8.b(r1)     // Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
        L_0x00c6:
            r1 = 1
        L_0x00c7:
            if (r1 == 0) goto L_0x00d7
            if (r8 == 0) goto L_0x00d7
            r8.c()     // Catch:{ Throwable -> 0x00cf }
            goto L_0x00d7
        L_0x00cf:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            org.xutils.common.util.LogUtil.b(r6, r5)
        L_0x00d7:
            return r0
        L_0x00d8:
            r5 = move-exception
            r2 = 1
            goto L_0x00f4
        L_0x00db:
            r0 = move-exception
            goto L_0x00e1
        L_0x00dd:
            r5 = move-exception
            goto L_0x00f4
        L_0x00df:
            r0 = move-exception
            r3 = 0
        L_0x00e1:
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x00f2 }
            org.xutils.common.util.LogUtil.b(r1, r0)     // Catch:{ all -> 0x00f2 }
            org.xutils.image.ImageLoader r0 = new org.xutils.image.ImageLoader     // Catch:{ all -> 0x00dd }
            r0.<init>()     // Catch:{ all -> 0x00dd }
            org.xutils.common.Callback$Cancelable r5 = r0.b(r5, r6, r7, r8)     // Catch:{ all -> 0x00dd }
            return r5
        L_0x00f2:
            r5 = move-exception
            r2 = r3
        L_0x00f4:
            if (r2 == 0) goto L_0x0104
            if (r8 == 0) goto L_0x0104
            r8.c()     // Catch:{ Throwable -> 0x00fc }
            goto L_0x0104
        L_0x00fc:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            org.xutils.common.util.LogUtil.b(r7, r6)
        L_0x0104:
            throw r5
        L_0x0105:
            org.xutils.image.ImageLoader r0 = new org.xutils.image.ImageLoader
            r0.<init>()
            org.xutils.common.Callback$Cancelable r5 = r0.b(r5, r6, r7, r8)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageLoader.a(android.widget.ImageView, java.lang.String, org.xutils.image.ImageOptions, org.xutils.common.Callback$CommonCallback):org.xutils.common.Callback$Cancelable");
    }

    private Callback.Cancelable b(ImageView imageView, String str, ImageOptions imageOptions, Callback.CommonCallback<Drawable> commonCallback) {
        this.c = new WeakReference<>(imageView);
        this.b = imageOptions;
        this.f11928a = new MemCacheKey(str, imageOptions);
        this.i = commonCallback;
        if (commonCallback instanceof Callback.ProgressCallback) {
            this.l = (Callback.ProgressCallback) commonCallback;
        }
        if (commonCallback instanceof Callback.PrepareCallback) {
            this.j = (Callback.PrepareCallback) commonCallback;
        }
        if (commonCallback instanceof Callback.CacheCallback) {
            this.k = (Callback.CacheCallback) commonCallback;
        }
        if (imageOptions.q()) {
            Drawable b2 = imageOptions.b(imageView);
            imageView.setScaleType(imageOptions.o());
            imageView.setImageDrawable(new AsyncDrawable(this, b2));
        } else {
            imageView.setImageDrawable(new AsyncDrawable(this, imageView.getDrawable()));
        }
        RequestParams a2 = a(str, imageOptions);
        if (imageView instanceof FakeImageView) {
            synchronized (q) {
                q.put(str, (FakeImageView) imageView);
            }
        }
        Callback.Cancelable a3 = x.d().a(a2, this);
        this.h = a3;
        return a3;
    }

    public void a() {
        this.f = true;
        this.g = true;
        if (this.h != null) {
            this.h.a();
        }
    }

    public boolean b() {
        return this.g || !a(false);
    }

    public void d() {
        if (this.l != null) {
            this.l.d();
        }
    }

    public void e() {
        if (a(true) && this.l != null) {
            this.l.e();
        }
    }

    public void a(long j2, long j3, boolean z) {
        if (a(true) && this.l != null) {
            this.l.a(j2, j3, z);
        }
    }

    public Type f() {
        return r;
    }

    /* renamed from: a */
    public Drawable c(File file) {
        if (!a(true)) {
            return null;
        }
        try {
            Drawable c2 = this.j != null ? this.j.c(file) : null;
            if (c2 == null) {
                c2 = ImageDecoder.a(file, this.b, (Callback.Cancelable) this);
            }
            if (c2 != null && (c2 instanceof ReusableDrawable)) {
                ((ReusableDrawable) c2).a(this.f11928a);
                p.a(this.f11928a, c2);
            }
            return c2;
        } catch (IOException e2) {
            IOUtil.a(file);
            LogUtil.e(e2.getMessage(), e2);
            return null;
        }
    }

    public boolean a(Drawable drawable) {
        if (!a(true) || drawable == null) {
            return false;
        }
        this.s = true;
        c(drawable);
        if (this.k != null) {
            return this.k.a(drawable);
        }
        if (this.i == null) {
            return true;
        }
        this.i.b(drawable);
        return true;
    }

    public void b(Drawable drawable) {
        if (a(!this.s) && drawable != null) {
            c(drawable);
            if (this.i != null) {
                this.i.b(drawable);
            }
        }
    }

    public void a(Throwable th, boolean z) {
        this.f = true;
        if (a(false)) {
            if (th instanceof FileLockedException) {
                LogUtil.a("ImageFileLocked: " + this.f11928a.f11939a);
                x.c().a((Runnable) new Runnable() {
                    public void run() {
                        ImageLoader.a((ImageView) ImageLoader.this.c.get(), ImageLoader.this.f11928a.f11939a, ImageLoader.this.b, (Callback.CommonCallback<Drawable>) ImageLoader.this.i);
                    }
                }, 10);
                return;
            }
            LogUtil.b(this.f11928a.f11939a, th);
            i();
            if (this.i != null) {
                this.i.a(th, z);
            }
        }
    }

    public void a(Callback.CancelledException cancelledException) {
        this.f = true;
        if (a(false) && this.i != null) {
            this.i.a(cancelledException);
        }
    }

    public void c() {
        this.f = true;
        if (((ImageView) this.c.get()) instanceof FakeImageView) {
            synchronized (q) {
                q.remove(this.f11928a.f11939a);
            }
        }
        if (a(false) && this.i != null) {
            this.i.c();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0023, code lost:
        r1 = r2.s();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.xutils.http.RequestParams a(java.lang.String r1, org.xutils.image.ImageOptions r2) {
        /*
            org.xutils.http.RequestParams r0 = new org.xutils.http.RequestParams
            r0.<init>(r1)
            java.lang.String r1 = "xUtils_img"
            r0.f((java.lang.String) r1)
            r1 = 8000(0x1f40, float:1.121E-41)
            r0.a((int) r1)
            org.xutils.common.task.Priority r1 = org.xutils.common.task.Priority.BG_LOW
            r0.a((org.xutils.common.task.Priority) r1)
            java.util.concurrent.Executor r1 = n
            r0.a((java.util.concurrent.Executor) r1)
            r1 = 1
            r0.f((boolean) r1)
            r1 = 0
            r0.c((boolean) r1)
            if (r2 == 0) goto L_0x002d
            org.xutils.image.ImageOptions$ParamsBuilder r1 = r2.s()
            if (r1 == 0) goto L_0x002d
            org.xutils.http.RequestParams r0 = r1.a(r0, r2)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageLoader.a(java.lang.String, org.xutils.image.ImageOptions):org.xutils.http.RequestParams");
    }

    private boolean a(boolean z) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView == null) {
            return false;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AsyncDrawable) {
            ImageLoader a2 = ((AsyncDrawable) drawable).a();
            if (a2 != null) {
                if (a2 == this) {
                    if (imageView.getVisibility() == 0) {
                        return true;
                    }
                    a2.a();
                    return false;
                } else if (this.e > a2.e) {
                    a2.a();
                    return true;
                } else {
                    a();
                    return false;
                }
            }
        } else if (z) {
            a();
            return false;
        }
        return true;
    }

    private void c(Drawable drawable) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            imageView.setScaleType(this.b.p());
            if (drawable instanceof GifDrawable) {
                if (imageView.getScaleType() == ImageView.ScaleType.CENTER) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
                imageView.setLayerType(1, (Paint) null);
            }
            if (this.b.n() != null) {
                ImageAnimationHelper.a(imageView, drawable, this.b.n());
            } else if (this.b.m()) {
                ImageAnimationHelper.a(imageView, drawable);
            } else {
                imageView.setImageDrawable(drawable);
            }
        }
    }

    private void i() {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            Drawable c2 = this.b.c(imageView);
            imageView.setScaleType(this.b.o());
            imageView.setImageDrawable(c2);
        }
    }

    private static void a(final ImageView imageView, final ImageOptions imageOptions, final String str, final Callback.CommonCallback<?> commonCallback) {
        x.c().a((Runnable) new Runnable() {
            public void run() {
                try {
                    if (commonCallback instanceof Callback.ProgressCallback) {
                        ((Callback.ProgressCallback) commonCallback).d();
                    }
                    if (!(imageView == null || imageOptions == null)) {
                        imageView.setScaleType(imageOptions.o());
                        imageView.setImageDrawable(imageOptions.c(imageView));
                    }
                    if (commonCallback != null) {
                        commonCallback.a(new IllegalArgumentException(str), false);
                    }
                    if (commonCallback != null) {
                        commonCallback.c();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    LogUtil.b(th.getMessage(), th);
                    return;
                }
                throw th;
                if (commonCallback != null) {
                    commonCallback.c();
                }
            }
        });
    }

    @SuppressLint({"ViewConstructor"})
    private static final class FakeImageView extends ImageView {
        private Drawable drawable;

        public void setLayerType(int i, Paint paint) {
        }

        public void setScaleType(ImageView.ScaleType scaleType) {
        }

        public void startAnimation(Animation animation) {
        }

        public FakeImageView() {
            super(x.b());
        }

        public void setImageDrawable(Drawable drawable2) {
            this.drawable = drawable2;
        }

        public Drawable getDrawable() {
            return this.drawable;
        }
    }
}
