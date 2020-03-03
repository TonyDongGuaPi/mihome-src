package com.xiaomi.miot.store.component.viewOverflow;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.views.view.ReactViewGroup;

public class RNViewOverflowLayout extends ReactViewGroup {
    public RNViewOverflowLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        try {
            ((ViewGroup) getParent()).setClipChildren(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
