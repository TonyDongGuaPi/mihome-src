package com.lidroid.xutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.BitmapCacheListener;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.lidroid.xutils.bitmap.core.AsyncDrawable;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.task.TaskHandler;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

public class BitmapUtils implements TaskHandler {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f6282a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public final Object c;
    private Context d;
    /* access modifiers changed from: private */
    public BitmapGlobalConfig e;
    private BitmapDisplayConfig f;

    public boolean f() {
        return true;
    }

    public boolean g() {
        return true;
    }

    public boolean h() {
        return true;
    }

    public BitmapUtils(Context context) {
        this(context, (String) null);
    }

    public BitmapUtils(Context context, String str) {
        this.f6282a = false;
        this.b = false;
        this.c = new Object();
        if (context != null) {
            this.d = context.getApplicationContext();
            this.e = BitmapGlobalConfig.a(this.d, str);
            this.f = new BitmapDisplayConfig();
            return;
        }
        throw new IllegalArgumentException("context may not be null");
    }

    public BitmapUtils(Context context, String str, int i) {
        this(context, str);
        this.e.c(i);
    }

    public BitmapUtils(Context context, String str, int i, int i2) {
        this(context, str);
        this.e.c(i);
        this.e.d(i2);
    }

    public BitmapUtils(Context context, String str, float f2) {
        this(context, str);
        this.e.a(f2);
    }

    public BitmapUtils(Context context, String str, float f2, int i) {
        this(context, str);
        this.e.a(f2);
        this.e.d(i);
    }

    public BitmapUtils a(Drawable drawable) {
        this.f.a(drawable);
        return this;
    }

    public BitmapUtils a(int i) {
        this.f.a(this.d.getResources().getDrawable(i));
        return this;
    }

    public BitmapUtils a(Bitmap bitmap) {
        this.f.a((Drawable) new BitmapDrawable(this.d.getResources(), bitmap));
        return this;
    }

    public BitmapUtils b(Drawable drawable) {
        this.f.b(drawable);
        return this;
    }

    public BitmapUtils b(int i) {
        this.f.b(this.d.getResources().getDrawable(i));
        return this;
    }

    public BitmapUtils b(Bitmap bitmap) {
        this.f.b((Drawable) new BitmapDrawable(this.d.getResources(), bitmap));
        return this;
    }

    public BitmapUtils a(int i, int i2) {
        this.f.a(new BitmapSize(i, i2));
        return this;
    }

    public BitmapUtils a(BitmapSize bitmapSize) {
        this.f.a(bitmapSize);
        return this;
    }

    public BitmapUtils a(Animation animation) {
        this.f.a(animation);
        return this;
    }

    public BitmapUtils a(boolean z) {
        this.f.a(z);
        return this;
    }

    public BitmapUtils b(boolean z) {
        this.f.b(z);
        return this;
    }

    public BitmapUtils a(Bitmap.Config config) {
        this.f.a(config);
        return this;
    }

    public BitmapUtils a(BitmapDisplayConfig bitmapDisplayConfig) {
        this.f = bitmapDisplayConfig;
        return this;
    }

    public BitmapUtils a(Downloader downloader) {
        this.e.a(downloader);
        return this;
    }

    public BitmapUtils a(long j) {
        this.e.a(j);
        return this;
    }

    public BitmapUtils c(int i) {
        this.e.a(i);
        return this;
    }

    public BitmapUtils d(int i) {
        this.e.b(i);
        return this;
    }

    public BitmapUtils e(int i) {
        this.e.e(i);
        return this;
    }

    public BitmapUtils c(boolean z) {
        this.e.a(z);
        return this;
    }

    public BitmapUtils d(boolean z) {
        this.e.b(z);
        return this;
    }

    public BitmapUtils a(FileNameGenerator fileNameGenerator) {
        this.e.a(fileNameGenerator);
        return this;
    }

    public BitmapUtils a(BitmapCacheListener bitmapCacheListener) {
        this.e.a(bitmapCacheListener);
        return this;
    }

    public <T extends View> void a(T t, String str) {
        a(t, str, (BitmapDisplayConfig) null, (BitmapLoadCallBack) null);
    }

    public <T extends View> void a(T t, String str, BitmapDisplayConfig bitmapDisplayConfig) {
        a(t, str, bitmapDisplayConfig, (BitmapLoadCallBack) null);
    }

    public <T extends View> void a(T t, String str, BitmapLoadCallBack<T> bitmapLoadCallBack) {
        a(t, str, (BitmapDisplayConfig) null, bitmapLoadCallBack);
    }

    public <T extends View> void a(T t, String str, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadCallBack<T> bitmapLoadCallBack) {
        if (t != null) {
            if (bitmapLoadCallBack == null) {
                bitmapLoadCallBack = new DefaultBitmapLoadCallBack<>();
            }
            if (bitmapDisplayConfig == null || bitmapDisplayConfig == this.f) {
                bitmapDisplayConfig = this.f.j();
            }
            BitmapSize a2 = bitmapDisplayConfig.a();
            bitmapDisplayConfig.a(BitmapCommonUtils.a(t, a2.a(), a2.b()));
            t.clearAnimation();
            if (TextUtils.isEmpty(str)) {
                bitmapLoadCallBack.a(t, str, bitmapDisplayConfig.d());
                return;
            }
            bitmapLoadCallBack.a(t, str, bitmapDisplayConfig);
            Bitmap a3 = this.e.f().a(str, bitmapDisplayConfig);
            if (a3 != null) {
                bitmapLoadCallBack.b(t, str, bitmapDisplayConfig);
                bitmapLoadCallBack.a(t, str, a3, bitmapDisplayConfig, BitmapLoadFrom.MEMORY_CACHE);
            } else if (!b(t, str, bitmapLoadCallBack)) {
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(t, str, bitmapDisplayConfig, bitmapLoadCallBack);
                PriorityExecutor j = this.e.j();
                File d2 = d(str);
                if ((d2 != null && d2.exists()) && j.b()) {
                    j = this.e.k();
                }
                bitmapLoadCallBack.a(t, (Drawable) new AsyncDrawable(bitmapDisplayConfig.c(), bitmapLoadTask));
                bitmapLoadTask.a(bitmapDisplayConfig.i());
                bitmapLoadTask.a((Executor) j, (Params[]) new Object[0]);
            }
        }
    }

    public void a() {
        this.e.p();
    }

    public void b() {
        this.e.q();
    }

    public void c() {
        this.e.r();
    }

    public void a(String str) {
        this.e.a(str);
    }

    public void b(String str) {
        this.e.b(str);
    }

    public void c(String str) {
        this.e.c(str);
    }

    public void d() {
        this.e.s();
    }

    public void e() {
        this.e.t();
    }

    public File d(String str) {
        return this.e.f().a(str);
    }

    public Bitmap a(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        if (bitmapDisplayConfig == null) {
            bitmapDisplayConfig = this.f;
        }
        return this.e.f().a(str, bitmapDisplayConfig);
    }

    public void i() {
        this.f6282a = true;
        d();
    }

    public void j() {
        this.f6282a = false;
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    public void k() {
        this.f6282a = true;
        this.b = true;
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    public boolean l() {
        return this.f6282a;
    }

    public boolean m() {
        return this.b;
    }

    /* access modifiers changed from: private */
    public static <T extends View> BitmapLoadTask<T> b(T t, BitmapLoadCallBack<T> bitmapLoadCallBack) {
        if (t == null) {
            return null;
        }
        Drawable a2 = bitmapLoadCallBack.a(t);
        if (a2 instanceof AsyncDrawable) {
            return ((AsyncDrawable) a2).a();
        }
        return null;
    }

    private static <T extends View> boolean b(T t, String str, BitmapLoadCallBack<T> bitmapLoadCallBack) {
        BitmapLoadTask<T> b2 = b(t, bitmapLoadCallBack);
        if (b2 == null) {
            return false;
        }
        String a2 = b2.b;
        if (!TextUtils.isEmpty(a2) && a2.equals(str)) {
            return true;
        }
        b2.a(true);
        return false;
    }

    public class BitmapLoadTask<T extends View> extends PriorityAsyncTask<Object, Object, Bitmap> {
        private static final int g = 0;
        private static final int h = 1;
        /* access modifiers changed from: private */
        public final String b;
        private final WeakReference<T> c;
        private final BitmapLoadCallBack<T> d;
        private final BitmapDisplayConfig e;
        private BitmapLoadFrom f = BitmapLoadFrom.DISK_CACHE;

        public BitmapLoadTask(T t, String str, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadCallBack<T> bitmapLoadCallBack) {
            if (t == null || str == null || bitmapDisplayConfig == null || bitmapLoadCallBack == null) {
                throw new IllegalArgumentException("args may not be null");
            }
            this.c = new WeakReference<>(t);
            this.d = bitmapLoadCallBack;
            this.b = str;
            this.e = bitmapDisplayConfig;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
            if (m() != false) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
            if (a() == null) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
            f(0);
            r1 = com.lidroid.xutils.BitmapUtils.d(r2.f6283a).f().b(r2.b, r2.e);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
            if (r1 != null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x005c, code lost:
            if (m() != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
            if (a() == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
            r1 = com.lidroid.xutils.BitmapUtils.d(r2.f6283a).f().a(r2.b, r2.e, (com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?>) r2);
            r2.f = com.lidroid.xutils.bitmap.callback.BitmapLoadFrom.URI;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            return r1;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0007 */
        /* JADX WARNING: Removed duplicated region for block: B:2:0x0007 A[LOOP:0: B:2:0x0007->B:29:0x0007, LOOP_START, SYNTHETIC] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.graphics.Bitmap c(java.lang.Object... r3) {
            /*
                r2 = this;
                com.lidroid.xutils.BitmapUtils r3 = com.lidroid.xutils.BitmapUtils.this
                java.lang.Object r3 = r3.c
                monitor-enter(r3)
            L_0x0007:
                com.lidroid.xutils.BitmapUtils r0 = com.lidroid.xutils.BitmapUtils.this     // Catch:{ all -> 0x007b }
                boolean r0 = r0.f6282a     // Catch:{ all -> 0x007b }
                r1 = 0
                if (r0 == 0) goto L_0x002a
                boolean r0 = r2.m()     // Catch:{ all -> 0x007b }
                if (r0 == 0) goto L_0x0017
                goto L_0x002a
            L_0x0017:
                com.lidroid.xutils.BitmapUtils r0 = com.lidroid.xutils.BitmapUtils.this     // Catch:{ Throwable -> 0x0007 }
                java.lang.Object r0 = r0.c     // Catch:{ Throwable -> 0x0007 }
                r0.wait()     // Catch:{ Throwable -> 0x0007 }
                com.lidroid.xutils.BitmapUtils r0 = com.lidroid.xutils.BitmapUtils.this     // Catch:{ Throwable -> 0x0007 }
                boolean r0 = r0.b     // Catch:{ Throwable -> 0x0007 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r3)     // Catch:{ all -> 0x007b }
                return r1
            L_0x002a:
                monitor-exit(r3)     // Catch:{ all -> 0x007b }
                boolean r3 = r2.m()
                if (r3 != 0) goto L_0x0056
                android.view.View r3 = r2.a()
                if (r3 == 0) goto L_0x0056
                r3 = 1
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r0 = 0
                java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
                r3[r0] = r1
                r2.f(r3)
                com.lidroid.xutils.BitmapUtils r3 = com.lidroid.xutils.BitmapUtils.this
                com.lidroid.xutils.bitmap.BitmapGlobalConfig r3 = r3.e
                com.lidroid.xutils.bitmap.core.BitmapCache r3 = r3.f()
                java.lang.String r0 = r2.b
                com.lidroid.xutils.bitmap.BitmapDisplayConfig r1 = r2.e
                android.graphics.Bitmap r1 = r3.b(r0, r1)
            L_0x0056:
                if (r1 != 0) goto L_0x007a
                boolean r3 = r2.m()
                if (r3 != 0) goto L_0x007a
                android.view.View r3 = r2.a()
                if (r3 == 0) goto L_0x007a
                com.lidroid.xutils.BitmapUtils r3 = com.lidroid.xutils.BitmapUtils.this
                com.lidroid.xutils.bitmap.BitmapGlobalConfig r3 = r3.e
                com.lidroid.xutils.bitmap.core.BitmapCache r3 = r3.f()
                java.lang.String r0 = r2.b
                com.lidroid.xutils.bitmap.BitmapDisplayConfig r1 = r2.e
                android.graphics.Bitmap r1 = r3.a((java.lang.String) r0, (com.lidroid.xutils.bitmap.BitmapDisplayConfig) r1, (com.lidroid.xutils.BitmapUtils.BitmapLoadTask<?>) r2)
                com.lidroid.xutils.bitmap.callback.BitmapLoadFrom r3 = com.lidroid.xutils.bitmap.callback.BitmapLoadFrom.URI
                r2.f = r3
            L_0x007a:
                return r1
            L_0x007b:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x007b }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.BitmapUtils.BitmapLoadTask.c(java.lang.Object[]):android.graphics.Bitmap");
        }

        public void a(long j, long j2) {
            f(1, Long.valueOf(j), Long.valueOf(j2));
        }

        /* access modifiers changed from: protected */
        public void b(Object... objArr) {
            View a2;
            if (objArr != null && objArr.length != 0 && (a2 = a()) != null) {
                switch (objArr[0].intValue()) {
                    case 0:
                        this.d.b(a2, this.b, this.e);
                        return;
                    case 1:
                        if (objArr.length == 3) {
                            this.d.a(a2, this.b, this.e, objArr[1].longValue(), objArr[2].longValue());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void a(Bitmap bitmap) {
            View a2 = a();
            if (a2 == null) {
                return;
            }
            if (bitmap != null) {
                this.d.a(a2, this.b, bitmap, this.e, this.f);
                return;
            }
            this.d.a(a2, this.b, this.e.d());
        }

        /* access modifiers changed from: protected */
        public void b(Bitmap bitmap) {
            synchronized (BitmapUtils.this.c) {
                BitmapUtils.this.c.notifyAll();
            }
        }

        public T a() {
            T t = (View) this.c.get();
            if (this == BitmapUtils.b(t, this.d)) {
                return t;
            }
            return null;
        }
    }
}
