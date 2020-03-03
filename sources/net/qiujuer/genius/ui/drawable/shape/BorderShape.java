package net.qiujuer.genius.ui.drawable.shape;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;

public class BorderShape extends Shape {

    /* renamed from: a  reason: collision with root package name */
    private RectF f3141a;
    private DashPathEffect b;
    private Path c;

    public BorderShape(RectF rectF) {
        this(rectF, 0.0f, 0.0f);
    }

    public BorderShape(RectF rectF, float f, float f2) {
        this.f3141a = null;
        this.b = null;
        this.c = null;
        if (rectF.left != 0.0f || rectF.top != 0.0f || rectF.right != 0.0f || rectF.bottom != 0.0f) {
            this.f3141a = rectF;
            if (f > 0.0f && f2 > 0.0f) {
                this.b = new DashPathEffect(new float[]{f, f2}, 0.0f);
                this.c = new Path();
            }
        }
    }

    public void a(RectF rectF) {
        if (rectF.left == 0.0f && rectF.top == 0.0f && rectF.right == 0.0f && rectF.bottom == 0.0f) {
            this.f3141a = null;
        } else if (this.f3141a == null) {
            this.f3141a = new RectF(rectF);
        } else {
            this.f3141a.set(rectF);
        }
    }

    public RectF b(RectF rectF) {
        if (!(this.f3141a == null || rectF == null)) {
            rectF.set(this.f3141a);
        }
        return rectF;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (this.f3141a != null) {
            float width = getWidth();
            float height = getHeight();
            if (this.b == null) {
                if (this.f3141a.left > 0.0f) {
                    canvas.drawRect(0.0f, 0.0f, this.f3141a.left, height, paint);
                }
                if (this.f3141a.top > 0.0f) {
                    canvas.drawRect(0.0f, 0.0f, width, this.f3141a.top, paint);
                }
                if (this.f3141a.right > 0.0f) {
                    canvas.drawRect(width - this.f3141a.right, 0.0f, width, height, paint);
                }
                if (this.f3141a.bottom > 0.0f) {
                    canvas.drawRect(0.0f, height - this.f3141a.bottom, width, height, paint);
                    return;
                }
                return;
            }
            if (paint.getPathEffect() != this.b) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setPathEffect(this.b);
            }
            if (this.f3141a.left > 0.0f) {
                paint.setStrokeWidth(this.f3141a.left);
                a(this.f3141a.left / 2.0f, 0.0f, this.f3141a.left / 2.0f, height);
                canvas.drawPath(this.c, paint);
            }
            if (this.f3141a.top > 0.0f) {
                paint.setStrokeWidth(this.f3141a.top);
                a(0.0f, this.f3141a.top / 2.0f, width, this.f3141a.top / 2.0f);
                canvas.drawPath(this.c, paint);
            }
            if (this.f3141a.right > 0.0f) {
                paint.setStrokeWidth(this.f3141a.right);
                a(width - (this.f3141a.right / 2.0f), 0.0f, width - (this.f3141a.right / 2.0f), height);
                canvas.drawPath(this.c, paint);
            }
            if (this.f3141a.bottom > 0.0f) {
                paint.setStrokeWidth(this.f3141a.bottom);
                a(0.0f, height - (this.f3141a.bottom / 2.0f), width, height - (this.f3141a.bottom / 2.0f));
                canvas.drawPath(this.c, paint);
            }
        }
    }

    private void a(float f, float f2, float f3, float f4) {
        this.c.reset();
        this.c.moveTo(f, f2);
        this.c.lineTo(f3, f4);
    }
}
