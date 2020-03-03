package com.mi.global.shop.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mi.global.shop.R;
import com.mi.global.shop.util.FontUtil;

public class CommonButton extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7152a = "CommonButton";
    private CustomTextView b;
    private ViewGroup c;

    public CommonButton(Context context) {
        super(context);
        a(context, (AttributeSet) null, 0);
    }

    public CommonButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public CommonButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, AttributeSet attributeSet, int i) {
        int i2;
        setPadding(0, 0, 0, 0);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.shop_layout_common_button, (ViewGroup) null, false);
        this.b = (CustomTextView) inflate.findViewById(R.id.common_button_middletext);
        this.c = (ViewGroup) inflate.findViewById(R.id.common_button_rootview);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.v6CommonButton, i, 0);
        try {
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_android_textColor))) {
                this.b.setTextColor(obtainStyledAttributes.getColor(R.styleable.v6CommonButton_android_textColor, Color.rgb(255, 255, 255)));
            }
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_android_text))) {
                this.b.setText(obtainStyledAttributes.getString(R.styleable.v6CommonButton_android_text).toUpperCase());
            }
            float f = 14.0f;
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_android_textSize))) {
                f = obtainStyledAttributes.getDimension(R.styleable.v6CommonButton_android_textSize, 14.0f);
            }
            this.b.setTextSize(0, f);
            GradientDrawable gradientDrawable = new GradientDrawable();
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            GradientDrawable gradientDrawable3 = new GradientDrawable();
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_stroke_width))) {
                obtainStyledAttributes.getDimensionPixelOffset(R.styleable.v6CommonButton_cb_stroke_width, 0);
                obtainStyledAttributes.getDimension(R.styleable.v6CommonButton_cb_stroke_width, (float) 0);
                i2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.v6CommonButton_cb_stroke_width, 0);
            } else {
                i2 = 0;
            }
            if (i2 > 0) {
                if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_strokecolor_nomal))) {
                    gradientDrawable.setStroke(i2, obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_strokecolor_nomal, 0));
                }
                if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_strokecolor_pressed))) {
                    gradientDrawable2.setStroke(i2, obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_strokecolor_pressed, 0));
                }
                if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_strokecolor_disabled))) {
                    gradientDrawable3.setStroke(i2, obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_strokecolor_disabled, 0));
                }
            }
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_bgcolor_nomal))) {
                gradientDrawable.setColor(obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_bgcolor_nomal, 0));
            }
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_bgcolor_pressed))) {
                gradientDrawable2.setColor(obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_bgcolor_pressed, 0));
            }
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_bgcolor_disabled))) {
                gradientDrawable3.setColor(obtainStyledAttributes.getColor(R.styleable.v6CommonButton_cb_bgcolor_disabled, 0));
            }
            if (!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_corners_radius))) {
                gradientDrawable.setCornerRadius((float) obtainStyledAttributes.getDimensionPixelOffset(R.styleable.v6CommonButton_cb_corners_radius, 0));
                gradientDrawable2.setCornerRadius((float) obtainStyledAttributes.getDimensionPixelOffset(R.styleable.v6CommonButton_cb_corners_radius, 0));
                gradientDrawable3.setCornerRadius((float) obtainStyledAttributes.getDimensionPixelOffset(R.styleable.v6CommonButton_cb_corners_radius, 0));
            } else {
                boolean z = !TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_corners_left_radius));
                boolean z2 = !TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_cb_corners_right_radius));
                if (!z || !z2) {
                    throw new RuntimeException("Must be set Radius, just add cb_corners_radius or add cb_corners_left_radius and cb_corners_right_radius");
                }
                float dimensionPixelSize = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.v6CommonButton_cb_corners_left_radius, 0);
                float dimensionPixelSize2 = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.v6CommonButton_cb_corners_right_radius, 0);
                float[] fArr = {dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize};
                gradientDrawable.setCornerRadii(fArr);
                gradientDrawable2.setCornerRadii(fArr);
                gradientDrawable3.setCornerRadii(fArr);
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
            stateListDrawable.addState(new int[]{-16842910}, gradientDrawable3);
            stateListDrawable.addState(new int[0], gradientDrawable);
            if (Build.VERSION.SDK_INT >= 16) {
                this.c.setBackground(stateListDrawable);
            } else {
                this.c.setBackgroundDrawable(stateListDrawable);
            }
            FontUtil.a(this.b, context);
            setEnabled(!TextUtils.isEmpty(obtainStyledAttributes.getString(R.styleable.v6CommonButton_android_enabled)) ? obtainStyledAttributes.getBoolean(R.styleable.v6CommonButton_android_enabled, true) : true);
            setClickable(true);
            addView(inflate);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public CharSequence getText() {
        return this.b.getText();
    }

    public void setTextColor(int i) {
        this.b.setTextColor(i);
    }

    public void setText(String str) {
        this.b.setText(str);
    }

    public void setText(int i) {
        this.b.setText(i);
    }
}
