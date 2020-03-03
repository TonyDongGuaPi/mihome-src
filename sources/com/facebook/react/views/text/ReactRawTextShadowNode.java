package com.facebook.react.views.text;

import android.support.annotation.Nullable;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.taobao.weex.el.parse.Operators;

public class ReactRawTextShadowNode extends ReactShadowNodeImpl {
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    @Nullable
    private String mText = null;

    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String str) {
        this.mText = str;
        markUpdated();
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    public String toString() {
        return getViewClass() + " [text: " + this.mText + Operators.ARRAY_END_STR;
    }
}
