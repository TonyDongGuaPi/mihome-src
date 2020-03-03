package com.xiaomi.smarthome.library.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.xiaomi.smarthome.R;

public class ColorRadioButton extends RadioButton {
    Drawable mAddNewDrawable;
    Drawable mBgDrawable;
    int mBgPadding;
    int mOvalColor;
    Paint mOvalPaint;
    RectF mOvalRect;

    public ColorRadioButton(Context context) {
        super(context);
        init();
    }

    public ColorRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ColorRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setIncludeFontPadding(false);
        initPaint();
        this.mOvalColor = -16777216;
        this.mOvalPaint.setColor(this.mOvalColor);
        this.mOvalRect = new RectF();
        this.mAddNewDrawable = getResources().getDrawable(R.drawable.yeelight_default_color_add);
        this.mBgDrawable = getResources().getDrawable(R.drawable.yeelight_default_color_bg);
        this.mBgPadding = getResources().getDimensionPixelSize(R.dimen.yeelight_radiobutton_bg_padding);
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void onFinishInflate() {
        super.onFinishInflate();
        initPaint();
        this.mOvalColor = getLinkTextColors().getDefaultColor();
        this.mOvalPaint.setColor(this.mOvalColor);
    }

    public void initPaint() {
        if (this.mOvalPaint == null) {
            this.mOvalPaint = new Paint(1);
            this.mOvalPaint.setStyle(Paint.Style.FILL);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        initPaint();
        this.mOvalColor = getLinkTextColors().getDefaultColor();
        this.mOvalPaint.setColor(this.mOvalColor);
        if (this.mOvalColor == -16777216) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            this.mAddNewDrawable.setBounds(paddingLeft, paddingTop, width, width);
            this.mAddNewDrawable.draw(canvas);
        } else {
            this.mOvalRect.left = (float) getPaddingLeft();
            this.mOvalRect.top = (float) getPaddingTop();
            this.mOvalRect.right = (float) (getWidth() - getPaddingRight());
            this.mOvalRect.bottom = this.mOvalRect.right;
            canvas.drawOval(this.mOvalRect, this.mOvalPaint);
        }
        if (isChecked() && this.mOvalColor != -16777216) {
            this.mBgDrawable.setBounds(this.mBgPadding, this.mBgPadding, getWidth() - this.mBgPadding, getWidth() - this.mBgPadding);
            this.mBgDrawable.draw(canvas);
            int paddingLeft2 = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop2 = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            setPadding(0, paddingTop2, 0, 0);
            super.onDraw(canvas);
            setPadding(paddingLeft2, paddingTop2, paddingRight, paddingBottom);
        }
    }
}
