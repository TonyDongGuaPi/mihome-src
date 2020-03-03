package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomiyoupin.toast.R;

public class CommonImageProgress extends View {
    private int backgroundHeight;
    private Bitmap destBmp;
    private Bitmap destFillBmp;
    private Paint mPaint;
    private PorterDuffXfermode porterDuffXfermode;
    private int progress;

    public CommonImageProgress(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonImageProgress(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonImageProgress(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.progress = 50;
        this.destBmp = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.ypd_common_progress_landing)).getBitmap();
        this.destFillBmp = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.ypd_common_progress_landing_fill)).getBitmap();
        this.backgroundHeight = (this.destBmp.getHeight() * (100 - this.progress)) / 100;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        setLayerType(1, (Paint) null);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.destBmp.getWidth(), this.destBmp.getHeight());
    }

    public void setProgress(@IntRange(from = 0, to = 100) int i) {
        if (i >= 0 && i <= 100) {
            this.progress = i;
            this.backgroundHeight = (this.destBmp.getHeight() * (100 - i)) / 100;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.destBmp, 0.0f, 0.0f, this.mPaint);
        canvas.drawRect(0.0f, (float) this.backgroundHeight, (float) this.destBmp.getWidth(), (float) this.destBmp.getHeight(), this.mPaint);
        this.mPaint.setXfermode(this.porterDuffXfermode);
        canvas.drawBitmap(this.destFillBmp, 0.0f, 0.0f, this.mPaint);
        this.mPaint.setXfermode((Xfermode) null);
    }
}
