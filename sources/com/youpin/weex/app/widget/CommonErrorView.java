package com.youpin.weex.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youpin.weex.app.R;

public class CommonErrorView extends RelativeLayout implements View.OnClickListener {
    static final int ERROR_NO_PERMISSION = 4014004;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f2550a;
    private TextView b;
    private TextView c;
    private View.OnClickListener d;

    public CommonErrorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.common_page_error, this, true);
        this.f2550a = (ImageView) findViewById(R.id.common_error_img);
        this.b = (TextView) findViewById(R.id.common_error_main);
        this.c = (TextView) findViewById(R.id.common_error_retry);
        setBackgroundColor(-328964);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CommonErrorView);
            setImg(obtainStyledAttributes.getResourceId(R.styleable.CommonErrorView_errorImg, R.drawable.common_error_network_unavailable));
            String string = obtainStyledAttributes.getString(R.styleable.CommonErrorView_errorMainContent);
            String string2 = obtainStyledAttributes.getString(R.styleable.CommonErrorView_errorButtonContent);
            ((RelativeLayout.LayoutParams) this.f2550a.getLayoutParams()).setMargins(0, obtainStyledAttributes.getDimensionPixelSize(R.styleable.CommonErrorView_imgMarginTop, getResources().getDimensionPixelSize(R.dimen.error_view_margin_top)), 0, 0);
            if (!TextUtils.isEmpty(string)) {
                setMainContent(string);
            }
            if (!TextUtils.isEmpty(string2)) {
                setButtonText(string2);
            }
            obtainStyledAttributes.recycle();
        }
        this.c.setOnClickListener(this);
        setClickable(true);
        setFocusableInTouchMode(true);
        setSoundEffectsEnabled(false);
    }

    public void setImg(@DrawableRes int i) {
        this.f2550a.setImageResource(i);
    }

    public void setMainContent(String str) {
        this.b.setText(str);
    }

    public void setButtonText(String str) {
        this.c.setText(str);
    }

    public void setOnRetryClickListener(View.OnClickListener onClickListener) {
        this.d = onClickListener;
    }

    public void onClick(View view) {
        if (R.id.common_error_retry == view.getId() && this.d != null) {
            this.d.onClick(view);
        }
    }

    public void setErrorInfo(int i, String str) {
        if (i == ERROR_NO_PERMISSION) {
            this.b.setText(R.string.common_error_no_permission);
            this.c.setVisibility(4);
            return;
        }
        this.b.setText(R.string.common_error_hint);
        this.c.setVisibility(0);
    }
}
