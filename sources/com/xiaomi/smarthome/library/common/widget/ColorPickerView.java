package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.view.View;
import com.libra.Color;

public class ColorPickerView extends View {
    private static final int f = 100;
    private static final int g = 100;
    private static final int h = 32;
    private static final float i = 3.1415925f;

    /* renamed from: a  reason: collision with root package name */
    private Paint f18787a;
    private Paint b;
    private final int[] c = {-65536, Color.k, Color.h, Color.j, Color.g, -256, -65536};
    private boolean d;
    private boolean e;

    private int a(int i2) {
        if (i2 < 0) {
            return 0;
        }
        if (i2 > 255) {
            return 255;
        }
        return i2;
    }

    public ColorPickerView(Context context, int i2) {
        super(context);
        SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, this.c, (float[]) null);
        this.f18787a = new Paint(1);
        this.f18787a.setShader(sweepGradient);
        this.f18787a.setStyle(Paint.Style.STROKE);
        this.f18787a.setStrokeWidth(32.0f);
        this.b = new Paint(1);
        this.b.setColor(i2);
        this.b.setStrokeWidth(5.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float strokeWidth = 100.0f - (this.f18787a.getStrokeWidth() * 0.5f);
        canvas.translate(100.0f, 100.0f);
        float f2 = -strokeWidth;
        canvas.drawOval(new RectF(f2, f2, strokeWidth, strokeWidth), this.f18787a);
        canvas.drawCircle(0.0f, 0.0f, 32.0f, this.b);
        if (this.d) {
            int color = this.b.getColor();
            this.b.setStyle(Paint.Style.STROKE);
            if (this.e) {
                this.b.setAlpha(255);
            } else {
                this.b.setAlpha(128);
            }
            canvas.drawCircle(0.0f, 0.0f, this.b.getStrokeWidth() + 32.0f, this.b);
            this.b.setStyle(Paint.Style.FILL);
            this.b.setColor(color);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(200, 200);
    }

    private int a(float f2) {
        return Math.round(f2);
    }

    private int a(int i2, int i3, float f2) {
        return i2 + Math.round(f2 * ((float) (i3 - i2)));
    }

    private int a(int[] iArr, float f2) {
        if (f2 <= 0.0f) {
            return iArr[0];
        }
        if (f2 >= 1.0f) {
            return iArr[iArr.length - 1];
        }
        float length = f2 * ((float) (iArr.length - 1));
        int i2 = (int) length;
        float f3 = length - ((float) i2);
        int i3 = iArr[i2];
        int i4 = iArr[i2 + 1];
        return android.graphics.Color.argb(a(android.graphics.Color.alpha(i3), android.graphics.Color.alpha(i4), f3), a(android.graphics.Color.red(i3), android.graphics.Color.red(i4), f3), a(android.graphics.Color.green(i3), android.graphics.Color.green(i4), f3), a(android.graphics.Color.blue(i3), android.graphics.Color.blue(i4), f3));
    }

    private int a(int i2, float f2) {
        int red = android.graphics.Color.red(i2);
        int green = android.graphics.Color.green(i2);
        int blue = android.graphics.Color.blue(i2);
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix.setRGB2YUV();
        colorMatrix2.setRotate(0, (f2 * 180.0f) / 3.1415927f);
        colorMatrix.postConcat(colorMatrix2);
        colorMatrix2.setYUV2RGB();
        colorMatrix.postConcat(colorMatrix2);
        float[] array = colorMatrix.getArray();
        float f3 = (float) red;
        float f4 = (float) green;
        float f5 = (float) blue;
        return android.graphics.Color.argb(android.graphics.Color.alpha(i2), a(a((array[0] * f3) + (array[1] * f4) + (array[2] * f5))), a(a((array[5] * f3) + (array[6] * f4) + (array[7] * f5))), a(a((array[10] * f3) + (array[11] * f4) + (array[12] * f5))));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            r1 = 1120403456(0x42c80000, float:100.0)
            float r0 = r0 - r1
            float r2 = r8.getY()
            float r2 = r2 - r1
            float r1 = r0 * r0
            float r3 = r2 * r2
            float r1 = r1 + r3
            double r3 = (double) r1
            double r3 = java.lang.Math.sqrt(r3)
            r5 = 4629700416936869888(0x4040000000000000, double:32.0)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r1 > 0) goto L_0x0020
            r1 = 1
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            int r8 = r8.getAction()
            switch(r8) {
                case 0: goto L_0x0033;
                case 1: goto L_0x0029;
                case 2: goto L_0x003d;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x006c
        L_0x0029:
            boolean r8 = r7.d
            if (r8 == 0) goto L_0x006c
            r7.d = r3
            r7.invalidate()
            goto L_0x006c
        L_0x0033:
            r7.d = r1
            if (r1 == 0) goto L_0x003d
            r7.e = r4
            r7.invalidate()
            goto L_0x006c
        L_0x003d:
            boolean r8 = r7.d
            if (r8 == 0) goto L_0x004b
            boolean r8 = r7.e
            if (r8 == r1) goto L_0x006c
            r7.e = r1
            r7.invalidate()
            goto L_0x006c
        L_0x004b:
            double r1 = (double) r2
            double r5 = (double) r0
            double r0 = java.lang.Math.atan2(r1, r5)
            float r8 = (float) r0
            r0 = 1086918618(0x40c90fda, float:6.283185)
            float r8 = r8 / r0
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x005e
            r0 = 1065353216(0x3f800000, float:1.0)
            float r8 = r8 + r0
        L_0x005e:
            android.graphics.Paint r0 = r7.b
            int[] r1 = r7.c
            int r8 = r7.a((int[]) r1, (float) r8)
            r0.setColor(r8)
            r7.invalidate()
        L_0x006c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.ColorPickerView.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
