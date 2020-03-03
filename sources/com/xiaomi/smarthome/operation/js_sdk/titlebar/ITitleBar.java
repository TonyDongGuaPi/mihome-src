package com.xiaomi.smarthome.operation.js_sdk.titlebar;

public interface ITitleBar {
    void onBackPressed(boolean z);

    void onProgressChanged(int i);

    void onReceivedTitle(String str);

    void setNavigationBar(String str);

    void setNavigationBarLoading(String str);

    void setOptionButton(String str);

    void setPopMenu(String str);

    void setShareButtonEnable(boolean z);
}
