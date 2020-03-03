package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.concurrent.Executor;

public final class SingleRequest<R> implements Request, ResourceCallback, SizeReadyCallback, FactoryPools.Poolable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5061a = "Request";
    private static final String b = "Glide";
    private static final Pools.Pool<SingleRequest<?>> c = FactoryPools.b(150, new FactoryPools.Factory<SingleRequest<?>>() {
        /* renamed from: a */
        public SingleRequest<?> b() {
            return new SingleRequest<>();
        }
    });
    private static final boolean e = Log.isLoggable(f5061a, 2);
    private Drawable A;
    private Drawable B;
    private Drawable C;
    private int D;
    private int E;
    @Nullable
    private RuntimeException F;
    private boolean d;
    @Nullable
    private final String f;
    private final StateVerifier g;
    @Nullable
    private RequestListener<R> h;
    private RequestCoordinator i;
    private Context j;
    private GlideContext k;
    @Nullable
    private Object l;
    private Class<R> m;
    private BaseRequestOptions<?> n;
    private int o;
    private int p;
    private Priority q;
    private Target<R> r;
    @Nullable
    private List<RequestListener<R>> s;
    private Engine t;
    private TransitionFactory<? super R> u;
    private Executor v;
    private Resource<R> w;
    private Engine.LoadStatus x;
    private long y;
    @GuardedBy("this")
    private Status z;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> a(Context context, GlideContext glideContext, Object obj, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i2, int i3, Priority priority, Target<R> target, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        SingleRequest<R> acquire = c.acquire();
        if (acquire == null) {
            acquire = new SingleRequest<>();
        }
        acquire.b(context, glideContext, obj, cls, baseRequestOptions, i2, i3, priority, target, requestListener, list, requestCoordinator, engine, transitionFactory, executor);
        return acquire;
    }

    SingleRequest() {
        this.f = e ? String.valueOf(super.hashCode()) : null;
        this.g = StateVerifier.a();
    }

    private synchronized void b(Context context, GlideContext glideContext, Object obj, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i2, int i3, Priority priority, Target<R> target, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.j = context;
        this.k = glideContext;
        this.l = obj;
        this.m = cls;
        this.n = baseRequestOptions;
        this.o = i2;
        this.p = i3;
        this.q = priority;
        this.r = target;
        this.h = requestListener;
        this.s = list;
        this.i = requestCoordinator;
        this.t = engine;
        this.u = transitionFactory;
        this.v = executor;
        this.z = Status.PENDING;
        if (this.F == null && glideContext.isLoggingRequestOriginsEnabled()) {
            this.F = new RuntimeException("Glide request origin trace");
        }
    }

    @NonNull
    public StateVerifier d_() {
        return this.g;
    }

    public synchronized void h() {
        j();
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = -1;
        this.p = -1;
        this.r = null;
        this.s = null;
        this.h = null;
        this.i = null;
        this.u = null;
        this.x = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = -1;
        this.E = -1;
        this.F = null;
        c.release(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a4, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.j()     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.util.pool.StateVerifier r0 = r3.g     // Catch:{ all -> 0x00ad }
            r0.b()     // Catch:{ all -> 0x00ad }
            long r0 = com.bumptech.glide.util.LogTime.a()     // Catch:{ all -> 0x00ad }
            r3.y = r0     // Catch:{ all -> 0x00ad }
            java.lang.Object r0 = r3.l     // Catch:{ all -> 0x00ad }
            if (r0 != 0) goto L_0x003a
            int r0 = r3.o     // Catch:{ all -> 0x00ad }
            int r1 = r3.p     // Catch:{ all -> 0x00ad }
            boolean r0 = com.bumptech.glide.util.Util.a((int) r0, (int) r1)     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0025
            int r0 = r3.o     // Catch:{ all -> 0x00ad }
            r3.D = r0     // Catch:{ all -> 0x00ad }
            int r0 = r3.p     // Catch:{ all -> 0x00ad }
            r3.E = r0     // Catch:{ all -> 0x00ad }
        L_0x0025:
            android.graphics.drawable.Drawable r0 = r3.m()     // Catch:{ all -> 0x00ad }
            if (r0 != 0) goto L_0x002d
            r0 = 5
            goto L_0x002e
        L_0x002d:
            r0 = 3
        L_0x002e:
            com.bumptech.glide.load.engine.GlideException r1 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00ad }
            java.lang.String r2 = "Received null model"
            r1.<init>(r2)     // Catch:{ all -> 0x00ad }
            r3.a((com.bumptech.glide.load.engine.GlideException) r1, (int) r0)     // Catch:{ all -> 0x00ad }
            monitor-exit(r3)
            return
        L_0x003a:
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.z     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ad }
            if (r0 == r1) goto L_0x00a5
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.z     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00ad }
            if (r0 != r1) goto L_0x004f
            com.bumptech.glide.load.engine.Resource<R> r0 = r3.w     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.load.DataSource r1 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00ad }
            r3.a((com.bumptech.glide.load.engine.Resource<?>) r0, (com.bumptech.glide.load.DataSource) r1)     // Catch:{ all -> 0x00ad }
            monitor-exit(r3)
            return
        L_0x004f:
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ad }
            r3.z = r0     // Catch:{ all -> 0x00ad }
            int r0 = r3.o     // Catch:{ all -> 0x00ad }
            int r1 = r3.p     // Catch:{ all -> 0x00ad }
            boolean r0 = com.bumptech.glide.util.Util.a((int) r0, (int) r1)     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0065
            int r0 = r3.o     // Catch:{ all -> 0x00ad }
            int r1 = r3.p     // Catch:{ all -> 0x00ad }
            r3.a((int) r0, (int) r1)     // Catch:{ all -> 0x00ad }
            goto L_0x006a
        L_0x0065:
            com.bumptech.glide.request.target.Target<R> r0 = r3.r     // Catch:{ all -> 0x00ad }
            r0.a((com.bumptech.glide.request.target.SizeReadyCallback) r3)     // Catch:{ all -> 0x00ad }
        L_0x006a:
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.z     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ad }
            if (r0 == r1) goto L_0x0076
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.z     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ad }
            if (r0 != r1) goto L_0x0085
        L_0x0076:
            boolean r0 = r3.q()     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0085
            com.bumptech.glide.request.target.Target<R> r0 = r3.r     // Catch:{ all -> 0x00ad }
            android.graphics.drawable.Drawable r1 = r3.l()     // Catch:{ all -> 0x00ad }
            r0.b((android.graphics.drawable.Drawable) r1)     // Catch:{ all -> 0x00ad }
        L_0x0085:
            boolean r0 = e     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x00a3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ad }
            r0.<init>()     // Catch:{ all -> 0x00ad }
            java.lang.String r1 = "finished run method in "
            r0.append(r1)     // Catch:{ all -> 0x00ad }
            long r1 = r3.y     // Catch:{ all -> 0x00ad }
            double r1 = com.bumptech.glide.util.LogTime.a(r1)     // Catch:{ all -> 0x00ad }
            r0.append(r1)     // Catch:{ all -> 0x00ad }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00ad }
            r3.a((java.lang.String) r0)     // Catch:{ all -> 0x00ad }
        L_0x00a3:
            monitor-exit(r3)
            return
        L_0x00a5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ad }
            java.lang.String r1 = "Cannot restart a running request"
            r0.<init>(r1)     // Catch:{ all -> 0x00ad }
            throw r0     // Catch:{ all -> 0x00ad }
        L_0x00ad:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.a():void");
    }

    private void i() {
        j();
        this.g.b();
        this.r.b((SizeReadyCallback) this);
        if (this.x != null) {
            this.x.a();
            this.x = null;
        }
    }

    private void j() {
        if (this.d) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    public synchronized void b() {
        j();
        this.g.b();
        if (this.z != Status.CLEARED) {
            i();
            if (this.w != null) {
                a((Resource<?>) this.w);
            }
            if (p()) {
                this.r.a(l());
            }
            this.z = Status.CLEARED;
        }
    }

    private void a(Resource<?> resource) {
        this.t.a(resource);
        this.w = null;
    }

    public synchronized boolean c() {
        return this.z == Status.RUNNING || this.z == Status.WAITING_FOR_SIZE;
    }

    public synchronized boolean d() {
        return this.z == Status.COMPLETE;
    }

    public synchronized boolean e() {
        return d();
    }

    public synchronized boolean f() {
        return this.z == Status.CLEARED;
    }

    public synchronized boolean g() {
        return this.z == Status.FAILED;
    }

    private Drawable k() {
        if (this.A == null) {
            this.A = this.n.D();
            if (this.A == null && this.n.E() > 0) {
                this.A = a(this.n.E());
            }
        }
        return this.A;
    }

    private Drawable l() {
        if (this.B == null) {
            this.B = this.n.G();
            if (this.B == null && this.n.F() > 0) {
                this.B = a(this.n.F());
            }
        }
        return this.B;
    }

    private Drawable m() {
        if (this.C == null) {
            this.C = this.n.I();
            if (this.C == null && this.n.H() > 0) {
                this.C = a(this.n.H());
            }
        }
        return this.C;
    }

    private Drawable a(@DrawableRes int i2) {
        return DrawableDecoderCompat.a((Context) this.k, i2, this.n.J() != null ? this.n.J() : this.j.getTheme());
    }

    private synchronized void n() {
        if (q()) {
            Drawable drawable = null;
            if (this.l == null) {
                drawable = m();
            }
            if (drawable == null) {
                drawable = k();
            }
            if (drawable == null) {
                drawable = l();
            }
            this.r.c(drawable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00f0, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r23, int r24) {
        /*
            r22 = this;
            r15 = r22
            monitor-enter(r22)
            com.bumptech.glide.util.pool.StateVerifier r0 = r15.g     // Catch:{ all -> 0x00f7 }
            r0.b()     // Catch:{ all -> 0x00f7 }
            boolean r0 = e     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x0026
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            java.lang.String r1 = "Got onSizeReady in "
            r0.append(r1)     // Catch:{ all -> 0x00f7 }
            long r1 = r15.y     // Catch:{ all -> 0x00f7 }
            double r1 = com.bumptech.glide.util.LogTime.a(r1)     // Catch:{ all -> 0x00f7 }
            r0.append(r1)     // Catch:{ all -> 0x00f7 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00f7 }
            r15.a((java.lang.String) r0)     // Catch:{ all -> 0x00f7 }
        L_0x0026:
            com.bumptech.glide.request.SingleRequest$Status r0 = r15.z     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00f7 }
            if (r0 == r1) goto L_0x002e
            monitor-exit(r22)
            return
        L_0x002e:
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00f7 }
            r15.z = r0     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            float r0 = r0.R()     // Catch:{ all -> 0x00f7 }
            r1 = r23
            int r1 = a((int) r1, (float) r0)     // Catch:{ all -> 0x00f7 }
            r15.D = r1     // Catch:{ all -> 0x00f7 }
            r1 = r24
            int r0 = a((int) r1, (float) r0)     // Catch:{ all -> 0x00f7 }
            r15.E = r0     // Catch:{ all -> 0x00f7 }
            boolean r0 = e     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x0066
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            java.lang.String r1 = "finished setup for calling load in "
            r0.append(r1)     // Catch:{ all -> 0x00f7 }
            long r1 = r15.y     // Catch:{ all -> 0x00f7 }
            double r1 = com.bumptech.glide.util.LogTime.a(r1)     // Catch:{ all -> 0x00f7 }
            r0.append(r1)     // Catch:{ all -> 0x00f7 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00f7 }
            r15.a((java.lang.String) r0)     // Catch:{ all -> 0x00f7 }
        L_0x0066:
            com.bumptech.glide.load.engine.Engine r1 = r15.t     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.GlideContext r2 = r15.k     // Catch:{ all -> 0x00f7 }
            java.lang.Object r3 = r15.l     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.load.Key r4 = r0.L()     // Catch:{ all -> 0x00f7 }
            int r5 = r15.D     // Catch:{ all -> 0x00f7 }
            int r6 = r15.E     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            java.lang.Class r7 = r0.B()     // Catch:{ all -> 0x00f7 }
            java.lang.Class<R> r8 = r15.m     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.Priority r9 = r15.q     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.load.engine.DiskCacheStrategy r10 = r0.C()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            java.util.Map r11 = r0.y()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r12 = r0.z()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r13 = r0.S()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.load.Options r14 = r0.A()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r0 = r0.K()     // Catch:{ all -> 0x00f7 }
            r21 = r0
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r16 = r0.T()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r17 = r0.U()     // Catch:{ all -> 0x00f7 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.n     // Catch:{ all -> 0x00f7 }
            boolean r18 = r0.V()     // Catch:{ all -> 0x00f7 }
            java.util.concurrent.Executor r0 = r15.v     // Catch:{ all -> 0x00f7 }
            r15 = r21
            r19 = r22
            r20 = r0
            com.bumptech.glide.load.engine.Engine$LoadStatus r0 = r1.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x00f3 }
            r1 = r22
            r1.x = r0     // Catch:{ all -> 0x00f1 }
            com.bumptech.glide.request.SingleRequest$Status r0 = r1.z     // Catch:{ all -> 0x00f1 }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00f1 }
            if (r0 == r2) goto L_0x00d1
            r0 = 0
            r1.x = r0     // Catch:{ all -> 0x00f1 }
        L_0x00d1:
            boolean r0 = e     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x00ef
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r0.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = "finished onSizeReady in "
            r0.append(r2)     // Catch:{ all -> 0x00f1 }
            long r2 = r1.y     // Catch:{ all -> 0x00f1 }
            double r2 = com.bumptech.glide.util.LogTime.a(r2)     // Catch:{ all -> 0x00f1 }
            r0.append(r2)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00f1 }
            r1.a((java.lang.String) r0)     // Catch:{ all -> 0x00f1 }
        L_0x00ef:
            monitor-exit(r22)
            return
        L_0x00f1:
            r0 = move-exception
            goto L_0x00f9
        L_0x00f3:
            r0 = move-exception
            r1 = r22
            goto L_0x00f9
        L_0x00f7:
            r0 = move-exception
            r1 = r15
        L_0x00f9:
            monitor-exit(r22)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.a(int, int):void");
    }

    private static int a(int i2, float f2) {
        return i2 == Integer.MIN_VALUE ? i2 : Math.round(f2 * ((float) i2));
    }

    private boolean o() {
        return this.i == null || this.i.b(this);
    }

    private boolean p() {
        return this.i == null || this.i.d(this);
    }

    private boolean q() {
        return this.i == null || this.i.c(this);
    }

    private boolean r() {
        return this.i == null || !this.i.i();
    }

    private void s() {
        if (this.i != null) {
            this.i.e(this);
        }
    }

    private void t() {
        if (this.i != null) {
            this.i.f(this);
        }
    }

    public synchronized void a(Resource<?> resource, DataSource dataSource) {
        this.g.b();
        this.x = null;
        if (resource == null) {
            a(new GlideException("Expected to receive a Resource<R> with an object of " + this.m + " inside, but instead got null."));
            return;
        }
        Object d2 = resource.d();
        if (d2 != null) {
            if (this.m.isAssignableFrom(d2.getClass())) {
                if (!o()) {
                    a(resource);
                    this.z = Status.COMPLETE;
                    return;
                }
                a(resource, d2, dataSource);
                return;
            }
        }
        a(resource);
        StringBuilder sb = new StringBuilder();
        sb.append("Expected to receive an object of ");
        sb.append(this.m);
        sb.append(" but instead got ");
        sb.append(d2 != null ? d2.getClass() : "");
        sb.append(Operators.BLOCK_START_STR);
        sb.append(d2);
        sb.append("} inside Resource{");
        sb.append(resource);
        sb.append("}.");
        sb.append(d2 != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
        a(new GlideException(sb.toString()));
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ad A[Catch:{ all -> 0x00bf }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(com.bumptech.glide.load.engine.Resource<R> r11, R r12, com.bumptech.glide.load.DataSource r13) {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r6 = r10.r()     // Catch:{ all -> 0x00c3 }
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00c3 }
            r10.z = r0     // Catch:{ all -> 0x00c3 }
            r10.w = r11     // Catch:{ all -> 0x00c3 }
            com.bumptech.glide.GlideContext r11 = r10.k     // Catch:{ all -> 0x00c3 }
            int r11 = r11.getLogLevel()     // Catch:{ all -> 0x00c3 }
            r0 = 3
            if (r11 > r0) goto L_0x006c
            java.lang.String r11 = "Glide"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c3 }
            r0.<init>()     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = "Finished loading "
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.Class r1 = r12.getClass()     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = r1.getSimpleName()     // Catch:{ all -> 0x00c3 }
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = " from "
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            r0.append(r13)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = " for "
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.Object r1 = r10.l     // Catch:{ all -> 0x00c3 }
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = " with size ["
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            int r1 = r10.D     // Catch:{ all -> 0x00c3 }
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = "x"
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            int r1 = r10.E     // Catch:{ all -> 0x00c3 }
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = "] in "
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            long r1 = r10.y     // Catch:{ all -> 0x00c3 }
            double r1 = com.bumptech.glide.util.LogTime.a(r1)     // Catch:{ all -> 0x00c3 }
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r1 = " ms"
            r0.append(r1)     // Catch:{ all -> 0x00c3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c3 }
            android.util.Log.d(r11, r0)     // Catch:{ all -> 0x00c3 }
        L_0x006c:
            r11 = 1
            r10.d = r11     // Catch:{ all -> 0x00c3 }
            r7 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.s     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0094
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.s     // Catch:{ all -> 0x00bf }
            java.util.Iterator r8 = r0.iterator()     // Catch:{ all -> 0x00bf }
            r9 = 0
        L_0x007b:
            boolean r0 = r8.hasNext()     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0095
            java.lang.Object r0 = r8.next()     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.request.RequestListener r0 = (com.bumptech.glide.request.RequestListener) r0     // Catch:{ all -> 0x00bf }
            java.lang.Object r2 = r10.l     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.request.target.Target<R> r3 = r10.r     // Catch:{ all -> 0x00bf }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.a(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bf }
            r9 = r9 | r0
            goto L_0x007b
        L_0x0094:
            r9 = 0
        L_0x0095:
            com.bumptech.glide.request.RequestListener<R> r0 = r10.h     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x00a9
            com.bumptech.glide.request.RequestListener<R> r0 = r10.h     // Catch:{ all -> 0x00bf }
            java.lang.Object r2 = r10.l     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.request.target.Target<R> r3 = r10.r     // Catch:{ all -> 0x00bf }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.a(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x00a9
            goto L_0x00aa
        L_0x00a9:
            r11 = 0
        L_0x00aa:
            r11 = r11 | r9
            if (r11 != 0) goto L_0x00b8
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r11 = r10.u     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.request.transition.Transition r11 = r11.a(r13, r6)     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.request.target.Target<R> r13 = r10.r     // Catch:{ all -> 0x00bf }
            r13.a(r12, r11)     // Catch:{ all -> 0x00bf }
        L_0x00b8:
            r10.d = r7     // Catch:{ all -> 0x00c3 }
            r10.s()     // Catch:{ all -> 0x00c3 }
            monitor-exit(r10)
            return
        L_0x00bf:
            r11 = move-exception
            r10.d = r7     // Catch:{ all -> 0x00c3 }
            throw r11     // Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.a(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource):void");
    }

    public synchronized void a(GlideException glideException) {
        a(glideException, 5);
    }

    /* JADX INFO: finally extract failed */
    private synchronized void a(GlideException glideException, int i2) {
        boolean z2;
        this.g.b();
        glideException.setOrigin(this.F);
        int logLevel = this.k.getLogLevel();
        if (logLevel <= i2) {
            Log.w(b, "Load failed for " + this.l + " with size [" + this.D + "x" + this.E + Operators.ARRAY_END_STR, glideException);
            if (logLevel <= 4) {
                glideException.logRootCauses(b);
            }
        }
        this.x = null;
        this.z = Status.FAILED;
        boolean z3 = true;
        this.d = true;
        try {
            if (this.s != null) {
                z2 = false;
                for (RequestListener<R> a2 : this.s) {
                    z2 |= a2.a(glideException, this.l, this.r, r());
                }
            } else {
                z2 = false;
            }
            if (this.h == null || !this.h.a(glideException, this.l, this.r, r())) {
                z3 = false;
            }
            if (!z2 && !z3) {
                n();
            }
            this.d = false;
            t();
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    public synchronized boolean a(Request request) {
        boolean z2 = false;
        if (!(request instanceof SingleRequest)) {
            return false;
        }
        SingleRequest singleRequest = (SingleRequest) request;
        synchronized (singleRequest) {
            if (this.o == singleRequest.o && this.p == singleRequest.p && Util.b(this.l, singleRequest.l) && this.m.equals(singleRequest.m) && this.n.equals(singleRequest.n) && this.q == singleRequest.q && a((SingleRequest<?>) singleRequest)) {
                z2 = true;
            }
        }
        return z2;
    }

    private synchronized boolean a(SingleRequest<?> singleRequest) {
        boolean z2;
        synchronized (singleRequest) {
            z2 = false;
            if ((this.s == null ? 0 : this.s.size()) == (singleRequest.s == null ? 0 : singleRequest.s.size())) {
                z2 = true;
            }
        }
        return z2;
    }

    private void a(String str) {
        Log.v(f5061a, str + " this: " + this.f);
    }
}
