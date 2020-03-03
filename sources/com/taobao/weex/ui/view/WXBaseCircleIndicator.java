package com.taobao.weex.ui.view;

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
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXBaseCircleIndicator extends FrameLayout implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float circlePadding;
    private int fillColor;
    private WXCircleViewPager mCircleViewPager;
    private ViewPager.OnPageChangeListener mListener;
    private final Paint mPaintFill;
    private final Paint mPaintPage = new Paint();
    private int pageColor;
    private float radius;
    private int realCurrentItem;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5754272034454945947L, "com/taobao/weex/ui/view/WXBaseCircleIndicator", 62);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ int access$002(WXBaseCircleIndicator wXBaseCircleIndicator, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBaseCircleIndicator.realCurrentItem = i;
        $jacocoInit[60] = true;
        return i;
    }

    static /* synthetic */ WXCircleViewPager access$100(WXBaseCircleIndicator wXBaseCircleIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        WXCircleViewPager wXCircleViewPager = wXBaseCircleIndicator.mCircleViewPager;
        $jacocoInit[61] = true;
        return wXCircleViewPager;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXBaseCircleIndicator(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mPaintFill = new Paint();
        this.pageColor = Color.d;
        this.fillColor = Color.b;
        $jacocoInit[2] = true;
        init();
        $jacocoInit[3] = true;
    }

    private void init() {
        boolean[] $jacocoInit = $jacocoInit();
        this.radius = (float) WXViewUtils.dip2px(5.0f);
        $jacocoInit[4] = true;
        this.circlePadding = (float) WXViewUtils.dip2px(5.0f);
        this.pageColor = Color.d;
        this.fillColor = Color.b;
        $jacocoInit[5] = true;
        this.mPaintFill.setStyle(Paint.Style.FILL);
        $jacocoInit[6] = true;
        this.mPaintFill.setAntiAlias(true);
        $jacocoInit[7] = true;
        this.mPaintPage.setAntiAlias(true);
        $jacocoInit[8] = true;
        this.mPaintPage.setColor(this.pageColor);
        $jacocoInit[9] = true;
        this.mPaintFill.setStyle(Paint.Style.FILL);
        $jacocoInit[10] = true;
        this.mPaintFill.setColor(this.fillColor);
        $jacocoInit[11] = true;
        setWillNotDraw(false);
        $jacocoInit[12] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXBaseCircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[13] = true;
        $jacocoInit[14] = true;
        this.mPaintFill = new Paint();
        this.pageColor = Color.d;
        this.fillColor = Color.b;
        $jacocoInit[15] = true;
        init();
        $jacocoInit[16] = true;
    }

    public void setCircleViewPager(WXCircleViewPager wXCircleViewPager) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCircleViewPager = wXCircleViewPager;
        if (this.mCircleViewPager == null) {
            $jacocoInit[17] = true;
        } else {
            if (this.mListener != null) {
                $jacocoInit[18] = true;
            } else {
                $jacocoInit[19] = true;
                this.mListener = new ViewPager.SimpleOnPageChangeListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBaseCircleIndicator this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-2661829649747543339L, "com/taobao/weex/ui/view/WXBaseCircleIndicator$1", 3);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void onPageSelected(int i) {
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBaseCircleIndicator.access$002(this.this$0, WXBaseCircleIndicator.access$100(this.this$0).getRealCurrentItem());
                        $jacocoInit[1] = true;
                        this.this$0.invalidate();
                        $jacocoInit[2] = true;
                    }
                };
                $jacocoInit[20] = true;
            }
            this.mCircleViewPager.addOnPageChangeListener(this.mListener);
            $jacocoInit[21] = true;
            this.realCurrentItem = this.mCircleViewPager.getRealCurrentItem();
            if (this.realCurrentItem >= 0) {
                $jacocoInit[22] = true;
            } else {
                this.realCurrentItem = 0;
                $jacocoInit[23] = true;
            }
        }
        requestLayout();
        $jacocoInit[24] = true;
    }

    public void setRadius(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.radius = f;
        $jacocoInit[25] = true;
    }

    public void setFillColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.fillColor = i;
        $jacocoInit[26] = true;
        this.mPaintFill.setColor(i);
        $jacocoInit[27] = true;
    }

    public void setPageColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.pageColor = i;
        $jacocoInit[28] = true;
        this.mPaintPage.setColor(i);
        $jacocoInit[29] = true;
    }

    public int getRealCurrentItem() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.realCurrentItem;
        $jacocoInit[30] = true;
        return i;
    }

    public void setRealCurrentItem(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.realCurrentItem = i;
        $jacocoInit[31] = true;
        invalidate();
        $jacocoInit[32] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[33] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[34] = true;
        return wXGesture;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            dispatchTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
        return dispatchTouchEvent;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDraw(canvas);
        float f = (this.circlePadding + this.radius) * 2.0f;
        $jacocoInit[39] = true;
        float width = ((float) (getWidth() / 2)) - ((((float) (getCount() - 1)) * f) / 2.0f);
        $jacocoInit[40] = true;
        float height = (float) ((getHeight() / 2) + getPaddingTop());
        $jacocoInit[41] = true;
        $jacocoInit[42] = true;
        int i = 0;
        while (i < getCount()) {
            float f2 = (((float) i) * f) + width;
            if (i != this.realCurrentItem) {
                $jacocoInit[43] = true;
                canvas.drawCircle(f2, height, this.radius, this.mPaintPage);
                $jacocoInit[44] = true;
            } else {
                canvas.drawCircle(f2, height, this.radius, this.mPaintFill);
                $jacocoInit[45] = true;
            }
            i++;
            $jacocoInit[46] = true;
        }
        $jacocoInit[47] = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        int mode = View.MeasureSpec.getMode(i);
        $jacocoInit[48] = true;
        int size = View.MeasureSpec.getSize(i);
        $jacocoInit[49] = true;
        int mode2 = View.MeasureSpec.getMode(i2);
        $jacocoInit[50] = true;
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            $jacocoInit[51] = true;
        } else {
            size = ((int) (((float) getPaddingLeft()) + (this.radius * 2.0f * ((float) getCount())) + (this.circlePadding * ((float) (getCount() - 1))) + ((float) getPaddingRight()))) + 1;
            $jacocoInit[52] = true;
        }
        if (mode2 == 1073741824) {
            $jacocoInit[53] = true;
        } else {
            size2 = ((int) (((float) getPaddingTop()) + (this.radius * 2.0f) + ((float) getPaddingBottom()))) + 1;
            $jacocoInit[54] = true;
        }
        setMeasuredDimension(size, size2);
        $jacocoInit[55] = true;
    }

    public int getCount() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCircleViewPager == null) {
            $jacocoInit[56] = true;
        } else if (this.mCircleViewPager.getAdapter() == null) {
            $jacocoInit[57] = true;
        } else {
            int realCount = this.mCircleViewPager.getRealCount();
            $jacocoInit[59] = true;
            return realCount;
        }
        $jacocoInit[58] = true;
        return 0;
    }
}
