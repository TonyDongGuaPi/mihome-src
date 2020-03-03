package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.mishopsdk.util.DensityUtil;

public class BadgeView extends TextView {
    private static final int DEFAULT_CORNER_RADIUS_DIP = 90;
    private int mBadgeColor = Color.parseColor("#FFFF6B00");
    private float mCounterHeight = 12.0f;

    public BadgeView(Context context) {
        super(context);
    }

    public BadgeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    private Drawable getDefaultBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.mBadgeColor);
        gradientDrawable.setCornerRadius(90.0f);
        return gradientDrawable;
    }

    public Drawable getDefaultBackground(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(90.0f);
        return gradientDrawable;
    }

    private Drawable getOvalDrawable() {
        OvalShape ovalShape = new OvalShape();
        ovalShape.resize((float) DensityUtil.dip2px(this.mCounterHeight), (float) DensityUtil.dip2px(this.mCounterHeight));
        ShapeDrawable shapeDrawable = new ShapeDrawable(ovalShape);
        shapeDrawable.getPaint().setColor(this.mBadgeColor);
        shapeDrawable.setIntrinsicHeight(DensityUtil.dip2px(this.mCounterHeight));
        shapeDrawable.setIntrinsicWidth(DensityUtil.dip2px(this.mCounterHeight));
        return shapeDrawable;
    }

    public Drawable getOvalDrawable(int i) {
        OvalShape ovalShape = new OvalShape();
        ovalShape.resize((float) DensityUtil.dip2px(this.mCounterHeight), (float) DensityUtil.dip2px(this.mCounterHeight));
        ShapeDrawable shapeDrawable = new ShapeDrawable(ovalShape);
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.setIntrinsicHeight(DensityUtil.dip2px(this.mCounterHeight));
        shapeDrawable.setIntrinsicWidth(DensityUtil.dip2px(this.mCounterHeight));
        return shapeDrawable;
    }

    public void setCount(int i) {
        if (i > 0) {
            if (i < 10) {
                setBackgroundDrawable(getOvalDrawable());
                setText(String.valueOf(i));
            } else if (i > 99) {
                setBackgroundDrawable(getDefaultBackground());
                setText("99+");
            } else {
                setBackgroundDrawable(getDefaultBackground());
                setText(String.valueOf(i));
            }
            setVisibility(0);
            return;
        }
        setVisibility(8);
    }

    public void setCount(int i, int i2) {
        if (i > 0) {
            if (i < 10) {
                setBackgroundDrawable(getDefaultBackground(i2));
                setText(String.valueOf(i));
            } else if (i > 99) {
                setBackgroundDrawable(getDefaultBackground(i2));
                setText("99+");
            } else {
                setBackgroundDrawable(getDefaultBackground(i2));
                setText(String.valueOf(i));
            }
            setVisibility(0);
            return;
        }
        setVisibility(8);
    }
}
