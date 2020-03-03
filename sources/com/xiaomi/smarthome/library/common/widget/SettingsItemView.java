package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class SettingsItemView extends FrameLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    View mContainerView;
    TextView mInfoTextView;
    CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;
    View.OnClickListener mOnClickListener;
    OnSelectedListener mOnSelectedListener;
    ImageView mOnclickImageView;
    ImageView mSelectImageView;
    boolean mSelected;
    TextView mSubTitleTextView;
    SwitchButton mSwitchButton;
    View mTitleContainer;
    TextView mTitleTextView;
    int mType;

    public interface OnSelectedListener {
        void a(View view);
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.mOnSelectedListener = onSelectedListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setChecked(boolean z) {
        if (this.mSwitchButton != null) {
            this.mSwitchButton.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.mSwitchButton.setChecked(z);
            this.mSwitchButton.setOnCheckedChangeListener(this);
        }
    }

    public boolean isChecked() {
        if (this.mSwitchButton != null) {
            return this.mSwitchButton.isChecked();
        }
        return false;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mContainerView.setEnabled(z);
    }

    public TextView getInfoView() {
        return this.mInfoTextView;
    }

    public SettingsItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingsItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelected = false;
        View inflate = LayoutInflater.from(context).inflate(R.layout.settings_item, (ViewGroup) null);
        this.mContainerView = inflate;
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
        inflate.setBackgroundDrawable(getBackground());
        this.mTitleTextView = (TextView) inflate.findViewById(R.id.settings_item_title);
        this.mSubTitleTextView = (TextView) inflate.findViewById(R.id.settings_item_sub_title);
        this.mSwitchButton = (SwitchButton) inflate.findViewById(R.id.settings_item_switch_btn);
        this.mOnclickImageView = (ImageView) inflate.findViewById(R.id.settings_item_arrow);
        this.mInfoTextView = (TextView) inflate.findViewById(R.id.settings_item_info);
        this.mSelectImageView = (ImageView) inflate.findViewById(R.id.settings_item_select);
        this.mTitleContainer = inflate.findViewById(R.id.title_container);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingsItem, 0, 0);
        String string = obtainStyledAttributes.getString(12);
        String string2 = obtainStyledAttributes.getString(10);
        String string3 = obtainStyledAttributes.getString(3);
        this.mType = obtainStyledAttributes.getInt(15, 1);
        this.mSelected = obtainStyledAttributes.getBoolean(9, false);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        obtainStyledAttributes.recycle();
        setTitle(string);
        setSubTitle(string2);
        setInfo(string3);
        setType(this.mType);
        View view = new View(getContext());
        view.setBackgroundColor(-1710619);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, 1);
        layoutParams.gravity = 80;
        layoutParams.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
        addView(view, layoutParams);
    }

    public void setTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    public String getTitle() {
        return this.mTitleTextView.getText().toString();
    }

    public void setSubTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mSubTitleTextView.setVisibility(8);
            return;
        }
        this.mSubTitleTextView.setText(str);
        this.mSubTitleTextView.setVisibility(0);
    }

    public void setInfo(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mInfoTextView.setText(str);
            this.mInfoTextView.setVisibility(0);
            return;
        }
        this.mInfoTextView.setVisibility(8);
    }

    public void setSelect(boolean z) {
        this.mSelected = z;
        if (z) {
            this.mSelectImageView.setVisibility(0);
            this.mTitleTextView.setTextColor(getResources().getColor(R.color.std_word_008));
            return;
        }
        this.mTitleTextView.setTextColor(getResources().getColor(R.color.black_80_transparent));
        this.mSelectImageView.setVisibility(4);
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setType(int i) {
        this.mType = i;
        if (this.mType == 0) {
            this.mSwitchButton.setVisibility(8);
            this.mOnclickImageView.setVisibility(8);
            this.mSelectImageView.setVisibility(8);
            this.mContainerView.setOnClickListener(this);
        } else if (this.mType == 1) {
            this.mSwitchButton.setVisibility(8);
            this.mContainerView.setOnClickListener(this);
            this.mSelectImageView.setVisibility(8);
        } else if (this.mType == 2) {
            this.mOnclickImageView.setVisibility(8);
            this.mSwitchButton.setOnCheckedChangeListener(this);
            this.mSelectImageView.setVisibility(8);
        } else if (this.mType == 3) {
            this.mSwitchButton.setVisibility(8);
            this.mOnclickImageView.setVisibility(8);
            this.mContainerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!SettingsItemView.this.mSelected) {
                        SettingsItemView.this.mSelected = true;
                        SettingsItemView.this.setSelect(SettingsItemView.this.mSelected);
                        if (SettingsItemView.this.mOnSelectedListener != null) {
                            SettingsItemView.this.mOnSelectedListener.a(SettingsItemView.this);
                            return;
                        }
                        return;
                    }
                    SettingsItemView.this.mSelected = false;
                    SettingsItemView.this.setSelect(SettingsItemView.this.mSelected);
                    if (SettingsItemView.this.mOnSelectedListener != null) {
                        SettingsItemView.this.mOnSelectedListener.a(SettingsItemView.this);
                    }
                }
            });
            setSelect(this.mSelected);
        }
    }

    public void onClick(View view) {
        if (this.mOnClickListener != null) {
            this.mOnClickListener.onClick(view);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.mOnCheckedChangeListener != null) {
            this.mOnCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }

    public void setSwitchEnable(boolean z) {
        if (this.mSwitchButton != null) {
            this.mSwitchButton.setEnabled(z);
        }
    }
}
