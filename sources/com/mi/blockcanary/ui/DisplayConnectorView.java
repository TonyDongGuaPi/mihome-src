package com.mi.blockcanary.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public final class DisplayConnectorView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final Paint f6771a = new Paint(1);
    private static final Paint b = new Paint(1);
    private static final Paint c = new Paint(1);
    private static final Paint d = new Paint(1);
    private Type e = Type.NODE;
    private Bitmap f;

    public enum Type {
        START,
        NODE,
        END
    }

    static {
        f6771a.setColor(-4539718);
        b.setColor(-8083771);
        c.setColor(-5155506);
        d.setColor(0);
        d.setXfermode(BlockCanaryUi.d);
    }

    public DisplayConnectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (!(this.f == null || (this.f.getWidth() == width && this.f.getHeight() == height))) {
            this.f.recycle();
            this.f = null;
        }
        if (this.f == null) {
            this.f = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(this.f);
            float f2 = (float) width;
            float f3 = f2 / 2.0f;
            float f4 = (float) height;
            float f5 = f4 / 2.0f;
            float f6 = f2 / 3.0f;
            float a2 = BlockCanaryUi.a(4.0f, getResources());
            f6771a.setStrokeWidth(a2);
            b.setStrokeWidth(a2);
            switch (this.e) {
                case NODE:
                    canvas2.drawLine(f3, 0.0f, f3, f4, f6771a);
                    canvas2.drawCircle(f3, f5, f3, f6771a);
                    canvas2.drawCircle(f3, f5, f6, d);
                    break;
                case START:
                    float f7 = f3 - (a2 / 2.0f);
                    canvas2.drawRect(0.0f, 0.0f, f2, f7, b);
                    canvas2.drawCircle(0.0f, f7, f7, d);
                    canvas2.drawCircle(f2, f7, f7, d);
                    Canvas canvas3 = canvas2;
                    float f8 = f3;
                    float f9 = f3;
                    canvas3.drawLine(f8, 0.0f, f9, f5, b);
                    canvas3.drawLine(f8, f5, f9, f4, f6771a);
                    canvas2.drawCircle(f3, f5, f3, f6771a);
                    canvas2.drawCircle(f3, f5, f6, d);
                    break;
                default:
                    canvas2.drawLine(f3, 0.0f, f3, f5, f6771a);
                    canvas2.drawCircle(f3, f5, f6, c);
                    break;
            }
        }
        canvas.drawBitmap(this.f, 0.0f, 0.0f, (Paint) null);
    }

    public void setType(Type type) {
        if (type != this.e) {
            this.e = type;
            if (this.f != null) {
                this.f.recycle();
                this.f = null;
            }
            invalidate();
        }
    }
}
