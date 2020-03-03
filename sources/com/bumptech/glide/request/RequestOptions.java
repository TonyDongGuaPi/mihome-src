package com.bumptech.glide.request;

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
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private static RequestOptions f5060a;
    @Nullable
    private static RequestOptions b;
    @Nullable
    private static RequestOptions c;
    @Nullable
    private static RequestOptions d;
    @Nullable
    private static RequestOptions e;
    @Nullable
    private static RequestOptions f;
    @Nullable
    private static RequestOptions g;
    @Nullable
    private static RequestOptions h;

    @CheckResult
    @NonNull
    public static RequestOptions a(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return (RequestOptions) new RequestOptions().b(f2);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (RequestOptions) new RequestOptions().a(diskCacheStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull Priority priority) {
        return (RequestOptions) new RequestOptions().a(priority);
    }

    @CheckResult
    @NonNull
    public static RequestOptions a(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().c(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions g(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().a(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().e(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions h(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().c(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions e(boolean z) {
        if (z) {
            if (f5060a == null) {
                f5060a = (RequestOptions) ((RequestOptions) new RequestOptions().d(true)).u();
            }
            return f5060a;
        }
        if (b == null) {
            b = (RequestOptions) ((RequestOptions) new RequestOptions().d(false)).u();
        }
        return b;
    }

    @CheckResult
    @NonNull
    public static RequestOptions a(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return (RequestOptions) new RequestOptions().e(i, i2);
    }

    @CheckResult
    @NonNull
    public static RequestOptions i(@IntRange(from = 0) int i) {
        return a(i, i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull Key key) {
        return (RequestOptions) new RequestOptions().a(key);
    }

    @CheckResult
    @NonNull
    public static RequestOptions a() {
        if (c == null) {
            c = (RequestOptions) ((RequestOptions) new RequestOptions().m()).u();
        }
        return c;
    }

    @CheckResult
    @NonNull
    public static RequestOptions b() {
        if (d == null) {
            d = (RequestOptions) ((RequestOptions) new RequestOptions().o()).u();
        }
        return d;
    }

    @CheckResult
    @NonNull
    public static RequestOptions c() {
        if (e == null) {
            e = (RequestOptions) ((RequestOptions) new RequestOptions().k()).u();
        }
        return e;
    }

    @CheckResult
    @NonNull
    public static RequestOptions d() {
        if (f == null) {
            f = (RequestOptions) ((RequestOptions) new RequestOptions().q()).u();
        }
        return f;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.bumptech.glide.request.RequestOptions c(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r1) {
        /*
            com.bumptech.glide.request.RequestOptions r0 = new com.bumptech.glide.request.RequestOptions
            r0.<init>()
            com.bumptech.glide.request.BaseRequestOptions r1 = r0.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r1)
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestOptions.c(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.RequestOptions");
    }

    @CheckResult
    @NonNull
    public static RequestOptions W() {
        if (g == null) {
            g = (RequestOptions) ((RequestOptions) new RequestOptions().r()).u();
        }
        return g;
    }

    @CheckResult
    @NonNull
    public static <T> RequestOptions b(@NonNull Option<T> option, @NonNull T t) {
        return (RequestOptions) new RequestOptions().a(option, t);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull Class<?> cls) {
        return (RequestOptions) new RequestOptions().a(cls);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull DecodeFormat decodeFormat) {
        return (RequestOptions) new RequestOptions().a(decodeFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@IntRange(from = 0) long j) {
        return (RequestOptions) new RequestOptions().a(j);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull DownsampleStrategy downsampleStrategy) {
        return (RequestOptions) new RequestOptions().a(downsampleStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions j(@IntRange(from = 0) int i) {
        return (RequestOptions) new RequestOptions().f(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions k(@IntRange(from = 0, to = 100) int i) {
        return (RequestOptions) new RequestOptions().e(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions b(@NonNull Bitmap.CompressFormat compressFormat) {
        return (RequestOptions) new RequestOptions().a(compressFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions X() {
        if (h == null) {
            h = (RequestOptions) ((RequestOptions) new RequestOptions().s()).u();
        }
        return h;
    }
}
