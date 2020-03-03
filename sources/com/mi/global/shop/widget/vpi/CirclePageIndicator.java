package com.mi.global.shop.widget.vpi;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.BasePageIndicatorAdapter;

public class CirclePageIndicator extends View implements PageIndicator {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7299a = -1;
    private float b;
    private final Paint c;
    private final Paint d;
    private final Paint e;
    private ViewPager f;
    private ViewPager.OnPageChangeListener g;
    private int h;
    private int i;
    private float j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private float q;
    private int r;
    private boolean s;
    private int t;

    public CirclePageIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = new Paint(1);
        this.d = new Paint(1);
        this.e = new Paint(1);
        this.q = -1.0f;
        this.r = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(R.color.default_circle_indicator_page_color);
            int color2 = resources.getColor(R.color.default_circle_indicator_fill_color);
            int integer = resources.getInteger(R.integer.default_circle_indicator_orientation);
            int color3 = resources.getColor(R.color.default_circle_indicator_stroke_color);
            float dimension = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
            float dimension2 = resources.getDimension(R.dimen.default_circle_indicator_radius);
            boolean z = resources.getBoolean(R.bool.default_circle_indicator_centered);
            boolean z2 = resources.getBoolean(R.bool.default_circle_indicator_snap);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CirclePageIndicator, i2, 0);
            this.m = obtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_centered, z);
            this.l = obtainStyledAttributes.getInt(R.styleable.CirclePageIndicator_android_orientation, integer);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_pageColor, color));
            this.d.setStyle(Paint.Style.STROKE);
            this.d.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_strokeColor, color3));
            this.d.setStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_strokeWidth, dimension));
            this.e.setStyle(Paint.Style.FILL);
            this.e.setColor(obtainStyledAttributes.getColor(R.styleable.CirclePageIndicator_fillColor, color2));
            this.b = obtainStyledAttributes.getDimension(R.styleable.CirclePageIndicator_radius, dimension2);
            this.o = obtainStyledAttributes.getBoolean(R.styleable.CirclePageIndicator_snap, z2);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.CirclePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.p = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public void setRealCount(int i2) {
        this.t = i2;
    }

    public void setCentered(boolean z) {
        this.m = z;
        invalidate();
    }

    public void setRighted(boolean z) {
        this.n = z;
        invalidate();
    }

    public boolean isCentered() {
        return this.m;
    }

    public void setPageColor(int i2) {
        this.c.setColor(i2);
        invalidate();
    }

    public int getPageColor() {
        return this.c.getColor();
    }

    public void setFillColor(int i2) {
        this.e.setColor(i2);
        invalidate();
    }

    public int getFillColor() {
        return this.e.getColor();
    }

    public void setOrientation(int i2) {
        switch (i2) {
            case 0:
            case 1:
                this.l = i2;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return this.l;
    }

    public void setStrokeColor(int i2) {
        this.d.setColor(i2);
        invalidate();
    }

    public int getStrokeColor() {
        return this.d.getColor();
    }

    public void setStrokeWidth(float f2) {
        this.d.setStrokeWidth(f2);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.d.getStrokeWidth();
    }

    public void setRadius(float f2) {
        this.b = f2;
        invalidate();
    }

    public float getRadius() {
        return this.b;
    }

    public void setSnap(boolean z) {
        this.o = z;
        invalidate();
    }

    public boolean isSnap() {
        return this.o;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        float f2;
        float f3;
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        if (this.f != null) {
            if (this.f.getAdapter() instanceof BasePageIndicatorAdapter) {
                i2 = ((BasePageIndicatorAdapter) this.f.getAdapter()).a();
            } else {
                i2 = this.f.getAdapter().getCount();
            }
            this.t = i2;
            if (i2 != 0) {
                if (this.h >= i2) {
                    setCurrentItem(i2 - 1);
                    return;
                }
                if (this.l == 0) {
                    i6 = getWidth();
                    i5 = getPaddingLeft();
                    i4 = getPaddingRight();
                    i3 = getPaddingTop();
                } else {
                    i6 = getHeight();
                    i5 = getPaddingTop();
                    i4 = getPaddingBottom();
                    i3 = getPaddingLeft();
                }
                double d2 = (double) this.b;
                Double.isNaN(d2);
                float f4 = (float) (d2 * 3.618d);
                float f5 = ((float) i3) + this.b;
                float f6 = ((float) i5) + this.b;
                if (this.m) {
                    double d3 = (double) f6;
                    double d4 = (double) (((float) i2) * f4);
                    double d5 = (double) (((float) ((i6 - i5) - i4)) / 2.0f);
                    double d6 = (double) this.b;
                    Double.isNaN(d6);
                    Double.isNaN(d4);
                    Double.isNaN(d5);
                    Double.isNaN(d3);
                    f6 = (float) (d3 + (d5 - ((d4 - (d6 * 1.618d)) / 2.0d)));
                }
                if (this.n) {
                    f6 += ((float) ((i6 - i5) - i4)) - (((float) i2) * f4);
                }
                float f7 = this.b;
                if (this.d.getStrokeWidth() > 0.0f) {
                    f7 -= this.d.getStrokeWidth() / 2.0f;
                }
                for (int i7 = 0; i7 < i2; i7++) {
                    float f8 = (((float) i7) * f4) + f6;
                    if (this.l == 0) {
                        f3 = f5;
                    } else {
                        f3 = f8;
                        f8 = f5;
                    }
                    if (this.c.getAlpha() > 0) {
                        canvas2.drawCircle(f8, f3, f7, this.c);
                    }
                    if (f7 != this.b) {
                        canvas2.drawCircle(f8, f3, this.b, this.d);
                    }
                }
                float f9 = ((float) (this.o ? this.i : this.h)) * f4;
                if (!this.o) {
                    f9 += this.j * f4;
                }
                if (this.l == 0) {
                    f2 = f9 + f6;
                } else {
                    float f10 = f5;
                    f5 = f9 + f6;
                    f2 = f10;
                }
                canvas2.drawCircle(f2, f5, this.b, this.e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        int i2 = 0;
        if (this.f == null || this.f.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                this.r = MotionEventCompat.getPointerId(motionEvent, 0);
                this.q = motionEvent.getX();
                break;
            case 1:
            case 3:
                if (!this.s) {
                    int count = this.f.getAdapter().getCount();
                    float width = (float) getWidth();
                    float f2 = width / 2.0f;
                    float f3 = width / 6.0f;
                    if (this.h > 0 && motionEvent.getX() < f2 - f3) {
                        if (action != 3) {
                            this.f.setCurrentItem(this.h - 1);
                        }
                        return true;
                    } else if (this.h < count - 1 && motionEvent.getX() > f2 + f3) {
                        if (action != 3) {
                            this.f.setCurrentItem(this.h + 1);
                        }
                        return true;
                    }
                }
                this.s = false;
                this.r = -1;
                if (this.f.isFakeDragging()) {
                    this.f.endFakeDrag();
                    break;
                }
                break;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.r));
                float f4 = x - this.q;
                if (!this.s && Math.abs(f4) > ((float) this.p)) {
                    this.s = true;
                }
                if (this.s) {
                    this.q = x;
                    if (this.f.isFakeDragging() || this.f.beginFakeDrag()) {
                        this.f.fakeDragBy(f4);
                        break;
                    }
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.q = MotionEventCompat.getX(motionEvent, actionIndex);
                this.r = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.r) {
                    if (actionIndex2 == 0) {
                        i2 = 1;
                    }
                    this.r = MotionEventCompat.getPointerId(motionEvent, i2);
                }
                this.q = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.r));
                break;
        }
        return true;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.f != viewPager) {
            if (this.f != null) {
                this.f.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
            }
            if (viewPager.getAdapter() != null) {
                this.f = viewPager;
                this.f.setOnPageChangeListener(this);
                invalidate();
                return;
            }
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    public void setViewPager(ViewPager viewPager, int i2) {
        setViewPager(viewPager);
        setCurrentItem(i2);
    }

    public void setCurrentItem(int i2) {
        if (this.f != null) {
            this.f.setCurrentItem(i2);
            this.h = i2;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void notifyDataSetChanged() {
        invalidate();
    }

    public void onPageScrollStateChanged(int i2) {
        this.k = i2;
        if (this.g != null) {
            this.g.onPageScrollStateChanged(i2);
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        this.h = i2 % this.t;
        this.j = f2;
        invalidate();
        if (this.g != null) {
            this.g.onPageScrolled(i2, f2, i3);
        }
    }

    public void onPageSelected(int i2) {
        if (this.o || this.k == 0) {
            this.h = i2 % this.t;
            this.i = i2 % this.t;
            invalidate();
        }
        if (this.g != null) {
            this.g.onPageSelected(i2 % this.t);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.l == 0) {
            setMeasuredDimension(a(i2), b(i3));
        } else {
            setMeasuredDimension(b(i2), a(i3));
        }
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || this.f == null) {
            return size;
        }
        int count = this.f.getAdapter().getCount();
        int paddingLeft = (int) (((float) (getPaddingLeft() + getPaddingRight())) + (((float) (count * 2)) * this.b) + (((float) (count - 1)) * this.b) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int b(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((this.b * 2.0f) + ((float) getPaddingTop()) + ((float) getPaddingBottom()) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.h = savedState.f7300a;
        this.i = savedState.f7300a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f7300a = this.h;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f7300a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f7300a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f7300a);
        }
    }
}
