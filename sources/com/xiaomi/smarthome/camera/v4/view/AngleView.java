package com.xiaomi.smarthome.camera.v4.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class AngleView extends ImageView {
    private static final String TAG = "AngleView";
    public float angle = 0.0f;
    private Bitmap bitmapBg;
    private Bitmap bitmapFg;
    private Matrix matrix;
    private Paint paint;
    public float scale = 1.0f;

    public AngleView(Context context) {
        super(context);
        init();
    }

    public AngleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public AngleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public AngleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public void setAngle(float f) {
        this.angle = f;
        invalidate();
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.matrix = new Matrix();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.angle_view_bg);
        this.bitmapBg = Bitmap.createScaledBitmap(decodeResource, (int) (((float) decodeResource.getWidth()) * this.scale), (int) (((float) decodeResource.getHeight()) * this.scale), true);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), R.drawable.angle_view_camera);
        this.bitmapFg = Bitmap.createScaledBitmap(decodeResource2, (int) (((float) decodeResource2.getWidth()) * this.scale), (int) (((float) decodeResource2.getHeight()) * this.scale), true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            int paddingLeft = getPaddingLeft();
            double width = (double) this.bitmapBg.getWidth();
            Double.isNaN(width);
            size = paddingLeft + ((int) (width * 1.5d)) + getPaddingRight();
        }
        if (mode2 != 1073741824) {
            int paddingTop = getPaddingTop();
            double height = (double) this.bitmapBg.getHeight();
            Double.isNaN(height);
            size2 = paddingTop + ((int) (height * 1.5d)) + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.bitmapBg, (float) getPaddingLeft(), (float) getPaddingTop(), this.paint);
        this.matrix.reset();
        this.matrix.postRotate(this.angle);
        Bitmap createBitmap = Bitmap.createBitmap(this.bitmapFg, getPaddingLeft(), getPaddingTop(), this.bitmapFg.getWidth() + getPaddingRight(), this.bitmapFg.getHeight() + getPaddingBottom(), this.matrix, true);
        canvas.drawBitmap(createBitmap, ((float) (this.bitmapBg.getWidth() - createBitmap.getWidth())) / 2.0f, ((float) (this.bitmapBg.getHeight() - createBitmap.getHeight())) / 2.0f, this.paint);
    }
}
