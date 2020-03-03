package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.R;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    private static final String b = "CustomViewTarget";
    @IdRes
    private static final int d = R.id.glide_custom_view_target_tag;

    /* renamed from: a  reason: collision with root package name */
    protected final T f5066a;
    private final SizeDeterminer e;
    @Nullable
    private View.OnAttachStateChangeListener f;
    private boolean g;
    private boolean h;
    @IdRes
    private int i;

    /* access modifiers changed from: protected */
    public abstract void d(@Nullable Drawable drawable);

    /* access modifiers changed from: protected */
    public void e(@Nullable Drawable drawable) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public CustomViewTarget(@NonNull T t) {
        this.f5066a = (View) Preconditions.a(t);
        this.e = new SizeDeterminer(t);
    }

    @NonNull
    public final CustomViewTarget<T, Z> b() {
        this.e.b = true;
        return this;
    }

    @NonNull
    public final CustomViewTarget<T, Z> c() {
        if (this.f != null) {
            return this;
        }
        this.f = new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                CustomViewTarget.this.e();
            }

            public void onViewDetachedFromWindow(View view) {
                CustomViewTarget.this.f();
            }
        };
        k();
        return this;
    }

    public final CustomViewTarget<T, Z> a(@IdRes int i2) {
        if (this.i == 0) {
            this.i = i2;
            return this;
        }
        throw new IllegalArgumentException("You cannot change the tag id once it has been set.");
    }

    @NonNull
    public final T d() {
        return this.f5066a;
    }

    public final void a(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.e.a(sizeReadyCallback);
    }

    public final void b(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.e.b(sizeReadyCallback);
    }

    public final void b(@Nullable Drawable drawable) {
        k();
        e(drawable);
    }

    public final void a(@Nullable Drawable drawable) {
        this.e.b();
        d(drawable);
        if (!this.g) {
            l();
        }
    }

    public final void a(@Nullable Request request) {
        a((Object) request);
    }

    @Nullable
    public final Request a() {
        Object j = j();
        if (j == null) {
            return null;
        }
        if (j instanceof Request) {
            return (Request) j;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    public String toString() {
        return "Target for: " + this.f5066a;
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        Request a2 = a();
        if (a2 != null && a2.f()) {
            a2.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final void f() {
        Request a2 = a();
        if (a2 != null) {
            this.g = true;
            a2.b();
            this.g = false;
        }
    }

    private void a(@Nullable Object obj) {
        this.f5066a.setTag(this.i == 0 ? d : this.i, obj);
    }

    @Nullable
    private Object j() {
        return this.f5066a.getTag(this.i == 0 ? d : this.i);
    }

    private void k() {
        if (this.f != null && !this.h) {
            this.f5066a.addOnAttachStateChangeListener(this.f);
            this.h = true;
        }
    }

    private void l() {
        if (this.f != null && this.h) {
            this.f5066a.removeOnAttachStateChangeListener(this.f);
            this.h = false;
        }
    }

    @VisibleForTesting
    static final class SizeDeterminer {
        @Nullable
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        static Integer f5068a;
        private static final int c = 0;
        boolean b;
        private final View d;
        private final List<SizeReadyCallback> e = new ArrayList();
        @Nullable
        private SizeDeterminerLayoutListener f;

        private boolean a(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        SizeDeterminer(@NonNull View view) {
            this.d = view;
        }

        private static int a(@NonNull Context context) {
            if (f5068a == null) {
                Display defaultDisplay = ((WindowManager) Preconditions.a((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f5068a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f5068a.intValue();
        }

        private void a(int i, int i2) {
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).a(i, i2);
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (!this.e.isEmpty()) {
                int d2 = d();
                int c2 = c();
                if (b(d2, c2)) {
                    a(d2, c2);
                    b();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(@NonNull SizeReadyCallback sizeReadyCallback) {
            int d2 = d();
            int c2 = c();
            if (b(d2, c2)) {
                sizeReadyCallback.a(d2, c2);
                return;
            }
            if (!this.e.contains(sizeReadyCallback)) {
                this.e.add(sizeReadyCallback);
            }
            if (this.f == null) {
                ViewTreeObserver viewTreeObserver = this.d.getViewTreeObserver();
                this.f = new SizeDeterminerLayoutListener(this);
                viewTreeObserver.addOnPreDrawListener(this.f);
            }
        }

        /* access modifiers changed from: package-private */
        public void b(@NonNull SizeReadyCallback sizeReadyCallback) {
            this.e.remove(sizeReadyCallback);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            ViewTreeObserver viewTreeObserver = this.d.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f);
            }
            this.f = null;
            this.e.clear();
        }

        private boolean b(int i, int i2) {
            return a(i) && a(i2);
        }

        private int c() {
            int paddingTop = this.d.getPaddingTop() + this.d.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            return a(this.d.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int d() {
            int paddingLeft = this.d.getPaddingLeft() + this.d.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            return a(this.d.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private int a(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.b && this.d.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.d.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable(CustomViewTarget.b, 4)) {
                Log.i(CustomViewTarget.b, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return a(this.d.getContext());
        }

        private static final class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {

            /* renamed from: a  reason: collision with root package name */
            private final WeakReference<SizeDeterminer> f5069a;

            SizeDeterminerLayoutListener(@NonNull SizeDeterminer sizeDeterminer) {
                this.f5069a = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable(CustomViewTarget.b, 2)) {
                    Log.v(CustomViewTarget.b, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.f5069a.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.a();
                return true;
            }
        }
    }
}
