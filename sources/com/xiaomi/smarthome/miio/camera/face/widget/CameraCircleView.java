package com.xiaomi.smarthome.miio.camera.face.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.libra.Color;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;

public class CameraCircleView extends View {
    public static final String TAG = "CameraCircleView";
    private Paint circlePaint;
    public float circleR;
    public float circleX;
    public float circleY;
    Rect destRect;
    private Bitmap four_corners;
    private Paint mBitPaint;
    private int mHeight;
    private int mWidth;
    public float offset;
    private Paint rectPaint;
    Rect srcRect;
    public boolean taked;
    private Paint textPaint;
    private Rect textRect;

    public CameraCircleView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public CameraCircleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CameraCircleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectPaint = new Paint();
        this.circlePaint = new Paint();
        this.textPaint = new Paint();
        this.mBitPaint = new Paint(1);
        this.taked = false;
        setLayerType(1, (Paint) null);
        this.circlePaint.setColor(Color.c);
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.rectPaint.setColor(-1);
        this.rectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        this.rectPaint.setAntiAlias(true);
        this.textPaint.setColor(-16777216);
        this.textPaint.setTextSize((float) DisplayUtils.a(context, 14.0f));
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.mBitPaint.setFilterBitmap(true);
        this.mBitPaint.setDither(true);
        this.four_corners = BitmapFactory.decodeResource(context.getResources(), R.drawable.mask_face_four_corners);
        this.srcRect = new Rect(0, 0, this.four_corners.getWidth(), this.four_corners.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
        this.circleX = (float) (this.mWidth / 2);
        if (this.mHeight > this.mWidth) {
            this.offset = (float) (this.mWidth / 5);
            this.circleR = ((float) this.mWidth) / 2.5f;
            this.circleY = this.circleX + this.offset;
        } else {
            this.circleR = ((float) this.mHeight) / 2.5f;
            this.circleY = (float) (this.mHeight / 2);
        }
        String str = TAG;
        SDKLog.b(str, this.mWidth + "-" + this.mHeight);
        String str2 = TAG;
        SDKLog.b(str2, "circleX=" + this.circleX + " circleY" + this.circleY + " circleR" + this.circleR);
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("offset");
        sb.append(this.offset);
        SDKLog.b(str3, sb.toString());
        int i5 = (int) (((float) this.mWidth) + this.offset + 1.0f);
        this.textRect = new Rect(0, i5, this.mWidth, i5 + 60);
        double d = (double) this.circleR;
        Double.isNaN(d);
        double d2 = d * 0.6d;
        String str4 = TAG;
        SDKLog.b(str4, "anchor=" + d2);
        double d3 = (double) this.circleX;
        Double.isNaN(d3);
        int i6 = (int) (d3 - d2);
        double d4 = (double) this.circleY;
        Double.isNaN(d4);
        int i7 = (int) (d4 - d2);
        double d5 = (double) this.circleX;
        Double.isNaN(d5);
        int i8 = (int) (d5 + d2);
        double d6 = (double) this.circleY;
        Double.isNaN(d6);
        String str5 = TAG;
        SDKLog.b(str5, i6 + "-" + i7 + "-" + i8);
        this.destRect = new Rect(i6, i7, i8, (int) (d2 + d6));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        SDKLog.b(TAG, "onDraw...");
        canvas.drawCircle(this.circleX, this.circleY, this.circleR, this.circlePaint);
        canvas.drawRect(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, this.rectPaint);
        if (!this.taked) {
            if (!(this.four_corners == null || this.destRect == null)) {
                canvas.drawBitmap(this.four_corners, this.srcRect, this.destRect, this.mBitPaint);
            }
            Paint.FontMetrics fontMetrics = this.textPaint.getFontMetrics();
            canvas.drawText(getContext().getString(R.string.camera_take_photo_tips), (float) this.textRect.centerX(), (float) ((int) ((((float) this.textRect.centerY()) - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 2.0f))), this.textPaint);
        }
    }
}
