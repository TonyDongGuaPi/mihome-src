package com.xiaomi.smarthome.frame.plugin.runtime.activity;

import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;

public interface IPluginRnActivity {
    String getDeviceModel();

    PluginRecord getPluginRecord();
}
