package com.mi.global.bbs.manager;

public interface SdkListener {
    void onNeedLogin(String str);

    void onTokenExpired(String str);
}
