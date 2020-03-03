package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String b = "ViewTarget";
    private static boolean d;
    @Nullable
    private static Integer e;

    /* renamed from: a  reason: collision with root package name */
    protected final T f5075a;
    private final SizeDeterminer f;
    @Nullable
    private View.OnAttachStateChangeListener g;
    private boolean h;
    private boolean i;

    public ViewTarget(@NonNull T t) {
        this.f5075a = (View) Preconditions.a(t);
        this.f = new SizeDeterminer(t);
    }

    @Deprecated
    public ViewTarget(@NonNull T t, boolean z) {
        this(t);
        if (z) {
            f();
        }
    }

    @NonNull
    public final ViewTarget<T, Z> c() {
        if (this.g != null) {
            return this;
        }
        this.g = new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                ViewTarget.this.d();
            }

            public void onViewDetachedFromWindow(View view) {
                ViewTarget.this.e();
            }
        };
        k();
        return this;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Request a2 = a();
        if (a2 != null && a2.f()) {
            a2.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        Request a2 = a();
        if (a2 != null) {
            this.h = true;
            a2.b();
            this.h = false;
        }
    }

    @NonNull
    public final ViewTarget<T, Z> f() {
        this.f.b = true;
        return this;
    }

    @CallSuper
    public void b(@Nullable Drawable drawable) {
        super.b(drawable);
        k();
    }

    private void k() {
        if (this.g != null && !this.i) {
            this.f5075a.addOnAttachStateChangeListener(this.g);
            this.i = true;
        }
    }

    private void l() {
        if (this.g != null && this.i) {
            this.f5075a.removeOnAttachStateChangeListener(this.g);
            this.i = false;
        }
    }

    @NonNull
    public T j() {
        return this.f5075a;
    }

    @CallSuper
    public void a(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.f.a(sizeReadyCallback);
    }

    @CallSuper
    public void b(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.f.b(sizeReadyCallback);
    }

    @CallSuper
    public void a(@Nullable Drawable drawable) {
        super.a(drawable);
        this.f.b();
        if (!this.h) {
            l();
        }
    }

    public void a(@Nullable Request request) {
        a((Object) request);
    }

    @Nullable
    public Request a() {
        Object m = m();
        if (m == null) {
            return null;
        }
        if (m instanceof Request) {
            return (Request) m;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        return "Target for: " + this.f5075a;
    }

    private void a(@Nullable Object obj) {
        if (e == null) {
            d = true;
            this.f5075a.setTag(obj);
            return;
        }
        this.f5075a.setTag(e.intValue(), obj);
    }

    @Nullable
    private Object m() {
        if (e == null) {
            return this.f5075a.getTag();
        }
        return this.f5075a.getTag(e.intValue());
    }

    public static void a(int i2) {
        if (e != null || d) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        e = Integer.valueOf(i2);
    }

    @VisibleForTesting
    static final class SizeDeterminer {
        @Nullable
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        static Integer f5077a;
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
            if (f5077a == null) {
                Display defaultDisplay = ((WindowManager) Preconditions.a((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f5077a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f5077a.intValue();
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
            if (Log.isLoggable(ViewTarget.b, 4)) {
                Log.i(ViewTarget.b, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return a(this.d.getContext());
        }

        private static final class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {

            /* renamed from: a  reason: collision with root package name */
            private final WeakReference<SizeDeterminer> f5078a;

            SizeDeterminerLayoutListener(@NonNull SizeDeterminer sizeDeterminer) {
                this.f5078a = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable(ViewTarget.b, 2)) {
                    Log.v(ViewTarget.b, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.f5078a.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.a();
                return true;
            }
        }
    }
}
