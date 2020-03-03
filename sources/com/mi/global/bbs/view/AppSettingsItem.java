package com.mi.global.bbs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;

public class AppSettingsItem extends LinearLayout {
    private ImageView mImageView;
    private ImageView mSubImageView;
    private TextView mSubTextView;
    private TextView mTitleTextView;

    public AppSettingsItem(Context context) {
        super(context);
        init(context);
    }

    public AppSettingsItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppSettingsItem);
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.AppSettingsItem_leftIcon) {
                this.mImageView.setImageDrawable(obtainStyledAttributes.getDrawable(index));
            } else if (index == R.styleable.AppSettingsItem_rightIcon) {
                this.mSubImageView.setImageDrawable(obtainStyledAttributes.getDrawable(index));
            } else if (index == R.styleable.AppSettingsItem_text) {
                this.mTitleTextView.setText(obtainStyledAttributes.getString(index));
            } else if (index == R.styleable.AppSettingsItem_subText) {
                this.mSubTextView.setText(obtainStyledAttributes.getString(index));
            } else if (index == R.styleable.AppSettingsItem_rightIconVisibility) {
                this.mSubImageView.setVisibility(obtainStyledAttributes.getBoolean(index, true) ? 0 : 8);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public AppSettingsItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public AppSettingsItem(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    private void init(Context context) {
        setOrientation(0);
        setBackgroundResource(R.drawable.white_pressed_selector);
        setGravity(16);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.settings_margin_lr);
        LayoutInflater.from(context).inflate(R.layout.bbs_app_setting_item, this, true);
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.mTitleTextView = (TextView) findViewById(R.id.app_settings_item_text);
        this.mImageView = (ImageView) findViewById(R.id.app_settings_item_icon);
        this.mSubImageView = (ImageView) findViewById(R.id.app_settings_item_icon_indicate);
        int i = Build.VERSION.SDK_INT;
        this.mSubTextView = (TextView) findViewById(R.id.app_settings_item_sub_text);
    }

    public void setImageDrawable(Drawable drawable) {
        this.mImageView.setImageDrawable(drawable);
    }

    public void setImageResource(@DrawableRes int i) {
        this.mImageView.setImageResource(i);
    }

    public void setSubImageDrawable(Drawable drawable) {
        this.mSubImageView.setImageDrawable(drawable);
    }

    public void setSubImageResource(@DrawableRes int i) {
        this.mSubImageView.setImageResource(i);
    }

    public void setText(CharSequence charSequence) {
        this.mTitleTextView.setText(charSequence);
    }

    public void setText(@StringRes int i) {
        this.mTitleTextView.setText(i);
    }

    public void setSubText(CharSequence charSequence) {
        this.mSubTextView.setText(charSequence);
    }

    public void setSubText(@StringRes int i) {
        this.mSubTextView.setText(i);
    }

    public void setTextColor(@ColorRes int i) {
        this.mTitleTextView.setTextColor(getResources().getColor(i));
    }

    public void setSubTextColor(@ColorRes int i) {
        this.mSubTextView.setTextColor(getResources().getColor(i));
    }

    public TextView getTitleView() {
        return this.mTitleTextView;
    }

    public TextView getSubTextView() {
        return this.mSubTextView;
    }
}
