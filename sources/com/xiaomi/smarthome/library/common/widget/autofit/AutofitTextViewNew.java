package com.xiaomi.smarthome.library.common.widget.autofit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutofitTextViewNew extends TextView {
    private final AutofitHelper mAutofitHelper = new AutofitHelper(this);

    public AutofitTextViewNew(Context context) {
        super(context);
        this.mAutofitHelper.a((AttributeSet) null, 0);
    }

    public AutofitTextViewNew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAutofitHelper.a(attributeSet, 0);
    }

    public AutofitTextViewNew(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAutofitHelper.a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        if (this.mAutofitHelper != null) {
            this.mAutofitHelper.a(i, f);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
    }
}
