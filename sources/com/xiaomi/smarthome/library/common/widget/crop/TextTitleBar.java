package com.xiaomi.smarthome.library.common.widget.crop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class TextTitleBar extends LinearLayout {
    TextView mLeftBtn = ((TextView) findViewById(R.id.titlebar_left_btn));
    TextView mRightBtn = ((TextView) findViewById(R.id.titlebar_right_text));
    TextView mTitle = ((TextView) findViewById(R.id.titlebar_title));

    public TextTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflate(context, R.layout.text_title_bar_layout, this);
    }

    public void setTitle(int i) {
        this.mTitle.setText(i);
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
    }

    public void setRightText(int i) {
        this.mRightBtn.setText(i);
    }

    public void setRightText(String str) {
        this.mRightBtn.setText(str);
    }

    public void setLeftText(int i) {
        this.mLeftBtn.setText(i);
    }

    public void setLeftText(String str) {
        this.mLeftBtn.setText(str);
    }

    public void setRightBtnOnClickListener(View.OnClickListener onClickListener) {
        this.mRightBtn.setOnClickListener(onClickListener);
    }

    public void setLeftOnClickListener(View.OnClickListener onClickListener) {
        this.mLeftBtn.setOnClickListener(onClickListener);
    }

    public TextView getLeftView() {
        return this.mLeftBtn;
    }

    public TextView getTitleView() {
        return this.mTitle;
    }

    public TextView getRightView() {
        return this.mRightBtn;
    }
}
