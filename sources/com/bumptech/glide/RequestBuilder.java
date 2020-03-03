package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements ModelTypes<RequestBuilder<TranscodeType>>, Cloneable {

    /* renamed from: a  reason: collision with root package name */
    protected static final RequestOptions f4814a = ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(DiskCacheStrategy.c)).a(Priority.LOW)).d(true));
    private final Context b;
    private final RequestManager c;
    private final Class<TranscodeType> d;
    private final Glide e;
    private final GlideContext f;
    @NonNull
    private TransitionOptions<?, ? super TranscodeType> g;
    @Nullable
    private Object h;
    @Nullable
    private List<RequestListener<TranscodeType>> i;
    @Nullable
    private RequestBuilder<TranscodeType> j;
    @Nullable
    private RequestBuilder<TranscodeType> k;
    @Nullable
    private Float l;
    private boolean m;
    private boolean n;
    private boolean o;

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(@NonNull Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.m = true;
        this.e = glide;
        this.c = requestManager;
        this.d = cls;
        this.b = context;
        this.g = requestManager.b(cls);
        this.f = glide.f();
        a(requestManager.o());
        b((BaseRequestOptions<?>) requestManager.p());
    }

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.e, requestBuilder.c, cls, requestBuilder.b);
        this.h = requestBuilder.h;
        this.n = requestBuilder.n;
        b((BaseRequestOptions<?>) requestBuilder);
    }

    @SuppressLint({"CheckResult"})
    private void a(List<RequestListener<Object>> list) {
        for (RequestListener<Object> b2 : list) {
            b(b2);
        }
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public RequestBuilder<TranscodeType> b(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.a(baseRequestOptions);
        return (RequestBuilder) super.b(baseRequestOptions);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> a(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        this.g = (TransitionOptions) Preconditions.a(transitionOptions);
        this.m = false;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> a(@Nullable RequestListener<TranscodeType> requestListener) {
        this.i = null;
        return b(requestListener);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> b(@Nullable RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.i == null) {
                this.i = new ArrayList();
            }
            this.i.add(requestListener);
        }
        return this;
    }

    @NonNull
    public RequestBuilder<TranscodeType> a(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.k = requestBuilder;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> b(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.j = requestBuilder;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> a(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        RequestBuilder<TranscodeType> requestBuilder = null;
        if (requestBuilderArr == null || requestBuilderArr.length == 0) {
            return b((RequestBuilder) null);
        }
        for (int length = requestBuilderArr.length - 1; length >= 0; length--) {
            RequestBuilder<TranscodeType> requestBuilder2 = requestBuilderArr[length];
            if (requestBuilder2 != null) {
                requestBuilder = requestBuilder == null ? requestBuilder2 : requestBuilder2.b(requestBuilder);
            }
        }
        return b(requestBuilder);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> a(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.l = Float.valueOf(f2);
        return this;
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable Object obj) {
        return c(obj);
    }

    @NonNull
    private RequestBuilder<TranscodeType> c(@Nullable Object obj) {
        this.h = obj;
        this.n = true;
        return this;
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable Bitmap bitmap) {
        return c(bitmap).b((BaseRequestOptions<?>) RequestOptions.b(DiskCacheStrategy.b));
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable Drawable drawable) {
        return c(drawable).b((BaseRequestOptions<?>) RequestOptions.b(DiskCacheStrategy.b));
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable String str) {
        return c(str);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable Uri uri) {
        return c(uri);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable File file) {
        return c(file);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable @RawRes @DrawableRes Integer num) {
        return c(num).b((BaseRequestOptions<?>) RequestOptions.b(ApplicationVersionSignature.a(this.b)));
    }

    @Deprecated
    @CheckResult
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable URL url) {
        return c(url);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<TranscodeType> a(@Nullable byte[] bArr) {
        RequestBuilder<TranscodeType> c2 = c(bArr);
        if (!c2.w()) {
            c2 = c2.b((BaseRequestOptions<?>) RequestOptions.b(DiskCacheStrategy.b));
        }
        return !c2.x() ? c2.b((BaseRequestOptions<?>) RequestOptions.e(true)) : c2;
    }

    @CheckResult
    /* renamed from: a */
    public RequestBuilder<TranscodeType> e() {
        RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.clone();
        requestBuilder.g = requestBuilder.g.clone();
        return requestBuilder;
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y a(@NonNull Y y) {
        return a(y, (RequestListener) null, Executors.a());
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public <Y extends Target<TranscodeType>> Y a(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, Executor executor) {
        return a(y, requestListener, this, executor);
    }

    private <Y extends Target<TranscodeType>> Y a(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.a(y);
        if (this.n) {
            Request b2 = b(y, requestListener, baseRequestOptions, executor);
            Request a2 = y.a();
            if (!b2.a(a2) || a(baseRequestOptions, a2)) {
                this.c.a((Target<?>) y);
                y.a(b2);
                this.c.a(y, b2);
                return y;
            }
            b2.h();
            if (!((Request) Preconditions.a(a2)).c()) {
                a2.a();
            }
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    private boolean a(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.K() && request.d();
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> a(@NonNull ImageView imageView) {
        BaseRequestOptions baseRequestOptions;
        Util.a();
        Preconditions.a(imageView);
        if (!g() && f() && imageView.getScaleType() != null) {
            switch (AnonymousClass1.f4815a[imageView.getScaleType().ordinal()]) {
                case 1:
                    baseRequestOptions = clone().j();
                    break;
                case 2:
                    baseRequestOptions = clone().n();
                    break;
                case 3:
                case 4:
                case 5:
                    baseRequestOptions = clone().l();
                    break;
                case 6:
                    baseRequestOptions = clone().n();
                    break;
            }
        }
        baseRequestOptions = this;
        return (ViewTarget) a(this.f.buildImageViewTarget(imageView, this.d), (RequestListener) null, baseRequestOptions, Executors.a());
    }

    @Deprecated
    public FutureTarget<TranscodeType> a(int i2, int i3) {
        return b(i2, i3);
    }

    @NonNull
    public FutureTarget<TranscodeType> b() {
        return b(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    public FutureTarget<TranscodeType> b(int i2, int i3) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i2, i3);
        return (FutureTarget) a(requestFutureTarget, requestFutureTarget, Executors.b());
    }

    @NonNull
    public Target<TranscodeType> c(int i2, int i3) {
        return a(PreloadTarget.a(this.c, i2, i3));
    }

    @NonNull
    public Target<TranscodeType> c() {
        return c(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    @CheckResult
    public <Y extends Target<File>> Y b(@NonNull Y y) {
        return d().a(y);
    }

    @Deprecated
    @CheckResult
    public FutureTarget<File> d(int i2, int i3) {
        return d().b(i2, i3);
    }

    /* access modifiers changed from: protected */
    @CheckResult
    @NonNull
    public RequestBuilder<File> d() {
        return new RequestBuilder(File.class, this).b((BaseRequestOptions<?>) f4814a);
    }

    /* renamed from: com.bumptech.glide.RequestBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f4815a = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        static {
            /*
                com.bumptech.glide.Priority[] r0 = com.bumptech.glide.Priority.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bumptech.glide.Priority r2 = com.bumptech.glide.Priority.LOW     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bumptech.glide.Priority r3 = com.bumptech.glide.Priority.NORMAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x002a }
                com.bumptech.glide.Priority r4 = com.bumptech.glide.Priority.HIGH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.bumptech.glide.Priority r5 = com.bumptech.glide.Priority.IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                android.widget.ImageView$ScaleType[] r4 = android.widget.ImageView.ScaleType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f4815a = r4
                int[] r4 = f4815a     // Catch:{ NoSuchFieldError -> 0x0048 }
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x0052 }
                android.widget.ImageView$ScaleType r4 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x005c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x0066 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x0066 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0066 }
            L_0x0066:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x0071 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x007c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x007c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007c }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007c }
            L_0x007c:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x0087 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = f4815a     // Catch:{ NoSuchFieldError -> 0x0093 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.MATRIX     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    @NonNull
    private Priority b(@NonNull Priority priority) {
        switch (priority) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                throw new IllegalArgumentException("unknown priority: " + N());
        }
    }

    private Request b(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return a(target, requestListener, (RequestCoordinator) null, this.g, baseRequestOptions.N(), baseRequestOptions.O(), baseRequestOptions.Q(), baseRequestOptions, executor);
    }

    private Request a(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i2, int i3, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        ErrorRequestCoordinator errorRequestCoordinator;
        ErrorRequestCoordinator errorRequestCoordinator2;
        if (this.k != null) {
            errorRequestCoordinator2 = new ErrorRequestCoordinator(requestCoordinator);
            errorRequestCoordinator = errorRequestCoordinator2;
        } else {
            errorRequestCoordinator = null;
            errorRequestCoordinator2 = requestCoordinator;
        }
        Request b2 = b(target, requestListener, errorRequestCoordinator2, transitionOptions, priority, i2, i3, baseRequestOptions, executor);
        if (errorRequestCoordinator == null) {
            return b2;
        }
        int O = this.k.O();
        int Q = this.k.Q();
        if (Util.a(i2, i3) && !this.k.P()) {
            O = baseRequestOptions.O();
            Q = baseRequestOptions.Q();
        }
        ErrorRequestCoordinator errorRequestCoordinator3 = errorRequestCoordinator;
        errorRequestCoordinator3.a(b2, this.k.a(target, requestListener, (RequestCoordinator) errorRequestCoordinator, this.k.g, this.k.N(), O, Q, (BaseRequestOptions<?>) this.k, executor));
        return errorRequestCoordinator3;
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [com.bumptech.glide.request.BaseRequestOptions<?>, com.bumptech.glide.request.BaseRequestOptions] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.bumptech.glide.request.Request b(com.bumptech.glide.request.target.Target<TranscodeType> r22, com.bumptech.glide.request.RequestListener<TranscodeType> r23, @android.support.annotation.Nullable com.bumptech.glide.request.RequestCoordinator r24, com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r25, com.bumptech.glide.Priority r26, int r27, int r28, com.bumptech.glide.request.BaseRequestOptions<?> r29, java.util.concurrent.Executor r30) {
        /*
            r21 = this;
            r10 = r21
            r4 = r24
            r11 = r26
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.j
            if (r0 == 0) goto L_0x0097
            boolean r0 = r10.o
            if (r0 != 0) goto L_0x008f
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.j
            com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r0 = r0.g
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.j
            boolean r1 = r1.m
            if (r1 == 0) goto L_0x001b
            r15 = r25
            goto L_0x001c
        L_0x001b:
            r15 = r0
        L_0x001c:
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.j
            boolean r0 = r0.M()
            if (r0 == 0) goto L_0x002d
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.j
            com.bumptech.glide.Priority r0 = r0.N()
        L_0x002a:
            r16 = r0
            goto L_0x0032
        L_0x002d:
            com.bumptech.glide.Priority r0 = r10.b((com.bumptech.glide.Priority) r11)
            goto L_0x002a
        L_0x0032:
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.j
            int r0 = r0.O()
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.j
            int r1 = r1.Q()
            boolean r2 = com.bumptech.glide.util.Util.a((int) r27, (int) r28)
            if (r2 == 0) goto L_0x0054
            com.bumptech.glide.RequestBuilder<TranscodeType> r2 = r10.j
            boolean r2 = r2.P()
            if (r2 != 0) goto L_0x0054
            int r0 = r29.O()
            int r1 = r29.Q()
        L_0x0054:
            r17 = r0
            r18 = r1
            com.bumptech.glide.request.ThumbnailRequestCoordinator r14 = new com.bumptech.glide.request.ThumbnailRequestCoordinator
            r14.<init>(r4)
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r29
            r4 = r14
            r5 = r25
            r6 = r26
            r7 = r27
            r8 = r28
            r9 = r30
            com.bumptech.glide.request.Request r0 = r0.a(r1, r2, (com.bumptech.glide.request.BaseRequestOptions<?>) r3, (com.bumptech.glide.request.RequestCoordinator) r4, r5, (com.bumptech.glide.Priority) r6, (int) r7, (int) r8, (java.util.concurrent.Executor) r9)
            r1 = 1
            r10.o = r1
            com.bumptech.glide.RequestBuilder<TranscodeType> r11 = r10.j
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.j
            r12 = r22
            r13 = r23
            r2 = r14
            r19 = r1
            r20 = r30
            com.bumptech.glide.request.Request r1 = r11.a(r12, r13, (com.bumptech.glide.request.RequestCoordinator) r14, r15, (com.bumptech.glide.Priority) r16, (int) r17, (int) r18, (com.bumptech.glide.request.BaseRequestOptions<?>) r19, (java.util.concurrent.Executor) r20)
            r3 = 0
            r10.o = r3
            r2.a(r0, r1)
            return r2
        L_0x008f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()"
            r0.<init>(r1)
            throw r0
        L_0x0097:
            java.lang.Float r0 = r10.l
            if (r0 == 0) goto L_0x00d5
            com.bumptech.glide.request.ThumbnailRequestCoordinator r12 = new com.bumptech.glide.request.ThumbnailRequestCoordinator
            r12.<init>(r4)
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r29
            r4 = r12
            r5 = r25
            r6 = r26
            r7 = r27
            r8 = r28
            r9 = r30
            com.bumptech.glide.request.Request r13 = r0.a(r1, r2, (com.bumptech.glide.request.BaseRequestOptions<?>) r3, (com.bumptech.glide.request.RequestCoordinator) r4, r5, (com.bumptech.glide.Priority) r6, (int) r7, (int) r8, (java.util.concurrent.Executor) r9)
            com.bumptech.glide.request.BaseRequestOptions r0 = r29.clone()
            java.lang.Float r1 = r10.l
            float r1 = r1.floatValue()
            com.bumptech.glide.request.BaseRequestOptions r3 = r0.b((float) r1)
            com.bumptech.glide.Priority r6 = r10.b((com.bumptech.glide.Priority) r11)
            r0 = r21
            r1 = r22
            com.bumptech.glide.request.Request r0 = r0.a(r1, r2, (com.bumptech.glide.request.BaseRequestOptions<?>) r3, (com.bumptech.glide.request.RequestCoordinator) r4, r5, (com.bumptech.glide.Priority) r6, (int) r7, (int) r8, (java.util.concurrent.Executor) r9)
            r12.a(r13, r0)
            return r12
        L_0x00d5:
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r29
            r4 = r24
            r5 = r25
            r6 = r26
            r7 = r27
            r8 = r28
            r9 = r30
            com.bumptech.glide.request.Request r0 = r0.a(r1, r2, (com.bumptech.glide.request.BaseRequestOptions<?>) r3, (com.bumptech.glide.request.RequestCoordinator) r4, r5, (com.bumptech.glide.Priority) r6, (int) r7, (int) r8, (java.util.concurrent.Executor) r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.b(com.bumptech.glide.request.target.Target, com.bumptech.glide.request.RequestListener, com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.TransitionOptions, com.bumptech.glide.Priority, int, int, com.bumptech.glide.request.BaseRequestOptions, java.util.concurrent.Executor):com.bumptech.glide.request.Request");
    }

    private Request a(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i2, int i3, Executor executor) {
        return SingleRequest.a(this.b, this.f, this.h, this.d, baseRequestOptions, i2, i3, priority, target, requestListener, this.i, requestCoordinator, this.f.getEngine(), transitionOptions.d(), executor);
    }
}
