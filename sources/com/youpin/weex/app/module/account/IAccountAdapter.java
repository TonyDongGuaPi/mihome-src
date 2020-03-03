package com.youpin.weex.app.module.account;

import com.taobao.weex.bridge.JSCallback;

public interface IAccountAdapter {
    void appendUserAgent(String str);

    void getUserAgent(JSCallback jSCallback);

    void getUserInfo(JSCallback jSCallback);

    void openLoginPage();

    @Deprecated
    void setUserAgent(String str);

    void updateLoginInfo(String str, String str2, boolean z);
}
