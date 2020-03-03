package com.taobao.weex.ui.flat;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import com.taobao.weex.ui.flat.widget.Widget;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public interface FlatComponent<T extends Widget> {
    @NonNull
    T getOrCreateFlatWidget();

    boolean promoteToView(boolean z);
}
