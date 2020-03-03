package com.xiaomi.plugin;

public interface IXmPluginActivity {
    public static final String EXTRA_CLASS = "extra_class";

    void attach(IXmPluginHostActivity iXmPluginHostActivity, XmPluginPackage xmPluginPackage);
}
