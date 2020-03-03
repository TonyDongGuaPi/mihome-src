package com.xiaomi.smarthome.camera.activity.alarm;

import com.mijia.model.alarm.AlarmItem;

public class AlarmDayBean {
    AlarmItem item = null;
    String title = "";

    AlarmDayBean(String str, AlarmItem alarmItem) {
        this.item = alarmItem;
        this.title = str;
    }
}
