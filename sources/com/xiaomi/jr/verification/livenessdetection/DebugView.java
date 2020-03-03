package com.xiaomi.jr.verification.livenessdetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.libra.Color;

public class DebugView extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f11049a;
    private Paint b;
    private Paint c;
    private Paint d;
    private String e;
    private Rect f;
    private Rect g;
    private String h;

    public DebugView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.f11049a = new Paint();
        this.f11049a.setTextSize(60.0f);
        this.f11049a.setColor(Color.b);
        this.b = new Paint();
        this.b.setTextSize(40.0f);
        this.b.setColor(Color.g);
        this.c = new Paint();
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setColor(Color.h);
        this.c.setStrokeWidth(2.0f);
        this.d = new Paint();
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setColor(Color.g);
        this.d.setStrokeWidth(1.0f);
    }

    public void updateProvider(String str) {
        this.e = str;
        invalidate();
    }

    public void updateFaceAllowRect(Rect rect) {
        this.f = rect;
        invalidate();
    }

    public void updateFaceRect(Rect rect) {
        this.g = rect;
        invalidate();
    }

    public void updateQuality(String str) {
        this.h = str;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.e != null) {
            canvas.drawText(this.e, 50.0f, 120.0f, this.f11049a);
        }
        if (this.f != null) {
            canvas.drawRect((float) this.f.left, (float) this.f.top, (float) this.f.right, (float) this.f.bottom, this.c);
        }
        if (this.g != null) {
            canvas.drawRect((float) this.g.left, (float) this.g.top, (float) this.g.right, (float) this.g.bottom, this.d);
        }
        if (this.h != null) {
            canvas.drawText(this.h, 200.0f, 600.0f, this.b);
        }
        super.onDraw(canvas);
    }
}
