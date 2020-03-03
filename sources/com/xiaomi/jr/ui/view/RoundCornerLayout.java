package com.xiaomi.jr.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.xiaomi.jr.ui.R;

public class RoundCornerLayout extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private float f11039a;
    private float b;
    private float c;
    private float d;
    private Paint e;
    private Paint f;

    public RoundCornerLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoundCornerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundCornerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerLayout);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.RoundCornerLayout_radius, 0.0f);
            this.f11039a = obtainStyledAttributes.getDimension(R.styleable.RoundCornerLayout_topLeftRadius, dimension);
            this.b = obtainStyledAttributes.getDimension(R.styleable.RoundCornerLayout_topRightRadius, dimension);
            this.c = obtainStyledAttributes.getDimension(R.styleable.RoundCornerLayout_bottomLeftRadius, dimension);
            this.d = obtainStyledAttributes.getDimension(R.styleable.RoundCornerLayout_bottomRightRadius, dimension);
            obtainStyledAttributes.recycle();
        }
        this.e = new Paint();
        this.e.setColor(-1);
        this.e.setStrokeWidth(1.0f);
        this.e.setAntiAlias(true);
        this.e.setStyle(Paint.Style.FILL_AND_STROKE);
        this.e.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f = new Paint();
        this.f.setXfermode((Xfermode) null);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.f, 31);
        super.dispatchDraw(canvas);
        a(canvas);
        b(canvas);
        c(canvas);
        d(canvas);
        canvas.restore();
    }

    private void a(Canvas canvas) {
        if (this.f11039a > 0.0f) {
            Path path = new Path();
            path.moveTo(0.0f, this.f11039a);
            path.lineTo(0.0f, 0.0f);
            path.lineTo(this.f11039a, 0.0f);
            path.arcTo(new RectF(0.0f, 0.0f, this.f11039a * 2.0f, this.f11039a * 2.0f), -90.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.e);
        }
    }

    private void b(Canvas canvas) {
        if (this.b > 0.0f) {
            int width = getWidth();
            Path path = new Path();
            float f2 = (float) width;
            path.moveTo(f2 - this.b, 0.0f);
            path.lineTo(f2, 0.0f);
            path.lineTo(f2, this.b);
            path.arcTo(new RectF(f2 - (this.b * 2.0f), 0.0f, f2, this.b * 2.0f), 0.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.e);
        }
    }

    private void c(Canvas canvas) {
        if (this.c > 0.0f) {
            int height = getHeight();
            Path path = new Path();
            float f2 = (float) height;
            path.moveTo(0.0f, f2 - this.c);
            path.lineTo(0.0f, f2);
            path.lineTo(this.c, f2);
            path.arcTo(new RectF(0.0f, f2 - (this.c * 2.0f), this.c * 2.0f, f2), 90.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.e);
        }
    }

    private void d(Canvas canvas) {
        if (this.d > 0.0f) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            float f2 = (float) width;
            float f3 = (float) height;
            path.moveTo(f2 - this.d, f3);
            path.lineTo(f2, f3);
            path.lineTo(f2, f3 - this.d);
            path.arcTo(new RectF(f2 - (this.d * 2.0f), f3 - (this.d * 2.0f), f2, f3), 0.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.e);
        }
    }
}
