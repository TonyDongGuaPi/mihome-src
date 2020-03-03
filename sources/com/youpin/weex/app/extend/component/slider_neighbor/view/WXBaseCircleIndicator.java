package com.youpin.weex.app.extend.component.slider_neighbor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.libra.Color;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXViewUtils;

public class WXBaseCircleIndicator extends FrameLayout implements WXGestureObservable {
    private float circlePadding;
    private int fillColor = Color.b;
    /* access modifiers changed from: private */
    public WXCircleViewPager mCircleViewPager;
    private ViewPager.OnPageChangeListener mListener;
    private final Paint mPaintFill = new Paint();
    private final Paint mPaintPage = new Paint();
    private int pageColor = Color.d;
    private float radius;
    /* access modifiers changed from: private */
    public int realCurrentItem;
    private WXGesture wxGesture;

    public WXGesture getGestureListener() {
        return null;
    }

    public WXBaseCircleIndicator(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.radius = (float) WXViewUtils.dip2px(5.0f);
        this.circlePadding = (float) WXViewUtils.dip2px(5.0f);
        this.pageColor = Color.d;
        this.fillColor = Color.b;
        this.mPaintFill.setStyle(Paint.Style.FILL);
        this.mPaintFill.setAntiAlias(true);
        this.mPaintPage.setAntiAlias(true);
        this.mPaintPage.setColor(this.pageColor);
        this.mPaintFill.setStyle(Paint.Style.FILL);
        this.mPaintFill.setColor(this.fillColor);
        setWillNotDraw(false);
    }

    public WXBaseCircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void setCircleViewPager(WXCircleViewPager wXCircleViewPager) {
        this.mCircleViewPager = wXCircleViewPager;
        if (this.mCircleViewPager != null) {
            if (this.mListener == null) {
                this.mListener = new ViewPager.SimpleOnPageChangeListener() {
                    public void onPageSelected(int i) {
                        int unused = WXBaseCircleIndicator.this.realCurrentItem = WXBaseCircleIndicator.this.mCircleViewPager.getRealCurrentItem();
                        WXBaseCircleIndicator.this.invalidate();
                    }
                };
            }
            this.mCircleViewPager.addOnPageChangeListener(this.mListener);
            this.realCurrentItem = this.mCircleViewPager.getRealCurrentItem();
            if (this.realCurrentItem < 0) {
                this.realCurrentItem = 0;
            }
        }
        requestLayout();
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    public void setFillColor(int i) {
        this.fillColor = i;
        this.mPaintFill.setColor(i);
    }

    public void setPageColor(int i) {
        this.pageColor = i;
        this.mPaintPage.setColor(i);
    }

    public int getRealCurrentItem() {
        return this.realCurrentItem;
    }

    public void setRealCurrentItem(int i) {
        this.realCurrentItem = i;
        invalidate();
    }

    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        return this.wxGesture != null ? dispatchTouchEvent | this.wxGesture.onTouch(this, motionEvent) : dispatchTouchEvent;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = (this.circlePadding + this.radius) * 2.0f;
        float width = ((float) (getWidth() / 2)) - ((((float) (getCount() - 1)) * f) / 2.0f);
        float height = (float) ((getHeight() / 2) + getPaddingTop());
        for (int i = 0; i < getCount(); i++) {
            float f2 = (((float) i) * f) + width;
            if (i != this.realCurrentItem) {
                canvas.drawCircle(f2, height, this.radius, this.mPaintPage);
            } else {
                canvas.drawCircle(f2, height, this.radius, this.mPaintFill);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = ((int) (((float) getPaddingLeft()) + (this.radius * 2.0f * ((float) getCount())) + (this.circlePadding * ((float) (getCount() - 1))) + ((float) getPaddingRight()))) + 1;
        }
        if (mode2 != 1073741824) {
            size2 = ((int) (((float) getPaddingTop()) + (this.radius * 2.0f) + ((float) getPaddingBottom()))) + 1;
        }
        setMeasuredDimension(size, size2);
    }

    public int getCount() {
        if (this.mCircleViewPager == null || this.mCircleViewPager.getAdapter() == null) {
            return 0;
        }
        return this.mCircleViewPager.getRealCount();
    }
}
