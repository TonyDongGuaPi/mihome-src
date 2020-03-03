package com.alipay.biometrics.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.alipay.mobile.security.bio.utils.BioLog;

public class WaveView extends View {
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.05f;
    public static final int DEFAULT_BEHIND_WAVE_COLOR = Color.parseColor("#28FFFFFF");
    public static final int DEFAULT_FRONT_WAVE_COLOR = Color.parseColor("#3CFFFFFF");
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    public static final ShapeType DEFAULT_WAVE_SHAPE = ShapeType.CIRCLE;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;
    private float mAmplitudeRatio = 0.05f;
    private int mBehindWaveColor = DEFAULT_BEHIND_WAVE_COLOR;
    private Paint mBorderPaint;
    private float mDefaultAmplitude;
    private double mDefaultAngularFrequency;
    private float mDefaultWaterLevel;
    private float mDefaultWaveLength;
    private int mFrontWaveColor = DEFAULT_FRONT_WAVE_COLOR;
    private Matrix mShaderMatrix;
    private ShapeType mShapeType = DEFAULT_WAVE_SHAPE;
    private boolean mShowWave;
    private Paint mViewPaint;
    private float mWaterLevelRatio = 0.5f;
    private float mWaveLengthRatio = 1.0f;
    private BitmapShader mWaveShader;
    private float mWaveShiftRatio = 0.0f;

    public enum ShapeType {
        CIRCLE,
        SQUARE
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mShaderMatrix = new Matrix();
        this.mViewPaint = new Paint();
        this.mViewPaint.setAntiAlias(true);
    }

    public float getWaveShiftRatio() {
        return this.mWaveShiftRatio;
    }

    public void setWaveShiftRatio(float f) {
        if (this.mWaveShiftRatio != f) {
            this.mWaveShiftRatio = f;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return this.mWaterLevelRatio;
    }

    public void setWaterLevelRatio(float f) {
        if (this.mWaterLevelRatio != f) {
            this.mWaterLevelRatio = f;
            invalidate();
        }
    }

    public float getAmplitudeRatio() {
        return this.mAmplitudeRatio;
    }

    public void setAmplitudeRatio(float f) {
        if (this.mAmplitudeRatio != f) {
            this.mAmplitudeRatio = f;
            invalidate();
        }
    }

    public float getWaveLengthRatio() {
        return this.mWaveLengthRatio;
    }

    public void setWaveLengthRatio(float f) {
        this.mWaveLengthRatio = f;
    }

    public boolean isShowWave() {
        return this.mShowWave;
    }

    public void setShowWave(boolean z) {
        this.mShowWave = z;
    }

    public void setBorder(int i, int i2) {
        if (this.mBorderPaint == null) {
            this.mBorderPaint = new Paint();
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setStyle(Paint.Style.STROKE);
        }
        this.mBorderPaint.setColor(i2);
        this.mBorderPaint.setStrokeWidth((float) i);
        invalidate();
    }

    public void setWaveColor(int i, int i2) {
        this.mBehindWaveColor = i;
        this.mFrontWaveColor = i2;
        this.mWaveShader = null;
        createShader();
        invalidate();
    }

    public void setShapeType(ShapeType shapeType) {
        this.mShapeType = shapeType;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        createShader();
    }

    private void createShader() {
        double width = (double) getWidth();
        Double.isNaN(width);
        this.mDefaultAngularFrequency = 6.283185307179586d / width;
        this.mDefaultAmplitude = ((float) getHeight()) * 0.05f;
        this.mDefaultWaterLevel = ((float) getHeight()) * 0.5f;
        this.mDefaultWaveLength = (float) getWidth();
        try {
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setStrokeWidth(2.0f);
            paint.setAntiAlias(true);
            int width2 = getWidth() + 1;
            int height = getHeight() + 1;
            float[] fArr = new float[width2];
            paint.setColor(this.mBehindWaveColor);
            for (int i = 0; i < width2; i++) {
                double d = (double) i;
                double d2 = this.mDefaultAngularFrequency;
                Double.isNaN(d);
                double d3 = d * d2;
                double d4 = (double) this.mDefaultWaterLevel;
                double d5 = (double) this.mDefaultAmplitude;
                double sin = Math.sin(d3);
                Double.isNaN(d5);
                Double.isNaN(d4);
                float f = (float) (d4 + (d5 * sin));
                float f2 = (float) i;
                canvas.drawLine(f2, f, f2, (float) height, paint);
                fArr[i] = f;
            }
            paint.setColor(this.mFrontWaveColor);
            int i2 = (int) (this.mDefaultWaveLength / 4.0f);
            for (int i3 = 0; i3 < width2; i3++) {
                float f3 = (float) i3;
                canvas.drawLine(f3, fArr[(i3 + i2) % width2], f3, (float) height, paint);
            }
            this.mWaveShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            this.mViewPaint.setShader(this.mWaveShader);
        } catch (OutOfMemoryError e) {
            BioLog.e(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.mShowWave || this.mWaveShader == null) {
            this.mViewPaint.setShader((Shader) null);
            return;
        }
        if (this.mViewPaint.getShader() == null) {
            this.mViewPaint.setShader(this.mWaveShader);
        }
        float f = 0.0f;
        this.mShaderMatrix.setScale(this.mWaveLengthRatio / 1.0f, this.mAmplitudeRatio / 0.05f, 0.0f, this.mDefaultWaterLevel);
        this.mShaderMatrix.postTranslate(this.mWaveShiftRatio * ((float) getWidth()), (0.5f - this.mWaterLevelRatio) * ((float) getHeight()));
        this.mWaveShader.setLocalMatrix(this.mShaderMatrix);
        if (this.mBorderPaint != null) {
            f = this.mBorderPaint.getStrokeWidth();
        }
        switch (this.mShapeType) {
            case CIRCLE:
                paintCircleShape(canvas, f);
                return;
            case SQUARE:
                paintSquare(canvas, f);
                return;
            default:
                return;
        }
    }

    private void paintSquare(Canvas canvas, float f) {
        if (f > 0.0f) {
            float f2 = f / 2.0f;
            canvas.drawRect(f2, f2, (((float) getWidth()) - f2) - 0.5f, (((float) getHeight()) - f2) - 0.5f, this.mBorderPaint);
        }
        canvas.drawRect(f, f, ((float) getWidth()) - f, ((float) getHeight()) - f, this.mViewPaint);
    }

    private void paintCircleShape(Canvas canvas, float f) {
        if (f > 0.0f) {
            canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - f) / 2.0f) - 1.0f, this.mBorderPaint);
        }
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, (((float) getWidth()) / 2.0f) - f, this.mViewPaint);
    }
}
