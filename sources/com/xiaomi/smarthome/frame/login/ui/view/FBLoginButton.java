package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.login.widget.LoginButton;

public class FBLoginButton extends LoginButton {
    public FBLoginButton(Context context) {
        super(context);
    }

    public FBLoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FBLoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        setInternalOnClickListener((View.OnClickListener) null);
    }
}
