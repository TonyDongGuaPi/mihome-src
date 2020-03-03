package com.xiaomi.smarthome.homeroom;

import com.xiaomi.smarthome.homeroom.model.Room;
import java.util.Map;

interface HomeEditActivityBridge {
    Map<String, Boolean> getDeviceSelectedStatus();

    Room getEditRoom();

    String getEditText();

    void updateSelectedCountTip(String str);
}
