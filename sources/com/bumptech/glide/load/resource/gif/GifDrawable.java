package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.graphics.drawable.Animatable2Compat;
import android.view.Gravity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifFrameLoader;
import com.bumptech.glide.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GifDrawable extends Drawable implements Animatable, Animatable2Compat, GifFrameLoader.FrameCallback {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5021a = -1;
    public static final int b = 0;
    private static final int c = 119;
    private final GifState d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private Paint l;
    private Rect m;
    private List<Animatable2Compat.AnimationCallback> n;

    public int getOpacity() {
        return -2;
    }

    @Deprecated
    public GifDrawable(Context context, GifDecoder gifDecoder, BitmapPool bitmapPool, Transformation<Bitmap> transformation, int i2, int i3, Bitmap bitmap) {
        this(context, gifDecoder, transformation, i2, i3, bitmap);
    }

    public GifDrawable(Context context, GifDecoder gifDecoder, Transformation<Bitmap> transformation, int i2, int i3, Bitmap bitmap) {
        this(new GifState(new GifFrameLoader(Glide.b(context), gifDecoder, i2, i3, transformation, bitmap)));
    }

    GifDrawable(GifState gifState) {
        this.h = true;
        this.j = -1;
        this.d = (GifState) Preconditions.a(gifState);
    }

    @VisibleForTesting
    GifDrawable(GifFrameLoader gifFrameLoader, Paint paint) {
        this(new GifState(gifFrameLoader));
        this.l = paint;
    }

    public int a() {
        return this.d.f5022a.e();
    }

    public Bitmap b() {
        return this.d.f5022a.b();
    }

    public void a(Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.d.f5022a.a(transformation, bitmap);
    }

    public Transformation<Bitmap> c() {
        return this.d.f5022a.a();
    }

    public ByteBuffer d() {
        return this.d.f5022a.g();
    }

    public int e() {
        return this.d.f5022a.h();
    }

    public int f() {
        return this.d.f5022a.f();
    }

    private void k() {
        this.i = 0;
    }

    public void g() {
        Preconditions.a(!this.e, "You cannot restart a currently running animation.");
        this.d.f5022a.l();
        start();
    }

    public void start() {
        this.f = true;
        k();
        if (this.h) {
            l();
        }
    }

    public void stop() {
        this.f = false;
        m();
    }

    private void l() {
        Preconditions.a(!this.g, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.d.f5022a.h() == 1) {
            invalidateSelf();
        } else if (!this.e) {
            this.e = true;
            this.d.f5022a.a((GifFrameLoader.FrameCallback) this);
            invalidateSelf();
        }
    }

    private void m() {
        this.e = false;
        this.d.f5022a.b(this);
    }

    public boolean setVisible(boolean z, boolean z2) {
        Preconditions.a(!this.g, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.h = z;
        if (!z) {
            m();
        } else if (this.f) {
            l();
        }
        return super.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.d.f5022a.c();
    }

    public int getIntrinsicHeight() {
        return this.d.f5022a.d();
    }

    public boolean isRunning() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.e = z;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.k = true;
    }

    public void draw(@NonNull Canvas canvas) {
        if (!this.g) {
            if (this.k) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), n());
                this.k = false;
            }
            canvas.drawBitmap(this.d.f5022a.k(), (Rect) null, n(), o());
        }
    }

    public void setAlpha(int i2) {
        o().setAlpha(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        o().setColorFilter(colorFilter);
    }

    private Rect n() {
        if (this.m == null) {
            this.m = new Rect();
        }
        return this.m;
    }

    private Paint o() {
        if (this.l == null) {
            this.l = new Paint(2);
        }
        return this.l;
    }

    private Drawable.Callback p() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    public void h() {
        if (p() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (f() == e() - 1) {
            this.i++;
        }
        if (this.j != -1 && this.i >= this.j) {
            q();
            stop();
        }
    }

    private void q() {
        if (this.n != null) {
            int size = this.n.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.n.get(i2).onAnimationEnd(this);
            }
        }
    }

    public Drawable.ConstantState getConstantState() {
        return this.d;
    }

    public void i() {
        this.g = true;
        this.d.f5022a.j();
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.g;
    }

    public void a(int i2) {
        if (i2 <= 0 && i2 != -1 && i2 != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (i2 == 0) {
            int i3 = this.d.f5022a.i();
            if (i3 == 0) {
                i3 = -1;
            }
            this.j = i3;
        } else {
            this.j = i2;
        }
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (animationCallback != null) {
            if (this.n == null) {
                this.n = new ArrayList();
            }
            this.n.add(animationCallback);
        }
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.n == null || animationCallback == null) {
            return false;
        }
        return this.n.remove(animationCallback);
    }

    public void clearAnimationCallbacks() {
        if (this.n != null) {
            this.n.clear();
        }
    }

    static final class GifState extends Drawable.ConstantState {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        final GifFrameLoader f5022a;

        public int getChangingConfigurations() {
            return 0;
        }

        GifState(GifFrameLoader gifFrameLoader) {
            this.f5022a = gifFrameLoader;
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        @NonNull
        public Drawable newDrawable() {
            return new GifDrawable(this);
        }
    }
}
