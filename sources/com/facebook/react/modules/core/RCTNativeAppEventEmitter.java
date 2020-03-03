package com.facebook.react.modules.core;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.JavaScriptModule;

public interface RCTNativeAppEventEmitter extends JavaScriptModule {
    void emit(String str, @Nullable Object obj);
}
