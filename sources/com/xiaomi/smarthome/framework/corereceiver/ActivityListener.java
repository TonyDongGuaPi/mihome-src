package com.xiaomi.smarthome.framework.corereceiver;

public interface ActivityListener {
    void onActivityResume(int i, int i2, String str);

    void onServerChanged();

    void onUnauthorized();
}
