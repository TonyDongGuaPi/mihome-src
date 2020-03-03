package com.xiaomi.smarthome.library.common.widget.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import java.util.HashMap;

public class ColorPicker extends ImageView {
    static final int CACHE_COLOR_COUNT = 300;
    private static final int[] COLORS = {-65536, -1, -256, Color.g, Color.j, Color.h, Color.k, -65536};
    private static final String STATE_ANGLE = "angle";
    private static final String STATE_OLD_COLOR = "color";
    private static final String STATE_PARENT = "parent";
    private static final String STATE_SHOW_OLD_COLOR = "showColor";
    static final String TAG = "ColorPicker";
    protected float mAngle;
    protected Paint mBmpPaint;
    protected Paint mCenterHaloPaint;
    private int mCenterNewColor;
    protected Paint mCenterNewPaint;
    private int mCenterOldColor;
    protected Paint mCenterOldPaint;
    protected RectF mCenterRectangle = new RectF();
    private int mColor;
    HashMap<Integer, Float> mColorAngleMap;
    protected int mColorCenterHaloRadius;
    private int mColorCenterRadius;
    private OnColorChangingListener mColorChangingListener;
    private Bitmap mColorCircleBmp;
    protected int mColorPointerHaloRadius;
    protected int mColorPointerRadius;
    private Paint mColorWheelPaint;
    protected int mColorWheelRadius;
    private RectF mColorWheelRectangle = new RectF();
    private int mColorWheelThickness;
    protected Bitmap mDotBmp;
    private float[] mHSV = new float[3];
    float mLastAngle;
    private OpacityBar mOpacityBar = null;
    protected Paint mPointerColor;
    protected Paint mPointerHaloPaint;
    private int mPreferredColorCenterHaloRadius;
    private int mPreferredColorCenterRadius;
    private int mPreferredColorWheelRadius;
    private SVBar mSVbar = null;
    private SaturationBar mSaturationBar = null;
    protected boolean mShowCenterOldColor;
    private float mSlopX;
    private float mSlopY;
    private boolean mTouchAnywhereOnColorWheelEnabled = true;
    protected float mTranslationOffset;
    private boolean mUserIsMovingPointer = false;
    private ValueBar mValueBar = null;
    private int oldChangedListenerColor;
    private int oldSelectedListenerColor;
    private OnColorChangedListener onColorChangedListener;
    private OnColorSelectedListener onColorSelectedListener;

    public interface OnColorChangedListener {
        void a(int i);
    }

    public interface OnColorChangingListener {
        void a(int i);
    }

    public interface OnColorSelectedListener {
        void a(int i);
    }

    public ColorPicker(Context context) {
        super(context);
        init((AttributeSet) null, 0);
    }

    public ColorPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public ColorPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener2) {
        this.onColorChangedListener = onColorChangedListener2;
    }

    public OnColorChangedListener getOnColorChangedListener() {
        return this.onColorChangedListener;
    }

    public void setOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener2) {
        this.onColorSelectedListener = onColorSelectedListener2;
    }

    public void setOnColorChangingListener(OnColorChangingListener onColorChangingListener) {
        this.mColorChangingListener = onColorChangingListener;
    }

    public OnColorSelectedListener getOnColorSelectedListener() {
        return this.onColorSelectedListener;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet, int i) {
        this.mColorCircleBmp = ((BitmapDrawable) getDrawable()).getBitmap();
        buildColorAngleMap();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ColorPicker, i, 0);
        Resources resources = getContext().getResources();
        this.mColorWheelThickness = obtainStyledAttributes.getDimensionPixelSize(5, resources.getDimensionPixelSize(R.dimen.color_wheel_thickness));
        this.mColorWheelRadius = obtainStyledAttributes.getDimensionPixelSize(4, resources.getDimensionPixelSize(R.dimen.color_wheel_radius));
        this.mPreferredColorWheelRadius = this.mColorWheelRadius;
        this.mColorCenterRadius = obtainStyledAttributes.getDimensionPixelSize(1, resources.getDimensionPixelSize(R.dimen.color_center_radius));
        this.mPreferredColorCenterRadius = this.mColorCenterRadius;
        this.mColorCenterHaloRadius = obtainStyledAttributes.getDimensionPixelSize(0, resources.getDimensionPixelSize(R.dimen.color_center_halo_radius));
        this.mPreferredColorCenterHaloRadius = this.mColorCenterHaloRadius;
        this.mColorPointerRadius = obtainStyledAttributes.getDimensionPixelSize(3, resources.getDimensionPixelSize(R.dimen.color_pointer_radius));
        this.mColorPointerHaloRadius = obtainStyledAttributes.getDimensionPixelSize(2, resources.getDimensionPixelSize(R.dimen.color_pointer_halo_radius));
        obtainStyledAttributes.recycle();
        this.mAngle = -1.5707964f;
        SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, COLORS, (float[]) null);
        this.mColorWheelPaint = new Paint(1);
        this.mColorWheelPaint.setShader(sweepGradient);
        this.mColorWheelPaint.setStyle(Paint.Style.STROKE);
        this.mColorWheelPaint.setStrokeWidth((float) this.mColorWheelThickness);
        this.mPointerHaloPaint = new Paint(1);
        this.mPointerHaloPaint.setColor(-16777216);
        this.mPointerHaloPaint.setAlpha(80);
        this.mPointerColor = new Paint(1);
        this.mPointerColor.setColor(calculateColor(this.mAngle));
        this.mCenterNewPaint = new Paint(1);
        this.mCenterNewPaint.setColor(calculateColor(this.mAngle));
        this.mCenterNewPaint.setStyle(Paint.Style.FILL);
        this.mCenterOldPaint = new Paint(1);
        this.mCenterOldPaint.setColor(calculateColor(this.mAngle));
        this.mCenterOldPaint.setStyle(Paint.Style.FILL);
        this.mCenterHaloPaint = new Paint(1);
        this.mCenterHaloPaint.setColor(-16777216);
        this.mCenterHaloPaint.setAlpha(0);
        this.mCenterNewColor = calculateColor(this.mAngle);
        this.mCenterOldColor = calculateColor(this.mAngle);
        this.mShowCenterOldColor = true;
        this.mBmpPaint = new Paint();
        this.mDotBmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.color_picker_pie_dot)).getBitmap();
    }

    private void buildColorAngleMap() {
        if (this.mColorAngleMap == null) {
            this.mColorAngleMap = new HashMap<>();
        }
        for (float f = 0.0f; ((double) f) <= 6.283185307179586d; f += 0.02094395f) {
            this.mColorAngleMap.put(Integer.valueOf(calculateColor(f)), Float.valueOf(f));
        }
    }

    public double colorDistance(int i, int i2) {
        double red = (double) ((android.graphics.Color.red(i) + android.graphics.Color.red(i2)) / 2);
        int red2 = android.graphics.Color.red(i) - android.graphics.Color.red(i2);
        Double.isNaN(red);
        Double.isNaN(red);
        double d = (double) red2;
        Double.isNaN(d);
        Double.isNaN(d);
        double d2 = ((red / 256.0d) + 2.0d) * d * d;
        double green = (double) (android.graphics.Color.green(i) - android.graphics.Color.green(i2));
        Double.isNaN(green);
        Double.isNaN(green);
        double blue = (double) (android.graphics.Color.blue(i) - android.graphics.Color.blue(i2));
        Double.isNaN(blue);
        Double.isNaN(blue);
        return Math.sqrt(d2 + (4.0d * green * green) + ((((255.0d - red) / 256.0d) + 2.0d) * blue * blue));
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"WrongCall"})
    public void callSuperOnDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(this.mTranslationOffset, this.mTranslationOffset);
        float[] calculatePointerPosition = calculatePointerPosition(this.mAngle);
        canvas.drawCircle(calculatePointerPosition[0], calculatePointerPosition[1], (float) this.mColorPointerHaloRadius, this.mPointerHaloPaint);
        canvas.drawCircle(calculatePointerPosition[0], calculatePointerPosition[1], (float) this.mColorPointerRadius, this.mPointerColor);
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
    public void onMeasure(int i, int i2) {
        int i3 = (this.mPreferredColorWheelRadius + this.mColorPointerHaloRadius) * 2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
        }
        if (mode2 == 1073741824) {
            i3 = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            i3 = Math.min(i3, size2);
        }
        int min = Math.min(size, i3);
        setMeasuredDimension(min, min);
        this.mTranslationOffset = ((float) min) * 0.5f;
        this.mColorWheelRadius = ((min / 2) - this.mColorWheelThickness) - this.mColorPointerHaloRadius;
        this.mColorWheelRectangle.set((float) (-this.mColorWheelRadius), (float) (-this.mColorWheelRadius), (float) this.mColorWheelRadius, (float) this.mColorWheelRadius);
        this.mColorCenterRadius = (int) (((float) this.mPreferredColorCenterRadius) * (((float) this.mColorWheelRadius) / ((float) this.mPreferredColorWheelRadius)));
        this.mColorCenterHaloRadius = (int) (((float) this.mPreferredColorCenterHaloRadius) * (((float) this.mColorWheelRadius) / ((float) this.mPreferredColorWheelRadius)));
        this.mCenterRectangle.set((float) (-this.mColorCenterRadius), (float) (-this.mColorCenterRadius), (float) this.mColorCenterRadius, (float) this.mColorCenterRadius);
    }

    private int ave(int i, int i2, float f) {
        return i + Math.round(f * ((float) (i2 - i)));
    }

    private int calculateColor(float f) {
        if (this.mColorCircleBmp == null) {
            return Color.h;
        }
        int min = Math.min((this.mColorCircleBmp.getWidth() / 2) - 2, (this.mColorCircleBmp.getHeight() / 2) - 2);
        double d = (double) f;
        double cos = Math.cos(d);
        double d2 = (double) min;
        Double.isNaN(d2);
        double sin = Math.sin(d);
        Double.isNaN(d2);
        return this.mColorCircleBmp.getPixel(((int) (cos * d2)) + (this.mColorCircleBmp.getWidth() / 2), ((int) (sin * d2)) + (this.mColorCircleBmp.getHeight() / 2));
    }

    public int getColor() {
        return this.mCenterNewColor;
    }

    public void setColor(int i) {
        this.mAngle = colorToAngle(i);
        this.mPointerColor.setColor(calculateColor(this.mAngle));
        if (this.mOpacityBar != null) {
            this.mOpacityBar.setColor(this.mColor);
            this.mOpacityBar.setOpacity(android.graphics.Color.alpha(i));
        }
        if (this.mSVbar != null) {
            android.graphics.Color.colorToHSV(i, this.mHSV);
            this.mSVbar.setColor(this.mColor);
            if (this.mHSV[1] < this.mHSV[2]) {
                this.mSVbar.setSaturation(this.mHSV[1]);
            } else if (this.mHSV[1] > this.mHSV[2]) {
                this.mSVbar.setValue(this.mHSV[2]);
            }
        }
        if (this.mSaturationBar != null) {
            android.graphics.Color.colorToHSV(i, this.mHSV);
            this.mSaturationBar.setColor(this.mColor);
            this.mSaturationBar.setSaturation(this.mHSV[1]);
        }
        if (this.mValueBar != null && this.mSaturationBar == null) {
            android.graphics.Color.colorToHSV(i, this.mHSV);
            this.mValueBar.setColor(this.mColor);
            this.mValueBar.setValue(this.mHSV[2]);
        } else if (this.mValueBar != null) {
            android.graphics.Color.colorToHSV(i, this.mHSV);
            this.mValueBar.setValue(this.mHSV[2]);
        }
        setNewCenterColor(i);
        if (this.mColorChangingListener != null) {
            this.mColorChangingListener.a(this.mCenterNewColor);
        }
    }

    private float colorToAngle(int i) {
        if (this.mColorAngleMap == null) {
            return 0.0f;
        }
        Float f = this.mColorAngleMap.get(Integer.valueOf(i));
        if (f != null) {
            return f.floatValue();
        }
        Integer num = null;
        double d = -1.0d;
        for (Integer next : this.mColorAngleMap.keySet()) {
            double colorDistance = colorDistance(next.intValue(), i);
            if (d < 0.0d || colorDistance < d) {
                num = next;
                d = colorDistance;
            }
        }
        if (num != null) {
            return this.mColorAngleMap.get(num).floatValue();
        }
        return 0.0f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        float x = motionEvent.getX() - this.mTranslationOffset;
        float y = motionEvent.getY() - this.mTranslationOffset;
        switch (motionEvent.getAction()) {
            case 0:
                if (Math.sqrt((double) ((x * x) + (y * y))) <= ((double) (this.mColorWheelRadius + this.mColorPointerHaloRadius)) && this.mTouchAnywhereOnColorWheelEnabled) {
                    calculatePointerPosition(this.mAngle);
                    this.mLastAngle = (float) Math.atan2((double) y, (double) x);
                    this.mUserIsMovingPointer = true;
                    invalidate();
                    break;
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
                break;
            case 1:
                this.mUserIsMovingPointer = false;
                this.mCenterHaloPaint.setAlpha(0);
                if (!(this.onColorSelectedListener == null || this.mCenterNewColor == this.oldSelectedListenerColor)) {
                    this.onColorSelectedListener.a(this.mCenterNewColor);
                    this.oldSelectedListenerColor = this.mCenterNewColor;
                }
                invalidate();
                break;
            case 2:
                if (this.mUserIsMovingPointer) {
                    float atan2 = (float) Math.atan2((double) y, (double) x);
                    this.mAngle += atan2 - this.mLastAngle;
                    if (((double) this.mAngle) > 6.283185307179586d) {
                        double d = (double) this.mAngle;
                        Double.isNaN(d);
                        this.mAngle = (float) (d - 6.283185307179586d);
                    } else if (this.mAngle < 0.0f) {
                        double d2 = (double) this.mAngle;
                        Double.isNaN(d2);
                        this.mAngle = (float) (d2 + 6.283185307179586d);
                    }
                    this.mLastAngle = atan2;
                    this.mPointerColor.setColor(calculateColor(this.mAngle));
                    int calculateColor = calculateColor(this.mAngle);
                    this.mCenterNewColor = calculateColor;
                    setNewCenterColor(calculateColor);
                    Log.d(TAG, "pointerColor=" + this.mCenterNewColor + ",angle=" + this.mAngle);
                    if (this.mColorChangingListener != null) {
                        this.mColorChangingListener.a(this.mCenterNewColor);
                    }
                    if (this.mOpacityBar != null) {
                        this.mOpacityBar.setColor(this.mColor);
                    }
                    if (this.mValueBar != null) {
                        this.mValueBar.setColor(this.mColor);
                    }
                    if (this.mSaturationBar != null) {
                        this.mSaturationBar.setColor(this.mColor);
                    }
                    if (this.mSVbar != null) {
                        this.mSVbar.setColor(this.mColor);
                    }
                    invalidate();
                    break;
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            case 3:
                if (!(this.onColorSelectedListener == null || this.mCenterNewColor == this.oldSelectedListenerColor)) {
                    this.onColorSelectedListener.a(this.mCenterNewColor);
                    this.oldSelectedListenerColor = this.mCenterNewColor;
                    break;
                }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public float[] calculatePointerPosition(float f) {
        double d = (double) ((this.mColorWheelRadius * 9) / 12);
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
        double d2 = (double) ((this.mColorWheelRadius * 8) / 12);
        double d3 = (double) ((float) (d + 3.141592653589793d));
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        return new float[]{(float) (cos * d2), (float) (d2 * sin)};
    }

    public void addSVBar(SVBar sVBar) {
        this.mSVbar = sVBar;
        this.mSVbar.setColorPicker(this);
        this.mSVbar.setColor(this.mColor);
    }

    public void addOpacityBar(OpacityBar opacityBar) {
        this.mOpacityBar = opacityBar;
        this.mOpacityBar.setColorPicker(this);
        this.mOpacityBar.setColor(this.mColor);
    }

    public void addSaturationBar(SaturationBar saturationBar) {
        this.mSaturationBar = saturationBar;
        this.mSaturationBar.setColorPicker(this);
        this.mSaturationBar.setColor(this.mColor);
    }

    public void addValueBar(ValueBar valueBar) {
        this.mValueBar = valueBar;
        this.mValueBar.setColorPicker(this);
        this.mValueBar.setColor(this.mColor);
    }

    public void setNewCenterColor(int i) {
        this.mCenterNewColor = i;
        this.mCenterNewPaint.setColor(i);
        if (this.mCenterOldColor == 0) {
            this.mCenterOldColor = i;
            this.mCenterOldPaint.setColor(i);
        }
        if (!(this.onColorChangedListener == null || i == this.oldChangedListenerColor)) {
            this.onColorChangedListener.a(i);
            this.oldChangedListenerColor = i;
        }
        invalidate();
    }

    public void setOldCenterColor(int i) {
        this.mCenterOldColor = i;
        this.mCenterOldPaint.setColor(i);
        invalidate();
    }

    public int getOldCenterColor() {
        return this.mCenterOldColor;
    }

    public void setShowOldCenterColor(boolean z) {
        this.mShowCenterOldColor = z;
        invalidate();
    }

    public boolean getShowOldCenterColor() {
        return this.mShowCenterOldColor;
    }

    public void changeOpacityBarColor(int i) {
        if (this.mOpacityBar != null) {
            this.mOpacityBar.setColor(i);
        }
    }

    public void changeSaturationBarColor(int i) {
        if (this.mSaturationBar != null) {
            this.mSaturationBar.setColor(i);
        }
    }

    public void changeValueBarColor(int i) {
        if (this.mValueBar != null) {
            this.mValueBar.setColor(i);
        }
    }

    public boolean hasOpacityBar() {
        return this.mOpacityBar != null;
    }

    public boolean hasValueBar() {
        return this.mValueBar != null;
    }

    public boolean hasSaturationBar() {
        return this.mSaturationBar != null;
    }

    public boolean hasSVBar() {
        return this.mSVbar != null;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_PARENT, onSaveInstanceState);
        bundle.putFloat("angle", this.mAngle);
        bundle.putInt("color", this.mCenterOldColor);
        bundle.putBoolean(STATE_SHOW_OLD_COLOR, this.mShowCenterOldColor);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable(STATE_PARENT));
        this.mAngle = bundle.getFloat("angle");
        setOldCenterColor(bundle.getInt("color"));
        this.mShowCenterOldColor = bundle.getBoolean(STATE_SHOW_OLD_COLOR);
        int calculateColor = calculateColor(this.mAngle);
        this.mPointerColor.setColor(calculateColor);
        setNewCenterColor(calculateColor);
    }

    public void setTouchAnywhereOnColorWheelEnabled(boolean z) {
        this.mTouchAnywhereOnColorWheelEnabled = z;
    }

    public boolean getTouchAnywhereOnColorWheel() {
        return this.mTouchAnywhereOnColorWheelEnabled;
    }
}
