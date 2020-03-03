package com.xiaomi.smarthome.device.api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import com.xiaomi.plugin.core.XmPluginPackage;

public interface IXmPluginMessageReceiver {
    public static final int DEVICE_LIST_MAIN_VIEW = 1;
    public static final int LAUNCHER = 1;
    public static final int MSG_BLE_CHARACTER_CHANGED = 22;
    public static final int MSG_BLUETOOTH_DISCONNECT = 9;
    public static final int MSG_BLUETOOTH_PAIRING = 8;
    public static final int MSG_BROADCAST = 15;
    public static final int MSG_BROADCAST_BLUETOOTH_DEVICE_ACTION_ACL_CONNECTED = 6;
    public static final int MSG_BROADCAST_BLUETOOTH_DEVICE_ACTION_ACL_DISCONNECTED = 7;
    public static final int MSG_CUSTOM_START = 10000;
    public static final int MSG_DESTROY_REQUEST_CAMERA_FRAME = 21;
    public static final int MSG_DEVICE_DELETED = 11;
    public static final int MSG_DEVICE_LIST_STATUS = 5;
    public static final int MSG_GET_SCENE_VALUE = 3;
    public static final int MSG_INIT_CAMERA_FRAME_SENDER = 18;
    public static final int MSG_LAUNCHER = 1;
    public static final int MSG_NOTIFICATION_PENDING_INTENT = 17;
    public static final int MSG_PAGE_NAVIGATE = 4;
    public static final int MSG_PUSH_MESSAGE = 2;
    public static final int MSG_SCENE_GET_CONDITION_EXTRA = 10;
    public static final int MSG_SET_SCENE_LARGE_EXTRA = 14;
    public static final int MSG_STAR_REQUEST_CAMERA_FRAME = 19;
    public static final int MSG_STOP_REQUEST_CAMERA_FRAME = 20;
    public static final int MSG_UPNP_CONNECT = 12;
    public static final int MSG_UPNP_DISCONNECT = 13;
    public static final int MSG_UPNP_EVENT = 16;
    public static final int PUSH_MESSAGE = 2;

    BaseWidgetView createWidgetView(Context context, LayoutInflater layoutInflater, XmPluginPackage xmPluginPackage, int i, Intent intent, DeviceStat deviceStat);

    boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int i, Intent intent, DeviceStat deviceStat);

    boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int i, Intent intent, DeviceStat deviceStat, MessageCallback messageCallback);
}
