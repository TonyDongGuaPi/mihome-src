package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomiyoupin.toast.R;

public class SingleCheckLinearLayout extends LinearLayout implements Checkable {
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
        this.mTextView = (TextView) findViewById(R.id.text1);
    }

    public void setChecked(boolean z) {
        this.mTextView.setSelected(z);
    }

    public boolean isChecked() {
        return this.mTextView.isSelected();
    }
}
