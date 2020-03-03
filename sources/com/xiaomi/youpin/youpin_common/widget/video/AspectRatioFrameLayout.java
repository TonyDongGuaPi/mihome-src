package com.xiaomi.youpin.youpin_common.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public final class AspectRatioFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final float f23825a = 0.01f;
    private float b;
    private int c;

    public AspectRatioFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
    }

    public void setAspectRatio(float f) {
        if (this.b != f) {
            this.b = f;
            requestLayout();
        }
    }

    public float getAspectRatio() {
        return this.b;
    }

    public void setResizeMode(int i) {
        if (this.c != i) {
            this.c = i;
            requestLayout();
        }
    }

    public int getResizeMode() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b != 0.0f) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = (float) measuredWidth;
            float f2 = (float) measuredHeight;
            float f3 = (this.b / (f / f2)) - 1.0f;
            if (Math.abs(f3) > f23825a) {
                switch (this.c) {
                    case 1:
                        measuredHeight = (int) (f / this.b);
                        break;
                    case 2:
                        measuredWidth = (int) (f2 * this.b);
                        break;
                    case 3:
                        break;
                    case 4:
                        int i3 = (int) (this.b * f2);
                        if (i3 >= measuredWidth) {
                            measuredWidth = i3;
                            break;
                        } else {
                            float f4 = (float) i3;
                            float f5 = f / f4;
                            measuredWidth = (int) (f4 * f5);
                            measuredHeight = (int) (f2 * f5);
                            break;
                        }
                    default:
                        if (f3 <= 0.0f) {
                            measuredWidth = (int) (f2 * this.b);
                            break;
                        } else {
                            measuredHeight = (int) (f / this.b);
                            break;
                        }
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }
}
