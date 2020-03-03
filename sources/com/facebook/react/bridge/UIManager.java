package com.facebook.react.bridge;

import android.support.annotation.Nullable;
import android.view.View;

public interface UIManager extends JSIModule, PerformanceCounter {
    <T extends View> int addRootView(T t, WritableMap writableMap, @Nullable String str);

    void dispatchCommand(int i, int i2, @Nullable ReadableArray readableArray);

    void dispatchCommand(int i, String str, @Nullable ReadableArray readableArray);

    void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap);

    void updateRootLayoutSpecs(int i, int i2, int i3);
}
