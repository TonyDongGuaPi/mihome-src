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

public class LinePageIndicator extends View implements PageIndicator {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7301a = -1;
    private final Paint b;
    private final Paint c;
    private ViewPager d;
    private ViewPager.OnPageChangeListener e;
    private int f;
    private boolean g;
    private float h;
    private float i;
    private float j;
    private int k;
    private float l;
    private int m;
    private boolean n;
    private int o;

    public LinePageIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.l = -1.0f;
        this.m = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(R.color.default_line_indicator_selected_color);
            int color2 = resources.getColor(R.color.default_line_indicator_unselected_color);
            float dimension = resources.getDimension(R.dimen.default_line_indicator_line_width);
            float dimension2 = resources.getDimension(R.dimen.default_line_indicator_line_height);
            float dimension3 = resources.getDimension(R.dimen.default_line_indicator_gap_width);
            float dimension4 = resources.getDimension(R.dimen.default_line_indicator_stroke_width);
            boolean z = resources.getBoolean(R.bool.default_line_indicator_centered);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinePageIndicator, i2, 0);
            this.g = obtainStyledAttributes.getBoolean(R.styleable.LinePageIndicator_centered, z);
            this.h = obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_lineWidth, dimension);
            this.i = obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_lineHeight, dimension2);
            this.j = obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_gapWidth, dimension3);
            setStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.LinePageIndicator_strokeWidth, dimension4));
            this.b.setColor(obtainStyledAttributes.getColor(R.styleable.LinePageIndicator_unselectedColor, color2));
            this.c.setColor(obtainStyledAttributes.getColor(R.styleable.LinePageIndicator_selectedColor, color));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.LinePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.k = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public void setCentered(boolean z) {
        this.g = z;
        invalidate();
    }

    public void setRealCount(int i2) {
        this.o = i2;
    }

    public boolean isCentered() {
        return this.g;
    }

    public void setUnselectedColor(int i2) {
        this.b.setColor(i2);
        invalidate();
    }

    public int getUnselectedColor() {
        return this.b.getColor();
    }

    public void setSelectedColor(int i2) {
        this.c.setColor(i2);
        invalidate();
    }

    public int getSelectedColor() {
        return this.c.getColor();
    }

    public void setLineWidth(float f2) {
        this.h = f2;
        invalidate();
    }

    public float getLineWidth() {
        return this.h;
    }

    public void setStrokeWidth(float f2) {
        this.c.setStrokeWidth(f2);
        this.b.setStrokeWidth(f2);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.c.getStrokeWidth();
    }

    public void setGapWidth(float f2) {
        this.j = f2;
        invalidate();
    }

    public float getGapWidth() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (this.d != null) {
            if (this.d.getAdapter() instanceof BasePageIndicatorAdapter) {
                i2 = ((BasePageIndicatorAdapter) this.d.getAdapter()).a();
            } else {
                i2 = this.d.getAdapter().getCount();
            }
            if (i2 != 0) {
                this.o = i2;
                if (this.f >= i2) {
                    setCurrentItem(i2 - 1);
                    return;
                }
                float f2 = this.h + this.j;
                float f3 = (((float) i2) * f2) - this.j;
                float paddingTop = (float) getPaddingTop();
                float paddingLeft = (float) getPaddingLeft();
                float paddingRight = (float) getPaddingRight();
                float height = paddingTop + (((((float) getHeight()) - paddingTop) - ((float) getPaddingBottom())) / 2.0f);
                if (this.g) {
                    paddingLeft += (((((float) getWidth()) - paddingLeft) - paddingRight) / 2.0f) - (f3 / 2.0f);
                }
                int i3 = 0;
                while (i3 < i2) {
                    float f4 = paddingLeft + (((float) i3) * f2);
                    canvas.drawRect(f4, height, f4 + this.h, height + this.i, i3 == this.f ? this.c : this.b);
                    i3++;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        int i2 = 0;
        if (this.d == null || this.d.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                this.m = MotionEventCompat.getPointerId(motionEvent, 0);
                this.l = motionEvent.getX();
                break;
            case 1:
            case 3:
                if (!this.n) {
                    int count = this.d.getAdapter().getCount();
                    float width = (float) getWidth();
                    float f2 = width / 2.0f;
                    float f3 = width / 6.0f;
                    if (this.f > 0 && motionEvent.getX() < f2 - f3) {
                        if (action != 3) {
                            this.d.setCurrentItem(this.f - 1);
                        }
                        return true;
                    } else if (this.f < count - 1 && motionEvent.getX() > f2 + f3) {
                        if (action != 3) {
                            this.d.setCurrentItem(this.f + 1);
                        }
                        return true;
                    }
                }
                this.n = false;
                this.m = -1;
                try {
                    if (this.d.isFakeDragging()) {
                        this.d.endFakeDrag();
                        break;
                    }
                } catch (NullPointerException unused) {
                    break;
                }
                break;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.m));
                float f4 = x - this.l;
                if (!this.n && Math.abs(f4) > ((float) this.k)) {
                    this.n = true;
                }
                if (this.n) {
                    this.l = x;
                    if (this.d.isFakeDragging() || this.d.beginFakeDrag()) {
                        this.d.fakeDragBy(f4);
                        break;
                    }
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.l = MotionEventCompat.getX(motionEvent, actionIndex);
                this.m = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.m) {
                    if (actionIndex2 == 0) {
                        i2 = 1;
                    }
                    this.m = MotionEventCompat.getPointerId(motionEvent, i2);
                }
                this.l = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.m));
                break;
        }
        return true;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.d != viewPager) {
            if (this.d != null) {
                this.d.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
            }
            if (viewPager.getAdapter() != null) {
                this.d = viewPager;
                this.d.setOnPageChangeListener(this);
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
        if (this.d != null) {
            this.d.setCurrentItem(i2);
            this.f = i2;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void notifyDataSetChanged() {
        invalidate();
    }

    public void onPageScrollStateChanged(int i2) {
        if (this.e != null) {
            this.e.onPageScrollStateChanged(i2);
        }
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.e != null) {
            this.e.onPageScrolled(i2, f2, i3);
        }
    }

    public void onPageSelected(int i2) {
        this.f = i2 % this.o;
        invalidate();
        if (this.e != null) {
            this.e.onPageSelected(i2);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.e = onPageChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(a(i2), b(i3));
    }

    private int a(int i2) {
        float f2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || this.d == null) {
            f2 = (float) size;
        } else {
            int count = this.d.getAdapter().getCount();
            f2 = ((float) (getPaddingLeft() + getPaddingRight())) + (((float) count) * this.h) + (((float) (count - 1)) * this.j);
            if (mode == Integer.MIN_VALUE) {
                f2 = Math.min(f2, (float) size);
            }
        }
        return (int) Math.ceil((double) f2);
    }

    private int b(int i2) {
        float f2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            f2 = (float) size;
        } else {
            float strokeWidth = this.c.getStrokeWidth() + ((float) getPaddingTop()) + ((float) getPaddingBottom());
            f2 = mode == Integer.MIN_VALUE ? Math.min(strokeWidth, (float) size) : strokeWidth;
        }
        return (int) Math.ceil((double) f2);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f = savedState.f7302a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f7302a = this.f;
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
        int f7302a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f7302a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f7302a);
        }
    }
}
