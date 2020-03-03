package com.xiaomi.jr.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.jr.ui.R;

public class ActionBarEndView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f11037a;
    private ImageView b;
    private ImageView c;

    public ActionBarEndView(Context context) {
        super(context);
    }

    public ActionBarEndView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActionBarEndView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f11037a = (TextView) findViewById(R.id.action_text);
        this.f11037a.setVisibility(8);
        this.b = (ImageView) findViewById(R.id.icon1);
        this.b.setVisibility(8);
        this.c = (ImageView) findViewById(R.id.icon2);
        this.c.setVisibility(8);
    }

    public void setIcon(int i, View.OnClickListener onClickListener) {
        this.f11037a.setVisibility(8);
        this.c.setVisibility(8);
        if (i != 0) {
            this.b.setVisibility(0);
            this.b.setImageResource(i);
            this.b.setOnClickListener(onClickListener);
            return;
        }
        this.b.setVisibility(8);
    }

    public void setIcon(int i, View.OnClickListener onClickListener, int i2, View.OnClickListener onClickListener2) {
        if (i == 0 || i2 == 0) {
            if (i != 0) {
                i2 = i;
            }
            if (i == 0) {
                onClickListener = onClickListener2;
            }
            setIcon(i2, onClickListener);
            return;
        }
        this.f11037a.setVisibility(8);
        this.b.setVisibility(0);
        this.c.setVisibility(0);
        this.b.setImageResource(i);
        this.b.setOnClickListener(onClickListener);
        this.c.setImageResource(i2);
        this.c.setOnClickListener(onClickListener2);
    }

    public void setText(String str, View.OnClickListener onClickListener) {
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        if (str != null) {
            this.f11037a.setVisibility(0);
            this.f11037a.setText(str);
            this.f11037a.setOnClickListener(onClickListener);
            return;
        }
        this.f11037a.setVisibility(8);
    }
}
