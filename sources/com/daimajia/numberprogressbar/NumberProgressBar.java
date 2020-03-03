package com.daimajia.numberprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import com.taobao.weex.el.parse.Operators;

public class NumberProgressBar extends View {
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_REACHED_BAR_COLOR = "reached_bar_color";
    private static final String INSTANCE_REACHED_BAR_HEIGHT = "reached_bar_height";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_TEXT_VISIBILITY = "text_visibility";
    private static final String INSTANCE_UNREACHED_BAR_COLOR = "unreached_bar_color";
    private static final String INSTANCE_UNREACHED_BAR_HEIGHT = "unreached_bar_height";
    private static final int PROGRESS_TEXT_VISIBLE = 0;
    private final float default_progress_text_offset;
    private final float default_reached_bar_height;
    private final int default_reached_color;
    private final int default_text_color;
    private final float default_text_size;
    private final float default_unreached_bar_height;
    private final int default_unreached_color;
    private String mCurrentDrawText;
    private int mCurrentProgress;
    private boolean mDrawReachedBar;
    private float mDrawTextEnd;
    private float mDrawTextStart;
    private float mDrawTextWidth;
    private boolean mDrawUnreachedBar;
    private boolean mIfDrawText;
    private OnProgressBarListener mListener;
    private int mMaxProgress;
    private float mOffset;
    private String mPrefix;
    private int mReachedBarColor;
    private float mReachedBarHeight;
    private Paint mReachedBarPaint;
    private RectF mReachedRectF;
    private String mSuffix;
    private int mTextColor;
    private Paint mTextPaint;
    private float mTextSize;
    private int mUnreachedBarColor;
    private float mUnreachedBarHeight;
    private Paint mUnreachedBarPaint;
    private RectF mUnreachedRectF;

    public enum ProgressTextVisibility {
        Visible,
        Invisible
    }

    public NumberProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxProgress = 100;
        this.mCurrentProgress = 0;
        this.mSuffix = Operators.MOD;
        this.mPrefix = "";
        this.default_text_color = Color.rgb(66, 145, 241);
        this.default_reached_color = Color.rgb(66, 145, 241);
        this.default_unreached_color = Color.rgb(204, 204, 204);
        this.mUnreachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mReachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mDrawUnreachedBar = true;
        this.mDrawReachedBar = true;
        this.mIfDrawText = true;
        this.default_reached_bar_height = dp2px(1.5f);
        this.default_unreached_bar_height = dp2px(1.0f);
        this.default_text_size = sp2px(10.0f);
        this.default_progress_text_offset = dp2px(3.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.NumberProgressBar, i, 0);
        this.mReachedBarColor = obtainStyledAttributes.getColor(R.styleable.NumberProgressBar_progress_reached_color, this.default_reached_color);
        this.mUnreachedBarColor = obtainStyledAttributes.getColor(R.styleable.NumberProgressBar_progress_unreached_color, this.default_unreached_color);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.NumberProgressBar_progress_text_color, this.default_text_color);
        this.mTextSize = obtainStyledAttributes.getDimension(R.styleable.NumberProgressBar_progress_text_size, this.default_text_size);
        this.mReachedBarHeight = obtainStyledAttributes.getDimension(R.styleable.NumberProgressBar_progress_reached_bar_height, this.default_reached_bar_height);
        this.mUnreachedBarHeight = obtainStyledAttributes.getDimension(R.styleable.NumberProgressBar_progress_unreached_bar_height, this.default_unreached_bar_height);
        this.mOffset = obtainStyledAttributes.getDimension(R.styleable.NumberProgressBar_progress_text_offset, this.default_progress_text_offset);
        if (obtainStyledAttributes.getInt(R.styleable.NumberProgressBar_progress_text_visibility, 0) != 0) {
            this.mIfDrawText = false;
        }
        setProgress(obtainStyledAttributes.getInt(R.styleable.NumberProgressBar_progress_current, 0));
        setMax(obtainStyledAttributes.getInt(R.styleable.NumberProgressBar_progress_max, 100));
        obtainStyledAttributes.recycle();
        initializePainters();
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        return (int) this.mTextSize;
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        return Math.max((int) this.mTextSize, Math.max((int) this.mReachedBarHeight, (int) this.mUnreachedBarHeight));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(measure(i, true), measure(i2, false));
    }

    private int measure(int i, boolean z) {
        int paddingTop;
        int paddingBottom;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (z) {
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        int i2 = paddingTop + paddingBottom;
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumWidth = i2 + (z ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight());
        if (mode != Integer.MIN_VALUE) {
            return suggestedMinimumWidth;
        }
        if (z) {
            return Math.max(suggestedMinimumWidth, size);
        }
        return Math.min(suggestedMinimumWidth, size);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mIfDrawText) {
            calculateDrawRectF();
        } else {
            calculateDrawRectFWithoutProgressText();
        }
        if (this.mDrawReachedBar) {
            canvas.drawRect(this.mReachedRectF, this.mReachedBarPaint);
        }
        if (this.mDrawUnreachedBar) {
            canvas.drawRect(this.mUnreachedRectF, this.mUnreachedBarPaint);
        }
        if (this.mIfDrawText) {
            canvas.drawText(this.mCurrentDrawText, this.mDrawTextStart, this.mDrawTextEnd, this.mTextPaint);
        }
    }

    private void initializePainters() {
        this.mReachedBarPaint = new Paint(1);
        this.mReachedBarPaint.setColor(this.mReachedBarColor);
        this.mUnreachedBarPaint = new Paint(1);
        this.mUnreachedBarPaint.setColor(this.mUnreachedBarColor);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize(this.mTextSize);
    }

    private void calculateDrawRectFWithoutProgressText() {
        this.mReachedRectF.left = (float) getPaddingLeft();
        this.mReachedRectF.top = (((float) getHeight()) / 2.0f) - (this.mReachedBarHeight / 2.0f);
        this.mReachedRectF.right = ((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) + ((float) getPaddingLeft());
        this.mReachedRectF.bottom = (((float) getHeight()) / 2.0f) + (this.mReachedBarHeight / 2.0f);
        this.mUnreachedRectF.left = this.mReachedRectF.right;
        this.mUnreachedRectF.right = (float) (getWidth() - getPaddingRight());
        this.mUnreachedRectF.top = (((float) getHeight()) / 2.0f) + ((-this.mUnreachedBarHeight) / 2.0f);
        this.mUnreachedRectF.bottom = (((float) getHeight()) / 2.0f) + (this.mUnreachedBarHeight / 2.0f);
    }

    private void calculateDrawRectF() {
        this.mCurrentDrawText = String.format("%d", new Object[]{Integer.valueOf((getProgress() * 100) / getMax())});
        this.mCurrentDrawText = this.mPrefix + this.mCurrentDrawText + this.mSuffix;
        this.mDrawTextWidth = this.mTextPaint.measureText(this.mCurrentDrawText);
        if (getProgress() == 0) {
            this.mDrawReachedBar = false;
            this.mDrawTextStart = (float) getPaddingLeft();
        } else {
            this.mDrawReachedBar = true;
            this.mReachedRectF.left = (float) getPaddingLeft();
            this.mReachedRectF.top = (((float) getHeight()) / 2.0f) - (this.mReachedBarHeight / 2.0f);
            this.mReachedRectF.right = (((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) - this.mOffset) + ((float) getPaddingLeft());
            this.mReachedRectF.bottom = (((float) getHeight()) / 2.0f) + (this.mReachedBarHeight / 2.0f);
            this.mDrawTextStart = this.mReachedRectF.right + this.mOffset;
        }
        this.mDrawTextEnd = (float) ((int) ((((float) getHeight()) / 2.0f) - ((this.mTextPaint.descent() + this.mTextPaint.ascent()) / 2.0f)));
        if (this.mDrawTextStart + this.mDrawTextWidth >= ((float) (getWidth() - getPaddingRight()))) {
            this.mDrawTextStart = ((float) (getWidth() - getPaddingRight())) - this.mDrawTextWidth;
            this.mReachedRectF.right = this.mDrawTextStart - this.mOffset;
        }
        float f = this.mDrawTextStart + this.mDrawTextWidth + this.mOffset;
        if (f >= ((float) (getWidth() - getPaddingRight()))) {
            this.mDrawUnreachedBar = false;
            return;
        }
        this.mDrawUnreachedBar = true;
        this.mUnreachedRectF.left = f;
        this.mUnreachedRectF.right = (float) (getWidth() - getPaddingRight());
        this.mUnreachedRectF.top = (((float) getHeight()) / 2.0f) + ((-this.mUnreachedBarHeight) / 2.0f);
        this.mUnreachedRectF.bottom = (((float) getHeight()) / 2.0f) + (this.mUnreachedBarHeight / 2.0f);
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public float getProgressTextSize() {
        return this.mTextSize;
    }

    public int getUnreachedBarColor() {
        return this.mUnreachedBarColor;
    }

    public int getReachedBarColor() {
        return this.mReachedBarColor;
    }

    public int getProgress() {
        return this.mCurrentProgress;
    }

    public int getMax() {
        return this.mMaxProgress;
    }

    public float getReachedBarHeight() {
        return this.mReachedBarHeight;
    }

    public float getUnreachedBarHeight() {
        return this.mUnreachedBarHeight;
    }

    public void setProgressTextSize(float f) {
        this.mTextSize = f;
        this.mTextPaint.setTextSize(this.mTextSize);
        invalidate();
    }

    public void setProgressTextColor(int i) {
        this.mTextColor = i;
        this.mTextPaint.setColor(this.mTextColor);
        invalidate();
    }

    public void setUnreachedBarColor(int i) {
        this.mUnreachedBarColor = i;
        this.mUnreachedBarPaint.setColor(this.mUnreachedBarColor);
        invalidate();
    }

    public void setReachedBarColor(int i) {
        this.mReachedBarColor = i;
        this.mReachedBarPaint.setColor(this.mReachedBarColor);
        invalidate();
    }

    public void setReachedBarHeight(float f) {
        this.mReachedBarHeight = f;
    }

    public void setUnreachedBarHeight(float f) {
        this.mUnreachedBarHeight = f;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.mMaxProgress = i;
            invalidate();
        }
    }

    public void setSuffix(String str) {
        if (str == null) {
            this.mSuffix = "";
        } else {
            this.mSuffix = str;
        }
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public void setPrefix(String str) {
        if (str == null) {
            this.mPrefix = "";
        } else {
            this.mPrefix = str;
        }
    }

    public String getPrefix() {
        return this.mPrefix;
    }

    public void incrementProgressBy(int i) {
        if (i > 0) {
            setProgress(getProgress() + i);
        }
        if (this.mListener != null) {
            this.mListener.onProgressChange(getProgress(), getMax());
        }
    }

    public void setProgress(int i) {
        if (i <= getMax() && i >= 0) {
            this.mCurrentProgress = i;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getProgressTextSize());
        bundle.putFloat(INSTANCE_REACHED_BAR_HEIGHT, getReachedBarHeight());
        bundle.putFloat(INSTANCE_UNREACHED_BAR_HEIGHT, getUnreachedBarHeight());
        bundle.putInt(INSTANCE_REACHED_BAR_COLOR, getReachedBarColor());
        bundle.putInt(INSTANCE_UNREACHED_BAR_COLOR, getUnreachedBarColor());
        bundle.putInt("max", getMax());
        bundle.putInt("progress", getProgress());
        bundle.putString("suffix", getSuffix());
        bundle.putString("prefix", getPrefix());
        bundle.putBoolean(INSTANCE_TEXT_VISIBILITY, getProgressTextVisibility());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mTextColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            this.mTextSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            this.mReachedBarHeight = bundle.getFloat(INSTANCE_REACHED_BAR_HEIGHT);
            this.mUnreachedBarHeight = bundle.getFloat(INSTANCE_UNREACHED_BAR_HEIGHT);
            this.mReachedBarColor = bundle.getInt(INSTANCE_REACHED_BAR_COLOR);
            this.mUnreachedBarColor = bundle.getInt(INSTANCE_UNREACHED_BAR_COLOR);
            initializePainters();
            setMax(bundle.getInt("max"));
            setProgress(bundle.getInt("progress"));
            setPrefix(bundle.getString("prefix"));
            setSuffix(bundle.getString("suffix"));
            setProgressTextVisibility(bundle.getBoolean(INSTANCE_TEXT_VISIBILITY) ? ProgressTextVisibility.Visible : ProgressTextVisibility.Invisible);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public float dp2px(float f) {
        return (f * getResources().getDisplayMetrics().density) + 0.5f;
    }

    public float sp2px(float f) {
        return f * getResources().getDisplayMetrics().scaledDensity;
    }

    public void setProgressTextVisibility(ProgressTextVisibility progressTextVisibility) {
        this.mIfDrawText = progressTextVisibility == ProgressTextVisibility.Visible;
        invalidate();
    }

    public boolean getProgressTextVisibility() {
        return this.mIfDrawText;
    }

    public void setOnProgressBarListener(OnProgressBarListener onProgressBarListener) {
        this.mListener = onProgressBarListener;
    }
}
