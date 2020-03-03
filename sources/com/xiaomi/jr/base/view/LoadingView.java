package com.xiaomi.jr.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.jr.base.R;

public class LoadingView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f10344a;

    public LoadingView(Context context) {
        super(context);
        a(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.f10344a = (TextView) LayoutInflater.from(context).inflate(R.layout.mifi_loading, this, true).findViewById(R.id.loading_text);
    }

    public void setLoadingText(String str) {
        if (this.f10344a != null) {
            this.f10344a.setText(str);
        }
    }
}
