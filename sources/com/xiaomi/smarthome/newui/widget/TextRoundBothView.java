package com.xiaomi.smarthome.newui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint({"AppCompatCustomView"})
public class TextRoundBothView extends TextView {
    private int back;
    private int backSelect;
    private float corner;
    private int edge;
    private int edgeSelect;
    private Paint paint = new Paint();
    private Path path;
    private RectF rectF = new RectF();

    public TextRoundBothView(Context context) {
        super(context);
        init();
    }

    public TextRoundBothView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TextRoundBothView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.paint.setAntiAlias(true);
        setFocusableInTouchMode(false);
    }

    public void setCorner(float f) {
        this.corner = f;
    }

    public void setStrokeWidth(float f) {
        this.paint.setStrokeWidth(f);
    }

    public void setBackground(int i, int i2) {
        this.edge = i;
        this.back = i2;
        invalidate();
    }

    public void setBackgroundSelect(int i, int i2) {
        this.edgeSelect = i;
        this.backSelect = i2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.path == null) {
            this.path = new Path();
        } else {
            this.path.reset();
        }
        float measuredWidth = (float) getMeasuredWidth();
        float measuredHeight = (float) getMeasuredHeight();
        float measuredHeight2 = ((float) (getMeasuredHeight() / 2)) - this.corner;
        this.path.moveTo(this.corner + measuredHeight2, this.corner);
        float f = measuredWidth - measuredHeight2;
        this.path.lineTo(f - this.corner, this.corner);
        this.rectF.left = (measuredWidth - measuredHeight) - this.corner;
        this.rectF.top = this.corner;
        this.rectF.right = measuredWidth - this.corner;
        this.rectF.bottom = measuredHeight - this.corner;
        this.path.arcTo(this.rectF, -90.0f, 180.0f, false);
        this.path.lineTo(f - this.corner, measuredHeight - this.corner);
        this.path.lineTo(this.corner + measuredHeight2, measuredHeight - this.corner);
        this.rectF.left = this.corner;
        this.rectF.top = this.corner;
        this.rectF.right = measuredHeight - this.corner;
        this.rectF.bottom = measuredHeight - this.corner;
        this.path.arcTo(this.rectF, 90.0f, 180.0f, false);
        this.path.lineTo(measuredHeight2 + this.corner, this.corner);
        this.path.close();
        this.paint.setColor(isSelected() ? this.backSelect : this.back);
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(this.path, this.paint);
        this.paint.setColor(isSelected() ? this.edgeSelect : this.edge);
        this.paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(this.path, this.paint);
        super.onDraw(canvas);
    }
}
