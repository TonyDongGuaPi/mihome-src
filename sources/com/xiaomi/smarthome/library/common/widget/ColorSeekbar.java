package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.HashMap;

public class ColorSeekbar extends PopSeekbar {
    Bitmap mColorBmp;
    HashMap<Integer, Integer> mColorProgressMap;
    Paint mPreviewCirclePaint;
    int mPreviewCircleRadius;
    Paint mPreviewPaint;

    public ColorSeekbar(Context context) {
        super(context);
        a();
    }

    public ColorSeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.mPreviewPaint = new Paint();
        this.mPreviewPaint.setStyle(Paint.Style.FILL);
        this.mPreviewPaint.setFlags(1);
        this.mPreviewPaint.setColor(-16777216);
        this.mPreviewCirclePaint = new Paint();
        this.mPreviewCirclePaint.setStyle(Paint.Style.STROKE);
        this.mPreviewCirclePaint.setFlags(1);
        this.mPreviewCirclePaint.setColor(Color.parseColor("#dfdfdf"));
        this.mPreviewCirclePaint.setStrokeWidth(1.0f);
        this.mPreviewCircleRadius = DisplayUtils.a(getContext(), 8.0f);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.color_seekbar_bg);
        this.mColorBmp = bitmapDrawable.getBitmap();
        setProgressDrawable(bitmapDrawable);
        this.mImgPopInner.setVisibility(0);
        this.mTxtPopProgress.setVisibility(8);
        this.mShowMax = 100;
        buildColorProgressMap();
    }

    /* access modifiers changed from: protected */
    public void buildColorProgressMap() {
        this.mColorProgressMap = new HashMap<>();
        int showMax = getShowMax();
        if (showMax > 0) {
            for (int i = 0; i < showMax; i++) {
                int calcRealProgress = calcRealProgress(i);
                this.mColorProgressMap.put(Integer.valueOf(getColorByProgress(calcRealProgress)), Integer.valueOf(calcRealProgress));
            }
        }
    }

    public void addColor2Map(int i, double d) {
        HashMap<Integer, Integer> hashMap = this.mColorProgressMap;
        Integer valueOf = Integer.valueOf(i);
        double showMax = (double) getShowMax();
        Double.isNaN(showMax);
        hashMap.put(valueOf, Integer.valueOf(calcRealProgress((int) (d * showMax))));
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            int centerX = this.mThumb.getBounds().centerX();
            this.mPreviewPaint.setColor(getSeekColor());
            float f = (float) centerX;
            float height = (float) (((getHeight() / 2) - DisplayUtils.a(getContext(), 1.0f)) - 1);
            canvas.drawCircle(f, height, (float) this.mPreviewCircleRadius, this.mPreviewPaint);
            canvas.drawCircle(f, height, (float) this.mPreviewCircleRadius, this.mPreviewCirclePaint);
        } catch (Exception unused) {
        }
    }

    public void setShowMax(int i) {
        super.setShowMax(i);
        buildColorProgressMap();
    }

    public void setSeekColor(int i) {
        if (this.mColorProgressMap != null) {
            Integer num = this.mColorProgressMap.get(Integer.valueOf(i));
            if (num != null) {
                setProgress(num.intValue());
                return;
            }
            Integer num2 = null;
            double d = -1.0d;
            for (Integer next : this.mColorProgressMap.keySet()) {
                double colorDistance = colorDistance(next.intValue(), i);
                if (d < 0.0d || colorDistance < d) {
                    num2 = next;
                    d = colorDistance;
                }
            }
            if (num2 != null) {
                setProgress(this.mColorProgressMap.get(num2).intValue());
            }
        }
    }

    public double colorDistance(int i, int i2) {
        double red = (double) ((Color.red(i) + Color.red(i2)) / 2);
        int red2 = Color.red(i) - Color.red(i2);
        Double.isNaN(red);
        Double.isNaN(red);
        double d = (double) red2;
        Double.isNaN(d);
        Double.isNaN(d);
        double d2 = ((red / 256.0d) + 2.0d) * d * d;
        double green = (double) (Color.green(i) - Color.green(i2));
        Double.isNaN(green);
        Double.isNaN(green);
        double blue = (double) (Color.blue(i) - Color.blue(i2));
        Double.isNaN(blue);
        Double.isNaN(blue);
        return Math.sqrt(d2 + (4.0d * green * green) + ((((255.0d - red) / 256.0d) + 2.0d) * blue * blue));
    }

    public int getSeekColor() {
        return getColorByProgress(getProgress());
    }

    /* access modifiers changed from: protected */
    public int getColorByProgress(int i) {
        int width = (i * this.mColorBmp.getWidth()) / getMax();
        int height = this.mColorBmp.getHeight() / 2;
        if (width < this.mColorBmp.getWidth()) {
            return this.mColorBmp.getPixel(width, height);
        }
        return this.mColorBmp.getPixel(this.mColorBmp.getWidth() - 1, height);
    }

    /* access modifiers changed from: package-private */
    public void onProgressRefresh(float f, boolean z) {
        super.onProgressRefresh(f, z);
        ((GradientDrawable) this.mImgPopInner.getDrawable()).setColor(getSeekColor());
    }
}
