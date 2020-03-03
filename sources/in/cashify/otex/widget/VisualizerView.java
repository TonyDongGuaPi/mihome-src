package in.cashify.otex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;
import in.cashify.otex.R;

public class VisualizerView extends SurfaceView {

    /* renamed from: a  reason: collision with root package name */
    public int f2618a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public final Paint g = new Paint();
    public final Paint h = new Paint();
    public final Paint i = new Paint();
    public float j;
    public float k;

    public enum a {
        BAR(1),
        PIXEL(2),
        FADE(4);
        

        /* renamed from: a  reason: collision with root package name */
        public final int f2619a;

        /* access modifiers changed from: public */
        a(int i) {
            this.f2619a = i;
        }

        public int a() {
            return this.f2619a;
        }
    }

    public VisualizerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        this.g.setColor(this.b);
        this.g.setStrokeWidth(1.0f);
        this.h.setColor(this.c);
        this.i.setColor(Color.argb(138, 255, 255, 255));
    }

    public final float a(int i2) {
        int i3;
        double random = Math.random();
        double d2 = (double) i2;
        Double.isNaN(d2);
        double d3 = (random * d2) + 1.0d;
        float height = (float) getHeight();
        int i4 = this.e;
        if (i4 == 0) {
            i3 = this.f;
        } else if (i4 != 1) {
            if (i4 == 2) {
                i3 = getHeight();
            }
            return (height / 100.0f) * ((float) d3);
        } else {
            i3 = getHeight() - this.f;
        }
        height = (float) i3;
        return (height / 100.0f) * ((float) d3);
    }

    public final RectF a(float f2, float f3, float f4) {
        int i2 = this.e;
        if (i2 == 0) {
            float f5 = (float) this.f;
            return new RectF(f2, f5 - f4, f3, f5);
        } else if (i2 == 1) {
            float f6 = (float) this.f;
            return new RectF(f2, f6, f3, f4 + f6);
        } else if (i2 != 2) {
            float f7 = (float) this.f;
            return new RectF(f2, f7 - f4, f3, f7);
        } else {
            float f8 = (float) this.f;
            return new RectF(f2, f8 - f4, f3, f8 + f4);
        }
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VisualizerView);
        this.f2618a = obtainStyledAttributes.getInteger(R.styleable.VisualizerView_numColumns, 20);
        this.b = obtainStyledAttributes.getColor(R.styleable.VisualizerView_renderColor, -1);
        this.d = obtainStyledAttributes.getInt(R.styleable.VisualizerView_renderType, a.BAR.a());
        this.e = obtainStyledAttributes.getInteger(R.styleable.VisualizerView_renderRange, 2);
        this.c = obtainStyledAttributes.getColor(R.styleable.VisualizerView_backColor, -1);
        obtainStyledAttributes.recycle();
    }

    public final void a(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.h);
    }

    public final void a(Canvas canvas, int i2) {
        int i3 = this.f2618a / 3;
        int i4 = i3 * 2;
        while (i3 <= i4) {
            float a2 = a(i2);
            float f2 = this.j;
            float f3 = this.k;
            i3++;
            canvas.drawRect(a((((float) i3) * f2) + f3, (((float) i3) * f2) - f3, a2), this.g);
        }
    }

    public final void a(Canvas canvas, int i2, int i3) {
        if (canvas != null) {
            if ((a.BAR.a() & i3) != 0) {
                a(canvas, i2);
            }
            if ((i3 & a.PIXEL.a()) != 0) {
                b(canvas, i2);
            }
        }
    }

    public synchronized void b(int i2) {
        if (this.f2618a > getWidth()) {
            this.f2618a = 20;
        }
        this.j = ((float) getWidth()) / ((float) this.f2618a);
        this.k = this.j / 8.0f;
        if (this.f == 0) {
            this.f = getHeight() / 2;
        }
        Canvas lockCanvas = getHolder().lockCanvas();
        if (lockCanvas != null) {
            a(lockCanvas);
            if (i2 <= 0) {
                b(lockCanvas);
            }
            a(lockCanvas, i2, this.d);
            getHolder().unlockCanvasAndPost(lockCanvas);
        }
    }

    public final void b(Canvas canvas) {
        float height = (float) (getHeight() >> 1);
        canvas.drawLine(0.0f, height, (float) getWidth(), height, this.g);
    }

    public final void b(Canvas canvas, int i2) {
        RectF rectF;
        int i3 = this.f2618a / 3;
        int i4 = i3 * 2;
        while (i3 <= i4) {
            float a2 = a(i2);
            float f2 = this.j;
            float f3 = this.k;
            float f4 = (((float) i3) * f2) + f3;
            i3++;
            float f5 = (((float) i3) * f2) - f3;
            int i5 = (int) (a2 / (f5 - f4));
            if (i5 == 0) {
                i5 = 1;
            }
            float f6 = a2 / ((float) i5);
            int i6 = 0;
            while (true) {
                if (i6 < i5) {
                    int i7 = this.e;
                    if (i7 == 0) {
                        float f7 = ((float) this.f) - (((float) i6) * f6);
                        rectF = new RectF(f4, (f7 - f6) + this.k, f5, f7);
                    } else if (i7 == 1) {
                        float f8 = ((float) this.f) + (((float) i6) * f6);
                        rectF = new RectF(f4, f8, f5, (f8 + f6) - this.k);
                    } else if (i7 == 2) {
                        float f9 = (((float) this.f) - (a2 / 2.0f)) + (((float) i6) * f6);
                        rectF = new RectF(f4, (f9 - f6) + this.k, f5, f9);
                    } else {
                        return;
                    }
                    canvas.drawRect(rectF, this.g);
                    i6++;
                }
            }
        }
    }

    public void setBaseY(int i2) {
        this.f = i2;
    }
}
