package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.view.View;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RequestManager implements ModelTypes<RequestBuilder<Drawable>>, LifecycleListener {
    private static final RequestOptions d = ((RequestOptions) RequestOptions.b((Class<?>) Bitmap.class).t());
    private static final RequestOptions e = ((RequestOptions) RequestOptions.b((Class<?>) GifDrawable.class).t());
    private static final RequestOptions f = ((RequestOptions) ((RequestOptions) RequestOptions.b(DiskCacheStrategy.c).a(Priority.LOW)).d(true));

    /* renamed from: a  reason: collision with root package name */
    protected final Glide f4816a;
    protected final Context b;
    final Lifecycle c;
    @GuardedBy("this")
    private final RequestTracker g;
    @GuardedBy("this")
    private final RequestManagerTreeNode h;
    @GuardedBy("this")
    private final TargetTracker i;
    private final Runnable j;
    private final Handler k;
    private final ConnectivityMonitor l;
    private final CopyOnWriteArrayList<RequestListener<Object>> m;
    @GuardedBy("this")
    private RequestOptions n;

    public RequestManager(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        this(glide, lifecycle, requestManagerTreeNode, new RequestTracker(), glide.e(), context);
    }

    RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker, ConnectivityMonitorFactory connectivityMonitorFactory, Context context) {
        this.i = new TargetTracker();
        this.j = new Runnable() {
            public void run() {
                RequestManager.this.c.a(RequestManager.this);
            }
        };
        this.k = new Handler(Looper.getMainLooper());
        this.f4816a = glide;
        this.c = lifecycle;
        this.h = requestManagerTreeNode;
        this.g = requestTracker;
        this.b = context;
        this.l = connectivityMonitorFactory.a(context.getApplicationContext(), new RequestManagerConnectivityListener(requestTracker));
        if (Util.d()) {
            this.k.post(this.j);
        } else {
            lifecycle.a(this);
        }
        lifecycle.a(this.l);
        this.m = new CopyOnWriteArrayList<>(glide.f().getDefaultRequestListeners());
        a(glide.f().getDefaultRequestOptions());
        glide.a(this);
    }

    /* access modifiers changed from: protected */
    public synchronized void a(@NonNull RequestOptions requestOptions) {
        this.n = (RequestOptions) ((RequestOptions) requestOptions.clone()).u();
    }

    private synchronized void d(@NonNull RequestOptions requestOptions) {
        this.n = (RequestOptions) this.n.b((BaseRequestOptions<?>) requestOptions);
    }

    @NonNull
    public synchronized RequestManager b(@NonNull RequestOptions requestOptions) {
        d(requestOptions);
        return this;
    }

    @NonNull
    public synchronized RequestManager c(@NonNull RequestOptions requestOptions) {
        a(requestOptions);
        return this;
    }

    public RequestManager a(RequestListener<Object> requestListener) {
        this.m.add(requestListener);
        return this;
    }

    public synchronized boolean a() {
        return this.g.a();
    }

    public synchronized void b() {
        this.g.b();
    }

    public synchronized void c() {
        this.g.c();
    }

    public synchronized void d() {
        b();
        for (RequestManager b2 : this.h.a()) {
            b2.b();
        }
    }

    public synchronized void e() {
        this.g.d();
    }

    public synchronized void f() {
        Util.a();
        e();
        for (RequestManager e2 : this.h.a()) {
            e2.e();
        }
    }

    public synchronized void g() {
        e();
        this.i.g();
    }

    public synchronized void h() {
        b();
        this.i.h();
    }

    public synchronized void i() {
        this.i.i();
        for (Target<?> a2 : this.i.a()) {
            a(a2);
        }
        this.i.b();
        this.g.e();
        this.c.b(this);
        this.c.b(this.l);
        this.k.removeCallbacks(this.j);
        this.f4816a.b(this);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Bitmap> j() {
        return a(Bitmap.class).b((BaseRequestOptions<?>) d);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<GifDrawable> k() {
        return a(GifDrawable.class).b((BaseRequestOptions<?>) e);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> l() {
        return a(Drawable.class);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable Bitmap bitmap) {
        return l().a(bitmap);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable Drawable drawable) {
        return l().a(drawable);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable String str) {
        return l().a(str);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable Uri uri) {
        return l().a(uri);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable File file) {
        return l().a(file);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable @RawRes @DrawableRes Integer num) {
        return l().a(num);
    }

    @Deprecated
    @CheckResult
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable URL url) {
        return l().a(url);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable byte[] bArr) {
        return l().a(bArr);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public RequestBuilder<Drawable> a(@Nullable Object obj) {
        return l().a(obj);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> m() {
        return a(File.class).b((BaseRequestOptions<?>) f);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> c(@Nullable Object obj) {
        return m().a(obj);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> n() {
        return a(File.class).b((BaseRequestOptions<?>) RequestOptions.e(true));
    }

    @CheckResult
    @NonNull
    public <ResourceType> RequestBuilder<ResourceType> a(@NonNull Class<ResourceType> cls) {
        return new RequestBuilder<>(this.f4816a, this, cls, this.b);
    }

    public void a(@NonNull View view) {
        a((Target<?>) new ClearTarget(view));
    }

    public synchronized void a(@Nullable Target<?> target) {
        if (target != null) {
            c(target);
        }
    }

    private void c(@NonNull Target<?> target) {
        if (!b(target) && !this.f4816a.a(target) && target.a() != null) {
            Request a2 = target.a();
            target.a((Request) null);
            a2.b();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean b(@NonNull Target<?> target) {
        Request a2 = target.a();
        if (a2 == null) {
            return true;
        }
        if (!this.g.c(a2)) {
            return false;
        }
        this.i.b(target);
        target.a((Request) null);
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(@NonNull Target<?> target, @NonNull Request request) {
        this.i.a(target);
        this.g.a(request);
    }

    /* access modifiers changed from: package-private */
    public List<RequestListener<Object>> o() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public synchronized RequestOptions p() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public <T> TransitionOptions<?, T> b(Class<T> cls) {
        return this.f4816a.f().getDefaultTransitionOptions(cls);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.g + ", treeNode=" + this.h + "}";
    }

    private class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        @GuardedBy("RequestManager.this")
        private final RequestTracker b;

        RequestManagerConnectivityListener(RequestTracker requestTracker) {
            this.b = requestTracker;
        }

        public void a(boolean z) {
            if (z) {
                synchronized (RequestManager.this) {
                    this.b.f();
                }
            }
        }
    }

    private static class ClearTarget extends ViewTarget<View, Object> {
        public void a(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        ClearTarget(@NonNull View view) {
            super(view);
        }
    }
}
