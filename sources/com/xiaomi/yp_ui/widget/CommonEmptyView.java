package com.xiaomi.yp_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.yp_ui.R;

public class CommonEmptyView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f1600a;
    private TextView b;
    private TextView c;

    public CommonEmptyView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonEmptyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.yp_common_empty, this, true);
        this.b = (TextView) findViewById(R.id.common_empty_main);
        this.f1600a = (ImageView) findViewById(R.id.common_empty_img);
        this.c = (TextView) findViewById(R.id.common_empty_btn);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.common_empty_background_color));
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CommonEmptyView);
            String string = obtainStyledAttributes.getString(R.styleable.CommonEmptyView_mainContent);
            String string2 = obtainStyledAttributes.getString(R.styleable.CommonEmptyView_buttonContent);
            setImage(obtainStyledAttributes.getResourceId(R.styleable.CommonEmptyView_img, R.drawable.yp_empty));
            setMainContent(string);
            if (TextUtils.isEmpty(string2)) {
                setButtonVisibility(8);
            } else {
                setButtonText(string2);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setImage(@DrawableRes int i) {
        this.f1600a.setImageResource(i);
    }

    public void setMainContent(String str) {
        this.b.setText(str);
    }

    public void setButtonText(String str) {
        this.c.setText(str);
        this.c.setVisibility(0);
    }

    public void setButtonVisibility(int i) {
        this.c.setVisibility(i);
    }

    public void setOnButtonClickListener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }
}
