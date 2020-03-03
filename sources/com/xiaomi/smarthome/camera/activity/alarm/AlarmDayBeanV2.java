package com.xiaomi.smarthome.camera.activity.alarm;

import com.mijia.model.alarm.AlarmItemV2;

public class AlarmDayBeanV2 {
    AlarmItemV2 item = null;
    String title = "";

    AlarmDayBeanV2(String str, AlarmItemV2 alarmItemV2) {
        this.item = alarmItemV2;
        this.title = str;
    }
}
