package com.xiaomi.plugin;

import android.content.Context;
import android.content.Intent;

public interface IXmPluginMessageReceiver {
    public static final int LAUNCHER = 1;
    public static final int MSG_CUSTOM_START = 10000;
    public static final int MSG_LAUNCHER = 1;
    public static final int PUSH_MESSAGE = 2;

    boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int i, Intent intent);

    XmPluginBaseFragment newFragment(Context context, XmPluginPackage xmPluginPackage, Intent intent);
}
