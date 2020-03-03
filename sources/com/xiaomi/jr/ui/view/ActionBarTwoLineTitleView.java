package com.xiaomi.jr.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.jr.ui.R;

public class ActionBarTwoLineTitleView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static float f11038a;
    private static float b;
    private static float c;
    private TextView d;
    private TextView e;

    public ActionBarTwoLineTitleView(Context context) {
        super(context);
    }

    public ActionBarTwoLineTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActionBarTwoLineTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (f11038a == 0.0f) {
            f11038a = getContext().getResources().getDimension(R.dimen.two_line_main_title_text_size);
        }
        if (b == 0.0f) {
            b = getContext().getResources().getDimension(R.dimen.two_line_sub_title_text_size);
        }
        if (c == 0.0f) {
            c = getContext().getResources().getDimension(R.dimen.one_line_main_title_text_size);
        }
        this.d = (TextView) findViewById(R.id.main_title);
        this.e = (TextView) findViewById(R.id.sub_title);
    }

    public void setTitle(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence)) {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
        } else if (TextUtils.isEmpty(charSequence2)) {
            this.d.setVisibility(0);
            this.e.setVisibility(8);
            this.d.setTextSize(0, c);
            this.d.setText(charSequence);
        } else {
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            this.d.setTextSize(0, f11038a);
            this.d.setText(charSequence);
            this.e.setTextSize(0, b);
            this.e.setText(charSequence2);
        }
    }
}
