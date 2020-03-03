package com.xiaomi.smarthome.operation.js_sdk.base;

public interface CommonWebViewFragmentBridge {
    void onBackButtonClick();

    void popWindow(String str);

    void pushWindow(String str);
}
