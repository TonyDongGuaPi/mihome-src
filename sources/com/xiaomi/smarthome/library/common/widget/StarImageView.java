package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class StarImageView extends ImageView {
    private float mLevel;
    Paint mPaint = new Paint();
    private Bitmap mStarEmpty;
    private Bitmap mStarFull;
    private Bitmap mStarHalf;

    public StarImageView(Context context) {
        super(context);
    }

    public StarImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setStartBitmap(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        this.mStarFull = bitmap;
        this.mStarHalf = bitmap2;
        this.mStarEmpty = bitmap3;
    }

    public void setLevel(float f) {
        this.mLevel = f;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mStarFull != null) {
            if (this.mLevel <= 0.0f) {
                canvas.drawBitmap(this.mStarEmpty, 0.0f, 0.0f, this.mPaint);
            } else if (((double) this.mLevel) <= 0.5d) {
                canvas.drawBitmap(this.mStarHalf, 0.0f, 0.0f, this.mPaint);
            } else {
                canvas.drawBitmap(this.mStarFull, 0.0f, 0.0f, this.mPaint);
            }
            int width = 0 + this.mStarEmpty.getWidth() + DisplayUtils.a(5.0f);
            if (this.mLevel <= 1.0f) {
                canvas.drawBitmap(this.mStarEmpty, (float) width, 0.0f, this.mPaint);
            } else if (((double) this.mLevel) <= 1.5d) {
                canvas.drawBitmap(this.mStarHalf, (float) width, 0.0f, this.mPaint);
            } else {
                canvas.drawBitmap(this.mStarFull, (float) width, 0.0f, this.mPaint);
            }
            int width2 = width + this.mStarEmpty.getWidth() + DisplayUtils.a(5.0f);
            if (this.mLevel <= 2.0f) {
                canvas.drawBitmap(this.mStarEmpty, (float) width2, 0.0f, this.mPaint);
            } else if (((double) this.mLevel) <= 2.5d) {
                canvas.drawBitmap(this.mStarHalf, (float) width2, 0.0f, this.mPaint);
            } else {
                canvas.drawBitmap(this.mStarFull, (float) width2, 0.0f, this.mPaint);
            }
            int width3 = width2 + this.mStarEmpty.getWidth() + DisplayUtils.a(5.0f);
            if (this.mLevel <= 3.0f) {
                canvas.drawBitmap(this.mStarEmpty, (float) width3, 0.0f, this.mPaint);
            } else if (((double) this.mLevel) <= 3.5d) {
                canvas.drawBitmap(this.mStarHalf, (float) width3, 0.0f, this.mPaint);
            } else {
                canvas.drawBitmap(this.mStarFull, (float) width3, 0.0f, this.mPaint);
            }
            int width4 = width3 + this.mStarEmpty.getWidth() + DisplayUtils.a(5.0f);
            if (this.mLevel <= 4.0f) {
                canvas.drawBitmap(this.mStarEmpty, (float) width4, 0.0f, this.mPaint);
            } else if (((double) this.mLevel) <= 4.5d) {
                canvas.drawBitmap(this.mStarHalf, (float) width4, 0.0f, this.mPaint);
            } else {
                canvas.drawBitmap(this.mStarFull, (float) width4, 0.0f, this.mPaint);
            }
        }
    }
}
