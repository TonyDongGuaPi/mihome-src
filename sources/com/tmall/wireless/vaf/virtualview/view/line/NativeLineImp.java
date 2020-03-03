package com.tmall.wireless.vaf.virtualview.view.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;
import com.tmall.wireless.vaf.virtualview.Helper.VirtualViewUtils;
import com.tmall.wireless.vaf.virtualview.core.IView;

public class NativeLineImp extends View implements IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9407a = "NLineImp_TMTEST";
    protected LineBase mBase;
    protected Paint mPaint = new Paint();

    public NativeLineImp(Context context, LineBase lineBase) {
        super(context);
        this.mBase = lineBase;
    }

    public void destroy() {
        this.mBase = null;
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setPaintParam(int i, int i2, int i3) {
        this.mPaint.setStrokeWidth((float) i2);
        this.mPaint.setColor(i);
        this.mPaint.setAntiAlias(true);
        switch (i3) {
            case 1:
                this.mPaint.setStyle(Paint.Style.FILL);
                return;
            case 2:
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setPathEffect(new DashPathEffect(this.mBase.ak, 1.0f));
                setLayerType(1, (Paint) null);
                return;
            default:
                return;
        }
    }

    public void onComMeasure(int i, int i2) {
        onMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        onLayout(z, i, i2, i3, i4);
    }

    public int getComMeasuredWidth() {
        return getMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return getMeasuredHeight();
    }

    public void measureComponent(int i, int i2) {
        measure(i, i2);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.mBase.ae().f9382a;
        int i4 = this.mBase.ae().b;
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                if (i3 != -1) {
                    if (i3 == -2) {
                        size = (int) Math.min((float) size, this.mPaint.getStrokeWidth());
                    }
                }
            } else if (mode != 0) {
                size = 0;
            } else if (i3 != -1) {
                if (i3 == -2) {
                    size = (int) this.mPaint.getStrokeWidth();
                }
            }
            size = i3;
        }
        if (mode2 != 1073741824) {
            if (mode2 == Integer.MIN_VALUE) {
                if (i4 != -1) {
                    if (i4 == -2) {
                        size2 = (int) Math.min((float) size2, this.mPaint.getStrokeWidth());
                    }
                }
            } else if (mode2 != 0) {
                size2 = 0;
            } else if (i4 != -1) {
                if (i4 == -2) {
                    size2 = (int) this.mPaint.getStrokeWidth();
                }
            }
            size2 = i4;
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int comMeasuredHeight = getComMeasuredHeight();
        VirtualViewUtils.b(canvas, this.mBase.n(), measuredWidth, comMeasuredHeight, 0, 0, 0, 0, 0);
        int strokeWidth = (int) this.mPaint.getStrokeWidth();
        int u = this.mBase.u();
        if (this.mBase.b()) {
            float f = (float) ((u & 32) != 0 ? comMeasuredHeight >> 1 : (u & 16) != 0 ? comMeasuredHeight - (strokeWidth >> 1) : strokeWidth >> 1);
            canvas.drawLine((float) this.mBase.aa(), f, (float) (measuredWidth - this.mBase.ac()), f, this.mPaint);
            return;
        }
        float f2 = (float) ((u & 4) != 0 ? measuredWidth >> 1 : (u & 2) != 0 ? measuredWidth - (strokeWidth >> 1) : strokeWidth >> 1);
        canvas.drawLine(f2, (float) this.mBase.ab(), f2, (float) (comMeasuredHeight - this.mBase.ad()), this.mPaint);
    }
}
