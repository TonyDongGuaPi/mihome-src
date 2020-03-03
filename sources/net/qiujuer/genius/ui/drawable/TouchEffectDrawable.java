package net.qiujuer.genius.ui.drawable;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import net.qiujuer.genius.ui.drawable.effect.Effect;

public class TouchEffectDrawable extends StatePaintDrawable implements Animatable {
    public static final int c = 1;
    public static final int d = 16;
    public static final int e = 280;
    public static final int f = 160;
    public static final int g = 90;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;
    protected boolean h;
    private TouchEffectState l;
    private boolean m;
    private WeakReference<PerformClicker> n;
    private WeakReference<PerformLongClicker> o;
    /* access modifiers changed from: private */
    public boolean p;
    /* access modifiers changed from: private */
    public float q;
    private int r;
    private int s;
    private final AnimRunnable t;
    private final AnimRunnable u;

    public static abstract class ClipFactory {
        public abstract void a(int i, int i2);

        public abstract boolean a(Canvas canvas);
    }

    public interface PerformClicker {
        void postPerformClick();
    }

    public interface PerformLongClicker {
        void postPerformLongClick();
    }

    public static abstract class ShaderFactory {
        public abstract Shader a(int i, int i2);
    }

    public TouchEffectDrawable() {
        this(new TouchEffectState((TouchEffectState) null), (Resources) null, (ColorStateList) null);
    }

    public TouchEffectDrawable(Effect effect) {
        this(new TouchEffectState((TouchEffectState) null), (Resources) null, (ColorStateList) null);
        this.l.c = effect;
    }

    public TouchEffectDrawable(Effect effect, ColorStateList colorStateList) {
        this(new TouchEffectState((TouchEffectState) null), (Resources) null, colorStateList);
        this.l.c = effect;
    }

    public Effect a() {
        return this.l.c;
    }

    public void a(Effect effect) {
        this.l.c = effect;
        p();
    }

    public void b(int i2) {
        this.s = i2;
    }

    public void a(ShaderFactory shaderFactory) {
        this.l.g = shaderFactory;
    }

    public ShaderFactory b() {
        return this.l.g;
    }

    public void a(ClipFactory clipFactory) {
        this.l.h = clipFactory;
    }

    public ClipFactory c() {
        return this.l.h;
    }

    public void a(int i2, int i3, int i4, int i5) {
        if ((i2 | i3 | i4 | i5) == 0) {
            this.l.d = null;
        } else {
            if (this.l.d == null) {
                this.l.d = new Rect();
            }
            this.l.d.set(i2, i3, i4, i5);
        }
        invalidateSelf();
    }

    public void a(Rect rect) {
        if (rect == null) {
            this.l.d = null;
        } else {
            if (this.l.d == null) {
                this.l.d = new Rect();
            }
            this.l.d.set(rect);
        }
        invalidateSelf();
    }

    public void e(int i2) {
        this.l.e = i2;
        invalidateSelf();
    }

    public void f(int i2) {
        this.l.f = i2;
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.l.e;
    }

    public int getIntrinsicHeight() {
        return this.l.f;
    }

    public boolean getPadding(Rect rect) {
        if (this.l.d == null) {
            return super.getPadding(rect);
        }
        rect.set(this.l.d);
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(Effect effect, Canvas canvas, Paint paint) {
        effect.a(canvas, paint);
    }

    public void draw(Canvas canvas) {
        if (this.p) {
            super.draw(canvas);
        }
    }

    public void a(Canvas canvas, Paint paint) {
        Rect bounds = getBounds();
        TouchEffectState touchEffectState = this.l;
        if (touchEffectState.c != null) {
            int save = canvas.save();
            canvas.translate((float) bounds.left, (float) bounds.top);
            if (touchEffectState.h != null) {
                touchEffectState.h.a(canvas);
            } else {
                canvas.clipRect(0, 0, bounds.width(), bounds.height());
            }
            a(touchEffectState.c, canvas, paint);
            canvas.restoreToCount(save);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.l.b;
    }

    public int getOpacity() {
        if (this.l.c == null) {
            return super.getOpacity();
        }
        return -3;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        p();
    }

    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.l.c != null) {
            this.l.c.a(outline);
            outline.setAlpha(((float) getAlpha()) / 255.0f);
        }
    }

    public Drawable.ConstantState getConstantState() {
        this.l.b = getChangingConfigurations();
        return this.l;
    }

    public Drawable mutate() {
        if (!this.m && super.mutate() == this) {
            if (this.l.d != null) {
                this.l.d = new Rect(this.l.d);
            } else {
                this.l.d = new Rect();
            }
            try {
                if (this.l.c != null) {
                    this.l.c = this.l.c.clone();
                }
                this.m = true;
            } catch (CloneNotSupportedException unused) {
                return null;
            }
        }
        return this;
    }

    public void g() {
        this.m = false;
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.h = false;
                b(motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
                this.h = true;
                c(motionEvent.getX(), motionEvent.getY());
                break;
            case 2:
                d(motionEvent.getX(), motionEvent.getY());
                break;
            case 3:
                this.h = true;
                a(motionEvent.getX(), motionEvent.getY());
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(float f2, float f3) {
        float f4;
        float f5;
        if (this.l.c != null) {
            Rect bounds = getBounds();
            if (f2 > ((float) bounds.right)) {
                f4 = (float) bounds.width();
            } else {
                f4 = f2 - ((float) bounds.left);
            }
            if (f3 > ((float) bounds.bottom)) {
                f5 = (float) bounds.height();
            } else {
                f5 = f3 - ((float) bounds.top);
            }
            this.l.c.c(f4, f5);
            w();
        }
    }

    /* access modifiers changed from: protected */
    public void b(float f2, float f3) {
        float f4;
        float f5;
        if (this.l.c != null) {
            Rect bounds = getBounds();
            if (f2 > ((float) bounds.right)) {
                f4 = (float) bounds.width();
            } else {
                f4 = f2 - ((float) bounds.left);
            }
            if (f3 > ((float) bounds.bottom)) {
                f5 = (float) bounds.height();
            } else {
                f5 = f3 - ((float) bounds.top);
            }
            this.l.c.a(f4, f5);
            stop();
            u();
        }
    }

    /* access modifiers changed from: protected */
    public void c(float f2, float f3) {
        float f4;
        float f5;
        if (this.l.c != null) {
            Rect bounds = getBounds();
            if (f2 > ((float) bounds.right)) {
                f4 = (float) bounds.width();
            } else {
                f4 = f2 - ((float) bounds.left);
            }
            if (f3 > ((float) bounds.bottom)) {
                f5 = (float) bounds.height();
            } else {
                f5 = f3 - ((float) bounds.top);
            }
            this.l.c.d(f4, f5);
            g(1);
            v();
        }
    }

    /* access modifiers changed from: protected */
    public void d(float f2, float f3) {
        float f4;
        float f5;
        if (this.l.c != null) {
            Rect bounds = getBounds();
            if (f2 > ((float) bounds.right)) {
                f4 = (float) bounds.width();
            } else {
                f4 = f2 - ((float) bounds.left);
            }
            if (f3 > ((float) bounds.bottom)) {
                f5 = (float) bounds.height();
            } else {
                f5 = f3 - ((float) bounds.top);
            }
            this.l.c.e(f4, f5);
            g(3);
        }
    }

    private void p() {
        if (this.l.c != null) {
            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();
            this.l.c.b((float) width, (float) height);
            if (this.l.g != null) {
                this.q_.setShader(this.l.g.a(width, height));
            }
            if (this.l.h != null) {
                this.l.h.a(width, height);
            }
        }
        invalidateSelf();
    }

    private TouchEffectDrawable(TouchEffectState touchEffectState, Resources resources, ColorStateList colorStateList) {
        super(colorStateList);
        this.h = false;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = 1.0f;
        this.r = 1;
        this.s = 1;
        this.t = new AnimRunnable() {
            /* access modifiers changed from: package-private */
            public void a() {
                this.b = 280;
                this.c = new DecelerateInterpolator(2.4f);
            }

            /* access modifiers changed from: package-private */
            public void a(float f) {
                TouchEffectDrawable.this.c(f);
            }

            /* access modifiers changed from: package-private */
            public void b() {
                TouchEffectDrawable.this.n();
            }
        };
        this.u = new AnimRunnable() {
            /* access modifiers changed from: package-private */
            public void a() {
                this.b = 160;
                this.c = new AccelerateInterpolator();
            }

            /* access modifiers changed from: package-private */
            public void a(float f) {
                TouchEffectDrawable.this.d(f);
            }

            /* access modifiers changed from: package-private */
            public void b() {
                TouchEffectDrawable.this.o();
            }
        };
        this.l = touchEffectState;
    }

    static TypedArray a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public boolean a(PerformClicker performClicker) {
        if ((this.s & 1) != 1) {
            return true;
        }
        if (q() == null) {
            b(performClicker);
            return false;
        } else if (this.t.c()) {
            return false;
        } else {
            s();
            return true;
        }
    }

    public boolean a(PerformLongClicker performLongClicker) {
        if ((this.s & 16) != 16) {
            return true;
        }
        if (r() == null) {
            b(performLongClicker);
            return false;
        } else if (this.t.c()) {
            return false;
        } else {
            t();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void h() {
        PerformClicker q2 = q();
        if (q2 != null) {
            q2.postPerformClick();
        }
    }

    /* access modifiers changed from: protected */
    public void i() {
        PerformLongClicker r2;
        if (this.r == 3 && (r2 = r()) != null) {
            r2.postPerformLongClick();
        }
    }

    private void b(PerformClicker performClicker) {
        synchronized (this) {
            this.n = new WeakReference<>(performClicker);
        }
    }

    private void b(PerformLongClicker performLongClicker) {
        synchronized (this) {
            this.o = new WeakReference<>(performLongClicker);
        }
    }

    private PerformClicker q() {
        synchronized (this) {
            if (this.n == null) {
                return null;
            }
            PerformClicker performClicker = (PerformClicker) this.n.get();
            return performClicker;
        }
    }

    private PerformLongClicker r() {
        synchronized (this) {
            if (this.o == null) {
                return null;
            }
            PerformLongClicker performLongClicker = (PerformLongClicker) this.o.get();
            return performLongClicker;
        }
    }

    private void s() {
        synchronized (this) {
            if (this.n != null) {
                this.n.clear();
                this.n = null;
            }
        }
    }

    private void t() {
        synchronized (this) {
            if (this.o != null) {
                this.o.clear();
                this.o = null;
            }
        }
    }

    public void start() {
        u();
    }

    public void stop() {
        this.t.e();
        this.u.e();
        g(1);
    }

    public boolean isRunning() {
        return this.p;
    }

    public int j() {
        return this.t.b;
    }

    public int k() {
        return this.u.b;
    }

    public void a(float f2) {
        if (f2 > 0.0f) {
            this.t.b = (int) (f2 * 280.0f);
        }
    }

    public void b(float f2) {
        if (f2 > 0.0f) {
            this.u.b = (int) (f2 * 160.0f);
        }
    }

    public Interpolator l() {
        return this.t.c;
    }

    public Interpolator m() {
        return this.u.c;
    }

    public void a(Interpolator interpolator) {
        if (interpolator != null) {
            this.t.c = interpolator;
        }
    }

    public void b(Interpolator interpolator) {
        if (interpolator != null) {
            this.u.c = interpolator;
        }
    }

    private void u() {
        s();
        t();
        this.t.a(90);
    }

    private void v() {
        if (!this.t.c()) {
            h();
            this.u.a(0);
        }
    }

    private void w() {
        if (this.p) {
            g(2);
            v();
            return;
        }
        stop();
    }

    private void g(int i2) {
        float f2;
        if (this.r != i2) {
            this.r = i2;
            switch (i2) {
                case 2:
                    f2 = 2.0f;
                    break;
                case 3:
                    f2 = 0.28f;
                    break;
                default:
                    f2 = 1.0f;
                    break;
            }
            if (this.q != f2) {
                this.q = f2;
                this.t.d();
                this.u.d();
            }
        }
    }

    static final class TouchEffectState extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int[] f3134a;
        int b;
        Effect c;
        Rect d;
        int e;
        int f;
        ShaderFactory g;
        ClipFactory h;

        TouchEffectState(TouchEffectState touchEffectState) {
            if (touchEffectState != null) {
                this.f3134a = touchEffectState.f3134a;
                this.c = touchEffectState.c;
                this.d = touchEffectState.d;
                this.e = touchEffectState.e;
                this.f = touchEffectState.f;
                this.g = touchEffectState.g;
                this.h = touchEffectState.h;
            }
        }

        public boolean canApplyTheme() {
            return this.f3134a != null;
        }

        public Drawable newDrawable() {
            return new TouchEffectDrawable(this, (Resources) null, (ColorStateList) null);
        }

        public Drawable newDrawable(Resources resources) {
            return new TouchEffectDrawable(this, resources, (ColorStateList) null);
        }

        public int getChangingConfigurations() {
            return this.b;
        }
    }

    abstract class AnimRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private boolean f3133a = true;
        int b;
        Interpolator c;
        private float e = 0.0f;
        private float f = 0.0f;

        /* access modifiers changed from: package-private */
        public abstract void a();

        /* access modifiers changed from: package-private */
        public abstract void a(float f2);

        /* access modifiers changed from: package-private */
        public abstract void b();

        AnimRunnable() {
            a();
        }

        public boolean c() {
            return !this.f3133a;
        }

        public void d() {
            this.f = (16.0f / ((float) this.b)) * TouchEffectDrawable.this.q;
        }

        public void a(int i) {
            this.f3133a = false;
            d();
            this.e = 0.0f;
            TouchEffectDrawable.this.scheduleSelf(this, SystemClock.uptimeMillis() + ((long) i));
        }

        public void e() {
            TouchEffectDrawable.this.unscheduleSelf(this);
            this.f3133a = true;
        }

        public void run() {
            if (!this.f3133a) {
                boolean unused = TouchEffectDrawable.this.p = true;
                this.e += this.f;
                if (this.e < 1.0f) {
                    a(this.c.getInterpolation(this.e));
                    TouchEffectDrawable.this.invalidateSelf();
                    TouchEffectDrawable.this.scheduleSelf(this, SystemClock.uptimeMillis() + 16);
                    return;
                }
                this.f3133a = true;
                TouchEffectDrawable.this.unscheduleSelf(this);
                a(1.0f);
                TouchEffectDrawable.this.invalidateSelf();
                b();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void c(float f2) {
        if (this.l.c != null) {
            this.l.c.a(f2);
        }
    }

    /* access modifiers changed from: protected */
    public void d(float f2) {
        if (this.l.c != null) {
            this.l.c.b(f2);
        }
    }

    /* access modifiers changed from: protected */
    public void n() {
        if (this.h) {
            v();
        } else {
            i();
        }
    }

    /* access modifiers changed from: protected */
    public void o() {
        this.p = false;
    }
}
