package com.xiaomi.smarthome.frame.login.ui.view;

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
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class LoginBaseTitleBar extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f16305a;
    private TextView b;
    private TextView c;

    public LoginBaseTitleBar(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public LoginBaseTitleBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginBaseTitleBar(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(@Nullable AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.smarthome_login_view_titlebar, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(-1, DisplayUtils.d(getContext(), 45.0f)));
        this.f16305a = (ImageView) findViewById(R.id.title_bar_back);
        this.b = (TextView) findViewById(R.id.title_bar_center_text);
        this.c = (TextView) findViewById(R.id.title_bar_right_button);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.xiaomi.smarthome.sdk.R.styleable.LoginBaseTitleBar, 0, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 0) {
                    setCenterText(obtainStyledAttributes.getString(0));
                } else if (index == 1) {
                    String string = obtainStyledAttributes.getString(1);
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

    public void setOnBackClickListener(View.OnClickListener onClickListener) {
        this.f16305a.setOnClickListener(onClickListener);
    }

    public void setCenterText(String str) {
        this.b.setText(str);
    }

    public void setCenterText(@StringRes int i) {
        this.b.setText(i);
    }

    public void setRightButtonVisible(int i) {
        this.c.setVisibility(i);
    }

    public void setRightButtonText(String str) {
        this.c.setText(str);
    }

    public void setRightButtonText(@StringRes int i) {
        this.c.setText(i);
    }

    public void setOnRightButtonClickListener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }
}
