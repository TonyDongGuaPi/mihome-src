package com.xiaomi.smarthome.device.api;

import com.xiaomi.plugin.core.XmPluginPackage;

public interface IXmPluginActivity {
    public static final String EXTRA_CLASS = "extra_class";
    public static final String EXTRA_PACKAGE = "extra_package";

    void attach(IXmPluginHostActivity iXmPluginHostActivity, XmPluginPackage xmPluginPackage, DeviceStat deviceStat);
}
