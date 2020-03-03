package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Map;

public abstract class BaseRequestOptions<T extends BaseRequestOptions<T>> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5057a = -1;
    private static final int b = 2;
    private static final int c = 4;
    private static final int d = 8;
    private static final int e = 16;
    private static final int f = 32;
    private static final int g = 64;
    private static final int h = 128;
    private static final int i = 256;
    private static final int j = 512;
    private static final int k = 1024;
    private static final int l = 2048;
    private static final int m = 4096;
    private static final int n = 8192;
    private static final int o = 16384;
    private static final int p = 32768;
    private static final int q = 65536;
    private static final int r = 131072;
    private static final int s = 262144;
    private static final int t = 524288;
    private static final int u = 1048576;
    private int A;
    @Nullable
    private Drawable B;
    private int C;
    private boolean D = true;
    private int E = -1;
    private int F = -1;
    @NonNull
    private Key G = EmptySignature.a();
    private boolean H;
    private boolean I = true;
    @Nullable
    private Drawable J;
    private int K;
    @NonNull
    private Options L = new Options();
    @NonNull
    private Map<Class<?>, Transformation<?>> M = new CachedHashCodeArrayMap();
    @NonNull
    private Class<?> N = Object.class;
    private boolean O;
    @Nullable
    private Resources.Theme P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T = true;
    private boolean U;
    private int v;
    private float w = 1.0f;
    @NonNull
    private DiskCacheStrategy x = DiskCacheStrategy.e;
    @NonNull
    private Priority y = Priority.NORMAL;
    @Nullable
    private Drawable z;

    private static boolean a(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    private T b() {
        return this;
    }

    @CheckResult
    @NonNull
    public T b(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (this.Q) {
            return clone().b(f2);
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.w = f2;
        this.v |= 2;
        return a();
    }

    @CheckResult
    @NonNull
    public T a(boolean z2) {
        if (this.Q) {
            return clone().a(z2);
        }
        this.R = z2;
        this.v |= 262144;
        return a();
    }

    @CheckResult
    @NonNull
    public T b(boolean z2) {
        if (this.Q) {
            return clone().b(z2);
        }
        this.U = z2;
        this.v |= 1048576;
        return a();
    }

    @CheckResult
    @NonNull
    public T c(boolean z2) {
        if (this.Q) {
            return clone().c(z2);
        }
        this.S = z2;
        this.v |= 524288;
        return a();
    }

    @CheckResult
    @NonNull
    public T a(@NonNull DiskCacheStrategy diskCacheStrategy) {
        if (this.Q) {
            return clone().a(diskCacheStrategy);
        }
        this.x = (DiskCacheStrategy) Preconditions.a(diskCacheStrategy);
        this.v |= 4;
        return a();
    }

    @CheckResult
    @NonNull
    public T a(@NonNull Priority priority) {
        if (this.Q) {
            return clone().a(priority);
        }
        this.y = (Priority) Preconditions.a(priority);
        this.v |= 8;
        return a();
    }

    @CheckResult
    @NonNull
    public T c(@Nullable Drawable drawable) {
        if (this.Q) {
            return clone().c(drawable);
        }
        this.B = drawable;
        this.v |= 64;
        this.C = 0;
        this.v &= -129;
        return a();
    }

    @CheckResult
    @NonNull
    public T a(@DrawableRes int i2) {
        if (this.Q) {
            return clone().a(i2);
        }
        this.C = i2;
        this.v |= 128;
        this.B = null;
        this.v &= -65;
        return a();
    }

    @CheckResult
    @NonNull
    public T d(@Nullable Drawable drawable) {
        if (this.Q) {
            return clone().d(drawable);
        }
        this.J = drawable;
        this.v |= 8192;
        this.K = 0;
        this.v &= -16385;
        return a();
    }

    @CheckResult
    @NonNull
    public T b(@DrawableRes int i2) {
        if (this.Q) {
            return clone().b(i2);
        }
        this.K = i2;
        this.v |= 16384;
        this.J = null;
        this.v &= -8193;
        return a();
    }

    @CheckResult
    @NonNull
    public T e(@Nullable Drawable drawable) {
        if (this.Q) {
            return clone().e(drawable);
        }
        this.z = drawable;
        this.v |= 16;
        this.A = 0;
        this.v &= -33;
        return a();
    }

    @CheckResult
    @NonNull
    public T c(@DrawableRes int i2) {
        if (this.Q) {
            return clone().c(i2);
        }
        this.A = i2;
        this.v |= 32;
        this.z = null;
        this.v &= -17;
        return a();
    }

    @CheckResult
    @NonNull
    public T a(@Nullable Resources.Theme theme) {
        if (this.Q) {
            return clone().a(theme);
        }
        this.P = theme;
        this.v |= 32768;
        return a();
    }

    @CheckResult
    @NonNull
    public T d(boolean z2) {
        if (this.Q) {
            return clone().d(true);
        }
        this.D = !z2;
        this.v |= 256;
        return a();
    }

    @CheckResult
    @NonNull
    public T e(int i2, int i3) {
        if (this.Q) {
            return clone().e(i2, i3);
        }
        this.F = i2;
        this.E = i3;
        this.v |= 512;
        return a();
    }

    @CheckResult
    @NonNull
    public T d(int i2) {
        return e(i2, i2);
    }

    @CheckResult
    @NonNull
    public T a(@NonNull Key key) {
        if (this.Q) {
            return clone().a(key);
        }
        this.G = (Key) Preconditions.a(key);
        this.v |= 1024;
        return a();
    }

    @CheckResult
    /* renamed from: e */
    public T clone() {
        try {
            T t2 = (BaseRequestOptions) super.clone();
            t2.L = new Options();
            t2.L.a(this.L);
            t2.M = new CachedHashCodeArrayMap();
            t2.M.putAll(this.M);
            t2.O = false;
            t2.Q = false;
            return t2;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Option, java.lang.Object, com.bumptech.glide.load.Option<Y>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T a(@android.support.annotation.NonNull com.bumptech.glide.load.Option<Y> r2, @android.support.annotation.NonNull Y r3) {
        /*
            r1 = this;
            boolean r0 = r1.Q
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r2 = r0.a(r2, r3)
            return r2
        L_0x000d:
            com.bumptech.glide.util.Preconditions.a(r2)
            com.bumptech.glide.util.Preconditions.a(r3)
            com.bumptech.glide.load.Options r0 = r1.L
            r0.a(r2, r3)
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(com.bumptech.glide.load.Option, java.lang.Object):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T a(@NonNull Class<?> cls) {
        if (this.Q) {
            return clone().a(cls);
        }
        this.N = (Class) Preconditions.a(cls);
        this.v |= 4096;
        return a();
    }

    public final boolean f() {
        return this.I;
    }

    public final boolean g() {
        return g(2048);
    }

    public final boolean h() {
        return this.O;
    }

    @CheckResult
    @NonNull
    public T a(@NonNull Bitmap.CompressFormat compressFormat) {
        return a(BitmapEncoder.b, Preconditions.a(compressFormat));
    }

    @CheckResult
    @NonNull
    public T e(@IntRange(from = 0, to = 100) int i2) {
        return a(BitmapEncoder.f4993a, Integer.valueOf(i2));
    }

    @CheckResult
    @NonNull
    public T a(@IntRange(from = 0) long j2) {
        return a(VideoDecoder.c, Long.valueOf(j2));
    }

    @CheckResult
    @NonNull
    public T a(@NonNull DecodeFormat decodeFormat) {
        Preconditions.a(decodeFormat);
        return a(Downsampler.b, decodeFormat).a(GifOptions.f5028a, decodeFormat);
    }

    @CheckResult
    @NonNull
    public T i() {
        return a(Downsampler.e, false);
    }

    @CheckResult
    @NonNull
    public T a(@NonNull DownsampleStrategy downsampleStrategy) {
        return a(DownsampleStrategy.h, Preconditions.a(downsampleStrategy));
    }

    @CheckResult
    @NonNull
    public T f(@IntRange(from = 0) int i2) {
        return a(HttpGlideUrlLoader.f4982a, Integer.valueOf(i2));
    }

    @CheckResult
    @NonNull
    public T j() {
        return a(DownsampleStrategy.b, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T k() {
        return b(DownsampleStrategy.b, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T l() {
        return d(DownsampleStrategy.f4999a, new FitCenter());
    }

    @CheckResult
    @NonNull
    public T m() {
        return c(DownsampleStrategy.f4999a, new FitCenter());
    }

    @CheckResult
    @NonNull
    public T n() {
        return d(DownsampleStrategy.e, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T o() {
        return c(DownsampleStrategy.e, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T p() {
        return a(DownsampleStrategy.b, (Transformation<Bitmap>) new CircleCrop());
    }

    @CheckResult
    @NonNull
    public T q() {
        return b(DownsampleStrategy.e, (Transformation<Bitmap>) new CircleCrop());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T a(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.Q
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r2 = r0.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r2
        L_0x000d:
            r1.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2)
            r2 = 0
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T b(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.Q
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r2 = r0.b((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r2
        L_0x000d:
            r1.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2)
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.b(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T c(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.c(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T d(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.d(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T a(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r1, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2, boolean r3) {
        /*
            r0 = this;
            if (r3 == 0) goto L_0x0007
            com.bumptech.glide.request.BaseRequestOptions r1 = r0.b((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r1, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
            goto L_0x000b
        L_0x0007:
            com.bumptech.glide.request.BaseRequestOptions r1 = r0.a((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r1, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
        L_0x000b:
            r2 = 1
            r1.T = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T a(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T a(@NonNull Transformation<Bitmap>... transformationArr) {
        if (transformationArr.length > 1) {
            return a((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformationArr), true);
        }
        if (transformationArr.length == 1) {
            return a((Transformation<Bitmap>) transformationArr[0]);
        }
        return a();
    }

    @CheckResult
    @NonNull
    @Deprecated
    public T b(@NonNull Transformation<Bitmap>... transformationArr) {
        return a((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformationArr), true);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T b(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.b(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T a(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = r2.Q
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r2.clone()
            com.bumptech.glide.request.BaseRequestOptions r3 = r0.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r4)
            return r3
        L_0x000d:
            com.bumptech.glide.load.resource.bitmap.DrawableTransformation r0 = new com.bumptech.glide.load.resource.bitmap.DrawableTransformation
            r0.<init>(r3, r4)
            java.lang.Class<android.graphics.Bitmap> r1 = android.graphics.Bitmap.class
            r2.a(r1, r3, (boolean) r4)
            java.lang.Class<android.graphics.drawable.Drawable> r1 = android.graphics.drawable.Drawable.class
            r2.a(r1, r0, (boolean) r4)
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r1 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.Transformation r0 = r0.a()
            r2.a(r1, r0, (boolean) r4)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r0 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.GifDrawableTransformation r1 = new com.bumptech.glide.load.resource.gif.GifDrawableTransformation
            r1.<init>(r3)
            r2.a(r0, r1, (boolean) r4)
            com.bumptech.glide.request.BaseRequestOptions r3 = r2.a()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T a(@android.support.annotation.NonNull java.lang.Class<Y> r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a(r2, r3, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Object, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation, java.lang.Object] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 2 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T a(@android.support.annotation.NonNull java.lang.Class<Y> r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3, boolean r4) {
        /*
            r1 = this;
            boolean r0 = r1.Q
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r2 = r0.a(r2, r3, (boolean) r4)
            return r2
        L_0x000d:
            com.bumptech.glide.util.Preconditions.a(r2)
            com.bumptech.glide.util.Preconditions.a(r3)
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r0 = r1.M
            r0.put(r2, r3)
            int r2 = r1.v
            r2 = r2 | 2048(0x800, float:2.87E-42)
            r1.v = r2
            r2 = 1
            r1.I = r2
            int r3 = r1.v
            r0 = 65536(0x10000, float:9.18355E-41)
            r3 = r3 | r0
            r1.v = r3
            r3 = 0
            r1.T = r3
            if (r4 == 0) goto L_0x0036
            int r3 = r1.v
            r4 = 131072(0x20000, float:1.83671E-40)
            r3 = r3 | r4
            r1.v = r3
            r1.H = r2
        L_0x0036:
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.a(java.lang.Class, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T b(@android.support.annotation.NonNull java.lang.Class<Y> r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r2 = r1.a(r2, r3, (boolean) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.b(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T r() {
        if (this.Q) {
            return clone().r();
        }
        this.M.clear();
        this.v &= -2049;
        this.H = false;
        this.v &= -131073;
        this.I = false;
        this.v |= 65536;
        this.T = true;
        return a();
    }

    @CheckResult
    @NonNull
    public T s() {
        return a(GifOptions.b, true);
    }

    @CheckResult
    @NonNull
    public T b(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        if (this.Q) {
            return clone().b(baseRequestOptions);
        }
        if (a(baseRequestOptions.v, 2)) {
            this.w = baseRequestOptions.w;
        }
        if (a(baseRequestOptions.v, 262144)) {
            this.R = baseRequestOptions.R;
        }
        if (a(baseRequestOptions.v, 1048576)) {
            this.U = baseRequestOptions.U;
        }
        if (a(baseRequestOptions.v, 4)) {
            this.x = baseRequestOptions.x;
        }
        if (a(baseRequestOptions.v, 8)) {
            this.y = baseRequestOptions.y;
        }
        if (a(baseRequestOptions.v, 16)) {
            this.z = baseRequestOptions.z;
            this.A = 0;
            this.v &= -33;
        }
        if (a(baseRequestOptions.v, 32)) {
            this.A = baseRequestOptions.A;
            this.z = null;
            this.v &= -17;
        }
        if (a(baseRequestOptions.v, 64)) {
            this.B = baseRequestOptions.B;
            this.C = 0;
            this.v &= -129;
        }
        if (a(baseRequestOptions.v, 128)) {
            this.C = baseRequestOptions.C;
            this.B = null;
            this.v &= -65;
        }
        if (a(baseRequestOptions.v, 256)) {
            this.D = baseRequestOptions.D;
        }
        if (a(baseRequestOptions.v, 512)) {
            this.F = baseRequestOptions.F;
            this.E = baseRequestOptions.E;
        }
        if (a(baseRequestOptions.v, 1024)) {
            this.G = baseRequestOptions.G;
        }
        if (a(baseRequestOptions.v, 4096)) {
            this.N = baseRequestOptions.N;
        }
        if (a(baseRequestOptions.v, 8192)) {
            this.J = baseRequestOptions.J;
            this.K = 0;
            this.v &= -16385;
        }
        if (a(baseRequestOptions.v, 16384)) {
            this.K = baseRequestOptions.K;
            this.J = null;
            this.v &= -8193;
        }
        if (a(baseRequestOptions.v, 32768)) {
            this.P = baseRequestOptions.P;
        }
        if (a(baseRequestOptions.v, 65536)) {
            this.I = baseRequestOptions.I;
        }
        if (a(baseRequestOptions.v, 131072)) {
            this.H = baseRequestOptions.H;
        }
        if (a(baseRequestOptions.v, 2048)) {
            this.M.putAll(baseRequestOptions.M);
            this.T = baseRequestOptions.T;
        }
        if (a(baseRequestOptions.v, 524288)) {
            this.S = baseRequestOptions.S;
        }
        if (!this.I) {
            this.M.clear();
            this.v &= -2049;
            this.H = false;
            this.v &= -131073;
            this.T = true;
        }
        this.v |= baseRequestOptions.v;
        this.L.a(baseRequestOptions.L);
        return a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseRequestOptions)) {
            return false;
        }
        BaseRequestOptions baseRequestOptions = (BaseRequestOptions) obj;
        if (Float.compare(baseRequestOptions.w, this.w) == 0 && this.A == baseRequestOptions.A && Util.a((Object) this.z, (Object) baseRequestOptions.z) && this.C == baseRequestOptions.C && Util.a((Object) this.B, (Object) baseRequestOptions.B) && this.K == baseRequestOptions.K && Util.a((Object) this.J, (Object) baseRequestOptions.J) && this.D == baseRequestOptions.D && this.E == baseRequestOptions.E && this.F == baseRequestOptions.F && this.H == baseRequestOptions.H && this.I == baseRequestOptions.I && this.R == baseRequestOptions.R && this.S == baseRequestOptions.S && this.x.equals(baseRequestOptions.x) && this.y == baseRequestOptions.y && this.L.equals(baseRequestOptions.L) && this.M.equals(baseRequestOptions.M) && this.N.equals(baseRequestOptions.N) && Util.a((Object) this.G, (Object) baseRequestOptions.G) && Util.a((Object) this.P, (Object) baseRequestOptions.P)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Util.a((Object) this.P, Util.a((Object) this.G, Util.a((Object) this.N, Util.a((Object) this.M, Util.a((Object) this.L, Util.a((Object) this.y, Util.a((Object) this.x, Util.a(this.S, Util.a(this.R, Util.a(this.I, Util.a(this.H, Util.b(this.F, Util.b(this.E, Util.a(this.D, Util.a((Object) this.J, Util.b(this.K, Util.a((Object) this.B, Util.b(this.C, Util.a((Object) this.z, Util.b(this.A, Util.a(this.w)))))))))))))))))))));
    }

    @NonNull
    public T t() {
        this.O = true;
        return b();
    }

    @NonNull
    public T u() {
        if (!this.O || this.Q) {
            this.Q = true;
            return t();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    @NonNull
    private T a() {
        if (!this.O) {
            return b();
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    /* access modifiers changed from: protected */
    public boolean v() {
        return this.Q;
    }

    public final boolean w() {
        return g(4);
    }

    public final boolean x() {
        return g(256);
    }

    @NonNull
    public final Map<Class<?>, Transformation<?>> y() {
        return this.M;
    }

    public final boolean z() {
        return this.H;
    }

    @NonNull
    public final Options A() {
        return this.L;
    }

    @NonNull
    public final Class<?> B() {
        return this.N;
    }

    @NonNull
    public final DiskCacheStrategy C() {
        return this.x;
    }

    @Nullable
    public final Drawable D() {
        return this.z;
    }

    public final int E() {
        return this.A;
    }

    public final int F() {
        return this.C;
    }

    @Nullable
    public final Drawable G() {
        return this.B;
    }

    public final int H() {
        return this.K;
    }

    @Nullable
    public final Drawable I() {
        return this.J;
    }

    @Nullable
    public final Resources.Theme J() {
        return this.P;
    }

    public final boolean K() {
        return this.D;
    }

    @NonNull
    public final Key L() {
        return this.G;
    }

    public final boolean M() {
        return g(8);
    }

    @NonNull
    public final Priority N() {
        return this.y;
    }

    public final int O() {
        return this.F;
    }

    public final boolean P() {
        return Util.a(this.F, this.E);
    }

    public final int Q() {
        return this.E;
    }

    public final float R() {
        return this.w;
    }

    /* access modifiers changed from: package-private */
    public boolean S() {
        return this.T;
    }

    private boolean g(int i2) {
        return a(this.v, i2);
    }

    public final boolean T() {
        return this.R;
    }

    public final boolean U() {
        return this.U;
    }

    public final boolean V() {
        return this.S;
    }
}
