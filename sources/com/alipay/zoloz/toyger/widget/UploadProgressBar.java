package com.alipay.zoloz.toyger.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.alipay.zoloz.toyger.R;
import com.libra.Color;
import com.taobao.weex.el.parse.Operators;

public class UploadProgressBar extends View {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    final int ANGLE_STEP;
    private float backColorWidth;
    private int backgroundColor;
    private int endAngle;
    public BitmapShader mBitmapShader;
    private Handler mMainHandle;
    private Matrix mMatrix;
    int mProgressAngle;
    private int mWidth;
    private int max;
    private Paint paint;
    private int progress;
    private int radius;
    private int roundColor;
    private int roundProgressColor;
    private boolean roundShader;
    private float roundWidth;
    private int secondProgressColor;
    private int startAngle;
    private int style;
    private int textColor;
    private boolean textIsDisplayable;
    private float textSize;

    public int getRadius() {
        return this.radius;
    }

    public UploadProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public UploadProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UploadProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.style = 0;
        this.radius = 0;
        this.mProgressAngle = 0;
        this.ANGLE_STEP = 120;
        this.paint = new Paint();
        this.mMainHandle = new Handler(Looper.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.eye_round_progressBar);
        this.roundColor = obtainStyledAttributes.getColor(R.styleable.eye_round_progressBar_eye_round_color, -65536);
        this.roundProgressColor = obtainStyledAttributes.getColor(R.styleable.eye_round_progressBar_eye_round_progress_color, Color.g);
        this.secondProgressColor = obtainStyledAttributes.getColor(R.styleable.eye_round_progressBar_eye_round_progress_color, Color.g);
        this.textColor = obtainStyledAttributes.getColor(R.styleable.eye_round_progressBar_eye_text_color, Color.g);
        this.textSize = obtainStyledAttributes.getDimension(R.styleable.eye_round_progressBar_eye_text_size, 15.0f);
        this.roundWidth = obtainStyledAttributes.getDimension(R.styleable.eye_round_progressBar_eye_round_width, 5.0f);
        this.max = obtainStyledAttributes.getInteger(R.styleable.eye_round_progressBar_eye_max, 100);
        this.textIsDisplayable = obtainStyledAttributes.getBoolean(R.styleable.eye_round_progressBar_eye_text_is_displayable, true);
        this.style = obtainStyledAttributes.getInt(R.styleable.eye_round_progressBar_eye_style, 0);
        this.roundShader = obtainStyledAttributes.getBoolean(R.styleable.eye_round_progressBar_eye_progress_shader, false);
        this.backColorWidth = obtainStyledAttributes.getDimension(R.styleable.eye_round_progressBar_eye_color_bg_width, 0.0f);
        this.startAngle = obtainStyledAttributes.getInt(R.styleable.eye_round_progressBar_eye_start_angle, 0);
        this.endAngle = obtainStyledAttributes.getInt(R.styleable.eye_round_progressBar_eye_end_angle, 360);
        this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.eye_round_progressBar_eye_background_color, -1);
        if (this.backColorWidth > 0.0f && this.roundShader) {
            this.mMatrix = new Matrix();
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.circle_bg);
            this.mBitmapShader = new BitmapShader(decodeResource, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mWidth = (int) this.backColorWidth;
            float min = (((float) this.mWidth) * 1.0f) / ((float) Math.min(decodeResource.getWidth(), decodeResource.getHeight()));
            this.mMatrix.setScale(min, min);
            this.mBitmapShader.setLocalMatrix(this.mMatrix);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        float f = (float) width;
        this.radius = (int) (f - (this.roundWidth / 2.0f));
        this.paint.setColor(this.roundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.roundWidth);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(this.backgroundColor);
        this.paint.setStrokeWidth(0.0f);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        int i = (int) ((((float) this.progress) / ((float) this.max)) * 100.0f);
        Paint paint2 = this.paint;
        float measureText = paint2.measureText(i + Operators.MOD);
        this.paint.setShader((Shader) null);
        if (this.textIsDisplayable && i != 0 && this.style == 0) {
            canvas.drawText(i + Operators.MOD, f - (measureText / 2.0f), f + (this.textSize / 2.0f), this.paint);
        }
        this.paint.setStrokeWidth(this.roundWidth);
        RectF rectF = new RectF((float) (width - this.radius), (float) (width - this.radius), (float) (this.radius + width), (float) (width + this.radius));
        this.paint.setColor(this.roundColor);
        switch (this.style) {
            case 0:
                paintStroke(canvas, rectF);
                return;
            case 1:
                this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (this.progress != 0) {
                    canvas.drawArc(rectF, (float) (this.startAngle + 90), (float) (((this.endAngle - this.startAngle) * this.progress) / this.max), true, this.paint);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void paintStroke(android.graphics.Canvas r16, android.graphics.RectF r17) {
        /*
            r15 = this;
            r0 = r15
            android.graphics.Paint r1 = r0.paint
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.STROKE
            r1.setStyle(r2)
            android.graphics.BitmapShader r1 = r0.mBitmapShader
            if (r1 == 0) goto L_0x0013
            android.graphics.Paint r1 = r0.paint
            android.graphics.BitmapShader r2 = r0.mBitmapShader
            r1.setShader(r2)
        L_0x0013:
            int r1 = r0.startAngle
            r2 = 180(0xb4, float:2.52E-43)
            int r1 = r1 + r2
            int r3 = r0.startAngle
            int r3 = r3 * 2
            int r3 = 180 - r3
            float r6 = (float) r1
            float r7 = (float) r3
            r8 = 0
            android.graphics.Paint r9 = r0.paint
            r4 = r16
            r5 = r17
            r4.drawArc(r5, r6, r7, r8, r9)
            int r4 = r0.mProgressAngle
            int r4 = r4 % 360
            r5 = 0
            r6 = 120(0x78, float:1.68E-43)
            if (r4 >= r1) goto L_0x003b
            int r3 = r4 + 120
            if (r3 <= r1) goto L_0x003e
            int r3 = r3 - r1
            r4 = r1
            r1 = r3
            goto L_0x0048
        L_0x003b:
            int r1 = r1 + r3
            if (r4 <= r1) goto L_0x0040
        L_0x003e:
            r1 = 0
            goto L_0x0048
        L_0x0040:
            int r3 = r4 + 120
            if (r3 >= r1) goto L_0x0047
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0048
        L_0x0047:
            int r1 = r1 - r4
        L_0x0048:
            if (r1 <= 0) goto L_0x005d
            android.graphics.Paint r3 = r0.paint
            int r7 = r0.roundProgressColor
            r3.setColor(r7)
            float r10 = (float) r4
            float r11 = (float) r1
            r12 = 0
            android.graphics.Paint r13 = r0.paint
            r8 = r16
            r9 = r17
            r8.drawArc(r9, r10, r11, r12, r13)
        L_0x005d:
            int r1 = r0.mProgressAngle
            int r1 = r1 % 360
            int r3 = r0.startAngle
            int r4 = r0.startAngle
            int r4 = r4 * 2
            int r4 = 180 - r4
            android.graphics.Paint r7 = r0.paint
            int r8 = r0.roundColor
            r7.setColor(r8)
            float r7 = (float) r3
            float r12 = (float) r4
            r13 = 0
            android.graphics.Paint r14 = r0.paint
            r9 = r16
            r10 = r17
            r11 = r7
            r9.drawArc(r10, r11, r12, r13, r14)
            if (r1 >= r3) goto L_0x0087
            int r4 = r1 + 120
            if (r4 <= r3) goto L_0x0094
            int r5 = r4 - r3
            r1 = r3
            goto L_0x0094
        L_0x0087:
            int r4 = r4 + r3
            if (r1 <= r4) goto L_0x008b
            goto L_0x0094
        L_0x008b:
            int r5 = r1 + 120
            if (r5 >= r4) goto L_0x0092
            r5 = 120(0x78, float:1.68E-43)
            goto L_0x0094
        L_0x0092:
            int r5 = r4 - r1
        L_0x0094:
            if (r5 <= 0) goto L_0x00aa
            android.graphics.Paint r2 = r0.paint
            int r3 = r0.roundProgressColor
            r2.setColor(r3)
            float r8 = (float) r1
            float r9 = (float) r5
            r10 = 0
            android.graphics.Paint r11 = r0.paint
            r6 = r16
            r7 = r17
            r6.drawArc(r7, r8, r9, r10, r11)
            goto L_0x00cb
        L_0x00aa:
            r4 = 270(0x10e, float:3.78E-43)
            if (r1 <= r4) goto L_0x00cb
            int r1 = r0.mProgressAngle
            int r1 = r1 + r6
            int r1 = r1 % 360
            if (r1 >= r2) goto L_0x00cb
            if (r1 <= r3) goto L_0x00cb
            android.graphics.Paint r2 = r0.paint
            int r4 = r0.roundProgressColor
            r2.setColor(r4)
            int r1 = r1 - r3
            float r12 = (float) r1
            r13 = 0
            android.graphics.Paint r14 = r0.paint
            r9 = r16
            r10 = r17
            r11 = r7
            r9.drawArc(r10, r11, r12, r13, r14)
        L_0x00cb:
            android.graphics.Paint r1 = r0.paint
            r2 = 0
            r1.setShader(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.toyger.widget.UploadProgressBar.paintStroke(android.graphics.Canvas, android.graphics.RectF):void");
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        postInvalidate();
    }

    public void setRoundColor(int i) {
        this.roundColor = i;
        postInvalidate();
    }

    public synchronized int getMax() {
        return this.max;
    }

    public synchronized void setMax(int i) {
        if (i >= 0) {
            this.max = i;
        } else {
            throw new IllegalArgumentException("max not less than 0");
        }
    }

    public synchronized int getProgress() {
        return this.progress;
    }

    public synchronized void setProgress(int i) {
        if (i >= 0) {
            if (i > this.max) {
                i = this.max;
            }
            if (i <= this.max) {
                this.progress = i;
                postInvalidate();
            }
        } else {
            throw new IllegalArgumentException("progress not less than 0");
        }
    }

    public synchronized void setProgressAngle(int i) {
        this.mProgressAngle = i;
        postInvalidate();
    }

    public int getCricleColor() {
        return this.roundColor;
    }

    public void setCricleColor(int i) {
        this.roundColor = i;
    }

    public int getCricleProgressColor() {
        return this.roundProgressColor;
    }

    public void setCricleProgressColor(int i) {
        this.roundProgressColor = i;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public float getRoundWidth() {
        return this.roundWidth;
    }

    public void setRoundWidth(float f) {
        this.roundWidth = f;
    }
}
