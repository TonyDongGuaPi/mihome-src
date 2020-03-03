package com.xiaomi.jr.mipay.pay.verifier.component;

import android.content.Context;
import android.widget.TextView;
import com.xiaomi.jr.mipay.pay.verifier.R;

public class ShortNumberEditTextItem extends TextView {
    private int[] mDrawableState;

    public ShortNumberEditTextItem(Context context) {
        super(context);
    }

    public void setBackground(int i, int i2, int i3) {
        setBackgroundResource(i);
        if (i3 <= 1) {
            this.mDrawableState = new int[]{R.attr.state_single_h};
        } else if (i2 == 0) {
            this.mDrawableState = new int[]{R.attr.state_first_h};
        } else if (i2 == i3 - 1) {
            this.mDrawableState = new int[]{R.attr.state_last_h};
        } else {
            this.mDrawableState = new int[]{R.attr.state_middle_h};
        }
        refreshDrawableState();
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.mDrawableState == null || this.mDrawableState.length <= 0) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + this.mDrawableState.length);
        mergeDrawableStates(onCreateDrawableState, this.mDrawableState);
        return onCreateDrawableState;
    }
}
