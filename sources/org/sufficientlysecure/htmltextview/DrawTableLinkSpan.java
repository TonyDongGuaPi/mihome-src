package org.sufficientlysecure.htmltextview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

public class DrawTableLinkSpan extends ReplacementSpan {
    private static final String d = "";
    private static float e = 80.0f;
    private static int f = -16776961;

    /* renamed from: a  reason: collision with root package name */
    protected String f4183a = "";
    protected float b = e;
    protected int c = f;

    public DrawTableLinkSpan a() {
        DrawTableLinkSpan drawTableLinkSpan = new DrawTableLinkSpan();
        drawTableLinkSpan.a(this.f4183a);
        drawTableLinkSpan.a(this.b);
        drawTableLinkSpan.a(this.c);
        return drawTableLinkSpan;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        int measureText = (int) paint.measureText(this.f4183a, 0, this.f4183a.length());
        this.b = paint.getTextSize();
        return measureText;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f2, int i3, int i4, int i5, Paint paint) {
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(this.c);
        paint2.setAntiAlias(true);
        paint2.setTextSize(this.b);
        canvas.drawText(this.f4183a, f2, (float) i5, paint2);
    }

    public void a(String str) {
        this.f4183a = str;
    }

    public void a(float f2) {
        this.b = f2;
    }

    public void a(int i) {
        this.c = i;
    }

    public String b() {
        return this.f4183a;
    }

    public float c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }
}
