package com.facebook.react.uimanager;

import android.support.annotation.Nullable;
import android.view.View;

public interface ViewManagerDelegate<T extends View> {
    void setProperty(T t, String str, @Nullable Object obj);
}
