package com.xiaomi.smarthome.library.common.widget.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;

public class ColorPickerRoundRect extends ColorPicker {
    RectF mPointerRect;

    public ColorPickerRoundRect(Context context) {
        super(context);
    }

    public ColorPickerRoundRect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColorPickerRoundRect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet, int i) {
        super.init(attributeSet, i);
        this.mPointerRect = new RectF();
        this.mDotBmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.yeelight_color_picker_thumb)).getBitmap();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        callSuperOnDraw(canvas);
        canvas.translate(this.mTranslationOffset, this.mTranslationOffset);
        float[] calculatePointerPosition = calculatePointerPosition(this.mAngle);
        canvas.drawCircle(calculatePointerPosition[0], calculatePointerPosition[1], (float) this.mColorPointerHaloRadius, this.mPointerHaloPaint);
        this.mPointerRect.left = calculatePointerPosition[0] - ((float) (this.mColorPointerRadius * 6));
        this.mPointerRect.top = calculatePointerPosition[1] - ((float) this.mColorPointerRadius);
        this.mPointerRect.right = calculatePointerPosition[0] + ((float) (this.mColorPointerRadius * 6));
        this.mPointerRect.bottom = calculatePointerPosition[1] + ((float) this.mColorPointerRadius);
        canvas.save();
        canvas.rotate((float) Math.toDegrees((double) this.mAngle), calculatePointerPosition[0], calculatePointerPosition[1]);
        canvas.drawRoundRect(this.mPointerRect, (float) this.mColorPointerRadius, (float) this.mColorPointerRadius, this.mPointerColor);
        canvas.restore();
        float[] calculateDotPosition = calculateDotPosition(this.mAngle);
        canvas.drawBitmap(this.mDotBmp, (float) ((int) (calculateDotPosition[0] - ((float) (this.mDotBmp.getWidth() / 2)))), (float) ((int) (calculateDotPosition[1] - ((float) (this.mDotBmp.getHeight() / 2)))), this.mBmpPaint);
        canvas.drawCircle(0.0f, 0.0f, (float) this.mColorCenterHaloRadius, this.mCenterHaloPaint);
        if (this.mShowCenterOldColor) {
            canvas.drawArc(this.mCenterRectangle, 90.0f, 180.0f, true, this.mCenterOldPaint);
            canvas.drawArc(this.mCenterRectangle, 270.0f, 180.0f, true, this.mCenterNewPaint);
            return;
        }
        canvas.drawArc(this.mCenterRectangle, 0.0f, 360.0f, true, this.mCenterNewPaint);
    }

    /* access modifiers changed from: protected */
    public float[] calculatePointerPosition(float f) {
        double d = (double) ((this.mColorWheelRadius * 9) / 16);
        double d2 = (double) f;
        double cos = Math.cos(d2);
        Double.isNaN(d);
        double sin = Math.sin(d2);
        Double.isNaN(d);
        return new float[]{(float) (cos * d), (float) (d * sin)};
    }

    /* access modifiers changed from: protected */
    public float[] calculateDotPosition(float f) {
        double d = (double) f;
        Double.isNaN(d);
        double d2 = (double) ((this.mColorWheelRadius * 8) / 16);
        double d3 = (double) ((float) (d + 3.141592653589793d));
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        return new float[]{(float) (cos * d2), (float) (d2 * sin)};
    }
}
