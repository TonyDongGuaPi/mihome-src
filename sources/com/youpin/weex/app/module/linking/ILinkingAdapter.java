package com.youpin.weex.app.module.linking;

import com.taobao.weex.bridge.JSCallback;

public interface ILinkingAdapter {
    void canOpenURL(String str, JSCallback jSCallback);

    void openURL(String str, JSCallback jSCallback);
}
