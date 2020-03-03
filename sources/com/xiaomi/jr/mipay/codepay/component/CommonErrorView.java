package com.xiaomi.jr.mipay.codepay.component;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.jr.mipay.codepay.R;

public class CommonErrorView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private ProgressBar f10891a;
    private TextView b;
    private Button c;
    private String d;
    private boolean e;

    public CommonErrorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.jr_mipay_code_pay_common_error_view, this);
        this.f10891a = (ProgressBar) findViewById(R.id.progress);
        this.b = (TextView) findViewById(R.id.summary);
        this.c = (Button) findViewById(R.id.button_retry);
        setShowRetryButton(false);
    }

    public void setShowRetryButton(boolean z) {
        this.e = z;
        this.c.setVisibility(z ? 0 : 8);
    }

    public void showError(String str) {
        showError(str, (View.OnClickListener) null);
    }

    public void showError(String str, View.OnClickListener onClickListener) {
        if (onClickListener == null || !this.e) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.c.setOnClickListener(onClickListener);
        }
        this.f10891a.setVisibility(8);
        this.b.setText(str);
        setVisibility(0);
    }

    public void setLoadingString(String str) {
        this.d = str;
    }

    public void startProgress() {
        this.f10891a.setVisibility(0);
        this.b.setText(TextUtils.isEmpty(this.d) ? getResources().getString(R.string.jr_mipay_loading) : this.d);
        this.c.setVisibility(8);
    }

    public void stopProgress() {
        this.f10891a.setVisibility(8);
        this.b.setText("");
    }

    private void setProgressDrawable(AnimationDrawable animationDrawable) {
        if (animationDrawable != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f10891a.getLayoutParams();
            layoutParams.width = animationDrawable.getIntrinsicWidth();
            layoutParams.height = animationDrawable.getIntrinsicHeight();
            this.f10891a.setLayoutParams(layoutParams);
            this.f10891a.setIndeterminateDrawable(animationDrawable);
        }
    }
}
