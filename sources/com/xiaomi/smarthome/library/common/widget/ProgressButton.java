package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.libra.Color;
import com.xiaomi.smarthome.library.R;

public class ProgressButton extends Button {
    public static final int MODE_BUTTON = 0;
    public static final int MODE_PROGRESS = 1;
    private Bitmap mBackgroundBitmap;
    private Drawable mBackgroundDrawable;
    private Paint mBackgroundPaint;
    private int mHeight;
    private int mMode;
    private int mPbColor;
    private int mPbTextColor;
    private int mProgress;
    private RectF mRectSrc;
    private int mTextColor;
    private Paint mTextPaint;
    private int mWidth;
    private PorterDuffXfermode mXfermode;

    public ProgressButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public ProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgress = -1;
        this.mPbColor = Color.g;
        this.mPbTextColor = -1;
        init(context, attributeSet);
    }

    public ProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgress = -1;
        this.mPbColor = Color.g;
        this.mPbTextColor = -1;
        init(context, attributeSet);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressButton);
        this.mPbColor = obtainStyledAttributes.getColor(R.styleable.ProgressButton_ProgressButton_pbColor, Color.g);
        this.mPbTextColor = obtainStyledAttributes.getColor(R.styleable.ProgressButton_ProgressButton_pbTextColor, -1);
        obtainStyledAttributes.recycle();
    }

    private void init(Context context, AttributeSet attributeSet) {
        initAttrs(context, attributeSet);
        this.mTextColor = getCurrentTextColor();
        this.mBackgroundPaint = new Paint();
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(getTextSize());
        this.mTextPaint.setColor(this.mTextColor);
        this.mBackgroundDrawable = getBackground();
        this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    }

    public void setProgress(int i) {
        if (i > this.mProgress) {
            this.mProgress = i;
            invalidate();
        }
    }

    public void setButtonMode(int i) {
        this.mMode = i;
        if (i == 0) {
            setBackground(this.mBackgroundDrawable);
            this.mProgress = -1;
        } else if (i == 1) {
            setBackgroundResource(0);
        } else {
            throw new UnsupportedOperationException("Unknown mode " + i);
        }
    }

    public int getButtonMode() {
        return this.mMode;
    }

    private void releaseBgBitmap() {
        if (this.mBackgroundBitmap != null) {
            this.mBackgroundBitmap.recycle();
            this.mBackgroundBitmap = null;
        }
    }

    public boolean isProgressMode() {
        return this.mMode == 1;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0 && i != i3 && i2 != i4) {
            this.mWidth = i;
            this.mHeight = i2;
            this.mRectSrc = new RectF(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight);
            if (this.mBackgroundBitmap != null) {
                releaseBgBitmap();
            }
            this.mBackgroundBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.mBackgroundBitmap);
            this.mBackgroundDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            this.mBackgroundDrawable.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mWidth > 0 && this.mHeight > 0 && isProgressMode()) {
            int saveLayer = canvas.saveLayer(this.mRectSrc, this.mBackgroundPaint, 31);
            canvas.drawBitmap(this.mBackgroundBitmap, (Rect) null, this.mRectSrc, this.mBackgroundPaint);
            this.mBackgroundPaint.setXfermode(this.mXfermode);
            this.mBackgroundPaint.setColor(this.mPbColor);
            this.mBackgroundPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0.0f, 0.0f, (float) ((this.mWidth * this.mProgress) / 100), (float) this.mHeight, this.mBackgroundPaint);
            this.mBackgroundPaint.setXfermode((Xfermode) null);
            drawText(canvas);
            canvas.restoreToCount(saveLayer);
        }
    }

    private void drawText(Canvas canvas) {
        float f = ((float) this.mProgress) * 0.01f;
        this.mTextPaint.setShader(new LinearGradient(0.0f, 0.0f, (float) this.mWidth, 0.0f, new int[]{this.mPbTextColor, this.mTextColor}, new float[]{f, f + 0.001f}, Shader.TileMode.CLAMP));
        String charSequence = getText().toString();
        canvas.drawText(charSequence, (((float) this.mWidth) - this.mTextPaint.measureText(charSequence)) / 2.0f, ((float) (this.mHeight / 2)) - ((this.mTextPaint.descent() + this.mTextPaint.ascent()) / 2.0f), this.mTextPaint);
    }

    public void destroy() {
        releaseBgBitmap();
    }
}
