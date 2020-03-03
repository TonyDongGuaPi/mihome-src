package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.adapter.BasePageIndicatorAdapter;

public class CirclePageIndicator extends View implements PageIndicator {
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId = -1;
    private boolean mCentered;
    private int mCurrentPage;
    private boolean mIsDragging;
    private float mLastMotionX = -1.0f;
    private ViewPager.OnPageChangeListener mListener;
    private int mOrientation;
    private float mPageOffset;
    private final Paint mPaintFill = new Paint(1);
    private final Paint mPaintPageFill = new Paint(1);
    private final Paint mPaintStroke = new Paint(1);
    private float mRadius;
    private int mRealCount;
    private boolean mRighted;
    private int mScrollState;
    private boolean mSnap;
    private int mSnapPage;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public CirclePageIndicator(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.mishopsdk_vpiCirclePageIndicatorStyle);
        init(context, attributeSet, R.attr.mishopsdk_vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(R.color.mishopsdk_default_circle_indicator_page_color);
            int color2 = resources.getColor(R.color.mishopsdk_default_circle_indicator_fill_color);
            int integer = resources.getInteger(R.integer.mishopsdk_default_circle_indicator_orientation);
            int color3 = resources.getColor(R.color.mishopsdk_default_circle_indicator_stroke_color);
            float dimension = resources.getDimension(R.dimen.mishopsdk_default_circle_indicator_stroke_width);
            float dimension2 = resources.getDimension(R.dimen.mishopsdk_default_circle_indicator_radius);
            boolean z = resources.getBoolean(R.bool.mishopsdk_default_circle_indicator_centered);
            boolean z2 = resources.getBoolean(R.bool.mishopsdk_default_circle_indicator_snap);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.mishopsdk_CirclePageIndicator, i, 0);
            this.mCentered = obtainStyledAttributes.getBoolean(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_centered, z);
            this.mOrientation = obtainStyledAttributes.getInt(R.styleable.mishopsdk_CirclePageIndicator_android_orientation, integer);
            this.mPaintPageFill.setStyle(Paint.Style.FILL);
            this.mPaintPageFill.setColor(obtainStyledAttributes.getColor(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_pageColor, color));
            this.mPaintStroke.setStyle(Paint.Style.STROKE);
            this.mPaintStroke.setColor(obtainStyledAttributes.getColor(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_strokeColor, color3));
            this.mPaintStroke.setStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_strokeWidth, dimension));
            this.mPaintFill.setStyle(Paint.Style.FILL);
            this.mPaintFill.setColor(obtainStyledAttributes.getColor(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_fillColor, color2));
            this.mRadius = obtainStyledAttributes.getDimension(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_radius, dimension2);
            this.mSnap = obtainStyledAttributes.getBoolean(R.styleable.mishopsdk_CirclePageIndicator_mishopsdk_snap, z2);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.mishopsdk_CirclePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public void setRealCount(int i) {
        this.mRealCount = i;
    }

    public void setCentered(boolean z) {
        this.mCentered = z;
        invalidate();
    }

    public void setRighted(boolean z) {
        this.mRighted = z;
        invalidate();
    }

    public boolean isCentered() {
        return this.mCentered;
    }

    public void setPageColor(int i) {
        this.mPaintPageFill.setColor(i);
        invalidate();
    }

    public int getPageColor() {
        return this.mPaintPageFill.getColor();
    }

    public void setFillColor(int i) {
        this.mPaintFill.setColor(i);
        invalidate();
    }

    public int getFillColor() {
        return this.mPaintFill.getColor();
    }

    public void setOrientation(int i) {
        switch (i) {
            case 0:
            case 1:
                this.mOrientation = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setStrokeColor(int i) {
        this.mPaintStroke.setColor(i);
        invalidate();
    }

    public int getStrokeColor() {
        return this.mPaintStroke.getColor();
    }

    public void setStrokeWidth(float f) {
        this.mPaintStroke.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.mPaintStroke.getStrokeWidth();
    }

    public void setRadius(float f) {
        this.mRadius = f;
        invalidate();
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setSnap(boolean z) {
        this.mSnap = z;
        invalidate();
    }

    public boolean isSnap() {
        return this.mSnap;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        PagerAdapter adapter;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f;
        float f2;
        super.onDraw(canvas);
        if (this.mViewPager != null && (adapter = this.mViewPager.getAdapter()) != null) {
            if (adapter instanceof BasePageIndicatorAdapter) {
                i = ((BasePageIndicatorAdapter) adapter).getReallyCount();
            } else {
                i = adapter.getCount();
            }
            this.mRealCount = i;
            if (i != 0) {
                if (this.mCurrentPage >= i) {
                    setCurrentItem(i - 1);
                    return;
                }
                if (this.mOrientation == 0) {
                    i5 = getWidth();
                    i4 = getPaddingLeft();
                    i3 = getPaddingRight();
                    i2 = getPaddingTop();
                } else {
                    i5 = getHeight();
                    i4 = getPaddingTop();
                    i3 = getPaddingBottom();
                    i2 = getPaddingLeft();
                }
                double d = (double) this.mRadius;
                Double.isNaN(d);
                float f3 = (float) (d * 3.618d);
                float f4 = ((float) i2) + this.mRadius;
                float f5 = ((float) i4) + this.mRadius;
                if (this.mCentered) {
                    f5 += (((float) ((i5 - i4) - i3)) / 2.0f) - ((((float) i) * f3) / 2.0f);
                }
                if (this.mRighted) {
                    f5 += ((float) ((i5 - i4) - i3)) - (((float) i) * f3);
                }
                float f6 = this.mRadius;
                if (this.mPaintStroke.getStrokeWidth() > 0.0f) {
                    f6 -= this.mPaintStroke.getStrokeWidth() / 2.0f;
                }
                for (int i6 = 0; i6 < i; i6++) {
                    float f7 = (((float) i6) * f3) + f5;
                    if (this.mOrientation == 0) {
                        f2 = f4;
                    } else {
                        f2 = f7;
                        f7 = f4;
                    }
                    if (this.mPaintPageFill.getAlpha() > 0 && i6 != this.mCurrentPage) {
                        canvas.drawCircle(f7, f2, f6, this.mPaintPageFill);
                    }
                }
                float f8 = ((float) (this.mSnap ? this.mSnapPage : this.mCurrentPage)) * f3;
                if (!this.mSnap) {
                    f8 += this.mPageOffset * f3;
                }
                if (this.mOrientation == 0) {
                    f = f8 + f5;
                } else {
                    float f9 = f4;
                    f4 = f8 + f5;
                    f = f9;
                }
                canvas.drawCircle(f, f4, this.mRadius, this.mPaintFill);
                if (f6 != this.mRadius) {
                    canvas.drawCircle(f, f4, this.mRadius, this.mPaintStroke);
                }
            }
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.mViewPager != viewPager) {
            if (this.mViewPager != null) {
                this.mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
            }
            if (viewPager.getAdapter() != null) {
                this.mViewPager = viewPager;
                this.mViewPager.setOnPageChangeListener(this);
                invalidate();
                return;
            }
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(i);
            this.mCurrentPage = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void notifyDataSetChanged() {
        invalidate();
    }

    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mPageOffset = f;
        invalidate();
        if (this.mListener != null) {
            this.mListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.mSnap || this.mScrollState == 0) {
            this.mCurrentPage = i % this.mRealCount;
            this.mSnapPage = i % this.mRealCount;
            invalidate();
        }
        if (this.mListener != null) {
            this.mListener.onPageSelected(i % this.mRealCount);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 0) {
            setMeasuredDimension(measureLong(i), measureShort(i2));
        } else {
            setMeasuredDimension(measureShort(i), measureLong(i2));
        }
    }

    private int measureLong(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.mViewPager == null) {
            return size;
        }
        int count = this.mViewPager.getAdapter().getCount();
        int paddingLeft = (int) (((float) (getPaddingLeft() + getPaddingRight())) + (((float) (count * 2)) * this.mRadius) + (((float) (count - 1)) * this.mRadius) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int measureShort(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((this.mRadius * 2.0f) + ((float) getPaddingTop()) + ((float) getPaddingBottom()) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        this.mSnapPage = savedState.currentPage;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPage = this.mCurrentPage;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPage;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPage = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPage);
        }
    }
}
