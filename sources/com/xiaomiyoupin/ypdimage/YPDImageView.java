package com.xiaomiyoupin.ypdimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;

public class YPDImageView extends AppCompatImageView {
    private int borderColor;
    private float borderWidth;
    private Paint paint;
    private Path path;
    private float[] radii;
    private RectF rectF;
    private RectF rectFBorder;
    /* access modifiers changed from: private */
    public float viewRadius;

    public YPDImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public YPDImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YPDImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.viewRadius = 0.0f;
        this.borderColor = 0;
        this.borderWidth = 0.0f;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setBorderWidth(float f) {
        this.borderWidth = dp2Px(f);
        invalidate();
    }

    public void setRadius(float f) {
        this.viewRadius = dp2Px(f);
        clipOutlineIfNecessary();
        invalidate();
    }

    private float dp2Px(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    public void draw(Canvas canvas) {
        clipCanvasIfNecessary(canvas);
        super.draw(canvas);
        drawBorderIfNecessary(canvas);
    }

    private void clipOutlineIfNecessary() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (this.viewRadius <= 0.0f) {
            setClipToOutline(false);
            return;
        }
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), YPDImageView.this.viewRadius);
            }
        });
    }

    private void clipCanvasIfNecessary(Canvas canvas) {
        if (Build.VERSION.SDK_INT < 21 && this.viewRadius > 0.0f) {
            createCanvasParamsIfNecessary();
            for (int i = 0; i < this.radii.length; i++) {
                this.radii[i] = this.viewRadius;
            }
            this.rectF.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.path.addRoundRect(this.rectF, this.radii, Path.Direction.CW);
            canvas.clipPath(this.path);
            this.path.reset();
        }
    }

    private void createCanvasParamsIfNecessary() {
        if (this.radii == null) {
            this.radii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        }
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        if (this.path == null) {
            this.path = new Path();
        }
    }

    private void drawBorderIfNecessary(Canvas canvas) {
        if (this.borderWidth > 0.0f && this.borderColor != 0) {
            createBorderParamsIfNecessary();
            float f = this.borderWidth;
            float f2 = 0.5f * f;
            this.rectFBorder.set(f2, f2, ((float) getWidth()) - f2, ((float) getHeight()) - f2);
            this.path.addRect(this.rectFBorder, Path.Direction.CW);
            this.paint.setStrokeWidth(f);
            this.paint.setAntiAlias(true);
            this.paint.setColor(this.borderColor);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(this.path, this.paint);
            this.path.reset();
        }
    }

    private void createBorderParamsIfNecessary() {
        if (this.paint == null) {
            this.paint = new Paint(1);
        }
        if (this.rectFBorder == null) {
            this.rectFBorder = new RectF();
        }
        if (this.path == null) {
            this.path = new Path();
        }
    }
}
