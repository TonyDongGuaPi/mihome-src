package com.facebook.react.touch;

import android.graphics.Rect;
import android.support.annotation.Nullable;

public interface ReactHitSlopView {
    @Nullable
    Rect getHitSlopRect();
}
