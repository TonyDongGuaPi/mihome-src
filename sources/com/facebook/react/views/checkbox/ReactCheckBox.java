package com.facebook.react.views.checkbox;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;

class ReactCheckBox extends AppCompatCheckBox {
    private boolean mAllowChange = true;

    public ReactCheckBox(Context context) {
        super(context);
    }

    public void setChecked(boolean z) {
        if (this.mAllowChange) {
            this.mAllowChange = false;
            super.setChecked(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
        }
        this.mAllowChange = true;
    }
}
