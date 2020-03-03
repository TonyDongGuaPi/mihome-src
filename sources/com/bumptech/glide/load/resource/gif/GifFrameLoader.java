package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

class GifFrameLoader {

    /* renamed from: a  reason: collision with root package name */
    final RequestManager f5024a;
    private final GifDecoder b;
    private final Handler c;
    private final List<FrameCallback> d;
    private final BitmapPool e;
    private boolean f;
    private boolean g;
    private boolean h;
    private RequestBuilder<Bitmap> i;
    private DelayTarget j;
    private boolean k;
    private DelayTarget l;
    private Bitmap m;
    private Transformation<Bitmap> n;
    private DelayTarget o;
    @Nullable
    private OnEveryFrameListener p;

    public interface FrameCallback {
        void h();
    }

    @VisibleForTesting
    interface OnEveryFrameListener {
        void a();
    }

    GifFrameLoader(Glide glide, GifDecoder gifDecoder, int i2, int i3, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this(glide.b(), Glide.c(glide.d()), gifDecoder, (Handler) null, a(Glide.c(glide.d()), i2, i3), transformation, bitmap);
    }

    GifFrameLoader(BitmapPool bitmapPool, RequestManager requestManager, GifDecoder gifDecoder, Handler handler, RequestBuilder<Bitmap> requestBuilder, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.d = new ArrayList();
        this.f5024a = requestManager;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new FrameLoaderCallback()) : handler;
        this.e = bitmapPool;
        this.c = handler;
        this.i = requestBuilder;
        this.b = gifDecoder;
        a(transformation, bitmap);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, java.lang.Object, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2, android.graphics.Bitmap r3) {
        /*
            r1 = this;
            java.lang.Object r0 = com.bumptech.glide.util.Preconditions.a(r2)
            com.bumptech.glide.load.Transformation r0 = (com.bumptech.glide.load.Transformation) r0
            r1.n = r0
            java.lang.Object r3 = com.bumptech.glide.util.Preconditions.a(r3)
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3
            r1.m = r3
            com.bumptech.glide.RequestBuilder<android.graphics.Bitmap> r3 = r1.i
            com.bumptech.glide.request.RequestOptions r0 = new com.bumptech.glide.request.RequestOptions
            r0.<init>()
            com.bumptech.glide.request.BaseRequestOptions r2 = r0.a((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
            com.bumptech.glide.RequestBuilder r2 = r3.b((com.bumptech.glide.request.BaseRequestOptions<?>) r2)
            r1.i = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifFrameLoader.a(com.bumptech.glide.load.Transformation, android.graphics.Bitmap):void");
    }

    /* access modifiers changed from: package-private */
    public Transformation<Bitmap> a() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public Bitmap b() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public void a(FrameCallback frameCallback) {
        if (this.k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        } else if (!this.d.contains(frameCallback)) {
            boolean isEmpty = this.d.isEmpty();
            this.d.add(frameCallback);
            if (isEmpty) {
                n();
            }
        } else {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
    }

    /* access modifiers changed from: package-private */
    public void b(FrameCallback frameCallback) {
        this.d.remove(frameCallback);
        if (this.d.isEmpty()) {
            o();
        }
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return k().getWidth();
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return k().getHeight();
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.b.m() + m();
    }

    /* access modifiers changed from: package-private */
    public int f() {
        if (this.j != null) {
            return this.j.f5025a;
        }
        return -1;
    }

    private int m() {
        return Util.a(k().getWidth(), k().getHeight(), k().getConfig());
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer g() {
        return this.b.c().asReadOnlyBuffer();
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.b.g();
    }

    /* access modifiers changed from: package-private */
    public int i() {
        return this.b.l();
    }

    private void n() {
        if (!this.f) {
            this.f = true;
            this.k = false;
            p();
        }
    }

    private void o() {
        this.f = false;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        this.d.clear();
        q();
        o();
        if (this.j != null) {
            this.f5024a.a((Target<?>) this.j);
            this.j = null;
        }
        if (this.l != null) {
            this.f5024a.a((Target<?>) this.l);
            this.l = null;
        }
        if (this.o != null) {
            this.f5024a.a((Target<?>) this.o);
            this.o = null;
        }
        this.b.o();
        this.k = true;
    }

    /* access modifiers changed from: package-private */
    public Bitmap k() {
        return this.j != null ? this.j.e_() : this.m;
    }

    private void p() {
        if (this.f && !this.g) {
            if (this.h) {
                Preconditions.a(this.o == null, "Pending target must be null when starting from the first frame");
                this.b.i();
                this.h = false;
            }
            if (this.o != null) {
                DelayTarget delayTarget = this.o;
                this.o = null;
                a(delayTarget);
                return;
            }
            this.g = true;
            long uptimeMillis = SystemClock.uptimeMillis() + ((long) this.b.f());
            this.b.e();
            this.l = new DelayTarget(this.c, this.b.h(), uptimeMillis);
            this.i.b((BaseRequestOptions<?>) RequestOptions.b(r())).a((Object) this.b).a(this.l);
        }
    }

    private void q() {
        if (this.m != null) {
            this.e.a(this.m);
            this.m = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void l() {
        Preconditions.a(!this.f, "Can't restart a running animation");
        this.h = true;
        if (this.o != null) {
            this.f5024a.a((Target<?>) this.o);
            this.o = null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(@Nullable OnEveryFrameListener onEveryFrameListener) {
        this.p = onEveryFrameListener;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(DelayTarget delayTarget) {
        if (this.p != null) {
            this.p.a();
        }
        this.g = false;
        if (this.k) {
            this.c.obtainMessage(2, delayTarget).sendToTarget();
        } else if (!this.f) {
            this.o = delayTarget;
        } else {
            if (delayTarget.e_() != null) {
                q();
                DelayTarget delayTarget2 = this.j;
                this.j = delayTarget;
                for (int size = this.d.size() - 1; size >= 0; size--) {
                    this.d.get(size).h();
                }
                if (delayTarget2 != null) {
                    this.c.obtainMessage(2, delayTarget2).sendToTarget();
                }
            }
            p();
        }
    }

    private class FrameLoaderCallback implements Handler.Callback {

        /* renamed from: a  reason: collision with root package name */
        static final int f5026a = 1;
        static final int b = 2;

        FrameLoaderCallback() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                GifFrameLoader.this.a((DelayTarget) message.obj);
                return true;
            } else if (message.what != 2) {
                return false;
            } else {
                GifFrameLoader.this.f5024a.a((Target<?>) (DelayTarget) message.obj);
                return false;
            }
        }
    }

    @VisibleForTesting
    static class DelayTarget extends SimpleTarget<Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        final int f5025a;
        private final Handler b;
        private final long d;
        private Bitmap e;

        DelayTarget(Handler handler, int i, long j) {
            this.b = handler;
            this.f5025a = i;
            this.d = j;
        }

        /* access modifiers changed from: package-private */
        public Bitmap e_() {
            return this.e;
        }

        public void a(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            this.e = bitmap;
            this.b.sendMessageAtTime(this.b.obtainMessage(1, this), this.d);
        }
    }

    private static RequestBuilder<Bitmap> a(RequestManager requestManager, int i2, int i3) {
        return requestManager.j().b((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) RequestOptions.b(DiskCacheStrategy.b).b(true)).d(true)).e(i2, i3));
    }

    private static Key r() {
        return new ObjectKey(Double.valueOf(Math.random()));
    }
}
