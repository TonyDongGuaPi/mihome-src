package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.lineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;

public class LinearGradientView extends View {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f17611a = new Paint(1);
    private Path b;
    private RectF c;
    private LinearGradient d;
    private float[] e;
    private float[] f = {0.0f, 0.0f};
    private float[] g = {0.0f, 1.0f};
    private int[] h;
    private boolean i = false;
    private float[] j = {0.5f, 0.5f};
    private float k = 45.0f;
    private int[] l = {0, 0};
    private float[] m = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public LinearGradientView(Context context) {
        super(context);
    }

    public void setStartPosition(ReadableArray readableArray) {
        this.f = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        a();
    }

    public void setEndPosition(ReadableArray readableArray) {
        this.g = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        a();
    }

    public void setColors(ReadableArray readableArray) {
        int[] iArr = new int[readableArray.size()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = readableArray.getInt(i2);
        }
        this.h = iArr;
        a();
    }

    public void setLocations(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = (float) readableArray.getDouble(i2);
        }
        this.e = fArr;
        a();
    }

    public void setUseAngle(boolean z) {
        this.i = z;
        a();
    }

    public void setAngleCenter(ReadableArray readableArray) {
        this.j = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        a();
    }

    public void setAngle(float f2) {
        this.k = f2;
        a();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = PixelUtil.toPixelFromDIP((float) readableArray.getDouble(i2));
        }
        this.m = fArr;
        b();
        a();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.l = new int[]{i2, i3};
        b();
        a();
    }

    private float[] a(float f2) {
        float sqrt = (float) Math.sqrt(2.0d);
        double d2 = (double) ((f2 - 90.0f) * 0.017453292f);
        return new float[]{((float) Math.cos(d2)) * sqrt, ((float) Math.sin(d2)) * sqrt};
    }

    private void a() {
        if (this.h == null) {
            return;
        }
        if (this.e == null || this.h.length == this.e.length) {
            float[] fArr = this.f;
            float[] fArr2 = this.g;
            if (this.i && this.j != null) {
                float[] a2 = a(this.k);
                float[] fArr3 = {this.j[0] - (a2[0] / 2.0f), this.j[1] - (a2[1] / 2.0f)};
                fArr2 = new float[]{this.j[0] + (a2[0] / 2.0f), this.j[1] + (a2[1] / 2.0f)};
                fArr = fArr3;
            }
            this.d = new LinearGradient(((float) this.l[0]) * fArr[0], fArr[1] * ((float) this.l[1]), fArr2[0] * ((float) this.l[0]), fArr2[1] * ((float) this.l[1]), this.h, this.e, Shader.TileMode.CLAMP);
            this.f17611a.setShader(this.d);
            invalidate();
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = new Path();
            this.c = new RectF();
        }
        this.b.reset();
        this.c.set(0.0f, 0.0f, (float) this.l[0], (float) this.l[1]);
        this.b.addRoundRect(this.c, this.m, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b == null) {
            canvas.drawPaint(this.f17611a);
        } else {
            canvas.drawPath(this.b, this.f17611a);
        }
    }
}
