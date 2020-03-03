package com.xiaomi.qrcode2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.qrcode2.camera.CameraManager;
import com.xiaomi.zxing.R;
import com.xiaomi.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f13027a = {0, 64, 128, 192, 255, 192, 128, 64};
    private static final long b = 15;
    private static final long c = 2500;
    private static final int d = 160;
    private static final int e = 20;
    private static final int f = 6;
    private CameraManager g;
    private final Paint h = new Paint(1);
    private Bitmap i;
    private Bitmap j;
    private final int k;
    private final int l;
    private final int m;
    private final int n;
    private final int o;
    private int p;
    private int q;
    private int r;
    private List<ResultPoint> s;
    private List<ResultPoint> t;
    private float u = -1.0f;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.k = resources.getColor(R.color.viewfinder_mask);
        this.l = resources.getColor(R.color.result_view);
        this.m = resources.getColor(R.color.viewfinder_laser);
        this.n = resources.getColor(R.color.possible_result_points);
        this.o = resources.getColor(R.color.scan_border_color);
        this.p = resources.getDimensionPixelSize(R.dimen.scan_border_width);
        this.q = resources.getDimensionPixelSize(R.dimen.scan_border_height);
        this.r = 0;
        this.s = new ArrayList(5);
        this.t = null;
    }

    public void setCameraManager(CameraManager cameraManager) {
        this.g = cameraManager;
        this.j = BitmapFactory.decodeResource(getResources(), R.drawable.scan_bar);
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        if (this.g != null) {
            Rect e2 = this.g.e();
            Rect f2 = this.g.f();
            if (e2 != null && f2 != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.h.setColor(this.k);
                float f3 = (float) width;
                canvas.drawRect(0.0f, 0.0f, f3, (float) e2.top, this.h);
                canvas.drawRect(0.0f, (float) e2.top, (float) e2.left, (float) (e2.bottom + 1), this.h);
                Canvas canvas2 = canvas;
                float f4 = f3;
                canvas2.drawRect((float) (e2.right + 1), (float) e2.top, f4, (float) (e2.bottom + 1), this.h);
                canvas2.drawRect(0.0f, (float) (e2.bottom + 1), f4, (float) height, this.h);
                if (this.u == -1.0f) {
                    this.u = (float) e2.top;
                }
                this.u += (((float) (((long) e2.height()) * 15)) * 1.0f) / 2500.0f;
                if (this.u >= ((float) (e2.top + e2.height()))) {
                    this.u = (float) e2.top;
                }
                canvas.drawBitmap(this.j, (float) e2.left, this.u, this.h);
                this.h.setColor(this.o);
                this.h.setStyle(Paint.Style.FILL);
                int i2 = e2.left;
                int i3 = e2.top;
                int width2 = e2.width();
                int height2 = e2.height();
                float f5 = (float) i3;
                float f6 = (float) i2;
                float f7 = f5;
                canvas.drawRect(f6, f7, (float) (this.q + i2), (float) (this.p + i3), this.h);
                canvas.drawRect(f6, f7, (float) (this.p + i2), (float) (this.q + i3), this.h);
                int i4 = height2 + i3;
                float f8 = (float) (i4 + 1);
                float f9 = f8;
                canvas.drawRect(f6, (float) ((i4 - this.q) + 1), (float) (this.p + i2), f9, this.h);
                Canvas canvas3 = canvas;
                canvas3.drawRect(f6, (float) ((i4 - this.p) + 1), (float) (this.q + i2), f9, this.h);
                int i5 = i2 + width2;
                float f10 = f5;
                float f11 = (float) (i5 + 1);
                canvas3.drawRect((float) ((i5 - this.q) + 1), f10, f11, (float) (this.p + i3), this.h);
                canvas3.drawRect((float) ((i5 - this.p) + 1), f10, f11, (float) (i3 + this.q), this.h);
                float f12 = f8;
                canvas3.drawRect((float) ((i5 - this.p) + 1), (float) ((i4 - this.q) + 1), f11, f12, this.h);
                canvas3.drawRect((float) ((i5 - this.q) + 1), (float) ((i4 - this.p) + 1), f11, f12, this.h);
                if (this.i == null) {
                    postInvalidateDelayed(15, e2.left - 6, e2.top - 6, e2.right + 6, e2.bottom + 6);
                }
            }
        }
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.i;
        this.i = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.i = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        List<ResultPoint> list = this.s;
        synchronized (list) {
            list.add(resultPoint);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }
}
