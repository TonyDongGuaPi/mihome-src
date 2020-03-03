package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class SingleCheckLinearLayout extends LinearLayout implements Checkable {
    Context mContext;
    View mSelectImageIconView;
    TextView mTextView;

    public void toggle() {
    }

    public SingleCheckLinearLayout(Context context) {
        super(context);
    }

    public SingleCheckLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSelectImageIconView = findViewById(R.id.select_icon);
        this.mTextView = (TextView) findViewById(R.id.text1);
    }

    public void setChecked(boolean z) {
        if (z) {
            this.mSelectImageIconView.setVisibility(0);
        } else {
            this.mSelectImageIconView.setVisibility(4);
        }
        this.mTextView.setSelected(z);
    }

    public boolean isChecked() {
        return this.mTextView.isSelected();
    }
}
