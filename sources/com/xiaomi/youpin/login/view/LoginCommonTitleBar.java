package com.xiaomi.youpin.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.other.common.ConvertUtils;

public class LoginCommonTitleBar extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f23632a;
    private ImageView b;
    private TextView c;
    private TextView d;

    public LoginCommonTitleBar(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public LoginCommonTitleBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginCommonTitleBar(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(@Nullable AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.login_view_common_titlebar, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(-1, ConvertUtils.a(getContext(), 45.0f)));
        this.f23632a = (ImageView) findViewById(R.id.title_bar_back);
        this.b = (ImageView) findViewById(R.id.title_bar_close);
        this.c = (TextView) findViewById(R.id.title_bar_center_text);
        this.d = (TextView) findViewById(R.id.title_bar_right_button);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LoginTitleBar, 0, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.LoginTitleBar_centerText) {
                    setCenterText(obtainStyledAttributes.getString(R.styleable.LoginTitleBar_centerText));
                } else if (index == R.styleable.LoginTitleBar_rightText) {
                    String string = obtainStyledAttributes.getString(R.styleable.LoginTitleBar_rightText);
                    if (TextUtils.isEmpty(string)) {
                        setRightButtonVisible(4);
                    } else {
                        setRightButtonVisible(0);
                        setRightButtonText(string);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.b.setVisibility(0);
        this.b.setOnClickListener(onClickListener);
    }

    public void setOnBackClickListener(View.OnClickListener onClickListener) {
        this.f23632a.setOnClickListener(onClickListener);
    }

    public void setCenterText(String str) {
        this.c.setText(str);
    }

    public void setCenterText(@StringRes int i) {
        this.c.setText(i);
    }

    public void setRightButtonVisible(int i) {
        this.d.setVisibility(i);
    }

    public void setRightButtonText(String str) {
        this.d.setText(str);
    }

    public void setRightButtonText(@StringRes int i) {
        this.d.setText(i);
    }

    public void setOnRightButtonClickListener(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }
}
