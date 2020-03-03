package net.qiujuer.genius.ui.widget.popup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import net.qiujuer.genius.ui.drawable.BalloonMarkerDrawable;
import net.qiujuer.genius.ui.widget.BalloonMarker;

public class PopupIndicator {

    /* renamed from: a  reason: collision with root package name */
    Point f3162a = new Point();
    private final WindowManager b;
    private boolean c;
    private Floater d;
    /* access modifiers changed from: private */
    public BalloonMarkerDrawable.MarkerAnimationListener e;
    private int[] f = new int[2];

    private int f(int i) {
        return (i & -426521) | 32768 | 8 | 16 | 512;
    }

    public PopupIndicator(Context context) {
        this.b = (WindowManager) context.getSystemService("window");
        this.d = new Floater(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f3162a.set(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public PopupIndicator(Context context, ColorStateList colorStateList, int i, float f2, String str) {
        this.b = (WindowManager) context.getSystemService("window");
        this.d = new Floater(this, context, colorStateList, i, f2, str);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f3162a.set(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            d();
            if (this.d != null) {
                this.d.b.setBackgroundColor(colorStateList);
            }
        }
    }

    public ColorStateList a() {
        if (this.d != null) {
            return this.d.b.getBackgroundColor();
        }
        return null;
    }

    public void a(Typeface typeface) {
        d();
        if (this.d != null) {
            this.d.b.setTypeface(typeface);
        }
    }

    public void a(int i) {
        d();
        if (this.d != null) {
            this.d.b.setSeparation(i);
        }
    }

    public void b(int i) {
        d();
        if (this.d != null) {
            this.d.b.setTextPadding(i);
        }
    }

    public void c(int i) {
        d();
        if (this.d != null) {
            this.d.b.setTextAppearance(i);
        }
    }

    public void a(float f2) {
        d();
        if (this.d != null) {
            this.d.b.setClosedSize(f2);
        }
    }

    public void a(String str) {
        d();
        if (this.d != null) {
            this.d.b.resetSizes(str);
        }
    }

    public void a(BalloonMarkerDrawable.MarkerAnimationListener markerAnimationListener) {
        this.e = markerAnimationListener;
    }

    private void e() {
        this.d.measure(View.MeasureSpec.makeMeasureSpec(this.f3162a.x, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f3162a.y, Integer.MIN_VALUE));
    }

    public void a(CharSequence charSequence) {
        this.d.b.setValue(charSequence);
    }

    public boolean b() {
        return this.c;
    }

    public void a(View view, Point point) {
        if (b()) {
            this.d.b.animateOpen();
            return;
        }
        IBinder windowToken = view.getWindowToken();
        if (windowToken != null) {
            WindowManager.LayoutParams a2 = a(windowToken);
            a2.gravity = 8388659;
            a(view, a2, point.y);
            this.c = true;
            e(point.x);
            a(a2);
        }
    }

    public void d(int i) {
        if (b()) {
            e(i);
        }
    }

    public void a(int i, int i2) {
        this.d.a(i, i2);
    }

    public void c() {
        this.d.b.animateClose();
    }

    public void d() {
        if (b()) {
            this.c = false;
            this.b.removeViewImmediate(this.d);
        }
    }

    private void a(View view, WindowManager.LayoutParams layoutParams, int i) {
        e();
        int measuredHeight = this.d.getMeasuredHeight();
        int paddingBottom = this.d.b.getPaddingBottom();
        view.getLocationInWindow(this.f);
        layoutParams.x = 0;
        layoutParams.y = (this.f[1] - measuredHeight) + i + paddingBottom;
        layoutParams.width = this.f3162a.x;
        layoutParams.height = measuredHeight;
    }

    private void e(int i) {
        this.d.a(i + this.f[0]);
    }

    private void a(WindowManager.LayoutParams layoutParams) {
        this.b.addView(this.d, layoutParams);
        this.d.b.animateOpen();
    }

    private WindowManager.LayoutParams a(IBinder iBinder) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = -3;
        layoutParams.flags = f(layoutParams.flags);
        layoutParams.type = 1000;
        layoutParams.token = iBinder;
        layoutParams.softInputMode = 3;
        layoutParams.setTitle("DiscreteSeekBar Indicator:" + Integer.toHexString(hashCode()));
        return layoutParams;
    }

    private class Floater extends FrameLayout implements BalloonMarkerDrawable.MarkerAnimationListener {
        /* access modifiers changed from: private */
        public BalloonMarker b;
        private int c;

        public Floater(Context context) {
            super(context);
            this.b = new BalloonMarker(context);
            addView(this.b, new FrameLayout.LayoutParams(-2, -2, 51));
        }

        public Floater(PopupIndicator popupIndicator, Context context, ColorStateList colorStateList, int i, float f, String str) {
            this(context);
            this.b.setBackgroundColor(colorStateList);
            this.b.setTextAppearance(i);
            this.b.setClosedSize(f);
            this.b.resetSizes(str);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            measureChildren(i, i2);
            setMeasuredDimension(View.MeasureSpec.getSize(i), this.b.getMeasuredHeight());
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int measuredWidth = this.c - (this.b.getMeasuredWidth() / 2);
            this.b.layout(measuredWidth, 0, this.b.getMeasuredWidth() + measuredWidth, this.b.getMeasuredHeight());
        }

        public void a(int i) {
            this.c = i;
            this.b.offsetLeftAndRight((i - (this.b.getMeasuredWidth() / 2)) - this.b.getLeft());
            if (!isHardwareAccelerated()) {
                invalidate();
            }
        }

        public void onClosingComplete() {
            if (PopupIndicator.this.e != null) {
                PopupIndicator.this.e.onClosingComplete();
            }
            PopupIndicator.this.d();
        }

        public void onOpeningComplete() {
            if (PopupIndicator.this.e != null) {
                PopupIndicator.this.e.onOpeningComplete();
            }
        }

        public void a(int i, int i2) {
            this.b.setColors(i, i2);
        }
    }
}
