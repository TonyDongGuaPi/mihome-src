package net.qiujuer.genius.ui.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Formatter;
import java.util.Locale;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.AlmostRippleDrawable;
import net.qiujuer.genius.ui.drawable.BalloonMarkerDrawable;
import net.qiujuer.genius.ui.drawable.SeekBarDrawable;
import net.qiujuer.genius.ui.widget.popup.PopupIndicator;

public abstract class AbsSeekBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3142a = "%d";
    private static final int b = 16842919;
    private static final int c = 16842908;
    private static final int d = 250;
    private static final int e = 150;
    private int A;
    private float B;
    private float C;
    private AlmostRippleDrawable f;
    /* access modifiers changed from: private */
    public SeekBarDrawable g;
    private final BalloonMarkerDrawable.MarkerAnimationListener h = new BalloonMarkerDrawable.MarkerAnimationListener() {
        public void onOpeningComplete() {
        }

        public void onClosingComplete() {
            AbsSeekBar.this.g.j();
        }
    };
    private final Runnable i = new Runnable() {
        public void run() {
            AbsSeekBar.this.g();
        }
    };
    /* access modifiers changed from: private */
    public int j = 100;
    /* access modifiers changed from: private */
    public int k = 0;
    private int l = 0;
    private int m = 1;
    private boolean n = false;
    private boolean o = true;
    private Formatter p;
    private String q;
    private NumericTransformer r;
    private StringBuilder s;
    private boolean t;
    private int u;
    private Rect v = new Rect();
    private Rect w = new Rect();
    private PopupIndicator x;
    private ValueAnimator y;
    /* access modifiers changed from: private */
    public float z;

    /* access modifiers changed from: protected */
    public void onHideBubble() {
    }

    /* access modifiers changed from: protected */
    public void onProgressChanged(int i2, boolean z2) {
    }

    /* access modifiers changed from: protected */
    public void onShowBubble() {
    }

    public AbsSeekBar(Context context) {
        super(context);
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, R.attr.gSeekBarStyle, R.style.Genius_Widget_SeekBar);
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet, i2, R.style.Genius_Widget_SeekBar);
    }

    @TargetApi(21)
    public AbsSeekBar(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(attributeSet, i2, i3);
    }

    private void a(AttributeSet attributeSet, int i2, int i3) {
        Context context = getContext();
        Resources resources = getResources();
        boolean z2 = !isInEditMode();
        setFocusable(true);
        setWillNotDraw(false);
        this.C = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.f = new AlmostRippleDrawable(UiCompat.b(resources, R.color.g_default_seek_bar_ripple));
        this.f.setCallback(this);
        this.g = new SeekBarDrawable(UiCompat.b(resources, R.color.g_default_seek_bar_track), UiCompat.b(resources, R.color.g_default_seek_bar_scrubber), UiCompat.b(resources, R.color.g_default_seek_bar_thumb));
        this.g.setCallback(this);
        if (attributeSet == null) {
            this.g.f(resources.getDimensionPixelSize(R.dimen.g_seekBar_trackStroke));
            this.g.e(resources.getDimensionPixelSize(R.dimen.g_seekBar_scrubberStroke));
            this.g.d(resources.getDimensionPixelSize(R.dimen.g_seekBar_touchSize));
            this.g.b(resources.getDimensionPixelSize(R.dimen.g_seekBar_tickSize));
            this.g.c(resources.getDimensionPixelSize(R.dimen.g_seekBar_thumbSize));
            if (z2) {
                this.x = new PopupIndicator(context);
                this.x.a(this.h);
                this.x.a(UiCompat.b(resources, R.color.g_default_seek_bar_indicator));
                this.x.a((float) (this.g.b() * 2));
            }
        } else {
            a(context, resources, z2, attributeSet, i2, i3);
        }
        this.g.a(this.j - this.k);
        b();
        setNumericTransformer(new DefaultNumericTransformer());
        isRtl();
    }

    private void a(Context context, Resources resources, boolean z2, AttributeSet attributeSet, int i2, int i3) {
        Typeface a2;
        Context context2 = context;
        Resources resources2 = resources;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.AbsSeekBar, i2, i3);
        int integer = obtainStyledAttributes.getInteger(R.styleable.AbsSeekBar_gMax, this.j);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.AbsSeekBar_gMin, this.k);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.AbsSeekBar_gValue, this.l);
        this.k = integer2;
        this.j = Math.max(integer2 + 1, integer);
        this.l = Math.max(integer2, Math.min(integer, integer3));
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.AbsSeekBar_gTrackColor);
        ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.AbsSeekBar_gThumbColor);
        ColorStateList colorStateList3 = obtainStyledAttributes.getColorStateList(R.styleable.AbsSeekBar_gScrubberColor);
        ColorStateList colorStateList4 = obtainStyledAttributes.getColorStateList(R.styleable.AbsSeekBar_gRippleColor);
        ColorStateList colorStateList5 = obtainStyledAttributes.getColorStateList(R.styleable.AbsSeekBar_gIndicatorBackgroundColor);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gTickSize, resources2.getDimensionPixelSize(R.dimen.g_seekBar_tickSize));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gThumbSize, resources2.getDimensionPixelSize(R.dimen.g_seekBar_thumbSize));
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gTouchSize, resources2.getDimensionPixelSize(R.dimen.g_seekBar_touchSize));
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gTrackStroke, resources2.getDimensionPixelSize(R.dimen.g_seekBar_trackStroke));
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gScrubberStroke, resources2.getDimensionPixelSize(R.dimen.g_seekBar_scrubberStroke));
        int i4 = obtainStyledAttributes.getInt(R.styleable.AbsSeekBar_gIndicator, 1);
        ColorStateList colorStateList6 = colorStateList5;
        this.n = obtainStyledAttributes.getBoolean(R.styleable.AbsSeekBar_gMirrorForRtl, this.n);
        this.o = obtainStyledAttributes.getBoolean(R.styleable.AbsSeekBar_gAllowTrackClickToDrag, this.o);
        this.q = obtainStyledAttributes.getString(R.styleable.AbsSeekBar_gIndicatorFormatter);
        int dimensionPixelSize6 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gIndicatorTextPadding, resources2.getDimensionPixelSize(R.dimen.g_balloonMarker_textPadding));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AbsSeekBar_gIndicatorTextAppearance, R.style.Genius_Widget_BalloonMarker_TextAppearance);
        String string = obtainStyledAttributes.getString(R.styleable.AbsSeekBar_gFont);
        int i5 = resourceId;
        int dimensionPixelSize7 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbsSeekBar_gIndicatorSeparation, resources2.getDimensionPixelSize(R.dimen.g_balloonMarker_separation));
        obtainStyledAttributes.recycle();
        this.g.f(dimensionPixelSize4);
        this.g.e(dimensionPixelSize5);
        this.g.d(dimensionPixelSize3);
        this.g.b(dimensionPixelSize);
        this.g.c(dimensionPixelSize2);
        if (colorStateList4 != null) {
            this.f.a(colorStateList4);
        }
        if (colorStateList != null) {
            this.g.a(colorStateList);
        }
        if (colorStateList2 != null) {
            this.g.c(colorStateList2);
        }
        if (colorStateList3 != null) {
            this.g.b(colorStateList3);
        }
        if (z2 && i4 != 0) {
            this.x = new PopupIndicator(context2);
            this.x.a(this.h);
            if (colorStateList6 != null) {
                this.x.a(colorStateList6);
            }
            this.x.c(i5);
            this.x.a((float) (dimensionPixelSize2 * 2));
            this.x.b(dimensionPixelSize6);
            this.x.a(dimensionPixelSize7);
            if (!(string == null || string.length() <= 0 || (a2 = Ui.a(getContext(), string)) == null)) {
                this.x.a(a2);
            }
        }
        setEnabled(Ui.a(context, attributeSet, 16842766, i2, i3, isEnabled()));
    }

    public void setTrackStroke(int i2) {
        if (i2 != this.g.e()) {
            this.g.f(i2);
            invalidate();
        }
    }

    public void setScrubberStroke(int i2) {
        if (i2 != this.g.d()) {
            this.g.e(i2);
            invalidate();
        }
    }

    public void setThumbRadius(int i2) {
        if (i2 != this.g.b()) {
            this.g.c(i2);
            if (!isInEditMode() && this.x != null) {
                this.x.a((float) (i2 * 2));
            }
            invalidate();
        }
    }

    public void setTouchRadius(int i2) {
        if (i2 != this.g.c()) {
            this.g.d(i2);
            invalidate();
        }
    }

    public void setTickRadius(int i2) {
        if (i2 != this.g.a()) {
            this.g.b(i2);
            invalidate();
        }
    }

    public void setIndicatorColor(ColorStateList colorStateList) {
        if (colorStateList != null && this.x != null && colorStateList != this.x.a()) {
            this.x.a(colorStateList);
            invalidate();
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (colorStateList != null && colorStateList != this.f.d()) {
            this.f.a(colorStateList);
            invalidate();
        }
    }

    public void setScrubberColor(ColorStateList colorStateList) {
        if (colorStateList != null && colorStateList != this.g.l()) {
            this.g.b(colorStateList);
            invalidate();
        }
    }

    public void setThumbColor(ColorStateList colorStateList) {
        if (colorStateList != null && colorStateList != this.g.m()) {
            this.g.c(colorStateList);
            invalidate();
        }
    }

    public void setTrackColor(ColorStateList colorStateList) {
        if (colorStateList != null && colorStateList != this.g.k()) {
            this.g.a(colorStateList);
            invalidate();
        }
    }

    public void setIndicatorFormatter(String str) {
        if (this.x != null) {
            this.q = str;
            a(this.l);
        }
    }

    public NumericTransformer getNumericTransformer() {
        return this.r;
    }

    public void setNumericTransformer(NumericTransformer numericTransformer) {
        if (numericTransformer == null) {
            numericTransformer = new DefaultNumericTransformer();
        }
        this.r = numericTransformer;
        if (!isInEditMode() && this.x != null) {
            if (this.r.a()) {
                this.x.a(this.r.b(this.j));
            } else {
                this.x.a(b(this.r.a(this.j)));
            }
        }
        a(this.l);
    }

    public int getMax() {
        return this.j;
    }

    public void setMax(int i2) {
        this.j = i2;
        if (this.j <= this.k) {
            setMin(this.j - 1);
        }
        b();
        this.g.a(this.j - this.k);
        if (this.l < this.k || this.l > this.j) {
            setProgress(this.l);
        } else {
            c(-1.0f);
        }
    }

    public int getMin() {
        return this.k;
    }

    public void setMin(int i2) {
        this.k = i2;
        if (this.k > this.j) {
            setMax(this.k + 1);
        }
        b();
        this.g.a(this.j - this.k);
        if (this.l < this.k || this.l > this.j) {
            setProgress(this.l);
        } else {
            c(-1.0f);
        }
    }

    public int getProgress() {
        return this.l;
    }

    public void setProgress(int i2) {
        a(i2, false, -1.0f);
    }

    public void setThumbColor(int i2, int i3) {
        this.g.c(ColorStateList.valueOf(i2));
        if (this.x != null) {
            this.x.a(i2, i3);
        }
    }

    public void setScrubberColor(int i2) {
        this.g.setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(View.MeasureSpec.getSize(i2), this.g.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            removeCallbacks(this.i);
            if (!isInEditMode() && this.x != null) {
                this.x.d();
            }
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.g.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        c(-1.0f);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.g.draw(canvas);
        if (isEnabled()) {
            this.f.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.B = motionEvent.getX();
                a(motionEvent, a());
                return true;
            case 1:
            case 3:
                if (this.g.g()) {
                    e();
                }
                onStopTrackingTouch();
                return true;
            case 2:
                if (this.t) {
                    a(motionEvent);
                    return true;
                } else if (Math.abs(motionEvent.getX() - this.B) <= this.C) {
                    return true;
                } else {
                    a(motionEvent, false);
                    return true;
                }
            default:
                return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r0 >= r5.j) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        c(r0 + r5.m);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        if (r0 <= r5.k) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        c(r0 - r5.m);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r4 = r3;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r3 == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r4 == false) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r6, android.view.KeyEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0038
            int r0 = r5.getAnimatedProgress()
            switch(r6) {
                case 21: goto L_0x0018;
                case 22: goto L_0x0012;
                default: goto L_0x000f;
            }
        L_0x000f:
            r3 = 0
            r4 = 0
            goto L_0x001e
        L_0x0012:
            boolean r3 = r5.isRtl()
            r3 = r3 ^ r2
            goto L_0x001c
        L_0x0018:
            boolean r3 = r5.isRtl()
        L_0x001c:
            r4 = r3
            r3 = 1
        L_0x001e:
            if (r3 == 0) goto L_0x0039
            if (r4 == 0) goto L_0x002d
            int r4 = r5.j
            if (r0 >= r4) goto L_0x0039
            int r4 = r5.m
            int r0 = r0 + r4
            r5.c((int) r0)
            goto L_0x0039
        L_0x002d:
            int r4 = r5.k
            if (r0 <= r4) goto L_0x0039
            int r4 = r5.m
            int r0 = r0 - r4
            r5.c((int) r0)
            goto L_0x0039
        L_0x0038:
            r3 = 0
        L_0x0039:
            if (r3 != 0) goto L_0x0041
            boolean r6 = super.onKeyDown(r6, r7)
            if (r6 == 0) goto L_0x0042
        L_0x0041:
            r1 = 1
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.qiujuer.genius.ui.widget.AbsSeekBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.g || drawable == this.f || super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        c();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.i);
        if (!isInEditMode() && this.x != null) {
            this.x.d();
        }
    }

    public boolean isRtl() {
        boolean z2 = false;
        if ((Build.VERSION.SDK_INT >= 17 ? getLayoutDirection() : 0) == 1 && this.n) {
            z2 = true;
        }
        this.g.a(z2);
        return z2;
    }

    private boolean a() {
        ViewParent parent = getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    private void a(int i2, boolean z2, float f2) {
        int max = Math.max(this.k, Math.min(this.j, i2));
        if (d()) {
            this.y.cancel();
        }
        if (this.l != max) {
            this.l = max;
            onProgressChanged(max, z2);
            a(max);
        }
        c(f2);
    }

    private void b() {
        int i2 = this.j - this.k;
        if (this.m == 0 || i2 / this.m > 20) {
            this.m = Math.max(1, Math.round(((float) i2) / 20.0f));
        }
    }

    private void c() {
        int[] drawableState = getDrawableState();
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 : drawableState) {
            if (i2 == c) {
                z2 = true;
            } else if (i2 == b) {
                z3 = true;
            }
        }
        if (!isEnabled() || (!z2 && !z3)) {
            h();
        } else {
            removeCallbacks(this.i);
            postDelayed(this.i, 150);
        }
        this.f.setState(drawableState);
        this.g.setState(drawableState);
    }

    private void a(int i2) {
        if (!isInEditMode() && this.x != null) {
            if (this.r.a()) {
                this.x.a((CharSequence) this.r.b(i2));
            } else {
                this.x.a((CharSequence) b(this.r.a(i2)));
            }
        }
    }

    private String b(int i2) {
        String str = this.q != null ? this.q : f3142a;
        if (this.p == null || !this.p.locale().equals(Locale.getDefault())) {
            int length = str.length() + String.valueOf(this.j).length();
            if (this.s == null) {
                this.s = new StringBuilder(length);
            } else {
                this.s.ensureCapacity(length);
            }
            this.p = new Formatter(this.s, Locale.getDefault());
        } else {
            this.s.setLength(0);
        }
        return this.p.format(str, new Object[]{Integer.valueOf(i2)}).toString();
    }

    private void a(MotionEvent motionEvent, boolean z2) {
        Rect rect = this.w;
        this.g.a(rect);
        boolean contains = rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        if (!contains && this.o && !z2) {
            contains = true;
            this.u = rect.width() / 2;
            a(motionEvent);
            this.g.a(this.g.f());
        }
        if (contains) {
            onStartTrackingTouch();
            a(motionEvent.getX(), motionEvent.getY());
            this.u = (int) (motionEvent.getX() - ((float) rect.centerX()));
        }
    }

    private int getAnimatedProgress() {
        return d() ? getAnimationTarget() : getProgress();
    }

    private boolean d() {
        return this.y != null && this.y.isRunning();
    }

    private void e() {
        float animationPosition = d() ? getAnimationPosition() : (this.g.f() * ((float) (this.j - this.k))) + ((float) this.k);
        this.A = getProgress();
        a(animationPosition);
    }

    private void c(int i2) {
        float animationPosition = d() ? getAnimationPosition() : (float) getProgress();
        if (i2 < this.k) {
            i2 = this.k;
        } else if (i2 > this.j) {
            i2 = this.j;
        }
        this.A = i2;
        a(animationPosition);
    }

    private void a(float f2) {
        if (this.y != null) {
            this.y.cancel();
            this.y.setFloatValues(new float[]{f2, (float) this.A});
        } else {
            this.y = ValueAnimator.ofFloat(new float[]{f2, (float) this.A});
            this.y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = AbsSeekBar.this.z = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    AbsSeekBar.this.b((AbsSeekBar.this.z - ((float) AbsSeekBar.this.k)) / ((float) (AbsSeekBar.this.j - AbsSeekBar.this.k)));
                }
            });
            this.y.setDuration(250);
        }
        this.y.start();
    }

    private int getAnimationTarget() {
        return this.A;
    }

    private float getAnimationPosition() {
        return this.z;
    }

    private void a(MotionEvent motionEvent) {
        a(motionEvent.getX(), motionEvent.getY());
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int x2 = ((int) motionEvent.getX()) - this.u;
        if (x2 < paddingLeft) {
            x2 = paddingLeft;
        } else if (x2 > width) {
            x2 = width;
        }
        float f2 = ((float) (x2 - paddingLeft)) / ((float) (width - paddingLeft));
        if (isRtl()) {
            f2 = 1.0f - f2;
        }
        a(Math.round((((float) (this.j - this.k)) * f2) + ((float) this.k)), true, f2);
    }

    /* access modifiers changed from: private */
    public void b(float f2) {
        int round = Math.round((((float) (this.j - this.k)) * f2) + ((float) this.k));
        if (round != getProgress()) {
            this.l = round;
            onProgressChanged(this.l, true);
            a(round);
        }
        c(f2);
    }

    private void c(float f2) {
        if (f2 == -1.0f) {
            f2 = ((float) (this.l - this.k)) / ((float) (this.j - this.k));
        }
        this.g.a(f2);
        Rect rect = this.w;
        this.g.a(rect);
        if (!isInEditMode() && this.x != null) {
            this.x.d(rect.centerX());
        }
        this.f.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        this.g.copyBounds(this.v);
        invalidate(this.v);
    }

    @TargetApi(21)
    private void a(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f.setHotspot(f2, f3);
        }
    }

    private void f() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (!isInEditMode() && this.x != null) {
            this.g.i();
            this.x.a((View) this, this.g.h());
            onShowBubble();
        }
    }

    private void h() {
        removeCallbacks(this.i);
        if (!isInEditMode() && this.x != null) {
            this.x.c();
            onHideBubble();
        }
    }

    /* access modifiers changed from: protected */
    public void onStartTrackingTouch() {
        this.t = true;
        setPressed(true);
        f();
    }

    /* access modifiers changed from: protected */
    public void onStopTrackingTouch() {
        this.t = false;
        setPressed(false);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        CustomState customState = new CustomState(super.onSaveInstanceState());
        int unused = customState.f3146a = getProgress();
        int unused2 = customState.b = this.j;
        int unused3 = customState.c = this.k;
        return customState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(CustomState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        CustomState customState = (CustomState) parcelable;
        setMin(customState.c);
        setMax(customState.b);
        setProgress(customState.f3146a);
        super.onRestoreInstanceState(customState.getSuperState());
    }

    public static abstract class NumericTransformer {
        public abstract int a(int i);

        public boolean a() {
            return false;
        }

        public String b(int i) {
            return String.valueOf(i);
        }
    }

    private static class DefaultNumericTransformer extends NumericTransformer {
        public int a(int i) {
            return i;
        }

        private DefaultNumericTransformer() {
        }
    }

    static class CustomState extends View.BaseSavedState {
        public static final Parcelable.Creator<CustomState> CREATOR = new Parcelable.Creator<CustomState>() {
            /* renamed from: a */
            public CustomState[] newArray(int i) {
                return new CustomState[i];
            }

            /* renamed from: a */
            public CustomState createFromParcel(Parcel parcel) {
                return new CustomState(parcel);
            }
        };
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f3146a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;

        public CustomState(Parcel parcel) {
            super(parcel);
            this.f3146a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        public CustomState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3146a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }
    }
}
