package com.xiaomi.qrcode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.xiaomi.qrcode.camera.CameraManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f12985a = {0, 64, 128, 192, 255, 192, 128, 64};
    private static final long b = 80;
    private static final int c = 160;
    private static final int d = 20;
    private static final int e = 6;
    private CameraManager f;
    private final Paint g = new Paint(1);
    private final Paint h = new Paint(1);
    private Bitmap i;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private int n;
    private List<ResultPoint> o;
    private List<ResultPoint> p;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.j = Color.parseColor("#66000000");
        this.k = resources.getColor(R.color.result_view);
        this.l = Color.parseColor("#FF0099FF");
        this.m = resources.getColor(R.color.possible_result_points);
        this.h.setColor(resources.getColor(R.color.white));
        this.h.setTextSize((float) DisplayUtils.a(context, 14.7f));
        this.n = 0;
        this.o = new ArrayList(5);
        this.p = null;
    }

    public void setCameraManager(CameraManager cameraManager) {
        this.f = cameraManager;
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        if (this.f != null) {
            Rect e2 = this.f.e();
            Rect f2 = this.f.f();
            if (e2 != null && f2 != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.g.setColor(this.i != null ? this.k : this.j);
                float f3 = (float) width;
                canvas.drawRect(0.0f, 0.0f, f3, (float) e2.top, this.g);
                canvas.drawRect(0.0f, (float) e2.top, (float) e2.left, (float) (e2.bottom + 1), this.g);
                Canvas canvas2 = canvas;
                float f4 = f3;
                canvas2.drawRect((float) (e2.right + 1), (float) e2.top, f4, (float) (e2.bottom + 1), this.g);
                canvas2.drawRect(0.0f, (float) (e2.bottom + 1), f4, (float) height, this.g);
                if (this.i != null) {
                    this.g.setAlpha(160);
                    canvas.drawBitmap(this.i, (Rect) null, e2, this.g);
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
        List<ResultPoint> list = this.o;
        synchronized (list) {
            list.add(resultPoint);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }
}
