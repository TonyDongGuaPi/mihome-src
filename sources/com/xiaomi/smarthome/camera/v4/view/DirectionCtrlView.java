package com.xiaomi.smarthome.camera.v4.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.libra.Color;
import com.xiaomi.smarthome.R;

public class DirectionCtrlView extends View {
    public static final int CHECK = 5;
    public static final int CHECK_END = -5;
    public static final int DOWN = 4;
    public static final int DOWN_END = -4;
    public static final int GET = 6;
    public static final int LEFT = 1;
    public static final int LEFT_END = -1;
    public static final int OFF = -1001;
    private static final int POST_DELAY_TIME = 100;
    public static final int RIGHT = 2;
    public static final int RIGHT_END = -2;
    private static final String TAG = "DirectionCtrlView";
    public static final int UP = 3;
    public static final int UP_END = -3;
    private AttributeSet attr;
    private Bitmap bitmap;
    private int defStyleAttr;
    /* access modifiers changed from: private */
    public int direction = -1;
    /* access modifiers changed from: private */
    public Runnable directionRunnable = new Runnable() {
        public void run() {
            if (DirectionCtrlView.this.isPress) {
                DirectionCtrlView.this.onDirection(DirectionCtrlView.this.direction);
                DirectionCtrlView.this.getHandler().postDelayed(DirectionCtrlView.this.directionRunnable, 100);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean isPress = false;
    private int mArrowH;
    private int mArrowW;
    private int mBgWidth;
    private Bitmap mBitmapRockerDisable;
    private int mDiameter;
    private long mDownTime = 0;
    private boolean mIsDisabled = true;
    private OnDirectionCtrlListener mOnDirectionCtrlListener;
    private Paint mPaint;
    private Bitmap mPtzBottom;
    private Bitmap mPtzLeft;
    private Bitmap mPtzRight;
    private Bitmap mPtzTop;
    private int mRadius;
    public float mScale = 1.0f;
    private float threshold = 0.001f;

    public interface OnDirectionCtrlListener {
        void onActionDown();

        void onActionUp(boolean z);

        void onClickPTZDirection(int i);
    }

    public DirectionCtrlView(Context context) {
        super(context);
        init((AttributeSet) null, 0);
    }

    public DirectionCtrlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public DirectionCtrlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        init(this.attr, this.defStyleAttr, configuration.orientation);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (getVisibility() != 0) {
            return false;
        }
        if (!this.mIsDisabled) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mDownTime = System.currentTimeMillis();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (x < ((float) this.mRadius) && y > x && y < ((float) this.mDiameter) - x) {
                    this.isPress = true;
                    this.direction = 1;
                    invalidate();
                } else if (y >= ((float) this.mRadius) || x >= ((float) this.mRadius) ? y < ((float) this.mDiameter) - x : y < x) {
                    this.isPress = true;
                    this.direction = 3;
                    invalidate();
                } else if (x <= ((float) this.mRadius) || y >= ((float) this.mRadius) ? y < x : y > ((float) this.mDiameter) - x) {
                    this.isPress = true;
                    this.direction = 2;
                    invalidate();
                } else if (y <= ((float) this.mRadius) || x >= ((float) this.mRadius) ? y > x : y > ((float) this.mDiameter) - x) {
                    this.isPress = true;
                    this.direction = 4;
                    invalidate();
                }
                if (this.isPress && this.mOnDirectionCtrlListener != null) {
                    this.mOnDirectionCtrlListener.onActionDown();
                }
                if (this.isPress) {
                    getHandler().post(this.directionRunnable);
                    break;
                }
                break;
            case 1:
            case 3:
                this.isPress = false;
                if (this.mOnDirectionCtrlListener != null) {
                    OnDirectionCtrlListener onDirectionCtrlListener = this.mOnDirectionCtrlListener;
                    if (System.currentTimeMillis() - this.mDownTime > 500) {
                        z = true;
                    }
                    onDirectionCtrlListener.onActionUp(z);
                }
                this.direction = -1;
                getHandler().removeCallbacks(this.directionRunnable);
                invalidate();
                break;
        }
        return true;
    }

    private void init(AttributeSet attributeSet, int i) {
        this.attr = attributeSet;
        this.defStyleAttr = i;
        init(attributeSet, i, 1);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes;
        if (!(attributeSet == null || (obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ViewScaleStyleable)) == null)) {
            this.mScale = obtainStyledAttributes.getFloat(0, 1.0f);
        }
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.c);
        this.mPaint.setAntiAlias(true);
        setBackgroundColor(0);
        if (i2 == 1) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_bg);
        } else {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_bg);
        }
        int width = (int) (((float) this.bitmap.getWidth()) * this.mScale);
        int height = (int) (((float) this.bitmap.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.bitmap = Bitmap.createScaledBitmap(this.bitmap, width, height, true);
        }
        this.mBgWidth = this.bitmap.getWidth();
        this.mDiameter = this.mBgWidth;
        this.mRadius = this.mBgWidth / 2;
        if (i2 == 1) {
            this.mPtzTop = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_top);
        } else {
            this.mPtzTop = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_up_press);
        }
        int width2 = (int) (((float) this.mPtzTop.getWidth()) * this.mScale);
        int height2 = (int) (((float) this.mPtzTop.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.mPtzTop = Bitmap.createScaledBitmap(this.mPtzTop, width2, height2, true);
        }
        this.mArrowW = this.mPtzTop.getWidth();
        this.mArrowH = this.mPtzTop.getHeight();
        if (i2 == 1) {
            this.mPtzBottom = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_buttom);
        } else {
            this.mPtzBottom = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_down_press);
        }
        int width3 = (int) (((float) this.mPtzBottom.getWidth()) * this.mScale);
        int height3 = (int) (((float) this.mPtzBottom.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.mPtzBottom = Bitmap.createScaledBitmap(this.mPtzBottom, width3, height3, true);
        }
        if (i2 == 1) {
            this.mPtzLeft = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_left);
        } else {
            this.mPtzLeft = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_left_press);
        }
        int width4 = (int) (((float) this.mPtzLeft.getWidth()) * this.mScale);
        int height4 = (int) (((float) this.mPtzLeft.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.mPtzLeft = Bitmap.createScaledBitmap(this.mPtzLeft, width4, height4, true);
        }
        if (i2 == 1) {
            this.mPtzRight = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_right);
        } else {
            this.mPtzRight = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_right_press);
        }
        int width5 = (int) (((float) this.mPtzRight.getWidth()) * this.mScale);
        int height5 = (int) (((float) this.mPtzRight.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.mPtzRight = Bitmap.createScaledBitmap(this.mPtzRight, width5, height5, true);
        }
        if (i2 == 1) {
            this.mBitmapRockerDisable = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_bg);
        } else {
            this.mBitmapRockerDisable = BitmapFactory.decodeResource(getResources(), R.drawable.home_ptz_fullscreen_bg);
        }
        int width6 = (int) (((float) this.mBitmapRockerDisable.getWidth()) * this.mScale);
        int height6 = (int) (((float) this.mBitmapRockerDisable.getHeight()) * this.mScale);
        if (Math.abs(this.mScale - 1.0f) > this.threshold) {
            this.mBitmapRockerDisable = Bitmap.createScaledBitmap(this.mBitmapRockerDisable, width6, height6, true);
        }
    }

    /* access modifiers changed from: private */
    public void onDirection(int i) {
        if (this.mOnDirectionCtrlListener != null) {
            switch (i) {
                case 1:
                    this.mOnDirectionCtrlListener.onClickPTZDirection(1);
                    return;
                case 2:
                    this.mOnDirectionCtrlListener.onClickPTZDirection(2);
                    return;
                case 3:
                    this.mOnDirectionCtrlListener.onClickPTZDirection(3);
                    return;
                case 4:
                    this.mOnDirectionCtrlListener.onClickPTZDirection(4);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = getPaddingLeft() + this.mBitmapRockerDisable.getWidth() + getPaddingRight();
        }
        if (mode2 != 1073741824) {
            size2 = getPaddingTop() + this.mBitmapRockerDisable.getHeight() + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvasOk(canvas);
    }

    private void canvasOk(Canvas canvas) {
        try {
            if (this.mIsDisabled) {
                canvas.drawBitmap(this.bitmap, (float) getPaddingLeft(), (float) getPaddingTop(), this.mPaint);
                if (this.isPress) {
                    switch (this.direction) {
                        case 1:
                            canvas.drawBitmap(this.mPtzLeft, (float) getPaddingLeft(), (float) (getPaddingTop() + ((this.mBgWidth - this.mArrowW) / 2)), this.mPaint);
                            return;
                        case 2:
                            canvas.drawBitmap(this.mPtzRight, (float) (getPaddingLeft() + (this.mBgWidth - this.mArrowH)), (float) (getPaddingTop() + ((this.mBgWidth - this.mArrowW) / 2)), this.mPaint);
                            return;
                        case 3:
                            canvas.drawBitmap(this.mPtzTop, (float) (getPaddingLeft() + ((this.mBgWidth - this.mArrowW) / 2)), (float) getPaddingTop(), this.mPaint);
                            return;
                        case 4:
                            canvas.drawBitmap(this.mPtzBottom, (float) (getPaddingLeft() + ((this.mBgWidth - this.mArrowW) / 2)), (float) ((getPaddingTop() + this.mBgWidth) - this.mArrowH), this.mPaint);
                            return;
                        default:
                            return;
                    }
                }
            } else {
                canvas.drawBitmap(this.mBitmapRockerDisable, (float) getPaddingLeft(), (float) getPaddingTop(), this.mPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisable(boolean z) {
        this.mIsDisabled = z;
        invalidate();
    }

    public int getRocherWidth() {
        return this.mBgWidth;
    }

    public void setOnDirectionCtrlListener(OnDirectionCtrlListener onDirectionCtrlListener) {
        this.mOnDirectionCtrlListener = onDirectionCtrlListener;
    }
}
