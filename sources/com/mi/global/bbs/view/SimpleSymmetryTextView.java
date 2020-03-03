package com.mi.global.bbs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.utils.TextHelper;

public class SimpleSymmetryTextView extends LinearLayout {
    final int DEFAULT_LEFT_TEXT_COLOR;
    final int DEFAULT_RIGHT_TEXT_COLOR;
    @BindView(2131493500)
    TextView itemLeftText;
    @BindView(2131493509)
    TextView itemRightText;

    public SimpleSymmetryTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SimpleSymmetryTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_LEFT_TEXT_COLOR = Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR);
        this.DEFAULT_RIGHT_TEXT_COLOR = Color.parseColor("#333333");
        setOrientation(0);
        setBackgroundColor(-1);
        LayoutInflater.from(context).inflate(R.layout.bbs_simple_text_layout, this, true);
        ButterKnife.bind((View) this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleSymmetryTextView);
        if (obtainStyledAttributes != null) {
            for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.SimpleSymmetryTextView_leftText) {
                    this.itemLeftText.setText(obtainStyledAttributes.getString(index));
                } else if (index == R.styleable.SimpleSymmetryTextView_leftTextColor) {
                    this.itemLeftText.setTextColor(obtainStyledAttributes.getColor(index, this.DEFAULT_LEFT_TEXT_COLOR));
                } else if (index == R.styleable.SimpleSymmetryTextView_rightText) {
                    this.itemRightText.setText(obtainStyledAttributes.getString(index));
                } else if (index == R.styleable.SimpleSymmetryTextView_rightTextColor) {
                    this.itemRightText.setTextColor(obtainStyledAttributes.getColor(index, this.DEFAULT_RIGHT_TEXT_COLOR));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setLeftText(String str) {
        this.itemLeftText.setText(str);
    }

    public void setLeftText(@StringRes int i) {
        this.itemLeftText.setText(i);
    }

    public void setRightText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.itemRightText.setText(str);
        }
    }

    public void setRightDefaultText(String str) {
        TextHelper.setText(this.itemRightText, str, getResources().getString(R.string.to_be_completed));
    }

    public void setRightText(@StringRes int i) {
        this.itemRightText.setText(i);
    }
}
